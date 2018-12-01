package com.zyhang.damon.factory;

import com.zyhang.damon.MvpPresenter;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * Created by zyhang on 2018/11/30.16:24
 */
public interface PresenterGetter<P extends MvpPresenter> {
    @Nullable
    List<? extends MvpPresenter> getPresenters();

    @Nullable
    P getPresenter();
}
