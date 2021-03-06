package com.mysheng.office.kkanseller.holder;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mysheng.office.kkanseller.R;
import com.mysheng.office.kkanseller.entity.ChatModel;


/**
 * Created by myaheng on 2018/5/11.
 */

public class TypeRightRecorderViewHolder extends TypeAbstractViewHolder{
    private ImageView mImageView;
   // private ImageView mContentImage;
    private TextView mTextView;
    public TypeRightRecorderViewHolder(View itemView) {
        super(itemView);
       // mContentImage = itemView.findViewById(R.id.id_content_img);
        mImageView = itemView.findViewById(R.id.id_useIcon);
        mTextView=itemView.findViewById(R.id.id_recorder_time);
    }
    @Override
    public void bindHolder(Object model,boolean isScrolling){
        if(model instanceof ChatModel){
            ChatModel chatModel= (ChatModel) model;
            //mContentImage.setImageResource(R.drawable.ynn);
            Log.d("mys", "bindHolder: "+ chatModel.mesTime);
            mTextView.setText( chatModel.mesTime+"\"");
            mImageView.setImageResource(R.drawable.ynn);//图片应该加载当前用户的头像地址
        }
    }
}
