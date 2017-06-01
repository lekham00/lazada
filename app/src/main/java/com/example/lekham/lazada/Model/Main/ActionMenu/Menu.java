package com.example.lekham.lazada.Model.Main.ActionMenu;

import com.example.lekham.lazada.ConnectInternet.DownloadJson;
import com.example.lekham.lazada.Model.ObjectClass.ProductType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Le Kham on 5/29/2017.
 */

public class Menu {
    public List<ProductType> parserJsonData(String data) {
        List<ProductType> productTypes = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(data);
            int count = jsonArray.length();
            for (int i = 0; i < count; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                ProductType productType = new ProductType();
                productType.setIDPRODUCT_TYPE(jsonObject.getInt("MALSP"));
                productType.setNAME_PRODUCT_TYPE(jsonObject.getString("TENLOAISP"));
                productType.setIDPRODUCT_TYPE_ROOT(jsonObject.getInt("MALOAI_CHA"));
                productTypes.add(productType);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return productTypes;
    }

    public List<ProductType> getDataChildForMenu(int id) {
        List<ProductType> productTypes = new ArrayList<>();
        String jsonData = "";
        String path = "http://192.168.1.8/lazadaserver/api/loaisanpham/maloaicha/" + id;
        DownloadJson downloadJson = new DownloadJson(path);
        downloadJson.execute();
        try {
            jsonData = downloadJson.get();
            Menu menu = new Menu();
            productTypes = menu.parserJsonData(jsonData);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return productTypes;
    }
}
