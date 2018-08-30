package com.mysheng.office.kkanseller.view;

import android.app.Dialog;
import android.content.Context;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mysheng.office.kkanseller.util.ChatTool;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by myaheng on 2018/5/16.
 */

public class GlideImageLoader extends ImageLoader {
    private Dialog dialog;
    private Button choosePhoto;
    private Button takePhoto;
    private Button cancel;
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        String imagePath= (String) path;
        if(!ChatTool.isNetUri(imagePath)){
            imagePath="file://"+imagePath;
        }
        Glide.with(context).load(imagePath).into(imageView);

    }
}
