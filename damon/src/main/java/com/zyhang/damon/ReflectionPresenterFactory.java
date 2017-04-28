package com.zyhang.damon;

import android.support.annotation.Nullable;

/**
 * ProjectName:Damon
 * Description:
 * Created by zyhang on 2017/4/28.下午11:00
 * Modify by:
 * Modify time:
 * Modify remark:
 */

public class ReflectionPresenterFactory<Presenter extends MvpPresenter> implements PresenterFactory<Presenter> {

    private Class<Presenter> mPresenterClass;

    private ReflectionPresenterFactory(Class<Presenter> presenterClass) {
        mPresenterClass = presenterClass;
    }

    @Nullable
    public static <P extends MvpPresenter> ReflectionPresenterFactory<P> fromViewClass(Class<?> cls) {
        RequiresPresenter annotation = cls.getAnnotation(RequiresPresenter.class);
        //noinspection unchecked
        Class<P> pClass = null != annotation ? (Class<P>) annotation.value() : null;
        return null != pClass ? new ReflectionPresenterFactory<>(pClass) : null;
    }

    @Override
    public Presenter createPresenter() {
        try {
            return mPresenterClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
