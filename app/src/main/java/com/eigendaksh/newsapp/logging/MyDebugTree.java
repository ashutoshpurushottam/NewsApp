package com.eigendaksh.newsapp.logging;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import timber.log.Timber;

public class MyDebugTree extends Timber.DebugTree {

    @Override
    protected @Nullable String createStackElementTag(@NotNull StackTraceElement element) {
        return super.createStackElementTag(element) + ":" + element.getLineNumber();
    }
}
