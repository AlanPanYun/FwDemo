//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.face.like;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Collections;

public class CustomViewPager extends ViewPager {
    private ArrayList<Integer> childCenterXAbs = new ArrayList();
    private SparseArray<Integer> childIndex = new SparseArray();

    public CustomViewPager(Context context) {
        super(context);
        this.init();
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    private void init() {
        this.setClipToPadding(false);
        this.setOverScrollMode(2);
    }

    public void setMargin(int margin){
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) getLayoutParams();
        layoutParams.setMargins(margin,0,margin,0);
        setLayoutParams(layoutParams);

    }

    protected int getChildDrawingOrder(int childCount, int n) {
        if (n == 0 || this.childIndex.size() != childCount) {
            this.childCenterXAbs.clear();
            this.childIndex.clear();
            int viewCenterX = this.getViewCenterX(this);

            for(int i = 0; i < childCount; ++i) {
                int indexAbs = Math.abs(viewCenterX - this.getViewCenterX(this.getChildAt(i)));
                if (this.childIndex.get(indexAbs) != null) {
                    ++indexAbs;
                }

                this.childCenterXAbs.add(indexAbs);
                this.childIndex.append(indexAbs, i);
            }

            Collections.sort(this.childCenterXAbs);
        }

        return (Integer)this.childIndex.get((Integer)this.childCenterXAbs.get(childCount - 1 - n));
    }

    private int getViewCenterX(View view) {
        int[] array = new int[2];
        view.getLocationOnScreen(array);
        return array[0] + view.getWidth() / 2;
    }
}
