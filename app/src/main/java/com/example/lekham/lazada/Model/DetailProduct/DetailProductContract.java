package com.example.lekham.lazada.Model.DetailProduct;

import com.example.lekham.lazada.Model.ObjectClass.SanPham;

/**
 * Created by Le Kham on 6/28/2017.
 */

public interface DetailProductContract {

    interface View {
        void resultDetailProduct(SanPham sanPham);
    }

    interface Interator {
        void performgetDetailProduct(int id);
    }

    interface Presenter {
        void getDetailProduct(int id);
    }

    interface OnDetailProductListener {
        void resultDetailProduct(SanPham sanPham);
    }

}
