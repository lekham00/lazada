package com.example.lekham.lazada.View.Main.Fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lekham.lazada.Adapter.BaseRecyclerViewAdapter;
import com.example.lekham.lazada.Adapter.ElectronicRecyclerAdapter;
import com.example.lekham.lazada.Model.Main.ActionMenu.Fragment.Electronics.ElectronicsContract;
import com.example.lekham.lazada.Model.ObjectClass.Electronic;
import com.example.lekham.lazada.Model.ObjectClass.SanPham;
import com.example.lekham.lazada.Model.ObjectClass.ThucDon;
import com.example.lekham.lazada.Model.ObjectClass.ThuongHieu;
import com.example.lekham.lazada.Presenter.ElectronicsPresenter;
import com.example.lekham.lazada.R;
import com.example.lekham.lazada.Until.TabLayoutMenu;
import com.example.lekham.lazada.View.ListProduct.ListProductActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ElectronicsFragment extends Fragment implements ElectronicsContract.View {
    public static final String TAG = ElectronicsFragment.class.getSimpleName();
    private RecyclerView mRecylerViewElectronic;
    private List<Electronic> electronics;
    private Electronic electronic = new Electronic();
    private View view;
    private ElectronicsPresenter mElectronicsPresenter;
    private ElectronicRecyclerAdapter mElectronicRecyclerAdapter;
    private int mIdTabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_electronics, container, false);
        handelGetIdTabLayout();
        mElectronicsPresenter = new ElectronicsPresenter(this);
        init();
        setElectronicRecyclerAdapter();
        Log.d("LK", "ElectronicsFragment");
        return view;
    }

    private void init() {

    }

    private void handelLoadData() {
        handelGetListBrand();
    }

    private void handelGetIdTabLayout() {
        ThucDon thucDon = TabLayoutMenu.initTabLayoutMenu(getContext()).getThucDonHashMap(getString(R.string.tabs_electronics).toString().hashCode());
        if (thucDon != null) {
            mIdTabLayout = thucDon.getMATHUCDON();
        }
    }

    private void setElectronicRecyclerAdapter() {
        mRecylerViewElectronic = (RecyclerView) view.findViewById(R.id.recylerViewElectronic);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecylerViewElectronic.setLayoutManager(layoutManager);
        mElectronicRecyclerAdapter = new ElectronicRecyclerAdapter(getContext(), onRecyclerBrandItemClicked, onRecyclerProductItemClicked);
        mRecylerViewElectronic.setAdapter(mElectronicRecyclerAdapter);
    }

    @Override
    public void onResultGetListBrand(List<ThuongHieu> thuongHieuList) {

        if (thuongHieuList != null && thuongHieuList.size() > 0) {
            electronic.setThuongHieuList(thuongHieuList);
            electronics.set(0, electronic);
            Log.d("LK", "onResultGetListBrand :" + electronics.size());
            mElectronicRecyclerAdapter.setListItem(electronics);
            mElectronicRecyclerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResultGetListProduct(List<SanPham> sanPhamList) {

        if (sanPhamList != null && sanPhamList.size() > 0) {
            electronic.setSanPhamList(sanPhamList);
            electronics.set(0, electronic);
            Log.d("LK", "onResultGetListProduct :" + electronics.size());
            mElectronicRecyclerAdapter.setListItem(electronics);
            mElectronicRecyclerAdapter.notifyDataSetChanged();

        }
    }

    private void handelGetListBrand() {
        AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                mElectronicsPresenter.getListBrand(mIdTabLayout);
                mElectronicsPresenter.getListProduct(mIdTabLayout);
                return null;
            }
        };
        asyncTask.execute();
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d("LK", "ElectronicsFragment  onResume");
        electronics = new ArrayList<>();
        electronics.add(electronic);
        handelLoadData();
    }

    BaseRecyclerViewAdapter.OnRecyclerItemClicked onRecyclerBrandItemClicked = new BaseRecyclerViewAdapter.OnRecyclerItemClicked() {
        @Override
        public void onItemClicked(View view, int position) {
            Log.d("LK", "BrandItem : " + position + "");
            if (electronics != null && electronics.size() > 0) {
                ThuongHieu thuongHieu = electronics.get(0).getThuongHieuList().get(position);
                if (thuongHieu != null)
                    ListProductActivity.startIntent(getContext(), thuongHieu.getMATH(), thuongHieu.getTENTH());
            }
        }
    };
    BaseRecyclerViewAdapter.OnRecyclerItemClicked onRecyclerProductItemClicked = new BaseRecyclerViewAdapter.OnRecyclerItemClicked() {
        @Override
        public void onItemClicked(View view, int position) {
            Log.d("LK", "ProductItem :" + position + "");
//            ListProductActivity.startIntent(getContext(), position);
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("LK", "onDestroy()");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("LK", "onStop()");
//        if (getActivity() != null) {
//            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//            Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_policies);
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.remove(fragment);
//            fragmentTransaction.commit();
//        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("LK", "onPause()()");
        if (getActivity() != null) {
//            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//            Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_policies);
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.remove(fragment);
//            fragmentTransaction.commit();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("LK", "onDestroyView()()");
//        FragmentManager fragManager = getActivity().getSupportFragmentManager();
//        Fragment fragment = fragManager.findFragmentById(R.id.fragment_policies);
//        if (fragment != null) {
//            fragManager.beginTransaction().remove(fragment).commit();
//        }
    }
}
