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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.mysheng.office.kkanseller.R;
import com.mysheng.office.kkanseller.entity.ChatModel;
import com.mysheng.office.kkanseller.holder.TypeAbstractViewHolder;
import com.mysheng.office.kkanseller.holder.TypeLeftImageViewHolder;
import com.mysheng.office.kkanseller.holder.TypeLeftRecorderViewHolder;
import com.mysheng.office.kkanseller.holder.TypeLeftTextViewHolder;
import com.mysheng.office.kkanseller.holder.TypeRightImageViewHolder;
import com.mysheng.office.kkanseller.holder.TypeRightLocationViewHolder;
import com.mysheng.office.kkanseller.holder.TypeRightRecorderViewHolder;
import com.mysheng.office.kkanseller.holder.TypeRightTextViewHolder;
import com.mysheng.office.kkanseller.holder.TypeRightVideoViewHolder;
import com.mysheng.office.kkanseller.holder.TypeTimeViewHolder;
import com.mysheng.office.kkanseller.util.ChatTool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by myaheng on 2018/5/11.
 */

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<ChatModel> mList=new ArrayList<>();
    private List<String> mImages=new ArrayList<>();
    private List<ImageView> imageViews=new ArrayList<>();
    private int mMinItemWidth;
    private int mMaxItemWidth;

    public ChatAdapter(Context context) {
        this.mContext=context;
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
                RecyclerView.ViewHolder viewHolder=new TypeRightTextViewHolder(view1);
                return viewHolder;
            case ChatModel.TYPE_THREE:
                return new TypeLeftImageViewHolder(mLayoutInflater.inflate(R.layout.items_left_image,parent,false));
            case ChatModel.TYPE_FOUR:
                View view4=mLayoutInflater.inflate(R.layout.items_right_image,parent,false);
                ImageView imageView=view4.findViewById(R.id.id_content_img);
                imageViews.add(0,imageView);
                return new TypeRightImageViewHolder(view4);
            case ChatModel.TYPE_FIVE:
                return new TypeLeftRecorderViewHolder(mLayoutInflater.inflate(R.layout.items_left_recorder,parent,false));
            case ChatModel.TYPE_SIX:
                View view6=mLayoutInflater.inflate(R.layout.items_right_recorder,parent,false);
                RecyclerView.ViewHolder viewHolder6=new TypeRightRecorderViewHolder(view6);
                return viewHolder6;
            case ChatModel.TYPE_TIME:
                View view7=mLayoutInflater.inflate(R.layout.item_time_layout,parent,false);
                RecyclerView.ViewHolder viewHolder7=new TypeTimeViewHolder(view7);
                return viewHolder7;
            case ChatModel.TYPE_LOCATION:
                View view8=mLayoutInflater.inflate(R.layout.items_right_location,parent,false);
                RecyclerView.ViewHolder viewHolder8=new TypeRightLocationViewHolder(view8);
                return viewHolder8;
            case ChatModel.SEND_VIDEO:
                View view11=mLayoutInflater.inflate(R.layout.items_right_video,parent,false);
                RecyclerView.ViewHolder viewHolder11=new TypeRightVideoViewHolder(view11);
                return viewHolder11;

        }
        return null;
    }

    public void addList(List<ChatModel> list){
        mList.addAll(list);
    }
    public void addImages(List<String> list){
        mImages.addAll(list);
    }
    public void addModel(ChatModel model){
        mList.add(model);
    }
    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof TypeLeftRecorderViewHolder ||holder instanceof TypeRightRecorderViewHolder){
            RelativeLayout.LayoutParams lp= (RelativeLayout.LayoutParams) holder.itemView.findViewById(R.id.id_recorder_length).getLayoutParams();
            lp.width= (int) (mMinItemWidth + (mMaxItemWidth / 60f)*mList.get(position).mesTime);
            Log.d("mys", "onBindViewHolder: "+lp.width);
            holder.itemView.findViewById(R.id.id_recorder_length).setLayoutParams(lp);
        }else if (holder instanceof TypeLeftImageViewHolder ||holder instanceof TypeRightImageViewHolder){
            holder.itemView.findViewById(R.id.id_content_img).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mItemClickListener!=null){
                        mItemClickListener.onItemClick(v,position,imageViews,mImages);
                    }

                }
            });
        }

       ((TypeAbstractViewHolder)holder).bindHolder(mList.get(position));

         //holder.itemView.findViewById(R.id.id_recorder_length);
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mItemClickListener!=null){
                    mItemClickListener.onItemClick(v,position,imageViews,mImages);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).mesType;
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }
    public interface OnItemClickListener{
        void onItemClick(View view, int position,List<ImageView> imageViews,List<String> lists);
    }
}
