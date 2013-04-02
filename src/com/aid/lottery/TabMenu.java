package com.aid.lottery;

import java.util.HashMap;

import android.R.integer;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabContentFactory;
import android.widget.TextView;
import android.widget.Toast;

public class TabMenu extends FragmentActivity implements TabHost.OnTabChangeListener 
{
	private TabHost mTabHost;
	private HashMap<String, TabInfo> mapTabInfo = new HashMap<String, TabInfo>();
	private TabInfo mLastTab = null;
	private Bundle b;
	private Drawable dailyDraw;
	private Drawable big4Draw;
	private Drawable cash5Draw;
	private Drawable powerDraw;
	private Drawable picksDraw;
	private SharedPreferences prefs;
	public static final String PREFS = "LotteryAid.prefs";
	private int requ = 0;
	private OrganizeEverything o_Everything;
	Bundle bundleGame1 = new Bundle();
	Bundle bundleGame2 = new Bundle();
	Bundle bundleGame3 = new Bundle();
	Bundle bundleGame4 = new Bundle();

	public class TabInfo 
	{
		private String tag;
		private Class<?> clss;
		private Bundle args;
		private Fragment fragment;

		TabInfo(String tag, Class<?> clazz, Bundle args) 
		{
			this.tag = tag;
			this.clss = clazz;
			this.args = args;
		}
	}

	class TabFactory implements TabContentFactory 
	{
		private final Context mContext;

		public TabFactory(Context context) 
		{
			mContext = context;
		}

		public View createTabContent(String tag) 
		{
			View v = new View(mContext);
			v.setMinimumWidth(0);
			v.setMinimumHeight(0);
			return v;
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		if(requ == 0)
		{
			//this.requestWindowFeature(Window.FEATURE_NO_TITLE);
			requ++;
		}
		
		super.onCreate(savedInstanceState);
		
		prefs = PreferenceManager.getDefaultSharedPreferences(this);

			Toast.makeText(this, prefs.getString("state_chosen", "hello"), Toast.LENGTH_SHORT).show();
		
		setContentView(R.layout.tab_main);
		//initOnCreate(savedInstanceState);
		
		prefs = PreferenceManager.getDefaultSharedPreferences(TabMenu.this);

		initTabHost(savedInstanceState);
		if (savedInstanceState != null) 
		{
			mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
		}
				
		initStyles();
		initTabWidgetStyle();
	}
	
