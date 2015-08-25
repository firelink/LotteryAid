package com.aid.lottery;

import java.util.HashMap;

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

public class TabMenuMore extends FragmentActivity implements TabHost.OnTabChangeListener 
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
	Bundle bundleGame5 = new Bundle();
	Bundle bundleGame6 = new Bundle();
	Bundle bundleGame7 = new Bundle();
	Bundle bundleGame8 = new Bundle();

	public class TabInfo {
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
		
		b = savedInstanceState;
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.tab_main);
		prefs = PreferenceManager.getDefaultSharedPreferences(TabMenuMore.this);

		initTabHost(savedInstanceState);
		if (savedInstanceState != null) 
		{
			mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
		}
				
		initStyles();
		
		try
		{
			((TextView)((LinearLayout)mTabHost.getTabWidget().getChildAt(1)).getChildAt(1)).setTextSize(10);
			((TextView)((LinearLayout)mTabHost.getTabWidget().getChildAt(2)).getChildAt(1)).setTextSize(10);
			((TextView)((LinearLayout)mTabHost.getTabWidget().getChildAt(3)).getChildAt(1)).setTextSize(10);
			((TextView)((LinearLayout)mTabHost.getTabWidget().getChildAt(4)).getChildAt(1)).setTextSize(10);
			((TextView)((LinearLayout)mTabHost.getTabWidget().getChildAt(5)).getChildAt(1)).setTextSize(10);
			
			((TextView)((LinearLayout)mTabHost.getTabWidget().getChildAt(1)).getChildAt(1)).setGravity(Gravity.CENTER);
			((TextView)((LinearLayout)mTabHost.getTabWidget().getChildAt(2)).getChildAt(1)).setGravity(Gravity.CENTER);
			((TextView)((LinearLayout)mTabHost.getTabWidget().getChildAt(3)).getChildAt(1)).setGravity(Gravity.CENTER);
			((TextView)((LinearLayout)mTabHost.getTabWidget().getChildAt(4)).getChildAt(1)).setGravity(Gravity.CENTER);
		}catch(Exception e)
		{
			((TextView)((RelativeLayout)mTabHost.getTabWidget().getChildAt(1)).getChildAt(1)).setTextSize(10);
			((TextView)((RelativeLayout)mTabHost.getTabWidget().getChildAt(2)).getChildAt(1)).setTextSize(10);
			((TextView)((RelativeLayout)mTabHost.getTabWidget().getChildAt(3)).getChildAt(1)).setTextSize(10);
			((TextView)((RelativeLayout)mTabHost.getTabWidget().getChildAt(4)).getChildAt(1)).setTextSize(10);
			((TextView)((RelativeLayout)mTabHost.getTabWidget().getChildAt(5)).getChildAt(1)).setTextSize(10);
			
			((TextView)((RelativeLayout)mTabHost.getTabWidget().getChildAt(1)).getChildAt(1)).setGravity(Gravity.CENTER);
			((TextView)((RelativeLayout)mTabHost.getTabWidget().getChildAt(2)).getChildAt(1)).setGravity(Gravity.CENTER);
			((TextView)((RelativeLayout)mTabHost.getTabWidget().getChildAt(3)).getChildAt(1)).setGravity(Gravity.CENTER);
			((TextView)((RelativeLayout)mTabHost.getTabWidget().getChildAt(4)).getChildAt(1)).setGravity(Gravity.CENTER);
		}

		mTabHost.getTabWidget().getChildAt(1).setBackgroundDrawable(dailyDraw);
		mTabHost.getTabWidget().getChildAt(2).setBackgroundDrawable(big4Draw);
		mTabHost.getTabWidget().getChildAt(3).setBackgroundDrawable(cash5Draw);
		mTabHost.getTabWidget().getChildAt(4).setBackgroundDrawable(powerDraw);
		mTabHost.getTabWidget().getChildAt(5).setBackgroundDrawable(picksDraw);
		
	}
		
	public void initOnCreate(Bundle savedInstanceState)
	{
		
		
	}

	public void onResumeFragments()
	{
		super.onResumeFragments();
		if(prefs.contains("state_chosen"))
		{
			o_Everything = new OrganizeEverything(this, 1);
			
			if(o_Everything.getNumberOfGames() < 5)
			{
				this.finish();
			}
			else
			{
				
				initStyles();
				this.onTabChanged("Game5");
				mTabHost.setCurrentTab(1);
				
				mTabHost.getTabWidget().getChildAt(1).setBackgroundDrawable(dailyDraw);
				mTabHost.getTabWidget().getChildAt(2).setBackgroundDrawable(big4Draw);
				mTabHost.getTabWidget().getChildAt(3).setBackgroundDrawable(cash5Draw);
				mTabHost.getTabWidget().getChildAt(4).setBackgroundDrawable(powerDraw);
				mTabHost.getTabWidget().getChildAt(5).setBackgroundDrawable(picksDraw);
				
				o_Everything = new OrganizeEverything(this, 5);
				try {
					
					((TextView)((LinearLayout)mTabHost.getTabWidget().getChildAt(1)).getChildAt(1))
							.setText(o_Everything.getGameName());
					((TextView)((LinearLayout)mTabHost.getTabWidget().getChildAt(1)).getChildAt(1))
							.setTextColor(Color.WHITE);
					
				} catch (Exception e) 
				{
					((TextView)((RelativeLayout)mTabHost.getTabWidget().getChildAt(1)).getChildAt(1))
							.setText(o_Everything.getGameName());
					((TextView)((RelativeLayout)mTabHost.getTabWidget().getChildAt(1)).getChildAt(1))
							.setTextColor(Color.WHITE);
				}
				
				
				if(o_Everything.getNumberOfGames() > 5)
				{
					o_Everything = new OrganizeEverything(this, 6);
					try {
						
						((TextView)((LinearLayout)mTabHost.getTabWidget().getChildAt(2)).getChildAt(1))
								.setText(o_Everything.getGameName());
						((TextView)((LinearLayout)mTabHost.getTabWidget().getChildAt(2)).getChildAt(1))
								.setTextColor(Color.WHITE);
						
					} catch (Exception e) 
					{
						((TextView)((RelativeLayout)mTabHost.getTabWidget().getChildAt(2)).getChildAt(1))
								.setText(o_Everything.getGameName());
						((TextView)((RelativeLayout)mTabHost.getTabWidget().getChildAt(2)).getChildAt(1))
								.setTextColor(Color.WHITE);
					}
					
					mTabHost.getTabWidget().getChildAt(2).setVisibility(View.VISIBLE);

				}
				else
				{
					mTabHost.getTabWidget().getChildAt(2).setVisibility(View.GONE);
				}
				
				if(o_Everything.getNumberOfGames() > 6)
				{
					o_Everything = new OrganizeEverything(this, 7);
					try {
						
						((TextView)((LinearLayout)mTabHost.getTabWidget().getChildAt(3)).getChildAt(1))
								.setText(o_Everything.getGameName());
						((TextView)((LinearLayout)mTabHost.getTabWidget().getChildAt(3)).getChildAt(1))
								.setTextColor(Color.WHITE);
						
					} catch (Exception e) 
					{
						((TextView)((RelativeLayout)mTabHost.getTabWidget().getChildAt(3)).getChildAt(1))
								.setText(o_Everything.getGameName());
						((TextView)((RelativeLayout)mTabHost.getTabWidget().getChildAt(3)).getChildAt(1))
								.setTextColor(Color.WHITE);
					}
					
					mTabHost.getTabWidget().getChildAt(3).setVisibility(View.VISIBLE);

				}
				else
				{
					mTabHost.getTabWidget().getChildAt(3).setVisibility(View.GONE);
				}
				
				if(o_Everything.getNumberOfGames() > 7)
				{
					o_Everything = new OrganizeEverything(this, 8);
					try {
						
						((TextView)((LinearLayout)mTabHost.getTabWidget().getChildAt(4)).getChildAt(1))
								.setText(o_Everything.getGameName());
						((TextView)((LinearLayout)mTabHost.getTabWidget().getChildAt(4)).getChildAt(1))
								.setTextColor(Color.WHITE);
						
					} catch (Exception e) 
					{
						((TextView)((RelativeLayout)mTabHost.getTabWidget().getChildAt(4)).getChildAt(1))
								.setText(o_Everything.getGameName());
						((TextView)((RelativeLayout)mTabHost.getTabWidget().getChildAt(4)).getChildAt(1))
								.setTextColor(Color.WHITE);
					}
					
					mTabHost.getTabWidget().getChildAt(4).setVisibility(View.VISIBLE);

				}
				else
				{
					mTabHost.getTabWidget().getChildAt(4).setVisibility(View.GONE);
				}
			}
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
				mTabHost.setCurrentTab(5);
				break;
			}
			case R.id.main_menu_settings:
				{
					Intent myIntent = new Intent(TabMenuMore.this, PrefActivity.class);
					startActivity(myIntent);
					break;
				}
			case R.id.main_menu_help:
				{
//					Intent intent = new Intent(TabMenu.this, TabMenu.class);
//					startActivity(intent);
//					break;
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
		mTabHost.getTabWidget().getChildAt(1).setBackgroundDrawable(dailyDraw);
		mTabHost.getTabWidget().getChildAt(2).setBackgroundDrawable(big4Draw);
		mTabHost.getTabWidget().getChildAt(3).setBackgroundDrawable(cash5Draw);
		mTabHost.getTabWidget().getChildAt(4).setBackgroundDrawable(powerDraw);
		mTabHost.getTabWidget().getChildAt(5).setBackgroundDrawable(picksDraw);
		
	}
	
	private void initStyles()
	{
		dailyDraw = getResources().obtainTypedArray(R.array.numberStyleTab).getDrawable(Integer.parseInt(prefs.getString("game5_background", "4")));
		big4Draw = getResources().obtainTypedArray(R.array.numberStyleTab).getDrawable(Integer.parseInt(prefs.getString("game6_background", "7")));
		cash5Draw = getResources().obtainTypedArray(R.array.numberStyleTab).getDrawable(Integer.parseInt(prefs.getString("game7_background", "8")));
		powerDraw = getResources().obtainTypedArray(R.array.numberStyleTab).getDrawable(Integer.parseInt(prefs.getString("game8_background", "9")));
		picksDraw = getResources().obtainTypedArray(R.array.numberStyleTab).getDrawable(Integer.parseInt(prefs.getString("picks_background", "5")));
	}
	
	private void initBundles()
	{
		bundleGame5.putString("string1", "game5_number");
		bundleGame5.putString("string2", "game5_game5");
		bundleGame5.putString("stringNum", "game5_num");
		bundleGame5.putString("stringBack", "game5_background");
		bundleGame5.putInt("gameNum", 5);
		bundleGame5.putString("defString", "4");
		
		bundleGame6.putString("string1", "game6_number");
		bundleGame6.putString("string2", "game6_game6");
		bundleGame6.putString("stringNum", "game6_num");
		bundleGame6.putString("stringBack", "game6_background");
		bundleGame6.putInt("gameNum", 6);
		bundleGame6.putString("defString", "7");
		
		bundleGame7.putString("string1", "game7_number");
		bundleGame7.putString("string2", "game7_game7");
		bundleGame7.putString("stringNum", "game7_num");
		bundleGame7.putString("stringBack", "game7_background");
		bundleGame7.putInt("gameNum", 7);
		bundleGame7.putString("defString", "8");
		
		bundleGame8.putString("string1", "game8_number");
		bundleGame8.putString("string2", "game8_game8");
		bundleGame8.putString("stringNum", "game8_num");
		bundleGame8.putString("stringBack", "game8_background");
		bundleGame8.putInt("gameNum", 8);
		bundleGame8.putString("defString", "9");
	}

	private void initTabHost(Bundle args) 
	{
		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup();
		TabInfo tabInfo = null;
		
		initBundles();
		
		TabMenuMore.addTab(this, this.mTabHost, this.mTabHost
				.newTabSpec("Back").setIndicator("<-"),
				(tabInfo = new TabInfo("Back", TabMenu.class, args)));
		this.mapTabInfo.put(tabInfo.tag, tabInfo);
		
		o_Everything = new OrganizeEverything(this, 5);
		TabMenuMore.addTab(this, this.mTabHost, this.mTabHost
				.newTabSpec("Game5").setIndicator(o_Everything.getGameName()),
				(tabInfo = new TabInfo("Game5", InitGames.class, bundleGame5)));
		this.mapTabInfo.put(tabInfo.tag, tabInfo);
		
		TabMenuMore.addTab(this, this.mTabHost, this.mTabHost
				.newTabSpec("Game6").setIndicator(o_Everything.getGameName()),
				(tabInfo = new TabInfo("Game6", InitGames.class, bundleGame6)));
		this.mapTabInfo.put(tabInfo.tag, tabInfo);
		
		TabMenuMore.addTab(this, this.mTabHost, this.mTabHost
					   .newTabSpec("Game7").setIndicator(o_Everything.getGameName()),
					   (tabInfo = new TabInfo("Game7", InitGames.class, bundleGame7)));
		this.mapTabInfo.put(tabInfo.tag, tabInfo);
		
		TabMenuMore.addTab(this, this.mTabHost, this.mTabHost
					   .newTabSpec("Game8").setIndicator(o_Everything.getGameName()),
					   (tabInfo = new TabInfo("Game8", InitGames.class, bundleGame8)));
		this.mapTabInfo.put(tabInfo.tag, tabInfo);
		
		TabMenuMore.addTab(this, this.mTabHost, this.mTabHost
				.newTabSpec("Picks").setIndicator("Picks"),
				(tabInfo = new TabInfo("Picks", Picks2.class, args)));
		this.mapTabInfo.put(tabInfo.tag, tabInfo);
		
		int height = mTabHost.getTabWidget().getChildAt(0).getLayoutParams().height;
		
		mTabHost.getTabWidget().getChildAt(0).setLayoutParams(new LinearLayout
				.LayoutParams(60, height));
		try
		{
			((TextView)((LinearLayout)mTabHost.getTabWidget().getChildAt(0)).getChildAt(1))
					.setTextSize(20);
		}catch(Exception e)
		{
			((TextView)((RelativeLayout)mTabHost.getTabWidget().getChildAt(0)).getChildAt(1))
			.setTextSize(20);
		}
		
		// Default to first tab
		this.onTabChanged("Game5");
		mTabHost.getTabWidget().getChildAt(5).setVisibility(View.GONE);
		mTabHost.getTabWidget().getChildAt(0).setPadding(0, 0, 0, 0);

		//
		mTabHost.setOnTabChangedListener(this);
	}
	
	private static void addTab(TabMenuMore activity, TabHost tabHost, TabHost.TabSpec tabSpec, TabInfo tabInfo)
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
		if(tag.equals("Back"))
		{
			Intent myIntent = new Intent(TabMenuMore.this, TabMenu.class);
			startActivity(myIntent);
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
