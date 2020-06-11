package com.paket.okulduyuru.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
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

public class activity_ogretmen_duyuru_add extends AppCompatActivity {

    private String duyuru_context,duyuru_baslik,duyuru_bolum,duyuru_yazar;
    private EditText ed_duyuru_baslik,ed_duyuru_icerik,ed_duyuru_yazar;
    private CheckBox chk_duyuru_bildirim;
    private Button btn_duyuru_yayinla;
    private Spinner bolumler;
    DatabaseReference databaseReference;
    public static String duyuruRandomKey,saveCurrentDate,saveCurrentTime;

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
        ed_duyuru_yazar = findViewById(R.id.ac_ogretmen_duyuru_add_author);
        //Checkbox
        chk_duyuru_bildirim = findViewById(R.id.ac_ogretmen_duyuru_add_checkbox_bildirim);
        //Button
        btn_duyuru_yayinla = findViewById(R.id.ac_ogretmen_duyuru_add_btnYayinla);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Duyuru");

        btn_duyuru_yayinla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                duyuruBilgileriniVeritabaninaKaydet();

            }
        });

    }

    private void duyuruBilgileriniVeritabaninaKaydet() {
        duyuru_context = ed_duyuru_icerik.getText().toString();
        duyuru_baslik = ed_duyuru_baslik.getText().toString();
        duyuru_bolum = bolumler.getSelectedItem().toString();
        duyuru_yazar = ed_duyuru_yazar.getText().toString();

        if (TextUtils.isEmpty(duyuru_context)) {
            Toast.makeText(activity_ogretmen_duyuru_add.this, "Bir duyuru içeriği girmelisiniz.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(duyuru_baslik)) {
            Toast.makeText(activity_ogretmen_duyuru_add.this,"Bir duyuru başlığı girmelisiniz.",Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(duyuru_yazar)) {
            Toast.makeText(activity_ogretmen_duyuru_add.this,"Yayınlayan kişiyi belirtmeniz gerekiyor.",Toast.LENGTH_LONG).show();
            return;
        }

        if (duyuru_bolum == null) {
            Toast.makeText(activity_ogretmen_duyuru_add.this,"Duyuruyu hangi bölüme yayınlayacağınızı seçmelisiniz.",Toast.LENGTH_LONG).show();
            return;
        }

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm");
        saveCurrentTime = currentTime.format(calendar.getTime());

        duyuruRandomKey = saveCurrentDate + saveCurrentTime;

        final HashMap<String, Object> duyuruMap = new HashMap<>();
        duyuruMap.put("pid",duyuruRandomKey);
        duyuruMap.put("date",saveCurrentDate);
        duyuruMap.put("time",saveCurrentTime);
        duyuruMap.put("context",duyuru_context);
        duyuruMap.put("title",duyuru_baslik);
        duyuruMap.put("bolum",duyuru_bolum);
        duyuruMap.put("yazar",duyuru_yazar);
        Duyuru duyuru1 = new Duyuru();
        String pid = (String) duyuruMap.get("pid");
        duyuru1.setPid(pid);

        databaseReference.child(duyuruRandomKey).updateChildren(duyuruMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) { Duyuru duyuru = new Duyuru();
                            Toast.makeText(activity_ogretmen_duyuru_add.this,"Duyuru yayınlama işlemi başarılı.",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),activity_ogretmen_duyuru_yonetim.class));

                        }
                        else {
                            String message = task.getException().toString();
                            Toast.makeText(activity_ogretmen_duyuru_add.this,"Error:" + message,Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),activity_ogretmen_duyuru_yonetim.class));
    }
}