package com.paket.okulduyuru;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context,"Ogrenci.db",null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table ogrenci(ogrenci_no text primary key,ad_soyad text,email text,sifre text,bolumler text)";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int eski,int yeni) {
        String sql = "drop table if exists ogrenci";
        db.execSQL(sql);
        onCreate(db);
    }
    //Tabloya öğrenci ekleme
    public boolean ogrenciEkle(String s1,String s2,String s3,String s4,String i1) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ad_soyad",s1);
        contentValues.put("email",s2);
        contentValues.put("sifre",s3);
        contentValues.put("bolumler",s4);
        contentValues.put("ogrenci_no",i1);
        long result = db.insert("ogrenci",null,contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public String loginKontrol(String ogrenci_no,String sifre) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select ogrenci_no,sifre from ogrenci where ogrenci_no=? and sifre=?",new String[]{ogrenci_no,sifre});
        if (cursor.getCount()>0) {
            cursor.moveToFirst();
            String ogrenciNo = cursor.getString(0);
            String password = cursor.getString(1);
            cursor.close();
            return ogrenciNo;
        }return null;
    }

    //Öğrenci kayıtlı mı değil mi?
    public boolean ogrenciKontrol(String ogrenci_no) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from ogrenci where ogrenci_no=?",new String[]{ogrenci_no});
        if (cursor.getCount()> 0) return false;
        else return true;

    }
    //Email Kontrolü
    public boolean emailKontrol(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from ogrenci where email=?",new String[]{email});
        if (cursor.getCount() > 0) return false;
        else return true;
    }

    //Verilere ulaşma
    public Cursor verilereUlas() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from ogrenci",null);
        return cursor;
    }



}
