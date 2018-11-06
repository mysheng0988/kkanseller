package com.mysheng.office.kkanseller.chart.interfaces.dataprovider;


import com.mysheng.office.kkanseller.chart.data.CandleData;

public interface CandleDataProvider extends BarLineScatterCandleBubbleDataProvider {

    CandleData getCandleData();
}
