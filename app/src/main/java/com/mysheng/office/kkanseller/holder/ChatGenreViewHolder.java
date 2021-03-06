package com.mysheng.office.kkanseller.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mysheng.office.kkanseller.R;
import com.mysheng.office.kkanseller.entity.ChatGenreBean;
import com.mysheng.office.kkanseller.entity.IndexBean;


/**
 * Created by myaheng on 2018/7/17.
 */

public class ChatGenreViewHolder extends  RecyclerView.ViewHolder{

    public ImageView genreImage;
    public TextView genreName;



    public ChatGenreViewHolder(View itemView) {
        super(itemView);
        genreImage = itemView.findViewById(R.id.genreImage);
        genreName = itemView.findViewById(R.id.genreName);

    }
    public void bindHolder(ChatGenreBean bean){
        genreImage.setImageResource(bean.getGenreImageId());
        genreName.setText(bean.getGenreName());
    }

}
