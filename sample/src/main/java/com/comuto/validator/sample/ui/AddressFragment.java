package com.comuto.validator.sample.ui;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.comuto.validator.sample.AddressFormValidator;
import com.comuto.validator.sample.R;

public class AddressFragment extends Fragment implements View.OnClickListener {

    private AddressFormValidator validator;

    public AddressFragment() {
    }

    public static AddressFragment newInstance() {
        return new AddressFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_address, container, false);

        TextInputEditText address = (TextInputEditText) view.findViewById(R.id.address);
        TextInputEditText city = (TextInputEditText) view.findViewById(R.id.city);
        AppCompatSpinner state = (AppCompatSpinner) view.findViewById(R.id.state);
        TextView stateError = (TextView) view.findViewById(R.id.state_error);
        TextInputEditText zipCode = (TextInputEditText) view.findViewById(R.id.zip_code);
        AppCompatSpinner country = (AppCompatSpinner) view.findViewById(R.id.country);
        TextView countryError = (TextView) view.findViewById(R.id.country_error);
        view.findViewById(R.id.submit).setOnClickListener(this);

        validator = new AddressFormValidator(address, city, state, zipCode, country, stateError, countryError);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (validator.isValidOrDisplayErrors()) {
            Toast.makeText(getContext(), "Is Valid", Toast.LENGTH_LONG).show();
        }
    }
}
