package com.mysheng.office.kkanseller.holder;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mysheng.office.kkanseller.R;
import com.mysheng.office.kkanseller.entity.ChatModel;


/**
 * Created by myaheng on 2018/5/11.
 */

public class TypeRightImageViewHolder extends TypeAbstractViewHolder{
    private ImageView mImageView;
    private ImageView mContentImage;
    public TypeRightImageViewHolder(View itemView) {
        super(itemView);
        mContentImage=itemView.findViewById(R.id.id_content_img);
        mImageView=itemView.findViewById(R.id.id_useIcon);
    }
    @Override
    public void bindHolder(Object model){
        if(model instanceof ChatModel){
            ChatModel chatModel= (ChatModel) model;
            if(chatModel.getContentPath()==null){
                mContentImage.setImageResource(R.drawable.bg);
            }else {
                Glide.with(mContentImage.getContext()).load("file://"+((ChatModel) model).getContentPath()).into(mContentImage);
            }

            mImageView.setImageResource(R.drawable.ynn);//图片应该加载当前用户的头像地址
        }
    }
}
