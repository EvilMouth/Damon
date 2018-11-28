package com.zyhang.damon.rxjava;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by zyhang on 2018/5/15.17:35
 */

public interface DisposableHelper {

    int DISPOSE_ON_PAUSE = 1;
    int DISPOSE_ON_DESTROY_VIEW = 2;

    @IntDef({DISPOSE_ON_PAUSE, DISPOSE_ON_DESTROY_VIEW})
    @Retention(RetentionPolicy.SOURCE)
    @interface DisposeOn {
    }

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
