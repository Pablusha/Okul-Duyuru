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
>>>>>>> 37306d8928ea673591e0c7788e6701cb0a8158a1
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    DatabaseHelper db;
<<<<<<< HEAD
    TextView txtAdSoyad,txtArelWeb;


=======
    TextView txtAdSoyad,txtDuyurular,txtArelWeb;
>>>>>>> 37306d8928ea673591e0c7788e6701cb0a8158a1
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        txtAdSoyad = findViewById(R.id.adSoyad);
<<<<<<< HEAD
        txtArelWeb = findViewById(R.id.txtArelWeb);
=======
        txtDuyurular = findViewById(R.id.txtDuyurular);
        txtArelWeb = findViewById(R.id.txtArelWeb);

>>>>>>> 37306d8928ea673591e0c7788e6701cb0a8158a1
        db = new DatabaseHelper(this);
        //Ad Ve Soyadı veritabanından çekiyoruz.
        Cursor cursor = db.verilereUlas();
        while (cursor.moveToNext()) {
            txtAdSoyad.setText(cursor.getString(1));
        }

<<<<<<< HEAD
=======
        //Duyurular sayfasına geçiş..
        txtDuyurular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,OgrenciDuyuruActivity.class);
                startActivity(intent);
            }
        });

        //Arel Web sayfasına geçiş.
>>>>>>> 37306d8928ea673591e0c7788e6701cb0a8158a1
        txtArelWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,ArelWebView.class);
                startActivity(intent);
            }
        });

<<<<<<< HEAD
    }
    }
=======


            }


>>>>>>> 37306d8928ea673591e0c7788e6701cb0a8158a1




