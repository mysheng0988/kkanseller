package com.mysheng.office.kkanseller.banner;

import android.support.v4.view.ViewPager.PageTransformer;

import com.mysheng.office.kkanseller.banner.transformer.AccordionTransformer;
import com.mysheng.office.kkanseller.banner.transformer.BackgroundToForegroundTransformer;
import com.mysheng.office.kkanseller.banner.transformer.CubeInTransformer;
import com.mysheng.office.kkanseller.banner.transformer.CubeOutTransformer;
import com.mysheng.office.kkanseller.banner.transformer.DefaultTransformer;
import com.mysheng.office.kkanseller.banner.transformer.DepthPageTransformer;
import com.mysheng.office.kkanseller.banner.transformer.FlipHorizontalTransformer;
import com.mysheng.office.kkanseller.banner.transformer.FlipVerticalTransformer;
import com.mysheng.office.kkanseller.banner.transformer.ForegroundToBackgroundTransformer;
import com.mysheng.office.kkanseller.banner.transformer.RotateDownTransformer;
import com.mysheng.office.kkanseller.banner.transformer.RotateUpTransformer;
import com.mysheng.office.kkanseller.banner.transformer.ScaleInOutTransformer;
import com.mysheng.office.kkanseller.banner.transformer.StackTransformer;
import com.mysheng.office.kkanseller.banner.transformer.TabletTransformer;
import com.mysheng.office.kkanseller.banner.transformer.ZoomInTransformer;
import com.mysheng.office.kkanseller.banner.transformer.ZoomOutSlideTransformer;
import com.mysheng.office.kkanseller.banner.transformer.ZoomOutTranformer;


public class Transformer {
    public static Class<? extends PageTransformer> Default = DefaultTransformer.class;
    public static Class<? extends PageTransformer> Accordion = AccordionTransformer.class;
    public static Class<? extends PageTransformer> BackgroundToForeground = BackgroundToForegroundTransformer.class;
    public static Class<? extends PageTransformer> ForegroundToBackground = ForegroundToBackgroundTransformer.class;
    public static Class<? extends PageTransformer> CubeIn = CubeInTransformer.class;
    public static Class<? extends PageTransformer> CubeOut = CubeOutTransformer.class;
    public static Class<? extends PageTransformer> DepthPage = DepthPageTransformer.class;
    public static Class<? extends PageTransformer> FlipHorizontal = FlipHorizontalTransformer.class;
    public static Class<? extends PageTransformer> FlipVertical = FlipVerticalTransformer.class;
    public static Class<? extends PageTransformer> RotateDown = RotateDownTransformer.class;
    public static Class<? extends PageTransformer> RotateUp = RotateUpTransformer.class;
    public static Class<? extends PageTransformer> ScaleInOut = ScaleInOutTransformer.class;
    public static Class<? extends PageTransformer> Stack = StackTransformer.class;
    public static Class<? extends PageTransformer> Tablet = TabletTransformer.class;
    public static Class<? extends PageTransformer> ZoomIn = ZoomInTransformer.class;
    public static Class<? extends PageTransformer> ZoomOut = ZoomOutTranformer.class;
    public static Class<? extends PageTransformer> ZoomOutSlide = ZoomOutSlideTransformer.class;
}
