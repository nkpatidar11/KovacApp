package com.kovac.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kovac.app.fragments.LandingFragment;

public class MainActivity extends AppCompatActivity implements LandingFragment.OnLandingFragmentInteractionListener {

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

    }
}
