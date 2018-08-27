package com.mysheng.office.kkanseller.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mysheng.office.kkanseller.R;
import com.mysheng.office.kkanseller.entity.Goods;


/**
 * Created by myaheng on 2018/7/17.
 */

public class GoodsListViewHolder extends  RecyclerView.ViewHolder{

    public ImageView goodsIcon;
    public TextView goodsName;
    public TextView goodsPrice;
    public TextView saleAmount;



    public GoodsListViewHolder(View itemView) {
        super(itemView);
        goodsIcon = itemView.findViewById(R.id.goodsIcon);
        goodsName = itemView.findViewById(R.id.goodsName);
        goodsPrice = itemView.findViewById(R.id.goodsPrice);
        saleAmount = itemView.findViewById(R.id.saleAmount);

    }
    public void bindHolder(Goods goods){
        Glide.with(goodsIcon.getContext()).load(goods.getGoodsPath()).into(goodsIcon);
        goodsName.setText(goods.getGoodsName());
        goodsPrice.setText(goods.getGoodsPrice());
        saleAmount.setText(goods.getSaleAmount());
    }

}
