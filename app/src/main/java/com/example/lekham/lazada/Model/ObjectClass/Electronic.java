package com.example.lekham.lazada.Model.ObjectClass;

import java.util.List;

/**
 * Created by Le Kham on 6/18/2017.
 */

public class Electronic {
    private List<ThuongHieu> thuongHieuList;
    private List<SanPham> sanPhamList;

    public List<ThuongHieu> getThuongHieuList() {
        return thuongHieuList;
    }

    public void setThuongHieuList(List<ThuongHieu> thuongHieuList) {
        this.thuongHieuList = thuongHieuList;
    }

    public List<SanPham> getSanPhamList() {
        return sanPhamList;
    }

    public void setSanPhamList(List<SanPham> sanPhamList) {
        this.sanPhamList = sanPhamList;
    }
}
