package com.xiongliang.module_news;


import com.xiongliang.common_network.base.IBaseView;
import com.xiongliang.module_news.bean.QuizzesData;


public interface NewsContract {

    interface IView extends IBaseView {

        void onLoadArticlesSuccess(QuizzesData result);

        void onLoadArticlesFailed();

    }

    interface IPresenter {

        void loadAllNews();
    }

}
