package com.mysheng.office.kkanseller.adpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.LinearLayout;

import com.mysheng.office.kkanseller.R;
import com.mysheng.office.kkanseller.entity.ChatModel;
import com.mysheng.office.kkanseller.entity.Goods;
import com.mysheng.office.kkanseller.holder.GoodsListViewHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: duke
 * @DateTime: 2017-01-03 22:24
 * @Description:
 */
public class GoodsListViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Goods> lists = new ArrayList<>();
    private OnItemClickListener mItemClickListener;
    private LayoutInflater mLayoutInflater;
    private Filter filter;
    public GoodsListViewAdapter(Context context) {
        lists.clear();
        mLayoutInflater=LayoutInflater.from(context);
    }

    @Override
    public GoodsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case Goods.ONLINE:
                View view1=mLayoutInflater.inflate(R.layout.item_goods_list, parent, false);
                return new GoodsListViewHolder(view1);
            case Goods.OFF_ONLINE:
                View view2=mLayoutInflater.inflate(R.layout.item_goods_lower, parent, false);
                return new GoodsListViewHolder(view2);
            case Goods.examine:
                View view3=mLayoutInflater.inflate(R.layout.item_goods_examine, parent, false);
                return new GoodsListViewHolder(view3);
            case Goods.examine_failed:
                View view4=mLayoutInflater.inflate(R.layout.item_goods_examine_failed, parent, false);
                return new GoodsListViewHolder(view4);
        }
        return null;

    }
    public void addList(List<Goods> list){
        lists.clear();
        lists.addAll(list);
    }
    public void addModel(Goods model){
        lists.add(model);
    }
    public void removeData(int position) {
        lists.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
    }
    public void goodsItemSort(final int isSort,final int type){

        Collections.sort(lists, new Comparator<Goods>() {
            @Override
            public int compare(Goods o1, Goods o2) {
                if(type==1){
                    if(isSort<50){
                        return o2.getGoodsPrice().compareTo(o1.getGoodsPrice());
                    }
                    return o1.getGoodsPrice().compareTo(o2.getGoodsPrice());

                }else if(type==2){
                    if(isSort<50){
                        return o2.getSaleAmount().compareTo(o1.getSaleAmount());
                    }
                    return o1.getSaleAmount().compareTo(o2.getSaleAmount());
                }else if (type==3){
                    if(isSort<50){
                        return o2.getGoodsInventory().compareTo(o1.getGoodsInventory());
                    }
                    return o1.getGoodsInventory().compareTo(o2.getGoodsInventory());

                }else{
                    if(isSort<50){
                        return o2.getAddTime().compareTo(o1.getAddTime());
                    }
                    return o1.getAddTime().compareTo(o2.getAddTime());

                }

            }
        });
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        GoodsListViewHolder viewHolder= (GoodsListViewHolder) holder;
        viewHolder.bindHolder(lists.get(position));
//        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(mItemClickListener!=null){
//                    mItemClickListener.onItemClick(v,lists.get(position));
//                }
//
//            }
//        });
        viewHolder.itemView.findViewById(R.id.goodsDetailed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(v, lists.get(position));
                }
            }
        });
        setRecursionClick(viewHolder.itemView.findViewById(R.id.container),lists.get(position));
    }



    @Override
    public int getItemCount() {
        return lists.size();
    }
    @Override
    public int getItemViewType(int position) {
        return lists.get(position).getGoodsType();
    }

    private void setRecursionClick(final View view, final Goods model) {
        if (view instanceof LinearLayout) {
            LinearLayout group = (LinearLayout) view;

            for (int i = 0; i < group.getChildCount(); i++) {
                View l=group.getChildAt(i);
                if (group.getChildAt(i) instanceof LinearLayout) {
                    group.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mItemClickListener != null) {
                                mItemClickListener.onItemClick(v, model);
                            }
                        }
                    });
                   // setRecursionClick(group.getChildAt(i),model);
                }
            }
        }
    }
    public void setItemClickListener(GoodsListViewAdapter.OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }
    public interface OnItemClickListener{
        void onItemClick(View view, Goods model);
    }
}