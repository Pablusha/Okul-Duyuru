package com.paket.okulduyuru;

public class Ogrenci {

    public Ogrenci(int ogrenci_no,String sifre) {
        this.ogrenci_no = ogrenci_no;
        this.sifre = sifre;
    }

    public int getOgrenci_no() {
        return ogrenci_no;
    }

    public void setOgrenci_no(int ogrenci_no) {
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
        return bolumler;
    }

    public void setBolumler(String bolumler) {
        this.bolumler = bolumler;
    }

    private int ogrenci_no;
    private String ad_soyad;
    private String email;
    private String sifre;
    private String bolumler;




}
