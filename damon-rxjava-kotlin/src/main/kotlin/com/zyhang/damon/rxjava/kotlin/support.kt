package com.zyhang.damon.rxjava.kotlin

import com.zyhang.damon.rxjava.RxMvpPresenter
import com.zyhang.damon.rxjava.support.RxMvpAppCompatActivity
import com.zyhang.damon.rxjava.support.RxMvpSupportFragment

/**
 * Created by zyhang on 2018/11/30.17:20
 */

class EmptyMvpPresenter : RxMvpPresenter<Any>()

open class EmptyPresenterRxMvpAppCompatActivity : RxMvpAppCompatActivity<EmptyMvpPresenter>()

open class EmptyPresenterRxMvpSupportFragment : RxMvpSupportFragment<EmptyMvpPresenter>()