package com.mysheng.office.kkanseller.chart.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TextView;


import com.mysheng.office.kkanseller.R;
import com.mysheng.office.kkanseller.chart.charts.Chart;
import com.mysheng.office.kkanseller.chart.charts.LineChart;
import com.mysheng.office.kkanseller.chart.components.MarkerView;
import com.mysheng.office.kkanseller.chart.data.Entry;
import com.mysheng.office.kkanseller.chart.data.LineData;
import com.mysheng.office.kkanseller.chart.data.LineDataSet;
import com.mysheng.office.kkanseller.chart.formatter.IAxisValueFormatter;
import com.mysheng.office.kkanseller.chart.highlight.Highlight;
import com.mysheng.office.kkanseller.chart.interfaces.datasets.ILineDataSet;
import com.mysheng.office.kkanseller.chart.utils.MPPointF;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by xhu_ww on 2018/6/1.
 * description:
 */
public class LineChartMarkView extends MarkerView {

    private TextView tvDate;
    private TextView tvValue0;
    private TextView tvValue1;
    private IAxisValueFormatter xAxisValueFormatter;
    DecimalFormat df = new DecimalFormat("0");

    public LineChartMarkView(Context context, IAxisValueFormatter xAxisValueFormatter) {
        super(context, R.layout.layout_markview);
        this.xAxisValueFormatter = xAxisValueFormatter;
        tvDate = (TextView) findViewById(R.id.tv_date);
        tvValue0 = (TextView) findViewById(R.id.tv_value0);
        tvValue1 = (TextView) findViewById(R.id.tv_value1);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        Chart chart = getChartView();
        if (chart instanceof LineChart) {
            LineData lineData = ((LineChart) chart).getLineData();
            //获取到图表中的所有曲线
            List<ILineDataSet> dataSetList = lineData.getDataSets();
            for (int i = 0; i < dataSetList.size(); i++) {
                LineDataSet dataSet = (LineDataSet) dataSetList.get(i);
                //获取到曲线的所有在Y轴的数据集合，根据当前X轴的位置 来获取对应的Y轴值
                float y = dataSet.getValues().get((int) e.getX()).getY();
                if (i == 0) {
                    tvValue0.setText(dataSet.getLabel() + "：" + df.format(y ) + "步");
                }
                if (i == 1) {
                    tvValue1.setText(dataSet.getLabel() + "：" + df.format(y ) + "步");
                }
            }
            tvDate.setText(xAxisValueFormatter.getFormattedValue(e.getX(), null));
        }
        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
