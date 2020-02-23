package com.paket.okulduyuru;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.paket.okulduyuru.utils.PreferenceUtils;


public class LoginActivity extends AppCompatActivity {
    private EditText eOgrenciNo;
    private EditText eSifre;
    private Button btnGiris,btnOgretmenGiris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        eOgrenciNo = findViewById(R.id.edittext_ogrenciNo);
        eSifre = findViewById(R.id.edittext_password);
        btnGiris = findViewById(R.id.btnGiris);
        btnOgretmenGiris = findViewById(R.id.btnOgretmenGiris);

        btnOgretmenGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,activity_ogretmen_login.class);
                startActivity(intent);
            }
        });


        try {
            if (PreferenceUtils.getOgrenciNo(LoginActivity.this) != null || !PreferenceUtils.getOgrenciNo(LoginActivity.this).equals("")) {
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), PreferenceUtils.getOgrenciNo(LoginActivity.this), Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
        }



    }

    public void kayitOl(View view) { //Kayıt ol sayfasına geçiş.
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);

    }
}
