package com.zyhang.damon.rxjava;

import io.reactivex.disposables.Disposable;

/**
 * Created by zyhang on 2018/5/15.17:35
 */

public interface DisposableHelper {
    void add(Disposable disposable);

    void remove(Disposable disposable);
}
