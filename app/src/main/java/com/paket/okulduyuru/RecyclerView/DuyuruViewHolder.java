package com.paket.okulduyuru.RecyclerView;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.paket.okulduyuru.Interface.ItemClickListener;
import com.paket.okulduyuru.R;

public class DuyuruViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView title,context,yazar,time;
    public ItemClickListener listener;

    public DuyuruViewHolder(@NonNull View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.ac_cv_duyuru_baslik);
        context = itemView.findViewById(R.id.ac_cv_duyuru_context);
        yazar = itemView.findViewById(R.id.ac_cv_duyuru_yazar);
        time = itemView.findViewById(R.id.ac_cv_duyuru_time);
    }

    public void setItemClickListener(ItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v,getAdapterPosition(),false);
    }
}
