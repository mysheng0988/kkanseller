package com.mysheng.office.kkanseller.chart.interfaces.dataprovider;


import com.mysheng.office.kkanseller.chart.data.CombinedData;

/**
 * Created by philipp on 11/06/16.
 */
public interface CombinedDataProvider extends LineDataProvider, BarDataProvider, BubbleDataProvider, CandleDataProvider, ScatterDataProvider {

    CombinedData getCombinedData();
}
