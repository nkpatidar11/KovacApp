package com.kovac.app.fragments;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
import com.android.volley.misc.Utils;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kovac.app.BuildConfig;
import com.kovac.app.MainActivity;
import com.kovac.app.R;
import com.kovac.app.sessions.UserSession;
import com.kovac.app.utils.Util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static android.R.attr.type;
import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

public class SignupIdentificationFragment extends Fragment {

    private OnSignupIdentificationFragmentInteractionListener mListener;
    private Button buttonBack, buttonNext, buttonTakePhoto, buttonSubmit;
    private ImageView imgIdentification, imgClearIdentification;

    protected static final int CAMERA_REQUEST = 0;
    protected static final int GALLERY_PICTURE = 1;
    private Intent pictureActionIntent = null;
    private static final int REQUEST_WRITE_STORAGE = 112;
    Bitmap bitmap;

    String selectedImagePath = null;
    String myBase64Image="";
    String fileName, userId;

    UserSession userSession;

    //Volley RequestQueue
    private RequestQueue requestQueue;

    public SignupIdentificationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Initializing the RequestQueue
        requestQueue = Volley.newRequestQueue(getContext());

        userSession = new UserSession(getContext());
        userId = userSession.getUserId();

        boolean hasPermission = (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
        if (!hasPermission) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_STORAGE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_signup_identification, container, false);

        imgIdentification = (ImageView) view.findViewById(R.id.imgIdentification);
        imgClearIdentification = (ImageView) view.findViewById(R.id.imgClearIdentification);

        buttonBack = (Button) view.findViewById(R.id.btnBack);
        buttonNext = (Button) view.findViewById(R.id.btnNext);
        buttonSubmit = (Button) view.findViewById(R.id.btnSubmit);
        buttonTakePhoto = (Button) view.findViewById(R.id.btnTakePhoto);

