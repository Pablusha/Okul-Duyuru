package com.paket.okulduyuru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    DatabaseHelper db;
    TextView txtAdSoyad,txtArelWeb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        txtAdSoyad = findViewById(R.id.adSoyad);
        txtArelWeb = findViewById(R.id.txtArelWeb);
        db = new DatabaseHelper(this);
        //Ad Ve Soyadı veritabanından çekiyoruz.
        Cursor cursor = db.verilereUlas();
        while (cursor.moveToNext()) {
            txtAdSoyad.setText(cursor.getString(1));
        }

        txtArelWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,ArelWebView.class);
                startActivity(intent);
            }
        });

    }
    }




