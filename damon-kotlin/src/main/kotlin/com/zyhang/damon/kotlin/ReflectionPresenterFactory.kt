package com.zyhang.damon.kotlin

import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.findAnnotation

/**
 * ProjectName:Damon
 * Description:
 * Created by zyhang on 2017/4/28.下午5:53
 * Modify by:
 * Modify time:
 * Modify remark:
 */

class ReflectionPresenterFactory<out P : MvpPresenter<MvpView>> private constructor(val presenterClass: KClass<out P>?) : PresenterFactory<P> {
    companion object {
        fun <P : MvpPresenter<MvpView>> fromViewClass(cls: KClass<*>): ReflectionPresenterFactory<P>? {
            val annotation = cls.findAnnotation<RequiresPresenter>()
            @Suppress("UNCHECKED_CAST")
            val pClass: KClass<P>? = annotation?.cls as KClass<P>?
            return if (null == pClass) null else ReflectionPresenterFactory(pClass)
        }
    }

    override fun createPresenter(): P? = try {
        presenterClass?.createInstance()
    } catch (e: Exception) {
        throw RuntimeException(e)
    }
}