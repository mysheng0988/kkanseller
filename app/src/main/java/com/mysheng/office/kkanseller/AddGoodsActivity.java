package com.mysheng.office.kkanseller;
import android.app.Dialog;
import android.app.Service;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v7.widget.GridLayoutManager;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.mysheng.office.kkanseller.adpter.AddImageViewAdapter;
import com.mysheng.office.kkanseller.adpter.GridImageViewAdapter;
import com.mysheng.office.kkanseller.banner.Banner;
import com.mysheng.office.kkanseller.banner.BannerConfig;
import com.mysheng.office.kkanseller.banner.GlideImageLoader;
import com.mysheng.office.kkanseller.util.TakePhotoSetting;
import com.mysheng.office.kkanseller.view.GridImageView;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by myaheng on 2018/8/30.
 */

public class AddGoodsActivity extends TakePhotoActivity implements View.OnClickListener{
    private RecyclerView bannerRecyclerView;
    private AddImageViewAdapter imageViewAdapter;
    private View inflate;
    private Banner banner;
    private List<String> addList=new ArrayList<>();
    private TakePhotoSetting takePhotoSetting;
    private TakePhoto mTakePhoto;
    private Button choosePhoto;
    private Button takePhoto;
    private Button cancel;
    private TextView title;
    private Dialog dialog;
    private ItemTouchHelper mItemTouchHelper;
    public static String[] offImages={
            "http://ww3.sinaimg.cn/woriginal/75d91745gw1f0echzpyx6j20sg13pqg3.jpg",
            "http://ww4.sinaimg.cn/woriginal/75d91745gw1f0eci2262gj20sg14bh14.jpg",
            "http://ww3.sinaimg.cn/woriginal/75d91745gw1f0eci5qk5kj20sg12bdt3.jpg",
            "http://ww1.sinaimg.cn/woriginal/75d91745gw1f0eci7jn0mj20sg0k80wq.jpg",
            "http://ww2.sinaimg.cn/woriginal/75d91745gw1f0eci3mwbqj20go0nbwkd.jpg",
            "http://ww3.sinaimg.cn/woriginal/75d91745gw1f0eci9sybpj20sg14enbf.jpg",
            "http://ww2.sinaimg.cn/woriginal/75d91745gw1f0ecif04ocj20sg14mqlb.jpg",
            "http://ww1.sinaimg.cn/woriginal/75d91745gw1f0ecigzhl9j20sg138gz4.jpg",
            "http://ww3.sinaimg.cn/woriginal/75d91745gw1f0echzpyx6j20sg13pqg3.jpg",
            "http://ww4.sinaimg.cn/woriginal/75d91745gw1f0eci2262gj20sg14bh14.jpg",
            "http://ww3.sinaimg.cn/woriginal/75d91745gw1f0eci5qk5kj20sg12bdt3.jpg",
            "http://ww1.sinaimg.cn/woriginal/75d91745gw1f0eci7jn0mj20sg0k80wq.jpg",
            "http://ww2.sinaimg.cn/woriginal/75d91745gw1f0eci3mwbqj20go0nbwkd.jpg",
            "http://ww3.sinaimg.cn/woriginal/75d91745gw1f0eci9sybpj20sg14enbf.jpg",
            "http://ww2.sinaimg.cn/woriginal/75d91745gw1f0ecif04ocj20sg14mqlb.jpg",
            "http://ww1.sinaimg.cn/woriginal/75d91745gw1f0ecigzhl9j20sg138gz4.jpg"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_goods_layout);
        initTakePhotoView();
        initBannerView();
    }

    private void initTakePhotoView() {
        takePhotoSetting=new TakePhotoSetting();
        mTakePhoto=getTakePhoto();
    }

    private void initBannerView() {
        banner=findViewById(R.id.id_banner);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setImageLoader(new GlideImageLoader());
        for (int i=0;i<offImages.length;i++){
            addList.add(offImages[i]);
        }
        banner.setImages(addList);
        banner.isAutoPlay(false);
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        banner.start();
        bannerRecyclerView=findViewById(R.id.bannerRecyclerView);
        bannerRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        imageViewAdapter=new AddImageViewAdapter(this);
        imageViewAdapter.setItemClickListener(new AddImageViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, List<String> list) {
                switch (view.getId()){
                    case R.id.resImage:
                        banner.start(position+1);
                        break;
                    case R.id.delImage:
                        imageViewAdapter.removeData(position);

                        banner.update(imageViewAdapter.getImagesList(),1);
                        imageViewAdapter.notifyDataSetChanged();
                        break;
                }
            }
        });

        bannerRecyclerView.setAdapter(imageViewAdapter);
        imageViewAdapter.addList(addList);
        imageViewAdapter.notifyDataSetChanged();
        bannerRecyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(bannerRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                Toast.makeText(AddGoodsActivity.this, addList.get(vh.getLayoutPosition()), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(RecyclerView.ViewHolder vh) {
                //判断被拖拽的是否是前两个，如果不是则执行拖拽
                if (vh.getLayoutPosition() != 0 && vh.getLayoutPosition() != 1) {
                    mItemTouchHelper.startDrag(vh);

                    //获取系统震动服务
                    Vibrator vib = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);//震动70毫秒
                    vib.vibrate(70);

                }
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
                        Collections.swap(addList, i, i + 1);
                    }
                } else {
                    for (int i = fromPosition; i > toPosition; i--) {
                        Collections.swap(addList, i, i - 1);
                    }
                }
                imageViewAdapter.notifyItemMoved(fromPosition, toPosition);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//                imageViewAdapter.notifyDataSetChanged();
//
//                banner.update(imageViewAdapter.getImagesList(),1);
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
                viewHolder.itemView.setBackgroundColor(0);

            }
        });

        mItemTouchHelper.attachToRecyclerView(bannerRecyclerView);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.choosePhoto:
                takePhotoSetting.pickBySelectImage(mTakePhoto);
                dialog.dismiss();
                break;
            case R.id.takePhoto:
                takePhotoSetting.pickByTakeImage(mTakePhoto);
                dialog.dismiss();
                break;
            case R.id.btn_cancel:
                dialog.dismiss();
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

//                mGridImageView.setImageData(list,false);
//                banner.update(mGridImageView.getImgDataList(),1);
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
    public void onBtnClick(View view){
        finish();
    }
}