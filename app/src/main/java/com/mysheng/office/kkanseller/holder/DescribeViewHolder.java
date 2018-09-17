package com.mysheng.office.kkanseller.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mysheng.office.kkanseller.KkanApplication;
import com.mysheng.office.kkanseller.R;
import com.mysheng.office.kkanseller.entity.DescribeModel;
import com.mysheng.office.kkanseller.entity.IndexBean;
import com.mysheng.office.kkanseller.util.ChatTool;


/**
 * Created by myaheng on 2018/7/17.
 */

public class DescribeViewHolder extends  RecyclerView.ViewHolder{

    public ImageView imagePath;



    public DescribeViewHolder(View itemView) {
        super(itemView);
        imagePath = itemView.findViewById(R.id.describe_img);


    }
    public void bindHolder(DescribeModel model){
        String imageUrl=model.getImagePath();
        if(!ChatTool.isNetUri(imageUrl)){
            imageUrl="file://"+imagePath;
        }
        Glide.with(KkanApplication.mContext).load(imageUrl).into(imagePath);
    }

}
