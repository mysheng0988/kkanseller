package com.mysheng.office.kkanseller.pickers.listeners;

import com.mysheng.office.kkanseller.pickers.entity.City;
import com.mysheng.office.kkanseller.pickers.entity.County;
import com.mysheng.office.kkanseller.pickers.entity.Province;
import com.mysheng.office.kkanseller.pickers.entity.TypeMax;
import com.mysheng.office.kkanseller.pickers.entity.TypeMiddle;
import com.mysheng.office.kkanseller.pickers.entity.TypeMin;

/**
 * @author matt
 * blog: addapp.cn
 */

public interface OnLinkageListener {
    /**
     * 选择地址
     *
     * @param province the province
     * @param city    the city
     * @param county   the county ，if {@code hideCounty} is true，this is null
     */
    void onAddressPicked(Province province, City city, County county);
    void onTypePicked(TypeMax typeMax, TypeMiddle typeMiddle, TypeMin typeMin);
}
