package com.zyhang.damon.example

import android.os.Bundle
import android.util.Log
import com.zyhang.damon.RequiresPresenter
import com.zyhang.damon.rxjava.support.MvpAppCompatActivityRx

@RequiresPresenter(KotlinPresenter::class)
class KotlinActivity : MvpAppCompatActivityRx<KotlinPresenter>(), KotlinView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun log(tips: String) {
        Log.i("main", tips)
    }
}
