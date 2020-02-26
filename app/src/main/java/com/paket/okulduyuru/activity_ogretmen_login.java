package com.paket.okulduyuru;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

public class activity_ogretmen_login extends AppCompatActivity {

    private EditText edEmail,edSifre;
    private Button btnGiris;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ogretmen_login);

        edEmail = findViewById(R.id.edittext_ogretmenEmail);
        edSifre = findViewById(R.id.edittext_ogretmenPassword);
        btnGiris = findViewById(R.id.btnGiris);

        firebaseAuth = FirebaseAuth.getInstance();

        btnGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edEmail.getText().toString().trim();
                String sifre = edSifre.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(activity_ogretmen_login.this,"Lütfen e-posta adresinizi giriniz.",Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(sifre)) {
                    Toast.makeText(activity_ogretmen_login.this,"Lütfen şifrenizi giriniz.",Toast.LENGTH_LONG).show();
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(email, sifre)
                        .addOnCompleteListener(activity_ogretmen_login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    startActivity(new Intent(getApplicationContext(),activity_ogretmen_home.class));
                                } else {
                                    emailCheck();
                                }

                            }
                        });

            }
        });

    }

    public void emailCheck() {
        firebaseAuth.fetchSignInMethodsForEmail(edEmail.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                        boolean check = !task.getResult().getSignInMethods().isEmpty();

                        if (check == true) {
                            Toast.makeText(activity_ogretmen_login.this,
                                    "E-Posta adresi veya şifre yanlış.",Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(activity_ogretmen_login.this,"E-Posta adresi kayıtlı değil.",Toast.LENGTH_LONG).show();
                        }

                    }
                });

    }

    public void kayitOl(View view) {
        Intent intent = new Intent(getApplicationContext(), activity_ogretmen_register.class);
        startActivity(intent);
    }
}