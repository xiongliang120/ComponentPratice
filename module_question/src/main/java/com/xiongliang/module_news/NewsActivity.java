package com.xiongliang.module_news;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.xiongliang.common_base.custome.CustomItemDecoration;
import com.xiongliang.common_base.manager.CatchLinearLayoutManager;
import com.xiongliang.common_util.ResUtils;
import com.xiongliang.module_news.bean.QuizzesData;

@Route(path = "/news/1")
public class NewsActivity extends Activity implements NewsContract.IView{
    protected RecyclerView mQuizzesRecycle;
    NewsPresenter newsPresenter;

    private QuizzesAdapter mQuizzesAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_activity_news);
        mQuizzesRecycle = findViewById(R.id.quizzes_recycle_view);
        setupRecycle();
        newsPresenter = new NewsPresenter();
        newsPresenter.attachView(this);
        newsPresenter.loadAllNews();
    }


    public void setupRecycle(){
        mQuizzesRecycle.setLayoutManager(new CatchLinearLayoutManager(this));
        int height = ResUtils.getDimenPixRes(R.dimen.px_24);
        mQuizzesRecycle.addItemDecoration(new CustomItemDecoration(this, LinearLayoutManager.VERTICAL, height, ContextCompat.getColor(this, R.color.color_transparent)));

//        int pageIndex = getArguments().getInt(EXTRA_INDEX, 0);
        int pageIndex  =0;
        mQuizzesAdapter = new QuizzesAdapter(this, pageIndex);
        mQuizzesRecycle.setAdapter(mQuizzesAdapter);
    }

    @Override
    public void onLoadArticlesSuccess(QuizzesData result) {
        if (mQuizzesAdapter != null) {
            mQuizzesAdapter.setItems(result.quizzes);
            mQuizzesAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onLoadArticlesFailed() {

    }
}
