package com.xiongliang.component.base;

import com.xiongliang.common_base.base.BaseApplication;
import com.xiongliang.common_util.AppCore;
import com.xiongliang.component.BuildConfig;
import com.xiongliang.module_article.base.ArticleInitLogic;


public class ComponentApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        boolean isDebugModel = BuildConfig.MODE_MARKET ? false : BuildConfig.MODE_DEBUG;
        boolean isLogModel = BuildConfig.MODE_MARKET ? false : BuildConfig.MODE_LOGGER;

        AppCore.enableDebug(isDebugModel);
        AppCore.enableLogger(isLogModel);
    }

    @Override
    protected void initLogic() {
        registerApplicationLogin(MainInitLogic.class);
        registerApplicationLogin(ArticleInitLogic.class);
    }
}
