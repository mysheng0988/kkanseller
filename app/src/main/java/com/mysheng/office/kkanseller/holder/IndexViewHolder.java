package com.mysheng.office.kkanseller.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mysheng.office.kkanseller.R;
import com.mysheng.office.kkanseller.entity.ChatListModel;
import com.mysheng.office.kkanseller.entity.IndexBean;
import com.mysheng.office.kkanseller.util.UtilDate;


/**
 * Created by myaheng on 2018/7/17.
 */

public class IndexViewHolder extends  RecyclerView.ViewHolder{

    public TextView ItemName;
    public TextView ItemNum;



    public IndexViewHolder(View itemView) {
        super(itemView);
        ItemName = itemView.findViewById(R.id.itemName);
        ItemNum = itemView.findViewById(R.id.itemNum);

    }
    public void bindHolder(IndexBean bean){
        ItemName.setText(bean.getItemName());
        ItemNum.setText(bean.getItemNum()+"");
    }

}
