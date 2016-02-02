package com.learner.learncode.Test;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.webkit.WebView;

import com.learner.learncode.R;

import cn.pedant.SafeWebViewBridge.InjectedChromeClient;

/**
 * Created by liting on 2016/1/21.
 */
public class JsBridgeTest extends FragmentActivity {


    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        mWebView = (WebView)findViewById(R.id.my_webview);
        mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.setWebChromeClient(new InjectedChromeClient("HostApp",HostJsScope.class));
        mWebView.loadUrl("file:///android_asset/test.html");
    }
}
