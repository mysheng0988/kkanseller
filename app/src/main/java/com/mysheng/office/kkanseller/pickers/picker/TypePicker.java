package com.mysheng.office.kkanseller.pickers.picker;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.mysheng.office.kkanseller.pickers.adapter.ArrayWheelAdapter;
import com.mysheng.office.kkanseller.pickers.entity.City;
import com.mysheng.office.kkanseller.pickers.entity.County;
import com.mysheng.office.kkanseller.pickers.entity.Province;
import com.mysheng.office.kkanseller.pickers.entity.TypeMax;
import com.mysheng.office.kkanseller.pickers.entity.TypeMiddle;
import com.mysheng.office.kkanseller.pickers.entity.TypeMin;
import com.mysheng.office.kkanseller.pickers.listeners.OnItemPickListener;
import com.mysheng.office.kkanseller.pickers.listeners.OnLinkageListener;
import com.mysheng.office.kkanseller.pickers.listeners.OnMoreWheelListener;
import com.mysheng.office.kkanseller.pickers.util.LogUtils;
import com.mysheng.office.kkanseller.pickers.widget.WheelListView;
import com.mysheng.office.kkanseller.pickers.widget.WheelView;

import java.util.ArrayList;
import java.util.List;


/**
 * 地址选择器（包括大类、中类、小类），地址数据见示例项目assets目录下。
 * @see Province
 * @see City
 * @see County
 */
public class TypePicker extends LinkagePicker {
    private OnLinkageListener onLinkageListener;
    private OnMoreWheelListener onMoreWheelListener;
    //只显示中类及小类
    private boolean hideTypeMax = false;
    //只显示大类及中类
    private boolean hideTypeMin = false;
    //省市区数据
    private ArrayList<TypeMax> typeMaxes = new ArrayList<TypeMax>();

    public TypePicker(Activity activity, ArrayList<TypeMax> typeMaxes) {
        super(activity, new TypeProvider(typeMaxes));
        this.typeMaxes = typeMaxes;
    }

    /**
     * 设置默认选中的类型
     */
    public void setSelectedItem(String typeMax, String typeMiddle, String typeMin) {
        super.setSelectedItem(typeMax, typeMiddle, typeMin);
    }

    public TypeMax getSelectedTypeMax() {
        return typeMaxes.get(selectedFirstIndex);
    }

    public TypeMiddle getSelectedTypeMiddle() {
        return getSelectedTypeMax().getMiddles().get(selectedSecondIndex);
    }

    public TypeMin getSelectedTypeMin() {
        return getSelectedTypeMiddle().getTypeMins().get(selectedThirdIndex);
    }

    /**
     * 隐藏大类，只显示中类和小类。
     * 设置为true的话，地址数据中只需要某大类的数据即可
     * 参见示例中的“assets/Type2.json”
     */
    public void setHideTypeMax(boolean hideTypeMax) {
        this.hideTypeMax = hideTypeMax;
    }

    /**
     * 隐藏县级行政区，只显示省级和市级。
     * 设置为true的话，hideTypeMax将强制为false
     * 数据源依然使用“assets/city.json” 仅在逻辑上隐藏县级选择框，实际项目中应该去掉县级数据。
     */
    public void setHideCounty(boolean hideTypeMin) {
        this.hideTypeMin = hideTypeMin;
    }

    /**
     * 设置滑动监听器
     */
    public void setOnMoreWheelListener(OnMoreWheelListener onMoreWheelListener) {
        this.onMoreWheelListener = onMoreWheelListener;
    }

    public void setOnLinkageListener(OnLinkageListener listener) {
        this.onLinkageListener = listener;
    }

