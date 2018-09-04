package com.mysheng.office.kkanseller.entity;

/**
 * Created by myaheng on 2018/9/3.
 */

public class GoodsParam {
    private String specName1;
    private String specName2;
    private String specNameType1;
    private String specNameType2;
    private String price;
    private String inventory;
    private boolean  firstOnly=false;

    public boolean isFirstOnly() {
        return firstOnly;
    }

    public void setFirstOnly(boolean firstOnly) {
        this.firstOnly = firstOnly;
    }

    public String getSpecNameType1() {
        return specNameType1;
    }

    public void setSpecNameType1(String specNameType1) {
        this.specNameType1 = specNameType1;
    }

    public String getSpecNameType2() {
        return specNameType2;
    }

    public void setSpecNameType2(String specNameType2) {
        this.specNameType2 = specNameType2;
    }
    public String getSpecName1() {
        return specName1;
    }

    public void setSpecName1(String specName1) {
        this.specName1 = specName1;
    }

    public String getSpecName2() {
        return specName2;
    }

    public void setSpecName2(String specName2) {
        this.specName2 = specName2;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }
}
