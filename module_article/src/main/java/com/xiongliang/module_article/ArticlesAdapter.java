package com.xiongliang.module_article;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.xiongliang.common_base.adapter.BaseRecycleAdapter;
import com.xiongliang.module_article.bean.ArticlesItem;


public class ArticlesAdapter extends BaseRecycleAdapter<ArticlesItem, ArticlesViewHolder> {

    public ArticlesAdapter(Context ctx) {
        super(ctx);
    }

    @Override
    public ArticlesViewHolder onCreateVHolder(ViewGroup parent, int viewType) {
        View overView = getLayoutView(R.layout.article_activity_articles_item_layer, R.dimen.px_332);
        ArticlesViewHolder holder = new ArticlesViewHolder(overView);
        holder.mIvLogo = overView.findViewById(R.id.articles_iv_logo);
        holder.mTvTitle = overView.findViewById(R.id.articles_tv_title);
        return holder;
    }

    @Override
    public void onBindVHolder(ArticlesViewHolder viewHolder, ArticlesItem data, int position) {
        viewHolder.bindData(data);
    }

}
