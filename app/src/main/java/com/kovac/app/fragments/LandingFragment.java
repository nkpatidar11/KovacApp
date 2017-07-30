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

public class LandingFragment extends Fragment {

    private OnLandingFragmentInteractionListener onLandingFragmentInteractionListener;
    private Button buttonStart, buttonLogin, buttonWithUs;



    public LandingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_landing, container, false);

        buttonStart = (Button) view.findViewById(R.id.buttonStart);
        buttonLogin = (Button) view.findViewById(R.id.buttonLogin);
        buttonWithUs = (Button) view.findViewById(R.id.buttonWithUs);

        //start here
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              onLandingFragmentInteractionListener.onLandingFragmentSelected("signupLoginDetail");
            }
        });

        //login
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLandingFragmentInteractionListener.onLandingFragmentSelected("login");
            }
        });

        //why be with us
        buttonWithUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLandingFragmentInteractionListener.onLandingFragmentSelected("aboutDetail");
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnLandingFragmentInteractionListener) {
            onLandingFragmentInteractionListener = (OnLandingFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnLandingFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onLandingFragmentInteractionListener = null;
    }

    public interface OnLandingFragmentInteractionListener {
        // TODO: Update argument type and name
        void onLandingFragmentSelected(String action);
    }
}
