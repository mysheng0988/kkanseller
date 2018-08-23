package com.mysheng.office.kkanseller.ImageViewer.imageload;

import android.content.Context;
import android.view.ViewGroup;

import com.mysheng.office.kkanseller.ImageViewer.listener.ProgressViewGet;
import com.mysheng.office.kkanseller.ImageViewer.view.RingLoadingView;
import com.mysheng.office.kkanseller.KkanApplication;


/**
 * Created by liuting on 18/3/19.
 */

public class MyProgressBarGet implements ProgressViewGet<RingLoadingView> {
    @Override
    public RingLoadingView getProgress(Context context) {
        RingLoadingView view = new RingLoadingView(context);
        view.setLayoutParams(new ViewGroup.LayoutParams(KkanApplication.dpToPx(50), KkanApplication.dpToPx(50)));
        return view;
    }

    @Override
    public void onProgressChange(RingLoadingView view, float progress) {
        view.setProgress(progress);
    }
}
