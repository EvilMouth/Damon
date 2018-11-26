package com.zyhang.damon;

import android.os.Bundle;

import java.util.concurrent.CopyOnWriteArrayList;

import androidx.annotation.Nullable;

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
     * may null when call this method before {@link #onCreate(Bundle, Bundle)} or after {@link #onDestroy()}
     *
     * @return mView
     */
    @Nullable
    public View getView() {
        return mView;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedState) {
    }

    @Override
    public void onCreateView() {
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onSave(Bundle state) {
    }

    @Override
    public void onResume() {
    }

    @Override
    public void onPause() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void onDestroyView() {
    }

    @Override
    public void onDestroy() {
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    void create(View view, @Nullable Bundle arguments, @Nullable Bundle savedState) {
        mView = view;
        onCreate(arguments, savedState);
    }

    void createView() {
        onCreateView();
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

    void destroyView() {
        onDestroyView();
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
