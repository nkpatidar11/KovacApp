package com.kovac.app.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.kovac.app.R;
import com.kovac.app.sessions.UserSession;
import com.kovac.app.utils.Util;

import java.util.Calendar;
import java.util.Date;

import static android.R.id.edit;

public class SignupPersonalDetailFragment extends Fragment {

    private OnSignupPersonalDetailFragmentInteractionListener onSignupPersonalDetailFragmentInteractionListener;
    private Spinner spGender, spState, spMaritalStatus, spDependantChildren;
    private Button buttonBack, buttonNext;
    private EditText etDob, etStretAddress, etSuburb, etPostCode, etMobile;
    private EditText etChildrenFirstName, etChildrenFirstDob, etChildrenSecondName, etChildrenSecondDob, etChildrenThirdName, etChildrenThirdDob, etChildrenFourthName, etChildrenFourthDob,
            etChildrenFifthName, etChildrenFifthDob, etChildrenSixthName, etChildrenSixthDob;
    private int year, month, day, hour, minute;
    private String dob;
    private LinearLayout lLChildrenFirst, lLChildrenSecond, lLChildrenThird, lLChildrenFourth, lLChildrenFifth, lLChildrenSixth;
    private TextView tvChildrenDetailLabel;

    String[] genderArray = {"", "Male", "Female"};
    String[] stateArray = {"", "ACT", "NSW", "NT", "QLD", "TAS", "VIC", "WA"};
    String[] maritalStatusArray = {"", "Single", "Married", "Defacto"};
    String[] dependantChildrenArray = {"0", "1", "2", "3", "4", "5", "6"};


