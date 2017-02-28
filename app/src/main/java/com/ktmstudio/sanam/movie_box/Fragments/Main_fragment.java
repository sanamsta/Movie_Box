package com.ktmstudio.sanam.movie_box.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ktmstudio.sanam.movie_box.Fragments.Movies_list_fragment;
import com.ktmstudio.sanam.movie_box.Infrastucture.Movies_Application;
import com.ktmstudio.sanam.movie_box.R;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class Main_fragment extends Fragment implements TabLayout.OnTabSelectedListener {
    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    Custom_adapter custom_adapter;
    public int index;


    public Main_fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        custom_adapter = new Custom_adapter(getChildFragmentManager());
        custom_adapter.addFragments(new Movies_list_fragment());
        custom_adapter.title = custom_adapter.getindex(index);


        viewPager.setAdapter(custom_adapter);
        // viewPager.setOffscreenPageLimit(2);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        index = getArguments().getInt("index");


        View v = inflater.inflate(R.layout.fragment_main_fragment, container, false);

        ButterKnife.bind(this, v);


        return v;
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {


    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    private class Custom_adapter extends FragmentStatePagerAdapter {

        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();


        String[] title;

        public Custom_adapter(FragmentManager childFragmentManager) {
            super(childFragmentManager);
        }


        public void addFragments(Fragment fragment) {

            this.fragmentArrayList.add(fragment);
            //tabtitle.add(title);

        }

        @Override
        public int getCount() {
            return title.length;
        }

        @Override
        public Fragment getItem(int position) {


            Movies_list_fragment fragment = new Movies_list_fragment();
            Bundle bundle = new Bundle();
            bundle.putInt("edttext", position);
            bundle.putInt("mp_index", index);
            fragment.setArguments(bundle);
            return fragment;


        }


        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }

        public String[] getindex(int index) {

            switch (index) {

                case 0:
                    return getActivity().getApplicationContext().getResources().getStringArray(R.array.Movie_Category);


                case 1:
                    return getActivity().getApplicationContext().getResources().getStringArray(R.array.Tv_series);

                case 2:
                    return getActivity().getApplicationContext().getResources().getStringArray(R.array.genres_tabTitle);

                case 3:

                    tabLayout.setVisibility(View.GONE);
                    return new String[]{""};

                default:
                    return null;

            }
        }

    }
}
