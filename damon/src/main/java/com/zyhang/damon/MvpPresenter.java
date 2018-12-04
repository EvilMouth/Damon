package com.zyhang.damon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.zyhang.damon.support.MvpAppCompatActivity;
import com.zyhang.damon.support.MvpSupportFragment;

import java.util.concurrent.CopyOnWriteArrayList;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * ProjectName:Damon
 * Description:
 * Created by zyhang on 2017/4/28.22:39
 * Modify by:
 * Modify time:
 * Modify remark:
 */

@SuppressWarnings("unused")
public class MvpPresenter<V> {

    @Nullable
    private V view;
    private CopyOnWriteArrayList<OnDestroyListener> onDestroyListeners = new CopyOnWriteArrayList<>();

    /**
     * may null when call this method before {@link #onHostCreate(Bundle, Bundle)} or after {@link #onHostDestroy()}
     *
     * @return view view
     */
    @Nullable
    public V getView() {
        return view;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    void create(@Nullable Bundle savedState) {
        onCreate(savedState);
    }

    void save(Bundle state) {
        onSave(state);
    }

    void destroy() {
        for (OnDestroyListener listener : onDestroyListeners) {
            listener.onDestroy();
        }
        onDestroy();
    }

    /**
     * called when presenter created
     *
     * @param savedState If the presenter is being re-instantiated after a process restart then this Bundle
     *                   contains the data it supplied in {@link #onSave}.
     */
    protected void onCreate(@Nullable Bundle savedState) {
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
     * called when presenter destroyed
     */
    protected void onDestroy() {
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    void hostCreate(V view, @Nullable Bundle arguments, @Nullable Bundle savedState) {
        this.view = view;
        onHostCreate(arguments, savedState);
    }

    void hostCreateView() {
        onHostCreateView();
    }

    void hostStart() {
        onHostStart();
    }

    void hostResume() {
        onHostResume();
    }

    void hostPause() {
        onHostPause();
    }

    void hostStop() {
        onHostStop();
    }

    void hostDestroyView() {
        onHostDestroyView();
    }

    void hostDestroy() {
        onHostDestroy();
        this.view = null;
    }

    /**
     * called when {@link Activity#onCreate(Bundle)},{@link Fragment#onCreate(Bundle)}
     *
     * @param arguments  {@link Activity#getIntent()},{@link Intent#getExtras()},{@link Fragment#getArguments()}
     * @param savedState {@link Activity#onSaveInstanceState(Bundle)},{@link Fragment#onSaveInstanceState(Bundle)}
     * @see MvpAppCompatActivity#onCreate(Bundle)
     * @see MvpSupportFragment#onCreate(Bundle)
     */
    protected void onHostCreate(@Nullable Bundle arguments, @Nullable Bundle savedState) {
    }

    /**
     * called when {@link Activity#onCreate(Bundle)},{@link Fragment#onCreateView(LayoutInflater, ViewGroup, Bundle)}
     *
     * @see MvpAppCompatActivity#onCreate(Bundle)
     * @see MvpSupportFragment#onCreateView(LayoutInflater, ViewGroup, Bundle)
     */
    protected void onHostCreateView() {
    }

    /**
     * called when {@link Activity#onStart()},{@link Fragment#onStart()}
     *
     * @see MvpAppCompatActivity#onStart()
     * @see MvpSupportFragment#onStart()
     */
    protected void onHostStart() {
    }

    /**
     * called when {@link Activity#onResume()},{@link Fragment#onResume()}
     *
     * @see MvpAppCompatActivity#onResume()
     * @see MvpSupportFragment#onResume()
     */
    protected void onHostResume() {
    }

    /**
     * called when {@link Activity#onPause()},{@link Fragment#onPause()}
     *
     * @see MvpAppCompatActivity#onPause()
     * @see MvpSupportFragment#onPause()
     */
    protected void onHostPause() {
    }

    /**
     * called when {@link Activity#onStop()},{@link Fragment#onStop()}
     *
     * @see MvpAppCompatActivity#onStop()
     * @see MvpSupportFragment#onStop()
     */
    protected void onHostStop() {
    }

    /**
     * called when {@link Activity#onDestroy()},{@link Fragment#onDestroyView()}
     *
     * @see MvpAppCompatActivity#onDestroy()
     * @see MvpSupportFragment#onDestroyView()
     */
    protected void onHostDestroyView() {
    }

    /**
     * called when {@link Activity#onDestroy()},{@link Fragment#onDestroy()}
     *
     * @see MvpAppCompatActivity#onDestroy()
     * @see MvpSupportFragment#onDestroy()
     */
    protected void onHostDestroy() {
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * A callback to be invoked when a presenter is about to be destroyed.
     */
    public interface OnDestroyListener {
        /**
         * Called before {@link MvpPresenter#onDestroy()}.
         */
        void onDestroy();
    }

    /**
     * Adds a listener observing {@link #onDestroy}.
     *
     * @param listener a listener to add.
     */
    public void addOnDestroyListener(OnDestroyListener listener) {
        onDestroyListeners.add(listener);
    }

    /**
     * Removed a listener observing {@link #onDestroy}.
     *
     * @param listener a listener to remove.
     */
    public void removeOnDestroyListener(OnDestroyListener listener) {
        onDestroyListeners.remove(listener);
    }
}
