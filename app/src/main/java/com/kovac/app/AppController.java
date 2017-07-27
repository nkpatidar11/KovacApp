package com.kovac.app;

import android.app.Activity;
import android.app.Application;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

/**
 * Created by Jai Gurudev on 7/26/2017.
 */

public class AppController extends Application {
    public static final String TAG = AppController.class.getSimpleName();
    private static AppController mInstance;

    @Override
    public void onCreate() {

        super.onCreate();
        mInstance = this;
        registerActivityLifecycleCallbacks(new ActivityLifecycleAdapter() {
            @Override
            public void onActivityCreated(Activity a, Bundle savedInstanceState) {
                a.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
        });
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }
}
