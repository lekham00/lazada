package com.example.lekham.lazada.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.lekham.lazada.R;
import com.example.lekham.lazada.View.DetailProduct.Fragment.OverviewFragment;
import com.example.lekham.lazada.View.DetailProduct.Fragment.ProductDetailFragment;
import com.example.lekham.lazada.View.DetailProduct.Fragment.QAFragment;
import com.example.lekham.lazada.View.DetailProduct.Fragment.RatingReviewFragment;

/**
 * Created by Le Kham on 6/27/2017.
 */

public class ViewPagerDetailProductAdapter extends FragmentStatePagerAdapter {

    Context mContext;

    public ViewPagerDetailProductAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new OverviewFragment();
            case 1:
                return new ProductDetailFragment();
            case 2:
                return new QAFragment();
            case 3:
                return new RatingReviewFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getResources().getString(R.string.tabs_view_pager_overview);
            case 1:
                return mContext.getResources().getString(R.string.tabs_view_pager_product_detail);
            case 2:
                return mContext.getResources().getString(R.string.tabs_view_pager_qa);
            case 3:
                return mContext.getResources().getString(R.string.tabs_view_pager_rating_review);
        }
        return super.getPageTitle(position);
    }
}