	public void initTabWidgetStyle()
	{
		int tada = 1;
		
		try {
			
			((TextView)((RelativeLayout)mTabHost.getTabWidget().getChildAt(0)).getChildAt(tada)).setTextSize(10);
			((TextView)((RelativeLayout)mTabHost.getTabWidget().getChildAt(1)).getChildAt(tada)).setTextSize(10);
			((TextView)((RelativeLayout)mTabHost.getTabWidget().getChildAt(2)).getChildAt(tada)).setTextSize(10);
			((TextView)((RelativeLayout)mTabHost.getTabWidget().getChildAt(3)).getChildAt(tada)).setTextSize(10);
			((TextView)((RelativeLayout)mTabHost.getTabWidget().getChildAt(4)).getChildAt(tada)).setTextSize(10);
			
			((TextView)((RelativeLayout)mTabHost.getTabWidget().getChildAt(0)).getChildAt(tada)).setGravity(Gravity.CENTER);
			((TextView)((RelativeLayout)mTabHost.getTabWidget().getChildAt(1)).getChildAt(tada)).setGravity(Gravity.CENTER);
			((TextView)((RelativeLayout)mTabHost.getTabWidget().getChildAt(2)).getChildAt(tada)).setGravity(Gravity.CENTER);
			((TextView)((RelativeLayout)mTabHost.getTabWidget().getChildAt(3)).getChildAt(tada)).setGravity(Gravity.CENTER);
	
			mTabHost.getTabWidget().getChildAt(0).setBackgroundDrawable(dailyDraw);
			mTabHost.getTabWidget().getChildAt(1).setBackgroundDrawable(big4Draw);
			mTabHost.getTabWidget().getChildAt(2).setBackgroundDrawable(cash5Draw);
			mTabHost.getTabWidget().getChildAt(3).setBackgroundDrawable(powerDraw);
			mTabHost.getTabWidget().getChildAt(4).setBackgroundDrawable(picksDraw);
			
			o_Everything = new OrganizeEverything(this, 1);
			((TextView)((RelativeLayout)mTabHost.getTabWidget().getChildAt(0)).getChildAt(tada))
							.setText(o_Everything.getGameName());
			((TextView)((RelativeLayout)mTabHost.getTabWidget().getChildAt(0)).getChildAt(tada))
					.setTextColor(Color.WHITE);
			
			o_Everything = new OrganizeEverything(this, 2);
			((TextView)((RelativeLayout)mTabHost.getTabWidget().getChildAt(1)).getChildAt(tada))
							.setText(o_Everything.getGameName());
			((TextView)((RelativeLayout)mTabHost.getTabWidget().getChildAt(1)).getChildAt(tada))
					.setTextColor(Color.WHITE);
			
			o_Everything = new OrganizeEverything(this, 3);
			((TextView)((RelativeLayout)mTabHost.getTabWidget().getChildAt(2)).getChildAt(tada))
							.setText(o_Everything.getGameName());
			((TextView)((RelativeLayout)mTabHost.getTabWidget().getChildAt(2)).getChildAt(tada))
					.setTextColor(Color.WHITE);
			
			o_Everything = new OrganizeEverything(this, 4);
			((TextView)((RelativeLayout)mTabHost.getTabWidget().getChildAt(3)).getChildAt(tada))
							.setText(o_Everything.getGameName());
			((TextView)((RelativeLayout)mTabHost.getTabWidget().getChildAt(3)).getChildAt(tada))
				.setTextColor(Color.WHITE);
		
		} catch (Exception e) 
		{
			Log.d("com.aid.lottery", e.getMessage());
			
			tada = 1;
			
			((TextView)((LinearLayout)mTabHost.getTabWidget().getChildAt(0)).getChildAt(tada)).setTextSize(10);
			((TextView)((LinearLayout)mTabHost.getTabWidget().getChildAt(1)).getChildAt(tada)).setTextSize(10);
			((TextView)((LinearLayout)mTabHost.getTabWidget().getChildAt(2)).getChildAt(tada)).setTextSize(10);
			((TextView)((LinearLayout)mTabHost.getTabWidget().getChildAt(3)).getChildAt(tada)).setTextSize(10);
			((TextView)((LinearLayout)mTabHost.getTabWidget().getChildAt(4)).getChildAt(tada)).setTextSize(10);
			
			((TextView)((LinearLayout)mTabHost.getTabWidget().getChildAt(0)).getChildAt(tada)).setGravity(Gravity.CENTER);
			((TextView)((LinearLayout)mTabHost.getTabWidget().getChildAt(1)).getChildAt(tada)).setGravity(Gravity.CENTER);
			((TextView)((LinearLayout)mTabHost.getTabWidget().getChildAt(2)).getChildAt(tada)).setGravity(Gravity.CENTER);
			((TextView)((LinearLayout)mTabHost.getTabWidget().getChildAt(3)).getChildAt(tada)).setGravity(Gravity.CENTER);
	
			mTabHost.getTabWidget().getChildAt(0).setBackgroundDrawable(dailyDraw);
			mTabHost.getTabWidget().getChildAt(1).setBackgroundDrawable(big4Draw);
			mTabHost.getTabWidget().getChildAt(2).setBackgroundDrawable(cash5Draw);
			mTabHost.getTabWidget().getChildAt(3).setBackgroundDrawable(powerDraw);
			mTabHost.getTabWidget().getChildAt(4).setBackgroundDrawable(picksDraw);
			
			o_Everything = new OrganizeEverything(this, 1);
			((TextView)((LinearLayout)mTabHost.getTabWidget().getChildAt(0)).getChildAt(tada))
							.setText(o_Everything.getGameName());
			((TextView)((LinearLayout)mTabHost.getTabWidget().getChildAt(0)).getChildAt(tada))
							.setTextColor(Color.WHITE);
			
			o_Everything = new OrganizeEverything(this, 2);
			((TextView)((LinearLayout)mTabHost.getTabWidget().getChildAt(1)).getChildAt(tada))
							.setText(o_Everything.getGameName());
			((TextView)((LinearLayout)mTabHost.getTabWidget().getChildAt(1)).getChildAt(tada))
							.setTextColor(Color.WHITE);
			
			o_Everything = new OrganizeEverything(this, 3);
			((TextView)((LinearLayout)mTabHost.getTabWidget().getChildAt(2)).getChildAt(tada))
							.setText(o_Everything.getGameName());
			((TextView)((LinearLayout)mTabHost.getTabWidget().getChildAt(2)).getChildAt(tada))
							.setTextColor(Color.WHITE);
			
			o_Everything = new OrganizeEverything(this, 4);
			((TextView)((LinearLayout)mTabHost.getTabWidget().getChildAt(3)).getChildAt(tada))
							.setText(o_Everything.getGameName());
			((TextView)((LinearLayout)mTabHost.getTabWidget().getChildAt(3)).getChildAt(tada))
							.setTextColor(Color.WHITE);
		}
	}

