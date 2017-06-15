package com.example.lekham.lazada.Model.ObjectClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Le Kham on 6/14/2017.
 */

public class ThuongHieu {
    @SerializedName("MATH")
    @Expose
    private Integer mATH;
    @SerializedName("TENTH")
    @Expose
    private String tENTH;
    @SerializedName("LUOTMUA")
    @Expose
    private Integer lUOTMUA;
    @SerializedName("MALSP")
    @Expose
    private Integer mALSP;
    @SerializedName("HINHTHUONGHIEU")
    @Expose
    private String hINHTHUONGHIEU;

    public Integer getMATH() {
        return mATH;
    }

    public void setMATH(Integer mATH) {
        this.mATH = mATH;
    }

    public String getTENTH() {
        return tENTH;
    }

    public void setTENTH(String tENTH) {
        this.tENTH = tENTH;
    }

    public Integer getLUOTMUA() {
        return lUOTMUA;
    }

    public void setLUOTMUA(Integer lUOTMUA) {
        this.lUOTMUA = lUOTMUA;
    }

    public Integer getMALSP() {
        return mALSP;
    }

    public void setMALSP(Integer mALSP) {
        this.mALSP = mALSP;
    }

    public String getHINHTHUONGHIEU() {
        return hINHTHUONGHIEU;
    }

    public void setHINHTHUONGHIEU(String hINHTHUONGHIEU) {
        this.hINHTHUONGHIEU = hINHTHUONGHIEU;
    }
}
