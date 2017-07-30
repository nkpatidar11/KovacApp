package com.kovac.app;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kovac.app.fragments.KovacWhyWithUsFragment;
import com.kovac.app.fragments.LandingFragment;
import com.kovac.app.fragments.LoginFragment;
import com.kovac.app.fragments.RegisterFragment;
import com.kovac.app.fragments.RegisterSuccessFragment;
import com.kovac.app.fragments.SignupEmploymentFragment;
import com.kovac.app.fragments.SignupInsuranceDetailFragment;
import com.kovac.app.fragments.SignupLoginDetailFragment;
import com.kovac.app.fragments.SignupPersonalDetailFragment;
import com.kovac.app.fragments.SignupSuperannuationDetailFragment;

public class MainActivity extends AppCompatActivity implements LandingFragment.OnLandingFragmentInteractionListener, LoginFragment.OnLoginFragmentInteractionListener,
        RegisterFragment.OnRegisterFragmentInteractionListener, RegisterSuccessFragment.OnRegisterSuccessFragmentInteractionListener, SignupLoginDetailFragment.OnSignupLoginDetailFragmentInteractionListener,
        KovacWhyWithUsFragment.OnKovacWhyWithUsFragmentInteractionListener, SignupPersonalDetailFragment.OnSignupPersonalDetailFragmentInteractionListener,
        SignupEmploymentFragment.OnSignupEmploymentFragmentInteractionListener, SignupSuperannuationDetailFragment.OnSignupSuperannuationDetailFragmentInteractionListener,
        SignupInsuranceDetailFragment.OnSignupInsuranceDetailFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content, new LandingFragment()).commit();
        }
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
        if (action.equals("homeBack"))
            fragment = new LandingFragment();
        else if(action.equals("register"))
            fragment = new RegisterFragment();

        fragment.setArguments(bundle);
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
        if (action.equals("backLogin"))
            fragment = new LoginFragment();
        else if(action.equals("successRegistration"))
            fragment = new RegisterSuccessFragment();

        fragment.setArguments(bundle);
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
        if (action.equals("homeBack"))
            fragment = new LandingFragment();

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
        if (action.equals("homeBack"))
            fragment = new LandingFragment();
        else if (action.equals("signupPersonalDetail"))
            fragment = new SignupPersonalDetailFragment();

        fragment.setArguments(bundle);

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
        if (action.equals("homeBack"))
            fragment = new LandingFragment();

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
        if (action.equals("signupLoginBack"))
            fragment = new SignupLoginDetailFragment();
        else if (action.equals("signupEmploymentDetail"))
            fragment = new SignupEmploymentFragment();

        fragment.setArguments(bundle);

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
        if (action.equals("signupPersonalBack"))
            fragment = new SignupPersonalDetailFragment();
        else if (action.equals("signupSuperannuationDetail"))
            fragment = new SignupSuperannuationDetailFragment();

        fragment.setArguments(bundle);

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
        if (action.equals("signupEmploymentBack"))
            fragment = new SignupSuperannuationDetailFragment();
        else if (action.equals("signupInsuranceDetail"))
            fragment = new SignupSuperannuationDetailFragment();

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

    }
}
