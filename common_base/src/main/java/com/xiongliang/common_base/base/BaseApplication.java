package com.xiongliang.common_base.base;

import android.app.Application;

import com.xiongliang.common_util.AppCore;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseApplication extends Application {
    private List<Class<? extends BaseAppLogic>> logicList = new ArrayList<>();

    private List<BaseAppLogic> logicClassList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        AppCore.init(this);
        initLogic();
        logicCreate();
    }

    protected abstract void initLogic();

    public void registerApplicationLogin(Class<? extends BaseAppLogic> logicClass){
        logicList.add(logicClass);
    }

    private void logicCreate(){
        for (Class<? extends BaseAppLogic> logicClass : logicList){
            try{
                BaseAppLogic appLogic = logicClass.newInstance();
                logicClassList.add(appLogic);
                appLogic.onCreate();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        for(BaseAppLogic appLogic:logicClassList){
            appLogic.onTerminate();
        }
    }
}
