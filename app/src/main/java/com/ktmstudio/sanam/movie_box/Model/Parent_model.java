package com.ktmstudio.sanam.movie_box.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by iosdeveloper on 11/8/16.
 */

public class Parent_model {

    @SerializedName("results")
    public List<Movie_model> modelList;

}
