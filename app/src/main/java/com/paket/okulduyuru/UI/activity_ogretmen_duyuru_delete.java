package com.paket.okulduyuru.UI;

import androidx.annotation.FontRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.api.Quota;
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
import com.paket.okulduyuru.RecyclerView.ViewHolder;

import java.util.ArrayList;

public class activity_ogretmen_duyuru_delete extends AppCompatActivity {

    private Toolbar toolbar;
    DatabaseReference duyuruRef;
    RecyclerView recyclerView;
    ArrayList<Duyuru> duyurular;
    RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ogretmen_duyuru_delete);

        recyclerView = findViewById(R.id.recyclerDelete);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        duyuruRef = FirebaseDatabase.getInstance().getReference();
        duyuruRef.keepSynced(true);
        duyurular = new ArrayList<>();

        getDataFromFirebase();
        setUpToolbar();

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

    private void setUpToolbar() {
        toolbar = findViewById(R.id.ac_duyuru_toolbar);
        toolbar.setSubtitle("Yayınlanan Duyurular");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),activity_ogretmen_duyuru_yonetim.class));
    }
}

