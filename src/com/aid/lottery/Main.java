package com.aid.lottery;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class Main extends Activity 
{
	private Dialog dialog;
	private Spinner spin;
	private int initSpin = 0;
	private Button butt;
	private SharedPreferences prefs;
	private SharedPreferences prefs2;
	private SharedPreferences.Editor pEditor;
	private SharedPreferences.Editor pEditor2;
	public static final String PREFS = "LotteryAid.prefs";
	Bundle bundleGame1 = new Bundle();
	Bundle bundleGame2 = new Bundle();
	Bundle bundleGame3 = new Bundle();
	Bundle bundleGame4 = new Bundle();


	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		if(!prefs.contains("state_chosen"))
		{
			showDialog(0);
		}
		else 
		{
			begin();
		}
		
		setContentView(R.layout.blank_layout);
	}
	
	protected android.app.Dialog onCreateDialog(int id)
	{
		switch(id)
		{
			case 0:
			{
				prefs = PreferenceManager.getDefaultSharedPreferences(Main.this);
				pEditor = prefs.edit();
				
				prefs2 = this.getSharedPreferences(PREFS, 0);
				pEditor2 = prefs2.edit();
				
				dialog = new Dialog(Main.this);
				dialog.setContentView(R.layout.custom_dialog);
				dialog.setTitle("Select a State");
				
				butt = (Button)dialog.findViewById(R.id.cust_dialog_button);
				butt.setEnabled(false);
				
				String arrayS[] = getResources().getStringArray(R.array.stateLottoAddresses);
				spin = (Spinner)dialog.findViewById(R.id.custom_spinner);
				
				ArrayAdapter<String> spinAdapter = new ArrayAdapter<String>(Main.this,
					R.layout.custom_spinner, R.id.spinner_text, arrayS);
					
				spin.setAdapter(spinAdapter);
				spinAdapter.setDropDownViewResource(R.layout.custom_spinner_row);
				
				spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
				{

					public void onItemSelected(AdapterView<?> p1, View p2, int p3, long p4)
					{
						if(initSpin > 0)
						{
							String typedZ = getResources().obtainTypedArray(R.array.stateLottoAddresses).getString(p3);
							if(typedZ.toString().equals("Select a State")){butt.setEnabled(false);}
							else if(typedZ.toString().equals("PA"))
							{
								pEditor.remove("state_chosen");
								pEditor.commit();
								butt.setEnabled(false);
							}
							else
							{
								pEditor.putString("state_chosen", p3+"");
								pEditor.commit();
								butt.setEnabled(true);
							}

						}
						else
							initSpin++;
					}

					public void onNothingSelected(AdapterView<?> p1)
					{
						// TODO: Implement this method
					}
				});				
				
				butt.setOnClickListener(new View.OnClickListener()
				{
					public void onClick(View arg0) 
					{
						if(prefs.contains("state_chosen"))
						{
							for(int i = 0; i < 5; i++)
							{
								pEditor2.remove("daily_number" + i);
								pEditor2.remove("big4_number" + i);
								pEditor2.remove("cash5_number" + i);
								pEditor2.remove("power_number" + i);
								
								pEditor2.commit();
							}
							for(int i = 0; i < 20; i++)
							{
								pEditor2.remove("daily_daily" + i);
								pEditor2.remove("big4_big4" + i);
								pEditor2.remove("cash5_cash5" + i);
								pEditor2.remove("power_power" + i);
								
								pEditor2.commit();
							}
							
							pEditor2.remove("daily_num");
							pEditor2.remove("big4_num");
							pEditor2.remove("cash5_num");
							pEditor2.remove("power_num");
							
							pEditor2.commit();
							
							begin();
							
							dialog.dismiss();
						}
						else
						{
							Toast.makeText(Main.this, "You must choose a state", Toast.LENGTH_SHORT).show();
						}
					}	
				});
			}
		}
		return dialog;
	}
	
	public void begin()
	{
		Intent myIntent = new Intent(Main.this, TabMenu.class);
		startActivity(myIntent);
		overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
		finish();
	}
}
