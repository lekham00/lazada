package com.example.lekham.lazada.Model.ObjectClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Le Kham on 6/17/2017.
 */

public class ThucDon {
    @SerializedName("MATHUCDON")
    @Expose
    private Integer mATHUCDON;
    @SerializedName("TENTHUCDON")
    @Expose
    private String tENTHUCDON;
    @SerializedName("THUTU")
    @Expose
    private Integer tHUTU;
    @SerializedName("CHOPHEP")
    @Expose
    private Boolean cHOPHEP;

    public Integer getMATHUCDON() {
        return mATHUCDON;
    }

    public void setMATHUCDON(Integer mATHUCDON) {
        this.mATHUCDON = mATHUCDON;
    }

    public String getTENTHUCDON() {
        return tENTHUCDON;
    }

    public void setTENTHUCDON(String tENTHUCDON) {
        this.tENTHUCDON = tENTHUCDON;
    }

    public Integer getTHUTU() {
        return tHUTU;
    }

    public void setTHUTU(Integer tHUTU) {
        this.tHUTU = tHUTU;
    }

    public Boolean getCHOPHEP() {
        return cHOPHEP;
    }

    public void setCHOPHEP(Boolean cHOPHEP) {
        this.cHOPHEP = cHOPHEP;
    }

}
