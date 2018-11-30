package com.zyhang.damon.rxjava.support;

import com.zyhang.damon.MvpView;
import com.zyhang.damon.rxjava.DisposableHelper;
import com.zyhang.damon.rxjava.MvpPresenterRx;
import com.zyhang.damon.support.MvpAppCompatActivity;

import androidx.annotation.CallSuper;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by zyhang on 2018/5/15.16:52
 */

public class MvpAppCompatActivityRx<P extends MvpPresenterRx> extends MvpAppCompatActivity<P> implements DisposableHelper, MvpView {

    private CompositeDisposable pause = new CompositeDisposable();
    private CompositeDisposable destroyView = new CompositeDisposable();

    @CallSuper
    @Override
    public void add(Disposable disposable, @DisposeOn int disposeOn) {
        switch (disposeOn) {
            case DISPOSE_ON_PAUSE:
                pause.add(disposable);
                break;
            case DISPOSE_ON_DESTROY_VIEW:
                destroyView.add(disposable);
                break;
        }
    }

    @CallSuper
    @Override
    public void remove(Disposable disposable, @DisposeOn int disposeOn) {
        switch (disposeOn) {
            case DISPOSE_ON_PAUSE:
                pause.remove(disposable);
                break;
            case DISPOSE_ON_DESTROY_VIEW:
                destroyView.remove(disposable);
                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        pause.clear();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        pause.dispose();
        destroyView.dispose();
    }
}
