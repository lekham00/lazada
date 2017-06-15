package com.example.lekham.lazada.Presenter;

import com.example.lekham.lazada.Model.Main.ActionMenu.Fragment.Electronics.ElectronicsContract;
import com.example.lekham.lazada.Model.Main.ActionMenu.Fragment.Electronics.ElectronicsInterator;
import com.example.lekham.lazada.Model.ObjectClass.ThuongHieu;

import java.util.List;

/**
 * Created by Le Kham on 6/14/2017.
 */

public class ElectronicsPresenter implements ElectronicsContract.Presenter, ElectronicsContract.OnElectronicsListener {

    ElectronicsContract.View mView;
    ElectronicsInterator mElectronicsInterator;

    public ElectronicsPresenter(ElectronicsContract.View view) {
        mView = view;
        mElectronicsInterator = new ElectronicsInterator(this);
    }

    @Override
    public void getListBrand() {
        mElectronicsInterator.performFetListBrand();
    }

    @Override
    public void onResultGetListBrand(List<ThuongHieu> thuongHieuList) {
        mView.onResultGetListBrand(thuongHieuList);
    }
}
