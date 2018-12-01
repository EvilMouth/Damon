package com.zyhang.damon.rxjava.kotlin

import com.zyhang.damon.MvpView
import com.zyhang.damon.rxjava.RxMvpPresenter
import com.zyhang.damon.rxjava.support.RxMvpAppCompatActivity
import com.zyhang.damon.rxjava.support.RxMvpSupportFragment

/**
 * Created by zyhang on 2018/11/30.17:20
 */

interface EmptyMvpView : MvpView

class EmptyMvpPresenter : RxMvpPresenter<EmptyMvpView>()

open class EmptyPresenterRxMvpAppCompatActivity : RxMvpAppCompatActivity<EmptyMvpPresenter>(), EmptyMvpView

open class EmptyPresenterRxMvpSupportFragment : RxMvpSupportFragment<EmptyMvpPresenter>(), EmptyMvpView