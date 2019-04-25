package com.xiongliang.module_news;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.xiongliang.common_base.adapter.BaseRecycleAdapter;
import com.xiongliang.module_news.bean.QuizzesEntity;


public class QuizzesAdapter extends BaseRecycleAdapter<QuizzesEntity, QuizzesViewHolder> {

    private int mPageIndex;

    public QuizzesAdapter(Context ctx, int index) {
        super(ctx);
        this.mPageIndex = index;
    }

    @Override
    public QuizzesViewHolder onCreateVHolder(ViewGroup parent, int viewType) {
        View overView = getLayoutView(R.layout.question_activity_quizzes_item_layer, R.dimen.px_240);
        QuizzesViewHolder holder = new QuizzesViewHolder(overView, mPageIndex);
        return holder;
    }

    @Override
    public void onBindVHolder(QuizzesViewHolder viewHolder, QuizzesEntity data, int position) {
        viewHolder.bindData(data);
    }

}
