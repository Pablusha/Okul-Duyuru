package com.paket.okulduyuru.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.paket.okulduyuru.Interface.ItemClickListener;
import com.paket.okulduyuru.R;

public class DuyuruViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView duyuruBaslik,duyuruContext,duyuruYazar,duyuruTime;
    public ItemClickListener listener;

    public DuyuruViewHolder(@NonNull View itemView) {
        super(itemView);

        duyuruBaslik = itemView.findViewById(R.id.ac_cv_duyuru_baslik);
        duyuruContext = itemView.findViewById(R.id.ac_cv_duyuru_context);
        duyuruYazar = itemView.findViewById(R.id.ac_cv_duyuru_yazar);
        duyuruTime = itemView.findViewById(R.id.ac_cv_duyuru_time);
    }

    public void setItemClickListener(ItemClickListener listener) {
        this.listener = listener;
    }


    @Override
    public void onClick(View v) {
        listener.onClick(v,getAdapterPosition(),false);
    }
}
