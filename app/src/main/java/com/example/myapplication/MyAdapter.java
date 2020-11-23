package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyAdapter extends FragmentPagerAdapter
{
    private static int NUM_ITEMS = 3;

    public MyAdapter(@NonNull FragmentManager fm)
    {
        super(fm);
    }

    // Returns total number of pages
    @Override
    public int getCount()
    {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return new PersonalInfoFragment();
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return new StudentInfoFragment();
            case 2: // Fragment # 1 - This will show SecondFragment
                return new SummaryFragment();
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position)
    {
        return "Page " + position;
    }

}

