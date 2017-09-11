package com.zyhang.damon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;

import java.util.concurrent.CopyOnWriteArrayList;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * ProjectName:Damon
 * Description:
 * Created by zyhang on 2017/4/28.下午10:39
 * Modify by:
 * Modify time:
 * Modify remark:
 */

public class MvpPresenter<View extends MvpView> implements MvpPresenterHelper, LifecycleProvider<ActivityEvent> {

    private View mView;

    /**
     * may null when call this method before {@link #onCreate(Bundle, Bundle)}
     *
     * @return mView
     */
    public View getView() {
        return mView;
    }

    private BehaviorSubject<ActivityEvent> mLifecycleSubject = BehaviorSubject.create();

    private CompositeDisposable mDisposable = new CompositeDisposable();

    /**
     * Registers a disposable to automatically dispose it during onDestroy.
     * See {@link CompositeDisposable#add(Disposable)} for details.}
     *
     * @param disposable a disposable to add.
     */
    public void add(Disposable disposable) {
        this.mDisposable.add(disposable);
    }

    /**
     * Removes and unsubscribes a disposable that has been registered with {@link #add} previously.
     * See {@link CompositeDisposable#remove(Disposable)} for details.
     *
     * @param disposable a disposable to remove.
     */
    public void remove(Disposable disposable) {
        this.mDisposable.remove(disposable);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * called when {@link Activity#onCreate(Bundle)},{@link Fragment#onCreate(Bundle)}
     *
     * @param arguments  {@link Activity#getIntent()},{@link Intent#getExtras()},{@link Fragment#getArguments()}
     * @param savedState If the presenter is being re-instantiated after a process restart then this Bundle
     *                   contains the data it supplied in {@link #onSave}.
     */
    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedState) {
    }

    /**
     * called when {@link Activity#onStart()},{@link Fragment#onStart()}
     */
    @Override
    public void onStart() {
    }

    /**
     * called when {@link Activity#onSaveInstanceState(Bundle)},{@link Fragment#onSaveInstanceState(Bundle)}
     *
     * @param state a non-null bundle which should be used to put presenter's state into.
     */
    @Override
    public void onSave(Bundle state) {
    }

    /**
     * called when {@link Activity#onResume()},{@link Fragment#onResume()}
     */
    public void onResume() {
    }

    /**
     * called when {@link Activity#onPause()},{@link Fragment#onPause()}
     */
    @Override
    public void onPause() {
    }

    /**
     * called when {@link Activity#onStop()},{@link Fragment#onStop()}
     */
    @Override
    public void onStop() {
    }

    /**
     * called when {@link Activity#onDestroy()},{@link Fragment#onDestroy()}
     */
    @CallSuper
    @Override
    public void onDestroy() {
        mDisposable.dispose();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    void create(View view, @Nullable Bundle arguments, @Nullable Bundle savedState) {
        mLifecycleSubject.onNext(ActivityEvent.CREATE);
        mView = view;
        onCreate(arguments, savedState);
    }

    void start() {
        mLifecycleSubject.onNext(ActivityEvent.START);
        onStart();
    }

    void save(Bundle state) {
        onSave(state);
    }

    void resume() {
        mLifecycleSubject.onNext(ActivityEvent.RESUME);
        onResume();
    }

    void pause() {
        mLifecycleSubject.onNext(ActivityEvent.PAUSE);
        onPause();
    }

    void stop() {
        mLifecycleSubject.onNext(ActivityEvent.STOP);
        onStop();
    }

    void destroy() {
        mLifecycleSubject.onNext(ActivityEvent.DESTROY);
        for (OnDestroyListener onDestroyListener : mOnDestroyListeners) {
            onDestroyListener.onDestroy();
        }
        onDestroy();
        mView = null;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private CopyOnWriteArrayList<OnDestroyListener> mOnDestroyListeners = new CopyOnWriteArrayList<>();

    public void addOnDestroyListener(OnDestroyListener listener) {
        mOnDestroyListeners.add(listener);
    }

    public void removeOnDestroyListener(OnDestroyListener listener) {
        mOnDestroyListeners.remove(listener);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @NonNull
    @Override
    public Observable<ActivityEvent> lifecycle() {
        return mLifecycleSubject.hide();
    }

    @NonNull
    @Override
    public <T> LifecycleTransformer<T> bindUntilEvent(@NonNull ActivityEvent event) {
        return RxLifecycle.bindUntilEvent(mLifecycleSubject, event);
    }

    @NonNull
    @Override
    public <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycleAndroid.bindActivity(mLifecycleSubject);
    }
}
