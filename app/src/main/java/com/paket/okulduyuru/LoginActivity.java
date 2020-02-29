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
import com.paket.okulduyuru.utils.PreferenceUtils;


public class LoginActivity extends AppCompatActivity {
    private EditText edEmail;
    private EditText edSifre;
    private Button btnGiris,btnOgretmenGiris;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edEmail = findViewById(R.id.edittext_email);
        edSifre = findViewById(R.id.edittext_password);
        btnGiris = findViewById(R.id.btnGiris);
        btnOgretmenGiris = findViewById(R.id.btnOgretmenGiris);

        firebaseAuth = FirebaseAuth.getInstance();

        btnGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edEmail.getText().toString();
                String sifre = edSifre.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(LoginActivity.this,"Lütfen bir E-Posta adresi giriniz.",Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(sifre)) {
                    Toast.makeText(LoginActivity.this,"Lütfen şifrenizi giriniz.",Toast.LENGTH_LONG).show();
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(email,sifre)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                                } else {
                                    emailCheck();
                                }
                            }
                        });
            }
        });

        btnOgretmenGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,activity_ogretmen_login.class);
                startActivity(intent);
            }
        });


        try {
            if (PreferenceUtils.getEmail(LoginActivity.this) != null || !PreferenceUtils.getEmail(LoginActivity.this).equals("")) {
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), PreferenceUtils.getEmail(LoginActivity.this), Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
        }

    }

    private void emailCheck() {
        firebaseAuth.fetchSignInMethodsForEmail(edEmail.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                        boolean check = !task.getResult().getSignInMethods().isEmpty();

                        if (check == true) {
                            Toast.makeText(LoginActivity.this,
                                    "E-Posta adresi veya şifre yanlış.",Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(LoginActivity.this,"E-Posta adresi kayıtlı değil.",Toast.LENGTH_LONG).show();
                        }

                    }
                });

    }

    public void kayitOl(View view) { //Kayıt ol sayfasına geçiş.
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);

    }
}