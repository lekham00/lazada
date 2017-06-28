package com.example.lekham.lazada.Presenter;

import com.example.lekham.lazada.Model.DetailProduct.DetailProductContract;
import com.example.lekham.lazada.Model.DetailProduct.DetailProductInterator;
import com.example.lekham.lazada.Model.ObjectClass.SanPham;

/**
 * Created by Le Kham on 6/29/2017.
 */

public class DetailProductPresenter implements DetailProductContract.Presenter, DetailProductContract.OnDetailProductListener {

    private DetailProductInterator mDetailProductInterator;
    private DetailProductContract.View mView;

    public DetailProductPresenter(DetailProductContract.View view) {
        mView = view;
        mDetailProductInterator = new DetailProductInterator(this);
    }

    @Override
    public void getDetailProduct(int id) {
        mDetailProductInterator.performgetDetailProduct(id);
    }

    @Override
    public void resultDetailProduct(SanPham sanPham) {
        mView.resultDetailProduct(sanPham);
    }
}
