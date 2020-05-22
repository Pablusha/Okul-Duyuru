package com.paket.okulduyuru.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import android.widget.RelativeLayout;

import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.paket.okulduyuru.R;
import com.paket.okulduyuru.utils.Sabit;

import io.paperdb.Paper;

public class HomeActivity extends AppCompatActivity {
    private TextView txtAdSoyad;
    private RelativeLayout rlDuyurular,rlArelWeb,rlChat,rlEtkinlikler;
    private FirebaseUser user;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        txtAdSoyad = findViewById(R.id.adSoyad);
        rlEtkinlikler = findViewById(R.id.ac_home_cv_etkinlikler);
        rlArelWeb = findViewById(R.id.ac_home_cv_arelweb);
        rlDuyurular = findViewById(R.id.ac_home_cv_duyurular);
        rlChat = findViewById(R.id.ac_home_cv_message);
        getAdSoyad();
        Paper.init(this);

        //Duyurular sayfasına geçiş..
        rlDuyurular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, OgrenciDuyuruActivity.class);
                startActivity(intent);
            }
        });

        //Arel Web sayfasına geçiş.
        rlArelWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ArelWebView.class);
                startActivity(intent);
            }
        });

        //Mesajlaşma sayfasına geçiş.
        rlChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ChatMainActivity.class);
                startActivity(intent);
            }
        });

        rlEtkinlikler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, EtkinlikActivity.class);
                startActivity(intent);
            }
        });



        String KEY_EMAIL = Paper.book().read(Sabit.KEY_EMAIL);
        String KEY_SIFRE = Paper.book().read(Sabit.KEY_SIFRE);





    }

    private void getAdSoyad() {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ogrenciRef = rootRef.child("Ogrenci");
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    String ad_soyad = dataSnapshot.child(uid).child("ad_soyad").getValue(String.class);
                    txtAdSoyad.setText(ad_soyad);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        ogrenciRef.addListenerForSingleValueEvent(eventListener);
    }

}

