package com.zyhang.damon.kotlin.support

import android.os.Bundle
import android.support.v4.app.Fragment
import com.zyhang.damon.kotlin.*

/**
 * ProjectName:Damon
 * Description:
 * Created by zyhang on 2017/4/28.下午5:55
 * Modify by:
 * Modify time:
 * Modify remark:
 */

open class MvpSupportFragment<out P : MvpPresenter<MvpView>> : Fragment(), ViewWithPresenter<P>, MvpView {

    private val PRESENTER_STATE_KEY = "presenter_state"
    val mPresenterDelegate: PresenterLifecycleDelegate<P> =
            PresenterLifecycleDelegate(ReflectionPresenterFactory.Companion.fromViewClass(javaClass.kotlin))

    override fun getPresenter(): P? {
        return mPresenterDelegate.presenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenterDelegate.OnCreate(this, arguments, savedInstanceState?.getBundle(PRESENTER_STATE_KEY))
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putBundle(PRESENTER_STATE_KEY, mPresenterDelegate.onSaveInstanceState())
    }

    override fun onStart() {
        super.onStart()
        mPresenterDelegate.onStart()
    }

    override fun onResume() {
        super.onResume()
        mPresenterDelegate.onResume()
    }

    override fun onPause() {
        mPresenterDelegate.onPause()
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
        mPresenterDelegate.onStop()
    }

    override fun onDestroy() {
        mPresenterDelegate.onDestroy(!activity.isChangingConfigurations)
        super.onDestroy()
    }
}