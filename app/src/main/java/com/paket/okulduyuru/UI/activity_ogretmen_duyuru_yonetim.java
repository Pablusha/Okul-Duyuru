package com.paket.okulduyuru.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.paket.okulduyuru.R;

public class activity_ogretmen_duyuru_yonetim extends AppCompatActivity {

    private RelativeLayout rl_add_button,rl_delete_button,rl_update_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ogretmen_duyuru_yonetim);

        rl_add_button = findViewById(R.id.ac_ogretmen_duyuru_add);
        rl_delete_button = findViewById(R.id.ac_ogretmen_duyuru_delete);
        rl_update_button = findViewById(R.id.ac_ogretmen_duyuru_update);

        //Duyuru ekleme aktivitesine geçiş.
        rl_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), activity_ogretmen_duyuru_add.class));
            }
        });

        rl_delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), activity_ogretmen_duyuru_delete.class));
            }
        });

        rl_update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),activity_ogretmen_duyuru_update.class));
            }
        });

    }
}
