package com.example.lekham.lazada.View.ReviewProduct;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lekham.lazada.R;
import com.example.lekham.lazada.Until.Number;

/**
 * Created by Le Kham on 7/2/2017.
 */

public class ReviewProductFragment extends Fragment {

    View mView;
    TextView mTxtTitle, mTxtPrice;

    public static ReviewProductFragment initFragment(Bundle bundle) {
        ReviewProductFragment reviewProductFragment = new ReviewProductFragment();
        reviewProductFragment.setArguments(bundle);
        return reviewProductFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_review_product, container, false);
        mTxtTitle = (TextView) mView.findViewById(R.id.txtTitle);
        mTxtPrice = (TextView) mView.findViewById(R.id.txtPrice);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            handelInformation(bundle);
        }
        return mView;
    }

    private void handelInformation(Bundle bundle) {
        mTxtTitle.setText(bundle.getString(ReviewProductActitvity.KEY_TITLE));
        mTxtPrice.setText(Number.formatPrice(bundle.getInt(ReviewProductActitvity.KEY_PRICE)) + " VND");
    }
}
