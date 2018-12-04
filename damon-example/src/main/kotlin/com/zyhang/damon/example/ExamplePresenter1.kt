package com.zyhang.damon.example

import android.os.Bundle
import android.util.Log
import com.zyhang.damon.rxjava.DisposeOn
import com.zyhang.damon.rxjava.RxMvpPresenter
import com.zyhang.damon.rxjava.kotlin.autoDispose
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

/**
 * ProjectName:Damon
 * Description:
 * Created by zyhang on 2017/4/28.23:42
 * Modify by:
 * Modify time:
 * Modify remark:
 */

class ExamplePresenter1 : RxMvpPresenter<ExampleView1>() {

    override fun onCreate(savedState: Bundle?) {
        super.onCreate(savedState)
        Log.i("ExamplePresenter1", "onCreate $savedState")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("ExamplePresenter1", "onDestroy")
    }

    override fun onHostCreate(arguments: Bundle?, savedState: Bundle?) {
        super.onHostCreate(arguments, savedState)
        Log.i("ExamplePresenter1", "onHostCreate $savedState")
    }

    override fun onHostDestroy() {
        super.onHostDestroy()
        Log.i("ExamplePresenter1", "onHostDestroy")
    }

    override fun onHostResume() {
        super.onHostResume()
        view?.log("ExamplePresenter1.onHostResume")

        Observable.interval(1, TimeUnit.SECONDS)
                .take(10)
                .subscribe { aLong ->
                    view!!.log("ExamplePresenter1.aLong === $aLong")
                }
                .autoDispose(this, DisposeOn.PAUSE)
    }

    override fun onHostPause() {
        super.onHostPause()
        view!!.log("ExamplePresenter1.onHostPause")
    }

    fun logByView(msg: String) {
        view?.log(msg)
    }
}
