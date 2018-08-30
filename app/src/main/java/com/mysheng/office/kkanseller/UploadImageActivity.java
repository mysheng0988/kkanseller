package com.mysheng.office.kkanseller;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.mysheng.office.kkanseller.adpter.GridImageViewAdapter;
import com.mysheng.office.kkanseller.customCamera.dialog.LoadingDialog;
import com.mysheng.office.kkanseller.util.TakePhotoSetting;
import com.mysheng.office.kkanseller.view.GridImageView;
import java.io.File;
import java.util.ArrayList;
import java.util.List;




public class UploadImageActivity extends TakePhotoActivity implements View.OnClickListener {

    private GridImageView<String> mGridImageView;
    private View inflate;
    private Button button;
    private Dialog dialog;
    private Button choosePhoto;
    private Button takePhoto;
    private Button cancel;
    private TextView title;
    private EditText remark;
    private RadioGroup radioGroup;
    private LinearLayout uploadFile;
    private File imagePath;
    private String dir;
    private String PATRURL="";
    private String loginUser_id;
    private String SessionKey;
    private LoadingDialog mDialog;
    private TakePhotoSetting takePhotoSetting;
    private TakePhoto mTakePhoto;
    boolean isTranslucentStatus = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_layout);
        mGridImageView= findViewById(R.id.gridImageView);
        button=findViewById(R.id.button);
        title= findViewById(R.id.common_title);
        remark= findViewById(R.id.remark);

        uploadFile=findViewById(R.id.uploadFile);
        takePhotoSetting=new TakePhotoSetting();
        mTakePhoto=getTakePhoto();
        uploadFile.setOnClickListener(this);
        //initImageWatcher();
        title.setText("商品评价");
        button.setOnClickListener(this);
        mGridImageView.setAdapter(new GridImageViewAdapter<String>() {
            @Override
            public void onDisplayImage(Context context, ImageView imageView, String path) {
                Glide.with(context).load("file://"+path).centerCrop().override(400, 400).into(imageView);
            }

            @Override
            public void onAddClick(Context context, List<String> list) {
                show();
            }

            @Override
            public int getShowStyle() {
                return GridImageView.STYLE_GRID;
            }

            @Override
            public void onItemImageClick(ImageView imageView,List<ImageView> imageViews,int index, List<String> list) {
                super.onItemImageClick(imageView,imageViews,index, list);
                Toast.makeText(getApplicationContext(), "--->" + index, Toast.LENGTH_SHORT).show();
            }
        });


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
                mGridImageView.setImageData(list,false);

            }


        }
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                break;
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
            case R.id.uploadFile:
                editTextClearFocus();
                break;
            default:
                break;


        }
    }
    private void editTextClearFocus(){
        Log.d("ClearFocus", "onClick: "+2222);
        uploadFile.setFocusable(true);
        uploadFile.setFocusableInTouchMode(true);
        uploadFile.requestFocus();
        InputMethodManager imm = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(uploadFile.getWindowToken(), 0);
    }
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
