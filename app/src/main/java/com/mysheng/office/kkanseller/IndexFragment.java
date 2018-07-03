package com.mysheng.office.kkanseller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.mysheng.office.kkanseller.pickers.entity.City;
import com.mysheng.office.kkanseller.pickers.entity.County;
import com.mysheng.office.kkanseller.pickers.entity.Province;
import com.mysheng.office.kkanseller.pickers.entity.TypeMax;
import com.mysheng.office.kkanseller.pickers.entity.TypeMiddle;
import com.mysheng.office.kkanseller.pickers.entity.TypeMin;
import com.mysheng.office.kkanseller.util.AddressPickTask;

/**
 * Created by myaheng on 2018/6/30.
 */

public class IndexFragment extends Fragment {
    private Button button;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view= inflater.inflate(R.layout.tab01, container, false);
        button=view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddressPicker();
            }
        });
        return view;
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
}
