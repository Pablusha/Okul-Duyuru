package com.paket.okulduyuru.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.paket.okulduyuru.ChatMainActivity;
import com.paket.okulduyuru.R;
import com.paket.okulduyuru.utils.Sabit;

import io.paperdb.Paper;

public class HomeActivity extends AppCompatActivity {
    private TextView txtAdSoyad;
    private RelativeLayout rlDuyurular,rlArelWeb,rlChat,rlEtkinlikler;
    private FirebaseUser user;
    private String uid;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseAuth = FirebaseAuth.getInstance();

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
        //Etkinlik sayfasına geçiş.
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

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Hesabınızdan çıkış yapmak istediğinize emin misiniz?");
        builder.setPositiveButton("EVET", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                firebaseAuth.signOut();
                SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("hatirla","false");
                editor.apply();
                finish();
            }
        });
        builder.setNegativeButton("HAYIR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
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

