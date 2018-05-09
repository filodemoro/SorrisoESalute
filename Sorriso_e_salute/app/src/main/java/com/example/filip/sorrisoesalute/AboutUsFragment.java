package com.example.filip.sorrisoesalute;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by filip on 28/02/2018.
 */

public class AboutUsFragment extends Fragment {

    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);
        getActivity().setTitle(R.string.main_activity_aboutUs_drawer);

        return view;
    }
}
