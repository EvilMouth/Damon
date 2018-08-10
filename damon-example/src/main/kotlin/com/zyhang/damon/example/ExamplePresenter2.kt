package com.zyhang.damon.example

import android.os.Bundle
import com.zyhang.damon.rxjava.MvpPresenterRx

/**
 * ProjectName:Damon
 * Description:
 * Created by zyhang on 2017/4/28.23:42
 * Modify by:
 * Modify time:
 * Modify remark:
 */

class ExamplePresenter2 : MvpPresenterRx<ExampleView2>() {

    override fun onCreate(arguments: Bundle?, savedState: Bundle?) {
        super.onCreate(arguments, savedState)
        view.log("ExamplePresenter2.onCreate")
    }

    override fun onResume() {
        super.onResume()
        view.log("ExamplePresenter2.onResume")
    }

    fun logByView(msg: String) {
        view.log(msg)
    }
}