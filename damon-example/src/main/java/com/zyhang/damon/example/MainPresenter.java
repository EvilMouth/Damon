package com.zyhang.damon.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.zyhang.damon.MvpPresenter;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * ProjectName:Damon
 * Description:
 * Created by zyhang on 2017/4/28.下午11:42
 * Modify by:
 * Modify time:
 * Modify remark:
 */

public class MainPresenter extends MvpPresenter<MainView> {

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedState) {
        super.onCreate(arguments, savedState);

//        Observable.interval(1, TimeUnit.SECONDS)
//                .compose(this.<Long>bindToLifecycle())
//                .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(@NonNull Long aLong) throws Exception {
//                        Log.i("accept", String.format("aLong === %s", aLong));
//                    }
//                });

        getView().log("onCreate");
    }

    @Override
    public void onResume() {
        super.onResume();

        Observable.interval(1, TimeUnit.SECONDS)
                .compose(this.<Long>bindToLifecycle())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        Log.i("accept", String.format("aLong === %s", aLong));
                    }
                });
    }
}
