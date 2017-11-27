package com.kovac.app.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.kovac.app.R;
import com.kovac.app.sessions.UserSession;

import static com.kovac.app.R.id.etChildrenFirstName;
import static com.kovac.app.R.id.switchCurrently;

public class SignupSuperannuationDetailFragment extends Fragment {

    private OnSignupSuperannuationDetailFragmentInteractionListener onSignupSuperannuationDetailFragmentInteractionListener;
    private Spinner spInvestmentOption;
    private Button buttonBack, buttonNext, buttonAdd, buttonRemove;
    private EditText etSuperNameFund, etMemberNumber, etCurrentValue, etSuperNameFund1, etMemberNumber1, etCurrentValue1, etSuperNameFund2, etMemberNumber2, etCurrentValue2,
            etSuperNameFund3, etMemberNumber3, etCurrentValue3, etSuperNameFund4, etMemberNumber4, etCurrentValue4, etSuperNameFund5, etMemberNumber5, etCurrentValue5,
            etSuperNameFund6, etMemberNumber6, etCurrentValue6, etSuperNameFund7, etMemberNumber7, etCurrentValue7;
    private CheckBox cbSuperannuationStatus;
    private RelativeLayout rLSuperannuation, rLSuperannuation1, rLSuperannuation2, rLSuperannuation3, rLSuperannuation4, rLSuperannuation5, rLSuperannuation6, rLSuperannuation7;

    String[] investmentOptionArray = {"", "Concervative", "Balanced", "Growth", "High Growth"};
    int numberOfLayout = 0;

