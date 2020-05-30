package com.paket.okulduyuru.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.paket.okulduyuru.R;

public class activity_duyuru_update_screen extends AppCompatActivity {

    private Spinner bolumler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duyuru_update_screen);

        bolumler = findViewById(R.id.ac_ogretmen_duyuru_update_spinner);

        ArrayAdapter<String> bolumAdapter = new ArrayAdapter<String>(activity_duyuru_update_screen.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.bolumler));
        bolumAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bolumler.setAdapter(bolumAdapter);

    }
}
