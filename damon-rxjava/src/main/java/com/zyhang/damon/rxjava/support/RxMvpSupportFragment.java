package com.zyhang.damon.rxjava.support;

import com.zyhang.damon.rxjava.DisposableDelegate;
import com.zyhang.damon.rxjava.DisposableDelegateFactory;
import com.zyhang.damon.rxjava.DisposableHelper;
import com.zyhang.damon.rxjava.DisposeOn;
import com.zyhang.damon.rxjava.RxMvpPresenter;
import com.zyhang.damon.support.MvpSupportFragment;

import androidx.annotation.CallSuper;
import io.reactivex.disposables.Disposable;

/**
 * Created by zyhang on 2018/5/15.16:56
 */

public class RxMvpSupportFragment<P extends RxMvpPresenter> extends MvpSupportFragment<P> implements DisposableHelper {

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
    public void onDestroyView() {
        super.onDestroyView();
        mDisposableDelegate.clearDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (!requireActivity().isChangingConfigurations()) {
            ((Disposable) mDisposableDelegate).dispose();
        }
    }
}
