package com.example.fwdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * @author Alan
 * @date 2019/4/22
 */
public class RatingBarView extends LinearLayout implements View.OnClickListener {


    private int currentRating;
    private int dimension;

    public RatingBarView(Context context) {
        super(context);

        initView(context);
    }

    public RatingBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RatingBarView);
        dimension = (int) typedArray.getDimension(R.styleable.RatingBarView_ratingWidth, 20);
        initView(context);
    }



    private void initView(Context context) {
        for (int i = 0; i < 5; i++) {
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setTag(i);
            LinearLayout.LayoutParams layoutParams = new LayoutParams(dimension, dimension);
            layoutParams.setMargins(20,10,20,10);
            imageView.setLayoutParams(layoutParams);
            imageView.setImageResource(R.drawable.ic_star_border_black_24dp);
            imageView.setOnClickListener(this);
            addView(imageView);
        }
    }

    @Override
    public void onClick(View view) {
        int tag = (int) view.getTag();
        currentRating = tag + 1;
        updateView(tag);
    }

    private void updateView(int tag) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) getChildAt(i);
            if (i <= tag) {
                imageView.setImageResource(R.drawable.ic_star_black_24dp);
            } else {
                imageView.setImageResource(R.drawable.ic_star_border_black_24dp);
            }
        }
    }

    public int getCurrentRating() {
        return currentRating;
    }

}
