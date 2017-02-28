package com.ktmstudio.sanam.movie_box;

import com.ktmstudio.sanam.movie_box.Model.CastCrew;
import com.ktmstudio.sanam.movie_box.Services.Movies_response;

import java.util.List;

/**
 * Created by iosdeveloper on 2/1/17.
 */

public interface DataProvider {

    void DataCompleted(Movies_response.MovieResponse movieResponse);


}
