package com.example.lekham.lazada.Presenter.Main.Menu;

import com.example.lekham.lazada.ConnectInternet.DownloadJson;
import com.example.lekham.lazada.Model.Main.ActionMenu.Menu;
import com.example.lekham.lazada.Model.ObjectClass.ProductType;
import com.example.lekham.lazada.View.Main.ViewMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Le Kham on 5/29/2017.
 */

public class PresenterLogicActionMenu implements IPresenterActionMenu {

    public ViewMenu mViewMenu;

    public PresenterLogicActionMenu(ViewMenu viewMenu) {
        mViewMenu = viewMenu;
    }

    @Override
    public void GetAllListDataMenu(int id) {
        List<ProductType> productTypes = new ArrayList<>();
        String jsonData = "";
        String path = "http://192.168.1.8/lazadaserver/api/loaisanpham/maloaicha/" + id;
        DownloadJson downloadJson = new DownloadJson(path);
        downloadJson.execute();
        try {
            jsonData = downloadJson.get();
            Menu menu = new Menu();
            productTypes = menu.parserJsonData(jsonData);
            mViewMenu.ViewAllListDataMenu(productTypes);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
