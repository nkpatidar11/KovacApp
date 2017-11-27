package com.kovac.app.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.NetworkError;
import com.android.volley.error.NoConnectionError;
import com.android.volley.error.ParseError;
import com.android.volley.error.ServerError;
import com.android.volley.error.TimeoutError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kovac.app.R;
import com.kovac.app.utils.Util;

import java.util.HashMap;
import java.util.Map;

import static com.kovac.app.R.id.etFirstName;
import static com.kovac.app.R.id.etSurname;

public class RegisterFragment extends Fragment {

    private OnRegisterFragmentInteractionListener onRegisterFragmentInteractionListener;

    private EditText etClientName, etEmail, etUserName, etPassword;
    private Button buttonBack, buttonSubmit;
    //Volley RequestQueue
    private RequestQueue requestQueue;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Initializing the RequestQueue
        requestQueue = Volley.newRequestQueue(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_register, container, false);

        etClientName = (EditText) view.findViewById(R.id.etClientName);
        etEmail = (EditText) view.findViewById(R.id.etEmail);
        etUserName = (EditText) view.findViewById(R.id.etUserName);
        etPassword = (EditText) view.findViewById(R.id.etPassword);

        buttonBack = (Button) view.findViewById(R.id.btnBack);
        buttonSubmit = (Button) view.findViewById(R.id.btnSubmit);

        //back to login screen
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRegisterFragmentInteractionListener.onRegisterFragmentSelected("backLogin", null);
            }
        });

        //go to success screen
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = 0;

                String clientName = etClientName.getText().toString();
                String email = etEmail.getText().toString();
                String userName = etUserName.getText().toString().trim().toLowerCase();
                String password = etPassword.getText().toString();

                if (clientName.trim().length() == 0){
                    etClientName.setError( "Client name is required!" );
                    count++;
                }

                if (email.trim().length() == 0){
                    etEmail.setError( "Email is required!" );
                    count++;
                } else if (!Util.isValidEmail(email)){
                    etEmail.setError("Invalid Email address!");
                    count++;
                }

                if (userName.trim().length() == 0){
                    etUserName.setError( "Username is required!" );
                    count++;
                }

                if (password.trim().length() == 0){
                    etPassword.setError( "Password is required!" );
                    count++;
                }

                if (count == 0){
                    callApi(clientName, userName, email, password);
                }else {
                    return;
                }
            }
        });

        return view;
    }

    private void callApi(final String clientName, final String userName, final String email, final String password){
        //Displaying a progress dialog
        final ProgressDialog loading = ProgressDialog.show(getContext(), "", "Please wait...", false, false);
        //Again creating the string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Util.ALREADY_EXIST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        Log.d("response", response);
                        if (response.equalsIgnoreCase("\"SUCCESS\""))  {
                            //userSession.setUserId(response);
                            onRegisterFragmentInteractionListener.onRegisterFragmentSelected("successRegistration", null);
                            //Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        if( error instanceof NetworkError) {
                            Toast.makeText(getContext(), "Network Error", Toast.LENGTH_LONG).show();
                        } else if( error instanceof ServerError) {
                            Toast.makeText(getContext(), "Server Error", Toast.LENGTH_LONG).show();
                        } else if( error instanceof AuthFailureError) {
                        } else if( error instanceof ParseError) {
                        } else if( error instanceof NoConnectionError) {
                            Toast.makeText(getContext(), "No Connection Error", Toast.LENGTH_LONG).show();
                        } else if( error instanceof TimeoutError) {
                            Toast.makeText(getContext(), "Timeout Error", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding the parameters to the request
                params.put(Util.USER_FNAME, clientName);
                params.put(Util.USER_NAME, userName);
                params.put(Util.USER_EMAIL, email);
                params.put(Util.USER_PASSWORD, password);
                params.put(Util.EXIST, "1");
                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        //Adding request the the queue
        requestQueue.add(stringRequest);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRegisterFragmentInteractionListener) {
            onRegisterFragmentInteractionListener = (OnRegisterFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnRegisterFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onRegisterFragmentInteractionListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnRegisterFragmentInteractionListener {
        // TODO: Update argument type and name
        void onRegisterFragmentSelected(String action, Bundle bundle);
    }
}
