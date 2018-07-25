package com.mysheng.office.kkanseller;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * Created by myaheng on 2018/6/30.
 */

public class GoodsFragment extends Fragment implements View.OnClickListener{
    private ImageView addMenu;
    private View inflate;
    private Dialog dialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view= inflater.inflate(R.layout.tab03, container, false);
        addMenu=view.findViewById(R.id.addMenu);
        addMenu.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addMenu:
                showDialog();
                break;
        }
    }

    public void showDialog(){
        dialog = new Dialog(getActivity(),R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        inflate = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_menu_layout, null);
        //初始化控件
//        choosePhoto =  inflate.findViewById(R.id.choosePhoto);
//        takePhoto = inflate.findViewById(R.id.takePhoto);
//        cancel=inflate.findViewById(R.id.btn_cancel);
//        choosePhoto.setOnClickListener(this);
//        takePhoto.setOnClickListener(this);
//        cancel.setOnClickListener(this);
        //将布局设置给Dialog
        dialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity( Gravity.BOTTOM);
        WindowManager m = getActivity().getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.width = (int) (d.getWidth() * 0.95); // 宽度设置为屏幕的0.95
        lp.y = 20;//设置Dialog距离底部的距离
        dialogWindow.setAttributes(lp);
        dialog.show();//显示对话框
    }
}
