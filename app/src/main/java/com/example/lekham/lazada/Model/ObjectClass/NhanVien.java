package com.example.lekham.lazada.Model.ObjectClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Le Kham on 6/6/2017.
 */

public class NhanVien {
    @SerializedName("MANV")
    @Expose
    private Integer mANV;
    @SerializedName("HOTENNV")
    @Expose
    private String hOTENNV;
    @SerializedName("TENDANGNHAP")
    @Expose
    private String tENDANGNHAP;
    @SerializedName("MATKHAU")
    @Expose
    private String mATKHAU;
    @SerializedName("EMAIL")
    @Expose
    private String eMAIL;
    @SerializedName("DIACHI")
    @Expose
    private String dIACHI;
    @SerializedName("NGAYSINH")
    @Expose
    private String nGAYSINH;
    @SerializedName("SODT")
    @Expose
    private Object sODT;
    @SerializedName("GIOITINH")
    @Expose
    private Object gIOITINH;
    @SerializedName("MALOAINV")
    @Expose
    private Integer mALOAINV;
    @SerializedName("SENDEMAIL")
    @Expose
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
