package com.example.fwdemo.rv;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alan
 * @date 2019/11/21
 */
public abstract class BaseRecyclerAdapter<T,K extends BaseViewHolder> extends RecyclerView.Adapter<K> {

    private RecyclerView recyclerView;
    private List<T> mData = new ArrayList<>();
    public BaseRecyclerAdapter() {

    }

    public BaseRecyclerAdapter(List<T> data) {
        this.mData = data==null?new ArrayList<T>():data;
    }


    @Override
    public void onBindViewHolder(@NonNull K holder, int position) {
        holder.onBaseBindView(holder,mData.get(position),position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addAll(List<T> data){
        this.mData.addAll(data);
    }

    public void setRecyclerView(RecyclerView recyclerView){
        this.recyclerView = recyclerView;
    }
}
