package com.xiongliang.common_network.base;

import android.text.TextUtils;


import com.xiongliang.common_network.network.HttpException;
import com.xiongliang.common_util.ToastUtils;

import io.reactivex.observers.ResourceObserver;



public abstract class CommonObserver<T> extends ResourceObserver<T> {

    @Override
    public void onError(Throwable throwable) {
        if (throwable instanceof HttpException) {
            onFailed((HttpException) throwable);
        } else {
            final String message = throwable.getMessage();
            if (!TextUtils.isEmpty(message)) {
                ToastUtils.showShort(message);
            }
        }
    }

    @Override
    public void onComplete() {

    }

    public void onFailed(HttpException exception) {
        final String message = exception.error;
        if (!TextUtils.isEmpty(message)) {
            ToastUtils.showShort(message);
        }
    }

}
