package com.zyhang.damon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.zyhang.damon.support.MvpAppCompatActivity;
import com.zyhang.damon.support.MvpSupportFragment;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * ProjectName:Damon
 * Description:
 * Created by zyhang on 2017/4/28.22:36
 * Modify by:
 * Modify time:
 * Modify remark:
 */

interface MvpPresenterHelper {
    /**
     * called when {@link Activity#onCreate(Bundle)},{@link Fragment#onCreate(Bundle)}
     *
     * @param arguments  {@link Activity#getIntent()},{@link Intent#getExtras()},{@link Fragment#getArguments()}
     * @param savedState If the presenter is being re-instantiated after a process restart then this Bundle
     *                   contains the data it supplied in {@link #onSave}.
     * @see MvpAppCompatActivity#onCreate(Bundle)
     * @see MvpSupportFragment#onCreate(Bundle)
     */
    void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedState);

    /**
     * called when {@link Activity#onCreate(Bundle)},{@link Fragment#onCreateView(LayoutInflater, ViewGroup, Bundle)}
     *
     * @see MvpAppCompatActivity#onCreate(Bundle)
     * @see MvpSupportFragment#onCreateView(LayoutInflater, ViewGroup, Bundle)
     */
    void onCreateView();

    /**
     * called when {@link Activity#onStart()},{@link Fragment#onStart()}
     *
     * @see MvpAppCompatActivity#onStart()
     * @see MvpSupportFragment#onStart()
     */
    void onStart();

    /**
     * called when {@link Activity#onSaveInstanceState(Bundle)},{@link Fragment#onSaveInstanceState(Bundle)}
     *
     * @param state a non-null bundle which should be used to put presenter's state into.
     * @see MvpAppCompatActivity#onSaveInstanceState(Bundle)
     * @see MvpSupportFragment#onSaveInstanceState(Bundle)
     */
    void onSave(Bundle state);

    /**
     * called when {@link Activity#onResume()},{@link Fragment#onResume()}
     *
     * @see MvpAppCompatActivity#onResume()
     * @see MvpSupportFragment#onResume()
     */
    void onResume();

    /**
     * called when {@link Activity#onPause()},{@link Fragment#onPause()}
     *
     * @see MvpAppCompatActivity#onPause()
     * @see MvpSupportFragment#onPause()
     */
    void onPause();

    /**
     * called when {@link Activity#onStop()},{@link Fragment#onStop()}
     *
     * @see MvpAppCompatActivity#onStop()
     * @see MvpSupportFragment#onStop()
     */
    void onStop();

    /**
     * called when {@link Activity#onDestroy()},{@link Fragment#onDestroyView()}
     *
     * @see MvpAppCompatActivity#onDestroy()
     * @see MvpSupportFragment#onDestroyView()
     */
    void onDestroyView();

    /**
     * called when {@link Activity#onDestroy()},{@link Fragment#onDestroy()}
     *
     * @see MvpAppCompatActivity#onDestroy()
     * @see MvpSupportFragment#onDestroy()
     */
    void onDestroy();
}
