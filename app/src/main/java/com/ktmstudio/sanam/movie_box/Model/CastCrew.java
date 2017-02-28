package com.ktmstudio.sanam.movie_box.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by backtrack on 2/26/2017.
 */

public class CastCrew {


    @SerializedName("cast")
   public List<CastCrew_list> castCrew_lists;

    public class CastCrew_list{

        @SerializedName("character")
        String cast_character;

        @SerializedName("name")
        String cast_name;

        @SerializedName("profile_path")
        String cast_profile_path;

        @SerializedName("id")
        int cast_id;

        public String getCast_character() {
            return cast_character;
        }

        public String getCast_name() {
            return cast_name;
        }

        public String getCast_profile_path() {
            return cast_profile_path;
        }

        public int getCast_id() {
            return cast_id;
        }
    }
}
