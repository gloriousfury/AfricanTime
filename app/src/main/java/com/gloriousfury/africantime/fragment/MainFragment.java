package com.gloriousfury.africantime.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gloriousfury.africantime.R;

import java.util.ArrayList;


public class MainFragment extends Fragment {

    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    Toolbar toolbar;
    private TodoSectionsPagerAdapter mSectionsPagerAdapter;
    TabLayout tabLayout;
    ViewPager mViewPager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        tabLayout = (TabLayout) v.findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) v.findViewById(R.id.viewpager);
//        getActivity().getActionBar().setTitle(" ");
        setUpViews();
        return v;


    }

    private void setUpViews() {


        mSectionsPagerAdapter = new TodoSectionsPagerAdapter(getChildFragmentManager());

        mViewPager.setAdapter(mSectionsPagerAdapter);

        tabLayout.setupWithViewPager(mViewPager);
    }


    public class TodoSectionsPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> _fragments;



        public TodoSectionsPagerAdapter(FragmentManager fm) {
            super(fm);

//            this._fragments = new ArrayList<Fragment>();
        }


//        public void add(Fragment fragment) {
//            this._fragments.add(fragment);
//        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {

                return TimeListFragment.newInstance();
            }else if (position == 1) {

                return LearningFragment.newInstance();
            }
            else {
                return BasicFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {

                case 0:

                    return "TIME";
                case 1:
                    return "LEARN";

            }
            return null;
        }
    }


}
