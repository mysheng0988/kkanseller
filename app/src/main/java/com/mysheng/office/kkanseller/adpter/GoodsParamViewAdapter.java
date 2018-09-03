package com.mysheng.office.kkanseller.adpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mysheng.office.kkanseller.R;
import com.mysheng.office.kkanseller.entity.Goods;
import com.mysheng.office.kkanseller.entity.GoodsParam;
import com.mysheng.office.kkanseller.holder.GoodsParamViewHolder;

import java.util.ArrayList;

/**
 * @Author: duke
 * @DateTime: 2017-01-03 22:24
 * @Description:
 */
public class GoodsParamViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<GoodsParam> lists = new ArrayList<>();
    private OnItemClickListener mItemClickListener;
    private LayoutInflater mLayoutInflater;
    public GoodsParamViewAdapter(Context context) {
        GoodsParam param=new GoodsParam();
        addModel(param);
        mLayoutInflater=LayoutInflater.from(context);
    }

    @Override
    public GoodsParamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /**
         * 加载滑动布局item_root，其中已经包含了content和optinos布局
         */
        View view=mLayoutInflater.inflate(R.layout.item_param_layout, parent, false);
        return new GoodsParamViewHolder(view);
    }
    public void addModel(GoodsParam model){
        if(lists.size()==1&&TextUtils.isEmpty(lists.get(0).getSpecNameType1())){
           removeData(0);
           notifyDataSetChanged();
        }
        lists.add(model);

    }
    public void removeData(int position) {
        lists.remove(position);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        GoodsParamViewHolder viewHolder= (GoodsParamViewHolder) holder;
        viewHolder.bindHolder(lists.get(position));
        ImageView imageView=viewHolder.itemView.findViewById(R.id.deleteItem);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mItemClickListener!=null){
                    mItemClickListener.onItemClick(v,position);
                }

            }
        });
    }



    @Override
    public int getItemCount() {
        return lists.size();
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
}