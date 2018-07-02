package com.mysheng.office.kkanseller.pickers.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 中类
 * Created by myaheng on 2018/7/2.
 */

public class TypeMiddle extends ItemBean{
    private String TypeMaxId;
    private List<TypeMin> typeMins = new ArrayList<>();

    public String getTypeMaxId() {
        return TypeMaxId;
    }

    public void setTypeMaxId(String typeMaxId) {
        TypeMaxId = typeMaxId;
    }

    public List<TypeMin> getTypeMins() {
        return typeMins;
    }

    public void setTypeMins(List<TypeMin> typeMins) {
        this.typeMins = typeMins;
    }
}
