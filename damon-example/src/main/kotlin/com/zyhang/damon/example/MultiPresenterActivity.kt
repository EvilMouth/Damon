package com.zyhang.damon.example

import android.os.Bundle
import com.zyhang.damon.annotation.BindPresenter
import com.zyhang.damon.annotation.RequiresPresenter
import com.zyhang.damon.rxjava.support.RxMvpAppCompatActivity
import kotlinx.android.synthetic.main.activity_multi_presenter.*

@RequiresPresenter(value = [ExamplePresenter1::class, ExamplePresenter2::class])
class MultiPresenterActivity : RxMvpAppCompatActivity<ExamplePresenter1>(), ExampleView1, ExampleView2 {

    @BindPresenter
    private var mPresenter1: ExamplePresenter1? = null
    @BindPresenter
    private var mPresenter2: ExamplePresenter2? = null

    private var messages = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi_presenter)

        mPresenter1?.logByView("mPresenter11 from @BindPresenter")
        mPresenter2?.logByView("mPresenter22 from @BindPresenter")
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
