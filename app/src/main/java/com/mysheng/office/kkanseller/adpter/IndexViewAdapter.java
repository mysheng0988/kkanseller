package com.mysheng.office.kkanseller.adpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mysheng.office.kkanseller.R;
import com.mysheng.office.kkanseller.entity.ChatListModel;
import com.mysheng.office.kkanseller.entity.ChatModel;
import com.mysheng.office.kkanseller.entity.IndexBean;
import com.mysheng.office.kkanseller.holder.ChatListViewHolder;
import com.mysheng.office.kkanseller.holder.IndexViewHolder;
import com.mysheng.office.kkanseller.view.SlideLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: duke
 * @DateTime: 2017-01-03 22:24
 * @Description:
 */
public class IndexViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<IndexBean> lists = new ArrayList<>();
    private OnItemClickListener mItemClickListener;
    private LayoutInflater mLayoutInflater;
    public IndexViewAdapter(Context context) {
        lists.clear();
        mLayoutInflater=LayoutInflater.from(context);
    }

    @Override
    public IndexViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /**
         * 加载滑动布局item_root，其中已经包含了content和optinos布局
         */
        View view=mLayoutInflater.inflate(R.layout.index_item_layout, parent, false);
        return new IndexViewHolder(view);
    }
    public void addList(List<IndexBean> list){
        lists.addAll(list);
    }
    public void addModel(IndexBean model){
        lists.add(model);
    }
    public void removeData(int position) {
        lists.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(0, getItemCount());
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        IndexViewHolder viewHolder= (IndexViewHolder) holder;
        viewHolder.bindHolder(lists.get(position));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mItemClickListener!=null){
                    mItemClickListener.onItemClick(v,lists.get(position));
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
        void onItemClick(View view, IndexBean model);
    }
}