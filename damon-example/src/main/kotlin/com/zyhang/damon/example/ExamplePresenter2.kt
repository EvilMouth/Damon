package com.zyhang.damon.example

import android.os.Bundle
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

    override fun onCreate(arguments: Bundle?, savedState: Bundle?) {
        super.onCreate(arguments, savedState)
        view?.log("ExamplePresenter2.onCreate")
    }

    override fun onResume() {
        super.onResume()
        view?.log("ExamplePresenter2.onResume")
    }

    override fun onPause() {
        super.onPause()
        view?.log("ExamplePresenter2.onPause")
    }

    fun logByView(msg: String) {
        view?.log(msg)
    }
}
