package shareshare.damon_kotlin_example

import com.zyhang.damon.kotlin.MvpView

/**
 * ProjectName:Damon
 * Description:
 * Created by zyhang on 2017/4/30.上午11:03
 * Modify by:
 * Modify time:
 * Modify remark:
 */

interface MainView : MvpView {
    fun log(tips: String)
}