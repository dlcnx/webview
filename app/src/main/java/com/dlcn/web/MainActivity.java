package com.dlcn.web;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.QbSdk.PreInitCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

public class MainActivity extends AppCompatActivity {
    // 程序入口
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TBS初始化
        QbSdk.setDownloadWithoutWifi(true);
        QbSdk.initX5Environment(this, new PreInitCallback() {
            @Override
            public void onCoreInitFinished() {}
            @Override
            public void onViewInitFinished(boolean isX5) {}
        });
        // 设置 WebView 内容
        WebView web_view = new WebView(this);
        web_view.setWebViewClient(new WebViewClient());
        web_view.setWebChromeClient(new WebChromeClient());

        WebSettings webSetting = web_view.getSettings();
        // 允许 js 运行
        webSetting.setJavaScriptEnabled(true);
        // 允许 file 协议跨域
        webSetting.setAllowFileAccessFromFileURLs(true);
        // 其他设置
        webSetting.setAllowFileAccess(true);
        webSetting.setSupportZoom(true);
        webSetting.setDatabaseEnabled(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setDomStorageEnabled(true);
        // 加载并切换界面
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