package com.ktmstudio.sanam.movie_box.Model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by iosdeveloper on 11/8/16.
 */
@Parcel
public class Movie_model {

    public Movie_model() {


    }

    @SerializedName("poster_path")
    String movie_poster;

    @SerializedName("release_date")
    String movie_releasedate;

    @SerializedName("title")
    String movie_title;

    @SerializedName("vote_average")
    double rating;


    @SerializedName("first_air_date")
    String releaseDate;


    @SerializedName("original_name")
    String SeriesName;

    @SerializedName("profile_path")
    String profilePath;

    @SerializedName("id")
    int Person_id;

    @SerializedName("name")
    String PersonName;




    @SerializedName("backdrop_path")
    String backdrop_path;



    @SerializedName("overview")
    String overview;

    public String getVote_count() {
        return Integer.toString(vote_count);
    }

    @SerializedName("vote_count")
    int vote_count;


    @SerializedName("known_for")
    public List<Sub_Movie_detail> movie_details;

    public List<Sub_Movie_detail> getMovie_details() {
        return movie_details;
    }

    public String getMovie_poster() {

        return movie_poster;
    }

    public String getMovie_releasedate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = simpleDateFormat.parse(movie_releasedate);
            SimpleDateFormat fmtOut = new SimpleDateFormat("yyyy");

            return fmtOut.format(date);
        } catch (Exception e) {

        }


        return "";
    }

    public String getMovie_title() {

        if (movie_title == null) {
            return "";
        }
        return movie_title;
    }

    public String getRating() {


        return Double.toString(rating);
    }

    public String getReleaseDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = simpleDateFormat.parse(releaseDate);
            SimpleDateFormat fmtOut = new SimpleDateFormat("yyyy");

            return fmtOut.format(date);
        } catch (Exception e) {

        }


        return "";
    }

    public String getSeriesName() {
        return SeriesName;
    }


    public String getProfilePath() {
        return profilePath;
    }

    public String getPerson_id() {
        return Integer.toString(Person_id);
    }

    public String getPersonName() {
        return PersonName;
    }

    public String get_moviepice() {
        if (profilePath == null) {

            return movie_poster;
        } else if (movie_poster == null) {
            return profilePath;
        } else return "";


    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getOverview() {
        return overview;
    }
}
