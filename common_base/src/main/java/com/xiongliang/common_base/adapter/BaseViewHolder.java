package com.xiongliang.common_base.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    public View mItemView;

    public BaseViewHolder(View itemView) {
        super(itemView);
        this.mItemView = itemView;
    }

    public abstract void bindData(T data);

    public void setOnItemClickListener(View.OnClickListener onClickListener) {
        if (mItemView != null) {
            mItemView.setOnClickListener(onClickListener);
        }
    }

}
