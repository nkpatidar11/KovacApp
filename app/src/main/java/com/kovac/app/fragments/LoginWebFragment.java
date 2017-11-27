package com.kovac.app.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.kovac.app.R;
import com.kovac.app.sessions.WebViewSession;
import com.kovac.app.utils.Util;

import static com.android.volley.ApplicationController.TAG;
import static com.kovac.app.utils.Util.hideKeyboard;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginWebFragment.OnLoginWebFragmentListener} interface
 * to handle interaction events.
 */
public class LoginWebFragment extends Fragment {

    private OnLoginWebFragmentListener mListener;
    private static final String TAG = "LoginWebFragment";

    private WebView wvLoginPage;
    private ProgressDialog progressBar;

    private WebViewSession webViewSession;

    public LoginWebFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        webViewSession = new WebViewSession(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_web, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        Util.hideKeyboard(getContext());
        this.wvLoginPage = (WebView) view.findViewById(R.id.wvLoginPage);

        WebSettings settings = wvLoginPage.getSettings();
        settings.setJavaScriptEnabled(true);
        wvLoginPage.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);

        progressBar = ProgressDialog.show(getContext(), "", "Please wait...", false, false);

        wvLoginPage.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.i(TAG, "Processing webview url click..." + url);
                view.loadUrl(url);
                return true;
            }

            public void onPageFinished(WebView view, String url) {
                Log.i(TAG, "Finished loading URL: " +url);
                webViewSession.setCurrentWebUrl(url);
                //if (url.equals("https://appw.xplan.iress.com.au/touch/client#client_dashboard")){
                if (url.equals("https://appw.xplan.iress.com.au/client/main#client_dashboard")){
                    webViewSession.setIsWebviewLogin(true);
                    //Toast.makeText(getContext(), "login successfully", Toast.LENGTH_SHORT).show();
               // } else if (url.equals("https://appw.xplan.iress.com.au/touch/client#logout")){
                } else if (url.equals("https://appw.xplan.iress.com.au/client/main#logout")){
                    //Toast.makeText(getContext(), "logout", Toast.LENGTH_SHORT).show();
                    webViewSession.setIsWebviewLogin(false);
                    webViewSession.setIsFingerprintLogin(false);
                }

                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Log.e(TAG, "Error: " + description);
                Toast.makeText(getContext(), "Oh no! " + description, Toast.LENGTH_SHORT).show();
            }
        });
        //wvLoginPage.loadUrl("https://appw.xplan.iress.com.au/touch/client");
        //wvLoginPage.loadUrl("http://kovacadvisory.com.au/kss_login/");
        if (webViewSession.getIsWebviewLogin()) {
            wvLoginPage.loadUrl("https://appw.xplan.iress.com.au/client/main#client_dashboard");
        }else {
            wvLoginPage.loadUrl("https://appw.xplan.iress.com.au/client");
        }

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnLoginWebFragmentListener) {
            mListener = (OnLoginWebFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnLoginWebFragmentListener");
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
     */
    public interface OnLoginWebFragmentListener {
        // TODO: Update argument type and name
        void onLoginWebFragmentSelected(String action, Bundle bundle);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        webViewSession.clearWebViewSession();
        webViewSession.setIsWebviewLogin(false);
        webViewSession.setIsFingerprintLogin(false);
    }
}


