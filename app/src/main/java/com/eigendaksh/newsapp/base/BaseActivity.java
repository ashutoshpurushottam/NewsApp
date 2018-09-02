package com.eigendaksh.newsapp.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.eigendaksh.newsapp.R;
import com.eigendaksh.newsapp.di.Injector;
import com.eigendaksh.newsapp.di.ScreenInjector;

import java.util.UUID;

import javax.inject.Inject;

public abstract class BaseActivity extends AppCompatActivity {

    @Inject ScreenInjector screenInjector;
    private static final String INSTANCE_ID_KEY = "instance_id";
    private String instanceId;
    private Router router;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if(savedInstanceState != null) {
            instanceId = savedInstanceState.getString(INSTANCE_ID_KEY);
        } else {
            instanceId = UUID.randomUUID().toString();
        }
        Injector.inject(this);

        setContentView(layoutRes());
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        ViewGroup screenContainer = findViewById(R.id.screen_container);
        if (screenContainer == null) {
            throw new NullPointerException("Activity must have a view with id: screen_container");
        }

        router = Conductor.attachRouter(this, screenContainer, savedInstanceState);
        if(!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(initialScreen()));
        }
        monitorBackStack();
        super.onCreate(savedInstanceState);
    }

    @LayoutRes
    protected abstract int layoutRes();

    protected abstract Controller initialScreen();

    private void monitorBackStack() {
        router.addChangeListener(new ControllerChangeHandler.ControllerChangeListener() {
            @Override
            public void onChangeStarted(
                    @Nullable Controller to,
                    @Nullable Controller from,
                    boolean isPush,
                    @NonNull ViewGroup container,
                    @NonNull ControllerChangeHandler handler) {

            }

            @Override
            public void onChangeCompleted(
                    @Nullable Controller to,
                    @Nullable Controller from,
                    boolean isPush,
                    @NonNull ViewGroup container,
                    @NonNull ControllerChangeHandler handler) {
                if (!isPush && from != null) {
                    Injector.clearComponent(from);
                }
            }
        });
    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(INSTANCE_ID_KEY, instanceId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(isFinishing()) {
            Injector.clearComponent(this);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        if(router == null || !router.handleBack()) {
            super.onBackPressed();
        }
    }

    public String getInstanceId() {
        return instanceId;
    }


    public ScreenInjector getScreenInjector() {
        return screenInjector;
    }
}
