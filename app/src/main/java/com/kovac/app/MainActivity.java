package com.kovac.app;

import android.app.Dialog;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.kovac.app.fragments.AssetsLiabilitiesFragment;
import com.kovac.app.fragments.DisclaimerFragment;
import com.kovac.app.fragments.KovacWhyWithUsFragment;
import com.kovac.app.fragments.LandingFragment;
import com.kovac.app.fragments.LoginFragment;
import com.kovac.app.fragments.LoginWebFragment;
import com.kovac.app.fragments.RegisterFragment;
import com.kovac.app.fragments.RegisterSuccessFragment;
import com.kovac.app.fragments.SignupEmploymentFragment;
import com.kovac.app.fragments.SignupIdentificationFragment;
import com.kovac.app.fragments.SignupInsuranceDetailFragment;
import com.kovac.app.fragments.SignupLoginDetailFragment;
import com.kovac.app.fragments.SignupPersonalDetailFragment;
import com.kovac.app.fragments.SignupRiskProfileFragment;
import com.kovac.app.fragments.SignupSuperannuationDetailFragment;
import com.kovac.app.fragments.SubmittedFragment;
import com.kovac.app.fragments.VisitWebsiteFragment;
import com.kovac.app.sessions.WebViewSession;
import com.kovac.app.utils.Util;
import com.multidots.fingerprintauth.FingerPrintAuthCallback;
import com.multidots.fingerprintauth.FingerPrintAuthHelper;

