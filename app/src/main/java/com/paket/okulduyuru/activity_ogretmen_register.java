package com.paket.okulduyuru;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity_ogretmen_register extends AppCompatActivity {

    private Dialog popUp;
    private EditText edOgretmenAdSoyad,edOgretmenEmail,edOgretmenSifre,edOgretmenSifreOnay;
    private Button btnOgretmenKayit;
    DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ogretmen_register);
        popUp = new Dialog(this);
        popUpGoster();

        //Spinner
        final Spinner bolumler = (Spinner) findViewById(R.id.spinnerBolum);

        ArrayAdapter<String> bolumAdapter = new ArrayAdapter<String>(activity_ogretmen_register.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.bolumler));
        bolumAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bolumler.setAdapter(bolumAdapter);

        //Casting Views
        //EditText
        edOgretmenAdSoyad = findViewById(R.id.edittext_adsoyad);
        edOgretmenEmail = findViewById(R.id.edittext_email);
        edOgretmenSifre = findViewById(R.id.edittext_password);
        edOgretmenSifreOnay = findViewById(R.id.edittext_confirmpassword);
        //Button
        btnOgretmenKayit = findViewById(R.id.btnKayit);

        databaseReference = FirebaseDatabase.getInstance().getReference("Ogretmen");
        firebaseAuth = FirebaseAuth.getInstance();

        btnOgretmenKayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ad_soyad = edOgretmenAdSoyad.getText().toString();
                final String email = edOgretmenEmail.getText().toString();
                final String sifre = edOgretmenSifre.getText().toString();
                String sifre_onay = edOgretmenSifreOnay.getText().toString();
                final String bolum = bolumler.getSelectedItem().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(activity_ogretmen_register.this,"Lütfen bir e-posta giriniz.",Toast.LENGTH_LONG).show();
                }

                if (TextUtils.isEmpty(sifre)) {
                    Toast.makeText(activity_ogretmen_register.this,"Lütfen bir şifre giriniz.",Toast.LENGTH_LONG).show();
                }

                if (TextUtils.isEmpty(ad_soyad)) {
                    Toast.makeText(activity_ogretmen_register.this,"Lütfen adınızı ve soyadınızı giriniz.",Toast.LENGTH_LONG).show();
                }

                if (TextUtils.isEmpty(sifre_onay)) {
                    Toast.makeText(activity_ogretmen_register.this,"Lütfen şifrenizi onaylayınız.",Toast.LENGTH_LONG).show();
                }

                if (TextUtils.isEmpty(bolum)) {
                    Toast.makeText(activity_ogretmen_register.this,"Lütfen bölümünüzü seçiniz.",Toast.LENGTH_LONG).show();
                }

                if (!sifre.equals(sifre_onay)) {
                    Toast.makeText(activity_ogretmen_register.this,"Şifreleriniz eşleşmiyor.",Toast.LENGTH_LONG).show();
                }

                emailCheck();
                firebaseAuth.createUserWithEmailAndPassword(email, sifre)
                        .addOnCompleteListener(activity_ogretmen_register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Ogretmen ogretmen = new Ogretmen(ad_soyad,email,sifre,bolum);
                                    FirebaseDatabase.getInstance().getReference("Ogretmen")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(ogretmen).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(activity_ogretmen_register.this,"Kayıt olma işlemi başarılı.",Toast.LENGTH_LONG).show();
                                            startActivity(new Intent(getApplicationContext(),activity_ogretmen_login.class));
                                        }
                                    });

                                } else {

                                }

                            }
                        });

            }
        });

    }

    public void emailCheck() {
        firebaseAuth.fetchSignInMethodsForEmail(edOgretmenEmail.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                        boolean check = !task.getResult().getSignInMethods().isEmpty();

                        if (check = true) {
                            Toast.makeText(activity_ogretmen_register.this,"E-postaya ait bir hesap zaten kayıtlı.",Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    public void girisYap(View view) {
        Intent intent = new Intent(getApplicationContext(),activity_ogretmen_login.class);
        startActivity(intent);
    }

    //Bilgilendirme Mesajı
    public void popUpGoster() {
        TextView txtClose;
        Button btnClose;
        popUp.setContentView(R.layout.popup);
        txtClose = popUp.findViewById(R.id.txtClose);
        btnClose = popUp.findViewById(R.id.btnClose);
        txtClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUp.dismiss();
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUp.dismiss();
            }
        });
        popUp.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popUp.show();
    }
}
