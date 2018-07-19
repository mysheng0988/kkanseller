package com.mysheng.office.kkanseller.holder;

import android.view.View;
import android.widget.ImageView;

import com.mysheng.office.kkanseller.R;
import com.mysheng.office.kkanseller.entity.ChatModel;


/**
 * Created by myaheng on 2018/5/11.
 */

public class TypeLeftImageViewHolder extends TypeAbstractViewHolder{
    private ImageView mImageView;
    private ImageView mContentImage;
    public TypeLeftImageViewHolder(View itemView) {
        super(itemView);
        mContentImage=itemView.findViewById(R.id.id_content_img);
        mImageView=itemView.findViewById(R.id.id_useIcon);
    }
    @Override
    public void bindHolder(Object model){
        if(model instanceof ChatModel){
            ChatModel chatModel= (ChatModel) model;
            mContentImage.setImageResource(R.drawable.timg);
            mImageView.setImageResource(R.drawable.icon);//图片应该加载当前用户的头像地址
        }
    }
}
