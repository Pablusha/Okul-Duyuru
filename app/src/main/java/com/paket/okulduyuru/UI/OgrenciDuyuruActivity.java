package com.paket.okulduyuru.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.paket.okulduyuru.Model.Duyuru;
import com.paket.okulduyuru.R;
import com.paket.okulduyuru.ViewHolder.DuyuruViewHolder;

public class OgrenciDuyuruActivity extends AppCompatActivity {

  private Toolbar toolbar;
  private DatabaseReference duyuruRef;
  private RecyclerView recyclerView;
  RecyclerView.LayoutManager layoutManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_ogrenci_duyuru);

    duyuruRef = FirebaseDatabase.getInstance().getReference().child("Duyuru");

    recyclerView = findViewById(R.id.recycler_menu);
    recyclerView.setHasFixedSize(true);
    layoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(layoutManager);

    setUpToolbar();
  }

  @Override
  protected void onStart() {
    super.onStart();

    FirebaseRecyclerOptions<Duyuru> options =
            new FirebaseRecyclerOptions.Builder<Duyuru>()
            .setQuery(duyuruRef,Duyuru.class)
            .build();

    FirebaseRecyclerAdapter<Duyuru, DuyuruViewHolder> adapter =
            new FirebaseRecyclerAdapter<Duyuru, DuyuruViewHolder>(options) {
              @Override
              protected void onBindViewHolder(@NonNull DuyuruViewHolder duyuruViewHolder, int i, @NonNull Duyuru duyuru)
              {
                  duyuruViewHolder.duyuruBaslik.setText(duyuru.getDuyuruBaslik());
                  duyuruViewHolder.duyuruContext.setText(duyuru.getDuyuruContext());
                  duyuruViewHolder.duyuruYazar.setText(duyuru.getDuyuruYazar());
                  duyuruViewHolder.duyuruTime.setText(duyuru.getDuyuruTime());

              }

              @NonNull
              @Override
              public DuyuruViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
              {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.duyuru_items_layout, parent, false);
                DuyuruViewHolder holder = new DuyuruViewHolder(view);
                return holder;
              }
            };
    recyclerView.setAdapter(adapter);
    adapter.startListening();
  }

  private void setUpToolbar() {
    toolbar = findViewById(R.id.toolbarXML);
    toolbar.setSubtitle("Bölüm Duyuruları");
  }

}