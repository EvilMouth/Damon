package com.zyhang.damon.rxjava;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;

/**
 * Created by zyhang on 2018/11/30.17:46
 */

@IntDef({DisposeOn.PAUSE, DisposeOn.DESTROY_VIEW})
@Retention(RetentionPolicy.SOURCE)
public @interface DisposeOn {
    int PAUSE = 1;
    int DESTROY_VIEW = 2;
}
