package com.rahulgupta.androidapp.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.rahulgupta.androidapp.Fragments.ViewPagerFragment;

/**
 * Created by rahul.gupta on 3/9/2016.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {



    private static int NUM_ITEMS = 4;

    public ViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);

    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return ViewPagerFragment.newInstance(0, "Fragment # 1");
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return ViewPagerFragment.newInstance(1, "Fragment # 2");
            case 2: // Fragment # 1 - This will show FirstFragment different title
                return ViewPagerFragment.newInstance(2, "Fragment # 3");
            case 3: // Fragment # 1 - This will show FirstFragment different title
                return ViewPagerFragment.newInstance(3, "Fragment # 4");

            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }

}


