package com.kovac.app.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.kovac.app.R;
import com.kovac.app.sessions.UserSession;


public class SignupEmploymentFragment extends Fragment {

    private OnSignupEmploymentFragmentInteractionListener onSignupEmploymentFragmentInteractionListener;
    private Spinner spEmploymentStatus;
    private Button buttonBack, buttonNext;
    private EditText etOccupation, etTextFileNumber, etAnnualIncome;

    String[] employmentStatusArray = {"", "Full-Time Employed", "Part-Time Employed", "Self-Employed", "Unemployed", "Retired"};

    public SignupEmploymentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup_employment, container, false);

        buttonBack = (Button) view.findViewById(R.id.btnBack);
        buttonNext = (Button) view.findViewById(R.id.btnNext);

        //Edittext object creation
        etOccupation = (EditText) view.findViewById(R.id.etOccupation);
        etTextFileNumber = (EditText) view.findViewById(R.id.etTaxFileNumber);
        etAnnualIncome = (EditText) view.findViewById(R.id.etAnnualIncome);

        //Employment status spinner
        spEmploymentStatus = (Spinner) view.findViewById(R.id.spEmploymentStatus);
        ArrayAdapter<String> spEmploymentStatusAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, employmentStatusArray);
        spEmploymentStatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spEmploymentStatus.setAdapter(spEmploymentStatusAdapter);

        //back to home screen
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSignupEmploymentFragmentInteractionListener.onSignupEmploymentFragmentSelected("signupPersonalBack", null);
            }
        });

        final UserSession userSession = new UserSession(getContext());
        //Toast.makeText(getContext(), "email is : " + userSession.getEmail(), Toast.LENGTH_SHORT).show();
        if(userSession.getOccupation().trim().length() > 0){
            spEmploymentStatus.setSelection(((ArrayAdapter<String>)spEmploymentStatus.getAdapter()).getPosition(userSession.getEmploymentStatus()));
            etOccupation.setText(userSession.getOccupation());
            etTextFileNumber.setText(userSession.getTextFileNumber());
            etAnnualIncome.setText(userSession.getAnnualIncome());
        }

        //Next screen
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = 0;

                String occupation = etOccupation.getText().toString();
                String textFileNumber = etTextFileNumber.getText().toString();
                String annualIncome = etAnnualIncome.getText().toString();
                String employmentStatus = spEmploymentStatus.getSelectedItem().toString();


                if (occupation.trim().length() == 0){
                    etOccupation.setError( "Occupation is required!" );
                    count++;
                }

                if (textFileNumber.trim().length() == 0){
                    etTextFileNumber.setError( "Text file number is required!" );
                    count++;
                }

                if (annualIncome.trim().length() == 0){
                    etAnnualIncome.setError( "Annual Income is required!" );
                    count++;
                }

                /*if (employmentStatus.trim().length() == 0){
                    // ((TextView)spEmploymentStatus.getChildAt(0)).setError("Employment Status is required!");
                    ((TextView) spEmploymentStatus.getSelectedView()).setError("Employment Status is Required");
                    count++;
                }*/

                Log.d("number of count is ", String.valueOf(count));
                if (count == 0){
                    userSession.setOccupation(occupation);
                    userSession.setTextFileNumber(textFileNumber);
                    userSession.setAnnualIncome(annualIncome);
                    userSession.setEmploymentStatus(employmentStatus);
                    onSignupEmploymentFragmentInteractionListener.onSignupEmploymentFragmentSelected("signupSuperannuationDetail", null);
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
        if (context instanceof OnSignupEmploymentFragmentInteractionListener) {
            onSignupEmploymentFragmentInteractionListener = (OnSignupEmploymentFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnSignupEmploymentFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onSignupEmploymentFragmentInteractionListener = null;
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

    public interface OnSignupEmploymentFragmentInteractionListener {
        void onSignupEmploymentFragmentSelected(String action, Bundle bundle);
    }
}
