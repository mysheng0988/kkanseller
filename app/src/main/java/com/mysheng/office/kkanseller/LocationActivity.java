package com.mysheng.office.kkanseller;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.mysheng.office.kkanseller.RxTool.RxLocation;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.amap.api.location.AMapLocationClientOption.AMapLocationMode.Hight_Accuracy;

public class LocationActivity extends AppCompatActivity {


    private static final String TAG = "TAG";
    private  TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_layout);

        textView=findViewById(R.id.location);
        //sHA1(this);
        startLocation();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    private Disposable disposable;

    private void startLocation() {
        RxLocation.newBuilder(this)
                .mode(Hight_Accuracy)                       //定位模式，默认值是 Height_Accuracy
                .cache(true)                                //缓存策略，默认关闭
                .onceLocationLatest(true)                   //获取最近3s内精度最高的一次定位结果，默认关闭
                .needAddress(true)                         //返回地址描述，默认开启
                .mockEnable(true)                           //外界在定位SDK通过GPS定位时模拟位置，默认关闭
                .wifiScan(false)                            //主动刷新设备wifi模块,默认开启
                .timeout(5000)                              //通过网络定位获取结果的超时时间，默认值是 20000
                .build().locate()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AMapLocation>() {
                    @Override
                    public void accept(AMapLocation location) throws Exception {
                        double latitude = location.getLatitude();               // 纬度
                        double longitude = location.getLongitude();             // 经度
                        float acc = location.getAccuracy();                     // 精度
                        double altitude = location.getAltitude();               // 海拔
                        float speed = location.getSpeed();                      // 速度
                        float bear = location.getBearing();                     // 方向角
                        String build = location.getBuildingId();                // 室内定位建筑物 id
                        String floor = location.getFloor();                     // 室内定位楼层
                        String address = location.getAddress();                 // 地址描述
                        String country = location.getCountry();                 // 国家
                        String province = location.getProvince();               // 省
                        String city = location.getCity();                       // 城市
                        String cityCode = location.getCityCode();               // 城市编码
                        String district = location.getDistrict();               // 城区
                        String adCode = location.getAdCode();                   // 区域编码
                        String street = location.getStreet();                   // 街道
                        String streetNum = location.getStreetNum();             // 街道门牌号
                        String poi = location.getPoiName();                     // 当前位置的 poi 名称
                        String aoi = location.getAoiName();                     // 当前位置的 aoi 名称
                        int gpsStatus = location.getGpsAccuracyStatus();        // 当前 gps 状态
                        int type = location.getLocationType();                  // 定位来源
                        String detail = location.getLocationDetail();           // 定位信息描述
                        textView.setText(address);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        String message = throwable.getMessage();                // 错误信息
                    }
                });
    }

    public static String sHA1(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_SIGNATURES);
            byte[] cert = info.signatures[0].toByteArray();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(cert);
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < publicKey.length; i++) {
                String appendString = Integer.toHexString(0xFF & publicKey[i])
                        .toUpperCase(Locale.US);
                if (appendString.length() == 1)
                    hexString.append("0");
                hexString.append(appendString);
                hexString.append(":");
            }
            String result= hexString.toString();
            return result.substring(0, result.length()-1);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
