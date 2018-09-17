package com.mysheng.office.kkanseller.holder;

import android.app.Application;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mysheng.office.kkanseller.KkanApplication;
import com.mysheng.office.kkanseller.R;
import com.mysheng.office.kkanseller.entity.Goods;


/**
 * Created by myaheng on 2018/7/17.
 */

public class AddImageViewHolder extends  RecyclerView.ViewHolder{

    public ImageView delImage;
    public ImageView resImage;


    public AddImageViewHolder(View itemView) {
        super(itemView);
        resImage=itemView.findViewById(R.id.resImage);
        delImage = itemView.findViewById(R.id.delImage);



    }
    public void bindHolder(String path){
        Glide.with(KkanApplication.mContext).load(path).into(resImage);

    }

}
