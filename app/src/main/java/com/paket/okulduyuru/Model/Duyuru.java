package com.paket.okulduyuru.Model;

public class Duyuru {

    public String duyuruBaslik,duyuruContext,duyuruYazar,duyuruTime;

    public Duyuru() {

    }

    public Duyuru(String duyuruBaslik, String duyuruContext, String duyuruYazar, String duyuruTime) {
        this.duyuruBaslik = duyuruBaslik;
        this.duyuruContext = duyuruContext;
        this.duyuruYazar = duyuruYazar;
        this.duyuruTime = duyuruTime;
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
