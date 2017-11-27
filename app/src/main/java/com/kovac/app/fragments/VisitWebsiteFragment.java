package com.kovac.app.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.kovac.app.R;
import com.kovac.app.utils.Util;

import static com.kovac.app.R.id.wvLoginPage;

/**
 * A simple {@link Fragment} subclass.
 */
public class VisitWebsiteFragment extends Fragment {
    private static final String TAG = "VisitWebsiteFragment";

    private WebView wvWebsitePage;
    private ProgressDialog progressBar;

    public VisitWebsiteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_visit_website, container, false);

        this.wvWebsitePage = (WebView) view.findViewById(R.id.wvWebsitePage);

        WebSettings settings = wvWebsitePage.getSettings();
        settings.setJavaScriptEnabled(true);
        wvWebsitePage.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);

        progressBar = ProgressDialog.show(getContext(), "", "Please wait...", false, false);

        wvWebsitePage.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.i(TAG, "Processing webview url click...");
                view.loadUrl(url);
                return true;
            }

            public void onPageFinished(WebView view, String url) {
                Log.i(TAG, "Finished loading URL: " +url);
                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Log.e(TAG, "Error: " + description);
                Toast.makeText(getContext(), "Oh no! " + description, Toast.LENGTH_SHORT).show();
            }
        });
        wvWebsitePage.loadUrl(Util.WEBSITE_URL);

        return view;
    }

}
