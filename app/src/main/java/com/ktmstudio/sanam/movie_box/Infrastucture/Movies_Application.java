package com.ktmstudio.sanam.movie_box.Infrastucture;

import android.app.Application;

import com.ktmstudio.sanam.movie_box.Module;
import com.squareup.otto.Bus;

/**
 * Created by iosdeveloper on 10/27/16.
 */

public class Movies_Application extends Application {

    private Bus bus;
    public  static  final String apikey="f1d5891317273220e1fc030f022d3859";
    public  static  final String baseurl="https://api.themoviedb.org/3/";
    public  static final String picture_url="http://image.tmdb.org/t/p/w185/";
    public  static final String backdrop_pic_url="http://image.tmdb.org/t/p/w500/";

    public int index;


    public Movies_Application() {

        this.bus = new Bus();

    }

    public Bus getBus() {

        return bus;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Module.Register(this);
    }
}
