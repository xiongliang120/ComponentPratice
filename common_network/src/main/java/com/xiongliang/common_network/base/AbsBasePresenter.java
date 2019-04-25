package com.xiongliang.common_network.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class AbsBasePresenter<V extends IBaseView> implements IBasePresenter<V> {
   private V mTargetView;

    /**
     * 综合订阅
     */
    private CompositeDisposable mCompositeDisposable;


    @Override
    public void attachView(V view) {
        this.mTargetView = view;
    }

    @Override
    public void detachView() {
        unregisterSub();
//        unregisterRecycle();
        this.mTargetView = null;
    }

    /**
     * 撤销
     */
    public void cancel() {
        unregisterSub();
    }

    /**
     * View层
     * 是否已附加
     *
     * @return
     */
    protected boolean isActive() {
        return mTargetView != null;
    }

    /**
     * 获取目标
     *
     * @return
     */
    protected V getTargetView() {
        return mTargetView;
    }

    /**
     * 注册订阅
     *
     * @param disposable
     */
    protected void registerSub(Disposable... disposable) {
        getCompositeDisposable().addAll(disposable);
    }

    /**
     * 清理订阅
     */
    protected void unregisterSub() {
        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.clear();
        }
    }

    private CompositeDisposable getCompositeDisposable() {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        return mCompositeDisposable;
    }
}
