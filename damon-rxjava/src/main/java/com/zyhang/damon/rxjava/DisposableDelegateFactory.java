package com.zyhang.damon.rxjava;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import static com.zyhang.damon.rxjava.DisposeOn.DESTROY_VIEW;
import static com.zyhang.damon.rxjava.DisposeOn.PAUSE;

/**
 * Created by zyhang on 2018/11/30.17:32
 */
public class DisposableDelegateFactory implements DisposableDelegate, Disposable {

    private CompositeDisposable pause;
    private CompositeDisposable destroyView;

    @Override
    public void add(Disposable disposable, int disposeOn) {
        switch (disposeOn) {
            case PAUSE:
                pause().add(disposable);
                break;
            case DESTROY_VIEW:
                destroyView().add(disposable);
                break;
        }
    }

    @Override
    public void remove(Disposable disposable, int disposeOn) {
        switch (disposeOn) {
            case PAUSE:
                pause().remove(disposable);
                break;
            case DESTROY_VIEW:
                destroyView().remove(disposable);
                break;
        }
    }

    @Override
    public void clearPause() {
        pause().clear();
    }

    @Override
    public void clearDestroyView() {
        destroyView().clear();
    }

    @Override
    public void dispose() {
        pause().dispose();
        destroyView().dispose();
    }

    @Override
    public boolean isDisposed() {
        return pause().isDisposed() && destroyView().isDisposed();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public CompositeDisposable pause() {
        if (pause == null) {
            pause = new CompositeDisposable();
        }
        return pause;
    }

    public CompositeDisposable destroyView() {
        if (destroyView == null) {
            destroyView = new CompositeDisposable();
        }
        return destroyView;
    }
}
