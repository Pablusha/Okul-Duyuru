package com.paket.okulduyuru;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.paket.okulduyuru.Model.Duyuru;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Duyuru> duyurular;

    public MyAdapter(Context c,ArrayList<Duyuru> d) {
        context = c;
        duyurular = d;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.content_duyuru,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.baslik.setText(duyurular.get(position).getDuyuruBaslik());
        holder.context.setText(duyurular.get(position).getDuyuruContext());
        holder.time.setText(duyurular.get(position).getDuyuruTime());
        holder.yazar.setText(duyurular.get(position).getDuyuruYazar());
    }

    @Override
    public int getItemCount() {
        return duyurular.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView baslik,context,yazar,time;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            baslik = itemView.findViewById(R.id.ac_cv_duyuru_baslik);
            context = itemView.findViewById(R.id.ac_cv_duyuru_context);
            time = itemView.findViewById(R.id.ac_cv_duyuru_time);
            yazar = itemView.findViewById(R.id.ac_cv_duyuru_yazar);
        }
    }

}
