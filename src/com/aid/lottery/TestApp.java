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
import java.net.*;
import java.io.*;
import org.jsoup.nodes.*;
import org.jsoup.*;
import org.jsoup.select.*;
import android.util.*;
import android.os.*;
import android.content.*;
import android.app.*;

public class TestApp extends Activity 
{
	public TextView text;
	public Context context;
	public ProgressDialog prog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		this.requestWindowFeature(Window.FEATURE_PROGRESS);

		super.onCreate(savedInstanceState);
		
		context = this;
		
		LinearLayout layout = new LinearLayout(this);
		text = new TextView(this);
		
		text.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
		layout.addView(text);
		setContentView(layout);
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

		int LOTTO_INDEX = Integer.parseInt(prefs.getString("state_chosen", "0"));

        final String url = getResources().obtainTypedArray(R.array.stateLottoWebsites).getString(LOTTO_INDEX);
		
		String myString = "";
		
		prog = ProgressDialog.show(context, "Fetching lottery results", "Fetching lottery results. Please wait");
		new NetClass().execute(url);
		
		//Toast.makeText(this, myString, Toast.LENGTH_LONG).show();
	}
	
	public class NetClass extends AsyncTask<String, Integer, String>
	{

		protected String doInBackground(String[] p1)
		{
			String myString = "";
			Document document;
			
			try
			{
				URL u = new URL(p1[0]);
				String test = "<html><head></head><body>hello</body></html>";
				document = Jsoup.parse(u, 5000);

				int count = 0;

				Elements content = document.getElementsByClass("info");

				for(Element numberGroup : content)
				{
					Elements numbers = numberGroup.getElementsByTag("span");

					for(Element number : numbers)
					{
						myString += number.text() + "\n";
					}
				}
				
			
				return myString;
			}
			catch (Exception e)
			{
				e.printStackTrace();
				//Log.d("AndroidRuntime", e.getMessage());
			}
			
			return "fail";
		}
		
		protected void onPostExecute(String value)
		{
			text.setText(value);
			prog.dismiss();
		}
	}
}
