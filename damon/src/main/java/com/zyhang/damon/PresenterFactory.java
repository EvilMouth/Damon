package com.zyhang.damon;

import java.util.List;

/**
 * ProjectName:Damon
 * Description:
 * Created by zyhang on 2017/4/28.22:57
 * Modify by:
 * Modify time:
 * Modify remark:
 */

interface PresenterFactory {
    List<? extends MvpPresenter> createPresenter();

    void bindPresenter(List<? extends MvpPresenter> presenters);
}
