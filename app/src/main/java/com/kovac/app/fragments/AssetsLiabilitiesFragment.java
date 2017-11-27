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

import com.kovac.app.R;
import com.kovac.app.sessions.UserSession;

import static com.kovac.app.R.id.cbIncomeProtection;
import static com.kovac.app.R.id.cbTraumaInsurance;
import static com.kovac.app.R.id.etHeight;
import static com.kovac.app.R.id.etIncomeProtectionValue;
import static com.kovac.app.R.id.etTraumaValue;
import static com.kovac.app.R.id.etWeight;
import static com.kovac.app.R.id.insuranceLayout;
import static com.kovac.app.R.id.switchCurrently;

public class AssetsLiabilitiesFragment extends Fragment {

    private OnAssetsLiabilitiesFragmentInteractionListener mListener;
    private EditText etOwnHome, etInvestmentProperty, etSharePortfolio, etOtherAssets, etHomeLoan, etInvestmentLoans, etCreditCards, etOtherLiabilities;
    private Button buttonBack, buttonNext;

    public AssetsLiabilitiesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_assets_liabilities, container, false);
        buttonBack = (Button) view.findViewById(R.id.btnBack);
        buttonNext = (Button) view.findViewById(R.id.btnNext);

        etOwnHome = (EditText) view.findViewById(R.id.etOwnHome);
        etInvestmentProperty = (EditText) view.findViewById(R.id.etInvestmentProperty);
        etSharePortfolio = (EditText) view.findViewById(R.id.etSharePortfolio);
        etOtherAssets = (EditText) view.findViewById(R.id.etOtherAssets);
        etHomeLoan = (EditText) view.findViewById(R.id.etHomeLoan);
        etInvestmentLoans = (EditText) view.findViewById(R.id.etInvestmentLoans);
        etCreditCards = (EditText) view.findViewById(R.id.etCreditCards);
        etOtherLiabilities = (EditText) view.findViewById(R.id.etOtherLiabilities);

        // set field value in session
        final UserSession userSession = new UserSession(getContext());
        //Toast.makeText(getContext(), "email is : " + userSession.getEmail(), Toast.LENGTH_SHORT).show();
        if(userSession.getOwnHome().trim().length() > 0){
            etOwnHome.setText(userSession.getOwnHome());
        }

        if(userSession.getInvestmentProperty().trim().length() > 0){
            etInvestmentProperty.setText(userSession.getInvestmentProperty());
        }

        if(userSession.getSharePortfolio().trim().length() > 0){
            etSharePortfolio.setText(userSession.getSharePortfolio());
        }

        if(userSession.getOtherAssets().trim().length() > 0){
            etOtherAssets.setText(userSession.getOtherAssets());
        }

        if(userSession.getHomeLoan().trim().length() > 0){
            etHomeLoan.setText(userSession.getHomeLoan());
        }

        if(userSession.getInvestmentLoans().trim().length() > 0){
            etInvestmentLoans.setText(userSession.getInvestmentLoans());
        }

        if(userSession.getCreditCards().trim().length() > 0){
            etCreditCards.setText(userSession.getCreditCards());
        }

        if(userSession.getOtherLiabilities().trim().length() > 0){
            etOtherLiabilities.setText(userSession.getOtherLiabilities());
        }

        //back to home screen
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onAssetsLiabilitiesFragmentSelected("signupInsuranceBack", null);
            }
        });

        //Next screen
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ownHome = etOwnHome.getText().toString();
                String investmentProperty = etInvestmentProperty.getText().toString();
                String sharePortfolio = etSharePortfolio.getText().toString();
                String otherAssets = etOtherAssets.getText().toString();
                String homeLoan = etHomeLoan.getText().toString();
                String investmentLoans = etInvestmentLoans.getText().toString();
                String creditCards = etCreditCards.getText().toString();
                String otherLiabilities = etOtherLiabilities.getText().toString();

                userSession.setOwnHome(ownHome);
                userSession.setInvestmentProperty(investmentProperty);
                userSession.setSharePortfolio(sharePortfolio);
                userSession.setOtherAssets(otherAssets);
                userSession.setHomeLoan(homeLoan);
                userSession.setInvestmentLoans(investmentLoans);
                userSession.setCreditCards(creditCards);
                userSession.setOtherLiabilities(otherLiabilities);

                mListener.onAssetsLiabilitiesFragmentSelected("signupRiskProfileDetail", null);
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAssetsLiabilitiesFragmentInteractionListener) {
            mListener = (OnAssetsLiabilitiesFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnAssetsLiabilitiesFragmentInteractionListener");
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
    public interface OnAssetsLiabilitiesFragmentInteractionListener {
        // TODO: Update argument type and name
        void onAssetsLiabilitiesFragmentSelected(String action, Bundle bundle);
    }
}
