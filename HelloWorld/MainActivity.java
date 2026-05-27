package com.example.moinfeha.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import java.io.FileInputStream;

public class MainActivity extends AppCompatActivity {

    // ❌ Hardcoded secret (Aikido will flag)
    private static final String API_KEY = "12345-SECRET-KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ❌ Insecure logging
        Log.d("DEBUG", "Loaded API key: " + API_KEY);

        // ❌ Unsafe WebView configuration
        WebView webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://example.com");

        // ❌ Unsafe file access (path traversal pattern)
        String filename = getIntent().getStringExtra("file");
        try {
            FileInputStream fis = new FileInputStream(filename);
        } catch (Exception e) {
            Log.e("ERROR", "File error: " + e.getMessage());
        }

        // ❌ Dangerous command execution pattern
        try {
            Runtime.getRuntime().exec("ping -c 1 " + filename);
        } catch (Exception e) {
            Log.e("ERROR", "Command error: " + e.getMessage());
        }
    }
}
