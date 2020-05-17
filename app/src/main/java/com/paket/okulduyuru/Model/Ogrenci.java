package com.paket.okulduyuru.Model;

public class Ogrenci {

    public Ogrenci(String ogrenci_no,String ad_soyad,String email,String sifre,String bolum) {
        this.ogrenci_no = ogrenci_no;
        this.ad_soyad = ad_soyad;
        this.email = email;
        this.sifre = sifre;
        this.bolum = bolum;
    }

    public Ogrenci() {
    }

    public String getOgrenci_no() {
        return ogrenci_no;
    }

    public void setOgrenci_no(String ogrenci_no) {
        this.ogrenci_no = ogrenci_no;
    }

    public String getAd_soyad() {
        return ad_soyad;
    }

    public void setAd_soyad(String ad_soyad) {
        this.ad_soyad = ad_soyad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getBolumler() {
        return bolum;
    }

    public void setBolumler(String bolumler) {
        this.bolum = bolumler;
    }

    private String ogrenci_no,ad_soyad,email,sifre,bolum;




}
