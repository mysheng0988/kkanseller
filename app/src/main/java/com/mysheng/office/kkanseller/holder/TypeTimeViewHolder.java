package com.mysheng.office.kkanseller.holder;

import android.view.View;
import android.widget.TextView;

import com.mysheng.office.kkanseller.R;
import com.mysheng.office.kkanseller.entity.ChatModel;
import com.mysheng.office.kkanseller.util.UtilDate;


/**
 * Created by myaheng on 2018/5/11.
 */

public class TypeTimeViewHolder extends TypeAbstractViewHolder{
    private TextView showTime;
    public TypeTimeViewHolder(View itemView) {
        super(itemView);
        showTime=itemView.findViewById(R.id.showTime);
    }
    @Override
    public void bindHolder(Object model,boolean isScrolling){
        if(model instanceof ChatModel){
            ChatModel chatModel= (ChatModel) model;
            String strTime= UtilDate.showDateTime(chatModel.getMesDate(),"yyyy-MM-dd HH:mm:ss");
            showTime.setText(strTime);

        }
    }
}
