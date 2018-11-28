package com.zyhang.damon.example

import android.os.Bundle
import com.zyhang.damon.BindPresenter
import com.zyhang.damon.RequiresPresenter
import com.zyhang.damon.rxjava.support.MvpAppCompatActivityRx
import kotlinx.android.synthetic.main.activity_multi_presenter.*

@RequiresPresenter(value = [ExamplePresenter1::class, ExamplePresenter2::class])
class MultiPresenterActivity : MvpAppCompatActivityRx(), ExampleView1, ExampleView2 {

    @BindPresenter
    private var presenter1: ExamplePresenter1? = null
    @BindPresenter
    private var presenter2: ExamplePresenter2? = null

    private var messages = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi_presenter)

        presenter1?.logByView("presenter1 from @BindPresenter")
        presenter2?.logByView("presenter2 from @BindPresenter")
    }

    override fun log(msg: String) {
        messages += if (messages.isEmpty()) {
            msg
        } else {
            "\n$msg"
        }

        multi_presenter_tv?.post {
            multi_presenter_tv.text = messages
        }
    }
}
