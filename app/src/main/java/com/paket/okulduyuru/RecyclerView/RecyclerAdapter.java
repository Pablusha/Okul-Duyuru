package com.paket.okulduyuru.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.paket.okulduyuru.Model.Duyuru;
import com.paket.okulduyuru.R;
import com.paket.okulduyuru.UI.activity_duyuru_update_screen;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private static final String Tag = "RecyclerView";
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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txtBaslik.setText(duyuruArrayList.get(position).getDuyuruBaslik());
        holder.txtContext.setText(duyuruArrayList.get(position).getDuyuruContext());
        holder.txtYazar.setText(duyuruArrayList.get(position).getDuyuruYazar());
        holder.txtTime.setText(duyuruArrayList.get(position).getDuyuruTime());
        holder.txtDate.setText(duyuruArrayList.get(position).getDuyuruDate());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, activity_duyuru_update_screen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("baslik",duyuruArrayList.get(position).getDuyuruBaslik());
                intent.putExtra("context",duyuruArrayList.get(position).getDuyuruContext());
                intent.putExtra("yazar",duyuruArrayList.get(position).getDuyuruYazar());
                intent.putExtra("time",duyuruArrayList.get(position).getDuyuruTime());
                intent.putExtra("date",duyuruArrayList.get(position).getDuyuruDate());
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return duyuruArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtBaslik,txtContext,txtYazar,txtTime,txtDate;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtBaslik = itemView.findViewById(R.id.ac_cv_duyuru_baslik);
            txtContext = itemView.findViewById(R.id.ac_cv_duyuru_context);
            txtTime = itemView.findViewById(R.id.ac_cv_duyuru_time);
            txtYazar = itemView.findViewById(R.id.ac_cv_duyuru_yazar);
            txtDate = itemView.findViewById(R.id.ac_cv_duyuru_date);
            parentLayout = itemView.findViewById(R.id.ac_items_layout);
        }
    }

    public void removeAt(int position) {
        duyuruArrayList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,duyuruArrayList.size());
    }

}
