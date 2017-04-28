package com.zyhang.damon.kotlin

import java.lang.annotation.Inherited
import kotlin.reflect.KClass

/**
 * ProjectName:Damon
 * Description:
 * Created by zyhang on 2017/4/28.下午5:52
 * Modify by:
 * Modify time:
 * Modify remark:
 */

@Inherited
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class RequiresPresenter(val cls: KClass<out MvpPresenter<MvpView>>)