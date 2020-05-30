package com.paket.okulduyuru.Model;

public class Duyuru {

    private String duyuruBaslik,duyuruContext,duyuruYazar,duyuruTime,duyuruDate;
    public static String pid;

    public Duyuru() {

    }

    public String getDuyuruDate() {
        return duyuruDate;
    }



    public void setDuyuruDate(String duyuruDate) {
        this.duyuruDate = duyuruDate;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        Duyuru.pid = pid;
    }

    public Duyuru(String duyuruBaslik, String duyuruContext, String duyuruYazar, String duyuruTime, String duyuruDate, String pid) {
        this.duyuruBaslik = duyuruBaslik;
        this.duyuruContext = duyuruContext;
        this.duyuruYazar = duyuruYazar;
        this.duyuruTime = duyuruTime;
        this.duyuruDate = duyuruDate;
        this.pid = pid;
    }

    public String getDuyuruBaslik() {
        return duyuruBaslik;
    }

    public void setDuyuruBaslik(String duyuruBaslik) {
        this.duyuruBaslik = duyuruBaslik;
    }

    public String getDuyuruContext() {
        return duyuruContext;
    }

    public void setDuyuruContext(String duyuruContext) {
        this.duyuruContext = duyuruContext;
    }

    public String getDuyuruYazar() {
        return duyuruYazar;
    }

    public void setDuyuruYazar(String duyuruYazar) {
        this.duyuruYazar = duyuruYazar;
    }

    public String getDuyuruTime() {
        return duyuruTime;
    }

    public void setDuyuruTime(String duyuruTime) {
        this.duyuruTime = duyuruTime;
    }
}
