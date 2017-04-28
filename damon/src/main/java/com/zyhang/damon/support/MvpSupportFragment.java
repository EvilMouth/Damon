package com.zyhang.damon.support;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.zyhang.damon.MvpPresenter;
import com.zyhang.damon.MvpView;
import com.zyhang.damon.PresenterLifecycleDelegate;
import com.zyhang.damon.ReflectionPresenterFactory;
import com.zyhang.damon.ViewWithPresenter;

/**
 * ProjectName:Damon
 * Description:
 * Created by zyhang on 2017/4/28.下午11:19
 * Modify by:
 * Modify time:
 * Modify remark:
 */

public class MvpSupportFragment<Presenter extends MvpPresenter> extends Fragment implements ViewWithPresenter<Presenter>, MvpView {

    private static final String PRESENTER_STATE_KEY = "presenter_state";
    private PresenterLifecycleDelegate<Presenter> mPresenterDelegate =
            new PresenterLifecycleDelegate<>(ReflectionPresenterFactory.<Presenter>fromViewClass(getClass()));

    @Override
    public Presenter getPresenter() {
        return mPresenterDelegate.getPresenter();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenterDelegate.onCreate(this, getArguments(), null != savedInstanceState ? savedInstanceState.getBundle(PRESENTER_STATE_KEY) : null);
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
        mPresenterDelegate.onDestroy(!getActivity().isChangingConfigurations());
        super.onDestroy();
    }
}
