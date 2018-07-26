package com.mysheng.office.kkanseller;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.mysheng.office.kkanseller.adpter.IndexViewAdapter;
import com.mysheng.office.kkanseller.decoration.SpacesItemDecoration;
import com.mysheng.office.kkanseller.entity.IndexBean;
import com.mysheng.office.kkanseller.pickers.entity.City;
import com.mysheng.office.kkanseller.pickers.entity.County;
import com.mysheng.office.kkanseller.pickers.entity.Province;
import com.mysheng.office.kkanseller.util.AddressPickTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by myaheng on 2018/6/30.
 */

public class IndexFragment extends Fragment {
    private Button button;
    private RecyclerView indexRecyclerView;
    private IndexViewAdapter indexViewAdapter;
    private List<IndexBean> mList=new ArrayList<>();
    private String[] dataStr={"今日订单数","今日成交额","今日收藏的商品","退款中","待付款","待发货","购物车的商品数","出售中"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view= inflater.inflate(R.layout.tab01, container, false);
        button=view.findViewById(R.id.button);
        indexRecyclerView=view.findViewById(R.id.indexRecyclerView);
        indexViewAdapter=new IndexViewAdapter(getActivity());
         GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),3);
        indexRecyclerView.setLayoutManager(gridLayoutManager);
//        int leftRight = dip2px(2);
//        int topBottom = dip2px(2);
        //indexRecyclerView.addItemDecoration(new SpacesItemDecoration(leftRight,topBottom,Color.GRAY));
        indexRecyclerView.setAdapter(indexViewAdapter);
        initData();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddressPicker();
            }
        });
        return view;
    }
    private void initData(){
        for (int i=0;i<dataStr.length;i++){
            IndexBean bean=new IndexBean();
            bean.setItemName(dataStr[i]);
            Random random=new Random();
            int num=random.nextInt(100);
            bean.setItemNum(num);
            mList.add(bean);
        }
        indexViewAdapter.addList(mList);
    }
    public void onAddressPicker() {
        AddressPickTask task = new AddressPickTask(getActivity());
        task.setHideProvince(false);
        task.setHideCounty(false);
        task.setCallback(new AddressPickTask.Callback() {


            /**
             * 选择地址
             *
             * @param province the province
             * @param city     the city
             * @param county   the county ，if {@code hideCounty} is true，this is null
             */
            @Override
            public void onAddressPicked(Province province, City city, County county) {
                if (county == null) {
                    showToast(province.getAreaName() + city.getAreaName());
                } else {
                    showToast(province.getAreaName() + city.getAreaName() + county.getAreaName());
                }
            }

            @Override
            public void onAddressInitFailed() {
                  showToast("数据初始化失败");
            }



        });
        task.execute("陕西", "榆林", "定边");
    }
    private void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
    public int dip2px(float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, getResources().getDisplayMetrics());
    }
}
