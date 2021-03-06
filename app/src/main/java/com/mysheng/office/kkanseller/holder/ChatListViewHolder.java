package com.mysheng.office.kkanseller.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mysheng.office.kkanseller.R;
import com.mysheng.office.kkanseller.entity.ChatListModel;
import com.mysheng.office.kkanseller.util.UtilDate;


/**
 * Created by myaheng on 2018/7/17.
 */

public class ChatListViewHolder extends  RecyclerView.ViewHolder{

    public TextView userId;
    public ImageView userIcon;
    public TextView userName;
    public TextView unReadNum;
    public TextView lastMsg;
    public TextView lastMsgData;



    public ChatListViewHolder(View itemView) {
        super(itemView);
        userId = itemView.findViewById(R.id.userId);
        userName = itemView.findViewById(R.id.userName);
        unReadNum = itemView.findViewById(R.id.unReadNum);
        userIcon = itemView.findViewById(R.id.userIcon);
        lastMsg = itemView.findViewById(R.id.lastMsg);
        lastMsgData = itemView.findViewById(R.id.lastMsgData);
    }
    public void bindHolder(ChatListModel model){
        userId.setText(model.getUserId());
       // Glide.with(userIcon.getContext()).load(model.getUserIcon()).into(userIcon);
        userIcon.setImageResource(R.drawable.icon);
        userName.setText(model.getUserName());
        if(model.getUnReadNum()==0){
            unReadNum.setVisibility(View.GONE);
        }else{
            unReadNum.setText(model.getUnReadNum()+"");
            unReadNum.setVisibility(View.VISIBLE);
        }
        lastMsg.setText(model.getLastMsg());
        lastMsgData.setText(UtilDate.showDate(model.getLastMsgData(),"yyyy-MM-dd HH:mm:ss"));
    }

}
