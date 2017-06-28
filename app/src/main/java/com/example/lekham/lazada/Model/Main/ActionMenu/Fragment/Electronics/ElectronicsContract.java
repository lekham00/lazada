package com.example.lekham.lazada.Model.Main.ActionMenu.Fragment.Electronics;

import com.example.lekham.lazada.Model.ObjectClass.SanPham;
import com.example.lekham.lazada.Model.ObjectClass.ThuongHieu;

import java.util.List;

/**
 * Created by Le Kham on 6/14/2017.
 */

public interface ElectronicsContract {
    interface View {
        void onResultGetListBrand(List<ThuongHieu> thuongHieuList);

        void onResultGetListProduct(List<SanPham> sanPhamList);
    }

    interface Presenter {
        void getListBrand(int id);

        void getListProduct(int id);
    }

    interface Interator {
        void performFetListBrand(int id);

        void performGetListProduct(int id);
    }

    interface OnElectronicsListener {
        void onResultGetListBrand(List<ThuongHieu> thuongHieuList);

        void onResultGetListProduct(List<SanPham> sanPhamList);
    }
}
