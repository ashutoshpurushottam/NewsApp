package com.eigendaksh.newsapp.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import com.eigendaksh.newsapp.R;
import com.eigendaksh.newsapp.base.BaseActivity;

import javax.inject.Inject;
import javax.inject.Named;

import timber.log.Timber;

public class MainActivity extends BaseActivity {

    @Inject @Named("author") String authorName;
    @Inject @Named("subauthor") String subAuthorName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String message = String.format("Author: %s, SubAuthor: %s", authorName, subAuthorName);
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
