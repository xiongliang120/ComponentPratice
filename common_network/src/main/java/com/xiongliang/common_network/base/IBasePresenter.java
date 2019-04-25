package com.xiongliang.common_network.base;

public interface IBasePresenter<T extends IBaseView> {
    /**
     * 添加View
     * @param view
     */
    void attachView(T view);

    /**
     * 剥离View
     */
    void detachView();
}
