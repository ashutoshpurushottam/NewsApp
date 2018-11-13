package com.eigendaksh.newsapp.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import com.eigendaksh.newsapp.R;
import com.eigendaksh.newsapp.article.WebViewActivity;
import com.eigendaksh.newsapp.base.BaseActivity;
import com.eigendaksh.newsapp.home.adapter.StoriesAdapter;
import com.eigendaksh.newsapp.home.businessnews.BusinessNewsFragment;
import com.eigendaksh.newsapp.home.sportsnews.SportsNewsFragment;
import com.eigendaksh.newsapp.home.worldnews.WorldNewsFragment;
import com.eigendaksh.newsapp.home.trendingnews.TrendingNewsFragment;
import com.eigendaksh.newsapp.search.SearchActivity;

import butterknife.BindView;


public class MainActivity extends BaseActivity implements StoriesAdapter.OnStoryClickedListener {

    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;

    private static final String SELECTED_INDEX_KEY = "selected_index";

    private static final String TOP_NEWS_TAG = "TOP_NEWS_TAG";
    private static final String TRENDING_NEWS_TAG = "TRENDING_NEWS_TAG";
    private static final String BUSINESS_NEWS_TAG = "BUSINESS_NEWS_TAG ";
    private static final String SPORTS_NEWS_TAG = "SPORTS_NEWS_TAG";

    private static final int TOP_NEWS_INDEX = 0;
    private static final int TRENDING_NEWS_INDEX = 1;
    private static final int BUSINESS_NEWS_INDEX = 2;
    private static final int SPORTS_NEWS_INDEX = 3;

    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragmentManager = getSupportFragmentManager();

        // Get selected index of bottom navigation
        int selectedIndex = savedInstanceState == null ? 0 : savedInstanceState.getInt(SELECTED_INDEX_KEY);
        loadFirstFragment(selectedIndex);
        setUpBottomNavigation();
    }

    /*
    Loads fragment on basis of selected index
    Default: 0
     */
    private void loadFirstFragment(int selectedIndex) {
        Fragment fragment = null;
        String tag = null;
        switch (selectedIndex) {
            case 0:
                tag = TOP_NEWS_TAG;
                fragment = fragmentManager.findFragmentByTag(TOP_NEWS_TAG);
                if (fragment == null) {
                    fragment = WorldNewsFragment.newInstance();
                }
                break;
            case 1:
                tag = TRENDING_NEWS_TAG;
                fragment = fragmentManager.findFragmentByTag(TRENDING_NEWS_TAG);
                if (fragment == null) {
                    fragment = TrendingNewsFragment.newInstance();
                }
                break;
            case 2:
                tag = BUSINESS_NEWS_TAG;
                fragment = fragmentManager.findFragmentByTag(BUSINESS_NEWS_TAG);
                if (fragment == null) {
                    fragment = BusinessNewsFragment.newInstance();
                }
                break;
            case 3:
                tag = SPORTS_NEWS_TAG;
                fragment = fragmentManager.findFragmentByTag(SPORTS_NEWS_TAG);
                if (fragment == null) {
                    fragment = SportsNewsFragment.newInstance();
                }
                break;
        }

        if (fragment != null) {
            changeFragment(fragment, tag);
        }
    }


    private void setUpBottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.action_top:
                    Fragment fragmentTop = fragmentManager.findFragmentByTag(TOP_NEWS_TAG);
                    if (fragmentTop == null) fragmentTop = WorldNewsFragment.newInstance();
                    changeFragment(fragmentTop, TOP_NEWS_TAG);
                    return true;
                case R.id.action_trending:
                    Fragment fragmentTrending = fragmentManager.findFragmentByTag(TRENDING_NEWS_TAG);
                    if (fragmentTrending == null)
                        fragmentTrending = TrendingNewsFragment.newInstance();
                    changeFragment(fragmentTrending, TRENDING_NEWS_TAG);
                    return true;
                case R.id.action_business:
                    Fragment fragmentBusiness = fragmentManager.findFragmentByTag(BUSINESS_NEWS_TAG);
                    if (fragmentBusiness == null)
                        fragmentBusiness = BusinessNewsFragment.newInstance();
                    changeFragment(fragmentBusiness, BUSINESS_NEWS_TAG);
                    return true;
                case R.id.action_sports:
                    Fragment fragmentSports = fragmentManager.findFragmentByTag(SPORTS_NEWS_TAG);
                    if (fragmentSports == null) fragmentSports = SportsNewsFragment.newInstance();
                    changeFragment(fragmentSports, SPORTS_NEWS_TAG);
                    return true;
            }
            return false;
        });

    }

    public void changeFragment(Fragment fragment, String tagFragmentName) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment currentFragment = fragmentManager.getPrimaryNavigationFragment();
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment);
        }

        Fragment fragmentTemp = fragmentManager.findFragmentByTag(tagFragmentName);
        if (fragmentTemp == null) {
            fragmentTemp = fragment;
            fragmentTransaction.add(R.id.fragment_frame, fragmentTemp, tagFragmentName);
        } else {
            fragmentTransaction.show(fragmentTemp);
        }

        fragmentTransaction.setPrimaryNavigationFragment(fragmentTemp);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commitNowAllowingStateLoss();
    }


    /*
    Save selected index of bottom navigation
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        int id = bottomNavigationView.getSelectedItemId();
        int selectedIndex = TOP_NEWS_INDEX;
        switch (id) {
            case R.id.action_top:
                selectedIndex = TOP_NEWS_INDEX;
                break;
            case R.id.action_trending:
                selectedIndex = TRENDING_NEWS_INDEX;
                break;
            case R.id.action_business:
                selectedIndex = BUSINESS_NEWS_INDEX;
                break;
            case R.id.action_sports:
                selectedIndex = SPORTS_NEWS_INDEX;
        }
        outState.putInt(SELECTED_INDEX_KEY, selectedIndex);
        super.onSaveInstanceState(outState);
    }

    // Inflate the menu : this adds items to the action bar if it is present
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Handle action bar item clicks here. The action bar will automatically handle clicks on the Home/Up button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_item:
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
                return true;
            case R.id.settings_notifications:
                //intent = new Intent(MainActivity.this, NotificationsActivity.class);
                //startActivity(intent);
                return true;
            case R.id.settings_help:
                return true;
            case R.id.settings_about:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }


    @Override
    public void onItemClicked(String url) {
        Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
        intent.putExtra("article_url", url);
        startActivity(intent);
    }
}
