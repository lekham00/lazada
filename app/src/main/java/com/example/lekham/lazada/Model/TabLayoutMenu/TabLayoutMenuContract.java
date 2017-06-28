package com.example.lekham.lazada.Model.TabLayoutMenu;

import android.content.Context;

import com.example.lekham.lazada.Model.ObjectClass.ThucDon;

import java.util.List;

/**
 * Created by Le Kham on 6/17/2017.
 */

public interface TabLayoutMenuContract {

    interface View {
        void resultMenuTabLayout(List<ThucDon> thucDons);
    }

    interface Interator {
        void getMenuForTabLayout(Context context);
    }

    interface Presenter {
        void perfromGetMenuForTabLayout(Context context);
    }

    interface OnListenerTabLayoutMenu {
        void resultMenuTabLayout(List<ThucDon> thucDons);
    }
}
