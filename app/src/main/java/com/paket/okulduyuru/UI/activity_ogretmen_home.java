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
import com.paket.okulduyuru.ChatMainActivity;
import com.paket.okulduyuru.R;

public class activity_ogretmen_home extends AppCompatActivity {

    private RelativeLayout rl_duyuru_yonetim,rl_ogretmen_chat;
    private TextView txtAdSoyad;
    private FirebaseUser user;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ogretmen_home);

        txtAdSoyad = findViewById(R.id.adSoyad);
        rl_ogretmen_chat = findViewById(R.id.ac_ogretmen_home_cv_message);
        rl_duyuru_yonetim = findViewById(R.id.ac_ogretmen_home_cv_duyuru_yonetim);
        getAdSoyad();

        //Duyuru yönetim aktivitesine geçiş.
        rl_duyuru_yonetim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), activity_ogretmen_duyuru_yonetim.class));
            }
        });

        //Mesajlaşma sayfasına geçiş.
        rl_ogretmen_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_ogretmen_home.this, ChatMainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void getAdSoyad() {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ogretmenRef = rootRef.child("Ogretmen");
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
        ogretmenRef.addListenerForSingleValueEvent(eventListener);
    }
}