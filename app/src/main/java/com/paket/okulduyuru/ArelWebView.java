package com.paket.okulduyuru;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ArelWebView extends AppCompatActivity {

  private WebView webView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_arel_web_view);



    webView=(WebView)findViewById(R.id.webView);

    webView.getSettings().setJavaScriptEnabled(true);
    webView.setWebViewClient(new WebViewClient());
    webView.loadUrl("https://www.arel.edu.tr");
  }
}