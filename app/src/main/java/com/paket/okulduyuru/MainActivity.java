package com.paket.okulduyuru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends AppCompatActivity {
  private CircleImageView imgLogo;
  private static int Gecis_Suresi = 4000; // 4 Saniye


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    imgLogo = (CircleImageView)findViewById(R.id.arel); //R Sınıfı uygulamamızdaki bütün resoource'lara erişmemizi sağlıyor.
    // Her bir resource için ayrı bir R alt sınıfı vardır.

    Animation animation = AnimationUtils.loadAnimation(this,R.anim.animation);
    imgLogo.startAnimation(animation);
    //Handler sınıfı ve Runnable sınıfı arka planda çalışan belirli aralıklar ile tekrarlanmasını istediğimiz olayları gerçekleştirmemizi sağlar.
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        Intent gecis = new Intent(MainActivity.this,LoginActivity.class); //Intent aktiviteler arasında geçiş yapmamızı sağlar.
        startActivity(gecis);
        finish();
      }
    },Gecis_Suresi);





  }
}