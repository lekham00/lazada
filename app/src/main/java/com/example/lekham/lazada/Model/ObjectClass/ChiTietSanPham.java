package com.example.lekham.lazada.Model.ObjectClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Le Kham on 6/28/2017.
 */

public class ChiTietSanPham {
    @SerializedName("MACTSP")
    @Expose
    private Integer mACTSP;
    @SerializedName("MASP")
    @Expose
    private Integer mASP;
    @SerializedName("TENCHITIET")
    @Expose
    private String tENCHITIET;
    @SerializedName("GIATRI")
    @Expose
    private String gIATRI;

    public Integer getMACTSP() {
        return mACTSP;
    }

    public void setMACTSP(Integer mACTSP) {
        this.mACTSP = mACTSP;
    }

    public Integer getMASP() {
        return mASP;
    }

    public void setMASP(Integer mASP) {
        this.mASP = mASP;
    }

    public String getTENCHITIET() {
        return tENCHITIET;
    }

    public void setTENCHITIET(String tENCHITIET) {
        this.tENCHITIET = tENCHITIET;
    }

    public String getGIATRI() {
        return gIATRI;
    }

    public void setGIATRI(String gIATRI) {
        this.gIATRI = gIATRI;
    }
}
