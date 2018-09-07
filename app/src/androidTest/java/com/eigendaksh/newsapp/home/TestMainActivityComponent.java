package com.eigendaksh.newsapp.home;

import com.eigendaksh.newsapp.di.ActivityScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

import static org.junit.Assert.*;

@ActivityScope
@Subcomponent(modules = {
    TestMainScreenBindingModule.class,
})
public interface TestMainActivityComponent extends AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity> {
    }


}