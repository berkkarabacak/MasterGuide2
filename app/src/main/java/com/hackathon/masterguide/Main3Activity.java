package com.hackathon.masterguide;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.hackathon.masterguide.core.LocationService;
import com.hackathon.masterguide.core.LocationServiceFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class Main3Activity extends AppCompatActivity {

    private LocationService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        service = LocationServiceFactory.instance().getService();
        List<String> countries = service.getCountries();

        final Spinner countrySpinner = (Spinner) findViewById(R.id.countrySpinner);
        final Spinner citySpinner = (Spinner) findViewById(R.id.citySpinner);

        final ArrayAdapter countryAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, countries.toArray());
        countrySpinner.setAdapter(countryAdapter);

        final ArrayAdapter cityAdapter = new ArrayAdapter(Main3Activity.this, android.R.layout.simple_spinner_item);
        citySpinner.setAdapter(cityAdapter);

        countrySpinner.setSelection(0, false);

        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                List<String> cities = service.getCities((String) countrySpinner.getSelectedItem());
                cityAdapter.clear();
                cityAdapter.addAll(cities);
                cityAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        final DatePickerFragment startDateFragment = new DatePickerFragment();
        final Button btnStartDate = (Button) findViewById(R.id.startDate);
        startDateFragment.setButton(btnStartDate);
        btnStartDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startDateFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        final DatePickerFragment endDateFragment = new DatePickerFragment();
        final Button btnEndDate = (Button) findViewById(R.id.endDate);
        endDateFragment.setButton(btnEndDate);
        btnEndDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                endDateFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        Button btnSearch = (Button) findViewById(R.id.search);
        btnSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main3Activity.this, Main4Activity.class);
                i.putExtra(ActivityConstants.COUNTRY, (String) countrySpinner.getSelectedItem());
                i.putExtra(ActivityConstants.CITY, (String) citySpinner.getSelectedItem());
                i.putExtra(ActivityConstants.START_DATE, btnStartDate.getText());
                i.putExtra(ActivityConstants.END_DATE, btnEndDate.getText());
                startActivity(i);
            }
        });
    }

}
