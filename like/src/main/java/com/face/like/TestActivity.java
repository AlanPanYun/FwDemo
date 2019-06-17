package com.face.like;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.face.library.ARouterConstant;
import com.face.library.ARouterUtils;
import com.face.library.ImageLoadUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * @author Alan
 * @date 2019/4/18
 */
@Route(path = ARouterConstant.like_test)
public class TestActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private int bannerStyle = BannerConfig.CIRCLE_INDICATOR;
    private ViewPager.OnPageChangeListener mOnPageChangeListener;
    private int currentItem = 2;
    private ViewPager viewPager;
    private int count = 0;
    private BannerViewAdapter viewAdapter;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_core);

        initView();
    }

    private void initView() {
        String property = System.getProperty("java.vm.version");
        Log.i("property",property);

//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//            }
//        });

        findViewById(R.id.tv_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouterUtils.navation(ARouterConstant.AVATAR_ACTIVITY);

            }
        });
        WindowManager windowManager = this.getWindowManager();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int widthPixels = displayMetrics.widthPixels;

        viewPager = findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setPageTransformer(false, new AlphaTransformer());

        viewAdapter = new BannerViewAdapter<String>();
        ArrayList<String> data = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            data.add("test" + i);
        }
        count = data.size();
        initViewPage(data);

        viewAdapter.setData(data);
        viewPager.setAdapter(viewAdapter);
        viewPager.setCurrentItem(2);
        initViewPagerScroll();
        viewPager.addOnPageChangeListener(this);

//        mOnPageChangeListener = this;

        ImageView imageView = findViewById(R.id.image);
        ImageLoadUtils.loadImage(this,null,imageView);

    }


    private void initViewPage(ArrayList<String> data) {


        ArrayList<String> newdata = new ArrayList<>();
        final int size = data.size();

        for (int i = 0; i <= size + 3; i++) {
            String content = "";

            if (i == 0){
                content = data.get(size -2);
            }else if (i == 1){
                content = data.get(size -1);
            }else if (i== size+2){
                content = data.get(0);
            }else if (i == size +3){
                content = data.get(1);
            }else {
                content = data.get(i-2);
            }

//            if (i == 0) {
//                content = data.get(size - 1);
//            } else if (i == size + 1) {
//                content = data.get(0);
//            } else {
//                content = data.get(i - 1);
//            }
            newdata.add(content);
        }

        data.clear();
        data.addAll(newdata);
    }

    private void initViewPagerScroll() {
        try {
            Field mField = ViewPager.class.getDeclaredField("mScroller");
            mField.setAccessible(true);
            BannerScroller mScroller = new BannerScroller(viewPager.getContext());
            mScroller.setDuration(800);
            mField.set(viewPager, mScroller);
        } catch (Exception e) {
            Log.e("tag", e.getMessage());
        }
    }


    @Override
    public void onPageScrollStateChanged(int state) {
        if (mOnPageChangeListener != null) {
            mOnPageChangeListener.onPageScrollStateChanged(state);
        }
        Log.i("banner", "state : " + state);
        switch (state) {
            case 0://No operation
                if (currentItem == count + 2) {
                    viewPager.setCurrentItem(2, false);
                } else if (currentItem == 1) {
                    viewPager.setCurrentItem(count+1, false);
                }
                break;
            case 1://start Sliding
//                Log.i("currentItem","currentItem = "+currentItem);
//                if (currentItem == count + 2) {
//                    viewPager.setCurrentItem(2, false);
//                } else if (currentItem == 1) {
//                    viewPager.setCurrentItem(count+1, false);
//                }
                break;
            case 2://end Sliding
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        Log.i("onPageScrolled","position  = "+position+"  positionOffset= "+positionOffset+
        "  positionOffsetPixels= "+positionOffsetPixels);
//        if (mOnPageChangeListener != null) {
//            mOnPageChangeListener.onPageScrolled(toRealPosition(position), positionOffset, positionOffsetPixels);
//        }
    }

    @Override
    public void onPageSelected(int position) {
        currentItem = position;
        Log.i("onPageSelected","position = "+position);
    }

    public int toRealPosition(int position) {
        int realPosition = (position - 1) % count;
        if (realPosition < 0)
            realPosition += count;
        return realPosition;
    }


    public int bsearch(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] >= value) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        if (low < n && a[low]==value) return low;
        else return -1;
    }


}
