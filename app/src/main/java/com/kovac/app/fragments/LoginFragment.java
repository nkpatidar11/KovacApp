package com.kovac.app.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kovac.app.R;

import static android.R.attr.button;

public class LoginFragment extends Fragment {
    private Button buttonBackToHome, buttonLogin;
    private TextView textViewRegister, textViewExistingClient;

    private OnLoginFragmentInteractionListener onLoginFragmentInteractionListener;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_login, container, false);

        buttonBackToHome = (Button) view.findViewById(R.id.btnHome);
        buttonLogin = (Button) view.findViewById(R.id.btnLogin);
        textViewRegister = (TextView) view.findViewById(R.id.textViewRegister);
        textViewExistingClient = (TextView) view.findViewById(R.id.textViewExistingClient);

        //Go Back to home
        buttonBackToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLoginFragmentInteractionListener.onLoginFragmentSelected("homeBack", null);
            }
        });

        //Go to register screen
        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLoginFragmentInteractionListener.onLoginFragmentSelected("register", null);
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnLoginFragmentInteractionListener) {
            onLoginFragmentInteractionListener = (OnLoginFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnLoginFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onLoginFragmentInteractionListener = null;
    }


    public interface OnLoginFragmentInteractionListener {
        // TODO: Update argument type and name
        void onLoginFragmentSelected(String action, Bundle bundle);
    }
}
