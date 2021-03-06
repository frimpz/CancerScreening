package com.drasare.cancerscreening.ui.pickup;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.location.Address;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.drasare.cancerscreening.R;
import com.schibstedspain.leku.LocationPickerActivity;

import static com.schibstedspain.leku.LocationPickerActivityKt.ADDRESS;
import static com.schibstedspain.leku.LocationPickerActivityKt.LATITUDE;
import static com.schibstedspain.leku.LocationPickerActivityKt.LEKU_POI;
import static com.schibstedspain.leku.LocationPickerActivityKt.LOCATION_ADDRESS;
import static com.schibstedspain.leku.LocationPickerActivityKt.LONGITUDE;
import static com.schibstedspain.leku.LocationPickerActivityKt.SEARCH_ZONE_DEFAULT_LOCALE;
import static com.schibstedspain.leku.LocationPickerActivityKt.ZIPCODE;

public class PickupFragment extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private static final int REQUEST_PLACE_PICKER = 1;
    EditText placeplcker,datepicker,input_name,input_email;
    Button btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pickup_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        input_name = view.findViewById(R.id.input_name);
        input_name.setOnClickListener(this);
        input_email =  view.findViewById(R.id.input_phone);
        input_email.setOnClickListener(this);
        placeplcker = view.findViewById(R.id.place_picker_button);
        placeplcker.setOnClickListener(this);
        datepicker = view.findViewById(R.id.date_picker_button);
        datepicker.setOnClickListener(this);
        btn = view.findViewById(R.id.btn_send);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.place_picker_button:
                showPlacePicker();
                break;
            case R.id.date_picker_button:
                myshowDatePicker().show();
                break;
            case R.id.btn_send:
                getActivity().onBackPressed();
                break;
        }
    }

    private DatePickerDialog myshowDatePicker() {

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), PickupFragment.this, 1990, 1, 1);
        return datePickerDialog;
    }

    private void showPlacePicker() {

        Intent locationPickerIntent = new LocationPickerActivity.Builder()
                .withGeolocApiKey(getActivity().getResources().getString(R.string.key_google_apis_android))
                .withSearchZone("en_US")
                .withDefaultLocaleSearchZone()
                .shouldReturnOkOnBackPressed()
                .build(getContext());
        locationPickerIntent.putExtra(SEARCH_ZONE_DEFAULT_LOCALE, true);
        startActivityForResult(locationPickerIntent, REQUEST_PLACE_PICKER);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && data != null) {
            Log.d("RESULT****", "OK");
            if (requestCode == 1) {
                double latitude = data.getDoubleExtra(LATITUDE, 0.0);
                Log.d("LATITUDE****", String.valueOf(latitude));
                double longitude = data.getDoubleExtra(LONGITUDE, 0.0);
                Log.d("LONGITUDE****", String.valueOf(longitude));
                String address = data.getStringExtra(LOCATION_ADDRESS);
                Log.d("ADDRESS****", address);
                String postalcode = data.getStringExtra(ZIPCODE);
                Log.d("POSTALCODE****", postalcode);
                Address fullAddress = data.getParcelableExtra(ADDRESS);
                if (fullAddress != null) {
                    Log.d("FULL ADDRESS****", fullAddress.toString());
                }
                placeplcker.setText(address);
            } else if (requestCode == 2) {
                double latitude = data.getDoubleExtra(LATITUDE, 0.0);
                Log.d("LATITUDE****", String.valueOf(latitude));
                double longitude = data.getDoubleExtra(LONGITUDE, 0.0);
                Log.d("LONGITUDE****", String.valueOf(longitude));
                String address = data.getStringExtra(LOCATION_ADDRESS);
                Log.d("ADDRESS****", address);
                double lekuPoi = data.getParcelableExtra(LEKU_POI);
                Log.d("LekuPoi****", String.valueOf(lekuPoi));
                placeplcker.setText(address);
            }
        }
        if (resultCode == Activity.RESULT_CANCELED) {
            Log.d("RESULT****", "CANCELLED");
        }
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        datepicker.setText( "" + dayOfMonth + "-" + (month + 1) + "-" + year );
    }

}
