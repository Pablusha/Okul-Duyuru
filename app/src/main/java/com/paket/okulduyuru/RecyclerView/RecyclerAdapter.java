package com.paket.okulduyuru.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.paket.okulduyuru.Model.Duyuru;
import com.paket.okulduyuru.R;
import com.paket.okulduyuru.UI.activity_duyuru_update_screen;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Duyuru> duyuruArrayList;

    public RecyclerAdapter(Context mContext, ArrayList<Duyuru> duyuruArrayList) {
        this.mContext = mContext;
        this.duyuruArrayList = duyuruArrayList;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.duyuru_items_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.txtBaslik.setText(duyuruArrayList.get(position).getDuyuruBaslik());
        holder.txtContext.setText(duyuruArrayList.get(position).getDuyuruContext());
        holder.txtYazar.setText(duyuruArrayList.get(position).getDuyuruYazar());
        holder.txtTime.setText(duyuruArrayList.get(position).getDuyuruTime());
        holder.txtDate.setText(duyuruArrayList.get(position).getDuyuruDate());

    }



    @Override
    public int getItemCount() {
        return duyuruArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtBaslik,txtContext,txtYazar,txtTime,txtDate;
        RelativeLayout parentLayout;
        View mView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mView = itemView;
            txtBaslik = itemView.findViewById(R.id.ac_cv_duyuru_baslik);
            txtContext = itemView.findViewById(R.id.ac_cv_duyuru_context);
            txtTime = itemView.findViewById(R.id.ac_cv_duyuru_time);
            txtYazar = itemView.findViewById(R.id.ac_cv_duyuru_yazar);
            txtDate = itemView.findViewById(R.id.ac_cv_duyuru_date);
            parentLayout = itemView.findViewById(R.id.ac_items_layout);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mClickListener.onItemClick(view,getAdapterPosition());
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mClickListener.onItemLongClick(view,getAdapterPosition());
                    return true;
                }
            });
        }

    }

    private ClickListener mClickListener;

    public interface ClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view,int position);
    }

    public void setOnClickListener(ClickListener clickListener) {
        mClickListener = clickListener;
    }

}
