package com.example.calculatorhtml5;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
	long startTime = 0;
	long endTime = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		WebView webView = (WebView) findViewById(R.id.web_view);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setDomStorageEnabled(true);
		
		webView.setWebViewClient(new WebViewClient() {
			public void onPageFinished(WebView view, String url) {
				endTime = System.currentTimeMillis();
				calculateTime();
			}
		});
		
		startTime = System.currentTimeMillis();
		webView.loadUrl("file:///android_asset/www/index.html");
	}
	
	void calculateTime() {
		long time = endTime - startTime;
		Log.d("CAL", Long.toString(time));
	}
}
