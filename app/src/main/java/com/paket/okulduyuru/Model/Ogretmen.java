package com.paket.okulduyuru.Model;

public class Ogretmen {

    private String ad_soyad,email,sifre,bolum;

    //Constructor
    public Ogretmen(String ad_soyad,String email,String sifre,String bolum) {
        this.ad_soyad = ad_soyad;
        this.email = email;
        this.sifre = sifre;
        this.bolum = bolum;
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

    public String getBolum() {
        return bolum;
    }

    public void setBolum(String bolum) {
        this.bolum = bolum;
    }
}