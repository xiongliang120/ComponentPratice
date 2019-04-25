package com.xiongliang.module_article;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiongliang.common_base.adapter.BaseViewHolder;
import com.xiongliang.common_util.ImageUtils;
import com.xiongliang.module_article.bean.ArticlesItem;


public class ArticlesViewHolder extends BaseViewHolder<ArticlesItem> {

    public ImageView mIvLogo;

    public TextView mTvTitle;

    private ArticlesItem articlesItem;

    public ArticlesViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(ArticlesItem data) {
        this.articlesItem = data;
        ImageUtils.get().loadImage(mIvLogo, data.images);
        mTvTitle.setText(data.title);
    }


}
