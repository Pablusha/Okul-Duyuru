package com.paket.okulduyuru;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class RegisterActivity extends AppCompatActivity {
    private DatabaseHelper db;
    private EditText eAdSoyad,eOgrenciNo,eSifre,eEMail,eSifreOnay;
    private Spinner spnBolumler;
    private Button btnKayit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new DatabaseHelper(this);

        eAdSoyad = findViewById(R.id.edittext_adsoyad);
        eOgrenciNo = findViewById(R.id.edittext_ogrenciNo);
        eEMail = findViewById(R.id.edittext_email);
        eSifre = findViewById(R.id.edittext_password);
        eSifreOnay = findViewById(R.id.edittext_confirmpassword);
        spnBolumler = findViewById(R.id.spinnerBolum);
        btnKayit = findViewById(R.id.btnKayit);

        btnKayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String adSoyad = eAdSoyad.getText().toString();
                String ogrenciNo = eOgrenciNo.getText().toString();
                String email = eEMail.getText().toString();
                String sifre = eSifre.getText().toString();
                String sifreOnay = eSifreOnay.getText().toString();
                String bolumler = spnBolumler.getSelectedItem().toString();
                if (adSoyad.equals("")||ogrenciNo.equals("")||email.equals("")||sifre.equals("")||sifreOnay.equals("")||bolumler.equals("")) {
                    Toast.makeText(RegisterActivity.this,"Bilgiler boş bırakılamaz.",Toast.LENGTH_LONG).show();
                }
                else {
                    if (sifre.equals(sifreOnay)) {
                        Boolean ogrenciKontrol = db.ogrenciKontrol(ogrenciNo);
                        if (ogrenciKontrol == true) {
                            Boolean ekle = db.ogrenciEkle(adSoyad,email,sifre,bolumler,ogrenciNo);
                            if (ekle == true) {
                                Toast.makeText(RegisterActivity.this,"Kayıt olma işlemi başarılı.",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                            }
                        }
                        else {
                            Toast.makeText(RegisterActivity.this,"Öğrenci numarası zaten kayıtlı.",Toast.LENGTH_LONG).show();
                        }
                    }
                }
                if (!sifre.equals(sifreOnay)) {
                    Toast.makeText(RegisterActivity.this,"Şifreleriniz eşleşmiyor.",Toast.LENGTH_LONG).show();
                }
            }
        });

        Spinner bolumler = (Spinner) findViewById(R.id.spinnerBolum); //Spinner'ımı tanımlıyorum.

        //ArrayAdapter şu işe yarıyor; Spinner'ın içindeki dataları ArrayAdapterla tutabiliyoruz.Burada Adapter ile Spinner'ı entegre ediyorum.
        ArrayAdapter<String> bolumAdapter = new ArrayAdapter<String>(RegisterActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.bolumler));
        //Arg1:Benim Contextim yani uygulamam.Arg2:Datalarımı tuttuğum resource dosyasıdır.Arg4:Data seti için oluşturduğum diziyi get ediyorum.
        bolumAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //Burada ise dropdown menü kullanacağımı belirtiyorum.
        bolumler.setAdapter(bolumAdapter); //Ben bu satırı yazmasaydım uygulama crash vermeyecekti fakat spinner'ın içine gönderdiğimiz data gözükmeyecekti.

    }

    public void girisYap(View view) { //Login sayfasına geçiş.
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }
}