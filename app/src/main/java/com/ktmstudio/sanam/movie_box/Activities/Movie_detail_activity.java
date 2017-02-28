package com.ktmstudio.sanam.movie_box.Activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ktmstudio.sanam.movie_box.Adapters.Movie_info_RecyclerAdapter;
import com.ktmstudio.sanam.movie_box.DataProvider;
import com.ktmstudio.sanam.movie_box.Infrastucture.Movies_Application;
import com.ktmstudio.sanam.movie_box.Model.CastCrew;
import com.ktmstudio.sanam.movie_box.Model.Movie_details;
import com.ktmstudio.sanam.movie_box.Model.Movie_model;
import com.ktmstudio.sanam.movie_box.R;
import com.ktmstudio.sanam.movie_box.Services.Movies_response;
import com.ktmstudio.sanam.movie_box.Views.ImageTransformation;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Movie_detail_activity extends Base_Activity implements DataProvider {

    @BindView(R.id.movie_image)
    ImageView movie_imageview;

    @BindView(R.id.mToolbar)
    Toolbar toolbar;
    private RecyclerView recyclerView;
    private Movie_info_RecyclerAdapter recyclerAdapter;

    private Movie_model movie_model;
    Movie_details movie_details;
    ArrayList<Movie_details> arrayList;
    ArrayList<CastCrew> castCrewArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail_activity);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        movie_model = Parcels.unwrap(getIntent().getParcelableExtra("movie_data"));
        Load_image();

        recyclerView = (RecyclerView) findViewById(R.id.movie_detail_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new Movie_info_RecyclerAdapter(movie_model, movie_details);


        recyclerView.setAdapter(recyclerAdapter);

       bus.post(new Movies_response.MovieRequest("movie", "mv_detail", movie_model.getPerson_id(), this, 1));
        bus.post(new Movies_response.MovieRequest("movie", "cast_crew", movie_model.getPerson_id(), this, 1));

    }


    public void Load_image() {

        Picasso.with(this)
                .load(Movies_Application.backdrop_pic_url + movie_model.getMovie_poster())

                .into(movie_imageview);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void DataCompleted(Movies_response.MovieResponse movieResponse) {

        if (movieResponse!=null &&movieResponse.castcrew_dataArrayList!=null){

            Toast.makeText(this,"name is "+ movieResponse.castcrew_dataArrayList.get(0).getCast_name(),Toast.LENGTH_SHORT).show();
        }

        if (movieResponse!=null &&movieResponse.movie_dataArrayList!=null){
            arrayList = (ArrayList<Movie_details>) (ArrayList<?>) (movieResponse.movie_dataArrayList);

          //  Toast.makeText(this,"name is "+ arrayList.get(0).getMd_title(),Toast.LENGTH_SHORT).show();
        }



//        arrayList = (ArrayList<Movie_details>) (ArrayList<?>) (movieResponse.movie_dataArrayList);
//
//  arrayList = (ArrayList<Movie_details>) (ArrayList<?>) (movieResponse.movie_dataArrayList);
////        else if (movieResponse.movie_dataArrayList.get(0) instanceof CastCrew.CastCrew_list) {
////
////            castCrewArrayList.addAll((ArrayList<CastCrew.CastCrew_list>) (ArrayList<?>) (movieResponse.castcrew_dataArrayList));
////
////            Toast.makeText(this,"name is "+  castCrewArrayList.get(0).getCast_name(),Toast.LENGTH_SHORT).show();
////
////
////
////        }
//
//        movie_details = arrayList.get(0);
//        recyclerAdapter = new Movie_info_RecyclerAdapter(movie_model, movie_details);
//
//
//        recyclerView.setAdapter(recyclerAdapter);
//        recyclerAdapter.notifyDataSetChanged();


    }



}
