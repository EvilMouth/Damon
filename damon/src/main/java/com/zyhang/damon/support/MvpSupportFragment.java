package com.zyhang.damon.support;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zyhang.damon.MvpPresenter;
import com.zyhang.damon.factory.PresenterGetter;
import com.zyhang.damon.PresenterLifecycleDelegate;
import com.zyhang.damon.factory.ReflectionPresenterFactory;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * ProjectName:Damon
 * Description:
 * Created by zyhang on 2017/4/28.23:19
 * Modify by:
 * Modify time:
 * Modify remark:
 */

public class MvpSupportFragment<P extends MvpPresenter> extends Fragment implements PresenterGetter<P> {

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
        mPresenterDelegate.dispatchCreate(this, getArguments(), null != savedInstanceState ? savedInstanceState.getBundle(PRESENTER_STATE_KEY) : null);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPresenterDelegate.dispatchCreateView();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(PRESENTER_STATE_KEY, mPresenterDelegate.dispatchSaveInstanceState());
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
        mPresenterDelegate.dispatchPause();
        super.onPause();
    }

    @Override
    public void onStop() {
        mPresenterDelegate.dispatchStop();
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        mPresenterDelegate.dispatchDestroyView();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        mPresenterDelegate.dispatchDestroy(!requireActivity().isChangingConfigurations());
        super.onDestroy();
    }
}
