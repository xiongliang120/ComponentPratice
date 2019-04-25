package com.xiongliang.common_base.routerinterceptor;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.xiongliang.common_util.SPUtils;

@Interceptor(name = "login", priority = 6)
public class LoginInterceptorImpl implements IInterceptor {
    private Context context; //context是application对象
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        String path = postcard.getPath();
        boolean isLogin = SPUtils.getBoolean("loginstatus", false);

        if (isLogin) { // 如果已经登录不拦截
            callback.onContinue(postcard);
        } else {  // 如果没有登录
            switch (path) {
                // 不需要登录的直接进入这个页面
                case "/article/1":
                    callback.onContinue(postcard);
                    break;
                case "/news/1":
                    callback.onContinue(postcard);
                    break;
                default:
                    callback.onInterrupt(null);
                    // 需要登录的直接拦截下来
                    break;
            }
        }

    }

    @Override
    public void init(Context context) {
        this.context = context;
    }
}
