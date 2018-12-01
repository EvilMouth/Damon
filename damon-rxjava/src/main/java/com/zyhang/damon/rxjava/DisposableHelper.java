package com.zyhang.damon.rxjava;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by zyhang on 2018/5/15.17:35
 */

public interface DisposableHelper {
    /**
     * Registers a disposable to automatically dispose it during @disposable.
     * See {@link CompositeDisposable#add(Disposable)} for details.}
     *
     * @param disposable a disposable to add.
     * @param disposeOn  target the disposable
     */
    void add(Disposable disposable, @DisposeOn int disposeOn);

    /**
     * Removes and unsubscribes a disposable that has been registered with {@link #add} previously.
     * See {@link CompositeDisposable#remove(Disposable)} for details.
     *
     * @param disposable a disposable to remove.
     * @param disposeOn  target the disposable
     */
    void remove(Disposable disposable, @DisposeOn int disposeOn);
}
