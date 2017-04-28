package com.zyhang.damon.kotlin

import android.os.Bundle

/**
 * ProjectName:Damon
 * Description:
 * Created by zyhang on 2017/4/28.下午5:54
 * Modify by:
 * Modify time:
 * Modify remark:
 */

class PresenterLifecycleDelegate<out P : MvpPresenter<MvpView>>(val mPresenterFactory: PresenterFactory<P>?) {
    companion object {
        private val PRESENTER_KEY = "presenter"
        private val PRESENTER_ID_KEY = "presenter_id"
    }

    private var mPresenter: P? = null
    private var mPresenterHasView: Boolean = false

    fun presenter(): P? = mPresenter

    fun OnCreate(view: MvpView, arguments: Bundle?, savedState: Bundle?) {
        val presenterBundle: Bundle? = savedState?.let {
            ParcelFn.unmarshall(ParcelFn.marshall(it))
        }
        createPresenter(presenterBundle)
        mPresenter?.create(view, arguments, presenterBundle?.getBundle(PRESENTER_KEY))
    }

    fun createPresenter(presenterBundle: Bundle?) {
        mPresenterFactory?.let {
            presenterBundle?.let {
                mPresenter = PresenterStorage.INSTANCE.getPresenter(it.getString(PRESENTER_ID_KEY))
            }
            if (mPresenter == null) {
                mPresenter = it.createPresenter()
                PresenterStorage.INSTANCE.add(mPresenter as MvpPresenter<MvpView>)
            }
        }
    }

    fun onStart() {
        mPresenter?.start()
    }

    fun onSaveInstanceState(): Bundle {
        val bundle = Bundle()
        mPresenter?.let {
            val presenterBundle = Bundle()
            it.save(presenterBundle)
            bundle.putBundle(PRESENTER_KEY, presenterBundle)
            bundle.putString(PRESENTER_ID_KEY, PresenterStorage.INSTANCE.getId(it))
        }
        return bundle
    }

    fun onResume() {
        if (!mPresenterHasView) mPresenter?.let {
            it.resume()
            mPresenterHasView = true
        }
    }

    fun onPause() {
        if (mPresenterHasView) mPresenter?.let {
            it.pause()
            mPresenterHasView = false
        }
    }

    fun onStop() {
        mPresenter?.stop()
    }

    fun onDestroy(isFinal: Boolean) {
        if (isFinal) mPresenter?.let {
            it.destroy()
            mPresenter = null
        }
    }
}