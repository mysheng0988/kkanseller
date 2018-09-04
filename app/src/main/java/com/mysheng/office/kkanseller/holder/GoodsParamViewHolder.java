package com.mysheng.office.kkanseller.holder;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mysheng.office.kkanseller.R;
import com.mysheng.office.kkanseller.entity.GoodsParam;


/**
 * Created by myaheng on 2018/7/17.
 */

public class GoodsParamViewHolder extends  RecyclerView.ViewHolder{

    public ImageView deleteItem;
    public EditText specName1;
    public EditText specName2;
    public EditText price;
    public EditText inventory;
    private LinearLayout spec1;
    private LinearLayout spec2;
    private TextView specNameType1;
    private TextView specNameType2;



    public GoodsParamViewHolder(View itemView) {
        super(itemView);
        spec1 = itemView.findViewById(R.id.spec1);
        spec2 = itemView.findViewById(R.id.spec2);
        specNameType1 = itemView.findViewById(R.id.specNameType1);
        specNameType2 = itemView.findViewById(R.id.specNameType2);
        deleteItem = itemView.findViewById(R.id.deleteItem);
        specName1 = itemView.findViewById(R.id.specType1);
        specName2 = itemView.findViewById(R.id.specType1);
        price = itemView.findViewById(R.id.price);
        inventory = itemView.findViewById(R.id.inventory);
    }
    public void bindHolder(GoodsParam param){
        if(param.isFirstOnly()) {
            spec1.setVisibility(View.GONE);
            spec2.setVisibility(View.GONE);
            deleteItem.setVisibility(View.GONE);
        }else {
            spec1.setVisibility(View.VISIBLE);
            spec2.setVisibility(View.VISIBLE);
            deleteItem.setVisibility(View.VISIBLE);
            specNameType1.setText(param.getSpecNameType1()+":");
            specNameType2.setText(param.getSpecNameType2()+":");
        }
        specName1.setText(param.getSpecName1());
        specName2.setText(param.getSpecName2());
        price.setText(param.getPrice());
        inventory.setText(param.getInventory());
    }

}
