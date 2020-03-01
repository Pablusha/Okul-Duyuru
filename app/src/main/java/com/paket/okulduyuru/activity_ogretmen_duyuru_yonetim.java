package com.paket.okulduyuru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class activity_ogretmen_duyuru_yonetim extends AppCompatActivity {

    private RelativeLayout rl_add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ogretmen_duyuru_yonetim);

        rl_add_button = findViewById(R.id.ac_ogretmen_duyuru_add);

        //Duyuru ekleme aktivitesine geçiş.
        rl_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),activity_ogretmen_duyuru_add.class));
            }
        });
    }
}
