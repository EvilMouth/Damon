package com.zyhang.damon.rxjava.support;

import com.zyhang.damon.MvpView;
import com.zyhang.damon.rxjava.DisposableDelegate;
import com.zyhang.damon.rxjava.DisposableDelegateFactory;
import com.zyhang.damon.rxjava.DisposableHelper;
import com.zyhang.damon.rxjava.DisposeOn;
import com.zyhang.damon.rxjava.RxMvpPresenter;
import com.zyhang.damon.support.MvpAppCompatActivity;

import androidx.annotation.CallSuper;
import io.reactivex.disposables.Disposable;

/**
 * Created by zyhang on 2018/5/15.16:52
 */

public class RxMvpAppCompatActivity<P extends RxMvpPresenter> extends MvpAppCompatActivity<P> implements DisposableHelper, MvpView {

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

    @Override
    public void onPause() {
        super.onPause();
        mDisposableDelegate.clearPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((Disposable) mDisposableDelegate).dispose();
    }
}
