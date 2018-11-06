package com.mysheng.office.kkanseller.chart.interfaces.dataprovider;


import com.mysheng.office.kkanseller.chart.data.ScatterData;

public interface ScatterDataProvider extends BarLineScatterCandleBubbleDataProvider {

    ScatterData getScatterData();
}
