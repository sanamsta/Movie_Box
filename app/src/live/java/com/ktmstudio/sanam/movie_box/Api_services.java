package com.ktmstudio.sanam.movie_box;

import com.ktmstudio.sanam.movie_box.Model.Biography;
import com.ktmstudio.sanam.movie_box.Model.CastCrew;
import com.ktmstudio.sanam.movie_box.Model.Movie_details;
import com.ktmstudio.sanam.movie_box.Model.Parent_model;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by iosdeveloper on 11/8/16.
 */

public interface Api_services {


//    @GET("/3/{tv_type}/{parameters}")
//    Call<Parent_model> Load_Movies(
//            @Path("tv_type") String tv_type,
//            @Path("parameters") String requestType,
//            @Query("page") String PAGE,
//            @Query("api_key") String APIKEY
//    );
//
//    @GET("/3/genre/{genre_id}/{mp_type}")
//    Call<Parent_model> Load_Genre(
//            @Path("genre_id") String genre_id,
//            @Path("mp_type") String mp_type,
//            @Query("page") String page,
//            @Query("api_key") String APIKEY
//    );


    @GET("{fullpath}")
    Call<Parent_model> Load_Movies2(
            @Path(value = "fullpath", encoded = true) String tv_type,
            @QueryMap Map<String,String> options
            );

    @GET("{fullpath}")
    Call<Biography> Load_person_detail(
            @Path(value = "fullpath", encoded = true) String person_detail,
            @QueryMap Map<String ,String> data
            );

    @GET("{fullpath}")
    Call<Movie_details> Load_Movie_details(

            @Path(value = "fullpath", encoded = true) String movie_detail,
            @QueryMap Map<String,String> data
    );

    @GET("{fullpath}")
    Call<CastCrew> Load_Castcrew_details(

            @Path(value = "fullpath", encoded = true) String cast_detail,
            @QueryMap Map<String,String> data
    );


}
