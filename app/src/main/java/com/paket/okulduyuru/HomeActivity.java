package com.paket.okulduyuru;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;


import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import android.widget.RelativeLayout;

import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {
    private TextView txtAdSoyad,txtArelWeb,txtDuyurular;
    private FirebaseUser user;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        txtAdSoyad = findViewById(R.id.adSoyad);
        txtArelWeb = findViewById(R.id.txtArelWeb);
        txtDuyurular = findViewById(R.id.ac_home_tv_duyurular);
        txtArelWeb = findViewById(R.id.txtArelWeb);

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


        //Duyurular sayfasına geçiş..
        txtDuyurular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,OgrenciDuyuruActivity.class);
                startActivity(intent);
            }
        });

        //Arel Web sayfasına geçiş.
        txtArelWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,ArelWebView.class);
                startActivity(intent);
            }
        });


    }
    }

