package com.zyhang.damon.rxjava.kotlin

import com.zyhang.damon.rxjava.DisposableHelper
import com.zyhang.damon.rxjava.DisposeOn
import com.zyhang.damon.rxjava.RxMvpPresenter
import com.zyhang.damon.rxjava.support.RxMvpAppCompatActivity
import com.zyhang.damon.rxjava.support.RxMvpSupportFragment
import io.reactivex.disposables.Disposable

/**
 * Created by zyhang on 2018/5/15.17:18
 */

/**
 * @see RxMvpPresenter.add
 * @see RxMvpAppCompatActivity.add
 * @see RxMvpSupportFragment.add
 */
fun DisposableHelper.add(@DisposeOn disposeOn: Int, disposable: () -> Disposable) {
    add(disposable(), disposeOn)
}

/**
 * @see RxMvpPresenter.add
 * @see RxMvpAppCompatActivity.add
 * @see RxMvpSupportFragment.add
 */
fun Disposable.autoDispose(disposableHelper: DisposableHelper, @DisposeOn disposeOn: Int) {
    disposableHelper.add(this, disposeOn)
}