package com.paket.okulduyuru;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class activity_ogretmen_register extends AppCompatActivity {

    Dialog popUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ogretmen_register);
        popUp = new Dialog(this);
        popUpGoster();


        Spinner bolumler = (Spinner) findViewById(R.id.spinnerBolum); //Spinner'ımı tanımlıyorum.

        ArrayAdapter<String> bolumAdapter = new ArrayAdapter<String>(activity_ogretmen_register.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.bolumler));
        bolumAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bolumler.setAdapter(bolumAdapter);

    }

    public void girisYap(View view) {
        Intent intent = new Intent(getApplicationContext(),activity_ogretmen_login.class);
        startActivity(intent);
    }

    //Bilgilendirme Mesajı
    public void popUpGoster() {
        TextView txtClose;
        Button btnClose;
        popUp.setContentView(R.layout.popup);
        txtClose = popUp.findViewById(R.id.txtClose);
        btnClose = popUp.findViewById(R.id.btnClose);
        txtClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUp.dismiss();
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUp.dismiss();
            }
        });
        popUp.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popUp.show();
    }
}
