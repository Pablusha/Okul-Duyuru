package com.paket.okulduyuru.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.paket.okulduyuru.Model.Ogrenci;
import com.paket.okulduyuru.R;

public class RegisterActivity extends AppCompatActivity {
    private EditText edAdSoyad,edOgrenciNo,edSifre,edEMail,edSifreOnay;
    private Spinner spnBolumler;
    private Button btnKayit;
    DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Spinner bolumler = (Spinner) findViewById(R.id.spinnerBolum); //Spinner'ımı tanımlıyorum.

        //ArrayAdapter şu işe yarıyor;
        //Spinner'ın içindeki dataları ArrayAdapterla tutabiliyoruz.Burada Adapter ile Spinner'ı entegre ediyorum.
        ArrayAdapter<String> bolumAdapter = new ArrayAdapter<String>(RegisterActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.bolumler));
        //Arg1:Benim Contextim yani uygulamam.
        //Arg2:Datalarımı tuttuğum resource dosyasıdır.
        //Arg4:Data seti için oluşturduğum diziyi get ediyorum.

        bolumAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Burada ise dropdown menü kullanacağımı belirtiyorum.
        bolumler.setAdapter(bolumAdapter);
        //Ben bu satırı yazmasaydım uygulama crash vermeyecekti fakat spinner'ın içine gönderdiğimiz data gözükmeyecekti.

        //Casting Views
        //EditText
        edAdSoyad = findViewById(R.id.edittext_adsoyad);
        edOgrenciNo = findViewById(R.id.edittext_ogrenciNo);
        edEMail = findViewById(R.id.edittext_email);
        edSifre = findViewById(R.id.edittext_password);
        edSifreOnay = findViewById(R.id.edittext_confirmpassword);
        //Spinner
        spnBolumler = findViewById(R.id.spinnerBolum);
        //Button
        btnKayit = findViewById(R.id.btnKayit);

        databaseReference = FirebaseDatabase.getInstance().getReference("Ogrenci");
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        btnKayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ogrenci_no = edOgrenciNo.getText().toString();
                final String ad_soyad = edAdSoyad.getText().toString();
                final String email = edEMail.getText().toString();
                final String sifre = edSifre.getText().toString();
                String sifre_onay = edSifreOnay.getText().toString();
                final String bolum = spnBolumler.getSelectedItem().toString();

                if (TextUtils.isEmpty(ogrenci_no)) {
                    Toast.makeText(RegisterActivity.this,"Öğrenci numarası boş bırakılamaz.",Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(ad_soyad)) {
                    Toast.makeText(RegisterActivity.this,"Ad ve soyad kısmı boş bırakılamaz.",Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(RegisterActivity.this,"E-Posta adresi girmelisiniz.",Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(sifre)) {
                    Toast.makeText(RegisterActivity.this,"Şifre kısmı boş bırakılamaz.",Toast.LENGTH_LONG).show();
                    return;
                }

                if (sifre.length() < 6) {
                    Toast.makeText(RegisterActivity.this,"Şifreniz en az 6 karakter olmalıdır.",Toast.LENGTH_LONG).show();
                    return;
                }

                if (sifre_onay.length() < 6) {
                    Toast.makeText(RegisterActivity.this,"Şifreniz en az 6 karakter olmalıdır.",Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(sifre_onay)) {
                    Toast.makeText(RegisterActivity.this,"Şifrenizi onaylamanız gerekiyor.",Toast.LENGTH_LONG).show();
                    return;
                }

                if (bolum == null) {
                    Toast.makeText(RegisterActivity.this,"Lütfen bir bölüm seçiniz.",Toast.LENGTH_LONG).show();
                    return;
                }

                if (!sifre.equals(sifre_onay)) {
                    Toast.makeText(RegisterActivity.this,"Şifreleriniz eşleşmiyor.",Toast.LENGTH_LONG).show();
                }

                ogrenciNoCheck();
                firebaseAuth.createUserWithEmailAndPassword(email, sifre)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Ogrenci ogrenci = new Ogrenci(ogrenci_no,ad_soyad,email,sifre,bolum);
                                    FirebaseDatabase.getInstance().getReference("Ogrenci")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(ogrenci).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(RegisterActivity.this,
                                                    "Kayıt olma işlemi başarılı.",Toast.LENGTH_LONG).show();
                                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                        }
                                    });
                                } else {

                                }

                            }
                        });
            }
        });

    }

    private void ogrenciNoCheck() {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ogrenciRef = rootRef.child("Ogrenci");

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    if (dataSnapshot.child("ogrenci_no").exists()) {
                        Toast.makeText(RegisterActivity.this,"Öğrenci numarası zaten kayıtlı.",Toast.LENGTH_LONG).show();
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        ogrenciRef.addListenerForSingleValueEvent(eventListener);
    }

    private boolean emailCheck() {
        firebaseAuth.fetchSignInMethodsForEmail(edEMail.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                        boolean check = !task.getResult().getSignInMethods().isEmpty();

                        if (check == true) {
                            Toast.makeText(RegisterActivity.this,"E-postaya ait bir hesap zaten kayıtlı.",Toast.LENGTH_LONG).show();
                        }

                    }
                });
        return true;
    }

    public void girisYap(View view) { //Login sayfasına geçiş.
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }
}