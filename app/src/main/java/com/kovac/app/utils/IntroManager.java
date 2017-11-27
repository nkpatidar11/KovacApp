package com.kovac.app.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Jai Gurudev on 10/4/2017.
 */

public class IntroManager {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context context;

    public IntroManager(Context context){
        this.context = context;
        preferences = context.getSharedPreferences("first", 0);
        editor = preferences.edit();
    }

    //set creating user first time or not
    public void setFirst(Boolean isFirst){
        editor.putBoolean("check", isFirst);
        editor.commit();
    }

    //check user open application previously or not
    public boolean checkFirst(){
        return preferences.getBoolean("check", true);
    }
}
