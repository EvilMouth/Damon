package com.zyhang.damon;

import java.util.List;

/**
 * ProjectName:Damon
 * Description:
 * Created by zyhang on 2017/4/28.下午10:57
 * Modify by:
 * Modify time:
 * Modify remark:
 */

interface PresenterFactory<Presenter extends MvpPresenter> {
    List<Presenter> createPresenter();
}
