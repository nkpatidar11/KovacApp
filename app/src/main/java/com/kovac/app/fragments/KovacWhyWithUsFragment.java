package com.kovac.app.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kovac.app.R;
import com.kovac.app.utils.Util;

import static android.R.attr.button;

public class KovacWhyWithUsFragment extends Fragment {

    private OnKovacWhyWithUsFragmentInteractionListener onKovacWhyWithUsFragmentInteractionListener;
    private Button buttonBack, buttonVisitWebsite;

    public KovacWhyWithUsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_kovac_why_with_us, container, false);
        buttonBack = (Button) view.findViewById(R.id.btnBack);
        buttonVisitWebsite = (Button) view.findViewById(R.id.btnVisitOurWebsite);

        //back to home screen
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onKovacWhyWithUsFragmentInteractionListener.onKovacWhyWithUsFragmentSelected("homeBack");
            }
        });

        buttonVisitWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Util.WEBSITE_URL));
                startActivity(intent);*/
                onKovacWhyWithUsFragmentInteractionListener.onKovacWhyWithUsFragmentSelected("visitWebsite");
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnKovacWhyWithUsFragmentInteractionListener) {
            onKovacWhyWithUsFragmentInteractionListener = (OnKovacWhyWithUsFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnKovacWhyWithUsFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onKovacWhyWithUsFragmentInteractionListener = null;
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
    public interface OnKovacWhyWithUsFragmentInteractionListener {
        // TODO: Update argument type and name
        void onKovacWhyWithUsFragmentSelected(String action);
    }
}
