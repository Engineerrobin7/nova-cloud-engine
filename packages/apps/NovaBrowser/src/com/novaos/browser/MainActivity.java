package com.novaos.browser;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private WebView mWebView;
    private EditText mUrlBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUrlBar = findViewById(R.id.url_bar);
        mWebView = findViewById(R.id.webview);
        mWebView.setWebViewClient(new NovaWebViewClient());
        mWebView.getSettings().setJavaScriptEnabled(true);

        findViewById(R.id.btn_go).setOnClickListener(v -> {
            String url = mUrlBar.getText().toString();
            if (!url.startsWith("http")) url = "https://" + url;
            mWebView.loadUrl(url);
        });

        mWebView.loadUrl("https://www.google.com"); // Default home
    }

    private class NovaWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // Nova Ad-Blocker: Simple domain-based blocking
            if (url.contains("ads.") || url.contains("doubleclick.net")) {
                android.util.Log.i("NovaBrowser", "Blocked ad: " + url);
                return true;
            }
            return false;
        }
    }
}
