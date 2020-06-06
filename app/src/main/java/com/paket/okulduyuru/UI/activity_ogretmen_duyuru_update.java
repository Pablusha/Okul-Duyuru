package com.paket.okulduyuru.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.paket.okulduyuru.Model.Duyuru;
import com.paket.okulduyuru.R;
import com.paket.okulduyuru.RecyclerView.RecyclerAdapter;

import java.util.ArrayList;

public class activity_ogretmen_duyuru_update extends AppCompatActivity {

    private Toolbar toolbar;
    public DatabaseReference duyuruRef;
    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;
    ArrayList<Duyuru> duyurular;
    RecyclerAdapter recyclerAdapter;
    Context context;
    FirebaseUser user;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ogretmen_duyuru_update);

        recyclerView = findViewById(R.id.recyclerUpdate);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        duyuruRef = FirebaseDatabase.getInstance().getReference();
        duyuruRef.keepSynced(true);
        duyurular = new ArrayList<>();
        final Query query = duyuruRef.child("Duyuru");

        getDataFromFirebase();
        setUpToolbar();
    }



    private void setUpToolbar() {
        toolbar = findViewById(R.id.ac_duyuru_toolbar);
        toolbar.setSubtitle("Yayınlanan Duyurular");
    }

    private void getDataFromFirebase() {
        Query query = duyuruRef.child("Duyuru");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    Duyuru duyuru = new Duyuru();
                    duyuru.setDuyuruBaslik(snapshot.child("title").getValue().toString());
                    duyuru.setDuyuruContext(snapshot.child("context").getValue().toString());
                    duyuru.setDuyuruTime(snapshot.child("time").getValue().toString());
                    duyuru.setDuyuruDate(snapshot.child("date").getValue().toString());
                    duyuru.setDuyuruYazar(snapshot.child("yazar").getValue().toString());

                    duyurular.add(duyuru);
                }
                recyclerAdapter = new RecyclerAdapter(getApplicationContext(),duyurular);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