public class MainActivity extends AppCompatActivity implements LandingFragment.OnLandingFragmentInteractionListener, LoginFragment.OnLoginFragmentInteractionListener,
        RegisterFragment.OnRegisterFragmentInteractionListener, RegisterSuccessFragment.OnRegisterSuccessFragmentInteractionListener, SignupLoginDetailFragment.OnSignupLoginDetailFragmentInteractionListener,
        KovacWhyWithUsFragment.OnKovacWhyWithUsFragmentInteractionListener, SignupPersonalDetailFragment.OnSignupPersonalDetailFragmentInteractionListener,
        SignupEmploymentFragment.OnSignupEmploymentFragmentInteractionListener, SignupSuperannuationDetailFragment.OnSignupSuperannuationDetailFragmentInteractionListener,
        SignupInsuranceDetailFragment.OnSignupInsuranceDetailFragmentInteractionListener, AssetsLiabilitiesFragment.OnAssetsLiabilitiesFragmentInteractionListener,
        SignupRiskProfileFragment.OnSignupRiskProfileFragmentInteractionListener, SignupIdentificationFragment.OnSignupIdentificationFragmentInteractionListener,
        DisclaimerFragment.OnDisclaimerFragmentInteractionListener, SubmittedFragment.OnSubmittedFragmentInteractionListener,
        LoginWebFragment.OnLoginWebFragmentListener, FingerPrintAuthCallback{

    private WebViewSession webViewSession;
    private FingerPrintAuthHelper mFingerPrintAuthHelper;
    private Dialog dialog;
    private boolean isFingrprintSupport = false;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Util.hideKeyboard(this);

        webViewSession = new WebViewSession(this);
        if (Build.VERSION.SDK_INT >= 23)
            isFingrprintSupport = true;
        else
            isFingrprintSupport = false;

        if (webViewSession.getCurrentWebUrl() != null){
            if (webViewSession.getIsWebviewLogin() && isFingrprintSupport){
                fingerPrintPopup();
            } else {
                //Toast.makeText(this, "Webview not login.", Toast.LENGTH_LONG).show();
                if (savedInstanceState == null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, new LandingFragment()).commit();
                }
            }
        } else {
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.content, new LandingFragment()).commit();
            }
        }


    }

    private void fingerPrintPopup(){
        mFingerPrintAuthHelper = FingerPrintAuthHelper.getHelper(this, this);

        dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.show();

        Button btnCancel = (Button) dialog.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                mFingerPrintAuthHelper.stopAuth();
                dialog.dismiss();
                webViewSession.setIsFingerprintLogin(false);
            }
        });

        //start finger print authentication
        mFingerPrintAuthHelper.startAuth();
    }

    @Override
    public void onNoFingerPrintHardwareFound() {
        Toast.makeText(this, "No Finger Print Hardware Found error", Toast.LENGTH_SHORT).show();
        webViewSession.setIsFingerprintLogin(false);
        webViewSession.setIsWebviewLogin(false);
        isFingrprintSupport = false;
    }

    @Override
    public void onNoFingerPrintRegistered() {
        Toast.makeText(this, "No Finger Print Registered error", Toast.LENGTH_SHORT).show();
        webViewSession.setIsFingerprintLogin(false);
        webViewSession.setIsWebviewLogin(false);
        isFingrprintSupport = true;
    }

    @Override
    public void onBelowMarshmallow() {
        isFingrprintSupport = false;
        //Toast.makeText(this, "below marshmallow", Toast.LENGTH_SHORT).show();
        webViewSession.setIsFingerprintLogin(false);
        webViewSession.setIsWebviewLogin(false);
        dialog.dismiss();

    }

    @Override
    public void onAuthSuccess(FingerprintManager.CryptoObject cryptoObject) {
        Toast.makeText(this, "Auth success", Toast.LENGTH_SHORT).show();
        webViewSession.setIsFingerprintLogin(true);
        isFingrprintSupport = true;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack("back");
        fragmentTransaction.replace(R.id.content, new LoginWebFragment());
        fragmentTransaction.commit();
        dialog.dismiss();
    }

    @Override
    public void onAuthFailed(int errorCode, String errorMessage) {
        if(errorCode == 566) {
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Auth error...!", Toast.LENGTH_SHORT).show();
        }
        webViewSession.setIsFingerprintLogin(false);
    }

    private class FingerprintException extends Exception {

        public FingerprintException(Exception e) {
            super(e);
        }
    }

    @Override
    public void onBackPressed() {
        //Toast.makeText(this, "back Pressed", Toast.LENGTH_SHORT).show();
        super.onBackPressed();
    }

    @Override
    public void onLandingFragmentSelected(String action) {
        Fragment fragment = null;
        if (action.equals("login"))
            fragment = new LoginFragment();
        else if (action.equals("signupLoginDetail"))
            fragment = new SignupLoginDetailFragment();
        else if (action.equals("aboutDetail"))
            fragment = new KovacWhyWithUsFragment();

        if (fragment != null){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.addToBackStack("back");
            fragmentTransaction.replace(R.id.content, fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onLoginFragmentSelected(String action, Bundle bundle) {
        Fragment fragment = null;
        if (action.equals("homeBack")) {
            fragment = new LandingFragment();
            onBackPressed();
        }else if(action.equals("register")) {
            fragment = new RegisterFragment();
            fragment.setArguments(bundle);
        } else if(action.equals("loginWeb")){
            fragment = new LoginWebFragment();
        }

        if (fragment != null){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.addToBackStack("back");
            fragmentTransaction.replace(R.id.content, fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onRegisterFragmentSelected(String action, Bundle bundle) {
        Fragment fragment = null;
        if (action.equals("backLogin")) {
            onBackPressed();
            fragment = new LoginFragment();
        }else if(action.equals("successRegistration")) {
            fragment = new RegisterSuccessFragment();
            fragment.setArguments(bundle);
        }
        if (fragment != null){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.addToBackStack("back");
            fragmentTransaction.replace(R.id.content, fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onRegisterSuccessFragmentSelected(String action) {
        Fragment fragment = null;
        if (action.equals("homeBack")) {
            onBackPressed();
            fragment = new LandingFragment();
        }

        if (fragment != null){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.addToBackStack("back");
            fragmentTransaction.replace(R.id.content, fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onSignupLoginDetailFragmentSelected(String action, Bundle bundle) {
        Fragment fragment = null;
        if (action.equals("homeBack")) {
            onBackPressed();
            fragment = new LandingFragment();
        }else if (action.equals("signupPersonalDetail")) {
            fragment = new SignupPersonalDetailFragment();
            fragment.setArguments(bundle);
        }

        if (fragment != null){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.addToBackStack("back");
            fragmentTransaction.replace(R.id.content, fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onKovacWhyWithUsFragmentSelected(String action) {
        Fragment fragment = null;
        if (action.equals("homeBack")) {
            onBackPressed();
            fragment = new LandingFragment();
        } else if (action.equals("visitWebsite")){
            fragment = new VisitWebsiteFragment();
        }

        if (fragment != null){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.addToBackStack("back");
            fragmentTransaction.replace(R.id.content, fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onSignupPersonalDetailFragmentSelected(String action, Bundle bundle) {
        Fragment fragment = null;
        if (action.equals("signupLoginBack")) {
            onBackPressed();
            fragment = new SignupLoginDetailFragment();
        }else if (action.equals("signupEmploymentDetail")) {
            fragment = new SignupEmploymentFragment();
            fragment.setArguments(bundle);
        }


        if (fragment != null){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.addToBackStack("back");
            fragmentTransaction.replace(R.id.content, fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onSignupEmploymentFragmentSelected(String action, Bundle bundle) {
        Fragment fragment = null;
        if (action.equals("signupPersonalBack")) {
            onBackPressed();
            fragment = new SignupPersonalDetailFragment();
        }else if (action.equals("signupSuperannuationDetail")) {
            fragment = new SignupSuperannuationDetailFragment();
            fragment.setArguments(bundle);
        }


        if (fragment != null){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.addToBackStack("back");
            fragmentTransaction.replace(R.id.content, fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onSignupSuperannuationDetailFragmentSelected(String action, Bundle bundle) {
        Fragment fragment = null;
        if (action.equals("signupEmploymentBack")) {
            onBackPressed();
            fragment = new SignupEmploymentFragment();
        }else if (action.equals("signupInsuranceDetail")) {
            fragment = new SignupInsuranceDetailFragment();
        }
        fragment.setArguments(bundle);

        if (fragment != null){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.addToBackStack("back");
            fragmentTransaction.replace(R.id.content, fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onSignupInsuranceDetailFragmentSelected(String action, Bundle bundle) {
        Fragment fragment = null;
        if (action.equals("signupSuperNnuationBack")) {
            onBackPressed();
            fragment = new SignupSuperannuationDetailFragment();
        }else if (action.equals("signupAssetLiabalitiesDetail")) {
            fragment = new AssetsLiabilitiesFragment();
        }
        fragment.setArguments(bundle);

        if (fragment != null){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.addToBackStack("back");
            fragmentTransaction.replace(R.id.content, fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onAssetsLiabilitiesFragmentSelected(String action, Bundle bundle) {
        Fragment fragment = null;
        if (action.equals("signupInsuranceBack")) {
            onBackPressed();
            fragment = new SignupInsuranceDetailFragment();
        }else if (action.equals("signupRiskProfileDetail")) {
            fragment = new SignupRiskProfileFragment();
        }
        fragment.setArguments(bundle);

        if (fragment != null){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.addToBackStack("back");
            fragmentTransaction.replace(R.id.content, fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onSignupRiskProfileFragmenteSelected(String action, Bundle bundle) {
        Fragment fragment = null;
        if (action.equals("signupAssetsLiabalitiesBack")) {
            onBackPressed();
            fragment = new AssetsLiabilitiesFragment();
        }else if (action.equals("signupIdentification")) {
            fragment = new SignupIdentificationFragment();
        }
        fragment.setArguments(bundle);

        if (fragment != null){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.addToBackStack("back");
            fragmentTransaction.replace(R.id.content, fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onSignupIdentificationFragmentSelected(String action, Bundle bundle) {
        Fragment fragment = null;
        if (action.equals("signupProfileRiskBack")) {
            onBackPressed();
            fragment = new SignupRiskProfileFragment();
        }else if (action.equals("signupSubmitted")) {
            fragment = new SubmittedFragment();
        } else if (action.equals("signupDiclaimer")) {
            fragment = new DisclaimerFragment();
        }
        fragment.setArguments(bundle);

        if (fragment != null){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.addToBackStack("back");
            fragmentTransaction.replace(R.id.content, fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onSubmittedFragmentSelected(String action, Bundle bundle) {
        Fragment fragment = null;
        if (action.equals("homeBack")) {
            onBackPressed();
            fragment = new LandingFragment();
        }

        fragment.setArguments(bundle);

        if (fragment != null){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.addToBackStack("back");
            fragmentTransaction.replace(R.id.content, fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onOnDisclaimerFragmentSelected(String action, Bundle bundle) {
        Fragment fragment = null;
        if (action.equals("signupIdentificationBack")) {
            onBackPressed();
            fragment = new SignupIdentificationFragment();
        }else if (action.equals("signupSubmitted")) {
            fragment = new SubmittedFragment();
        }
        fragment.setArguments(bundle);

        if (fragment != null){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.addToBackStack("back");
            fragmentTransaction.replace(R.id.content, fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onLoginWebFragmentSelected(String action, Bundle bundle) {
        Fragment fragment = null;
        if (action.equals("login")) {
            onBackPressed();
            fragment = new LoginFragment();
        }
        fragment.setArguments(bundle);

        if (fragment != null){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.addToBackStack("back");
            fragmentTransaction.replace(R.id.content, fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webViewSession.clearWebViewSession();
        webViewSession.setIsWebviewLogin(false);
        webViewSession.setIsFingerprintLogin(false);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //No call for super(). Bug on API Level > 11.
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (isFingrprintSupport){
            if (webViewSession.getCurrentWebUrl() != null) {
                if (webViewSession.getIsWebviewLogin()) {
                    if (webViewSession.getIsFingerprintLogin()){
                        //Toast.makeText(this, "Re start with login", Toast.LENGTH_SHORT).show();
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.addToBackStack("back");
                        fragmentTransaction.replace(R.id.content, new LoginWebFragment());
                        fragmentTransaction.commit();
                    } /*else {
                        //Toast.makeText(this, "Re start with out login", Toast.LENGTH_SHORT).show();
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.addToBackStack("back");
                        fragmentTransaction.replace(R.id.content, new LandingFragment());
                        //fragmentTransaction.commit();
                        fragmentTransaction.commitAllowingStateLoss();
                        fingerPrintPopup();
                    }*/
                }/*else {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.addToBackStack("back");
                    fragmentTransaction.replace(R.id.content, new LandingFragment());
                    //fragmentTransaction.commit();
                    fragmentTransaction.commitAllowingStateLoss();
                }*/
            }
        }
    }
}