    @NonNull
    @Override
    protected View makeCenterView() {
//        super.makeCenterView();
        if (null == provider) {
            throw new IllegalArgumentException("please set address provider before make view");
        }
        if (hideTypeMin) {
            hideTypeMax = false;
        }
        int[] widths = getColumnWidths(hideTypeMax || hideTypeMin);
        int provinceWidth = widths[0];
        int cityWidth = widths[1];
        int countyWidth = widths[2];
        if (hideTypeMax) {
            provinceWidth = 0;
            cityWidth = widths[0];
            countyWidth = widths[1];
        }
        LinearLayout layout = new LinearLayout(activity);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setGravity(Gravity.CENTER);
        //判断是选择ios滚轮模式还是普通模式
        if(wheelModeEnable){
            final WheelView maxView = new WheelView(activity);
            maxView.setCanLoop(canLoop);
            maxView.setLayoutParams(new LinearLayout.LayoutParams(provinceWidth, WRAP_CONTENT));
            maxView.setTextSize(textSize);
            maxView.setSelectedTextColor(textColorFocus);
            maxView.setUnSelectedTextColor(textColorNormal);
            maxView.setLineConfig(lineConfig);
            maxView.setAdapter(new ArrayWheelAdapter<>(provider.provideFirstData()));
            maxView.setCurrentItem(selectedFirstIndex);
            if (hideTypeMax) {
                maxView.setVisibility(View.GONE);
            }
            layout.addView(maxView);

            final WheelView middleView = new WheelView(activity);
            middleView.setCanLoop(canLoop);
            middleView.setTextSize(textSize);
            middleView.setSelectedTextColor(textColorFocus);
            middleView.setUnSelectedTextColor(textColorNormal);
            middleView.setLineConfig(lineConfig);
            middleView.setAdapter(new ArrayWheelAdapter<>(provider.provideSecondData(selectedFirstIndex)));
            middleView.setCurrentItem(selectedSecondIndex);
            middleView.setLayoutParams(new LinearLayout.LayoutParams(cityWidth, WRAP_CONTENT));

            layout.addView(middleView);

            final WheelView minView = new WheelView(activity);
            minView.setCanLoop(canLoop);
            minView.setTextSize(textSize);
            minView.setSelectedTextColor(textColorFocus);
            minView.setUnSelectedTextColor(textColorNormal);
            minView.setLineConfig(lineConfig);
            minView.setAdapter(new ArrayWheelAdapter<>(provider.provideThirdData(selectedFirstIndex, selectedSecondIndex)));
            minView.setCurrentItem(selectedThirdIndex);
            minView.setLayoutParams(new LinearLayout.LayoutParams(countyWidth, WRAP_CONTENT));
            if (hideTypeMin) {
                minView.setVisibility(View.GONE);
            }
            layout.addView(minView);
            maxView.setOnItemPickListener(new OnItemPickListener<String>() {
                @Override
                public void onItemPicked(int index, String item) {
                    selectedFirstItem = item;
                    selectedFirstIndex = index;
                    if (onMoreWheelListener != null) {
                        onMoreWheelListener.onFirstWheeled(selectedFirstIndex, selectedFirstItem);
                    }
//                    if (!canLinkage) {
//                        return;
//                    }
                    LogUtils.verbose(this, "change cities after province wheeled");
                    selectedSecondIndex = 0;//重置地级索引
                    selectedThirdIndex = 0;//重置县级索引
                    //根据大类获取中类
                    List<String> cities = provider.provideSecondData(selectedFirstIndex);
                    if (cities.size() > 0) {
                        middleView.setAdapter(new ArrayWheelAdapter<>(cities));
                        middleView.setCurrentItem(selectedSecondIndex);
                    } else {
                        middleView.setAdapter(new ArrayWheelAdapter<>(new ArrayList<String>()));
                    }
                    //根据中类获取小类
                    List<String> counties = provider.provideThirdData(selectedFirstIndex, selectedSecondIndex);
                    if (counties.size() > 0) {
                        minView.setAdapter(new ArrayWheelAdapter<>(counties));
                        minView.setCurrentItem(selectedThirdIndex);
                    } else {
                        minView.setAdapter(new ArrayWheelAdapter<>(new ArrayList<String>()));
                    }
                }
            });

            middleView.setOnItemPickListener(new OnItemPickListener<String>() {
                @Override
                public void onItemPicked( int index, String item) {
                    selectedSecondItem = item;
                    selectedSecondIndex = index;
                    if (onMoreWheelListener != null) {
                        onMoreWheelListener.onSecondWheeled(selectedSecondIndex, selectedSecondItem);
                    }
//                    if (!canLinkage) {
//                        return;
//                    }
                    LogUtils.verbose(this, "change counties after city wheeled");
                    selectedThirdIndex = 0;//重置县级索引
                    //根据地市获取区县
                    List<String> counties = provider.provideThirdData(selectedFirstIndex, selectedSecondIndex);
                    if (counties.size() > 0) {
                        //若不是用户手动滚动，说明联动需要指定默认项
                        minView.setAdapter(new ArrayWheelAdapter<>(counties));
                        minView.setCurrentItem(selectedThirdIndex);
                    } else {
                        minView.setAdapter(new ArrayWheelAdapter<>(new ArrayList<String>()));
                    }
                }
            });
            minView.setOnItemPickListener(new OnItemPickListener<String>() {
                @Override
                public void onItemPicked( int index, String item) {
                    selectedThirdItem = item;
                    selectedThirdIndex = index;
                    if (onMoreWheelListener != null) {
                        onMoreWheelListener.onThirdWheeled(selectedThirdIndex, selectedThirdItem);
                    }
                }
            });
        }else{
            final WheelListView maxView = new WheelListView(activity);
            maxView.setCanLoop(canLoop);
            maxView.setLayoutParams(new LinearLayout.LayoutParams(provinceWidth, WRAP_CONTENT));
            maxView.setTextSize(textSize);
            maxView.setSelectedTextColor(textColorFocus);
            maxView.setUnSelectedTextColor(textColorNormal);
            maxView.setLineConfig(lineConfig);
            maxView.setOffset(offset);
            layout.addView(maxView);
            if (hideTypeMax) {
                maxView.setVisibility(View.GONE);
            }

            final WheelListView middleView = new WheelListView(activity);
            middleView.setCanLoop(canLoop);
            middleView.setLayoutParams(new LinearLayout.LayoutParams(cityWidth, WRAP_CONTENT));
            middleView.setTextSize(textSize);
            middleView.setSelectedTextColor(textColorFocus);
            middleView.setUnSelectedTextColor(textColorNormal);
            middleView.setLineConfig(lineConfig);
            middleView.setOffset(offset);
            layout.addView(middleView);

            final WheelListView minView = new WheelListView(activity);
            minView.setCanLoop(canLoop);
            minView.setLayoutParams(new LinearLayout.LayoutParams(countyWidth, WRAP_CONTENT));
            minView.setTextSize(textSize);
            minView.setSelectedTextColor(textColorFocus);
            minView.setUnSelectedTextColor(textColorNormal);
            minView.setLineConfig(lineConfig);
            minView.setOffset(offset);
            layout.addView(minView);
            if (hideTypeMin) {
                minView.setVisibility(View.GONE);
            }

            minView.setItems(provider.provideFirstData(), selectedFirstIndex);
            minView.setOnWheelChangeListener(new WheelListView.OnWheelChangeListener() {
                @Override
                public void onItemSelected(int index, String item) {
                    selectedFirstItem = item;
                    selectedFirstIndex = index;
                    if (onMoreWheelListener != null) {
                        onMoreWheelListener.onFirstWheeled(selectedFirstIndex, selectedFirstItem);
                    }
//                    if (!canLinkage) {
//                        return;
//                    }
                    LogUtils.verbose(this, "change cities after province wheeled");
                    selectedSecondIndex = 0;//重置中类索引
                    selectedThirdIndex = 0;//重置小类索引
                    //根据大类获取中类
                    List<String> middles = provider.provideSecondData(selectedFirstIndex);
                    if (middles.size() > 0) {
                        middleView.setItems(middles, selectedSecondIndex);
                    } else {
                        middleView.setItems(new ArrayList<String>());
                    }
                    //根据中类获取小类
                    List<String> mins = provider.provideThirdData(selectedFirstIndex, selectedSecondIndex);
                    if (mins.size() > 0) {
                        middleView.setItems(mins, selectedThirdIndex);
                    } else {
                        middleView.setItems(new ArrayList<String>());
                    }
                }
            });

            middleView.setItems(provider.provideSecondData(selectedFirstIndex), selectedSecondIndex);
            middleView.setOnWheelChangeListener(new WheelListView.OnWheelChangeListener() {
                @Override
                public void onItemSelected( int index, String item) {
                    selectedSecondItem = item;
                    selectedSecondIndex = index;
                    if (onMoreWheelListener != null) {
                        onMoreWheelListener.onSecondWheeled(selectedSecondIndex, selectedSecondItem);
                    }
//                    if (!canLinkage) {
//                        return;
//                    }
                    LogUtils.verbose(this, "change counties after city wheeled");
                    selectedThirdIndex = 0;//重置小类索引
                    //根据中类获取小类
                    List<String> mins = provider.provideThirdData(selectedFirstIndex, selectedSecondIndex);
                    if (mins.size() > 0) {
                        //若不是用户手动滚动，说明联动需要指定默认项
                        minView.setItems(mins, selectedThirdIndex);
                    } else {
                        minView.setItems(new ArrayList<String>());
                    }
                }
            });

            minView.setItems(provider.provideThirdData(selectedFirstIndex, selectedSecondIndex), selectedThirdIndex);
            minView.setOnWheelChangeListener(new WheelListView.OnWheelChangeListener() {
                @Override
                public void onItemSelected( int index, String item) {
                    selectedThirdItem = item;
                    selectedThirdIndex = index;
                    if (onMoreWheelListener != null) {
                        onMoreWheelListener.onThirdWheeled(selectedThirdIndex, selectedThirdItem);
                    }
                }
            });
        }

        return layout;
    }

