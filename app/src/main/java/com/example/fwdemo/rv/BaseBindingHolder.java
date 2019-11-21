package com.example.fwdemo.rv;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * @author Alan
 * @date 2019/11/21
 */
public abstract class BaseBindingHolder<T,B extends ViewDataBinding> extends BaseViewHolder<T>{

    public  final  B binding;

    public BaseBindingHolder(ViewGroup viewGroup, int layoutId) {
        super(DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),layoutId,viewGroup,false).getRoot());
        binding = DataBindingUtil.getBinding(this.itemView);
    }

    @Override
    protected void onBaseBindView(BaseViewHolder<T> holder, T bean, int position) {
        onBindingView(bean,binding,position);
    }

    protected abstract  void onBindingView(T bean, B binding, int position);
}