	public void onResumeFragments()
	{
		super.onResumeFragments();
		if(prefs.contains("state_chosen"))
		{
			initStyles();
			initTabWidgetStyle();
			
			if(o_Everything.getNumberOfGames() > 4)
			{
				mTabHost.getTabWidget().getChildAt(5).setVisibility(View.VISIBLE);
			}
			else 
			{
				mTabHost.getTabWidget().getChildAt(5).setVisibility(View.GONE);
			}
			
//			this.onTabChanged("Daily");
//			mTabHost.setCurrentTab(0);
		}
	}

	protected void onSaveInstanceState(Bundle out) 
	{
		out.putString("tab", mTabHost.getCurrentTabTag());
		super.onSaveInstanceState(out);
	}
	
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflate = getMenuInflater();
		inflate.inflate(R.menu.main_menu, menu);

		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId())
		{
			case R.id.main_menu_picks:
			{
				this.onTabChanged("Picks");
				mTabHost.setCurrentTab(4);
				break;
			}
			case R.id.main_menu_web:
			{
				Intent myIntent = new Intent(TabMenu.this, TestApp.class);
				startActivity(myIntent);
				break;
			}
			case R.id.main_menu_settings:
			{
				Intent myIntent = new Intent(TabMenu.this, PrefActivity.class);
				startActivity(myIntent);
				break;
			}
			case R.id.main_menu_help:
			{
//				Intent intent = new Intent(TabMenu.this, TabMenu.class);
//				startActivity(intent);
//				break;
			}
		}

