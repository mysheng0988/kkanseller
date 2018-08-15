package com.mysheng.office.kkanseller.holder;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mysheng.office.kkanseller.R;
import com.mysheng.office.kkanseller.entity.ChatModel;
import com.mysheng.office.kkanseller.util.ChatTool;


/**
 * Created by myaheng on 2018/5/11.
 */

public class TypeRightImageViewHolder extends TypeAbstractViewHolder{
    private ImageView mImageView;
    private ImageView mContentImage;
    ViewGroup.LayoutParams para;
    public TypeRightImageViewHolder(View itemView) {
        super(itemView);
        mContentImage=itemView.findViewById(R.id.id_content_img);
        mImageView=itemView.findViewById(R.id.id_useIcon);
    }
    @Override
    public void bindHolder(Object model){
        if(model instanceof ChatModel){
            ChatModel chatModel= (ChatModel) model;
            Log.e("mys", "H: "+chatModel.getHeight());
            Log.e("mys", "W: "+chatModel.getWidth());
            para = mContentImage.getLayoutParams();
            if(chatModel.getWidth()<chatModel.getHeight()){
                para.height = ChatTool.VIEW_WIDTH;
                para.width = ChatTool.VIEW_HEIGHT;
                mContentImage.setLayoutParams(para);
            }else{
                para.height = ChatTool.VIEW_HEIGHT;
                para.width = ChatTool.VIEW_WIDTH;
                mContentImage.setLayoutParams(para);
            }
            if(chatModel.getContentPath()==null){
                mContentImage.setImageResource(R.drawable.bg);
            }else {
                Glide.with(mContentImage.getContext()).load("file://"+((ChatModel) model).getContentPath()).into(mContentImage);
            }

            mImageView.setImageResource(R.drawable.ynn);//图片应该加载当前用户的头像地址
        }
    }
}
