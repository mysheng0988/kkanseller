package com.mysheng.office.kkanseller.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mysheng.office.kkanseller.R;
import com.mysheng.office.kkanseller.entity.Goods;
import com.mysheng.office.kkanseller.util.UtilDate;


/**
 * Created by myaheng on 2018/7/17.
 */

public class GoodsListViewHolder extends  RecyclerView.ViewHolder{

    public ImageView goodsIcon;
    public TextView goodsName;
    public TextView goodsPrice;
    public TextView goodsInventory;
    public TextView saleAmount;
    public TextView addTime;



    public GoodsListViewHolder(View itemView) {
        super(itemView);
        goodsIcon = itemView.findViewById(R.id.goodsIcon);
        goodsName = itemView.findViewById(R.id.goodsName);
        goodsPrice = itemView.findViewById(R.id.goodsPrice);
        goodsInventory = itemView.findViewById(R.id.goodsInventory);
        saleAmount = itemView.findViewById(R.id.saleAmount);
        addTime = itemView.findViewById(R.id.addTime);


    }
    public void bindHolder(Goods goods){
        Glide.with(goodsIcon.getContext()).load(goods.getGoodsPath()).into(goodsIcon);
        goodsName.setText(goods.getGoodsName());
        goodsPrice.setText("￥:"+goods.getGoodsPrice()+"元");
        saleAmount.setText("销量:"+goods.getSaleAmount()+"件");
        addTime.setText("添加日期:"+goods.getAddTime());
        goodsInventory.setText("库存:"+goods.getGoodsInventory()+"件");
    }

}
