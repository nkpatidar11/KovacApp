package com.kovac.app.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.kovac.app.R;

import static com.kovac.app.R.id.spEmploymentStatus;
import static com.kovac.app.R.id.spGender;


public class SignupEmploymentFragment extends Fragment {

    private OnSignupEmploymentFragmentInteractionListener onSignupEmploymentFragmentInteractionListener;
    private Spinner spEmploymentStatus;
    private Button buttonBack, buttonNext;

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


        //Next screen
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSignupEmploymentFragmentInteractionListener.onSignupEmploymentFragmentSelected("signupSuperannuationDetail", null);
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
