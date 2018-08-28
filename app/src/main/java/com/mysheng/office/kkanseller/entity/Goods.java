package com.mysheng.office.kkanseller.entity;

import android.support.annotation.NonNull;
import java.io.Serializable;

/**
 * Created by myaheng on 2018/8/27.
 */

public class Goods implements Serializable, Comparable<Goods>{
    public  static final int ONLINE=1;
    public  static final int OFF_ONLINE=2;

    private String goodsId;
    private String addTime;
    private String goodsName;
    private Double goodsPrice;
    private Double goodsOldPrice;
    private Integer  goodsInventory;
    private String goodsStyle;//颜色分类
    private int goodsType;//类型分类
    private String goodsCode;
    private String goodsPath;
    private Integer  saleAmount;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Double getGoodsOldPrice() {
        return goodsOldPrice;
    }

    public void setGoodsOldPrice(Double goodsOldPrice) {
        this.goodsOldPrice = goodsOldPrice;
    }

    public Integer getGoodsInventory() {
        return goodsInventory;
    }

    public void setGoodsInventory(Integer goodsInventory) {
        this.goodsInventory = goodsInventory;
    }

    public String getGoodsStyle() {
        return goodsStyle;
    }

    public void setGoodsStyle(String goodsStyle) {
        this.goodsStyle = goodsStyle;
    }

    public int getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(int goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsPath() {
        return goodsPath;
    }

    public void setGoodsPath(String goodsPath) {
        this.goodsPath = goodsPath;
    }

    public Integer getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(Integer saleAmount) {
        this.saleAmount = saleAmount;
    }



    @Override
    public int compareTo(@NonNull Goods o) {
        return this.saleAmount.compareTo(o.getSaleAmount());
    }
}
