package com.zyhang.damon;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.zyhang.damon.support.MvpAppCompatActivity;
import com.zyhang.damon.support.MvpSupportFragment;

import androidx.annotation.Nullable;

/**
 * ProjectName:Damon
 * Description:
 * Created by zyhang on 2017/4/28.22:39
 * Modify by:
 * Modify time:
 * Modify remark:
 */

public class MvpPresenter<V> {

    private V view;

    /**
     * may null when call this method before {@link #onCreate(Bundle, Bundle)} or after {@link #onDestroy()}
     *
     * @return view view
     */
    @Nullable
    public V getView() {
        return view;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * called when {@link Activity#onCreate(Bundle)},{@link Fragment#onCreate(Bundle)}
     *
     * @param arguments  {@link Activity#getIntent()},{@link Intent#getExtras()},{@link Fragment#getArguments()}
     * @param savedState If the presenter is being re-instantiated after a process restart then this Bundle
     *                   contains the data it supplied in {@link #onSave}.
     * @see MvpAppCompatActivity#onCreate(Bundle)
     * @see MvpSupportFragment#onCreate(Bundle)
     */
    protected void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedState) {
    }

    /**
     * called when {@link Activity#onCreate(Bundle)},{@link Fragment#onCreateView(LayoutInflater, ViewGroup, Bundle)}
     *
     * @see MvpAppCompatActivity#onCreate(Bundle)
     * @see MvpSupportFragment#onCreateView(LayoutInflater, ViewGroup, Bundle)
     */
    protected void onCreateView() {
    }

    /**
     * called when {@link Activity#onStart()},{@link Fragment#onStart()}
     *
     * @see MvpAppCompatActivity#onStart()
     * @see MvpSupportFragment#onStart()
     */
    protected void onStart() {
    }

    /**
     * called when {@link Activity#onSaveInstanceState(Bundle)},{@link Fragment#onSaveInstanceState(Bundle)}
     *
     * @param state a non-null bundle which should be used to put presenter's state into.
     * @see MvpAppCompatActivity#onSaveInstanceState(Bundle)
     * @see MvpSupportFragment#onSaveInstanceState(Bundle)
     */
    protected void onSave(Bundle state) {
    }

    /**
     * called when {@link Activity#onResume()},{@link Fragment#onResume()}
     *
     * @see MvpAppCompatActivity#onResume()
     * @see MvpSupportFragment#onResume()
     */
    protected void onResume() {
    }

    /**
     * called when {@link Activity#onPause()},{@link Fragment#onPause()}
     *
     * @see MvpAppCompatActivity#onPause()
     * @see MvpSupportFragment#onPause()
     */
    protected void onPause() {
    }

    /**
     * called when {@link Activity#onStop()},{@link Fragment#onStop()}
     *
     * @see MvpAppCompatActivity#onStop()
     * @see MvpSupportFragment#onStop()
     */
    protected void onStop() {
    }

    /**
     * called when {@link Activity#onDestroy()},{@link Fragment#onDestroyView()}
     *
     * @see MvpAppCompatActivity#onDestroy()
     * @see MvpSupportFragment#onDestroyView()
     */
    protected void onDestroyView() {
    }

    /**
     * called when {@link Activity#onDestroy()},{@link Fragment#onDestroy()}
     *
     * @see MvpAppCompatActivity#onDestroy()
     * @see MvpSupportFragment#onDestroy()
     */
    protected void onDestroy() {
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    void create(V view, @Nullable Bundle arguments, @Nullable Bundle savedState) {
        this.view = view;
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
        onDestroy();
        this.view = null;
    }
}
