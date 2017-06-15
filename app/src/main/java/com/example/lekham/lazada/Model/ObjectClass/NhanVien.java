package com.example.lekham.lazada.Model.ObjectClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Le Kham on 6/6/2017.
 */

public class NhanVien {
    @SerializedName("MANV")
    private Integer mANV;
    @SerializedName("HOTENNV")
    private String hOTENNV;
    @SerializedName("TENDANGNHAP")
    private String tENDANGNHAP;
    @SerializedName("MATKHAU")
    private String mATKHAU;
    @SerializedName("EMAIL")
    private String eMAIL;
    @SerializedName("DIACHI")
    private String dIACHI;
    @SerializedName("NGAYSINH")
    private String nGAYSINH;
    @SerializedName("SODT")
    private Object sODT;
    @SerializedName("GIOITINH")
    private Object gIOITINH;
    @SerializedName("MALOAINV")
    private Integer mALOAINV;
    @SerializedName("SENDEMAIL")
    private Boolean sENDEMAIL;

    public Integer getMANV() {
        return mANV;
    }

    public void setMANV(Integer mANV) {
        this.mANV = mANV;
    }

    public String getHOTENNV() {
        return hOTENNV;
    }

    public void setHOTENNV(String hOTENNV) {
        this.hOTENNV = hOTENNV;
    }

    public String getTENDANGNHAP() {
        return tENDANGNHAP;
    }

    public void setTENDANGNHAP(String tENDANGNHAP) {
        this.tENDANGNHAP = tENDANGNHAP;
    }

    public String getMATKHAU() {
        return mATKHAU;
    }

    public void setMATKHAU(String mATKHAU) {
        this.mATKHAU = mATKHAU;
    }

    public String getEMAIL() {
        return eMAIL;
    }

    public void setEMAIL(String eMAIL) {
        this.eMAIL = eMAIL;
    }

    public String getDIACHI() {
        return dIACHI;
    }

    public void setDIACHI(String dIACHI) {
        this.dIACHI = dIACHI;
    }

    public String getNGAYSINH() {
        return nGAYSINH;
    }

    public void setNGAYSINH(String nGAYSINH) {
        this.nGAYSINH = nGAYSINH;
    }

    public Object getSODT() {
        return sODT;
    }

    public void setSODT(Object sODT) {
        this.sODT = sODT;
    }

    public Object getGIOITINH() {
        return gIOITINH;
    }

    public void setGIOITINH(Object gIOITINH) {
        this.gIOITINH = gIOITINH;
    }

    public Integer getMALOAINV() {
        return mALOAINV;
    }

    public void setMALOAINV(Integer mALOAINV) {
        this.mALOAINV = mALOAINV;
    }

    public Boolean getSENDEMAIL() {
        return sENDEMAIL;
    }

    public void setSENDEMAIL(Boolean sENDEMAIL) {
        this.sENDEMAIL = sENDEMAIL;
    }

}
