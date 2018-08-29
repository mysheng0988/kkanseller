package com.mysheng.office.kkanseller.adpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.mysheng.office.kkanseller.R;
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
public class GoodsListViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {
    private ArrayList<Goods> lists = new ArrayList<>();
    private ArrayList<Goods> mlists = new ArrayList<>();//备份
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
        }
        return null;

    }
    public void addList(List<Goods> list){
        lists.clear();
        mlists.clear();
        lists.addAll(list);
        mlists.addAll(list);
    }
    public void addModel(Goods model){
        lists.add(model);
    }
    public void removeData(int position) {
        lists.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(0, getItemCount());
    }
    public void goodsItemSort(final int isSort,final int type){

        Collections.sort(lists, new Comparator<Goods>() {
            @Override
            public int compare(Goods o1, Goods o2) {
                if(type==1){
                    if(isSort<50){
                        return o2.getAddTime().compareTo(o1.getAddTime());
                    }
                    return o1.getAddTime().compareTo(o2.getAddTime());

                }else if(type==2){
                    if(isSort<50){
                        return o2.getSaleAmount().compareTo(o1.getSaleAmount());
                    }
                    return o1.getSaleAmount().compareTo(o2.getSaleAmount());
                }else {
                    if(isSort<50){
                        return o2.getGoodsPrice().compareTo(o1.getGoodsPrice());
                    }
                    return o1.getGoodsPrice().compareTo(o2.getGoodsPrice());

                }

            }
        });
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        GoodsListViewHolder viewHolder= (GoodsListViewHolder) holder;
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
    @Override
    public int getItemViewType(int position) {
        return lists.get(position).getGoodsType();
    }
    public void setItemClickListener(GoodsListViewAdapter.OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    /**
     * 过滤
     * @return
     */
    @Override
    public Filter getFilter() {

        if (filter == null){
            filter = new Filter() {

                @Override
                protected FilterResults performFiltering(CharSequence charSequence) {
                    lists=mlists;
                    FilterResults results = new FilterResults();
                    if (!TextUtils.isEmpty(charSequence)) {
                        List<Goods> entries = new ArrayList<>();
                        for (Goods entry : lists) {
                            String pinyin = entry.getGoodsNameAB();
                            if (pinyin.contains(charSequence.toString())){
                                entries.add(entry);
                            }
                        }
                        results.count = entries.size();
                        results.values = entries;
                    }else {
                        results.count = mlists.size();
                        results.values =mlists;
                    }
                    return results;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {

                    lists.clear();
                    lists= (ArrayList<Goods>) results.values;
                    notifyDataSetChanged();
                    int size= getItemCount();
                    Log.e("mys", "publishResults: "+size );
                }
            };
        }
        return filter;
    }

    public interface OnItemClickListener{
        void onItemClick(View view, Goods model);
    }
}