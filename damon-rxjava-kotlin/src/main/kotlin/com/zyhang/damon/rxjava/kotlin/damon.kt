package com.zyhang.damon.rxjava.kotlin

import com.zyhang.damon.rxjava.DisposableHelper
import com.zyhang.damon.rxjava.MvpPresenterRx
import com.zyhang.damon.rxjava.support.MvpAppCompatActivityRx
import com.zyhang.damon.rxjava.support.MvpSupportFragmentRx
import io.reactivex.disposables.Disposable

/**
 * Created by zyhang on 2018/5/15.17:18
 */

/**
 * @see MvpPresenterRx.add
 * @see MvpAppCompatActivityRx.add
 * @see MvpSupportFragmentRx.add
 */
fun DisposableHelper.add(@DisposableHelper.DisposeOn disposeOn: Int, disposable: () -> Disposable) {
    add(disposable(), disposeOn)
}

/**
 * @see MvpPresenterRx.add
 * @see MvpAppCompatActivityRx.add
 * @see MvpSupportFragmentRx.add
 */
fun Disposable.autoDispose(disposableHelper: DisposableHelper, @DisposableHelper.DisposeOn disposeOn: Int) {
    disposableHelper.add(this, disposeOn)
}