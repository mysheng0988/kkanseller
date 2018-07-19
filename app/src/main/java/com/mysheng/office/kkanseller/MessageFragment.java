package com.mysheng.office.kkanseller;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mysheng.office.kkanseller.adpter.ChatListViewAdapter;
import com.mysheng.office.kkanseller.holder.ViewLineDivider;

import java.util.ArrayList;

/**
 * Created by myaheng on 2018/6/30.
 */

public class MessageFragment extends Fragment {
    private RecyclerView recyclerView;
    private TextView strTitle;
    private ChatListViewAdapter adapter;
    private ArrayList<String> list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view= inflater.inflate(R.layout.tab02, container, false);
        initData();
        recyclerView = view.findViewById(R.id.recycler_view);
        strTitle= view.findViewById(R.id.chat_title);
        strTitle.setText("聊天聊表");
        adapter = new ChatListViewAdapter(list);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new ViewLineDivider(LinearLayoutManager.VERTICAL, 4, Color.WHITE));
        recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        /**
         * 传递出slidelayout中content和options两个布局所有view的点击事件，根据需要做判断
         */
        adapter.setOnDeleteViewClickListener(new ChatListViewAdapter.OnDeleteViewClickListener() {
            @Override
            public void onChildClick(View view) {
                String string = "";
                if (view.getId() == R.id.chatListItem) {
//                     TextView textView=view.findViewById(R.id.userName);
//                     string = textView.getText().toString();
//                    Toast.makeText(getActivity(), string, Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();

                    Intent intent = new Intent(getActivity(), ChatActivity.class);
                    startActivity(intent);
                } else if (view.getId() == R.id.options_root) {
                    string = "操作菜单点击";
                    Toast.makeText(getActivity(), string, Toast.LENGTH_SHORT).show();
                } else if (view.getId() == R.id.tv_open) {
                    TextView textView = (TextView) view;
                    string = textView.getText().toString();
                    Toast.makeText(getActivity(), string, Toast.LENGTH_SHORT).show();

                } else if (view.getId() == R.id.tv_delete) {
                    TextView textView = (TextView) view;
                    string = textView.getText().toString();
                    Toast.makeText(getActivity(), string, Toast.LENGTH_SHORT).show();

                }
            }
        });
        return view;
    }
    private void initData() {
        for (int i = 0; i < 10; i++) {
            list.add("测试" + i);
        }
    }
}
