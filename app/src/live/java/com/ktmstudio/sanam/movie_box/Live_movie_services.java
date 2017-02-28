package com.ktmstudio.sanam.movie_box;

import com.ktmstudio.sanam.movie_box.Infrastucture.Movies_Application;
import com.ktmstudio.sanam.movie_box.Model.Biography;
import com.ktmstudio.sanam.movie_box.Model.CastCrew;
import com.ktmstudio.sanam.movie_box.Model.Movie_details;
import com.ktmstudio.sanam.movie_box.Model.Parent_model;
import com.ktmstudio.sanam.movie_box.Services.Movies_response;
import com.squareup.otto.Subscribe;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by iosdeveloper on 10/27/16.
 */

public class Live_movie_services extends Base_live_service {


    public Live_movie_services(Movies_Application application, Api_services api_services) {
        super(application, api_services);
    }



    @Subscribe
    public void getmovieMessage(final Movies_response.MovieRequest movieRequest) {


        final Movies_response.MovieResponse movieresponse = new Movies_response.MovieResponse();


        if (movieRequest.gen_type == "gen") {

            String fullpath = "genre/" + movieRequest.query + "/movies";
            Call<Parent_model> call = api_services.Load_Movies2(fullpath, movieRequest.data);
            callRetrofit(call, movieRequest, movieresponse);
        }else if (movieRequest.gen_type == "person"){

            String fullpath = movieRequest.tv_type + "/" + movieRequest.query;
            Call<Biography> call = api_services.Load_person_detail(fullpath, movieRequest.data);
            callRetrofit(call, movieRequest, movieresponse);
        }else if (movieRequest.gen_type == "mv_detail"){

            String fullpath = movieRequest.tv_type+"/"+movieRequest.query;
            Call<Movie_details> call = api_services.Load_Movie_details(fullpath,movieRequest.data);
            callRetrofit(call,movieRequest,movieresponse);

        }else if (movieRequest.gen_type == "cast_crew"){
            String fullpath = movieRequest.tv_type+"/"+movieRequest.query+"/credits";
            Call<CastCrew> call = api_services.Load_Castcrew_details(fullpath,movieRequest.data);
            callRetrofit(call,movieRequest,movieresponse);

        }

        else {
            String fullpath = movieRequest.tv_type + "/" + movieRequest.query;
            Call<Parent_model> call = api_services.Load_Movies2(fullpath, movieRequest.data);
            callRetrofit(call, movieRequest, movieresponse);

        }


    }



    public static <T> void callRetrofit(Call<T> call, final Movies_response.MovieRequest movieRequest, final Movies_response.MovieResponse movieresponse) {

        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {

                if (response.body() instanceof Parent_model) {
                    movieresponse.movie_dataArrayList.clear();
                    movieresponse.movie_dataArrayList.addAll(((Parent_model) response.body()).modelList);
                    DataProvider dataProvider = movieRequest.context;
                    dataProvider.DataCompleted(movieresponse);
                }
               else if (response.body() instanceof CastCrew){


                    movieresponse.castcrew_dataArrayList.clear();
                    movieresponse.castcrew_dataArrayList.addAll(((CastCrew) response.body()).castCrew_lists);

                    DataProvider dataProvider = movieRequest.context;
                    dataProvider.DataCompleted(movieresponse);

                } else if (response.body() instanceof Movie_details){


                    movieresponse.movie_dataArrayList.clear();
                    movieresponse.movie_dataArrayList.add(response.body());


                }else {
                    movieresponse.movie_dataArrayList.clear();
                    movieresponse.movie_dataArrayList.add(response.body());
                    DataProvider dataProvider = movieRequest.context;
                    dataProvider.DataCompleted(movieresponse);
                }



            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {

            }
        });

    }


}