    public SignupPersonalDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_signup_personal_detail, container, false);
        final UserSession userSession = new UserSession(getContext());

        buttonBack = (Button) view.findViewById(R.id.btnBack);
        buttonNext = (Button) view.findViewById(R.id.btnNext);

        //edit text field
        etMobile = (EditText) view.findViewById(R.id.etMobile);
        etDob = (EditText) view.findViewById(R.id.etDob);
        etStretAddress = (EditText) view.findViewById(R.id.etStreetAddress);
        etSuburb = (EditText) view.findViewById(R.id.etSuburb);
        etPostCode = (EditText) view.findViewById(R.id.etPostCode);

        /**
         * Children detail
         */
        lLChildrenFirst = (LinearLayout) view.findViewById(R.id.lLChildrenFirst);
        lLChildrenSecond = (LinearLayout) view.findViewById(R.id.lLChildrenSecond);
        lLChildrenThird = (LinearLayout) view.findViewById(R.id.lLChildrenThird);
        lLChildrenFourth = (LinearLayout) view.findViewById(R.id.lLChildrenFourth);
        lLChildrenFifth = (LinearLayout) view.findViewById(R.id.lLChildrenFifth);
        lLChildrenSixth = (LinearLayout) view.findViewById(R.id.lLChildrenSixth);

        etChildrenFirstName = (EditText) view.findViewById(R.id.etChildrenFirstName);
        etChildrenFirstDob = (EditText) view.findViewById(R.id.etChildrenFirstDob);
        etChildrenSecondName = (EditText) view.findViewById(R.id.etChildrenSecondName);
        etChildrenSecondDob = (EditText) view.findViewById(R.id.etChildrenSecondDob);
        etChildrenThirdName = (EditText) view.findViewById(R.id.etChildrenThirdName);
        etChildrenThirdDob = (EditText) view.findViewById(R.id.etChildrenThirdDob);
        etChildrenFourthName = (EditText) view.findViewById(R.id.etChildrenFourthName);
        etChildrenFourthDob = (EditText) view.findViewById(R.id.etChildrenFourthDob);
        etChildrenFifthName = (EditText) view.findViewById(R.id.etChildrenFifthName);
        etChildrenFifthDob = (EditText) view.findViewById(R.id.etChildrenFifthDob);
        etChildrenSixthName = (EditText) view.findViewById(R.id.etChildrenSixthName);
        etChildrenSixthDob = (EditText) view.findViewById(R.id.etChildrenSixthDob);

        tvChildrenDetailLabel = (TextView) view.findViewById(R.id.tvChildrenDetailLabel);

        //Gender spinner
        spGender = (Spinner) view.findViewById(R.id.spGender);
        final ArrayAdapter<String> spGenderAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, genderArray);
        spGenderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGender.setAdapter(spGenderAdapter);

        //State Spinner
        spState = (Spinner) view.findViewById(R.id.spState);
        ArrayAdapter<String> spStateAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, stateArray);
        spStateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spState.setAdapter(spStateAdapter);


        //Marital status Spinner
        spMaritalStatus = (Spinner) view.findViewById(R.id.spMaritalStatus);
        final ArrayAdapter<String> spMaritalStatusAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, maritalStatusArray);
        spMaritalStatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMaritalStatus.setAdapter(spMaritalStatusAdapter);

        //Dependant Children Spinner
        spDependantChildren = (Spinner) view.findViewById(R.id.spDependantChildren);
        ArrayAdapter<String> spDependantChildrenAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, dependantChildrenArray);
        spDependantChildrenAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDependantChildren.setAdapter(spDependantChildrenAdapter);

        //if set in session then value set in EditText or Spinner
        if (userSession.getDob().length() > 0){
            etMobile.setText(userSession.getMobile());
            spGender.setSelection(((ArrayAdapter<String>)spGender.getAdapter()).getPosition(userSession.getGender()));
            etDob.setText(userSession.getDob());
            etStretAddress.setText(userSession.getStreetAddress());
            etSuburb.setText(userSession.getSuburb());
            spState.setSelection(((ArrayAdapter<String>)spState.getAdapter()).getPosition(userSession.getState()));
            etPostCode.setText(userSession.getPostCode());
            spMaritalStatus.setSelection(((ArrayAdapter<String>)spMaritalStatus.getAdapter()).getPosition(userSession.getMaritalStatus()));
            spDependantChildren.setSelection(((ArrayAdapter<String>)spDependantChildren.getAdapter()).getPosition(userSession.getDependantChildren()));

            if (userSession.getDependantChildren().equals("0")){
                tvChildrenDetailLabel.setVisibility(View.GONE);
                lLChildrenFirst.setVisibility(View.GONE);
                lLChildrenSecond.setVisibility(View.GONE);
                lLChildrenThird.setVisibility(View.GONE);
                lLChildrenFourth.setVisibility(View.GONE);
                lLChildrenFifth.setVisibility(View.GONE);
                lLChildrenSixth.setVisibility(View.GONE);
            }else if (userSession.getDependantChildren().equals("1")) {
                tvChildrenDetailLabel.setVisibility(View.VISIBLE);
                lLChildrenFirst.setVisibility(View.VISIBLE);
                lLChildrenSecond.setVisibility(View.GONE);
                lLChildrenThird.setVisibility(View.GONE);
                lLChildrenFourth.setVisibility(View.GONE);
                lLChildrenFifth.setVisibility(View.GONE);
                lLChildrenSixth.setVisibility(View.GONE);
            }else if (userSession.getDependantChildren().equals("2")) {
                tvChildrenDetailLabel.setVisibility(View.VISIBLE);
                lLChildrenFirst.setVisibility(View.VISIBLE);
                lLChildrenSecond.setVisibility(View.VISIBLE);
                lLChildrenThird.setVisibility(View.GONE);
                lLChildrenFourth.setVisibility(View.GONE);
                lLChildrenFifth.setVisibility(View.GONE);
                lLChildrenSixth.setVisibility(View.GONE);
            }else if (userSession.getDependantChildren().equals("3")) {
                tvChildrenDetailLabel.setVisibility(View.VISIBLE);
                lLChildrenFirst.setVisibility(View.VISIBLE);
                lLChildrenSecond.setVisibility(View.VISIBLE);
                lLChildrenThird.setVisibility(View.VISIBLE);
                lLChildrenFourth.setVisibility(View.GONE);
                lLChildrenFifth.setVisibility(View.GONE);
                lLChildrenSixth.setVisibility(View.GONE);
            }else if (userSession.getDependantChildren().equals("4")) {
                tvChildrenDetailLabel.setVisibility(View.VISIBLE);
                lLChildrenFirst.setVisibility(View.VISIBLE);
                lLChildrenSecond.setVisibility(View.VISIBLE);
                lLChildrenThird.setVisibility(View.VISIBLE);
                lLChildrenFourth.setVisibility(View.VISIBLE);
                lLChildrenFifth.setVisibility(View.GONE);
                lLChildrenSixth.setVisibility(View.GONE);
            }else if (userSession.getDependantChildren().equals("5")) {
                tvChildrenDetailLabel.setVisibility(View.VISIBLE);
                lLChildrenFirst.setVisibility(View.VISIBLE);
                lLChildrenSecond.setVisibility(View.VISIBLE);
                lLChildrenThird.setVisibility(View.VISIBLE);
                lLChildrenFourth.setVisibility(View.VISIBLE);
                lLChildrenFifth.setVisibility(View.VISIBLE);
                lLChildrenSixth.setVisibility(View.GONE);
            }else if (userSession.getDependantChildren().equals("6")) {
                tvChildrenDetailLabel.setVisibility(View.VISIBLE);
                lLChildrenFirst.setVisibility(View.VISIBLE);
                lLChildrenSecond.setVisibility(View.VISIBLE);
                lLChildrenThird.setVisibility(View.VISIBLE);
                lLChildrenFourth.setVisibility(View.VISIBLE);
                lLChildrenFifth.setVisibility(View.VISIBLE);
                lLChildrenSixth.setVisibility(View.VISIBLE);
            }else {
                tvChildrenDetailLabel.setVisibility(View.GONE);
                lLChildrenFirst.setVisibility(View.GONE);
                lLChildrenSecond.setVisibility(View.GONE);
                lLChildrenThird.setVisibility(View.GONE);
                lLChildrenFourth.setVisibility(View.GONE);
                lLChildrenFifth.setVisibility(View.GONE);
                lLChildrenSixth.setVisibility(View.GONE);
            }

            if(userSession.getFirstChildrenName() != null)
                etChildrenFirstName.setText(userSession.getFirstChildrenName());

            if (userSession.getFirstChildrenDob() != null)
                etChildrenFirstDob.setText(userSession.getFirstChildrenDob());

            if(userSession.getSecondChildrenName() != null)
                etChildrenSecondName.setText(userSession.getSecondChildrenName());

            if (userSession.getSecondChildrenDob() != null)
                etChildrenSecondDob.setText(userSession.getSecondChildrenDob());

            if(userSession.getThirdChildrenName() != null)
                etChildrenThirdName.setText(userSession.getThirdChildrenName());

            if (userSession.getThirdChildrenDob() != null)
                etChildrenThirdDob.setText(userSession.getThirdChildrenDob());

            if (userSession.getFourthChildrenName() != null)
                etChildrenFourthName.setText(userSession.getFourthChildrenName());

            if (userSession.getFourthChildrenDob() != null)
                etChildrenFourthDob.setText(userSession.getFourthChildrenDob());

            if (userSession.getFifthChildrenName() != null)
                etChildrenFifthName.setText(userSession.getFifthChildrenName());

            if (userSession.getFifthChildrenDob() != null)
                etChildrenFifthDob.setText(userSession.getFifthChildrenDob());

            if (userSession.getSixthChildrenName() != null)
                etChildrenSixthName.setText(userSession.getSixthChildrenName());

            if (userSession.getSixthChildrenDob() != null)
                etChildrenSixthDob.setText(userSession.getSixthChildrenDob());
        }

        spDependantChildren.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                //Toast.makeText(getContext(), "select "+ dependantChildrenArray[position], Toast.LENGTH_SHORT).show();
                if (dependantChildrenArray[position].equals("0")){
                    tvChildrenDetailLabel.setVisibility(View.GONE);
                    lLChildrenFirst.setVisibility(View.GONE);
                    lLChildrenSecond.setVisibility(View.GONE);
                    lLChildrenThird.setVisibility(View.GONE);
                    lLChildrenFourth.setVisibility(View.GONE);
                    lLChildrenFifth.setVisibility(View.GONE);
                    lLChildrenSixth.setVisibility(View.GONE);
                }else if (dependantChildrenArray[position].equals("1")) {
                    tvChildrenDetailLabel.setVisibility(View.VISIBLE);
                    lLChildrenFirst.setVisibility(View.VISIBLE);
                    lLChildrenSecond.setVisibility(View.GONE);
                    lLChildrenThird.setVisibility(View.GONE);
                    lLChildrenFourth.setVisibility(View.GONE);
                    lLChildrenFifth.setVisibility(View.GONE);
                    lLChildrenSixth.setVisibility(View.GONE);
                }else if (dependantChildrenArray[position].equals("2")) {
                    tvChildrenDetailLabel.setVisibility(View.VISIBLE);
                    lLChildrenFirst.setVisibility(View.VISIBLE);
                    lLChildrenSecond.setVisibility(View.VISIBLE);
                    lLChildrenThird.setVisibility(View.GONE);
                    lLChildrenFourth.setVisibility(View.GONE);
                    lLChildrenFifth.setVisibility(View.GONE);
                    lLChildrenSixth.setVisibility(View.GONE);
                }else if (dependantChildrenArray[position].equals("3")) {
                    tvChildrenDetailLabel.setVisibility(View.VISIBLE);
                    lLChildrenFirst.setVisibility(View.VISIBLE);
                    lLChildrenSecond.setVisibility(View.VISIBLE);
                    lLChildrenThird.setVisibility(View.VISIBLE);
                    lLChildrenFourth.setVisibility(View.GONE);
                    lLChildrenFifth.setVisibility(View.GONE);
                    lLChildrenSixth.setVisibility(View.GONE);
                }else if (dependantChildrenArray[position].equals("4")) {
                    tvChildrenDetailLabel.setVisibility(View.VISIBLE);
                    lLChildrenFirst.setVisibility(View.VISIBLE);
                    lLChildrenSecond.setVisibility(View.VISIBLE);
                    lLChildrenThird.setVisibility(View.VISIBLE);
                    lLChildrenFourth.setVisibility(View.VISIBLE);
                    lLChildrenFifth.setVisibility(View.GONE);
                    lLChildrenSixth.setVisibility(View.GONE);
                }else if (dependantChildrenArray[position].equals("5")) {
                    tvChildrenDetailLabel.setVisibility(View.VISIBLE);
                    lLChildrenFirst.setVisibility(View.VISIBLE);
                    lLChildrenSecond.setVisibility(View.VISIBLE);
                    lLChildrenThird.setVisibility(View.VISIBLE);
                    lLChildrenFourth.setVisibility(View.VISIBLE);
                    lLChildrenFifth.setVisibility(View.VISIBLE);
                    lLChildrenSixth.setVisibility(View.GONE);
                }else if (dependantChildrenArray[position].equals("6")) {
                    tvChildrenDetailLabel.setVisibility(View.VISIBLE);
                    lLChildrenFirst.setVisibility(View.VISIBLE);
                    lLChildrenSecond.setVisibility(View.VISIBLE);
                    lLChildrenThird.setVisibility(View.VISIBLE);
                    lLChildrenFourth.setVisibility(View.VISIBLE);
                    lLChildrenFifth.setVisibility(View.VISIBLE);
                    lLChildrenSixth.setVisibility(View.VISIBLE);
                }else {
                    tvChildrenDetailLabel.setVisibility(View.GONE);
                    lLChildrenFirst.setVisibility(View.GONE);
                    lLChildrenSecond.setVisibility(View.GONE);
                    lLChildrenThird.setVisibility(View.GONE);
                    lLChildrenFourth.setVisibility(View.GONE);
                    lLChildrenFifth.setVisibility(View.GONE);
                    lLChildrenSixth.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        etDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((EditText)v).setInputType(InputType.TYPE_NULL);
                final Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                hour = calendar.get(Calendar.HOUR_OF_DAY);
                minute = calendar.get(Calendar.MINUTE);
                //final String[] MONTH_ARRAY = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
                final String[] MONTH_ARRAY = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};

                final DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker datePicker, int year1, int monthOfYear, int dayOfMonth) {
                        //etDob = MONTH_ARRAY[monthOfYear] + " " +dayOfMonth + ", " + year1;
                        dob = dayOfMonth + "/" + MONTH_ARRAY[monthOfYear] + "/" + year1;
                        etDob.setText(dob);
                        //day_time = year1 + "-" + (monthOfYear+1) + "-" + dayOfMonth +" " + hour + ":" + minute + ":00";
                    }
                }, year, month, day);
                Date newDate = calendar.getTime();
                datePickerDialog.getDatePicker().setMaxDate(newDate.getTime());
                datePickerDialog.show();

            }
        });

        setDateEditText(etChildrenFirstDob);
        setDateEditText(etChildrenSecondDob);
        setDateEditText(etChildrenThirdDob);
        setDateEditText(etChildrenFourthDob);
        setDateEditText(etChildrenFifthDob);
        setDateEditText(etChildrenSixthDob);


        //back to home screen
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSignupPersonalDetailFragmentInteractionListener.onSignupPersonalDetailFragmentSelected("signupLoginBack", null);
            }
        });


        //Next screen
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = 0;

                String gender = spGender.getSelectedItem().toString();
                dob = etDob.getText().toString();
                String mobileNo = etMobile.getText().toString();
                String streetAddress = etStretAddress.getText().toString();
                String suburb = etSuburb.getText().toString();
                String state = spState.getSelectedItem().toString();
                String postCode = etPostCode.getText().toString();
                String maritalStatus = spMaritalStatus.getSelectedItem().toString();
                String dependentChildren = spDependantChildren.getSelectedItem().toString();


               /* if (gender.trim().length() == 0){
                   // ((TextView)spGender.getChildAt(0)).setError("Gender is required!");
                    ((TextView) spGender.getSelectedView()).setError("Gender is Required");
                    count++;
                }*/

                if (mobileNo.trim().length() == 0){
                    etMobile.setError( "Mobile number is required!" );
                    count++;
                }

                if (dob.trim().length() == 0){
                    etDob.setError( "Date of birth is required!" );
                    count++;
                }

                if (streetAddress.trim().length() == 0){
                    etStretAddress.setError( "Street address is required!" );
                    count++;
                }

                if (suburb.trim().length() == 0){
                    etSuburb.setError( "Suburb is required!" );
                    count++;
                }

                /*if (state.trim().length() == 0){
                    // ((TextView)spState.getChildAt(0)).setError("State is required!");
                     ((TextView) spState.getSelectedView()).setError("State is Required");
                    count++;
                }*/

                if (postCode.trim().length() == 0){
                    etPostCode.setError( "Postcode is required!" );
                    count++;
                }
                String dependentChildrenData = "";
                String firstChildrenName = etChildrenFirstName.getText().toString();
                String firstChildrenDob = etChildrenFirstDob.getText().toString();
                String secondChildrenName = etChildrenSecondName.getText().toString();
                String secondChildrenDob = etChildrenSecondDob.getText().toString();
                String thirdChildrenName = etChildrenThirdName.getText().toString();
                String thirdChildrenDob = etChildrenThirdDob.getText().toString();
                String fourthChildrenName = etChildrenFourthName.getText().toString();
                String fourthChildrenDob = etChildrenFourthDob.getText().toString();
                String fifthChildrenName = etChildrenFifthName.getText().toString();
                String fifthChildrenDob = etChildrenFifthDob.getText().toString();
                String sixthChildrenName = etChildrenSixthName.getText().toString();
                String sixthChildrenDob = etChildrenSixthDob.getText().toString();

                if (firstChildrenName.trim().length() > 0){
                    dependentChildrenData += firstChildrenName;
                    if (firstChildrenDob.trim().length() > 0){
                        dependentChildrenData += "-"+ firstChildrenDob;
                    }
                }

                if (secondChildrenName.trim().length() > 0){
                    dependentChildrenData += "," + secondChildrenName;
                    if (secondChildrenDob.trim().length() > 0){
                        dependentChildrenData += "-"+ secondChildrenDob;
                    }
                }

                if (thirdChildrenName.trim().length() > 0){
                    dependentChildrenData += "," +thirdChildrenName;
                    if (thirdChildrenDob.trim().length() > 0){
                        dependentChildrenData += "-"+ thirdChildrenDob;
                    }
                }

                if (fourthChildrenName.trim().length() > 0){
                    dependentChildrenData += "," +fourthChildrenName;
                    if (fourthChildrenDob.trim().length() > 0){
                        dependentChildrenData += "-"+ fourthChildrenDob;
                    }
                }

                if (fifthChildrenName.trim().length() > 0){
                    dependentChildrenData += "," +fifthChildrenName;
                    if (fifthChildrenDob.trim().length() > 0){
                        dependentChildrenData += "-"+ fifthChildrenDob;
                    }
                }

                if (sixthChildrenName.trim().length() > 0){
                    dependentChildrenData += "," +sixthChildrenName;
                    if (sixthChildrenDob.trim().length() > 0){
                        dependentChildrenData += "-"+ sixthChildrenDob;
                    }
                }


                /*if (maritalStatus.trim().length() == 0){
                    // ((TextView)spMaritalStatus.getChildAt(0)).setError("Marital Status is required!");
                    ((TextView) spMaritalStatus.getSelectedView()).setError("Marital Status is Required");
                    count++;
                }*/

                Log.d("number of count is ", String.valueOf(count));
                Log.d("data children ", dependentChildrenData);
                if (count == 0){
                    //set form value in session
                    userSession.setMobile(mobileNo);
                    userSession.setGender(gender);
                    userSession.setDob(dob);
                    userSession.setStreetAddress(streetAddress);
                    userSession.setSuburb(suburb);
                    userSession.setState(state);
                    userSession.setPostCode(postCode);
                    userSession.setMaritalStatus(maritalStatus);
                    userSession.setDependantChildren(dependentChildren);
                    userSession.setDependentChildrenData(dependentChildrenData);
                    userSession.setFirstChildrenName(firstChildrenName);
                    userSession.setFirstChildrenDob(firstChildrenDob);
                    userSession.setSecondChildrenName(secondChildrenName);
                    userSession.setSecondChildrenDob(secondChildrenDob);
                    userSession.setThirdChildrenName(thirdChildrenName);
                    userSession.setThirdChildrenDob(thirdChildrenDob);
                    userSession.setFourthChildrenName(fourthChildrenName);
                    userSession.setFourthChildrenDob(fourthChildrenDob);
                    userSession.setFifthChildrenName(fifthChildrenName);
                    userSession.setFifthChildrenDob(fifthChildrenDob);
                    userSession.setSixthChildrenName(sixthChildrenName);
                    userSession.setSixthChildrenDob(sixthChildrenDob);

                    onSignupPersonalDetailFragmentInteractionListener.onSignupPersonalDetailFragmentSelected("signupEmploymentDetail", null);
                }else {
                    return;
                }
            }
        });

        return view;
    }

    void setDateEditText(final EditText editText){
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((EditText)v).setInputType(InputType.TYPE_NULL);
                final Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                hour = calendar.get(Calendar.HOUR_OF_DAY);
                minute = calendar.get(Calendar.MINUTE);
                //final String[] MONTH_ARRAY = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
                final String[] MONTH_ARRAY = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};

                final DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker datePicker, int year1, int monthOfYear, int dayOfMonth) {
                        //etDob = MONTH_ARRAY[monthOfYear] + " " +dayOfMonth + ", " + year1;
                        dob = dayOfMonth + "/" + MONTH_ARRAY[monthOfYear] + "/" + year1;
                        editText.setText(dob);
                        //day_time = year1 + "-" + (monthOfYear+1) + "-" + dayOfMonth +" " + hour + ":" + minute + ":00";
                    }
                }, year, month, day);
                Date newDate = calendar.getTime();
                datePickerDialog.getDatePicker().setMaxDate(newDate.getTime());
                datePickerDialog.show();

            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSignupPersonalDetailFragmentInteractionListener) {
            onSignupPersonalDetailFragmentInteractionListener = (OnSignupPersonalDetailFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnSignupPersonalDetailFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onSignupPersonalDetailFragmentInteractionListener = null;
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
    public interface OnSignupPersonalDetailFragmentInteractionListener {
        // TODO: Update argument type and name
        void onSignupPersonalDetailFragmentSelected(String action, Bundle bundle);
    }
}
