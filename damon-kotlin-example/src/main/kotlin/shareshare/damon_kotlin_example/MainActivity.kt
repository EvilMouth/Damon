package shareshare.damon_kotlin_example

import android.os.Bundle
import android.util.Log
import com.zyhang.damon.kotlin.RequiresPresenter
import com.zyhang.damon.kotlin.support.MvpAppCompatActivity

/**
 * ProjectName:Damon
 * Description:
 * Created by zyhang on 2017/4/30.上午11:02
 * Modify by:
 * Modify time:
 * Modify remark:
 */

@RequiresPresenter(MainPresenter::class)
class MainActivity : MvpAppCompatActivity<MainPresenter>(), MainView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun log(tips: String) {
        Log.i("main", tips)
    }
}