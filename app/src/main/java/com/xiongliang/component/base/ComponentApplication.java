package com.xiongliang.component.base;

import com.xiongliang.common_base.base.BaseApplication;
import com.xiongliang.common_util.AppCore;
import com.xiongliang.component.BuildConfig;
import com.xiongliang.module_article.base.ArticleInitLogic;


public class ComponentApplication extends BaseApplication {
    @Override
    protected void initLogic() {
        registerApplicationLogin(MainInitLogic.class);
        registerApplicationLogin(ArticleInitLogic.class);
    }
}
