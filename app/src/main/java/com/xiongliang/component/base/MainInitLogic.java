package com.xiongliang.component.base;

import com.alibaba.android.arouter.launcher.ARouter;
import com.xiongliang.common_base.base.BaseAppLogic;
import com.xiongliang.common_util.AppCore;
import com.xiongliang.component.BuildConfig;

public class MainInitLogic extends BaseAppLogic{
    @Override
    public void onCreate() {
        super.onCreate();
        if(AppCore.isDebugModel()){
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(AppCore.getApplication());
    }
}
