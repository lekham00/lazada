package com.example.lekham.lazada.Presenter;

import android.content.Context;

import com.example.lekham.lazada.Model.ObjectClass.ThucDon;
import com.example.lekham.lazada.Model.TabLayoutMenu.TabLayoutMenuContract;
import com.example.lekham.lazada.Model.TabLayoutMenu.TabLayoutMenuInterator;

import java.util.List;

/**
 * Created by Le Kham on 6/17/2017.
 */

public class TabLayoutPresenter implements TabLayoutMenuContract.Presenter, TabLayoutMenuContract.OnListenerTabLayoutMenu {

    private TabLayoutMenuContract.View mViewTabLayout;
    private TabLayoutMenuInterator mVabLayoutMenuInterator;

    public TabLayoutPresenter(TabLayoutMenuContract.View viewTabLayout) {
        mViewTabLayout = viewTabLayout;
        mVabLayoutMenuInterator = new TabLayoutMenuInterator(this);
    }

    @Override
    public void perfromGetMenuForTabLayout(Context context) {
        mVabLayoutMenuInterator.getMenuForTabLayout(context);
    }

    @Override
    public void resultMenuTabLayout(List<ThucDon> thucDons) {
        mViewTabLayout.resultMenuTabLayout(thucDons);
    }
}
