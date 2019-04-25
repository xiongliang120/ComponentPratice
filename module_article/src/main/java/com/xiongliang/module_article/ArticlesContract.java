package com.xiongliang.module_article;


import com.xiongliang.common_network.base.IBaseView;
import com.xiongliang.module_article.bean.ArticlesItem;

import java.util.List;


public interface ArticlesContract {

    interface IView extends IBaseView {

        void onLoadArticlesSuccess(List<ArticlesItem> result, boolean hasMore);

        void onLoadMoreArticlesSuccess(List<ArticlesItem> result, boolean hasMore);

        void onLoadArticlesFailed();

    }

    interface IPresenter {

        void loadArticles();

        void loadMoreArticles();

    }

}
