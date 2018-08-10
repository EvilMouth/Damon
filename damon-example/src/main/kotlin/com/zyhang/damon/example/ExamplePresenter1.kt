package com.zyhang.damon.example

import android.os.Bundle
import com.zyhang.damon.rxjava.MvpPresenterRx
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

class ExamplePresenter1 : MvpPresenterRx<ExampleView1>() {

    override fun onCreate(arguments: Bundle?, savedState: Bundle?) {
        super.onCreate(arguments, savedState)
        view.log("ExamplePresenter1.onCreate")
    }

    override fun onResume() {
        super.onResume()
        view.log("ExamplePresenter1.onResume")

        Observable.interval(1, TimeUnit.SECONDS)
                .take(10)
                .subscribe { aLong ->
                    view.log("ExamplePresenter1.aLong === $aLong")
                }
                .autoDispose(this)
    }

    fun logByView(msg: String) {
        view.log(msg)
    }
}
