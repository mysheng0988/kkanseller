package com.mysheng.office.kkanseller.adpter;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;


import com.mysheng.office.kkanseller.R;
import com.mysheng.office.kkanseller.view.GridImageView;

import java.util.List;

/**
 * @param <T> 指定类型
 */
public abstract class GridImageViewAdapter<T> {
    public abstract void onDisplayImage(Context context, ImageView imageView,  T path);

    public abstract void onAddClick(Context context, List<T> list);

    public void onItemImageClick(ImageView imageView,List<ImageView> group,int index, List<T> list) {

    }
    public void onItemDelImageClick(T path) {

    }
    public int getShowStyle() {
        return GridImageView.STYLE_GRID;
    }

    public
    @DrawableRes
    int generateAddIcon() {
        return R.drawable.add_image;
    }
}