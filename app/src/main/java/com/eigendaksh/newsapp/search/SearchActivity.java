package com.eigendaksh.newsapp.search;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.eigendaksh.newsapp.R;
import com.eigendaksh.newsapp.apiResponses.SearchApiResponse;
import com.eigendaksh.newsapp.base.BaseActivity;
import com.eigendaksh.newsapp.data.NewsApi;
import com.eigendaksh.newsapp.searchresults.SearchResultsActivity;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneOffset;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;

import butterknife.BindView;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;


public class SearchActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener {

    @BindView(R.id.fromDate)
    TextView fromDate;
    @BindView(R.id.toDate)
    TextView toDate;
    @BindView(R.id.searchButton)
    Button searchButton;
    @BindView(R.id.artsCheckBox)
    CheckBox artsCheckBox;
    @BindView(R.id.businessCheckBox)
    CheckBox businessCheckBox;
    @BindView(R.id.entrepreneursCheckBox)
    CheckBox entrepreneursCheckBox;
    @BindView(R.id.politicsCheckBox)
    CheckBox politicsCheckBox;
    @BindView(R.id.sportsCheckBox)
    CheckBox sportsCheckBox;
    @BindView(R.id.travelCheckBox)
    CheckBox travelCheckBox;
    @BindView(R.id.notificationSearchTerm)
    EditText searchTermText;
    @BindView(R.id.sortSwitch)
    Switch sortSwitch;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    ArrayList<CheckBox> searchSubjectCheckBoxes;

    private boolean isFromDateSelected;
    private String categoriesText;
    private String beginDate;
    private String endDate;

    @Override
    protected int layoutRes() {
        return R.layout.activity_search;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //create a new array list to store all the check boxes in the Search activity and add all the checkboxes to facilitate easy checking of state
        searchSubjectCheckBoxes = new ArrayList<>();
        searchSubjectCheckBoxes.add(artsCheckBox);
        searchSubjectCheckBoxes.add(businessCheckBox);
        searchSubjectCheckBoxes.add(entrepreneursCheckBox);
        searchSubjectCheckBoxes.add(politicsCheckBox);
        searchSubjectCheckBoxes.add(sportsCheckBox);
        searchSubjectCheckBoxes.add(travelCheckBox);

        fromDate.setOnClickListener(v -> {
            isFromDateSelected = true;
            final Calendar c = Calendar.getInstance();
            DatePickerFragment datePicker = DatePickerFragment.newInstance(
                    c.get(Calendar.YEAR),
                    c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH));
            datePicker.setListener(this);
            datePicker.show(getSupportFragmentManager(), null);

        });

        toDate.setOnClickListener(v -> {
            isFromDateSelected = false;
            final Calendar c = Calendar.getInstance();
            DatePickerFragment datePicker = DatePickerFragment.newInstance(
                    c.get(Calendar.YEAR),
                    c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH));
            datePicker.setListener(this);
            datePicker.show(getSupportFragmentManager(), null);
        });

        searchButton.setOnClickListener(v -> {
            if (goToSearch()) {
                StringBuilder sb = new StringBuilder();
                sb.append("news_desk:(");
                for (CheckBox checkBox : searchSubjectCheckBoxes) {
                    if (checkBox.isChecked()) {
                        sb.append("\"").append(checkBox.getText().toString()).append("\"");
                    }
                }
                sb.append(")");
                categoriesText = sb.toString();

                showSearchResults(
                        searchTermText.getText().toString(),
                        categoriesText,
                        beginDate,
                        endDate
                );


            } else {
                showToast(getString(R.string.search_msg));
            }

        });

        Calendar c = Calendar.getInstance();
        beginDate = String.format(Locale.getDefault(), "%d%02d%02d", c.get(Calendar.YEAR) - 1, c.get(Calendar.MONTH) + 1, c.get(Calendar.DATE));
        endDate = String.format(Locale.getDefault(), "%d%02d%02d", c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DATE));
        showToast("b: " + beginDate + " e: " + endDate);
    }

    private boolean goToSearch() {
        String searchTerm = searchTermText.getText().toString();
        for (CheckBox checkBox : searchSubjectCheckBoxes) {
            if (checkBox.isChecked() && !searchTerm.isEmpty()) {
                return true;
            }
        }

        return false;
    }



    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        if (isFromDateSelected) {
            fromDate.setText(String.format(Locale.getDefault(), "%d/%d/%d", dayOfMonth, month + 1, year));
            beginDate = String.format(Locale.getDefault(), "%d%02d%02d", year, month + 1, dayOfMonth);
        } else {
            toDate.setText(String.format(Locale.US, "%d/%d/%d", dayOfMonth, month + 1, year));
            endDate = String.format(Locale.getDefault(), "%d%02d%02d", year, month + 1, dayOfMonth);
        }
    }

    private void showSearchResults(String query, String categories, String beginDate, String endDate) {
        Intent intent = new Intent(this, SearchResultsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("query", query);
        bundle.putString("categories", categories);
        bundle.putString("begin", beginDate);
        bundle.putString("end", endDate);
        intent.putExtras(bundle);
        startActivity(intent);

    }
}
