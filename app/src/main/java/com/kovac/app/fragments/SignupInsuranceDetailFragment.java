package com.kovac.app.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kovac.app.R;

public class SignupInsuranceDetailFragment extends Fragment {

    private OnSignupInsuranceDetailFragmentInteractionListener onSignupInsuranceDetailFragmentInteractionListener;

    public SignupInsuranceDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup_insurance_detail, container, false);
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
