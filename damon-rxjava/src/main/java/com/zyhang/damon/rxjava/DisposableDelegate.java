package com.zyhang.damon.rxjava;

/**
 * Created by zyhang on 2018/11/30.17:53
 */

public interface DisposableDelegate extends DisposableHelper {
    /**
     * dispose on pause
     * but can be add again
     */
    void clearPause();

    /**
     * dispose on destroyView
     * but can be add again
     */
    void clearDestroyView();
}
