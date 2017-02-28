package com.ktmstudio.sanam.movie_box.Adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ktmstudio.sanam.movie_box.Fragments.BiographyFragment;
import com.ktmstudio.sanam.movie_box.Fragments.Movies_list_fragment;
import com.ktmstudio.sanam.movie_box.Fragments.Movies_played;
import com.ktmstudio.sanam.movie_box.Model.Biography;

/**
 * Created by backtrack on 2/12/2017.
 */

public class InfoPager_Adapter extends FragmentStatePagerAdapter {

    String[] title={"Biography","Movies Played"};
    private String Person_id;

    public InfoPager_Adapter(FragmentManager fm,String person_id) {
        super(fm);
        this.Person_id = person_id;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 0:
                Bundle bundle = new Bundle();
                bundle.putString("person_id",Person_id);
                BiographyFragment biographyFragment = new BiographyFragment();
                biographyFragment.setArguments(bundle);

                return biographyFragment;

            case 1: return new Movies_played();

            default: return null;
        }


    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
