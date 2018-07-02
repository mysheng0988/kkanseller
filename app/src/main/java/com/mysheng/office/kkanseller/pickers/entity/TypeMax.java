package com.mysheng.office.kkanseller.pickers.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 大类
 * Created by myaheng on 2018/7/2.
 */

public class TypeMax extends ItemBean{
    private List<TypeMiddle> middles = new ArrayList<>();

    public List<TypeMiddle> getMiddles() {
        return middles;
    }

    public void setMiddles(List<TypeMiddle> middles) {
        this.middles = middles;
    }
}
