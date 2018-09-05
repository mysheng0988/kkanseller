package com.mysheng.office.kkanseller;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.app.TakePhotoFragment;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.mysheng.office.kkanseller.adpter.IndexViewAdapter;
import com.mysheng.office.kkanseller.circleprogress.CircleProgress;
import com.mysheng.office.kkanseller.customCamera.dialog.LoadingDialog;
import com.mysheng.office.kkanseller.entity.IndexBean;
import com.mysheng.office.kkanseller.pickers.entity.City;
import com.mysheng.office.kkanseller.pickers.entity.County;
import com.mysheng.office.kkanseller.pickers.entity.Province;
import com.mysheng.office.kkanseller.util.AddressPickTask;
import com.mysheng.office.kkanseller.decoration.DividerGridItemDecoration;
import com.mysheng.office.kkanseller.util.TakePhotoSetting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by myaheng on 2018/6/30.
 */

public class IndexFragment extends TakePhotoFragment {
    private Button button;
    private RecyclerView indexRecyclerView;
    private IndexViewAdapter indexViewAdapter;
    private TakePhotoSetting takePhotoSetting;
    private TakePhoto mTakePhoto;
    private List<IndexBean> mList=new ArrayList<>();
    private String[] dataStr={"今日订单数","今日成交额","今日收藏的商品","退款中","待付款","待发货","购物车的商品数","出售中","今日访问量"};
    private CircleProgress mCircleProgress;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view= inflater.inflate(R.layout.tab01, container, false);
        button=view.findViewById(R.id.button);
        indexRecyclerView=view.findViewById(R.id.indexRecyclerView);
        mCircleProgress=view.findViewById(R.id.circle_progress);
        indexViewAdapter=new IndexViewAdapter(getActivity());
         GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),3);
        indexRecyclerView.setLayoutManager(gridLayoutManager);

        indexRecyclerView.addItemDecoration(new DividerGridItemDecoration());
        indexViewAdapter.setItemClickListener(new IndexViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, IndexBean model) {
                takePhotoSetting.pickBySelectImage(mTakePhoto);
            }
        });
        indexRecyclerView.setAdapter(indexViewAdapter);
        initData();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddressPicker();
            }
        });
        takePhotoSetting=new TakePhotoSetting();
        takePhotoSetting.setLimit(1);
        takePhotoSetting.setCutting(true);
        mTakePhoto=getTakePhoto();
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

    @Override
    public void takeCancel() {
        super.takeCancel();
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);

    }

    @Override
    public void takeSuccess(TResult result) {

        ArrayList<TImage> images=result.getImages();



		for (int i = 0;i<images.size(); i ++) {
			Log.d("mmm", "takeSuccess: "+images.get(i).getCompressPath());


		}


    }
}
