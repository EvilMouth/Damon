package com.zyhang.damon.support;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zyhang.damon.MvpView;
import com.zyhang.damon.PresenterLifecycleDelegate;
import com.zyhang.damon.ReflectionPresenterFactory;

/**
 * ProjectName:Damon
 * Description:
 * Created by zyhang on 2017/4/28.23:24
 * Modify by:
 * Modify time:
 * Modify remark:
 */

public class MvpAppCompatActivity extends AppCompatActivity implements MvpView {

    private static final String PRESENTER_STATE_KEY = "presenter_state";
    private PresenterLifecycleDelegate mPresenterDelegate =
            new PresenterLifecycleDelegate(ReflectionPresenterFactory.fromViewClass(this, getClass()));

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenterDelegate.onCreate(this, getIntent().getExtras(), null != savedInstanceState ? savedInstanceState.getBundle(PRESENTER_STATE_KEY) : null);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(PRESENTER_STATE_KEY, mPresenterDelegate.onSaveInstanceState());
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenterDelegate.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenterDelegate.onResume();
    }

    @Override
    public void onPause() {
        mPresenterDelegate.onPause();
        super.onPause();
    }

    @Override
    public void onStop() {
        mPresenterDelegate.onStop();
        super.onStop();
    }

    @Override
    public void onDestroy() {
        mPresenterDelegate.onDestroy(!isChangingConfigurations());
        super.onDestroy();
    }
}
