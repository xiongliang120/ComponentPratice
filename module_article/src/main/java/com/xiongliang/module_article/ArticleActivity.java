package com.xiongliang.module_article;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.xiongliang.common_base.custome.CustomItemDecoration;
import com.xiongliang.common_base.manager.CatchLinearLayoutManager;
import com.xiongliang.common_util.AppUtils;
import com.xiongliang.common_util.ResUtils;
import com.xiongliang.module_article.bean.ArticlesItem;

import java.util.List;

@Route(path = "/article/1")
public class ArticleActivity extends Activity implements ArticlesContract.IView {
    ArticlePresenter girlsPresenter;

    protected SmartRefreshLayout mArticlesLayout;

    protected RecyclerView mArticlesRecycle;
    private ArticlesAdapter mArticlesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_activity_girls);
        initView();
        setupRecycle();
        girlsPresenter = new ArticlePresenter();
        girlsPresenter.attachView(this);
        girlsPresenter.loadArticles();
    }

    public void initView(){
        mArticlesLayout = findViewById(R.id.articles_refresh_layout);
        mArticlesRecycle = findViewById(R.id.articles_recycle_view);
    }

    public static void intentStart(Context context){
        Intent intent = new Intent(context, ArticleActivity.class);
        AppUtils.launchApp(context,intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        girlsPresenter.detachView();
    }

    private void setupRecycle() {
        mArticlesRecycle.setLayoutManager(new CatchLinearLayoutManager(this));
        int height = ResUtils.getDimenPixRes(R.dimen.px_48);
        mArticlesRecycle.addItemDecoration(new CustomItemDecoration(this, LinearLayoutManager.VERTICAL, height, ContextCompat.getColor(this, R.color.color_transparent)));
        mArticlesAdapter = new ArticlesAdapter(this);
        mArticlesRecycle.setAdapter(mArticlesAdapter);
    }

    @Override
    public void onLoadArticlesSuccess(List<ArticlesItem> result, boolean hasMore) {
        mArticlesAdapter.setItems(result);
        mArticlesAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadMoreArticlesSuccess(List<ArticlesItem> result, boolean hasMore) {

    }

    @Override
    public void onLoadArticlesFailed() {

    }
}
