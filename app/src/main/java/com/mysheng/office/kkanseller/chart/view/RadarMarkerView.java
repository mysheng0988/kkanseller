
package com.mysheng.office.kkanseller.chart.view;

import android.content.Context;
import android.widget.TextView;


import com.mysheng.office.kkanseller.R;
import com.mysheng.office.kkanseller.chart.components.MarkerView;
import com.mysheng.office.kkanseller.chart.data.Entry;
import com.mysheng.office.kkanseller.chart.highlight.Highlight;
import com.mysheng.office.kkanseller.chart.utils.MPPointF;

import java.text.DecimalFormat;

/**
 * Custom implementation of the MarkerView.
 * 
 * @author Philipp Jahoda
 */
public class RadarMarkerView extends MarkerView {

    private TextView tvContent;
    private DecimalFormat format = new DecimalFormat("##0");

    public RadarMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);

        tvContent = (TextView) findViewById(R.id.tvContent);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        tvContent.setText(format.format(e.getY()));

        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight() - 10);
    }
}
