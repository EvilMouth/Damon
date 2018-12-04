package com.zyhang.damon.support;

import android.os.Bundle;

import com.zyhang.damon.MvpPresenter;
import com.zyhang.damon.factory.PresenterGetter;
import com.zyhang.damon.PresenterLifecycleDelegate;
import com.zyhang.damon.factory.ReflectionPresenterFactory;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * ProjectName:Damon
 * Description:
 * Created by zyhang on 2017/4/28.23:24
 * Modify by:
 * Modify time:
 * Modify remark:
 */

public class MvpAppCompatActivity<P extends MvpPresenter> extends AppCompatActivity implements PresenterGetter<P> {

    private static final String PRESENTER_STATE_KEY = "presenter_state";
    private PresenterLifecycleDelegate<P> mPresenterDelegate =
            new PresenterLifecycleDelegate<>(ReflectionPresenterFactory.fromViewClass(this, getClass()));

    @Nullable
    @Override
    public List<? extends MvpPresenter> getPresenters() {
        return mPresenterDelegate.getPresenters();
    }

    @Nullable
    @Override
    public P getPresenter() {
        return mPresenterDelegate.getPresenter();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenterDelegate.dispatchCreate(this, getIntent().getExtras(), savedInstanceState, null != savedInstanceState ? savedInstanceState.getBundle(PRESENTER_STATE_KEY) : null);
        mPresenterDelegate.dispatchCreateView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(PRESENTER_STATE_KEY, mPresenterDelegate.getPresenterSaveState());
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenterDelegate.dispatchStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenterDelegate.dispatchResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenterDelegate.dispatchPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenterDelegate.dispatchStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenterDelegate.dispatchDestroyView();
        mPresenterDelegate.dispatchDestroy(!isChangingConfigurations());
    }
}
