package com.eigendaksh.newsapp.home;

import android.annotation.SuppressLint;

import com.bluelinelabs.conductor.Controller;
import com.eigendaksh.newsapp.R;
import com.eigendaksh.newsapp.base.BaseActivity;

public class MainActivity extends BaseActivity {


    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected Controller initialScreen() {
        return new MainActivityContainerController();
    }


}
