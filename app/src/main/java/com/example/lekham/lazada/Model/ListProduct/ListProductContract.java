package com.example.lekham.lazada.Model.ListProduct;

import com.example.lekham.lazada.Model.ObjectClass.SanPham;
import com.example.lekham.lazada.Model.ObjectClass.ThuongHieu;

import java.util.List;

/**
 * Created by Le Kham on 6/23/2017.
 */

public interface ListProductContract {
    interface View {
        void onResultGetListProduct(List<SanPham> sanPhamList);
    }

    interface Presenter {
        void getListProductBrand(int id, int skip, int take);
    }

    interface Interator {
        void performGetListProductBrand(int id, int skip, int take);
    }

    interface OnElectronicsListener {
        void onResultGetListProduct(List<SanPham> sanPhamList);
    }
}
