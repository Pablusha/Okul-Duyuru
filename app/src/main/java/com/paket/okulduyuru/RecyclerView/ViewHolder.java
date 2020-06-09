package com.paket.okulduyuru.RecyclerView;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.paket.okulduyuru.R;

public class ViewHolder extends RecyclerView.ViewHolder {

    View mView;

    public ViewHolder(@NonNull final View itemView) {
        super(itemView);

        mView = itemView;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(v,getAdapterPosition());

            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mClickListener.onItemLongClick(v,getAdapterPosition());
                return true;
            }
        });
    }

    public void setDetails(Context cxt,String title,String context,String date,String time,String yazar) {
        TextView mBaslikTv = itemView.findViewById(R.id.ac_cv_duyuru_baslik);
        TextView mContextTv = mView.findViewById(R.id.ac_cv_duyuru_context);
        TextView mTimeTv = mView.findViewById(R.id.ac_cv_duyuru_time);
        TextView mYazarTv = mView.findViewById(R.id.ac_cv_duyuru_yazar);
        TextView mDateTv = mView.findViewById(R.id.ac_cv_duyuru_date);
        mBaslikTv.setText(title);
        mContextTv.setText(context);
        mTimeTv.setText(time);
        mDateTv.setText(date);
        mYazarTv.setText(yazar);
    }

    private ViewHolder.ClickListener mClickListener;

    public interface ClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public void setOnClickListener(ViewHolder.ClickListener clickListener) {
        mClickListener = clickListener;
    }

    public int getItem(int position) {
        return position;
    }



}
