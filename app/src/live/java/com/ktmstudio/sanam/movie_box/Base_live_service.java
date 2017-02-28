package com.ktmstudio.sanam.movie_box;

import com.ktmstudio.sanam.movie_box.Infrastucture.Movies_Application;
import com.squareup.otto.Bus;

/**
 * Created by iosdeveloper on 10/27/16.
 */

public class Base_live_service {

    protected Movies_Application application;
    protected Bus bus;
    protected Api_services api_services;

    public Base_live_service(Movies_Application application,Api_services api_services) {

        this.api_services=api_services;
        this.application = application;
        bus = application.getBus();
        bus.register(this);
    }
}
