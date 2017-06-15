package com.example.lekham.lazada.View.Main.Fragment;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.lekham.lazada.Adapter.BaseRecyclerViewAdapter;
import com.example.lekham.lazada.Adapter.BrandAdapter;
import com.example.lekham.lazada.Model.Main.ActionMenu.Fragment.Electronics.ElectronicsContract;
import com.example.lekham.lazada.Model.ObjectClass.ThuongHieu;
import com.example.lekham.lazada.Presenter.ElectronicsPresenter;
import com.example.lekham.lazada.R;
import com.example.lekham.lazada.Adapter.RecyclerviewAdapterThemOne;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ElectronicsFragment extends Fragment implements ElectronicsContract.View, BaseRecyclerViewAdapter.OnRecyclerItemClicked {
    public static final String TAG = ElectronicsFragment.class.getSimpleName();
    private RecyclerView mRecylerviewTopLike;
    private View view;
    private ElectronicsPresenter mElectronicsPresenter;
    private BrandAdapter brandAdapter;
    private List<ThuongHieu> mThuongHieuList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_electronics, container, false);
        mElectronicsPresenter = new ElectronicsPresenter(this);
        init();
        setUpRecyclerViewTopLike();
        handelGetListBrand();
        return view;
    }

    private void init() {

    }

    private void setUpRecyclerViewTopLike() {
        mRecylerviewTopLike = (RecyclerView) view.findViewById(R.id.recylerviewTopLike);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3, GridLayoutManager.HORIZONTAL, false);
        mRecylerviewTopLike.setLayoutManager(gridLayoutManager);
        brandAdapter = new BrandAdapter(getContext(), this);
        mRecylerviewTopLike.setAdapter(brandAdapter);
    }

    @Override
    public void onResultGetListBrand(List<ThuongHieu> thuongHieuList) {
        if (thuongHieuList != null && thuongHieuList.size() > 0) {
            Log.d(TAG, "thuongHieuList :" + thuongHieuList);
            mThuongHieuList = thuongHieuList;
            brandAdapter.setItem(mThuongHieuList);
            brandAdapter.notifyDataSetChanged();
        }
    }

    private void handelGetListBrand() {
        AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                mElectronicsPresenter.getListBrand();
                return null;
            }
        };
        asyncTask.execute();
    }

    @Override
    public void onItemClicked(View view, int position) {

    }


}
