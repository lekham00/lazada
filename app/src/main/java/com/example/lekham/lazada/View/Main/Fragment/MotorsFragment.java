package com.example.lekham.lazada.View.Main.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lekham.lazada.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MotorsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MotorsFragment extends Fragment {

    public MotorsFragment() {
        // Required empty public constructor
    }


    public static MotorsFragment newInstance(String param1, String param2) {
        MotorsFragment fragment = new MotorsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_motors, container, false);
    }

}