    public SignupSuperannuationDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup_superannuation_detail, container, false);

        buttonBack = (Button) view.findViewById(R.id.btnBack);
        buttonNext = (Button) view.findViewById(R.id.btnNext);
        buttonAdd = (Button) view.findViewById(R.id.btnAdd);
        buttonRemove = (Button) view.findViewById(R.id.btnRemove);

        etSuperNameFund = (EditText) view.findViewById(R.id.etSuperFundName);
        etMemberNumber = (EditText) view.findViewById(R.id.etMemberNumber);
        etCurrentValue = (EditText) view.findViewById(R.id.etCurrentValue);

        //dynamic field
        etSuperNameFund1 = (EditText) view.findViewById(R.id.etSuperFundName1);
        etMemberNumber1 = (EditText) view.findViewById(R.id.etMemberNumber1);
        etCurrentValue1 = (EditText) view.findViewById(R.id.etCurrentValue1);

        etSuperNameFund2 = (EditText) view.findViewById(R.id.etSuperFundName2);
        etMemberNumber2 = (EditText) view.findViewById(R.id.etMemberNumber2);
        etCurrentValue2 = (EditText) view.findViewById(R.id.etCurrentValue2);

        etSuperNameFund3 = (EditText) view.findViewById(R.id.etSuperFundName3);
        etMemberNumber3 = (EditText) view.findViewById(R.id.etMemberNumber3);
        etCurrentValue3 = (EditText) view.findViewById(R.id.etCurrentValue3);

        etSuperNameFund4 = (EditText) view.findViewById(R.id.etSuperFundName4);
        etMemberNumber4 = (EditText) view.findViewById(R.id.etMemberNumber4);
        etCurrentValue4 = (EditText) view.findViewById(R.id.etCurrentValue4);

        etSuperNameFund5 = (EditText) view.findViewById(R.id.etSuperFundName5);
        etMemberNumber5 = (EditText) view.findViewById(R.id.etMemberNumber5);
        etCurrentValue5 = (EditText) view.findViewById(R.id.etCurrentValue5);

        etSuperNameFund6 = (EditText) view.findViewById(R.id.etSuperFundName6);
        etMemberNumber6 = (EditText) view.findViewById(R.id.etMemberNumber6);
        etCurrentValue6 = (EditText) view.findViewById(R.id.etCurrentValue6);

        etSuperNameFund7 = (EditText) view.findViewById(R.id.etSuperFundName7);
        etMemberNumber7 = (EditText) view.findViewById(R.id.etMemberNumber7);
        etCurrentValue7 = (EditText) view.findViewById(R.id.etCurrentValue7);

        //cbSuperannuationStatus = (CheckBox) view.findViewById(R.id.cbSuperannuationStatus);

        rLSuperannuation = (RelativeLayout) view.findViewById(R.id.rLSuperannuation);
        rLSuperannuation1 = (RelativeLayout) view.findViewById(R.id.rLSuperannuation1);
        rLSuperannuation2 = (RelativeLayout) view.findViewById(R.id.rLSuperannuation2);
        rLSuperannuation3 = (RelativeLayout) view.findViewById(R.id.rLSuperannuation3);
        rLSuperannuation4 = (RelativeLayout) view.findViewById(R.id.rLSuperannuation4);
        rLSuperannuation5 = (RelativeLayout) view.findViewById(R.id.rLSuperannuation5);
        rLSuperannuation6 = (RelativeLayout) view.findViewById(R.id.rLSuperannuation6);
        rLSuperannuation7 = (RelativeLayout) view.findViewById(R.id.rLSuperannuation7);


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 if (numberOfLayout < 7){
                     numberOfLayout++;
                     dynamicLayoutManager();
                 } else {
                     Toast.makeText(getContext(), "You have reached a maximum number of investment declaration.", Toast.LENGTH_SHORT).show();
                 }
                //Toast.makeText(getContext(), "numberOfLayout " + numberOfLayout, Toast.LENGTH_LONG).show();
            }
        });

        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 if (numberOfLayout > 0){
                     numberOfLayout--;
                     dynamicLayoutManager();
                 }
                 //Toast.makeText(getContext(), "numberOfLayout " + numberOfLayout, Toast.LENGTH_LONG).show();
            }
        });

        //Employment status spinner
        /*spInvestmentOption = (Spinner) view.findViewById(R.id.spInvestmentOption);
        ArrayAdapter<String> spInvestmentOptionAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, investmentOptionArray);
        spInvestmentOptionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spInvestmentOption.setAdapter(spInvestmentOptionAdapter);*/

        //back to home screen
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSignupSuperannuationDetailFragmentInteractionListener.onSignupSuperannuationDetailFragmentSelected("signupEmploymentBack", null);
            }
        });

        // set field value in session
        final UserSession userSession = new UserSession(getContext());
        //Toast.makeText(getContext(), "email is : " + userSession.getEmail(), Toast.LENGTH_SHORT).show();
        if(userSession.getSuperFundName().trim().length() > 0){
            //spInvestmentOption.setSelection(((ArrayAdapter<String>)spInvestmentOption.getAdapter()).getPosition(userSession.getInvestmentOption()));
            etSuperNameFund.setText(userSession.getSuperFundName());
            etMemberNumber.setText(userSession.getMemberNumber());
            etCurrentValue.setText(userSession.getCurrentValue());

            numberOfLayout = Integer.parseInt(userSession.getNumberOfLayout());
           /* if (userSession.getSuperannuationStatus().equals("Yes"))
                cbSuperannuationStatus.setChecked(true);
            else
                cbSuperannuationStatus.setChecked(false);*/

            if (userSession.getNumberOfLayout().equals("1")){
                rLSuperannuation1.setVisibility(View.VISIBLE);
                rLSuperannuation2.setVisibility(View.GONE);
                rLSuperannuation3.setVisibility(View.GONE);
                rLSuperannuation4.setVisibility(View.GONE);
                rLSuperannuation5.setVisibility(View.GONE);
                rLSuperannuation6.setVisibility(View.GONE);
                rLSuperannuation7.setVisibility(View.GONE);
            }  else if (userSession.getNumberOfLayout().equals("2")){
                rLSuperannuation1.setVisibility(View.VISIBLE);
                rLSuperannuation2.setVisibility(View.VISIBLE);
                rLSuperannuation3.setVisibility(View.GONE);
                rLSuperannuation4.setVisibility(View.GONE);
                rLSuperannuation5.setVisibility(View.GONE);
                rLSuperannuation6.setVisibility(View.GONE);
                rLSuperannuation7.setVisibility(View.GONE);
            }  else if (userSession.getNumberOfLayout().equals("3")){
                rLSuperannuation1.setVisibility(View.VISIBLE);
                rLSuperannuation2.setVisibility(View.VISIBLE);
                rLSuperannuation3.setVisibility(View.VISIBLE);
                rLSuperannuation4.setVisibility(View.GONE);
                rLSuperannuation5.setVisibility(View.GONE);
                rLSuperannuation6.setVisibility(View.GONE);
                rLSuperannuation7.setVisibility(View.GONE);
            } else if (userSession.getNumberOfLayout().equals("4")){
                rLSuperannuation1.setVisibility(View.VISIBLE);
                rLSuperannuation2.setVisibility(View.VISIBLE);
                rLSuperannuation3.setVisibility(View.VISIBLE);
                rLSuperannuation4.setVisibility(View.VISIBLE);
                rLSuperannuation5.setVisibility(View.GONE);
                rLSuperannuation6.setVisibility(View.GONE);
                rLSuperannuation7.setVisibility(View.GONE);
            } else if (userSession.getNumberOfLayout().equals("5")){
                rLSuperannuation1.setVisibility(View.VISIBLE);
                rLSuperannuation2.setVisibility(View.VISIBLE);
                rLSuperannuation3.setVisibility(View.VISIBLE);
                rLSuperannuation4.setVisibility(View.VISIBLE);
                rLSuperannuation5.setVisibility(View.VISIBLE);
                rLSuperannuation6.setVisibility(View.GONE);
                rLSuperannuation7.setVisibility(View.GONE);
            } else if (userSession.getNumberOfLayout().equals("6")){
                rLSuperannuation1.setVisibility(View.VISIBLE);
                rLSuperannuation2.setVisibility(View.VISIBLE);
                rLSuperannuation3.setVisibility(View.VISIBLE);
                rLSuperannuation4.setVisibility(View.VISIBLE);
                rLSuperannuation5.setVisibility(View.VISIBLE);
                rLSuperannuation6.setVisibility(View.VISIBLE);
                rLSuperannuation7.setVisibility(View.GONE);
            } else if (userSession.getNumberOfLayout().equals("7")){
                rLSuperannuation1.setVisibility(View.VISIBLE);
                rLSuperannuation2.setVisibility(View.VISIBLE);
                rLSuperannuation3.setVisibility(View.VISIBLE);
                rLSuperannuation4.setVisibility(View.VISIBLE);
                rLSuperannuation5.setVisibility(View.VISIBLE);
                rLSuperannuation6.setVisibility(View.VISIBLE);
                rLSuperannuation7.setVisibility(View.VISIBLE);
            } else {
                rLSuperannuation1.setVisibility(View.GONE);
                rLSuperannuation2.setVisibility(View.GONE);
                rLSuperannuation3.setVisibility(View.GONE);
                rLSuperannuation4.setVisibility(View.GONE);
                rLSuperannuation5.setVisibility(View.GONE);
                rLSuperannuation6.setVisibility(View.GONE);
                rLSuperannuation7.setVisibility(View.GONE);
            }

            if(userSession.getSuperFundName1() != null) {
                etSuperNameFund1.setText(userSession.getSuperFundName1());
                etMemberNumber1.setText(userSession.getMemberNumber1());
                etCurrentValue1.setText(userSession.getCurrentValue1());
            }

            if(userSession.getSuperFundName2() != null) {
                etSuperNameFund2.setText(userSession.getSuperFundName2());
                etMemberNumber2.setText(userSession.getMemberNumber2());
                etCurrentValue2.setText(userSession.getCurrentValue2());
            }

            if(userSession.getSuperFundName3() != null) {
                etSuperNameFund3.setText(userSession.getSuperFundName3());
                etMemberNumber3.setText(userSession.getMemberNumber3());
                etCurrentValue3.setText(userSession.getCurrentValue3());
            }

            if(userSession.getSuperFundName4() != null) {
                etSuperNameFund4.setText(userSession.getSuperFundName4());
                etMemberNumber4.setText(userSession.getMemberNumber4());
                etCurrentValue4.setText(userSession.getCurrentValue4());
            }

            if(userSession.getSuperFundName() != null) {
                etSuperNameFund5.setText(userSession.getSuperFundName5());
                etMemberNumber5.setText(userSession.getMemberNumber5());
                etCurrentValue5.setText(userSession.getCurrentValue5());
            }

            if(userSession.getSuperFundName5() != null) {
                etSuperNameFund5.setText(userSession.getSuperFundName5());
                etMemberNumber5.setText(userSession.getMemberNumber5());
                etCurrentValue5.setText(userSession.getCurrentValue5());
            }

            if(userSession.getSuperFundName6() != null) {
                etSuperNameFund6.setText(userSession.getSuperFundName6());
                etMemberNumber6.setText(userSession.getMemberNumber6());
                etCurrentValue6.setText(userSession.getCurrentValue6());
            }

            if(userSession.getSuperFundName7() != null) {
                etSuperNameFund7.setText(userSession.getSuperFundName7());
                etMemberNumber7.setText(userSession.getMemberNumber7());
                etCurrentValue7.setText(userSession.getCurrentValue7());
            }

        }

        //Next screen
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = 0;

                String superNameFund = etSuperNameFund.getText().toString();
                String memberNumber = etMemberNumber.getText().toString();
                String currentValue = etCurrentValue.getText().toString();
                //String investmentOption = spInvestmentOption.getSelectedItem().toString();
                //String supernnautionStatus = (cbSuperannuationStatus.isChecked())? "Yes" : "No";


                if (superNameFund.trim().length() == 0){
                    etSuperNameFund.setError( "Super name fund is required!" );
                    count++;
                }

                if (memberNumber.trim().length() == 0){
                    etMemberNumber.setError( "Member number is required!" );
                    count++;
                }

                if (currentValue.trim().length() == 0){
                    etCurrentValue.setError( "Current value is required!" );
                    count++;
                }

                String superNameFund1 = etSuperNameFund1.getText().toString();
                String memberNumber1 = etMemberNumber1.getText().toString();
                String currentValue1 = etCurrentValue1.getText().toString();

                String superNameFund2 = etSuperNameFund2.getText().toString();
                String memberNumber2 = etMemberNumber2.getText().toString();
                String currentValue2 = etCurrentValue2.getText().toString();

                String superNameFund3 = etSuperNameFund3.getText().toString();
                String memberNumber3 = etMemberNumber3.getText().toString();
                String currentValue3 = etCurrentValue3.getText().toString();

                String superNameFund4 = etSuperNameFund4.getText().toString();
                String memberNumber4 = etMemberNumber4.getText().toString();
                String currentValue4 = etCurrentValue4.getText().toString();

                String superNameFund5 = etSuperNameFund5.getText().toString();
                String memberNumber5 = etMemberNumber5.getText().toString();
                String currentValue5 = etCurrentValue5.getText().toString();

                String superNameFund6 = etSuperNameFund6.getText().toString();
                String memberNumber6 = etMemberNumber6.getText().toString();
                String currentValue6 = etCurrentValue6.getText().toString();

                String superNameFund7 = etSuperNameFund7.getText().toString();
                String memberNumber7 = etMemberNumber7.getText().toString();
                String currentValue7 = etCurrentValue7.getText().toString();

                String superNameFundData, memberNumberData, currentValueData = "";
                superNameFundData = superNameFund;
                memberNumberData = memberNumber;
                currentValueData = currentValue;

                //Log.d("super1", superNameFund1 + "super2"+ superNameFund3.trim().length() + "super3"+ superNameFund4.trim().length());
                if (superNameFund1.trim().length() == 0 && numberOfLayout == 1){
                    etSuperNameFund1.setError( "Super name fund1 is required!" );
                    count++;
                }else {
                    if(superNameFund1.trim().length() != 0)
                        superNameFundData += ","+ superNameFund1;
                }

                if (superNameFund2.trim().length() == 0 && numberOfLayout == 2){
                    etSuperNameFund2.setError( "Super name fund2 is required!" );
                    count++;
                }else {
                    if(superNameFund2.trim().length() != 0)
                        superNameFundData += ","+ superNameFund2;
                }

                if (superNameFund3.trim().length() == 0 && numberOfLayout == 3){
                    etSuperNameFund3.setError( "Super name fund3 is required!" );
                    count++;
                }else {
                    if(superNameFund3.trim().length() != 0)
                        superNameFundData += ","+ superNameFund3;
                }

                if (superNameFund4.trim().length() == 0 && numberOfLayout == 4){
                    etSuperNameFund4.setError( "Super name fund4 is required!" );
                    count++;
                }else {
                    if(superNameFund4.trim().length() != 0)
                        superNameFundData += ","+ superNameFund4;
                }

                if (superNameFund5.trim().length() == 0 && numberOfLayout == 5){
                    etSuperNameFund5.setError( "Super name fund5 is required!" );
                    count++;
                }else {
                    if(superNameFund5.trim().length() != 0)
                        superNameFundData += ","+ superNameFund5;
                }

                if (superNameFund6.trim().length() == 0 && numberOfLayout == 6){
                    etSuperNameFund6.setError( "Super name fund6 is required!" );
                    count++;
                }else {
                    if(superNameFund6.trim().length() != 0)
                        superNameFundData += ","+ superNameFund6;
                }

                if (superNameFund7.trim().length() == 0 && numberOfLayout == 7){
                    etSuperNameFund7.setError( "Super name fund7 is required!" );
                    count++;
                }else {
                    if(superNameFund7.trim().length() != 0)
                     superNameFundData += ","+ superNameFund7;
                }

                if (memberNumber1.trim().length() == 0 && numberOfLayout == 1){
                    etMemberNumber1.setError( "Member Number1 is required!" );
                    count++;
                }else {
                    if (memberNumber1.trim().length() != 0)
                        memberNumberData += ","+ memberNumber1;
                }

                if (memberNumber2.trim().length() == 0&& numberOfLayout == 2){
                    etMemberNumber2.setError( "Member Number2 is required!" );
                    count++;
                }else {
                    if (memberNumber2.trim().length() != 0)
                        memberNumberData += ","+ memberNumber2;
                }

                if (memberNumber3.trim().length() == 0 && numberOfLayout == 3){
                    etMemberNumber3.setError( "Member Number3 is required!" );
                    count++;
                }else {
                    if (memberNumber3.trim().length() != 0)
                        memberNumberData += ","+ memberNumber3;
                }

                if (memberNumber4.trim().length() == 0 && numberOfLayout == 4){
                    etMemberNumber4.setError( "Member Number4 is required!" );
                    count++;
                }else {
                    if (memberNumber4.trim().length() != 0)
                        memberNumberData += ","+ memberNumber4;
                }

                if (memberNumber5.trim().length() == 0 && numberOfLayout == 5){
                    etMemberNumber5.setError( "Member Number5 is required!" );
                    count++;
                }else {
                    if (memberNumber5.trim().length() != 0)
                        memberNumberData += ","+ memberNumber5;
                }

                if (memberNumber6.trim().length() == 0 && numberOfLayout == 6){
                    etMemberNumber6.setError( "Member Number6 is required!" );
                    count++;
                }else {
                    if (memberNumber6.trim().length() != 0)
                        memberNumberData += ","+ memberNumber6;
                }

                if (memberNumber7.trim().length() == 0 && numberOfLayout == 7){
                    etMemberNumber7.setError( "Member Number7 is required!" );
                    count++;
                }else {
                    if (memberNumber7.trim().length() != 0)
                        memberNumberData += ","+ memberNumber7;
                }

                if (currentValue1.trim().length() == 0 && numberOfLayout == 1){
                    etCurrentValue1.setError( "Current Value1 is required!" );
                    count++;
                }else {
                    if (currentValue1.trim().length() != 0)
                        currentValueData += ","+ currentValue1;
                }

                if (currentValue2.trim().length() == 0 && numberOfLayout == 2){
                    etCurrentValue2.setError( "Current Value2 is required!" );
                    count++;
                }else {
                    if (currentValue2.trim().length() != 0)
                        currentValueData += ","+ currentValue2;
                }

                if (currentValue3.trim().length() == 0 && numberOfLayout == 3){
                    etCurrentValue3.setError( "Current Value3 is required!" );
                    count++;
                }else {
                    if (currentValue3.trim().length() != 0)
                        currentValueData += ","+ currentValue3;
                }

                if (currentValue4.trim().length() == 0 && numberOfLayout == 4){
                    etCurrentValue4.setError( "Current Value4 is required!" );
                    count++;
                }else {
                    if (currentValue4.trim().length() != 0)
                        currentValueData += ","+ currentValue4;
                }

                if (currentValue5.trim().length() == 0 && numberOfLayout == 5){
                    etCurrentValue5.setError( "Current Value5 is required!" );
                    count++;
                }else {
                    if (currentValue5.trim().length() != 0)
                        currentValueData += ","+ currentValue5;
                }

                if (currentValue6.trim().length() == 0 && numberOfLayout == 6){
                    etCurrentValue6.setError( "Current Value6 is required!" );
                    count++;
                }else {
                    if (currentValue6.trim().length() != 0)
                        currentValueData += ","+ currentValue6;
                }

                if (currentValue7.trim().length() == 0 && numberOfLayout == 7){
                    etCurrentValue7.setError( "Current Value7 is required!" );
                    count++;
                }else {
                    if (currentValue7.trim().length() != 0)
                        currentValueData += ","+ currentValue7;
                }

                Log.d("number of count is ", String.valueOf(count));
                if (count == 0){
                    userSession.setSuperFundName(superNameFund);
                    userSession.setMemberNumber(memberNumber);
                    userSession.setCurrentValue(currentValue);

                    userSession.setSuperFundName1(superNameFund1);
                    userSession.setMemberNumber1(memberNumber1);
                    userSession.setCurrentValue1(currentValue1);

                    userSession.setSuperFundName2(superNameFund2);
                    userSession.setMemberNumber2(memberNumber2);
                    userSession.setCurrentValue2(currentValue2);

                    userSession.setSuperFundName3(superNameFund3);
                    userSession.setMemberNumber3(memberNumber3);
                    userSession.setCurrentValue3(currentValue3);

                    userSession.setSuperFundName4(superNameFund4);
                    userSession.setMemberNumber4(memberNumber4);
                    userSession.setCurrentValue4(currentValue4);

                    userSession.setSuperFundName5(superNameFund5);
                    userSession.setMemberNumber5(memberNumber5);
                    userSession.setCurrentValue5(currentValue5);

                    userSession.setSuperFundName6(superNameFund6);
                    userSession.setMemberNumber6(memberNumber6);
                    userSession.setCurrentValue6(currentValue6);

                    userSession.setSuperFundName7(superNameFund7);
                    userSession.setMemberNumber7(memberNumber7);
                    userSession.setCurrentValue7(currentValue7);

                    userSession.setSuperFundNameData(superNameFundData);
                    userSession.setMemberNumberData(memberNumberData);
                    userSession.setCurrentValueData(currentValueData);

                    userSession.setNumberOfLayout(String.valueOf(numberOfLayout));
                   // userSession.setInvestmentOption(investmentOption);
                   // userSession.setSuperannuationStatus(supernnautionStatus);
                    onSignupSuperannuationDetailFragmentInteractionListener.onSignupSuperannuationDetailFragmentSelected("signupInsuranceDetail", null);
                }else {
                    return;
                }
            }
        });

        return view;
    }

    private void dynamicLayoutManager(){
        if (numberOfLayout == 1){
            rLSuperannuation1.setVisibility(View.VISIBLE);
            rLSuperannuation2.setVisibility(View.GONE);
            rLSuperannuation3.setVisibility(View.GONE);
            rLSuperannuation4.setVisibility(View.GONE);
            rLSuperannuation5.setVisibility(View.GONE);
            rLSuperannuation6.setVisibility(View.GONE);
            rLSuperannuation7.setVisibility(View.GONE);
        }  else if (numberOfLayout == 2){
            rLSuperannuation1.setVisibility(View.VISIBLE);
            rLSuperannuation2.setVisibility(View.VISIBLE);
            rLSuperannuation3.setVisibility(View.GONE);
            rLSuperannuation4.setVisibility(View.GONE);
            rLSuperannuation5.setVisibility(View.GONE);
            rLSuperannuation6.setVisibility(View.GONE);
            rLSuperannuation7.setVisibility(View.GONE);
        }  else if (numberOfLayout == 3){
            rLSuperannuation1.setVisibility(View.VISIBLE);
            rLSuperannuation2.setVisibility(View.VISIBLE);
            rLSuperannuation3.setVisibility(View.VISIBLE);
            rLSuperannuation4.setVisibility(View.GONE);
            rLSuperannuation5.setVisibility(View.GONE);
            rLSuperannuation6.setVisibility(View.GONE);
            rLSuperannuation7.setVisibility(View.GONE);
        } else if (numberOfLayout == 4){
            rLSuperannuation1.setVisibility(View.VISIBLE);
            rLSuperannuation2.setVisibility(View.VISIBLE);
            rLSuperannuation3.setVisibility(View.VISIBLE);
            rLSuperannuation4.setVisibility(View.VISIBLE);
            rLSuperannuation5.setVisibility(View.GONE);
            rLSuperannuation6.setVisibility(View.GONE);
            rLSuperannuation7.setVisibility(View.GONE);
        } else if (numberOfLayout == 5){
            rLSuperannuation1.setVisibility(View.VISIBLE);
            rLSuperannuation2.setVisibility(View.VISIBLE);
            rLSuperannuation3.setVisibility(View.VISIBLE);
            rLSuperannuation4.setVisibility(View.VISIBLE);
            rLSuperannuation5.setVisibility(View.VISIBLE);
            rLSuperannuation6.setVisibility(View.GONE);
            rLSuperannuation7.setVisibility(View.GONE);
        } else if (numberOfLayout == 6){
            rLSuperannuation1.setVisibility(View.VISIBLE);
            rLSuperannuation2.setVisibility(View.VISIBLE);
            rLSuperannuation3.setVisibility(View.VISIBLE);
            rLSuperannuation4.setVisibility(View.VISIBLE);
            rLSuperannuation5.setVisibility(View.VISIBLE);
            rLSuperannuation6.setVisibility(View.VISIBLE);
            rLSuperannuation7.setVisibility(View.GONE);
        } else if (numberOfLayout == 7){
            rLSuperannuation1.setVisibility(View.VISIBLE);
            rLSuperannuation2.setVisibility(View.VISIBLE);
            rLSuperannuation3.setVisibility(View.VISIBLE);
            rLSuperannuation4.setVisibility(View.VISIBLE);
            rLSuperannuation5.setVisibility(View.VISIBLE);
            rLSuperannuation6.setVisibility(View.VISIBLE);
            rLSuperannuation7.setVisibility(View.VISIBLE);
        } else {
            rLSuperannuation1.setVisibility(View.GONE);
            rLSuperannuation2.setVisibility(View.GONE);
            rLSuperannuation3.setVisibility(View.GONE);
            rLSuperannuation4.setVisibility(View.GONE);
            rLSuperannuation5.setVisibility(View.GONE);
            rLSuperannuation6.setVisibility(View.GONE);
            rLSuperannuation7.setVisibility(View.GONE);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSignupSuperannuationDetailFragmentInteractionListener) {
            onSignupSuperannuationDetailFragmentInteractionListener = (OnSignupSuperannuationDetailFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnSignupSuperannuationDetailFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onSignupSuperannuationDetailFragmentInteractionListener = null;
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
    public interface OnSignupSuperannuationDetailFragmentInteractionListener {
        // TODO: Update argument type and name
        void onSignupSuperannuationDetailFragmentSelected(String action, Bundle bundle);
    }
}
