package com.kovac.app.sessions;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Jai Gurudev on 10/20/2017.
 */

public class WebViewSession {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    // shared pref mode
    int PRIVATE_MODE = 0;
    // Shared preferences file name
    private static final String PREF_NAME = "kovac_webview_pref";

    private static final String CURRENT_WEB_URL = "current_web_url";

    private static final String IS_WEBVIEW_LOGIN = "isWebViewLogin";
    private static final String IS_FINGERPRINT_LOGIN = "isFingerPirntLogin";

    public WebViewSession(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void clearWebViewSession(){
        editor.clear();
        editor.commit();
    }

    public void setCurrentWebUrl(String currentWebUrl) {
        editor.putString(CURRENT_WEB_URL, currentWebUrl);
        editor.commit();
    }

    public String getCurrentWebUrl() {
        return pref.getString(CURRENT_WEB_URL, "");
    }

    public void setIsWebviewLogin(boolean isWebviewLogin) {
        editor.putBoolean(IS_WEBVIEW_LOGIN, isWebviewLogin);
        editor.commit();
    }

    public boolean getIsWebviewLogin() {
        return pref.getBoolean(IS_WEBVIEW_LOGIN, false);
    }

    public void setIsFingerprintLogin(boolean isFingerprintLogin) {
        editor.putBoolean(IS_FINGERPRINT_LOGIN, isFingerprintLogin);
        editor.commit();
    }

    public boolean getIsFingerprintLogin() {
        return pref.getBoolean(IS_FINGERPRINT_LOGIN, false);
    }

    //https://appw.xplan.iress.com.au/touch/client#client_dashboard
}
