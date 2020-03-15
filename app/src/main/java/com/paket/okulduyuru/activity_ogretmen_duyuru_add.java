package com.paket.okulduyuru;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class activity_ogretmen_duyuru_add extends AppCompatActivity {

    private String duyuru_context,duyuru_baslik,duyuru_bolum;
    private EditText ed_duyuru_baslik,ed_duyuru_icerik;
    private CheckBox chk_duyuru_bildirim;
    private Button btn_duyuru_yayinla;
    private Spinner bolumler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ogretmen_duyuru_add);


        //Spinner
        bolumler = findViewById(R.id.ac_ogretmen_duyuru_add_spinner);

        ArrayAdapter<String> bolumAdapter = new ArrayAdapter<String>(activity_ogretmen_duyuru_add.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.bolumler));
        bolumAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bolumler.setAdapter(bolumAdapter);

        //Casting Views
        ed_duyuru_baslik = findViewById(R.id.ac_ogretmen_duyuru_add_title);
        ed_duyuru_icerik = findViewById(R.id.ac_ogretmen_duyuru_add_context);
        //Checkbox
        chk_duyuru_bildirim = findViewById(R.id.ac_ogretmen_duyuru_add_checkbox_bildirim);
        //Button
        btn_duyuru_yayinla = findViewById(R.id.ac_ogretmen_duyuru_add_btnYayinla);

        btn_duyuru_yayinla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                duyuruBilgileriniKontrolEt();

            }
        });

    }

    private void duyuruBilgileriniKontrolEt() {
        duyuru_context = ed_duyuru_icerik.getText().toString();
        duyuru_baslik = ed_duyuru_baslik.getText().toString();
        duyuru_bolum = bolumler.getSelectedItem().toString();

        if (TextUtils.isEmpty(duyuru_context)) {
            Toast.makeText(activity_ogretmen_duyuru_add.this, "Bir duyuru içeriği girmelisiniz.", Toast.LENGTH_SHORT).show();
        }

        if (TextUtils.isEmpty(duyuru_baslik)) {
            Toast.makeText(activity_ogretmen_duyuru_add.this,"Bir duyuru başlığı girmelisiniz.",Toast.LENGTH_LONG).show();
        }

        if (duyuru_bolum == null) {
            Toast.makeText(activity_ogretmen_duyuru_add.this,"Duyuruyu hangi bölüme yayınlayacağınızı seçmelisiniz.",Toast.LENGTH_LONG).show();
        }

    }
}