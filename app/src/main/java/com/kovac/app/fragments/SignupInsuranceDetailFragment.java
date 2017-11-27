package com.kovac.app.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.kovac.app.R;
import com.kovac.app.sessions.UserSession;
import com.kyleduo.switchbutton.SwitchButton;
import static com.kovac.app.R.id.etCurrentValue;
import static com.kovac.app.R.id.etMemberNumber;
import static com.kovac.app.R.id.switchCurrently;

public class SignupInsuranceDetailFragment extends Fragment {
    private EditText etHeight, etWeight, etLifeInsuranceValue, etDisabilityInsuranceValue, etIncomeProtectionValue, etTraumaValue;
    private Button buttonBack, buttonNext;
    private SwitchButton switchCurrently, switchSmoker;
    private CheckBox cbLifeInsurance, cbDisabilityInsurance, cbIncomeProtection, cbTraumaInsurance;
    private ConstraintLayout insuranceLayout;

    private OnSignupInsuranceDetailFragmentInteractionListener onSignupInsuranceDetailFragmentInteractionListener;

    public SignupInsuranceDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_signup_insurance_detail, container, false);

        buttonBack = (Button) view.findViewById(R.id.btnBack);
        buttonNext = (Button) view.findViewById(R.id.btnNext);

        etHeight = (EditText) view.findViewById(R.id.etHeight);
        etWeight = (EditText) view.findViewById(R.id.etWeight);
        etLifeInsuranceValue = (EditText) view.findViewById(R.id.etLifeInsuranceValue);
        etDisabilityInsuranceValue = (EditText) view.findViewById(R.id.etDisabilityInsuranceValue);
        etIncomeProtectionValue = (EditText) view.findViewById(R.id.etIncomeProtectionValue);
        etTraumaValue = (EditText) view.findViewById(R.id.etTraumaValue);

        switchCurrently = (SwitchButton) view.findViewById(R.id.switchCurrently);
        switchSmoker = (SwitchButton) view.findViewById(R.id.switchSmoker);

        cbLifeInsurance = (CheckBox) view.findViewById(R.id.cbLifeInsurance);
        cbDisabilityInsurance = (CheckBox) view.findViewById(R.id.cbDisabilityInsurance);
        cbIncomeProtection = (CheckBox) view.findViewById(R.id.cbIncomeProtection);
        cbTraumaInsurance = (CheckBox) view.findViewById(R.id.cbTraumaInsurance);

        insuranceLayout = (ConstraintLayout) view.findViewById(R.id.insuranceLayout);

        // set field value in session
        final UserSession userSession = new UserSession(getContext());
        //Toast.makeText(getContext(), "email is : " + userSession.getEmail(), Toast.LENGTH_SHORT).show();
        if(userSession.getHeight().trim().length() > 0){
            etHeight.setText(userSession.getHeight());
            etWeight.setText(userSession.getWeight());
            if (userSession.getSmoker().equals("Yes"))
                switchSmoker.setChecked(true);
            else
                switchSmoker.setChecked(false);

            if (userSession.getInsuranceCover().equals("Yes")) {
                switchCurrently.setChecked(true);
                insuranceLayout.setVisibility(View.VISIBLE);
                if (userSession.getLifeInsuranceStatus().equals("Yes")){
                    cbLifeInsurance.setChecked(true);
                    etLifeInsuranceValue.setEnabled(true);
                    etLifeInsuranceValue.setText(userSession.getLifeInsuranceValue());
                }

                if (userSession.getDisabilityInsuranceStatus().equals("Yes")){
                    cbDisabilityInsurance.setChecked(true);
                    etDisabilityInsuranceValue.setEnabled(true);
                    etDisabilityInsuranceValue.setText(userSession.getDisabilityInsuranceValue());
                }

                if (userSession.getIncomeProtectionStatus().equals("Yes")){
                    cbIncomeProtection.setChecked(true);
                    etIncomeProtectionValue.setEnabled(true);
                    etIncomeProtectionValue.setText(userSession.getIncomeProtectionValue());
                }

                if (userSession.getTraumaInsuranceStatus().equals("Yes")){
                    cbTraumaInsurance.setChecked(true);
                    etTraumaValue.setEnabled(true);
                    etTraumaValue.setText(userSession.getTraumaInsuranceValue());
                }
            }
            else {
                switchCurrently.setChecked(false);
                insuranceLayout.setVisibility(View.GONE);
            }
        }

        switchCurrently.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //Toast.makeText(getContext(), " "+ isChecked, Toast.LENGTH_SHORT).show();
                if (isChecked){
                    insuranceLayout.setVisibility(View.VISIBLE);
                }else {
                    insuranceLayout.setVisibility(View.GONE);
                }
            }
        });

        cbLifeInsurance.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked)
                    etLifeInsuranceValue.setEnabled(true);
                else
                    etLifeInsuranceValue.setEnabled(false);
            }
        });

        cbDisabilityInsurance.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked)
                    etDisabilityInsuranceValue.setEnabled(true);
                else
                    etDisabilityInsuranceValue.setEnabled(false);
            }
        });

        cbIncomeProtection.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked)
                    etIncomeProtectionValue.setEnabled(true);
                else
                    etIncomeProtectionValue.setEnabled(false);
            }
        });

        cbTraumaInsurance.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked)
                    etTraumaValue.setEnabled(true);
                else
                    etTraumaValue.setEnabled(false);
            }
        });

        //back to home screen
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSignupInsuranceDetailFragmentInteractionListener.onSignupInsuranceDetailFragmentSelected("signupSuperNnuationBack", null);
            }
        });


        //Next screen
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = 0;

                String height = etHeight.getText().toString();
                String weight = etWeight.getText().toString();

                String lifeInsuranceValue = etLifeInsuranceValue.getText().toString();
                String lifeInsuranceStatus = (cbLifeInsurance.isChecked())? "Yes" : "No";

                String disabilityInsuranceValue = etDisabilityInsuranceValue.getText().toString();
                String disabilityInsuranceStatus = (cbDisabilityInsurance.isChecked())? "Yes" : "No";

                String incomeProtectionValue = etIncomeProtectionValue.getText().toString();
                String incomeProtectionStatus =(cbIncomeProtection.isChecked())? "Yes" : "No";

                String traumaValue = etTraumaValue.getText().toString();
                String traumaStatus = (cbTraumaInsurance.isChecked())? "Yes" : "No";

                String smoker = (switchSmoker.isChecked()) ? "Yes" : "No";
                String currently = (switchCurrently.isChecked()) ? "Yes" : "No";

                Log.d("switch value", "smoker "+ smoker + "currently "+ currently);


                if (height.trim().length() == 0){
                    etHeight.setError( "Height is required!" );
                    count++;
                }

                if (weight.trim().length() == 0){
                    etWeight.setError( "Weight is required!" );
                    count++;
                }

                if (cbLifeInsurance.isChecked()){
                    if (lifeInsuranceValue.trim().length() == 0){
                        etLifeInsuranceValue.setError( "Life Insurance Value is required!" );
                        count++;
                    }
                }

                if (cbDisabilityInsurance.isChecked()){
                    if (lifeInsuranceValue.trim().length() == 0){
                        etDisabilityInsuranceValue.setError( "Disability Insurance Value is required!" );
                        count++;
                    }
                }

                if (cbIncomeProtection.isChecked()){
                    if (lifeInsuranceValue.trim().length() == 0){
                        etIncomeProtectionValue.setError( "Income Protection Value is required!" );
                        count++;
                    }
                }

                if (cbTraumaInsurance.isChecked()){
                    if (lifeInsuranceValue.trim().length() == 0){
                        etTraumaValue.setError( "Trauma Value is required!" );
                        count++;
                    }
                }


                Log.d("number of count is ", String.valueOf(count));
                if (count == 0){
                    userSession.setHeight(height);
                    userSession.setWeight(weight);

                    userSession.setLifeInsuranceValue(lifeInsuranceValue);
                    userSession.setLifeInsuranceStatus(lifeInsuranceStatus);

                    userSession.setDisabilityInsuranceValue(disabilityInsuranceValue);
                    userSession.setDisabilityInsuranceStatus(disabilityInsuranceStatus);

                    userSession.setIncomeProtectionValue(incomeProtectionValue);
                    userSession.setIncomeProtectionStatus(incomeProtectionStatus);

                    userSession.setTraumaInsuranceValue(traumaValue);
                    userSession.setTraumaInsuranceStatus(traumaStatus);

                    userSession.setSmoker(smoker);
                    userSession.setInsuranceCover(currently);

                    onSignupInsuranceDetailFragmentInteractionListener.onSignupInsuranceDetailFragmentSelected("signupAssetLiabalitiesDetail", null);
                }else {
                    return;
                }
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSignupInsuranceDetailFragmentInteractionListener) {
            onSignupInsuranceDetailFragmentInteractionListener = (OnSignupInsuranceDetailFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnSignupInsuranceDetailFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onSignupInsuranceDetailFragmentInteractionListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnSignupInsuranceDetailFragmentInteractionListener {
        // TODO: Update argument type and name
        void onSignupInsuranceDetailFragmentSelected(String action, Bundle bundle);
    }
}
