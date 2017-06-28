package com.example.lekham.lazada.Presenter;

import com.example.lekham.lazada.Model.ListProduct.ListProductContract;
import com.example.lekham.lazada.Model.ListProduct.ListProductInterator;
import com.example.lekham.lazada.Model.ObjectClass.SanPham;

import java.util.List;

/**
 * Created by Le Kham on 6/23/2017.
 */

public class ListProductPresenter implements ListProductContract.Presenter, ListProductContract.OnElectronicsListener {

    ListProductContract.View mView;
    ListProductInterator mListProductInterator;

    public ListProductPresenter(ListProductContract.View view) {
        mView = view;
        mListProductInterator = new ListProductInterator(this);

    }


    @Override
    public void getListProductBrand(int id, int skip, int take) {
        mListProductInterator.performGetListProductBrand(id, skip, take);
    }

    @Override
    public void onResultGetListProduct(List<SanPham> sanPhamList) {
        mView.onResultGetListProduct(sanPhamList);
    }
}
