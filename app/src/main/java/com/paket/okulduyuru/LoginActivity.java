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


public class LoginActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText eOgrenciNo;
    EditText eSifre;
    Button btnGiris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DatabaseHelper(this);
        final Cursor cursor = db.verilereUlas();

        eOgrenciNo = findViewById(R.id.edittext_ogrenciNo);
        eSifre = findViewById(R.id.edittext_password);
        btnGiris = findViewById(R.id.btnGiris);

        btnGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ogrenci_no = eOgrenciNo.getText().toString();
                String sifre = eSifre.getText().toString();
                db.verilereUlas();
                if (ogrenci_no.equals("")||sifre.equals("")) {
                    Toast.makeText(getApplicationContext(),"Bilgiler boş bırakılamaz.",Toast.LENGTH_LONG).show();
                }
                else if (null != db.loginKontrol(ogrenci_no,sifre) ) {
                    String ogrenciDb = db.loginKontrol(ogrenci_no,sifre);
                    Intent i = new Intent(getApplicationContext(),TelefonDogrulama.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Öğrenci numarası veya şifre yanlış.",Toast.LENGTH_LONG).show();
                    eOgrenciNo.setText("");
                    eSifre.setText("");
                    eOgrenciNo.requestFocus();
                }

            }
        });

    }


    public void kayitOl(View view) { //Kayıt ol sayfasına geçiş.
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);

    }
}
