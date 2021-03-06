package com.mysheng.office.kkanseller.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mysheng.office.kkanseller.R;
import com.mysheng.office.kkanseller.entity.ChatModel;


/**
 * Created by myaheng on 2018/5/11.
 */

public class TypeLeftRecorderViewHolder extends TypeAbstractViewHolder{
    private ImageView mImageView;
    private View mImage;
    private TextView mTextView;

    public TypeLeftRecorderViewHolder(View itemView) {
        super(itemView);
        mImage = itemView.findViewById(R.id.id_anim);
        mImageView = itemView.findViewById(R.id.id_useIcon);
        mTextView=itemView.findViewById(R.id.id_recorder_time);
    }
    @Override
    public void bindHolder(Object model,boolean isScrolling){
        if(model instanceof ChatModel){
            ChatModel chatModel= (ChatModel) model;
           // mImage.setBackgroundResource(R.drawable.adj_l);
            mTextView.setText(chatModel.mesTime+"\"");
            mImageView.setImageResource(R.drawable.icon);//图片应该加载当前用户的头像地址
        }
    }
}
