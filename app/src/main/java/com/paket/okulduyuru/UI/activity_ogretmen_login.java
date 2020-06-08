package com.paket.okulduyuru.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.paket.okulduyuru.R;

public class activity_ogretmen_login extends AppCompatActivity {

    private EditText edEmail,edSifre;
    private Button btnGiris;
    private FirebaseAuth firebaseAuth;
    private CheckBox chkBeniHatirla;
    private DatabaseReference UsersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ogretmen_login);

        edEmail = findViewById(R.id.edittext_ogretmenEmail);
        edSifre = findViewById(R.id.edittext_ogretmenPassword);
        btnGiris = findViewById(R.id.btnGiris);
        chkBeniHatirla = findViewById(R.id.ac_ogretmen_login_chk_beni_hatirla);

        firebaseAuth = FirebaseAuth.getInstance();
        UsersRef = FirebaseDatabase.getInstance().getReference().child("Users");

        SharedPreferences preferences = getSharedPreferences("ogretmenCheckbox",MODE_PRIVATE);
        String checkbox = preferences.getString("ogretmenHatirla","");
        if (checkbox.equals("true")) {
            startActivity(new Intent(getApplicationContext(),activity_ogretmen_home.class));
        } else if (checkbox.equals("false")) {

        }

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
                                    setToken();
                                } else {
                                    emailCheck();
                                }

                            }
                        });
            }
        });

        chkBeniHatirla.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    SharedPreferences preferences = getSharedPreferences("ogretmenCheckbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("ogretmenHatirla","true");
                    editor.apply();
                } else if (!buttonView.isChecked()) {
                    SharedPreferences preferences = getSharedPreferences("ogretmenCheckbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("ogretmenHatirla","false");
                    editor.apply();
                }
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


    private void setToken() {

        String currentUserID = firebaseAuth.getCurrentUser().getUid();
        String deviceToken = FirebaseInstanceId.getInstance().getToken();

        UsersRef.child(currentUserID).child("device_token")
                .setValue(deviceToken)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            startActivity(new Intent(getApplicationContext(), activity_ogretmen_home.class));
                        }
                    }

                });
    }
}