package com.example.sayyadabid.testapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sayyadabid.testapp.R;
import com.example.sayyadabid.testapp.datamodels.Destination;
import com.example.sayyadabid.testapp.datamodels.FromCentral;
import com.example.sayyadabid.testapp.datamodels.Location;
import com.example.sayyadabid.testapp.ui.controller.Scenario2Controller;

/**
 * Activity for Scenario 2 implementation. Uses Scenario2Controller to call the web services and populate the UI accordingly.
 * Created by Sayyad.abid on 12-Mar-16.
 */
public class Scenario2Activity extends MainActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private Scenario2Controller controller;
    private TextView tvModeCar;
    private TextView tvModeTrain;
    private Button btnNavigate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout flContainer = (FrameLayout) findViewById(R.id.main_container);
        View view = getLayoutInflater().inflate(R.layout.content_scenario2, null, false);
        flContainer.addView(view);

        tvModeCar = (TextView) findViewById(R.id.tv_mode_car);
        tvModeTrain = (TextView) findViewById(R.id.tv_mode_train);

        btnNavigate = (Button) findViewById(R.id.btn_navigate);
        btnNavigate.setOnClickListener(this);
        btnNavigate.setEnabled(false);

        Spinner spnrDestinations = (Spinner) findViewById(R.id.spnr_destinations);
        spnrDestinations.setOnItemSelectedListener(this);

        controller = new Scenario2Controller(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String[] destinationNames = controller.getDestinationNames();
        if (destinationNames == null) {
            controller.getDestinationsFromServer();
        } else {
            populateSpinner(destinationNames);
        }
    }

    /**
     * Method to populate the location spinner. Mostly called after the web service response is received.
     *
     * @param destinationNames the destination names to be displayed as selection values in spinner
     */
    public void populateSpinner(final String[] destinationNames) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ArrayAdapter adapter = new ArrayAdapter(Scenario2Activity.this, R.layout.custom_spinner_item, destinationNames);
                adapter.setDropDownViewResource(R.layout.custom_spinner_item);
                Spinner spnrDestinations = (Spinner) findViewById(R.id.spnr_destinations);
                spnrDestinations.setAdapter(adapter);
            }
        });
    }

    /**
     * Method to display destination details for input destination.
     * Mosly called after destination is selected from spinner.
     *
     * @param destination the Destination
     */
    private void displayDestinationDetails(Destination destination) {
        FromCentral fromCentral = destination.getFromcentral();
        if (fromCentral != null) {
            String byCar = fromCentral.getCar();
            if (!TextUtils.isEmpty(byCar)) {
                tvModeCar.setVisibility(View.VISIBLE);
                String formattedText = String.format(getString(R.string.mode_car), byCar);
                tvModeCar.setText(formattedText);
            } else {
                tvModeCar.setVisibility(View.INVISIBLE);
            }

            String byTrain = fromCentral.getTrain();
            if (!TextUtils.isEmpty(byTrain)) {
                tvModeTrain.setVisibility(View.VISIBLE);
                String formattedText = String.format(getString(R.string.mode_train), byTrain);
                tvModeTrain.setText(formattedText);
            } else {
                tvModeTrain.setVisibility(View.INVISIBLE);
            }
        } else {
            tvModeCar.setVisibility(View.INVISIBLE);
            tvModeTrain.setVisibility(View.INVISIBLE);
        }

        Location location = destination.getLocation();
        if (location != null) {
            btnNavigate.setEnabled(true);
            btnNavigate.setTag(destination);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_navigate:
                Destination destination = (Destination) view.getTag();
                Intent mapsIntent = new Intent(Scenario2Activity.this, MapsActivity.class);
                mapsIntent.putExtra(MapsActivity.TAG_SELECTED_DESTINATION, destination);
                startActivity(mapsIntent);
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Destination destination = controller.getDestinationAt(i);
        displayDestinationDetails(destination);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
