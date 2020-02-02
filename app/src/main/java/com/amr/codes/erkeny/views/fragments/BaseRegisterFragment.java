package com.amr.codes.erkeny.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amr.codes.erkeny.R;
import com.amr.codes.erkeny.views.customeView.CustomViewPager;
import com.amr.codes.erkeny.views.pagers.FragmentsPagerAdapter;

import java.io.Serializable;

public class BaseRegisterFragment extends BaseFragment {

    private View containerView;
    private CustomViewPager viewPager;
    private TabLayout tabLayout;
    private FragmentsPagerAdapter pagerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        containerView = inflater.inflate(R.layout.fragment_base_register, container, false);
        tabLayout =  (TabLayout) containerView.findViewById(R.id.two_way_reservation_fragment_tabs);
        viewPager = (CustomViewPager) containerView.findViewById(R.id.two_way_reservation_fragment_viewpager);
        createViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setPagingEnabled(false);

        createTabIcons();
        return containerView;
    }

    private void createTabIcons() {

        TextView clientTab = null;
        TextView companyTab = null;


        clientTab = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.fragment_tab_header, null);
        clientTab.setText(getString(R.string.str_client_registeration_text));

        companyTab = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.fragment_tab_header, null);
        companyTab.setText(getString(R.string.str_company_registeration_text));

        tabLayout.getTabAt(0).setCustomView(clientTab);
        tabLayout.getTabAt(1).setCustomView(companyTab);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void createViewPager(ViewPager viewPager) {

        pagerAdapter = new FragmentsPagerAdapter(getChildFragmentManager());
        ClientRegisterationFragment clientFragment = new ClientRegisterationFragment();
        CompanyRegisterationFragment companyFragment = new CompanyRegisterationFragment();


        pagerAdapter.addFrag(clientFragment, "");
        pagerAdapter.addFrag(companyFragment, "");

        viewPager.setAdapter(pagerAdapter);


    }
}
