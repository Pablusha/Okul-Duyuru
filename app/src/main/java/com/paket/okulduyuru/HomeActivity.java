package com.paket.okulduyuru;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    DatabaseHelper db;
    TextView txtAdSoyad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        txtAdSoyad = findViewById(R.id.adSoyad);
        db = new DatabaseHelper(this);
        //Ad Ve Soyadı veritabanından çekiyoruz.
        Cursor cursor = db.verilereUlas();
        while (cursor.moveToNext()) {
            txtAdSoyad.setText(cursor.getString(1));
        }
    }


}

