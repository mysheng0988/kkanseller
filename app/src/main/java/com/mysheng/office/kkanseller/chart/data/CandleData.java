package com.mysheng.office.kkanseller.chart.data;



import com.mysheng.office.kkanseller.chart.interfaces.datasets.ICandleDataSet;

import java.util.List;

public class CandleData extends BarLineScatterCandleBubbleData<ICandleDataSet> {

    public CandleData() {
        super();
    }

    public CandleData(List<ICandleDataSet> dataSets) {
        super(dataSets);
    }

    public CandleData(ICandleDataSet... dataSets) {
        super(dataSets);
    }
}
