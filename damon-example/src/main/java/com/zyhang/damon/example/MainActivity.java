package com.zyhang.damon.example;

import android.os.Bundle;
import android.util.Log;

import com.zyhang.damon.RequiresPresenter;
import com.zyhang.damon.rxjava.support.MvpAppCompatActivityRx;

@RequiresPresenter(MainPresenter.class)
public class MainActivity extends MvpAppCompatActivityRx<MainPresenter> implements MainView {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void log(String tips) {
        Log.i("main", tips);
    }
}
