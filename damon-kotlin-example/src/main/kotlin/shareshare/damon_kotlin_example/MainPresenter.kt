package shareshare.damon_kotlin_example

import android.os.Bundle
import android.util.Log
import com.zyhang.damon.kotlin.MvpPresenter
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

/**
 * ProjectName:Damon
 * Description:
 * Created by zyhang on 2017/4/30.上午11:03
 * Modify by:
 * Modify time:
 * Modify remark:
 */

class MainPresenter : MvpPresenter<MainView>() {

    override fun onCreate(arguments: Bundle?, savedState: Bundle?) {
        super.onCreate(arguments, savedState)

        view()?.log("onCreate")
    }

    override fun onResume() {
        super.onResume()

        Observable.interval(1, TimeUnit.SECONDS)
                .compose(bindToLifecycle())
                .subscribe {
                    Log.i("accept", "aLong === $it")
                }
    }
}