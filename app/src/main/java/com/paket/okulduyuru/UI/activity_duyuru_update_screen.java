package com.paket.okulduyuru.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.paket.okulduyuru.Model.Duyuru;
import com.paket.okulduyuru.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class activity_duyuru_update_screen extends AppCompatActivity {

    private Spinner bolumler;
    private Button btnUpdate;
    DatabaseReference reference;
    String baslik,context,yazar,date,time,pid;
    EditText duyuruBaslik,duyuruContext,duyuruYazar;
    String saveCurrentDate,saveCurrentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duyuru_update_screen);

        btnUpdate = findViewById(R.id.ac_ogretmen_duyuru_update_btnGuncelle);

        reference = FirebaseDatabase.getInstance().getReference("Duyuru");

        baslik = getIntent().getStringExtra("baslik");
        context = getIntent().getStringExtra("context");
        yazar = getIntent().getStringExtra("yazar");
        time = getIntent().getStringExtra("time");
        date = getIntent().getStringExtra("date");
        pid = getIntent().getStringExtra("pid");
        Toast.makeText(activity_duyuru_update_screen.this,pid,Toast.LENGTH_LONG).show();

        bolumler = findViewById(R.id.ac_ogretmen_duyuru_update_spinner);

        ArrayAdapter<String> bolumAdapter = new ArrayAdapter<String>(activity_duyuru_update_screen.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.bolumler));
        bolumAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bolumler.setAdapter(bolumAdapter);

        duyuruBaslik = findViewById(R.id.ac_ogretmen_duyuru_update_title);
        duyuruBaslik.setText(baslik);
        duyuruContext = findViewById(R.id.ac_ogretmen_duyuru_update_context);
        duyuruContext.setText(context);
        duyuruYazar = findViewById(R.id.ac_ogretmen_duyuru_update_author);
        duyuruYazar.setText(yazar);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                duyuruBilgileriniGuncelle(pid);
            }
        });

    }

    private void duyuruBilgileriniGuncelle(String pid) {
        String duyuru_baslik = duyuruBaslik.getText().toString();
        String duyuru_context = duyuruContext.getText().toString();
        String duyuru_yazar = duyuruYazar.getText().toString();
        String duyuru_bolum = bolumler.getSelectedItem().toString();

        if (TextUtils.isEmpty(duyuru_context)) {
            Toast.makeText(activity_duyuru_update_screen.this, "Bir duyuru içeriği girmelisiniz.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(duyuru_baslik)) {
            Toast.makeText(activity_duyuru_update_screen.this,"Bir duyuru başlığı girmelisiniz.",Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(duyuru_yazar)) {
            Toast.makeText(activity_duyuru_update_screen.this,"Yayınlayan kişiyi belirtmeniz gerekiyor.",Toast.LENGTH_LONG).show();
            return;
        }

        if (duyuru_bolum == null) {
            Toast.makeText(activity_duyuru_update_screen.this,"Duyuruyu hangi bölüme yayınlayacağınızı seçmelisiniz.",Toast.LENGTH_LONG).show();
            return;
        }

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm");
        saveCurrentTime = currentTime.format(calendar.getTime());

        HashMap<String, Object> duyuruMap = new HashMap<>();
        duyuruMap.put("date",saveCurrentDate);
        duyuruMap.put("time",saveCurrentTime);
        duyuruMap.put("context",duyuru_context);
        duyuruMap.put("title",duyuru_baslik);
        duyuruMap.put("bolum",duyuru_bolum);
        duyuruMap.put("yazar",duyuru_yazar);
        Duyuru duyuru = new Duyuru();
        duyuru.setPid(pid);

        reference.child(pid).updateChildren(duyuruMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(activity_duyuru_update_screen.this,"Duyuru başarıyla güncellendi.",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),activity_ogretmen_duyuru_yonetim.class));
                        }
                        else {
                            String message = task.getException().toString();
                            Toast.makeText(activity_duyuru_update_screen.this,"Error:" + message,Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

}
