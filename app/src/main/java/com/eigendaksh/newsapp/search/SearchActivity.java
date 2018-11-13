package com.eigendaksh.newsapp.search;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
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
import com.eigendaksh.newsapp.base.BaseActivity;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;


public class SearchActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener{

    @BindView(R.id.fromDate) TextView fromDate; //the from date text view of the search activity
    @BindView(R.id.toDate) TextView toDate; //the to date text view of the search activity
    @BindView(R.id.searchButton) Button searchButton; //the search button of the search activity
    @BindView(R.id.artsCheckBox) CheckBox artsCheckBox; //the arts checkbox of the search activity
    @BindView(R.id.businessCheckBox) CheckBox businessCheckBox; //the business check box of the search activity
    @BindView(R.id.entrepreneursCheckBox) CheckBox entrepreneursCheckBox; //the entrepreneurs check box of the search activity
    @BindView(R.id.politicsCheckBox) CheckBox politicsCheckBox; //the politics check box of the search activity
    @BindView(R.id.sportsCheckBox) CheckBox sportsCheckBox; //the sports check box of the search activity
    @BindView(R.id.travelCheckBox) CheckBox travelCheckBox; //the travel check box of the search activity
    @BindView(R.id.notificationSearchTerm) EditText searchTermText; //the search term text view of the search activity
    @BindView(R.id.sortSwitch) Switch sortSwitch; //the switch to determine sorting order
    @BindView(R.id.progressBar) ProgressBar progressBar; //the progress bar to be displayed as data is being asynchronously downloaded
    ArrayList data; //array list to hold the downloaded data
    ArrayList<CheckBox> searchSubjectCheckBoxes; //an arraylist to hold all the search activities check boxes

    private boolean isFromDateSelected;
    private boolean isToDateSelected;




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
            isToDateSelected = false;

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
            isToDateSelected = true;
            final Calendar c = Calendar.getInstance();
            DatePickerFragment datePicker = DatePickerFragment.newInstance(
                    c.get(Calendar.YEAR),
                    c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH));
            datePicker.setListener(this);
            datePicker.show(getSupportFragmentManager(), null);
        });

    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        if(isFromDateSelected) {
            showToast("From date: " + dayOfMonth + "/" + month + "/" + year);
        } else {
            showToast("To date: " + dayOfMonth + "/" + month + "/" + year);
        }
    }
}
