package com.kovac.app.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.kovac.app.MainActivity;
import com.kovac.app.R;

public class SignupLoginDetailFragment extends Fragment {

    private OnSignupLoginDetailFragmentInteractionListener onSignupLoginDetailFragmentInteractionListener;
    private Button buttonBack, buttonNext;

    public SignupLoginDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_signup_login_detail, container, false);

        buttonBack = (Button) view.findViewById(R.id.btnBack);
        buttonNext = (Button) view.findViewById(R.id.btnNext);

        //back to home screen
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSignupLoginDetailFragmentInteractionListener.onSignupLoginDetailFragmentSelected("homeBack", null);
            }
        });


        //Next screen
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSignupLoginDetailFragmentInteractionListener.onSignupLoginDetailFragmentSelected("signupPersonalDetail", null);
            }
        });

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSignupLoginDetailFragmentInteractionListener) {
            onSignupLoginDetailFragmentInteractionListener = (OnSignupLoginDetailFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnSignupLoginDetailFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onSignupLoginDetailFragmentInteractionListener = null;
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
    public interface OnSignupLoginDetailFragmentInteractionListener {
        // TODO: Update argument type and name
        void onSignupLoginDetailFragmentSelected(String action, Bundle bundle);
    }
}
