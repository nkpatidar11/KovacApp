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
import static com.kovac.app.R.id.spInvestmentOption;

public class SignupSuperannuationDetailFragment extends Fragment {

    private OnSignupSuperannuationDetailFragmentInteractionListener onSignupSuperannuationDetailFragmentInteractionListener;
    private Spinner spInvestmentOption;
    private Button buttonBack, buttonNext;

    String[] investmentOptionArray = {"", "Concervative", "Balanced", "Growth", "High Growth"};

    public SignupSuperannuationDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup_superannuation_detail, container, false);

        buttonBack = (Button) view.findViewById(R.id.btnBack);
        buttonNext = (Button) view.findViewById(R.id.btnNext);

        //Employment status spinner
        spInvestmentOption = (Spinner) view.findViewById(R.id.spInvestmentOption);
        ArrayAdapter<String> spInvestmentOptionAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, investmentOptionArray);
        spInvestmentOptionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spInvestmentOption.setAdapter(spInvestmentOptionAdapter);

        //back to home screen
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSignupSuperannuationDetailFragmentInteractionListener.onSignupSuperannuationDetailFragmentSelected("signupEmploymentBack", null);
            }
        });


        //Next screen
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSignupSuperannuationDetailFragmentInteractionListener.onSignupSuperannuationDetailFragmentSelected("signupInsuranceDetail", null);
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSignupSuperannuationDetailFragmentInteractionListener) {
            onSignupSuperannuationDetailFragmentInteractionListener = (OnSignupSuperannuationDetailFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnSignupSuperannuationDetailFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onSignupSuperannuationDetailFragmentInteractionListener = null;
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
    public interface OnSignupSuperannuationDetailFragmentInteractionListener {
        // TODO: Update argument type and name
        void onSignupSuperannuationDetailFragmentSelected(String action, Bundle bundle);
    }
}
