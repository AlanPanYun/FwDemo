package com.face.like;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * @author Alan
 * @date 2019/5/5
 */
public class BannerViewAdapter<T> extends PagerAdapter {


    public ArrayList<LinearLayout> itemView;
    public ArrayList<T> mData;
    public SparseArray< View> views;

    public BannerViewAdapter() {
        views = new SparseArray<>();
        itemView = new ArrayList<>();
        mData = new ArrayList<>();
    }

    public void setData(ArrayList<T> data) {
        mData.clear();
        mData.addAll(data);
    }


    @Override
    public int getCount() {
        Log.i("size", mData.size() + "-size");
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {

//        if (views.get(position) !=null) {
//            return views.get(position);
//        }
        Log.i("position", "position  = " + position);
        View inflate = LayoutInflater.from(container.getContext())
                .inflate(R.layout.view_banner_item, null);
        container.addView(inflate);
//        views.put(position,inflate);
//        LinearLayout layout = inflate.findViewById(R.id.layout_main);
//        itemView.add(position,layout);
        onBind(mData.get(position), inflate);

//        views.put(position,inflate);

        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(container.getContext(), "positin = " + (position - 2), Toast.LENGTH_SHORT).show();
            }
        });
        return inflate;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        Log.i("destroyItem", "position  = " + (position - 2));
        container.removeView((View) object);
//        itemView.set(position,null);

    }

    private void onBind(T data, View inflate) {

        TextView tvContent = inflate.findViewById(R.id.tv_content);
        tvContent.setText((String) data);
    }

}
