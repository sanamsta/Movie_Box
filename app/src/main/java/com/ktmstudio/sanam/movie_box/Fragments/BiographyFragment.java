package com.ktmstudio.sanam.movie_box.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ktmstudio.sanam.movie_box.Activities.Person_Activity;
import com.ktmstudio.sanam.movie_box.DataProvider;
import com.ktmstudio.sanam.movie_box.Helper.AgeCalculator;
import com.ktmstudio.sanam.movie_box.Infrastucture.Movies_Application;
import com.ktmstudio.sanam.movie_box.Model.Biography;
import com.ktmstudio.sanam.movie_box.R;
import com.ktmstudio.sanam.movie_box.Services.Movies_response;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class BiographyFragment extends Fragment {


    @BindView(R.id.biograpgy)
    TextView biographyTextView;

    @BindView(R.id.bioTitle)
    TextView bioTitle;

    Movies_Application movies_application;

    public BiographyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_biography, container, false);

        ButterKnife.bind(this, v);

        String get_personid = getArguments().getString("person_id");

        movies_application = (Movies_Application) getActivity().getApplication();

        movies_application.getBus().register(this);

        return v;
    }



    @Subscribe
    public void get_person_data(Biography biography){
        biographyTextView.setText(biography.getP_biography());

        AgeCalculator ageCalculator = new AgeCalculator(movies_application);

        bioTitle.setText("Biography ( Age "+ageCalculator.getAge(biography.getP_birthday())+" )");


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        movies_application.getBus().unregister(this);
    }
}