        //back to home screen
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onSignupIdentificationFragmentSelected("signupProfileRiskBack", null);
            }
        });


        buttonTakePhoto.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                boolean hasPermission = (ContextCompat.checkSelfPermission(getContext(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
                if (!hasPermission) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            REQUEST_WRITE_STORAGE);
                }

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
                fileName = "IMG_" + timeStamp + ".jpg";
                File f = new File(android.os.Environment.getExternalStorageDirectory(), fileName);
                //File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                /*Uri photoURI = Uri.fromFile(f);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);*/
                Uri photoURI = FileProvider.getUriForFile(getContext(), BuildConfig.APPLICATION_ID + ".provider",
                       f);

                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
                    intent.setClipData(ClipData.newRawUri("", photoURI));
                    intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION|Intent.FLAG_GRANT_READ_URI_PERMISSION);
                }
                startActivityForResult(intent, CAMERA_REQUEST);
            }
        });


       /* //Next screen
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onSignupIdentificationFragmentSelected("signupDiclaimer", null);
            }
        });*/

       buttonSubmit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               //mListener.onSignupIdentificationFragmentSelected("signupSubmitted", null);
               callApi();
           }
       });

        imgClearIdentification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myBase64Image = "";
                imgIdentification.setImageResource(R.drawable.identification_placeholder);
                imgClearIdentification.setVisibility(View.GONE);
                buttonSubmit.setVisibility(View.GONE);
                buttonTakePhoto.setVisibility(View.VISIBLE);
            }
        });

        return  view;
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

                        if (!response.equalsIgnoreCase("")) {
                            userSession.clearUserSession();
                            userSession.setUserId(userId);
                            mListener.onSignupIdentificationFragmentSelected("signupSubmitted", null);
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
                params.put(Util.USER_NAME, userSession.getUserName());
                params.put(Util.USERID, userSession.getUserId());
                params.put(Util.USER_EMAIL, userSession.getEmail());
                params.put(Util.USER_PASSWORD, userSession.getPassword());
                params.put(Util.USER_FNAME, userSession.getFirstName());
                params.put(Util.USER_LNAME, userSession.getSurname());
                params.put(Util.USER_MOBILENO, userSession.getMobile());
                params.put(Util.USER_GENDER, userSession.getGender());
                params.put(Util.USER_DOB, userSession.getDob());
                params.put(Util.USER_ADDRESS, userSession.getStreetAddress());
                params.put(Util.USER_SUBURB, userSession.getSuburb());
                params.put(Util.USER_STATE, userSession.getState());
                params.put(Util.USER_POSTCODE, userSession.getPostCode());
                params.put(Util.USER_MARITAL_STATUS, userSession.getMaritalStatus());
                params.put(Util.USER_DEPENDANT_CHILDREN, userSession.getDependantChildren());
                params.put(Util.USER_DEPENDANT_CHILDREN_DATA, userSession.getDependentChildrenData());
                params.put(Util.EMP_STATUS, userSession.getEmploymentStatus());
                params.put(Util.EMP_OCCUPATION, userSession.getOccupation());
                params.put(Util.EMP_TAX_FILE_NO, userSession.getTextFileNumber());
                params.put(Util.EMP_ANNUAL_INCOME, userSession.getAnnualIncome());
                params.put(Util.EMP_SUPER_FUND_NAME, userSession.getSuperFundNameData());
                params.put(Util.EMP_MEMBER_NO, userSession.getMemberNumberData());
                params.put(Util.EMP_CURRENT_VALUE, userSession.getCurrentValueData());
                //params.put(Util.EMP_INVESTMENT_OPTION, userSession.getInvestmentOption());
                //params.put(Util.EMP_SUPERANNUATION_STATUS, userSession.getSuperannuationStatus());
                params.put(Util.EMP_INSURANCE_COVER, userSession.getInsuranceCover());
                params.put(Util.EMP_SMOKER, userSession.getSmoker());
                params.put(Util.EMP_HEIGHT, userSession.getHeight());
                params.put(Util.EMP_WEIGHT, userSession.getWeight());
                params.put(Util.EMP_LIFE_INSURANCE, userSession.getLifeInsuranceStatus());
                params.put(Util.EMP_LIFE_INSURANCE_VALUE, userSession.getLifeInsuranceValue());
                params.put(Util.EMP_DISABILITY_INSURANCE, userSession.getDisabilityInsuranceStatus());
                params.put(Util.EMP_DISABILITY_INSURANCE_VALUE, userSession.getDisabilityInsuranceValue());
                params.put(Util.EMP_INCOME_PROTECTION, userSession.getIncomeProtectionStatus());
                params.put(Util.EMP_INCOME_PROTECTION_VALUE, userSession.getIncomeProtectionValue());
                params.put(Util.EMP_TRAUMA_INSURANCE, userSession.getTraumaInsuranceStatus());
                params.put(Util.EMP_TRAUMA_INSURANCE_VALUE, userSession.getTraumaInsuranceValue());
                params.put(Util.EMP_OWN_HOME, userSession.getOwnHome());
                params.put(Util.EMP_INVESTMENT_PROPERTY, userSession.getInvestmentProperty());
                params.put(Util.EMP_SHARE_PORTFOLIO, userSession.getSharePortfolio());
                params.put(Util.EMP_OTHER_ASSETS, userSession.getOtherAssets());
                params.put(Util.EMP_HOME_LOAN, userSession.getHomeLoan());
                params.put(Util.EMP_CREDIT_CARDS, userSession.getCreditCards());
                params.put(Util.EMP_INVESTMENT_LOANS, userSession.getInvestmentLoans());
                params.put(Util.EMP_OTHER_LIABILITIES, userSession.getOtherLiabilities());
                params.put(Util.EMP_RISK_PROFILE, userSession.getRiskProfile());
                params.put(Util.EMP_IDENTIFICATION, myBase64Image);
                params.put(Util.EMP_DECLAIMER, "Yes");
                params.put(Util.NEW, "1");
                Log.d("request data", params.toString());
                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        //Adding request the the queue
        requestQueue.add(stringRequest);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case REQUEST_WRITE_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    //reload my activity with permission granted or use the features what required the permission
                } else
                {
                    Toast.makeText(getContext(), "The app was not allowed to write to your storage. Hence, it cannot function properly. Please consider granting it this permission", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        bitmap = null;
        if (requestCode == CAMERA_REQUEST) {
            if (resultCode == RESULT_OK) {
                // successfully captured the image
                // display it in image view
                File f = new File(Environment.getExternalStorageDirectory().toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals(fileName)) {
                        f = temp;
                        break;
                    }
                }

                if (!f.exists()) {
                    Toast.makeText(getContext(), "Error while capturing image", Toast.LENGTH_LONG).show();

                    return;
                }

                try {
                    selectedImagePath = f.getAbsolutePath();

                    // Decode image size
                    /*BitmapFactory.Options o = new BitmapFactory.Options();
                    o.inJustDecodeBounds = true;
                    bitmap = BitmapFactory.decodeFile(selectedImagePath, o);*/
                    bitmap = BitmapFactory.decodeFile(selectedImagePath);

                    myBase64Image= Util.encodeToBase64(Util.scaleBitmap(bitmap, 480f, 800f), Bitmap.CompressFormat.JPEG,100);

                    imgIdentification.setImageBitmap(Util.scaleBitmap(bitmap, 480f, 800f));

                    /*// the new size we want to scale to
                    final int REQUIRED_SIZE = 600;

                    // Find the correct scale value. It should be the power of 2.
                    int width_tmp = o.outWidth, height_tmp = o.outHeight;
                    int scale = 1;
                    while (true) {
                        if (width_tmp < REQUIRED_SIZE && height_tmp < REQUIRED_SIZE) {
                            break;
                        }
                        width_tmp /= 2;
                        height_tmp /= 2;
                        scale *= 2;
                    }

                    // decode with inSampleSize
                    BitmapFactory.Options o2 = new BitmapFactory.Options();
                    o2.inSampleSize = scale;
                    Bitmap bitmapNew= BitmapFactory.decodeFile(selectedImagePath, o2);


                    myBase64Image= Util.encodeToBase64(bitmapNew, Bitmap.CompressFormat.JPEG,100);

                    imgIdentification.setImageBitmap(bitmapNew);*/
                    imgClearIdentification.setVisibility(View.VISIBLE);
                    buttonTakePhoto.setVisibility(View.GONE);
                    buttonSubmit.setVisibility(View.VISIBLE);
                    //storeImageTosdCard(bitmap);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else if (resultCode == RESULT_CANCELED) {
                imgClearIdentification.setVisibility(View.GONE);
                buttonTakePhoto.setVisibility(View.VISIBLE);
                buttonSubmit.setVisibility(View.GONE);
                // user cancelled Image capture
                Toast.makeText(getContext(), "User cancelled image capture", Toast.LENGTH_SHORT).show();
            } else {
                imgClearIdentification.setVisibility(View.GONE);
                buttonTakePhoto.setVisibility(View.VISIBLE);
                buttonSubmit.setVisibility(View.GONE);
                // failed to capture image
                Toast.makeText(getContext(), "Sorry! Failed to capture image", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSignupIdentificationFragmentInteractionListener) {
            mListener = (OnSignupIdentificationFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnSignupIdentificationFragmentInteractionListener");
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
    public interface OnSignupIdentificationFragmentInteractionListener {
        // TODO: Update argument type and name
        void onSignupIdentificationFragmentSelected(String action, Bundle bundle);
    }
}
