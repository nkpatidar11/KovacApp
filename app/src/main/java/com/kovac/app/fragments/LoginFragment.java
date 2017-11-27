package com.kovac.app.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kovac.app.R;
import com.kovac.app.sessions.WebViewSession;
import com.kovac.app.utils.Util;

import static android.R.attr.button;

public class LoginFragment extends Fragment {
    private Button buttonBackToHome, buttonLogin;
    private TextView textViewRegister, textViewExistingClient;
    private EditText etUserName, etPassword;

    private WebViewSession webViewSession;

    private OnLoginFragmentInteractionListener onLoginFragmentInteractionListener;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        webViewSession = new WebViewSession(getContext());
        /*if (webViewSession.getCurrentWebUrl() != null){
            if (webViewSession.getIsWebviewLogin()){
                Toast.makeText(getContext(), "webview already login.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getContext(), "webview not login.", Toast.LENGTH_LONG).show();
            }
        }*/

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_login, container, false);

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        buttonBackToHome = (Button) view.findViewById(R.id.btnHome);
        buttonLogin = (Button) view.findViewById(R.id.btnLogin);
        textViewRegister = (TextView) view.findViewById(R.id.textViewRegister);
        textViewExistingClient = (TextView) view.findViewById(R.id.textViewExistingClient);

        etUserName = (EditText) view.findViewById(R.id.etUserName);
        etPassword = (EditText) view.findViewById(R.id.etPassword);

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

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = 0;

                String userName = etUserName.getText().toString().trim().toLowerCase();
                String password = etPassword.getText().toString();

                if (userName.trim().length() == 0){
                    etUserName.setError( "Username is required!" );
                    count++;
                }

                if (password.trim().length() == 0){
                    etPassword.setError( "Password is required!" );
                    count++;
                }
                if (count == 0){
                    Util.hideKeyboard(getContext());
                    onLoginFragmentInteractionListener.onLoginFragmentSelected("loginWeb", null);
                }
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
