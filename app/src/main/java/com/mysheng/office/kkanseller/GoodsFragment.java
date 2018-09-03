package com.mysheng.office.kkanseller;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.util.Util;
import com.mysheng.office.kkanseller.adpter.GoodsListViewAdapter;
import com.mysheng.office.kkanseller.entity.Goods;
import com.mysheng.office.kkanseller.util.ChinesePinyinUtil;
import com.mysheng.office.kkanseller.util.UtilDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by myaheng on 2018/6/30.
 */

public class GoodsFragment extends Fragment implements View.OnClickListener,GoodsListViewAdapter.OnItemClickListener{
    private ImageView addMenu;
    private View inflate;
    private Dialog dialog;
    private RecyclerView mGoodsView;
    private GoodsListViewAdapter mAdapter;
    private ImageView addTimeSort;
    private ImageView saleSort;
    private ImageView priceSort;
    private ImageView inventorySort;
    private List<Goods> mData=new ArrayList<>();
    private List<Goods> mDataOff=new ArrayList<>();
    private TextView goodsOnline;
    private TextView goodsExamine;
    private TextView goodsOff;
    private  TextView addGoods;
    private  TextView batch;
    private  TextView classification;
    private  TextView cancel;
    public static String[] netImages = {
            "http://wx1.sinaimg.cn/woriginal/61e7f4aaly1fgrt0bj3htj20gg0c7myr.jpg",
            "http://wx4.sinaimg.cn/woriginal/61e7f4aaly1fgrt0bpvkxj20go080wg9.jpg",
            "http://wx2.sinaimg.cn/woriginal/61e7f4aaly1fgrt0bcfhpj20u011hwha.jpg",
            "http://wx3.sinaimg.cn/woriginal/61e7f4aaly1fgrt0agnqyj20p00v9diq.jpg",
            "http://wx2.sinaimg.cn/woriginal/61e7f4aaly1fgrt0apox0j20u011idjh.jpg",
            "http://wx4.sinaimg.cn/woriginal/61e7f4aaly1fgrt0b0ww9j20u011iwj9.jpg",
            "http://wx1.sinaimg.cn/woriginal/daaf97d2gy1fgsxkq8uc3j20dw0ku74x.jpg",
            "http://wx1.sinaimg.cn/woriginal/daaf97d2gy1fgsxkqm7b0j20dw0kut9h.jpg",
            "http://wx4.sinaimg.cn/woriginal/daaf97d2gy1fgsxks2l4ij20dw0kldhb.jpg",
            "http://wx2.sinaimg.cn/woriginal/daaf97d2gy1fgsxksskbkj20dw0kut9b.jpg",
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2966021298,3341101515&fm=23&gp=0.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496402134202&di=6c7f4a6afa5bdf02000c788f7a51e9c0&imgtype=0&src=http%3A%2F%2Fcdnq.duitang.com%2Fuploads%2Fitem%2F201506%2F23%2F20150623183946_iZtFs.jpeg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496996892&di=ea1e213c8ddd4427c55f073db9bf91b7&imgtype=jpg&er=1&src=http%3A%2F%2Fpic27.nipic.com%2F20130323%2F9483785_182530048000_2.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496996959&di=13c094ba73675a24df2ad1d2c730c02c&imgtype=jpg&er=1&src=http%3A%2F%2Fdasouji.com%2Fwp-content%2Fuploads%2F2015%2F07%2F%25E9%2595%25BF%25E8%258A%25B1%25E5%259B%25BE-6.jpg"
    };
    public static String[] offImages={
            "http://ww3.sinaimg.cn/woriginal/75d91745gw1f0echzpyx6j20sg13pqg3.jpg",
            "http://ww4.sinaimg.cn/woriginal/75d91745gw1f0eci2262gj20sg14bh14.jpg",
            "http://ww3.sinaimg.cn/woriginal/75d91745gw1f0eci5qk5kj20sg12bdt3.jpg",
            "http://ww1.sinaimg.cn/woriginal/75d91745gw1f0eci7jn0mj20sg0k80wq.jpg",
            "http://ww2.sinaimg.cn/woriginal/75d91745gw1f0eci3mwbqj20go0nbwkd.jpg",
            "http://ww3.sinaimg.cn/woriginal/75d91745gw1f0eci9sybpj20sg14enbf.jpg",
            "http://ww2.sinaimg.cn/woriginal/75d91745gw1f0ecif04ocj20sg14mqlb.jpg",
            "http://ww1.sinaimg.cn/woriginal/75d91745gw1f0ecigzhl9j20sg138gz4.jpg",
            "http://ww3.sinaimg.cn/woriginal/75d91745gw1f0echzpyx6j20sg13pqg3.jpg",
            "http://ww4.sinaimg.cn/woriginal/75d91745gw1f0eci2262gj20sg14bh14.jpg",
            "http://ww3.sinaimg.cn/woriginal/75d91745gw1f0eci5qk5kj20sg12bdt3.jpg",
            "http://ww1.sinaimg.cn/woriginal/75d91745gw1f0eci7jn0mj20sg0k80wq.jpg",
            "http://ww2.sinaimg.cn/woriginal/75d91745gw1f0eci3mwbqj20go0nbwkd.jpg",
            "http://ww3.sinaimg.cn/woriginal/75d91745gw1f0eci9sybpj20sg14enbf.jpg",
            "http://ww2.sinaimg.cn/woriginal/75d91745gw1f0ecif04ocj20sg14mqlb.jpg",
            "http://ww1.sinaimg.cn/woriginal/75d91745gw1f0ecigzhl9j20sg138gz4.jpg"
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
        priceSort=view.findViewById(R.id.price_sort);
        priceSort.getDrawable().setLevel(5);
        inventorySort=view.findViewById(R.id.inventory_sort);
        inventorySort.getDrawable().setLevel(5);
        goodsOnline=view.findViewById(R.id.goods_online);
        goodsExamine=view.findViewById(R.id.goods_examine);
        goodsOff=view.findViewById(R.id.goods_off);
        addMenu.setOnClickListener(this);
        addTimeSort.setOnClickListener(this);
        saleSort.setOnClickListener(this);
        inventorySort.setOnClickListener(this);
        goodsOnline.setOnClickListener(this);
        goodsExamine.setOnClickListener(this);
        goodsOff.setOnClickListener(this);
        mGoodsView=view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mAdapter=new GoodsListViewAdapter(getActivity());
        mAdapter.setItemClickListener(this);
        mGoodsView.setAdapter(mAdapter);
        mGoodsView.setLayoutManager(linearLayoutManager);
        initData();
        return view;

    }

