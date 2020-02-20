package com.paket.okulduyuru;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ArelWebView extends AppCompatActivity {
<<<<<<< HEAD
    WebView webView;
=======

    WebView webView;

>>>>>>> 37306d8928ea673591e0c7788e6701cb0a8158a1
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arel_web_view);

<<<<<<< HEAD
        webView=(WebView)findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.arel.edu.tr");


=======


        webView=(WebView)findViewById(R.id.webView);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.arel.edu.tr/");
>>>>>>> 37306d8928ea673591e0c7788e6701cb0a8158a1
    }
}
