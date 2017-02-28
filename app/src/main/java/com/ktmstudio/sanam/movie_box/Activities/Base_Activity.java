package com.ktmstudio.sanam.movie_box.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ktmstudio.sanam.movie_box.Infrastucture.Movies_Application;
import com.squareup.otto.Bus;

/**
 * Created by iosdeveloper on 10/27/16.
 */

public class Base_Activity extends AppCompatActivity {

protected Movies_Application movies_application;
 protected Bus bus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        movies_application= (Movies_Application) getApplication();

        bus=movies_application.getBus();
        bus.register(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bus.unregister(this);
    }
}
