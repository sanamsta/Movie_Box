package com.ktmstudio.sanam.movie_box.Model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by backtrack on 2/15/2017.
 */
@Parcel
public class Sub_Movie_detail {

    @SerializedName("poster_path")
    String dPosterpath;

    @SerializedName("overview")
    String dOverview;

    @SerializedName("release_date")
    String drelease_date;

    @SerializedName("title")
    String doriginal_title;

    @SerializedName("backdrop_path")
    String dbackdrop_path;

    @SerializedName("vote_average")
    String dvote_average;

    public String getdPosterpath() {
        return dPosterpath;
    }

    public String getdOverview() {
        return dOverview;
    }

    public String getDrelease_date() {
        return drelease_date;
    }

    public String getDoriginal_title() {
        return doriginal_title;
    }

    public String getDbackdrop_path() {
        return dbackdrop_path;
    }

    public String getDvote_average() {
        return dvote_average;
    }
}
