package com.ktmstudio.sanam.movie_box.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by backtrack on 2/26/2017.
 */

public class Movie_details {

    @SerializedName("budget")
    String md_budget;

    @SerializedName("imdb_id")
    String md_imdb_id;

    @SerializedName("overview")
    String md_overview;

    @SerializedName("release_date")

    String md_release_date;

    @SerializedName("status")
    String md_status;

    @SerializedName("title")
    String md_title;

    @SerializedName("vote_average")
    String md_vote_average;


    @SerializedName("vote_count")
    String md_vote_count;

    @SerializedName("belongs_to_collection")
    Colletion colletion;




    @SerializedName("genres")
   List<Genre> genreList;


    public String getMd_budget() {
        return md_budget;
    }

    public String getMd_imdb_id() {
        return md_imdb_id;
    }

    public String getMd_overview() {
        return md_overview;
    }

    public String getMd_release_date() {
        return md_release_date;
    }

    public String getMd_status() {
        return md_status;
    }

    public String getMd_title() {
        return md_title;
    }

    public String getMd_vote_average() {
        return md_vote_average;
    }

    public String getMd_vote_count() {
        return md_vote_count;
    }

    public Colletion getColletion() {
        return colletion;
    }

    public List<Genre> getGenreList() {
        return genreList;
    }


    public class Colletion {



        @SerializedName("id")
        int collection_id;

        public String getCollection_id() {
            return Integer.toString(collection_id);
        }


    }

    public class Genre{


        @SerializedName("name")
        String genre_name;

        public String getGenre_name() {
            return genre_name;
        }
    }
}
