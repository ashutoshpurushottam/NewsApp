package com.eigendaksh.newsapp.search.screens.search;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;
import com.eigendaksh.newsapp.R;
import com.eigendaksh.newsapp.base.BaseController;
import com.eigendaksh.newsapp.search.screens.searcharticles.SearchArticlesController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class SearchScreenController extends BaseController {

    @Inject
    SearchScreenPresenter presenter;
    @Inject
    SearchScreenViewModel viewModel;
    @Inject
    Context appContext;

    @BindView(R.id.search_query)
    EditText searchQuery;
    @BindView(R.id.checkbox_arts)
    CheckBox artsCheckBox;
    @BindView(R.id.checkbox_business)
    CheckBox businessCheckBox;
    @BindView(R.id.checkbox_politics)
    CheckBox politicsCheckBox;
    @BindView(R.id.checkbox_entrepreneurs)
    CheckBox entrepreneursCheckBox;
    @BindView(R.id.checkbox_sports)
    CheckBox sportsCheckBox;
    @BindView(R.id.checkbox_travel)
    CheckBox travelCheckBox;
    @BindView(R.id.search_button)
    Button searchButton;


    @Override
    protected int layoutRes() {
        return R.layout.search_screen;
    }

    @Override
    protected void onViewBound(View view) {
        super.onViewBound(view);
        Objects.requireNonNull(getActivity()).setTitle(getActivity().getString(R.string
                .search_screen_title));
        searchButton.setOnClickListener(__ -> searchButtonPressed());
    }

    private void searchButtonPressed() {
        String query = searchQuery.getText().toString();
        List<String> categories = new ArrayList<>();
        if (artsCheckBox.isChecked()) {
            categories.add("arts");
        }
        if (businessCheckBox.isChecked()) {
            categories.add("business");
        }
        if (politicsCheckBox.isChecked()) {
            categories.add("politics");
        }
        if (entrepreneursCheckBox.isChecked()) {
            categories.add("entrepreneurs");
        }
        if (sportsCheckBox.isChecked()) {
            categories.add("sports");
        }
        if (travelCheckBox.isChecked()) {
            categories.add("travel");
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (String category : categories) {
            stringBuilder.append(category).append("+");
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.setLength(stringBuilder.length() - 1);
        }
        try {
            presenter.searchButtonPressed(query, stringBuilder.toString());
        } catch (Exception e) {
            Timber.e(e.getLocalizedMessage());
        }
    }

    @Override
    protected Disposable[] subscriptions() {
        return new Disposable[]{
                viewModel.getMessageObs()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::showToast),
                viewModel.getNextScreenObs()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(strings -> moveToNextScreen(strings[0], strings[1]))

        };
    }

    private void moveToNextScreen(String query, String categories) {
        getRouter()
                .pushController(RouterTransaction.with(SearchArticlesController.newInstance
                        (query, categories))
                        .pushChangeHandler(new FadeChangeHandler())
                        .popChangeHandler(new FadeChangeHandler()));
    }


    private void showToast(String message) {
        Toast toast = Toast.makeText(appContext, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
