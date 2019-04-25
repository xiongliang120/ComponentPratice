package com.xiongliang.module_article;

import com.xiongliang.common_network.base.AbsBasePresenter;
import com.xiongliang.common_network.base.CommonObserver;
import com.xiongliang.common_network.bean.HttpParams;
import com.xiongliang.common_network.network.HttpUtils;
import com.xiongliang.module_article.bean.ArticlesData;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class ArticlePresenter extends AbsBasePresenter<ArticlesContract.IView> implements ArticlesContract.IPresenter {
    private int mPageNumber = 1;


    public void loadArticles() {
        HttpParams httpParams = HttpParams.get();
        httpParams.addParam("page_num", mPageNumber = 1);
        httpParams.addParam("page_size", 10);
        registerSub(HttpUtils.doPost("/article/list", httpParams, ArticlesData.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new CommonObserver<ArticlesData>() {
                    @Override
                    public void onNext(ArticlesData articlesData) {
                        if (isActive()) {
                            handleArticles(articlesData);
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        super.onError(throwable);
                        if (isActive()) {
                            getTargetView().onLoadArticlesFailed();
                        }
                    }
                }));
    }

    @Override
    public void loadMoreArticles() {

    }

    private void handleArticles(ArticlesData articlesData) {
        if (articlesData != null) {
            getTargetView().onLoadArticlesSuccess(articlesData.list, articlesData.hasMore);
        } else {
            getTargetView().onLoadArticlesFailed();
        }
    }
}
