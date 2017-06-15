package com.example.lekham.lazada.Model.Main.ActionMenu.Fragment.Electronics;

import com.example.lekham.lazada.Model.ObjectClass.ThuongHieu;

import java.util.List;

/**
 * Created by Le Kham on 6/14/2017.
 */

public interface ElectronicsContract {
    interface View {
        void onResultGetListBrand(List<ThuongHieu> thuongHieuList);
    }
    interface Presenter {
        void getListBrand();
    }

    interface Interator {
        void performFetListBrand();
    }
    interface OnElectronicsListener {
        void onResultGetListBrand(List<ThuongHieu> thuongHieuList);
    }
}
