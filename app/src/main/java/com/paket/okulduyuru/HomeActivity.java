package com.paket.okulduyuru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
<<<<<<< HEAD

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

=======
import android.widget.RelativeLayout;
>>>>>>> ac3e0705d906ff9a7184646aa93c7ee47dae60bb
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    DatabaseHelper db;
<<<<<<< HEAD

    TextView txtAdSoyad,txtArelWeb,txtDuyurular;





=======
    TextView txtAdSoyad,txtArelWeb;
    RelativeLayout txtDuyurular;
>>>>>>> ac3e0705d906ff9a7184646aa93c7ee47dae60bb
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        txtAdSoyad = findViewById(R.id.adSoyad);
<<<<<<< HEAD

        txtArelWeb = findViewById(R.id.txtArelWeb);

        txtDuyurular = findViewById(R.id.txtDuyurular);
=======
        txtDuyurular = findViewById(R.id.ac_home_cv_duyurular);
>>>>>>> ac3e0705d906ff9a7184646aa93c7ee47dae60bb
        txtArelWeb = findViewById(R.id.txtArelWeb);


        db = new DatabaseHelper(this);
        //Ad Ve Soyadı veritabanından çekiyoruz.
        Cursor cursor = db.verilereUlas();
        while (cursor.moveToNext()) {
            txtAdSoyad.setText(cursor.getString(1));
        }


        //Duyurular sayfasına geçiş..
        txtDuyurular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,OgrenciDuyuruActivity.class);
                startActivity(intent);
            }
        });

        //Arel Web sayfasına geçiş.
        txtArelWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,ArelWebView.class);
                startActivity(intent);
            }
        });


    }
    }











