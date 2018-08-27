package com.mysheng.office.kkanseller;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.mysheng.office.kkanseller.adpter.GoodsListViewAdapter;
import com.mysheng.office.kkanseller.entity.Goods;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by myaheng on 2018/6/30.
 */

public class GoodsFragment extends Fragment implements View.OnClickListener{
    private ImageView addMenu;
    private View inflate;
    private Dialog dialog;
    private RecyclerView mGoodsView;
    private GoodsListViewAdapter mAdapter;
    private ImageView addTimeSort;
    private ImageView saleSort;
    private ImageView inventorySort;
    private List<Goods> mData=new ArrayList<>();
    public static String[] netImages = {
            "http://wx1.sinaimg.cn/woriginal/daaf97d2gy1fgsxkq8uc3j20dw0ku74x.jpg",
            "http://wx1.sinaimg.cn/woriginal/daaf97d2gy1fgsxkqm7b0j20dw0kut9h.jpg",
            "http://wx4.sinaimg.cn/woriginal/daaf97d2gy1fgsxks2l4ij20dw0kldhb.jpg",
            "http://wx2.sinaimg.cn/woriginal/daaf97d2gy1fgsxksskbkj20dw0kut9b.jpg",
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2966021298,3341101515&fm=23&gp=0.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496402134202&di=6c7f4a6afa5bdf02000c788f7a51e9c0&imgtype=0&src=http%3A%2F%2Fcdnq.duitang.com%2Fuploads%2Fitem%2F201506%2F23%2F20150623183946_iZtFs.jpeg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496996892&di=ea1e213c8ddd4427c55f073db9bf91b7&imgtype=jpg&er=1&src=http%3A%2F%2Fpic27.nipic.com%2F20130323%2F9483785_182530048000_2.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496996959&di=13c094ba73675a24df2ad1d2c730c02c&imgtype=jpg&er=1&src=http%3A%2F%2Fdasouji.com%2Fwp-content%2Fuploads%2F2015%2F07%2F%25E9%2595%25BF%25E8%258A%25B1%25E5%259B%25BE-6.jpg"
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view= inflater.inflate(R.layout.tab03, container, false);
        addMenu=view.findViewById(R.id.addMenu);
        addTimeSort=view.findViewById(R.id.add_time_sort);
        addTimeSort.getDrawable().setLevel(5);
        saleSort=view.findViewById(R.id.sale_sort);
        saleSort.getDrawable().setLevel(5);
        inventorySort=view.findViewById(R.id.inventory_sort);
        inventorySort.getDrawable().setLevel(5);
        addMenu.setOnClickListener(this);
        addTimeSort.setOnClickListener(this);
        saleSort.setOnClickListener(this);
        inventorySort.setOnClickListener(this);
        mGoodsView=view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mAdapter=new GoodsListViewAdapter(getActivity());
        mGoodsView.setAdapter(mAdapter);
        mGoodsView.setLayoutManager(linearLayoutManager);
        initData();
        return view;

    }

    private void initData() {
        for(int i=0;i<netImages.length;i++){
            Goods goods=new Goods();
            goods.setGoodsPath(netImages[i]);
            goods.setGoodsName("测试商品"+i);
            goods.setSaleAmount("销量:"+getRandomNum()+"件");
            goods.setGoodsPrice("￥:"+getRandomNum()+"元");
            mData.add(goods);
        }
        mAdapter.addList(mData);


    }

    private int getRandomNum(){
        Random rand = new Random();
        return rand.nextInt(999) + 1;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addMenu:
                showDialog();
                break;
            case R.id.add_time_sort:
            case R.id.sale_sort:
            case R.id.inventory_sort:
                itemSort(v,v.getId());
                break;
        }
    }
    private void itemSort(View view,int id){
//        addTimeSort.getDrawable().setLevel(5);
//        saleSort.getDrawable().setLevel(5);
//        inventorySort.getDrawable().setLevel(5);
        ImageView imageView=view.findViewById(id);
        int level=imageView.getDrawable().getLevel();
        if (level<10){
            imageView.getDrawable().setLevel(20);
        }else if(level>10&&level<50){
            imageView.getDrawable().setLevel(70);
        }else if(level>50){
            imageView.getDrawable().setLevel(20);
        }

    }
    public void showDialog(){
        dialog = new Dialog(getActivity(),R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        inflate = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_menu_layout, null);
        //初始化控件
//        choosePhoto =  inflate.findViewById(R.id.choosePhoto);
//        takePhoto = inflate.findViewById(R.id.takePhoto);
//        cancel=inflate.findViewById(R.id.btn_cancel);
//        choosePhoto.setOnClickListener(this);
//        takePhoto.setOnClickListener(this);
//        cancel.setOnClickListener(this);
        //将布局设置给Dialog
        dialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity( Gravity.BOTTOM);
        WindowManager m = getActivity().getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.width = (int) (d.getWidth() * 0.95); // 宽度设置为屏幕的0.95
        lp.y = 20;//设置Dialog距离底部的距离
        dialogWindow.setAttributes(lp);
        dialog.show();//显示对话框
    }
}
