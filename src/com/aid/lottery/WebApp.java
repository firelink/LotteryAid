package com.aid.lottery;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.webkit.*;
import android.widget.*;
import android.view.*;

public class WebApp extends Activity 
{
	private String theArray[];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		this.requestWindowFeature(Window.FEATURE_PROGRESS);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web_layout);
		
		WebView webView = (WebView)findViewById(R.id.webView1);
		//WebView webView = new WebView(this);
		//setContentView(webView);
		
		webView.getSettings().setJavaScriptEnabled(true);
		
		final Activity act = this;
		webView.setWebChromeClient(new WebChromeClient()
		{
			public void onProgressChanged(WebView view, int progress)
			{
				act.setProgress(progress * 1000);
			}
		});
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		
		int LOTTO_INDEX = Integer.parseInt(prefs.getString("state_chosen", "0"));
		
		//final String mimeType = "text/html";
        //final String encoding = "utf-8";
        //final String url = "http://google.com";
        final String url = getResources().obtainTypedArray(R.array.stateLottoWebsites).getString(LOTTO_INDEX);
        
        webView.loadUrl(url);
        
		//webView.loadData("<br/><br/><a href='" + url + "'>Check the numbers.</a> <br/><br/> " +
		//		"Record the numbers below to check if you won!", mimeType, encoding);
				
		webView.setWebViewClient(new WebViewClient()
			{
				public boolean shouldOverrideUrlLoading(WebView view, String url)
				{
					//view.loadUrl(url);
					
					return false;
				}
			});
		
		
		Spinner spinner = (Spinner)findViewById(R.id.web_spinner);
		theArray = setTheGameArray();
		
		spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, theArray));
		
		Button webButton = (Button)findViewById(R.id.web_okButton);
		
		webButton.setOnClickListener(new Button.OnClickListener()
		{

				public void onClick(View p1)
				{
					// TODO: Implement this method
				}

			
		});
	}
	
	
	public String[] setTheGameArray()
	{
		String arrayString[];
		OrganizeEverything o_Everything = new OrganizeEverything(this, 1);
		arrayString = new String[o_Everything.getNumberOfGames()];
		
		for(int i = 0; i < arrayString.length; i++)
		{
			o_Everything = new OrganizeEverything(this, i+1);
			arrayString[i] = o_Everything.getGameName();
		}
		
		
		return arrayString;
		
	}
}
