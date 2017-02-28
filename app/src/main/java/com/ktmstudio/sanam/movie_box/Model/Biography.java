package com.ktmstudio.sanam.movie_box.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by backtrack on 2/15/2017.
 */

public class Biography {

    @SerializedName("biography")
    String p_biography;

    @SerializedName("birthday")
    String p_birthday;

    @SerializedName("place_of_birth")
    String p_place_of_birth;

    @SerializedName("profile_path")
    String p_profile_path;

    public String getP_biography() {
        return p_biography;
    }

    public String getP_birthday() {
        return p_birthday;
    }

    public String getP_place_of_birth() {
        return p_place_of_birth;
    }

    public String getP_profile_path() {
        return p_profile_path;
    }
}
