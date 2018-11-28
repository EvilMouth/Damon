package com.zyhang.damon.example

import android.os.Bundle
import com.zyhang.damon.BindPresenter
import com.zyhang.damon.RequiresPresenter
import com.zyhang.damon.rxjava.support.MvpAppCompatActivityRx
import kotlinx.android.synthetic.main.activity_single_presenter.*

@RequiresPresenter(ExamplePresenter1::class)
class SinglePresenterActivity : MvpAppCompatActivityRx(), ExampleView1 {

    @BindPresenter
    private var presenter1: ExamplePresenter1? = null

    private var messages = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_presenter)

        presenter1?.logByView("presenter1 from @BindPresenter")
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
