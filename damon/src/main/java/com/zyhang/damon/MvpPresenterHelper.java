package com.zyhang.damon;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * ProjectName:Damon
 * Description:
 * Created by zyhang on 2017/4/28.下午10:36
 * Modify by:
 * Modify time:
 * Modify remark:
 */

interface MvpPresenterHelper {
    /**
     * called when {@link Activity#onCreate(Bundle)},{@link Fragment#onCreate(Bundle)}
     *
     * @param arguments  {@link Activity#getIntent().getExtras()},{@link Fragment#getArguments()}
     * @param savedState If the presenter is being re-instantiated after a process restart then this Bundle
     *                   contains the data it supplied in {@link #onSave}.
     */
    void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedState);

    /**
     * called when {@link Activity#onStart()},{@link Fragment#onStart()}
     */
    void onStart();

    /**
     * called when {@link Activity#onSaveInstanceState(Bundle)},{@link Fragment#onSaveInstanceState(Bundle)}
     *
     * @param state a non-null bundle which should be used to put presenter's state into.
     */
    void onSave(Bundle state);

    /**
     * called when {@link Activity#onResume()},{@link Fragment#onResume()}
     *
     */
    void onResume();

    /**
     * called when {@link Activity#onPause()},{@link Fragment#onPause()}
     */
    void onPause();

    /**
     * called when {@link Activity#onStop()},{@link Fragment#onStop()}
     */
    void onStop();

    /**
     * called when {@link Activity#onDestroy()},{@link Fragment#onDestroy()}
     */
    void onDestroy();
}
