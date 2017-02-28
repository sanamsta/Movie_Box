package com.ktmstudio.sanam.movie_box.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ktmstudio.sanam.movie_box.Activities.Movie_detail_activity;
import com.ktmstudio.sanam.movie_box.Activities.Person_Activity;
import com.ktmstudio.sanam.movie_box.Adapters.Recycler_adapter;
import com.ktmstudio.sanam.movie_box.DataProvider;
import com.ktmstudio.sanam.movie_box.Infrastucture.Movies_Application;
import com.ktmstudio.sanam.movie_box.Model.CastCrew;
import com.ktmstudio.sanam.movie_box.Model.Movie_model;
import com.ktmstudio.sanam.movie_box.Model.Sub_Movie_detail;
import com.ktmstudio.sanam.movie_box.R;
import com.ktmstudio.sanam.movie_box.Services.Movies_response;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class Movies_list_fragment extends Fragment implements Recycler_adapter.Clicklistener_recycler, DataProvider, SearchView.OnQueryTextListener {
    @BindView(R.id.view_recycler)
    RecyclerView recyclerView;
    private RecyclerView.LayoutManager manager;
    private Recycler_adapter recyclerAdapter;
    private ArrayList<Movie_model> movie_datas = new ArrayList<>();
    private ArrayList<Sub_Movie_detail> sub_movie_details = new ArrayList<>();
    Movies_Application application;
    private int strtext, pageno = 1, mp_index;

    private String gen_type;

    private String[] movies_type;
    private String[] mp_type;
    private SearchView searchView;

    public Movies_list_fragment() {
        // Required empty public constructor


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        strtext = getArguments().getInt("edttext");
        mp_index = getArguments().getInt("mp_index");
        // makeRetrofitCalls(movies_type[strtext]);


        application = (Movies_Application) getActivity().getApplication();


        mp_type = application.getResources().getStringArray(R.array.mp_type);
        if (mp_index == 0) {

            movies_type = application.getResources().getStringArray(R.array.movie_request);
            gen_type = "movies";


        } else if (mp_index == 1) {
            movies_type = application.getResources().getStringArray(R.array.tv_request);
            gen_type = "movies";
        } else if (mp_index == 2) {
            movies_type = application.getResources().getStringArray(R.array.genre_id);
            gen_type = "gen";
        } else if (mp_index == 3) {
            movies_type = new String[1];
            movies_type[0] = "popular";
            gen_type = "movies";
        }

        application.getBus().post(new Movies_response.MovieRequest(mp_type[mp_index], gen_type, movies_type[strtext], this, pageno));

        View view = inflater.inflate(R.layout.fragment_movies_list_fragment, container, false);

        ButterKnife.bind(this, view);

        showMovies();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (!recyclerView.canScrollVertically(1)) {

                    onScrolledToBottom();
                }

            }
        });


        return view;


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);

        MenuItem item = menu.findItem(R.id.search_view);

        searchView = (SearchView) MenuItemCompat.getActionView(item);

        searchView.setOnQueryTextListener(this);

        MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                Toast.makeText(getContext(), "expand", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Toast.makeText(getContext(), "collapse", Toast.LENGTH_SHORT).show();
                recyclerAdapter.filtered_movieList(movie_datas);

                return true;
            }
        });


    }

    private void onScrolledToBottom() {
        pageno++;

        Toast.makeText(getContext(), "scrolkled bottom", Toast.LENGTH_SHORT).show();

        application.getBus().post(new Movies_response.MovieRequest(mp_type[mp_index], gen_type, movies_type[strtext], this, pageno));

    }


    public void showMovies()

    {

        manager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(manager);
        recyclerAdapter = new Recycler_adapter(movie_datas, mp_index);
        recyclerAdapter.setRecyclerClicklistener(this);
        recyclerView.setAdapter(recyclerAdapter);


    }

    @Override
    public void recyclerview_clicked(View v, int position, Movie_model movieData) {


        switch (mp_index){

            case 0:
                Intent intent = new Intent(getContext(), Movie_detail_activity.class);
                intent.putExtra("movie_data",Parcels.wrap(movieData));
                startActivity(intent);
                break;

            default:
                Intent intent1 = new Intent(getContext(), Person_Activity.class);
                intent1.putExtra("Property", Parcels.wrap(movieData));
                startActivity(intent1);

                break;
        }




    }


    @Override
    public void DataCompleted(Movies_response.MovieResponse movieResponse) {


        movie_datas.addAll((ArrayList<Movie_model>) (ArrayList<?>) (movieResponse.movie_dataArrayList));

        recyclerAdapter.notifyDataSetChanged();


    }



    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        if (TextUtils.isEmpty(newText)) {
            recyclerAdapter.filtered_movieList(movie_datas);
        } else {
            Toast.makeText(getContext(), "text: " + newText, Toast.LENGTH_SHORT).show();


            ArrayList<Movie_model> movies = filtered_list(movie_datas, newText);
            recyclerAdapter.filtered_movieList(movies);
        }
        return true;
    }


    private ArrayList<Movie_model> filtered_list(ArrayList<Movie_model> modelArrayList, String query) {

        query = query.toLowerCase();

        final ArrayList<Movie_model> movieModels = new ArrayList<>();
        movieModels.clear();

        for (final Movie_model movie_model : modelArrayList) {

            final String moviename = movie_model.getMovie_title();

            if (moviename.toLowerCase().contains(query)) {
                movieModels.add(movie_model);


            }

        }

        return movieModels;


    }

    //    private void makeRetrofitCalls(String name) {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Movies_Application.baseurl) // 1
//                .addConverterFactory(GsonConverterFactory.create()) // 2
//                .build();
//
//        Api_services retrofitAPI = retrofit.create(Api_services.class); // 3
//
//        Call<Parent_model> call=retrofitAPI.Load_Movies(name,Movies_Application.apikey);
//
//
//        call.enqueue(new Callback<Parent_model>() {
//            @Override
//            public void onResponse(Call<Parent_model> call, Response<Parent_model> response) {
//                for (Movie_model movie_model:response.body().modelList){
//
//
//
//                   movie_datas.add(new Movie_datas(movie_model.getMovie_title(),movie_model.getMovie_releasedate(),movie_model.getMovie_poster()));
//
//                };
//
//                showMovies();
//
//            }
//
//            @Override
//            public void onFailure(Call<Parent_model> call, Throwable t) {
//
//            }
//        });
//
//    }


    //   @Subscribe
//    public void onMessageEvent(Movies_response.MovieResponse movieResponse) {
//
//
//        moviedata.put(movies_type[strtext], movieResponse.movie_dataArrayList);
//
//        // movie_datas.clear();
//        //   movie_datas.addAll(movieResponse.movie_dataArrayList);
//
//        //recyclerAdapter.notifyDataSetChanged();
//
//        showMovies();
//
//
//    }
}
