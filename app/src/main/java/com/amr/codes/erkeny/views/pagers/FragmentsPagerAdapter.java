package com.amr.codes.erkeny.views.pagers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.amr.codes.erkeny.views.fragments.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eslamm on 1/24/2016.
 */
public class FragmentsPagerAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> fragments;
    private List<String> fragmentsTitles;

//    private Context context;

    public FragmentsPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
        fragmentsTitles = new ArrayList<>();
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {

        return fragments.get(position);
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        if (fragments == null)
            return 0;
        else
            return fragments.size();
    }

    public void addFrag(BaseFragment fragment, String title) {
        fragments.add(fragment);
        fragmentsTitles.add(title);
    }
    public void addFrag(BaseFragment fragment, String title, int position) {
        fragments.add(position,fragment);
        fragmentsTitles.add(position,title);
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentsTitles.get(position);
    }
}
