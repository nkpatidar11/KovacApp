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

public class SignupPersonalDetailFragment extends Fragment {

    private OnSignupPersonalDetailFragmentInteractionListener onSignupPersonalDetailFragmentInteractionListener;
    private Spinner spGender, spState, spMaritalStatus, spDependantChildren;
    private Button buttonBack, buttonNext;

    String[] genderArray = {"", "Male", "Female"};
    String[] stateArray = {"", "ACT", "NSW", "NT", "QLD", "TAS", "VIC", "WA"};
    String[] maritalStatusArray = {"", "Single", "Married", "Defacto"};
    String[] dependantChildrenArray = {"", "0", "1", "2", "3", "4", "5"};


    public SignupPersonalDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_signup_personal_detail, container, false);

        buttonBack = (Button) view.findViewById(R.id.btnBack);
        buttonNext = (Button) view.findViewById(R.id.btnNext);

        //Gender spinner
        spGender = (Spinner) view.findViewById(R.id.spGender);
        ArrayAdapter<String> spGenderAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, genderArray);
        spGenderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGender.setAdapter(spGenderAdapter);

        //State Spinner
        spState = (Spinner) view.findViewById(R.id.spState);
        ArrayAdapter<String> spStateAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, stateArray);
        spStateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spState.setAdapter(spStateAdapter);


        //Marital status Spinner
        spMaritalStatus = (Spinner) view.findViewById(R.id.spMaritalStatus);
        ArrayAdapter<String> spMaritalStatusAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, maritalStatusArray);
        spMaritalStatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMaritalStatus.setAdapter(spMaritalStatusAdapter);

        //Dependant Children Spinner
        spDependantChildren = (Spinner) view.findViewById(R.id.spDependantChildren);
        ArrayAdapter<String> spDependantChildrenAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, dependantChildrenArray);
        spDependantChildrenAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDependantChildren.setAdapter(spDependantChildrenAdapter);


        //back to home screen
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSignupPersonalDetailFragmentInteractionListener.onSignupPersonalDetailFragmentSelected("signupLoginBack", null);
            }
        });


        //Next screen
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSignupPersonalDetailFragmentInteractionListener.onSignupPersonalDetailFragmentSelected("signupEmploymentDetail", null);
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSignupPersonalDetailFragmentInteractionListener) {
            onSignupPersonalDetailFragmentInteractionListener = (OnSignupPersonalDetailFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnSignupPersonalDetailFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onSignupPersonalDetailFragmentInteractionListener = null;
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
    public interface OnSignupPersonalDetailFragmentInteractionListener {
        // TODO: Update argument type and name
        void onSignupPersonalDetailFragmentSelected(String action, Bundle bundle);
    }
}
