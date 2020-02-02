package com.amr.codes.erkeny.views.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amr.codes.erkeny.R;

public class MapFragment extends BaseFragment {

    private View mapFragmentView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      mapFragmentView = inflater.inflate(R.layout.fragment_map, container, false);

      return mapFragmentView;
    }
}
