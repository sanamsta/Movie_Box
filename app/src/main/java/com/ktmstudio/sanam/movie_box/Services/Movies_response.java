package com.ktmstudio.sanam.movie_box.Services;

import com.ktmstudio.sanam.movie_box.DataProvider;
import com.ktmstudio.sanam.movie_box.Infrastucture.Movies_Application;
import com.ktmstudio.sanam.movie_box.Model.CastCrew;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by iosdeveloper on 10/27/16.
 */

public class Movies_response {

    public Movies_response() {

    }

    public static class MovieRequest {

        public Map<String, String> data = new HashMap<>();
        public String query;
        public DataProvider context;
        public String mvPage;
        public String tv_type;
        public String gen_type;

        public MovieRequest(String ty_type, String gen_type, String query, DataProvider context, int page) {
            this.tv_type = ty_type;
            this.gen_type = gen_type;
            this.query = query;
            this.context = context;
            this.mvPage = Integer.toString(page);

            data.put("api_key", Movies_Application.apikey);
            data.put("page", String.valueOf(page));

        }
    }

    public static class MovieResponse {

        public static  ArrayList<Object> movie_dataArrayList = new ArrayList<>();
        public static  ArrayList<CastCrew.CastCrew_list> castcrew_dataArrayList= new ArrayList<>();

    }
}
