package com.example.lekham.lazada.Until;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Le Kham on 6/3/2017.
 */

public class SharePre {

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private static final String NAME = "SharePreLazada";
    public static String KEY_ACCESS_TOKEN = "KEY_ACCESS_TOKEN";

    public static SharePre instantSharePre = null;

    public static SharePre instantSharePre(Context context) {
        if (instantSharePre == null) {
            instantSharePre = new SharePre(context);
        }
        return instantSharePre;
    }


    public SharePre(Context context) {
        mSharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }

    public void setValueString(String key, String value) {
        mEditor.putString(key, value);
        mEditor.commit();
    }

    public String getValueString(String key) {
        return mSharedPreferences.getString(key, null);
    }

}
