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

public class RegisterSuccessFragment extends Fragment {

    private OnRegisterSuccessFragmentInteractionListener onRegisterSuccessFragmentInteractionListener;

    private Button buttonBackToHome;

    public RegisterSuccessFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_register_success, container, false);

        buttonBackToHome = (Button) view.findViewById(R.id.btnBackToHome);

        // Go back to home screen
        buttonBackToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRegisterSuccessFragmentInteractionListener.onRegisterSuccessFragmentSelected("homeBack");
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRegisterSuccessFragmentInteractionListener) {
            onRegisterSuccessFragmentInteractionListener = (OnRegisterSuccessFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnRegisterSuccessFragmentInteractionListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        onRegisterSuccessFragmentInteractionListener = null;
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
    public interface OnRegisterSuccessFragmentInteractionListener {
        // TODO: Update argument type and name
        void onRegisterSuccessFragmentSelected(String action);
    }
}
