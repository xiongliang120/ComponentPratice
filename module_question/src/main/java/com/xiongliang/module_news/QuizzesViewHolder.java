package com.xiongliang.module_news;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiongliang.common_base.adapter.BaseViewHolder;
import com.xiongliang.common_util.ImageUtils;
import com.xiongliang.module_news.bean.QuizzesEntity;

public class QuizzesViewHolder extends BaseViewHolder<QuizzesEntity> {


    public ImageView mIvLogo;


    public TextView mTvTitle;

    private int mPageIndex;

    private QuizzesEntity mQuizzesEntity;

    public QuizzesViewHolder(View itemView, int pageIndex) {
        super(itemView);
        mPageIndex = pageIndex;
        mIvLogo = itemView.findViewById(R.id.quizzes_iv_logo);
        mTvTitle = itemView.findViewById(R.id.quizzes_tv_title);
    }

    @Override
    public void bindData(QuizzesEntity data) {
        this.mQuizzesEntity = data;
        ImageUtils.get().loadImage(mIvLogo, data.image);
        mTvTitle.setText(data.title);
    }

}
