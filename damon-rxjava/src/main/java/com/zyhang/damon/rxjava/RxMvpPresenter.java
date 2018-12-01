package com.zyhang.damon.rxjava;

import com.zyhang.damon.MvpPresenter;
import com.zyhang.damon.MvpView;

import androidx.annotation.CallSuper;
import io.reactivex.disposables.Disposable;

/**
 * Created by zyhang on 2018/5/15.16:46
 */

public class RxMvpPresenter<View extends MvpView> extends MvpPresenter<View> implements DisposableHelper {

    private DisposableDelegate mDisposableDelegate = new DisposableDelegateFactory();

    @CallSuper
    @Override
    public void add(Disposable disposable, @DisposeOn int disposeOn) {
        mDisposableDelegate.add(disposable, disposeOn);
    }

    @CallSuper
    @Override
    public void remove(Disposable disposable, @DisposeOn int disposeOn) {
        mDisposableDelegate.remove(disposable, disposeOn);
    }

    @CallSuper
    @Override
    protected void onPause() {
        mDisposableDelegate.clearPause();
    }

    @CallSuper
    @Override
    protected void onDestroyView() {
        mDisposableDelegate.clearDestroyView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((Disposable) mDisposableDelegate).dispose();
    }
}
