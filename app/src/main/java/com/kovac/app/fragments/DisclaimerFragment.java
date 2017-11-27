package com.kovac.app.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kovac.app.R;


public class DisclaimerFragment extends Fragment {

    private OnDisclaimerFragmentInteractionListener mListener;
    private Button buttonBack, buttonSubmit;

    public DisclaimerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_disclaimer, container, false);

        buttonBack = (Button) view.findViewById(R.id.btnBack);
        buttonSubmit = (Button) view.findViewById(R.id.btnSubmit);

        //back to home screen
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onOnDisclaimerFragmentSelected("signupIdentificationBack", null);
            }
        });


        //Next screen
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onOnDisclaimerFragmentSelected("signupSubmitted", null);
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDisclaimerFragmentInteractionListener) {
            mListener = (OnDisclaimerFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnDisclaimerFragmentInteractionListener");
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
    public interface OnDisclaimerFragmentInteractionListener {
        // TODO: Update argument type and name
        void onOnDisclaimerFragmentSelected(String action, Bundle bundle);
    }
}
