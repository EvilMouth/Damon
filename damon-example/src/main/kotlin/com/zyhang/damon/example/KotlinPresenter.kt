package com.zyhang.damon.example

import android.os.Bundle
import com.zyhang.damon.rxjava.MvpPresenterRx
import com.zyhang.damon.rxjava.kotlin.add
import com.zyhang.damon.rxjava.kotlin.autoDispose
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

/**
 * ProjectName:Damon
 * Description:
 * Created by zyhang on 2017/4/28.下午11:42
 * Modify by:
 * Modify time:
 * Modify remark:
 */

class KotlinPresenter : MvpPresenterRx<KotlinView>() {

    override fun onCreate(arguments: Bundle?, savedState: Bundle?) {
        super.onCreate(arguments, savedState)
        view.log("onCreate")
    }

    override fun onResume() {
        super.onResume()

        add {
            Observable.interval(1, TimeUnit.SECONDS)
                    .subscribe { aLong ->
                        view.log("aLong === $aLong")
                    }
        }

        // or

        Observable.interval(1, TimeUnit.SECONDS)
                .subscribe { aLong ->
                    view.log("aLong === $aLong")
                }
                .autoDispose(this)
    }
}
