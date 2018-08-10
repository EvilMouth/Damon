package com.zyhang.damon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * ProjectName:Damon
 * Description:
 * Created by zyhang on 2017/4/28.22:39
 * Modify by:
 * Modify time:
 * Modify remark:
 */

public class MvpPresenter<View extends MvpView> implements MvpPresenterHelper {

    private View mView;

    /**
     * may null when call this method before {@link #onCreate(Bundle, Bundle)}
     *
     * @return mView
     */
    public View getView() {
        return mView;
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
    @Override
    public void onDestroy() {
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    void create(View view, @Nullable Bundle arguments, @Nullable Bundle savedState) {
        mView = view;
        onCreate(arguments, savedState);
    }

    void start() {
        onStart();
    }

    void save(Bundle state) {
        onSave(state);
    }

    void resume() {
        onResume();
    }

    void pause() {
        onPause();
    }

    void stop() {
        onStop();
    }

    void destroy() {
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
}
