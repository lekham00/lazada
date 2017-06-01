package com.example.lekham.lazada.Model.ObjectClass;

import java.util.List;

/**
 * Created by Le Kham on 5/29/2017.
 */

public class ProductType {
    private int IDPRODUCT_TYPE;
    private int IDPRODUCT_TYPE_ROOT;
    private String NAME_PRODUCT_TYPE;
    private List<ProductType> productTypeList;

    public int getIDPRODUCT_TYPE() {
        return IDPRODUCT_TYPE;
    }

    public void setIDPRODUCT_TYPE(int IDPRODUCT_TYPE) {
        this.IDPRODUCT_TYPE = IDPRODUCT_TYPE;
    }

    public int getIDPRODUCT_TYPE_ROOT() {
        return IDPRODUCT_TYPE_ROOT;
    }

    public void setIDPRODUCT_TYPE_ROOT(int IDPRODUCT_TYPE_ROOT) {
        this.IDPRODUCT_TYPE_ROOT = IDPRODUCT_TYPE_ROOT;
    }

    public String getNAME_PRODUCT_TYPE() {
        return NAME_PRODUCT_TYPE;
    }

    public void setNAME_PRODUCT_TYPE(String NAME_PRODUCT_TYPE) {
        this.NAME_PRODUCT_TYPE = NAME_PRODUCT_TYPE;
    }

    public List<ProductType> getProductTypeList() {
        return productTypeList;
    }

    public void setProductTypeList(List<ProductType> productTypeList) {
        this.productTypeList = productTypeList;
    }
}