    private void initData() {
        mData.clear();
        for(int i=0;i<netImages.length;i++){
            Goods goods=new Goods();
            goods.setGoodsType(1);
            goods.setGoodsPath(netImages[i]);
            String goodsName=RandomChinese();
            goods.setGoodsName(goodsName);
            goods.setGoodsNameAB(ChinesePinyinUtil.getPinYinHeadChar(goodsName));
            goods.setSaleAmount(getRandomNum(999));
            goods.setGoodsInventory(getRandomNum(999));
            goods.setGoodsPrice(getRandomDouble());
            String str="2018-08-"+getRandomDay();
            goods.setAddTime(str);
            mData.add(goods);
        }

        mAdapter.addList(mData);
        mAdapter.notifyDataSetChanged();
        int size= mAdapter.getItemCount();
        Log.e("mys", "initData: "+size );


    }
    private String RandomChinese(){
        String str="";
        Random rand = new Random();
       int len= 5+rand.nextInt(30) + 1;
        for (int i=0;i<len;i++){
            str+= ChinesePinyinUtil.getRandomChar();
        }
        return str;
    }
    private void initDataOff() {
        mDataOff.clear();
        for(int i=0;i<offImages.length;i++){
            Goods goods=new Goods();
            goods.setGoodsType(2);
            goods.setGoodsPath(offImages[i]);
            String goodsName=RandomChinese();
            goods.setGoodsName(goodsName);
            goods.setGoodsNameAB(ChinesePinyinUtil.getPinYinHeadChar(goodsName));
            goods.setSaleAmount(getRandomNum(999));
            goods.setGoodsInventory(getRandomNum(999));
            goods.setGoodsPrice(getRandomDouble());
            String str="2018-08-"+getRandomDay();
            goods.setAddTime(str);
            mDataOff.add(goods);
        }
        mAdapter.addList(mDataOff);
        mAdapter.notifyDataSetChanged();
        int size= mAdapter.getItemCount();
        Log.e("mys", "initDataOff: "+size );


    }
    private void initDataExamine() {
        mDataOff.clear();
        for(int i=0;i<offImages.length;i++){
            Goods goods=new Goods();
            goods.setGoodsType(getRandomNum(2)+2);
            goods.setGoodsPath(offImages[i]);
            String goodsName=RandomChinese();
            goods.setGoodsName(goodsName);
            goods.setGoodsNameAB(ChinesePinyinUtil.getPinYinHeadChar(goodsName));
            goods.setSaleAmount(0);
            goods.setGoodsInventory(getRandomNum(999));
            goods.setGoodsPrice(getRandomDouble());
            String str="2018-08-"+getRandomDay();
            goods.setAddTime(str);
            mDataOff.add(goods);
        }
        mAdapter.addList(mDataOff);
        mAdapter.notifyDataSetChanged();
        int size= mAdapter.getItemCount();
        Log.e("mys", "initDataOff: "+size );


    }
    private int getRandomNum(int num){
        Random rand = new Random();
        return rand.nextInt(num) + 1;
    }
    private String getRandomDay(){
        Random rand = new Random();
        int day=rand.nextInt(30) + 1;
        return day>9?day+"":"0"+day;
    }
    private Double getRandomDouble(){
        Random rand = new Random();
        return Double.valueOf(Math.round((rand.nextDouble()*100000)/100));
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addMenu:
                showDialog();
                break;
            case R.id.price_sort:
                saleSort.getDrawable().setLevel(5);
                inventorySort.getDrawable().setLevel(5);
                addTimeSort.getDrawable().setLevel(5);
                itemSort(priceSort,1);
                break;
            case R.id.sale_sort:
                priceSort.getDrawable().setLevel(5);
                inventorySort.getDrawable().setLevel(5);
                addTimeSort.getDrawable().setLevel(5);
                itemSort(saleSort,2);
                break;
            case R.id.inventory_sort:
                priceSort.getDrawable().setLevel(5);
                saleSort.getDrawable().setLevel(5);
                addTimeSort.getDrawable().setLevel(5);
                itemSort(inventorySort,3);
                break;
            case R.id.add_time_sort:
                priceSort.getDrawable().setLevel(5);
                saleSort.getDrawable().setLevel(5);
                inventorySort.getDrawable().setLevel(5);
                itemSort(addTimeSort,4);
                break;
            case R.id.goods_online:
                reInitBackground();
                goodsOnline.setBackground(getResources().getDrawable(R.drawable.bg_bottom_borders));
                initData();
                break;
            case R.id.goods_examine:
                reInitBackground();
                goodsExamine.setBackground(getResources().getDrawable(R.drawable.bg_white_borders));
                initDataExamine();
                break;
            case R.id.goods_off:
                reInitBackground();
                goodsOff.setBackground(getResources().getDrawable(R.drawable.bg_bottom_borders));
                initDataOff();
                break;
            case R.id.addGoods:
                Intent intent=new Intent(getActivity(),AddGoodsActivity.class);
                startActivity(intent);
                dialog.dismiss();
                break;
            case R.id.btn_cancel:
                dialog.dismiss();
                break;
        }
    }
    private void reInitBackground(){
        goodsOnline.setBackground(getResources().getDrawable(R.drawable.bg_white_borders));
        goodsExamine.setBackground(getResources().getDrawable(R.drawable.bg_white_borders));
        goodsOff.setBackground(getResources().getDrawable(R.drawable.bg_white_borders));
    }
    private int getImageLevel(ImageView imageView){
        return imageView.getDrawable().getLevel();
    }
    private void itemSort(ImageView imageView,int type){
//        addTimeSort.getDrawable().setLevel(5);
//        saleSort.getDrawable().setLevel(5);
//        inventorySort.getDrawable().setLevel(5);
        int level= getImageLevel(imageView);
        if (level<10){
            imageView.getDrawable().setLevel(20);
            mAdapter.goodsItemSort(20,type);
        }else if(level>10&&level<50){
            imageView.getDrawable().setLevel(70);
            mAdapter.goodsItemSort(70,type);
        }else if(level>50){
            mAdapter.goodsItemSort(20,type);
            imageView.getDrawable().setLevel(20);
        }

    }

    public void showDialog(){
        dialog = new Dialog(getActivity(),R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        inflate = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_menu_layout, null);
        //初始化控件
        addGoods =  inflate.findViewById(R.id.addGoods);
        batch = inflate.findViewById(R.id.batch);
        classification = inflate.findViewById(R.id.classification);
        cancel=inflate.findViewById(R.id.btn_cancel);
        addGoods.setOnClickListener(this);
        batch.setOnClickListener(this);
        classification.setOnClickListener(this);
        cancel.setOnClickListener(this);
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

    @Override
    public void onItemClick(View view, Goods model) {
        switch (view.getId()){
            case R.id.goodsDetailed:
                Toast.makeText(getActivity(),model.getGoodsPrice()+"商品详情",Toast.LENGTH_SHORT).show();
                break;
            case R.id.goodsPreview:
                Toast.makeText(getActivity(),model.getGoodsPrice()+"预览",Toast.LENGTH_SHORT).show();
                break;
            case R.id.goodsReduce:
                Toast.makeText(getActivity(),model.getGoodsPrice()+"减库存",Toast.LENGTH_SHORT).show();
                break;
            case R.id.goodsOff_online:
                Toast.makeText(getActivity(),model.getGoodsPrice()+"下架",Toast.LENGTH_SHORT).show();
                break;
            case R.id.goods_online:
                Toast.makeText(getActivity(),model.getGoodsPrice()+"上架",Toast.LENGTH_SHORT).show();
                break;
            case R.id.goodsShare:
                Toast.makeText(getActivity(),model.getGoodsPrice()+"分享",Toast.LENGTH_SHORT).show();
                break;
            case R.id.goodsDelete:
                Toast.makeText(getActivity(),model.getGoodsPrice()+"删除",Toast.LENGTH_SHORT).show();
                break;
            case R.id.examineDetailed:
                Toast.makeText(getActivity(),model.getGoodsPrice()+"审核详情",Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
