package com.kovac.app.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.NetworkError;
import com.android.volley.error.NoConnectionError;
import com.android.volley.error.ParseError;
import com.android.volley.error.ServerError;
import com.android.volley.error.TimeoutError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kovac.app.MainActivity;
import com.kovac.app.R;
import com.kovac.app.sessions.UserSession;
import com.kovac.app.utils.JsonParser;
import com.kovac.app.utils.Util;


import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.attr.name;

public class SignupLoginDetailFragment extends Fragment {

    private OnSignupLoginDetailFragmentInteractionListener onSignupLoginDetailFragmentInteractionListener;
    private Button buttonBack, buttonNext;
    private EditText etFirstName, etSurname, etEmail, etUserName, etPassword;
    UserSession userSession;

    //Volley RequestQueue
    private RequestQueue requestQueue;

    public SignupLoginDetailFragment() {
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
        View view =  inflater.inflate(R.layout.fragment_signup_login_detail, container, false);

        buttonBack = (Button) view.findViewById(R.id.btnBack);
        buttonNext = (Button) view.findViewById(R.id.btnNext);

        etFirstName = (EditText) view.findViewById(R.id.etFirstName);
        etSurname = (EditText) view.findViewById(R.id.etSurname);
        etEmail = (EditText) view.findViewById(R.id.etEmail);
        etUserName = (EditText) view.findViewById(R.id.etUserName);
        etPassword = (EditText) view.findViewById(R.id.etPassword);

        //back to home screen
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSignupLoginDetailFragmentInteractionListener.onSignupLoginDetailFragmentSelected("homeBack", null);
            }
        });

        userSession = new UserSession(getContext());
        //Toast.makeText(getContext(), "email is : " + userSession.getEmail(), Toast.LENGTH_SHORT).show();
        if(userSession.getEmail().trim().length() > 0){
            onSignupLoginDetailFragmentInteractionListener.onSignupLoginDetailFragmentSelected("signupPersonalDetail", null);
            /*etFirstName.setText(userSession.getFirstName());
            etSurname.setText(userSession.getSurname());
            etEmail.setText(userSession.getEmail());
            etUserName.setText(userSession.getUserName());
            etPassword.setText(userSession.getPassword());*/
        }

        //Next screen
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = 0;

                String firstName = etFirstName.getText().toString();
                String surname = etSurname.getText().toString();
                String email = etEmail.getText().toString();
                String userName = etUserName.getText().toString().trim().toLowerCase();
                String password = etPassword.getText().toString();

                if (firstName.trim().length() == 0){
                    etFirstName.setError( "First Name is required!" );
                    count++;
                }

                if (surname.trim().length() == 0){
                    etSurname.setError( "Surname is required!" );
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
                } /*else if (!Util.isValidPassword(password)){
                    etPassword.setError( "Password length must be 6 characters or more!" );
                    count++;
                }*/

                //Log.d("number of count is ", String.valueOf(count));
                if (count == 0){
                    userSession.setFirstName(firstName);
                    userSession.setSurname(surname);
                    userSession.setUserName(userName);
                    userSession.setEmail(email);
                    userSession.setPassword(password);
                    callApi(firstName, surname, userName, email, password);
                      //new CreateNewUser(userName, email, password).execute();
                }else {
                    return;
                }
            }
        });

        return view;
    }

    private void callApi(final String firstName, final String surname, final String userName, final String email, final String password){
        //Displaying a progress dialog
        final ProgressDialog loading = ProgressDialog.show(getContext(), "", "Please wait...", false, false);
        //Again creating the string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Util.REGISTRATION_LOGIN_DETAIL_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        Log.d("response", response);
                        /*try {*/
                            //Creating the json object from the response
                            //JSONObject jsonResponse = new JSONObject(response);
                            if (Integer.parseInt(response) > 0 && !response.equals("null") && !response.equals(""))  {
                                userSession.setUserId(response);
                                onSignupLoginDetailFragmentInteractionListener.onSignupLoginDetailFragmentSelected("signupPersonalDetail", null);
                            }
                            //Log.d("response",jsonResponse.toString());
                            //If it is success
                            /*if(jsonResponse.getInt(Utility.TAG_STATUS) == 1){

                                // Log.d("arra List", categoryList.toString());
                            }else{
                                Toast.makeText(context, jsonResponse.getString(Utility.TAG_MESSAGE), Toast.LENGTH_LONG).show();
                            }*/
                        /*} catch (JSONException e) {
                            e.printStackTrace();
                        }*/
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
                params.put(Util.USER_FNAME, firstName);
                params.put(Util.USER_LNAME, surname);
                params.put(Util.USER_NAME, userName);
                params.put(Util.USER_EMAIL, email);
                params.put(Util.USER_PASSWORD, password);
                params.put(Util.HALFFORM, "1");
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
