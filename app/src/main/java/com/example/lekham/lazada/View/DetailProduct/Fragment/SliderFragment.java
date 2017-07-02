package com.example.lekham.lazada.View.DetailProduct.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.lekham.lazada.R;

/**
 * Created by Le Kham on 6/29/2017.
 */

public class SliderFragment extends Fragment {

    public static final String KEY_IMAGES = "KEY_IMAGES";
    ImageView mImageView;

//    public static SliderFragment init(Bundle bundle) {
//        SliderFragment sliderFragment = new SliderFragment();
//        sliderFragment.setArguments(bundle);
//        return sliderFragment;
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_slider, container, false);
        mImageView = (ImageView) view.findViewById(R.id.images);
        Log.d("LK", "onCreateView");
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("LK", "onAttach");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("LK", "onActivityCreated");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("LK", "onResume");
        if (getArguments() != null) {
            Log.d("LK", "getArguments().getString(KEY_IMAGES) :" + getArguments().getString(KEY_IMAGES));
            Glide.with(getContext()).load(getArguments().getString(KEY_IMAGES))
                    .thumbnail(0.5f)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(mImageView);
        }
    }
}
