package com.example.fwdemo.rv;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author Alan
 * @date 2019/11/21
 */
public abstract class BaseBindingAdapter<T,B extends ViewDataBinding>
extends BaseRecyclerAdapter<T,BaseBindingHolder<T,B>>{

    private int mLayoutId;

    public BaseBindingAdapter(int mLayoutId) {
        this.mLayoutId = mLayoutId;
    }

    public BaseBindingAdapter(int mLayoutId, List<T> data) {
        super(data);
        this.mLayoutId = mLayoutId;
    }

    @NonNull
    @Override
    public BaseBindingHolder<T, B> onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return    new ViewHolder(viewGroup, mLayoutId);
    }

    private class  ViewHolder extends BaseBindingHolder<T,B>{


        public ViewHolder(ViewGroup viewGroup, int layoutId) {
            super(viewGroup, layoutId);
        }

        @Override
        protected void onBindingView(T bean, B binding, int position) {
            bindView(bean,binding,position);
        }

    }
    protected abstract void bindView(T bean , B binding,int position);
}
