package com.zyhang.damon.example

import android.os.Bundle
import com.zyhang.damon.annotation.RequiresPresenter
import com.zyhang.damon.rxjava.support.RxMvpAppCompatActivity
import kotlinx.android.synthetic.main.activity_single_presenter.*

@RequiresPresenter(ExamplePresenter1::class)
class SinglePresenterActivity : RxMvpAppCompatActivity<ExamplePresenter1>(), ExampleView1 {

    private var messages = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_presenter)

        presenter?.logByView("presenter1 from getPresenter")
    }

    override fun log(msg: String) {
        messages += if (messages.isEmpty()) {
            msg
        } else {
            "\n$msg"
        }

        single_presenter_tv?.post {
            single_presenter_tv.text = messages
        }
    }
}
