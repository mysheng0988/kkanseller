package com.mysheng.office.kkanseller;

import android.app.Activity;
import android.app.Dialog;
import android.app.Service;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.mysheng.office.kkanseller.adpter.DescribeViewAdapter;
import com.mysheng.office.kkanseller.entity.DescribeModel;
import com.mysheng.office.kkanseller.holder.DescribeViewHolder;
import com.mysheng.office.kkanseller.util.TakePhotoSetting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by myaheng on 2018/9/6.
 */

public class DescribeActivity extends TakePhotoActivity implements View.OnClickListener{
    private View inflate;
    private RecyclerView describeView;
    private TakePhotoSetting takePhotoSetting;
    private TakePhoto mTakePhoto;
    private TextView choosePhoto;
    private TextView takePhoto;
    private TextView cancel;
    private ImageView comeBack;
    private Dialog dialog;
    private ItemTouchHelper mItemTouchHelper;
    private ImageView addMenu;
    private DescribeViewAdapter mAdapter;
    private List<String> mlist=new ArrayList<>();
    private List<DescribeModel> modelslist=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.describe_layout);
        mlist = (List<String>) getIntent().getSerializableExtra("imageLists");
        for(int i=0;i<mlist.size();i++){
            DescribeModel describeModel=new DescribeModel();
            describeModel.setImagePath(mlist.get(i));
            modelslist.add(describeModel);
        }
        initView();
        initEvent();
    }



    private void initView() {
        takePhotoSetting=new TakePhotoSetting();
        mTakePhoto=getTakePhoto();
        comeBack=findViewById(R.id.comeBack);
        addMenu=findViewById(R.id.addMenu);
        describeView=findViewById(R.id.describe_view);
        if(mAdapter==null){
            mAdapter=new DescribeViewAdapter(this);
        }else{
            mAdapter.notifyDataSetChanged();
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        describeView.setLayoutManager(linearLayoutManager);
        describeView.setAdapter(mAdapter);
        mAdapter.addList(modelslist);
        mAdapter.notifyDataSetChanged();
        describeView.addOnItemTouchListener(new OnRecyclerItemClickListener(describeView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
              //TODO 添加单机事件
            }

            @Override
            public void onItemLongClick(RecyclerView.ViewHolder vh) {
                //判断被拖拽的是否是前两个，如果不是则执行拖拽
//                if (vh.getLayoutPosition() != 0 && vh.getLayoutPosition() != 1) {
                    mItemTouchHelper.startDrag(vh);

                    //获取系统震动服务
                    Vibrator vib = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);//震动70毫秒
                    vib.vibrate(70);

               // }
            }
        });

        mItemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {

            /**
             * 是否处理滑动事件 以及拖拽和滑动的方向 如果是列表类型的RecyclerView的只存在UP和DOWN，如果是网格类RecyclerView则还应该多有LEFT和RIGHT
             * @param recyclerView
             * @param viewHolder
             * @return
             */
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                    final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN |
                            ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                    final int swipeFlags = 0;
                    return makeMovementFlags(dragFlags, swipeFlags);
                } else {
                    final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                    final int swipeFlags = 0;
//                    final int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
                    return makeMovementFlags(dragFlags, swipeFlags);
                }
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                //得到当拖拽的viewHolder的Position
                int fromPosition = viewHolder.getAdapterPosition();
                //拿到当前拖拽到的item的viewHolder
                int toPosition = target.getAdapterPosition();
                if (fromPosition < toPosition) {
                    for (int i = fromPosition; i < toPosition; i++) {
                        Collections.swap(mAdapter.getImagesList(),i,i+1);
                    }
                } else {
                    for (int i = fromPosition; i > toPosition; i--) {
                        Collections.swap(mAdapter.getImagesList(),i,i-1);
                    }
                }
                mAdapter.notifyItemMoved(fromPosition, toPosition);

                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//
            }

            /**
             * 重写拖拽可用
             * @return
             */
            @Override
            public boolean isLongPressDragEnabled() {
                return false;
            }

            /**
             * 长按选中Item的时候开始调用
             *
             * @param viewHolder
             * @param actionState
             */
            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                    viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
                }
                super.onSelectedChanged(viewHolder, actionState);
            }

            /**
             * 手指松开的时候还原
             * @param recyclerView
             * @param viewHolder
             */
            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                viewHolder.itemView.setBackgroundResource(R.drawable.bg_item_radius);
               // banner.update(imageViewAdapter.getImagesList(),viewHolder.getLayoutPosition()+1);

            }
        });

        mItemTouchHelper.attachToRecyclerView(describeView);

    }
    private void initEvent() {
        comeBack.setOnClickListener(this);
        addMenu.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addMenu:
                show();
                break;
            case R.id.comeBack:
                finish();
                break;
            default:
                break;
        }
    }


    @Override
    public void takeCancel() {
        super.takeCancel();
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
    }

    @Override
    public void takeSuccess(TResult result) {
        List<String>list=new ArrayList<>();
        ArrayList<TImage> images=result.getImages();

        for (int i = 0;i<images.size(); i ++) {
            Log.d("mmm", "takeSuccess: "+images.get(i).getCompressPath());
            list.add(images.get(i).getCompressPath());

        }

        Message msg =new Message();
        msg.obj = list;//可以是基本类型，可以是对象，可以是List、map等；
        mHandler.sendMessage(msg);
        //mGridImageView.setImageData(list,false);

    }
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.obj!=null){
                List<String> list= (List<String>) msg.obj;

            }


        }
    };

    public void show(){
        dialog = new Dialog(this,R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        inflate = LayoutInflater.from(this).inflate(R.layout.dialog_button_layout, null);
        //初始化控件
        choosePhoto =  inflate.findViewById(R.id.choosePhoto);
        takePhoto = inflate.findViewById(R.id.takePhoto);
        cancel=inflate.findViewById(R.id.btn_cancel);
        choosePhoto.setOnClickListener(this);
        takePhoto.setOnClickListener(this);
        cancel.setOnClickListener(this);
        //将布局设置给Dialog
        dialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity( Gravity.BOTTOM);
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.width = (int) (d.getWidth() * 0.95); // 宽度设置为屏幕的0.95
        lp.y = 20;//设置Dialog距离底部的距离
        dialogWindow.setAttributes(lp);
        dialog.show();//显示对话框
    }
}
