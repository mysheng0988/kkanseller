package com.mysheng.office.kkanseller.adpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;


import com.mysheng.office.kkanseller.R;
import com.mysheng.office.kkanseller.entity.ChatModel;
import com.mysheng.office.kkanseller.holder.TypeAbstractViewHolder;
import com.mysheng.office.kkanseller.holder.TypeLeftImageViewHolder;
import com.mysheng.office.kkanseller.holder.TypeLeftRecorderViewHolder;
import com.mysheng.office.kkanseller.holder.TypeLeftTextViewHolder;
import com.mysheng.office.kkanseller.holder.TypeRightImageViewHolder;
import com.mysheng.office.kkanseller.holder.TypeRightRecorderViewHolder;
import com.mysheng.office.kkanseller.holder.TypeRightTextViewHolder;
import com.mysheng.office.kkanseller.holder.TypeTimeViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by myaheng on 2018/5/11.
 */

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{
    private LayoutInflater mLayoutInflater;
    private List<ChatModel> mList=new ArrayList<>();
    private int mMinItemWidth;
    private int mMaxItemWidth;

    public ChatAdapter(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);

        mMaxItemWidth = (int) (outMetrics.widthPixels * 0.7f);
        mMinItemWidth = (int) (outMetrics.widthPixels * 0.15f);
        mLayoutInflater=LayoutInflater.from(context);
    }
    private OnItemClickListener mItemClickListener;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType){
            case ChatModel.TYPE_ONE:
            return new TypeLeftTextViewHolder(mLayoutInflater.inflate(R.layout.items_left_text,parent,false));
            case ChatModel.TYPE_TWO:
                View view1=mLayoutInflater.inflate(R.layout.items_right_text,parent,false);
              //  view.setOnClickListener(this);
                RecyclerView.ViewHolder viewHolder=new TypeRightTextViewHolder(view1);
                return viewHolder;
            case ChatModel.TYPE_THREE:
                return new TypeLeftImageViewHolder(mLayoutInflater.inflate(R.layout.items_left_image,parent,false));
            case ChatModel.TYPE_FOUR:
                return new TypeRightImageViewHolder(mLayoutInflater.inflate(R.layout.items_right_image,parent,false));
            case ChatModel.TYPE_FIVE:
                return new TypeLeftRecorderViewHolder(mLayoutInflater.inflate(R.layout.items_left_recorder,parent,false));
            case ChatModel.TYPE_SIX:
                View view6=mLayoutInflater.inflate(R.layout.items_right_recorder,parent,false);
               // view6.setOnClickListener(this);
                RecyclerView.ViewHolder viewHolder6=new TypeRightRecorderViewHolder(view6);
                return viewHolder6;
            case ChatModel.TYPE_TIME:
                View view7=mLayoutInflater.inflate(R.layout.item_time_layout,parent,false);
                RecyclerView.ViewHolder viewHolder7=new TypeTimeViewHolder(view7);
                return viewHolder7;

        }
        return null;
    }

    public void addList(List<ChatModel> list){
        mList.addAll(list);
    }
    public void addModel(ChatModel model){
        mList.add(model);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof TypeLeftRecorderViewHolder ||holder instanceof TypeRightRecorderViewHolder){
            RelativeLayout.LayoutParams lp= (RelativeLayout.LayoutParams) holder.itemView.findViewById(R.id.id_recorder_length).getLayoutParams();
            lp.width= (int) (mMinItemWidth + (mMaxItemWidth / 60f)*mList.get(position).mesTime);
            Log.d("mys", "onBindViewHolder: "+lp.width);
            holder.itemView.findViewById(R.id.id_recorder_length).setLayoutParams(lp);
        }

       ((TypeAbstractViewHolder)holder).bindHolder(mList.get(position));

         //holder.itemView.findViewById(R.id.id_recorder_length);
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(this);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).mesType;
    }

    @Override
    public void onClick(View v) {
        if(mItemClickListener!=null){
            mItemClickListener.onItemClick(v,mList.get((Integer) v.getTag()));
        }

    }
    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }
    public interface OnItemClickListener{
        void onItemClick(View view, ChatModel model);
    }
}
