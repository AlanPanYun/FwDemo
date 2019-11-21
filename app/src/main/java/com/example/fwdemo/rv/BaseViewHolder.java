package com.example.fwdemo.rv;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Alan
 * @date 2019/11/21
 */
public abstract  class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    private final SparseArray<View> views;

    public BaseViewHolder(ViewGroup viewGroup,int layoutId) {
        this(LayoutInflater.from(viewGroup.getContext()).inflate(layoutId,viewGroup,false));

    }

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        this.views = new SparseArray<>();
    }

    protected abstract void onBaseBindView(BaseViewHolder<T> holder, T bean, int position);
}
