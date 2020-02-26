package com.paket.okulduyuru;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.Toolbar;

public class OgrenciDuyuruActivity extends AppCompatActivity {

  private Toolbar toolbar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_ogrenci_duyuru);

    setUpToolbar();
  }

  private void setUpToolbar() {
    toolbar = findViewById(R.id.toolbarXML);
    toolbar.setSubtitle("Bölüm Duyuruları");
  }

}