package com.mysheng.office.kkanseller.pickers.listeners;

import com.mysheng.office.kkanseller.pickers.entity.City;
import com.mysheng.office.kkanseller.pickers.entity.County;
import com.mysheng.office.kkanseller.pickers.entity.Province;
import com.mysheng.office.kkanseller.pickers.entity.TypeMax;
import com.mysheng.office.kkanseller.pickers.entity.TypeMiddle;
import com.mysheng.office.kkanseller.pickers.entity.TypeMin;

/**
 * @author mysheng
 *
 */

public interface OnTypeLinkageListener {
    /**
     * 类型选择
     * @param typeMax
     * @param typeMiddle
     * @param typeMin
     */
    void onTypePicked(TypeMax typeMax, TypeMiddle typeMiddle, TypeMin typeMin);
}
