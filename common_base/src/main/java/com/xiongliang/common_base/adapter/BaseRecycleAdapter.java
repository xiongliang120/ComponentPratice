package com.xiongliang.common_base.adapter;

import android.content.Context;
import android.support.annotation.DimenRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.xiongliang.common_util.ResUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



public abstract class BaseRecycleAdapter<T, VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected ArrayList<T> mItems = new ArrayList<>();

    public abstract VH onCreateVHolder(ViewGroup parent, int viewType);

    public abstract void onBindVHolder(VH viewHolder, T data, int position);

    protected Context mContext;

    public BaseRecycleAdapter(Context ctx) {
        this.mContext = ctx;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreateVHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final T item = getItem(position);
        onBindVHolder((VH) holder, item, position);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    /**
     * 获取填充的资源
     *
     * @param resource
     * @param dimen
     * @return
     */
    public View getLayoutView(int resource, @DimenRes int dimen) {
        final View rootView = LayoutInflater.from(mContext).inflate(resource, null);
        final int height = ResUtils.getDimenPixRes(dimen);
        rootView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height));
        return rootView;
    }

    public View getLayoutView(int resource) {
        final View rootView = LayoutInflater.from(mContext).inflate(resource, null);
        rootView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return rootView;
    }

    /**
     * 获取元素
     *
     * @param position
     * @return
     */
    public T getItem(int position) {
        //防止越界
        final int index = (position >= 0 && position < mItems.size()) ? position : 0;
        return mItems.get(index);
    }

    /**
     * 添加元素
     *
     * @param item
     */
    public void addItem(T item) {
        if (item != null) {
            mItems.add(item);
        }
    }

    /**
     * 添加元素
     *
     * @param item
     */
    public void addItem(int index, T item) {
        if (item != null) {
            mItems.add(index, item);
        }
    }

    public void addItems(Collection<? extends T> items) {
        if (items != null) {
            mItems.addAll(items);
        }
    }

    /**
     * 重置元素
     *
     * @param items
     */
    public void setItems(List<? extends T> items) {
        mItems.clear();
        addItems(items);
    }

    /**
     * 移除
     *
     * @param index
     */
    public void removeItem(int index) {
        if (index >= 0 && index < mItems.size()) {
            mItems.remove(index);
        }
    }

    public ArrayList<T> getItems() {
        return mItems;
    }

    public void clearItems() {
        mItems.clear();
    }

    public Context getContext() {
        return mContext;
    }

}
