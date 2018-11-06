package com.mysheng.office.kkanseller.chart.interfaces.dataprovider;


import com.mysheng.office.kkanseller.chart.components.YAxis;
import com.mysheng.office.kkanseller.chart.data.LineData;

public interface LineDataProvider extends BarLineScatterCandleBubbleDataProvider {

    LineData getLineData();

    YAxis getAxis(YAxis.AxisDependency dependency);
}
