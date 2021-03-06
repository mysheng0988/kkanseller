package com.mysheng.office.kkanseller.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mysheng.office.kkanseller.R;
import com.mysheng.office.kkanseller.entity.ChatModel;


/**
 * Created by myaheng on 2018/5/11.
 */

public class TypeRightTextViewHolder extends TypeAbstractViewHolder{
    private ImageView mImageView;
    private TextView mTextView;
    public TypeRightTextViewHolder(View itemView) {
        super(itemView);
        mTextView=itemView.findViewById(R.id.id_content_text);
        mImageView=itemView.findViewById(R.id.id_useIcon);
    }

    @Override
    public void bindHolder(Object model,boolean isScrolling){
        if(model instanceof ChatModel){
            ChatModel chatModel= (ChatModel) model;
            mTextView.setText(chatModel.content);
            mImageView.setImageResource(R.drawable.ynn);
        }
    }

}
