package com.paket.okulduyuru.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.paket.okulduyuru.R;

public class activity_duyuru_update_screen extends AppCompatActivity {

    private Spinner bolumler;
    DatabaseReference reference;
    String baslik,context,yazar,date,time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duyuru_update_screen);

        reference = FirebaseDatabase.getInstance().getReference("Duyuru");


        bolumler = findViewById(R.id.ac_ogretmen_duyuru_update_spinner);

        ArrayAdapter<String> bolumAdapter = new ArrayAdapter<String>(activity_duyuru_update_screen.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.bolumler));
        bolumAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bolumler.setAdapter(bolumAdapter);

        getComingIntent();

    }

    private void sendUpdatesToFirebase() {
        if (isBaslikChanged() || isContextChanged()) {
            Toast.makeText(activity_duyuru_update_screen.this,"Güncelleme başarılı.",Toast.LENGTH_LONG).show();
        }
    }

    private boolean isContextChanged() {
        return true;
    }

    private boolean isBaslikChanged() {
        EditText duyuruBaslik = findViewById(R.id.ac_ogretmen_duyuru_update_title);
        if (baslik.equals(duyuruBaslik.getText().toString())) {

        }
        return true;
    }

    private void getComingIntent() {
        if (getIntent().hasExtra("baslik") && getIntent().hasExtra("context") && getIntent().hasExtra("yazar") && getIntent().hasExtra("time")
        && getIntent().hasExtra("date")) {
            String baslik = getIntent().getStringExtra("baslik");
            String context = getIntent().getStringExtra("context");
            String yazar = getIntent().getStringExtra("yazar");
            String time = getIntent().getStringExtra("time");
            String date = getIntent().getStringExtra("date");

            setDatas(baslik,context,yazar,time,date);
        }
    }

    private void setDatas(String baslik,String context,String yazar,String time,String date) {
        EditText duyuruBaslik = findViewById(R.id.ac_ogretmen_duyuru_update_title);
        duyuruBaslik.setText(baslik);
        EditText duyuruContext = findViewById(R.id.ac_ogretmen_duyuru_update_context);
        duyuruContext.setText(context);
        EditText duyuruYazar = findViewById(R.id.ac_ogretmen_duyuru_update_author);
        duyuruYazar.setText(yazar);

    }
}
