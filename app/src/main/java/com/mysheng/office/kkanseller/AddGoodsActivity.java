package com.mysheng.office.kkanseller;
import android.app.Dialog;
import android.app.Service;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v7.widget.DividerItemDecoration;
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
import android.widget.Toast;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.mysheng.office.kkanseller.adpter.AddImageViewAdapter;
import com.mysheng.office.kkanseller.adpter.GoodsParamViewAdapter;
import com.mysheng.office.kkanseller.banner.Banner;
import com.mysheng.office.kkanseller.banner.BannerConfig;
import com.mysheng.office.kkanseller.banner.GlideImageLoader;
import com.mysheng.office.kkanseller.util.DividerGridItemDecoration;
import com.mysheng.office.kkanseller.util.TakePhotoSetting;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by myaheng on 2018/8/30.
 */

public class AddGoodsActivity extends TakePhotoActivity implements View.OnClickListener{
    private RecyclerView bannerRecyclerView;
    private RecyclerView paramRecyclerView;
    private AddImageViewAdapter imageViewAdapter;
    private GoodsParamViewAdapter paramViewAdapter;
    private View inflate;
    private Banner banner;
    private List<String> addList=new ArrayList<>();
    private TakePhotoSetting takePhotoSetting;
    private TakePhoto mTakePhoto;
    private Button choosePhoto;
    private Button takePhoto;
    private Button cancel;
    private ImageView comeBack;
    private Dialog dialog;
    private ItemTouchHelper mItemTouchHelper;
    public static String[] offImages={
            "http://wx1.sinaimg.cn/woriginal/daaf97d2gy1fgsxkq8uc3j20dw0ku74x.jpg",
            "http://wx1.sinaimg.cn/woriginal/daaf97d2gy1fgsxkqm7b0j20dw0kut9h.jpg",
            "http://wx4.sinaimg.cn/woriginal/daaf97d2gy1fgsxks2l4ij20dw0kldhb.jpg",
            "http://wx2.sinaimg.cn/woriginal/daaf97d2gy1fgsxksskbkj20dw0kut9b.jpg",
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2966021298,3341101515&fm=23&gp=0.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496402134202&di=6c7f4a6afa5bdf02000c788f7a51e9c0&imgtype=0&src=http%3A%2F%2Fcdnq.duitang.com%2Fuploads%2Fitem%2F201506%2F23%2F20150623183946_iZtFs.jpeg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496996892&di=ea1e213c8ddd4427c55f073db9bf91b7&imgtype=jpg&er=1&src=http%3A%2F%2Fpic27.nipic.com%2F20130323%2F9483785_182530048000_2.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496996959&di=13c094ba73675a24df2ad1d2c730c02c&imgtype=jpg&er=1&src=http%3A%2F%2Fdasouji.com%2Fwp-content%2Fuploads%2F2015%2F07%2F%25E9%2595%25BF%25E8%258A%25B1%25E5%259B%25BE-6.jpg"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_goods_layout);
        initTakePhotoView();
        initBannerView();
        initParamView();
    }

    private void initTakePhotoView() {
        comeBack=findViewById(R.id.comeBack);
        comeBack.setOnClickListener(this);
        takePhotoSetting=new TakePhotoSetting();
        mTakePhoto=getTakePhoto();
    }
    private void initParamView() {
        paramRecyclerView=findViewById(R.id.paramRecyclerView);
        paramRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        paramRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        paramViewAdapter=new GoodsParamViewAdapter(this);
        paramRecyclerView.setAdapter(paramViewAdapter);
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
        bannerRecyclerView.addItemDecoration(new DividerGridItemDecoration());
        imageViewAdapter=new AddImageViewAdapter(this);
        imageViewAdapter.setItemClickListener(new AddImageViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(AddGoodsActivity.this, position+"", Toast.LENGTH_SHORT).show();
                imageViewAdapter.getImagesList().remove(position);
                banner.update(imageViewAdapter.getImagesList(),position+1);
                imageViewAdapter. notifyItemRemoved(position);
                imageViewAdapter.notifyItemRangeChanged(position, imageViewAdapter.getItemCount());;
            }
        });

        bannerRecyclerView.setAdapter(imageViewAdapter);
        imageViewAdapter.addList(addList);
        imageViewAdapter.notifyDataSetChanged();
        bannerRecyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(bannerRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
               banner.start(vh.getLayoutPosition()+1);
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
                        Collections.swap(imageViewAdapter.getImagesList(),i,i+1);
                    }
                } else {
                    for (int i = fromPosition; i > toPosition; i--) {
                        Collections.swap(imageViewAdapter.getImagesList(),i,i-1);
                    }
                }
                imageViewAdapter.notifyItemMoved(fromPosition, toPosition);

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
                banner.update(imageViewAdapter.getImagesList(),viewHolder.getLayoutPosition()+1);

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


                banner.update(list,1);
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