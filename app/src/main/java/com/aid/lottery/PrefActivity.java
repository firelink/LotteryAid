package com.aid.lottery;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;

public class PrefActivity extends PreferenceActivity
{
	private SharedPreferences prefs;
	private SharedPreferences prefs2;
	@SuppressWarnings("unused")
	private SharedPreferences.Editor pEditor;
	private SharedPreferences.Editor pEditor2;
	private int theNum;
	PreferenceCategory pCategory1;
	PreferenceCategory pCategory2;
	PreferenceCategory pCategory3;
	PreferenceCategory pCategory4;
	PreferenceScreen preferenceScreen;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.main_prefs);
		
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		pEditor = prefs.edit();
		prefs2 = this.getSharedPreferences("LotteryAid.prefs", 0);
		pEditor2 = prefs2.edit();
		
		theNum = Integer.parseInt(prefs.getString("state_chosen", "Error"));
		
		pCategory1 = (PreferenceCategory)findPreference("game_5_cat");
		pCategory2 = (PreferenceCategory)findPreference("game_6_cat");
		pCategory3 = (PreferenceCategory)findPreference("game_7_cat");
		pCategory4 = (PreferenceCategory)findPreference("game_8_cat");
		preferenceScreen = (PreferenceScreen)findPreference("main_preferencescreen");
		
		
		setTitles();
		setSummaries();
		
		
		ListPreference lp102 = (ListPreference)findPreference("state_chosen");
		lp102.setOnPreferenceChangeListener(new OnPreferenceChangeListener() 
		{	
			public boolean onPreferenceChange(Preference preference, Object newValue) 
			{
				theNum = Integer.parseInt(newValue.toString());
				setTitles();
				setSummaries();
				
				for(int i = 0; i < 5; i++)
				{
					pEditor2.remove("daily_number" + i);
					pEditor2.remove("big4_number" + i);
					pEditor2.remove("cash5_number" + i);
					pEditor2.remove("power_number" + i);
					pEditor2.remove("game5_number" + i);
					pEditor2.remove("game6_number" + i);
					pEditor2.remove("game7_number" + i);
					pEditor2.remove("game8_number" + i);
					
					pEditor2.commit();
				}
				for(int i = 0; i < 20; i++)
				{
					pEditor2.remove("daily_daily" + i);
					pEditor2.remove("big4_big4" + i);
					pEditor2.remove("cash5_cash5" + i);
					pEditor2.remove("power_power" + i);
					pEditor2.remove("game5_game5" + i);
					pEditor2.remove("game6_game6" + i);
					pEditor2.remove("game7_game7" + i);
					pEditor2.remove("game8_game8" + i);
					
					pEditor2.commit();
				}
				
				pEditor2.remove("daily_num");
				pEditor2.remove("big4_num");
				pEditor2.remove("cash5_num");
				pEditor2.remove("power_num");
				pEditor2.remove("game5_num");
				pEditor2.remove("game6_num");
				pEditor2.remove("game7_num");
				pEditor2.remove("game8_num");
				
				pEditor2.commit();
				
				//Toast.makeText(PrefActivity.this, newValue.toString(), Toast.LENGTH_LONG).show();
				
//				if(newValue.toString().equals("33"))
//				{
//					pEditor.remove("state_chosen");
//					pEditor.commit();
//					
//					return false;
//				}
					
				return true;
			}
		});

	}
	
	public void setTitles()
	{
		int i = Integer.parseInt(getResources().obtainTypedArray(R.array.stateLottoGameNum)
				.getString(theNum));
		
		if(i < 5)
		{
			preferenceScreen.removePreference(pCategory1);
			preferenceScreen.removePreference(pCategory2);
			preferenceScreen.removePreference(pCategory3);
			preferenceScreen.removePreference(pCategory4);
		}
		if(i < 6)
		{
			preferenceScreen.removePreference(pCategory2);
			preferenceScreen.removePreference(pCategory3);
			preferenceScreen.removePreference(pCategory4);
		}
		if(i < 7)
		{
			preferenceScreen.removePreference(pCategory3);
			preferenceScreen.removePreference(pCategory4);
		}
		if(i < 8)
		{
			preferenceScreen.removePreference(pCategory4);
		}
		
		if(i > 4)
		{	
			preferenceScreen.addPreference(pCategory1);
		}
		if(i > 5)
		{	
			preferenceScreen.addPreference(pCategory2);
		}
		if(i > 6)
		{	
			preferenceScreen.addPreference(pCategory3);
		}
		if(i > 7)
		{	
			preferenceScreen.addPreference(pCategory4);
		}
		
		ListPreference lp = (ListPreference)findPreference("daily_background");
		lp.setTitle(getResources().obtainTypedArray(R.array.stateLottoFirstGameName)
				.getString(theNum));
		
		ListPreference lp2 = (ListPreference)findPreference("big4_background");
		lp2.setTitle(getResources().obtainTypedArray(R.array.stateLottoSecondGameName)
				.getString(theNum));
		
		ListPreference lp3 = (ListPreference)findPreference("cash5_background");
		lp3.setTitle(getResources().obtainTypedArray(R.array.stateLottoThirdGameName)
				.getString(theNum));
		
		ListPreference lp4 = (ListPreference)findPreference("power_background");
		lp4.setTitle(getResources().obtainTypedArray(R.array.stateLottoFourthGameName)
				.getString(theNum));
		
		if(i > 4)
		{
			ListPreference lp5 = (ListPreference)findPreference("game5_background");
			lp5.setTitle(getResources().obtainTypedArray(R.array.stateLottoFifthGameName)
					.getString(theNum));
		}
		
		if(i > 5)
		{
			ListPreference lp6 = (ListPreference)findPreference("game6_background");
			lp6.setTitle(getResources().obtainTypedArray(R.array.stateLottoSixthGameName)
					.getString(theNum));
		}
		
		if(i > 6)
		{
			ListPreference lp7 = (ListPreference)findPreference("game7_background");
			lp7.setTitle(getResources().obtainTypedArray(R.array.stateLottoSeventhGameName)
					.getString(theNum));
		}
		
		if(i > 7)
		{
			ListPreference lp8 = (ListPreference)findPreference("game8_background");
			lp8.setTitle(getResources().obtainTypedArray(R.array.stateLottoEighthGameName)
					.getString(theNum));
		}
	}
	
	public void setSummaries()
	{
		ListPreference lp = (ListPreference)findPreference("daily_background");
		lp.setSummary("Choose color style for " + lp.getTitle() + " page");
		
		ListPreference lp2 = (ListPreference)findPreference("big4_background");
		lp2.setSummary("Choose color style for " + lp2.getTitle() + " page");
		
		ListPreference lp3 = (ListPreference)findPreference("cash5_background");
		lp3.setSummary("Choose color style for " + lp3.getTitle() + " page");
		
		ListPreference lp4 = (ListPreference)findPreference("power_background");
		lp4.setSummary("Choose color style for " + lp4.getTitle() + " page");
	}
}
