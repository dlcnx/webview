package com.dlcn.web;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;

import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // 程序入口
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置 WebView 内容
        WebView web_view = new WebView(this);
        web_view.getSettings().setJavaScriptEnabled(true);
        web_view.setWebChromeClient(new WebChromeClient());
        web_view.loadUrl("file:///android_asset/index.html");
        setContentView(web_view);
    }

    // 2s内双击退出
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}