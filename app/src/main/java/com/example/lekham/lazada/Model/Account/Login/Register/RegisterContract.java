package com.example.lekham.lazada.Model.Account.Login.Register;

import android.content.Context;

import com.example.lekham.lazada.Model.ObjectClass.NhanVien;

/**
 * Created by Le Kham on 6/7/2017.
 */

public interface RegisterContract {
    interface View {
        void onRegisterSuccess();

        void onRegisterFailure();
    }

    interface Presenter {
        void registerAccount(Context context, NhanVien nhanVien);
    }

    interface Interator {
        void performRegisterAccount(Context context, NhanVien nhanVien);
    }

    interface OnRegisterListener {
        void onSuccess();

        void onFailuer();
    }

}
