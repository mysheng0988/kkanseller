package com.mysheng.office.kkanseller.adpter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mysheng.office.kkanseller.R;
import com.mysheng.office.kkanseller.holder.AddImageViewHolder;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: duke
 * @DateTime: 2017-01-03 22:24
 * @Description:
 */
public class AddImageViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> lists = new ArrayList<>();
    private OnItemClickListener mItemClickListener;
    private LayoutInflater mLayoutInflater;
    public AddImageViewAdapter(Context context) {
        lists.clear();
        mLayoutInflater=LayoutInflater.from(context);

    }

    @Override
    public AddImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /**
         * 加载滑动布局item_root，其中已经包含了content和optinos布局
         */
        View view=mLayoutInflater.inflate(R.layout.add_item_image, parent, false);
        return new AddImageViewHolder(view);
    }
    public void addList(List<String> list){
        lists.addAll(list);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final AddImageViewHolder viewHolder= (AddImageViewHolder) holder;

        viewHolder.bindHolder(lists.get(position));
        viewHolder.delImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener. onItemClick(v , viewHolder.getLayoutPosition());
            }
        });
        //setRecursionClick(holder.itemView,position,lists);
    }



    @Override
    public int getItemCount() {
        return lists.size();
    }
    public List<String> getImagesList(){
        return lists;
    }
//    //递归设置点击事件
//    private void setRecursionClick(final View view,final int position, final List<String> lists) {
//        if (view instanceof ViewGroup) {
//            ViewGroup group = (ViewGroup) view;
//            group.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (mItemClickListener != null) {
//                        mItemClickListener.onItemClick(view,  position,lists);
//                    }
//                }
//            });
//            for (int i = 0; i < group.getChildCount(); i++) {
//                setRecursionClick(group.getChildAt(i),position,lists);
//            }
//        } else {
//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (mItemClickListener != null) {
//                        mItemClickListener.onItemClick(view, position,lists);
//                    }
//                }
//            });
//        }
//    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
}