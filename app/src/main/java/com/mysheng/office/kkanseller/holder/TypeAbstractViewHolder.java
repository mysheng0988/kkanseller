package com.mysheng.office.kkanseller.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by myaheng on 2018/5/11.
 */

public abstract class TypeAbstractViewHolder extends RecyclerView.ViewHolder{
    public TypeAbstractViewHolder(View itemView) {
        super(itemView);
    }
    public abstract void bindHolder(Object model);
}
