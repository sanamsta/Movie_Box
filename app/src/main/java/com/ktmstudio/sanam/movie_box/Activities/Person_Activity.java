package com.ktmstudio.sanam.movie_box.Activities;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ktmstudio.sanam.movie_box.Adapters.InfoPager_Adapter;
import com.ktmstudio.sanam.movie_box.DataProvider;
import com.ktmstudio.sanam.movie_box.Infrastucture.Movies_Application;
import com.ktmstudio.sanam.movie_box.Model.Biography;
import com.ktmstudio.sanam.movie_box.Model.CastCrew;
import com.ktmstudio.sanam.movie_box.Model.Movie_model;
import com.ktmstudio.sanam.movie_box.Model.Sub_Movie_detail;
import com.ktmstudio.sanam.movie_box.R;
import com.ktmstudio.sanam.movie_box.Services.Movies_response;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Person_Activity extends Base_Activity implements AppBarLayout.OnOffsetChangedListener, DataProvider {
    private static final int PERCENTAGE_TO_ANIMATE_AVATAR = 20;
    private boolean mIsAvatarShown = true;
    private int mMaxScrollSize;
    private ImageView mProfileImage;

    @BindView(R.id.person_name)
    TextView personname;

    @BindView(R.id.person_birthplace)
    TextView person_birthplace;

    @BindView(R.id.materialup_tabs)
    TabLayout tabLayout;

    @BindView(R.id.materialup_viewpager)
    ViewPager viewPager;

    @BindView(R.id.materialup_appbar)
    AppBarLayout appBarLayout;
    Toolbar toolbar;
    Movie_model example;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_fragment);

        toolbar = (Toolbar) findViewById(R.id.materialup_toolbar);
        mProfileImage = (ImageView) findViewById(R.id.materialup_profile_image);
        setSupportActionBar(toolbar);
//        getWindow().getDecorView().setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        ButterKnife.bind(this);

        appBarLayout.addOnOffsetChangedListener(this);

        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

     example = Parcels.unwrap(getIntent().getParcelableExtra("Property"));
        personname.setText(example.getPersonName());
        Picasso.with(this).
                load(Movies_Application.picture_url + example.get_moviepice())
                .fit()
                .centerCrop()
                .into(mProfileImage);

        InfoPager_Adapter adapter = new InfoPager_Adapter(getSupportFragmentManager(), example.getPerson_id());
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
        // bus.post(new Movies_response.MovieRequest());
        movies_application.getBus().post(new Movies_response.MovieRequest("person", "person", example.getPerson_id(), this, 1));


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {


        if (mMaxScrollSize == 0)
            mMaxScrollSize = appBarLayout.getTotalScrollRange();

        int percentage = (Math.abs(i)) * 100 / mMaxScrollSize;


        if (percentage >= PERCENTAGE_TO_ANIMATE_AVATAR && mIsAvatarShown) {
            mIsAvatarShown = false;

            mProfileImage.animate().scaleY(0).scaleX(0).setDuration(200).start();

        }

        if (percentage <= PERCENTAGE_TO_ANIMATE_AVATAR && !mIsAvatarShown) {
            mIsAvatarShown = true;


            mProfileImage.animate()
                    .scaleY(1).scaleX(1)
                    .start();
        }

        if (i * (-1) > mMaxScrollSize - 50) {

            appBarLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        } else {
            appBarLayout.setBackgroundColor(getResources().getColor(R.color.white));
        }
    }


    @Override
    public void DataCompleted(Movies_response.MovieResponse movieResponse) {

        ArrayList<Biography> arrayList = (ArrayList<Biography>) (ArrayList<?>) (movieResponse.movie_dataArrayList);
        Biography biography = arrayList.get(0);

        person_birthplace.setText(biography.getP_place_of_birth());

        bus.post(biography);

        bus.post(example);


    }



}
