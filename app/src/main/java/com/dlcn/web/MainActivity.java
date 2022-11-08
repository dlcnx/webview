package com.dlcn.web;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import org.mozilla.geckoview.GeckoRuntime;
import org.mozilla.geckoview.GeckoSession;
import org.mozilla.geckoview.GeckoSessionSettings;
import org.mozilla.geckoview.GeckoView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // GeckoView
        GeckoView view = new GeckoView(this);
        view.setAutofillEnabled(true);
        GeckoSession session = new GeckoSession();
        GeckoSessionSettings settings = session.getSettings();
        // 启用 js 支持
        settings.setAllowJavascript(true);
        // 适配手机屏幕
        settings.setViewportMode(GeckoSessionSettings.VIEWPORT_MODE_MOBILE);
        // 显示内容
        session.open(GeckoRuntime.create(this));
        view.setSession(session);
        session.loadUri("resource://android/assets/index.html");
        setContentView(view);
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