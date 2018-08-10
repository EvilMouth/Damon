package com.zyhang.damon;

import android.support.annotation.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * ProjectName:Damon
 * Description:
 * Created by zyhang on 2017/4/28.23:00
 * Modify by:
 * Modify time:
 * Modify remark:
 */

public class ReflectionPresenterFactory implements PresenterFactory {

    private Object mHost;
    private Class<? extends MvpPresenter>[] mPresenterClass;
    private List<Field> mPresenterFields;

    private ReflectionPresenterFactory(Object host, Class<? extends MvpPresenter>[] presenterClass, List<Field> presenterFields) {
        mHost = host;
        mPresenterClass = presenterClass;
        mPresenterFields = presenterFields;
    }

    @Nullable
    public static ReflectionPresenterFactory fromViewClass(Object host, Class<?> cls) {
        RequiresPresenter annotation = cls.getAnnotation(RequiresPresenter.class);
        Class<? extends MvpPresenter>[] pClass = null != annotation ? annotation.value() : null;
        if (null == pClass) {
            return null;
        }
        List<Field> fields = new ArrayList<>();
        for (Field field : cls.getDeclaredFields()) {
            Annotation[] annotations = field.getDeclaredAnnotations();
            if (annotations.length < 1) {
                continue;
            }
            if (annotations[0] instanceof BindPresenter) {
                fields.add(field);
            }
        }
        return new ReflectionPresenterFactory(host, pClass, fields);
    }

    @Override
    public List<? extends MvpPresenter> createPresenter() {
        try {
            List<MvpPresenter> presenters = new ArrayList<>();
            for (Class<? extends MvpPresenter> presenterClass : mPresenterClass) {
                presenters.add(presenterClass.newInstance());
            }
            return presenters;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void bindPresenter(List<? extends MvpPresenter> presenters) {
        for (Field presenterField : mPresenterFields) {
            String fieldClassName = presenterField.getType().getName();
            for (MvpPresenter presenter : presenters) {
                if (fieldClassName.equals(presenter.getClass().getName())) {
                    try {
                        presenterField.setAccessible(true);
                        presenterField.set(mHost, presenter);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
            }
        }
    }
}