		return true;
	}
	
	public void onConfigurationChanged(Configuration newConfig)
	{
		super.onConfigurationChanged(newConfig);
		
		setContentView(R.layout.tab_main);

		initTabHost(b);
		if (b != null) 
		{
			mTabHost.setCurrentTabByTag(b.getString("tab"));
		}
		initStyles();
		mTabHost.getTabWidget().getChildAt(0).setBackgroundDrawable(dailyDraw);
		mTabHost.getTabWidget().getChildAt(1).setBackgroundDrawable(big4Draw);
		mTabHost.getTabWidget().getChildAt(2).setBackgroundDrawable(cash5Draw);
		mTabHost.getTabWidget().getChildAt(3).setBackgroundDrawable(powerDraw);
		mTabHost.getTabWidget().getChildAt(4).setBackgroundDrawable(picksDraw);
		
	}
	
	private void initStyles()
	{
		dailyDraw = getResources().obtainTypedArray(R.array.numberStyleTab).getDrawable(Integer.parseInt(prefs.getString("daily_background", "2")));
		big4Draw = getResources().obtainTypedArray(R.array.numberStyleTab).getDrawable(Integer.parseInt(prefs.getString("big4_background", "1")));
		cash5Draw = getResources().obtainTypedArray(R.array.numberStyleTab).getDrawable(Integer.parseInt(prefs.getString("cash5_background", "0")));
		powerDraw = getResources().obtainTypedArray(R.array.numberStyleTab).getDrawable(Integer.parseInt(prefs.getString("power_background", "6")));
		picksDraw = getResources().obtainTypedArray(R.array.numberStyleTab).getDrawable(Integer.parseInt(prefs.getString("picks_background", "5")));
	}
	
	private void initBundles()
	{
		bundleGame1.putString("string1", "daily_number");
		bundleGame1.putString("string2", "daily_daily");
		bundleGame1.putString("stringNum", "daily_num");
		bundleGame1.putString("stringBack", "daily_background");
		bundleGame1.putInt("gameNum", 1);
		bundleGame1.putString("defString", "2");
		
		bundleGame2.putString("string1", "big4_number");
		bundleGame2.putString("string2", "big4_big4");
		bundleGame2.putString("stringNum", "big4_num");
		bundleGame2.putString("stringBack", "big4_background");
		bundleGame2.putInt("gameNum", 2);
		bundleGame2.putString("defString", "1");
		
		bundleGame3.putString("string1", "cash5_number");
		bundleGame3.putString("string2", "cash5_cash5");
		bundleGame3.putString("stringNum", "cash5_num");
		bundleGame3.putString("stringBack", "cash5_background");
		bundleGame3.putInt("gameNum", 3);
		bundleGame3.putString("defString", "0");
		
		bundleGame4.putString("string1", "power_number");
		bundleGame4.putString("string2", "power_power");
		bundleGame4.putString("stringNum", "power_num");
		bundleGame4.putString("stringBack", "power_background");
		bundleGame4.putInt("gameNum", 4);
		bundleGame4.putString("defString", "6");
	}

	private void initTabHost(Bundle args) 
	{
		mTabHost = (TabHost)findViewById(android.R.id.tabhost);
		mTabHost.setup();
		TabInfo tabInfo = null;
		
		initBundles();
		
		TabMenu.addTab(this, this.mTabHost, this.mTabHost
				.newTabSpec("Daily").setIndicator("Daily"),
				(tabInfo = new TabInfo("Daily", InitGames.class, bundleGame1)));
		this.mapTabInfo.put(tabInfo.tag, tabInfo);
		
		TabMenu.addTab(this, this.mTabHost, this.mTabHost
				.newTabSpec("Big4").setIndicator("Big 4"),
				(tabInfo = new TabInfo("Big4", InitGames.class, bundleGame2)));
		this.mapTabInfo.put(tabInfo.tag, tabInfo);
		
		TabMenu.addTab(this, this.mTabHost, this.mTabHost
					   .newTabSpec("Cash5").setIndicator("Cash 5"),
					   (tabInfo = new TabInfo("Cash5", InitGames.class, bundleGame3)));
		this.mapTabInfo.put(tabInfo.tag, tabInfo);
		
		TabMenu.addTab(this, this.mTabHost, this.mTabHost
					   .newTabSpec("Power").setIndicator("Powerball"),
					   (tabInfo = new TabInfo("Power", InitGames.class, bundleGame4)));
		this.mapTabInfo.put(tabInfo.tag, tabInfo);
		
		TabMenu.addTab(this, this.mTabHost, this.mTabHost
				.newTabSpec("Picks").setIndicator("Picks"),
				(tabInfo = new TabInfo("Picks", Picks.class, args)));
		this.mapTabInfo.put(tabInfo.tag, tabInfo);
		
		TabMenu.addTab(this, this.mTabHost, this.mTabHost
				.newTabSpec("More").setIndicator("->"),
				(tabInfo = new TabInfo("More", TabMenuMore.class, args)));
		this.mapTabInfo.put(tabInfo.tag, tabInfo);
		
		o_Everything = new OrganizeEverything(this, 1);
		if(o_Everything.getNumberOfGames() > 4)
		{
			mTabHost.getTabWidget().getChildAt(5).setVisibility(View.VISIBLE);
		}
		else 
		{
			mTabHost.getTabWidget().getChildAt(5).setVisibility(View.GONE);
		}
		int height = mTabHost.getTabWidget().getChildAt(5).getLayoutParams().height;
		
		mTabHost.getTabWidget().getChildAt(5).setLayoutParams(new LinearLayout
				.LayoutParams(60, height));
		try 
		{
			((TextView)((LinearLayout)mTabHost.getTabWidget().getChildAt(5)).getChildAt(1))
					.setTextSize(20);
			
		} catch (Exception e) 
		{
			try {
				((TextView)((RelativeLayout)mTabHost.getTabWidget().getChildAt(5)).getChildAt(1))
						.setTextSize(20);	
			} catch (Exception e2) {
				
			}
		}
		
		
		// Default to first tab
		this.onTabChanged("Daily");
		mTabHost.getTabWidget().getChildAt(4).setVisibility(View.GONE);
		mTabHost.getTabWidget().getChildAt(5).setPadding(0, 0, 0, 0);

		//
		mTabHost.setOnTabChangedListener(this);
	}
	
	private static void addTab(TabMenu activity, TabHost tabHost, TabHost.TabSpec tabSpec, TabInfo tabInfo)
	{
		tabSpec.setContent(activity.new TabFactory(activity));
		String tag = tabSpec.getTag();
		
		tabInfo.fragment = activity.getSupportFragmentManager().findFragmentByTag(tag);
		if(tabInfo.fragment != null && !tabInfo.fragment.isDetached())
		{
			FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
			ft.detach(tabInfo.fragment);
			ft.commit();
			activity.getSupportFragmentManager().executePendingTransactions();
		}
		
		tabHost.addTab(tabSpec);
	}

	public void onTabChanged(String tag) 
	{
		if(tag.equals("More"))
		{
			Intent myIntent = new Intent(TabMenu.this, TabMenuMore.class);
			startActivity(myIntent);
			finish();
		}
		else
		{
			TabInfo newTab = (TabInfo) this.mapTabInfo.get(tag);
			
		
			if(mLastTab != newTab)
			{
				FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
				ft.setCustomAnimations(R.anim.zoom_enter, R.anim.zoom_exit);
			
				if(mLastTab != null)
				{
					if(mLastTab.fragment != null)
					{
						ft.detach(mLastTab.fragment);
					}
				}
			
				if(newTab != null)
				{
					if(newTab.fragment == null)
					{
						newTab.fragment = Fragment.instantiate(this, newTab.clss.getName(), newTab.args);
						
						//newTab.fragment.setArguments(bundleGame1);
						
						ft.add(R.id.realtabcontent, newTab.fragment, newTab.tag);
					
					}
					else
					{
						ft.attach(newTab.fragment);
					}
				}
			
				mLastTab = newTab;
				ft.commit();
				this.getSupportFragmentManager().executePendingTransactions();	
			}
		}
	}
}
