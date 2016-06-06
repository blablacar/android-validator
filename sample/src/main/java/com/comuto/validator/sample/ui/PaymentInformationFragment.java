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
import com.comuto.validator.sample.PaymentInformationFormValidator;
import com.comuto.validator.sample.R;

public class PaymentInformationFragment extends Fragment implements View.OnClickListener {

    private PaymentInformationFormValidator validator;

    public PaymentInformationFragment() {
    }

    public static PaymentInformationFragment newInstance() {
        return new PaymentInformationFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment_information, container, false);

        AppCompatSpinner cardType = (AppCompatSpinner) view.findViewById(R.id.card_type);
        TextInputEditText cardNumber = (TextInputEditText) view.findViewById(R.id.card_number);
        TextInputEditText expirationDate = (TextInputEditText) view.findViewById(R.id.expiration_date);
        TextInputEditText securityCode = (TextInputEditText) view.findViewById(R.id.security_code);
        TextView cardTypeError = (TextView) view.findViewById(R.id.card_type_error);
        view.findViewById(R.id.submit).setOnClickListener(this);

        validator =
            new PaymentInformationFormValidator(cardType, cardNumber, expirationDate, securityCode, cardTypeError);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (validator.isValidOrDisplayErrors()) {
            Toast.makeText(getContext(), "Is Valid", Toast.LENGTH_LONG).show();
        }
    }
}
