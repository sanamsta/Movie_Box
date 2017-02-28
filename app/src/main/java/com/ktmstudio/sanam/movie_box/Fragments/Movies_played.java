package com.ktmstudio.sanam.movie_box.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ktmstudio.sanam.movie_box.Adapters.Movies_Played_Adapter;
import com.ktmstudio.sanam.movie_box.Adapters.Recycler_adapter;
import com.ktmstudio.sanam.movie_box.Helper.AgeCalculator;
import com.ktmstudio.sanam.movie_box.Infrastucture.Movies_Application;
import com.ktmstudio.sanam.movie_box.Model.Biography;
import com.ktmstudio.sanam.movie_box.Model.Movie_model;
import com.ktmstudio.sanam.movie_box.R;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class Movies_played extends Fragment {

    @BindView(R.id.recycler_movies_played)
    RecyclerView recyclerView;

    private Movies_Played_Adapter adapter;

    private ArrayList<Movie_model> movie_datas = new ArrayList<>();

    Movies_Application application;
    public Movies_played() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.movies_played_fragment, container, false);

        ButterKnife.bind(this,v);

        application = (Movies_Application)getActivity().getApplication();
        application.getBus().register(this);




        return v;
    }


    @Subscribe
    public void get_person_data(Movie_model movie_model){

        Toast.makeText(application,"movie name "+movie_model.getMovie_details().get(0).getDoriginal_title(),Toast.LENGTH_SHORT).show();


          movie_datas.add(movie_model);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        adapter = new Movies_Played_Adapter(movie_datas.get(0).getMovie_details());
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();


    }

}
