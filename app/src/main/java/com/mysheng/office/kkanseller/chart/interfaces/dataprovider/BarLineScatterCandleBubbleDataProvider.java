package com.mysheng.office.kkanseller.chart.interfaces.dataprovider;


import com.mysheng.office.kkanseller.chart.components.YAxis;
import com.mysheng.office.kkanseller.chart.data.BarLineScatterCandleBubbleData;
import com.mysheng.office.kkanseller.chart.utils.Transformer;

public interface BarLineScatterCandleBubbleDataProvider extends ChartInterface {

    Transformer getTransformer(YAxis.AxisDependency axis);
    boolean isInverted(YAxis.AxisDependency axis);
    
    float getLowestVisibleX();
    float getHighestVisibleX();

    BarLineScatterCandleBubbleData getData();
}
