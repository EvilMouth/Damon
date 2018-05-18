package com.zyhang.damon.rxjava.kotlin

import com.zyhang.damon.MvpView
import com.zyhang.damon.rxjava.DisposableHelper
import com.zyhang.damon.rxjava.MvpPresenterRx
import com.zyhang.damon.rxjava.support.MvpAppCompatActivityRx
import com.zyhang.damon.rxjava.support.MvpSupportFragmentRx
import io.reactivex.disposables.Disposable

/**
 * Created by zyhang on 2018/5/15.17:18
 */

fun MvpPresenterRx<out MvpView>.add(disposable: () -> Disposable) {
    add(disposable())
}

fun MvpAppCompatActivityRx<out MvpPresenterRx<out MvpView>>.add(disposable: () -> Disposable) {
    add(disposable())
}

fun MvpSupportFragmentRx<out MvpPresenterRx<out MvpView>>.add(disposable: () -> Disposable) {
    add(disposable())
}

/**
 * autoDispose when page destroy
 *
 * @see MvpPresenterRx.onDestroy
 * @see MvpAppCompatActivityRx.onDestroy
 * @see MvpSupportFragmentRx.onDestroy
 */
fun Disposable.autoDispose(disposableHelper: DisposableHelper) {
    disposableHelper.add(this)
}