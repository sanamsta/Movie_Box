package com.ktmstudio.sanam.movie_box;

import android.content.Context;
import android.widget.Toast;

import com.ktmstudio.sanam.movie_box.Infrastucture.Connectivity;
import com.ktmstudio.sanam.movie_box.Infrastucture.Movies_Application;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by iosdeveloper on 11/7/16.
 */

public class Module {

    public  Context context;

public static void Register(Movies_Application application){


    new Live_movie_services(application,api_services(application));

}

    private  static Api_services api_services(final Context context){


        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client=new OkHttpClient.Builder().addInterceptor(interceptor).build();


        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Movies_Application.baseurl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return  retrofit.create(Api_services.class);

    }
}
