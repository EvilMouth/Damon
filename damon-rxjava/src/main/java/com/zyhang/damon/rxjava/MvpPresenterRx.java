package com.zyhang.damon.rxjava;

import android.support.annotation.CallSuper;

import com.zyhang.damon.MvpPresenter;
import com.zyhang.damon.MvpView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by zyhang on 2018/5/15.16:46
 */

public class MvpPresenterRx<View extends MvpView> extends MvpPresenter<View> implements DisposableHelper {

    private CompositeDisposable mDisposable = new CompositeDisposable();

    /**
     * Registers a disposable to automatically dispose it during onDestroy.
     * See {@link CompositeDisposable#add(Disposable)} for details.}
     *
     * @param disposable a disposable to add.
     */
    @CallSuper
    @Override
    public void add(Disposable disposable) {
        this.mDisposable.add(disposable);
    }

    /**
     * Removes and unsubscribes a disposable that has been registered with {@link #add} previously.
     * See {@link CompositeDisposable#remove(Disposable)} for details.
     *
     * @param disposable a disposable to remove.
     */
    @CallSuper
    @Override
    public void remove(Disposable disposable) {
        this.mDisposable.remove(disposable);
    }

    @CallSuper
    @Override
    public void onDestroy() {
        super.onDestroy();
        mDisposable.dispose();
    }
}
