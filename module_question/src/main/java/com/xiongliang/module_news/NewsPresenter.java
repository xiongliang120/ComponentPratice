package com.xiongliang.module_news;

import com.xiongliang.common_network.base.AbsBasePresenter;
import com.xiongliang.common_network.base.CommonObserver;
import com.xiongliang.common_network.bean.HttpParams;
import com.xiongliang.common_network.network.HttpUtils;
import com.xiongliang.module_news.bean.QuizzesData;

import io.reactivex.android.schedulers.AndroidSchedulers;


public class NewsPresenter extends AbsBasePresenter<NewsContract.IView> implements NewsContract.IPresenter {

    @Override
    public void loadAllNews() {
        HttpParams httpParams = HttpParams.get();
        registerSub(HttpUtils.doPost("/quizzes/all", httpParams, QuizzesData.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new CommonObserver<QuizzesData>() {
                    @Override
                    public void onNext(QuizzesData quizzesData) {
                        if (isActive()) {
                            handleNews(quizzesData);
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


   public void handleNews(QuizzesData quizzesData){
       getTargetView().onLoadArticlesSuccess(quizzesData);
   }
}
