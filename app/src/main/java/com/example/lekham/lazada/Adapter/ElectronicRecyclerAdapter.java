package com.example.lekham.lazada.Adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.lekham.lazada.Model.ObjectClass.Electronic;
import com.example.lekham.lazada.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by Le Kham on 6/16/2017.
 */

public class ElectronicRecyclerAdapter extends RecyclerView.Adapter<ElectronicRecyclerAdapter.MyViewHolder> {

    private Context mContext;
    private List<Electronic> mElectronics = Collections.emptyList();
    private BrandAdapter mBrandAdapter;
    private ProductAdapter mProductAdapter;
    private BaseRecyclerViewAdapter.OnRecyclerItemClicked mOnRecyclerBrandItemClicked;
    private BaseRecyclerViewAdapter.OnRecyclerItemClicked mOnRecyclerProductItemClicked;

    public ElectronicRecyclerAdapter(Context context, BaseRecyclerViewAdapter.OnRecyclerItemClicked onRecyclerBrandItemClicked,
                                     BaseRecyclerViewAdapter.OnRecyclerItemClicked onRecyclerProductItemClicked) {
        mContext = context;
        mOnRecyclerBrandItemClicked = onRecyclerBrandItemClicked;
        mOnRecyclerProductItemClicked = onRecyclerProductItemClicked;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_electronic_recycler_adapter, parent, false);
        return new MyViewHolder(view);
    }

    public void setListItem(List<Electronic> electronics) {
        mElectronics = electronics;

        Log.d("LK", electronics.size() + "");
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Electronic electronic = mElectronics.get(position);
        if (electronic != null) {
            if (electronic.getThuongHieuList() != null && electronic.getThuongHieuList().size() > 0) {
                handelBrandAdapter(holder, electronic);
            }
            if (electronic.getSanPhamList() != null && electronic.getSanPhamList().size() > 0)
                handelProductAdapter(holder, electronic);
        }
    }


    private void handelBrandAdapter(MyViewHolder holder, Electronic electronic) {
        Log.d("LK", "handelBrandAdapter :" + electronic.getThuongHieuList());
        mBrandAdapter = new BrandAdapter(mContext, mOnRecyclerBrandItemClicked, electronic.getThuongHieuList());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3, GridLayoutManager.HORIZONTAL, false);
        holder.mRecyclerViewBrand.setLayoutManager(gridLayoutManager);
        holder.mRecyclerViewBrand.setAdapter(mBrandAdapter);
        holder.mRecyclerViewBrand.setNestedScrollingEnabled(false);
//        mBrandAdapter.setItem(electronic.getThuongHieuList());
        mBrandAdapter.notifyDataSetChanged();
    }

    private void handelProductAdapter(MyViewHolder holder, Electronic electronic) {
        Log.d("LK", "handelProductAdapter :" + electronic.getSanPhamList());
        mProductAdapter = new ProductAdapter(mContext, mOnRecyclerProductItemClicked, electronic.getSanPhamList());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 1, GridLayoutManager.HORIZONTAL, false);
        holder.mRecylerViewProductTopBuy.setLayoutManager(gridLayoutManager);
        holder.mRecylerViewProductTopBuy.setNestedScrollingEnabled(false);
        holder.mRecylerViewProductTopBuy.setAdapter(mProductAdapter);
        mProductAdapter.setWidthCardView(130);
//        mProductAdapter.setItem(electronic.getSanPhamList());
        mProductAdapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mElectronics.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        View mViewBannerOne, mViewBannerTwo;
        ImageView mImgBannerOne, mImgBannerTwo;
        RecyclerView mRecyclerViewBrand, mRecylerViewProductTopBuy;

        public MyViewHolder(View itemView) {
            super(itemView);
            mViewBannerOne = itemView.findViewById(R.id.viewBannerOne);
            mImgBannerOne = (ImageView) mViewBannerOne.findViewById(R.id.imgBanner);
            mViewBannerTwo = itemView.findViewById(R.id.viewBannerTwo);
            mImgBannerTwo = (ImageView) mViewBannerTwo.findViewById(R.id.imgBanner);
            mRecyclerViewBrand = (RecyclerView) itemView.findViewById(R.id.recylerviewTopLike);
            mRecylerViewProductTopBuy = (RecyclerView) itemView.findViewById(R.id.recylerViewProductTopBuy);
        }
    }
}
