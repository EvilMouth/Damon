package com.zyhang.damon;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ProjectName:Damon
 * Description:
 * Created by zyhang on 2017/4/28.下午10:58
 * Modify by:
 * Modify time:
 * Modify remark:
 */

@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresPresenter {
    Class<? extends MvpPresenter> value();
}
