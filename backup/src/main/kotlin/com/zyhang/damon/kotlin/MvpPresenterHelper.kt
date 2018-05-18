package com.zyhang.damon.kotlin

import android.os.Bundle

/**
 * ProjectName:Damon
 * Description:
 * Created by zyhang on 2017/4/28.下午5:47
 * Modify by:
 * Modify time:
 * Modify remark:
 */

internal interface MvpPresenterHelper {
    fun onCreate(arguments: Bundle?, savedState: Bundle?)
    fun onStart()
    fun onSave(state: Bundle)
    fun onResume()
    fun onPause()
    fun onStop()
    fun onDestroy()
}