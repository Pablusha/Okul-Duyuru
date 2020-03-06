package com.paket.okulduyuru.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceUtils {

    public PreferenceUtils () {

    }

    public static String getEmail (Context context) {
        SharedPreferences ozellikler = PreferenceManager.getDefaultSharedPreferences(context);
        //defValue = Eğer preferences'i bulamazsa döndürülecek değer.
        return ozellikler.getString(Sabit.KEY_EMAIL,null);
    }

    public static boolean saveEmail(String email,Context context) {
        SharedPreferences ozellikler = PreferenceManager.getDefaultSharedPreferences(context);
        //SharedPreferences.Editor = SharedPreferences sınıfı içinde sakladığımız değerler üzerinde değişiklik yapmamızı sağlayan bir interface.
        //Yeni bir editör oluşturma.
        SharedPreferences.Editor editor = ozellikler.edit();
        editor.putString(Sabit.KEY_EMAIL, email);
        //apply = commit
        editor.apply();
        return true;
    }

    public static String getSifre (Context context) {
        SharedPreferences ozellikler = PreferenceManager.getDefaultSharedPreferences(context);
        return ozellikler.getString(Sabit.KEY_SIFRE,null);
    }

    public static boolean saveSifre (String sifre, Context context) {
        SharedPreferences ozellikler = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = ozellikler.edit();
        editor.putString(Sabit.KEY_SIFRE, sifre);
        editor.apply();
        return true;
    }

}