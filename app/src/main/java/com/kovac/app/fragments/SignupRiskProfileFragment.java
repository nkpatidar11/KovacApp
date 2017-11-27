package com.kovac.app.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kovac.app.R;
import com.kovac.app.sessions.UserSession;

import static com.kovac.app.R.id.etOwnHome;

public class SignupRiskProfileFragment extends Fragment {

    private OnSignupRiskProfileFragmentInteractionListener mListener;
    private Button buttonBack, buttonNext;
    private RadioGroup rgRiskProfile;
    private RadioButton rbRiskProfile;

    public SignupRiskProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_signup_risk_profile, container, false);

        buttonBack = (Button) view.findViewById(R.id.btnBack);
        buttonNext = (Button) view.findViewById(R.id.btnNext);

        rgRiskProfile = (RadioGroup) view.findViewById(R.id.rgRiskProfile);
        // set field value in session
        final UserSession userSession = new UserSession(getContext());
        //Toast.makeText(getContext(), "email is : " + userSession.getEmail(), Toast.LENGTH_SHORT).show();
        if(userSession.getRiskProfileId().trim().length() > 0){
            //rgRiskProfile.setText(userSession.getRiskProfile());
            int riskProfileId = Integer.parseInt(userSession.getRiskProfileId());
            if(!(riskProfileId == -1)){
                // find the radio button by returned id
                rbRiskProfile = (RadioButton) view.findViewById(riskProfileId);
            }
            if (rbRiskProfile != null)
                rbRiskProfile.setChecked(true);
        }

        //back to home screen
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onSignupRiskProfileFragmenteSelected("signupAssetsLiabalitiesBack", null);
            }
        });


        //Next screen
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = rgRiskProfile.getCheckedRadioButtonId();
                if(!(selectedId == -1)){
                    // find the radio button by returned id
                    rbRiskProfile = (RadioButton) view.findViewById(selectedId);
                }

                String riskProfile = null;
                if (rbRiskProfile != null) {
                   riskProfile = rbRiskProfile.getText().toString();
                }

                userSession.setRiskProfile(riskProfile);
                userSession.setRiskProfileId(String.valueOf(selectedId));

                mListener.onSignupRiskProfileFragmenteSelected("signupIdentification", null);
            }
        });



        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSignupRiskProfileFragmentInteractionListener) {
            mListener = (OnSignupRiskProfileFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnSignupRiskProfileFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public interface OnSignupRiskProfileFragmentInteractionListener {
        // TODO: Update argument type and name
        void onSignupRiskProfileFragmenteSelected(String action, Bundle bundle);
    }
}
