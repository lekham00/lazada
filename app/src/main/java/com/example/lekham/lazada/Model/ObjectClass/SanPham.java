package com.example.lekham.lazada.Model.ObjectClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Le Kham on 6/19/2017.
 */

public class SanPham {
    @SerializedName("MASP")
    @Expose
    private Integer mASP;
    @SerializedName("TENSP")
    @Expose
    private String tENSP;
    @SerializedName("GIASP")
    @Expose
    private Integer gIASP;
    @SerializedName("THONGTINSP")
    @Expose
    private String tHONGTINSP;
    @SerializedName("HINHSPLON")
    @Expose
    private String hINHSPLON;
    @SerializedName("HINHSPNHO")
    @Expose
    private Object hINHSPNHO;
    @SerializedName("SOLUONG")
    @Expose
    private Integer sOLUONG;
    @SerializedName("MALSP")
    @Expose
    private Integer mALSP;
    @SerializedName("MATH")
    @Expose
    private Integer mATH;
    @SerializedName("MANV")
    @Expose
    private Integer mANV;
    @SerializedName("LUOTMUA")
    @Expose
    private Integer lUOTMUA;
    @SerializedName("DANHSACHCHITIETSANPHAM")
    @Expose
    private List<ChiTietSanPham> dANHSACHCHITIETSANPHAM = null;

    public Integer getMASP() {
        return mASP;
    }

    public void setMASP(Integer mASP) {
        this.mASP = mASP;
    }

    public String getTENSP() {
        return tENSP;
    }

    public void setTENSP(String tENSP) {
        this.tENSP = tENSP;
    }

    public Integer getGIASP() {
        return gIASP;
    }

    public void setGIASP(Integer gIASP) {
        this.gIASP = gIASP;
    }

    public String getTHONGTINSP() {
        return tHONGTINSP;
    }

    public void setTHONGTINSP(String tHONGTINSP) {
        this.tHONGTINSP = tHONGTINSP;
    }

    public String getHINHSPLON() {
        return hINHSPLON;
    }

    public void setHINHSPLON(String hINHSPLON) {
        this.hINHSPLON = hINHSPLON;
    }

    public Object getHINHSPNHO() {
        return hINHSPNHO;
    }

    public void setHINHSPNHO(Object hINHSPNHO) {
        this.hINHSPNHO = hINHSPNHO;
    }

    public Integer getSOLUONG() {
        return sOLUONG;
    }

    public void setSOLUONG(Integer sOLUONG) {
        this.sOLUONG = sOLUONG;
    }

    public Integer getMALSP() {
        return mALSP;
    }

    public void setMALSP(Integer mALSP) {
        this.mALSP = mALSP;
    }

    public Integer getMATH() {
        return mATH;
    }

    public void setMATH(Integer mATH) {
        this.mATH = mATH;
    }

    public Integer getMANV() {
        return mANV;
    }

    public void setMANV(Integer mANV) {
        this.mANV = mANV;
    }

    public Integer getLUOTMUA() {
        return lUOTMUA;
    }

    public void setLUOTMUA(Integer lUOTMUA) {
        this.lUOTMUA = lUOTMUA;
    }
    public List<ChiTietSanPham> getDANHSACHCHITIETSANPHAM() {
        return dANHSACHCHITIETSANPHAM;
    }

    public void setDANHSACHCHITIETSANPHAM(List<ChiTietSanPham> dANHSACHCHITIETSANPHAM) {
        this.dANHSACHCHITIETSANPHAM = dANHSACHCHITIETSANPHAM;
    }
}
