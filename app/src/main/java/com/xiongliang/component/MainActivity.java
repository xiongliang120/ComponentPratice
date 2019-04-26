package com.xiongliang.component;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.alibaba.android.arouter.launcher.ARouter;
import com.xiongliang.common_base.routerinterceptor.LoginNavigationCallbackImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.article)
    protected Button article;

    @BindView(R.id.news)
    protected Button news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.article)
    public void onClickArticle(){
        ARouter.getInstance().build("/article/1")
                .navigation();
    }


    @OnClick(R.id.news)
    public void onClickNews(){
        ARouter.getInstance().build("/news/1")
                .navigation(MainActivity.this, new LoginNavigationCallbackImpl());
    }

    @OnClick(R.id.fragment)
    public void onClickArticleFragment(){
        Fragment articleFragment = (Fragment) ARouter.getInstance().build("/article/fragment").navigation();
        if(articleFragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container,articleFragment)
                    .commit();
        }
    }
}
