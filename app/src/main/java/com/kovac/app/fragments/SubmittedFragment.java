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
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import com.kovac.app.sessions.UserSession;
import com.kovac.app.utils.Util;

import java.util.HashMap;
import java.util.Map;


public class SubmittedFragment extends Fragment {

    private OnSubmittedFragmentInteractionListener mListener;
    private Button buttonBack, buttonReferFriendOffer;
    private CheckBox cbSubmittedTerm;

    //Volley RequestQueue
    private RequestQueue requestQueue;

    UserSession userSession;

    public SubmittedFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Initializing the RequestQueue
        requestQueue = Volley.newRequestQueue(getContext());

        userSession = new UserSession(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_submitted, container, false);
        buttonBack = (Button) view.findViewById(R.id.btnBack);
        cbSubmittedTerm = (CheckBox) view.findViewById(R.id.cbSubmittedTerm);
        //buttonReferFriendOffer = (Button) view.findViewById(R.id.btnReferFriendOffer);

        //back to home screen
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userSession.clearUserSession();
                mListener.onSubmittedFragmentSelected("homeBack", null);
            }
        });

        cbSubmittedTerm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    callApi();
                }
            }
        });


        //Friend invite screen
       /* buttonReferFriendOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mListener.onSubmittedFragmentSelected("friendInvite", null);
            }
        });*/


        return view;
    }

    private void callApi(){
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
                        if (response.equalsIgnoreCase("\"Success\""))  {
                            //userSession.setUserId(response);
                            Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
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
                params.put(Util.FINANCIAL_ADVISER, "1");
                params.put(Util.EMP_PERSONAL_ADVISER, "Yes");
                params.put(Util.USERID, userSession.getUserId());
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
        if (context instanceof OnSubmittedFragmentInteractionListener) {
            mListener = (OnSubmittedFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnSubmittedFragmentInteractionListener");
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
    public interface OnSubmittedFragmentInteractionListener {
        // TODO: Update argument type and name
        void onSubmittedFragmentSelected(String action, Bundle bundle);
    }
}