    @Override
    public void onSubmit() {
        if (onLinkageListener != null) {
            TypeMax typeMax=getSelectedTypeMax();
            TypeMiddle typeMiddle=getSelectedTypeMiddle();
            TypeMin typeMin = null;
            if (!hideTypeMin) {
                typeMin = getSelectedTypeMin();
            }
            onLinkageListener.onTypePicked(typeMax, typeMiddle, typeMin);
        }
    }


    /**
     * 类型提供者
     */
    public static class TypeProvider implements DataProvider {
        private List<String> firstList = new ArrayList<>();
        private List<List<String>> secondList = new ArrayList<>();
        private List<List<List<String>>> thirdList = new ArrayList<>();

        public TypeProvider(List<TypeMax> typeMaxes) {
            parseData(typeMaxes);
        }

        @Override
        public boolean isOnlyTwo() {
            return thirdList.size() == 0;
        }

        @Override
        public List<String> provideFirstData() {
            return firstList;
        }

        @Override
        public List<String> provideSecondData(int firstIndex) {
            return secondList.get(firstIndex);
        }

        @Override
        public List<String> provideThirdData(int firstIndex, int secondIndex) {
            return thirdList.get(firstIndex).get(secondIndex);
        }

        private void parseData(List<TypeMax> data) {
            int typeMaxSize = data.size();
            //添加大类
            for (int x = 0; x < typeMaxSize; x++) {
                TypeMax pro = data.get(x);
                firstList.add(pro.getAreaName());
                List<TypeMiddle> middles = pro.getMiddles();
                List<String> xMiddles = new ArrayList<>();
                List<List<String>> xMinTypes = new ArrayList<>();
                int middleSize = middles.size();
                //添加中类
                for (int y = 0; y < middleSize; y++) {
                    TypeMiddle typeMiddle = middles.get(y);
                    typeMiddle.setTypeMaxId(pro.getAreaId());
                    xMiddles.add(typeMiddle.getAreaName());
                    List<TypeMin> typeMins = typeMiddle.getTypeMins();
                    ArrayList<String> yTypeMins = new ArrayList<>();
                    int countySize = typeMins.size();
                    //添加区县
                    if (countySize == 0) {
                        yTypeMins.add(typeMiddle.getAreaName());
                    } else {
                        for (int z = 0; z < countySize; z++) {
                            TypeMin min = typeMins.get(z);
                            min.setTypeMiddleId(typeMiddle.getAreaId());
                            yTypeMins.add(min.getAreaName());
                        }
                    }
                    xMinTypes.add(yTypeMins);
                }
                secondList.add(xMiddles);
                thirdList.add(xMinTypes);
            }
        }

    }

}
