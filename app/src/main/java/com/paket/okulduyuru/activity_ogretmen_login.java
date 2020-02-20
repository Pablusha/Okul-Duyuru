package com.paket.okulduyuru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class activity_ogretmen_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ogretmen_login);
    }

    public void kayitOl(View view) {
        Intent intent = new Intent(getApplicationContext(), activity_ogretmen_register.class);
        startActivity(intent);
    }
}
