package com.zyhang.damon.factory;

import com.zyhang.damon.MvpPresenter;

import java.util.List;

/**
 * ProjectName:Damon
 * Description:
 * Created by zyhang on 2017/4/28.22:57
 * Modify by:
 * Modify time:
 * Modify remark:
 */

public interface PresenterFactory {
    List<? extends MvpPresenter> createPresenter();

    void bindPresenter(List<? extends MvpPresenter> presenters);
}
