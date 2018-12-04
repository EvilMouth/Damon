package com.zyhang.damon.example

import android.os.Bundle
import android.util.Log
import com.zyhang.damon.rxjava.RxMvpPresenter

/**
 * ProjectName:Damon
 * Description:
 * Created by zyhang on 2017/4/28.23:42
 * Modify by:
 * Modify time:
 * Modify remark:
 */

class ExamplePresenter2 : RxMvpPresenter<ExampleView2>() {

    override fun onCreate(savedState: Bundle?) {
        super.onCreate(savedState)
        Log.i("ExamplePresenter2", "onCreate $savedState")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("ExamplePresenter2", "onDestroy")
    }

    override fun onHostCreate(arguments: Bundle?, savedState: Bundle?) {
        super.onHostCreate(arguments, savedState)
        Log.i("ExamplePresenter2", "onHostCreate $savedState")
    }

    override fun onHostDestroy() {
        super.onHostDestroy()
        Log.i("ExamplePresenter2", "onHostDestroy")
    }

    override fun onHostResume() {
        super.onHostResume()
        view!!.log("ExamplePresenter2.onHostResume")
    }

    override fun onHostPause() {
        super.onHostPause()
        view!!.log("ExamplePresenter2.onHostPause")
    }

    fun logByView(msg: String) {
        view?.log(msg)
    }
}
