package com.xiongliang.module_news.debug;

import com.xiongliang.common_base.base.BaseApplication;
import com.xiongliang.common_util.AppCore;

public class NewsApplication extends BaseApplication {
    @Override
    protected void initLogic() {
        AppCore.init(this);
    }
}
