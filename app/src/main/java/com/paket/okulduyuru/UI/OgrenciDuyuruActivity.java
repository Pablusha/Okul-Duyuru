package com.paket.okulduyuru.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.paket.okulduyuru.Model.Duyuru;
import com.paket.okulduyuru.MyAdapter;
import com.paket.okulduyuru.R;

import java.util.ArrayList;

public class OgrenciDuyuruActivity extends AppCompatActivity {

  private Toolbar toolbar;
  DatabaseReference databaseReference;
  RecyclerView recyclerView;
  ArrayList<Duyuru> list;
  MyAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_ogrenci_duyuru);

    try {
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    } catch (Exception e) {

    }

    list = new ArrayList<Duyuru>();


    databaseReference = FirebaseDatabase.getInstance().getReference().child("Profiles");
    databaseReference.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {
                Duyuru d = dataSnapshot1.getValue(Duyuru.class);
                list.add(d);
            }
            try {
                adapter = new MyAdapter(OgrenciDuyuruActivity.this,list);
                recyclerView.setAdapter(adapter);
            } catch (Exception e) {

            }


        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Toast.makeText(OgrenciDuyuruActivity.this,"Duyurular yüklenemedi..",Toast.LENGTH_LONG).show();
        }
    });


    setUpToolbar();
  }

  private void setUpToolbar() {
    toolbar = findViewById(R.id.toolbarXML);
    toolbar.setSubtitle("Bölüm Duyuruları");
  }

}