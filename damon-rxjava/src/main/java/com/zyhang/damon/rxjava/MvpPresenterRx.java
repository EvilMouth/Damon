package com.zyhang.damon.rxjava;

import com.zyhang.damon.MvpPresenter;
import com.zyhang.damon.MvpView;

import androidx.annotation.CallSuper;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by zyhang on 2018/5/15.16:46
 */

public class MvpPresenterRx<View extends MvpView> extends MvpPresenter<View> implements DisposableHelper {

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

    @CallSuper
    @Override
    protected void onPause() {
        pause.clear();
    }

    @CallSuper
    @Override
    protected void onDestroyView() {
        destroyView.clear();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pause.dispose();
        destroyView.dispose();
    }
}
