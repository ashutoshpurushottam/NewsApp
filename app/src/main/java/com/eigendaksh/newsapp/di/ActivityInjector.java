package com.eigendaksh.newsapp.di;

import android.app.Activity;
import android.content.Context;

import com.eigendaksh.newsapp.base.BaseActivity;
import com.eigendaksh.newsapp.base.MyApplication;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.android.AndroidInjector;

/*
    Replaces default AndroidInjector for Activity
    to avoid recreation of components on config changes.
 */
public class ActivityInjector {


}
