package com.aid.lottery;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

public class Picks extends Fragment
{
	//fields
	String daily[];
	NumAdapter dailyAdapter;
	Drawable dailyDrawableButton;
	Drawable dailyDrawableTab;
	int dailyPickNum;

	String big4[];
	NumAdapter big4Adapter;
	Drawable big4DrawableButton;
	Drawable big4DrawableTab;
	private int big4PickNum;
	
	String cash5[];
	NumAdapter cash5Adapter;
	Drawable cash5DrawableButton;
	Drawable cash5DrawableTab;
	private int cash5PickNum;
	
	String power[];
	NumAdapter powerAdapter;
	Drawable powerDrawableButton;
	Drawable powerDrawableTab;
	private int powerPickNum;
	
	ListView numListView;
	
	public int helper = 0;

	public static final String PREFS = "LotteryAid.prefs";
	SharedPreferences p;
	SharedPreferences prefs;
	SharedPreferences.Editor pEditor;

	View menuView;
	
	LinearLayout theLayout;
	
	private Drawable drawablePicks;
	
	private OrganizeEverything o_Everything;
	

	//end of fields

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		theLayout = (LinearLayout)inflater.inflate(R.layout.picks, container, false);
		
		p = this.getActivity().getSharedPreferences(PREFS, 0);
		
		initStyles();
		
		theLayout.setBackgroundDrawable(drawablePicks);

		o_Everything = new OrganizeEverything(getActivity(), 1);
		Button dailyButton = (Button)theLayout.findViewById(R.id.picks_DailyButton);
		dailyButton.setOnClickListener(dailyClickListener);
		dailyButton.setBackgroundDrawable(dailyDrawableTab);
		dailyButton.setText(o_Everything.getGameName());
		
		o_Everything = new OrganizeEverything(getActivity(), 2);
		Button big4Button = (Button)theLayout.findViewById(R.id.picks_Big4Button);
		big4Button.setOnClickListener(big4ClickListener);
		big4Button.setBackgroundDrawable(big4DrawableTab);
		big4Button.setText(o_Everything.getGameName());
		
		o_Everything = new OrganizeEverything(getActivity(), 3);
		Button cash5Button = (Button)theLayout.findViewById(R.id.picks_Cash5Button);
		cash5Button.setOnClickListener(cash5ClickListener);
		cash5Button.setBackgroundDrawable(cash5DrawableTab);
		cash5Button.setText(o_Everything.getGameName());
		
		o_Everything = new OrganizeEverything(getActivity(), 4);
		Button powerButton = (Button)theLayout.findViewById(R.id.picks_PowerButton);
		powerButton.setOnClickListener(powerClickListener);
		powerButton.setBackgroundDrawable(powerDrawableTab);
		powerButton.setText(o_Everything.getGameName());

		return theLayout;

	}
	
	@Override
	public void onResume() 
	{
		super.onResume();
		
		initStyles();
		
		theLayout.setBackgroundDrawable(drawablePicks);

		Button dailyButton = (Button)theLayout.findViewById(R.id.picks_DailyButton);
		dailyButton.setBackgroundDrawable(dailyDrawableTab);
		
		Button big4Button = (Button)theLayout.findViewById(R.id.picks_Big4Button);
		big4Button.setBackgroundDrawable(big4DrawableTab);
		
		Button cash5Button = (Button)theLayout.findViewById(R.id.picks_Cash5Button);
		cash5Button.setBackgroundDrawable(cash5DrawableTab);
		
		Button powerButton = (Button)theLayout.findViewById(R.id.picks_PowerButton);
		powerButton.setBackgroundDrawable(powerDrawableTab);
		
		
		
	}

	private void initStyles() 
	{
		prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
		
		dailyPickNum = 20;
		big4PickNum = 20;
		cash5PickNum = 20;
		powerPickNum = 20;
		
		drawablePicks = getResources().obtainTypedArray(R.array.numberStyleBackPicks).getDrawable(Integer.parseInt(prefs.getString("picks_background", "5")));
		
		dailyDrawableButton = getResources().obtainTypedArray(R.array.numberStyleButton).getDrawable(Integer.parseInt(prefs.getString("daily_background", "2")));
		dailyDrawableTab = getResources().obtainTypedArray(R.array.numberStyleTab).getDrawable(Integer.parseInt(prefs.getString("daily_background", "2")));
		
		big4DrawableButton = getResources().obtainTypedArray(R.array.numberStyleButton).getDrawable(Integer.parseInt(prefs.getString("big4_background", "1")));
		big4DrawableTab = getResources().obtainTypedArray(R.array.numberStyleTab).getDrawable(Integer.parseInt(prefs.getString("big4_background", "1")));
		
		cash5DrawableButton = getResources().obtainTypedArray(R.array.numberStyleButton).getDrawable(Integer.parseInt(prefs.getString("cash5_background", "0")));
		cash5DrawableTab = getResources().obtainTypedArray(R.array.numberStyleTab).getDrawable(Integer.parseInt(prefs.getString("cash5_background", "0")));
		
		powerDrawableButton = getResources().obtainTypedArray(R.array.numberStyleButton).getDrawable(Integer.parseInt(prefs.getString("power_background", "6")));
		powerDrawableTab = getResources().obtainTypedArray(R.array.numberStyleTab).getDrawable(Integer.parseInt(prefs.getString("power_background", "6")));
		
	}

	public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo)
	{
		super.onCreateContextMenu(menu, view, menuInfo);

		menuView = view;
		MenuInflater inflate = this.getActivity().getMenuInflater();
		inflate.inflate(R.menu.number_menu, menu);
	}

	public boolean onContextItemSelected(MenuItem item)
	{
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

		p = this.getActivity().getSharedPreferences(PREFS, 0);
		pEditor = p.edit();

		switch (item.getItemId())
		{
			case R.id.number_menu_remove:
				{
					if (helper == 1)
					{
						pEditor.remove("daily_daily" + info.id);
						int num = (p.getInt("daily_num", 0));

						if (num == 1)
						{
							daily = null;
							pEditor.putInt("daily_num", (num - 1));
							pEditor.commit();
							setData("daily");

							numListView.setAdapter(null);
							
							Button dailyButton = (Button)getActivity().findViewById(R.id.picks_DailyButton);
							dailyButton.setBackgroundDrawable(dailyDrawableTab);
						}
						else
						{
							pEditor.putInt("daily_num", (num - 1));
							pEditor.commit();
							setData("daily");

							numListView.setAdapter(dailyAdapter);
							
						}
					}
					else if (helper == 2)
					{
						pEditor.remove("big4_big4" + info.id);
						int num = (p.getInt("big4_num", 0));

						if (num == 1)
						{
							big4 = null;
							pEditor.putInt("big4_num", (num - 1));
							pEditor.commit();
							setData("big4");

							numListView.setAdapter(null);
							
							Button big4Button = (Button)getActivity().findViewById(R.id.picks_Big4Button);
							big4Button.setBackgroundDrawable(big4DrawableTab);
						}
						else
						{
							pEditor.putInt("big4_num", (num - 1));
							pEditor.commit();
							setData("big4");

							numListView.setAdapter(big4Adapter);
						}
					}
					else if (helper == 3)
					{
						pEditor.remove("cash5_cash5" + info.id);
						int num = (p.getInt("cash5_num", 0));

						if (num == 1)
						{
							cash5 = null;
							pEditor.putInt("cash5_num", (num - 1));
							pEditor.commit();
							setData("cash5");

							numListView.setAdapter(null);
							
							Button cash5Button = (Button)getActivity().findViewById(R.id.picks_Cash5Button);
							cash5Button.setBackgroundDrawable(cash5DrawableTab);
						}
						else
						{
							pEditor.putInt("cash5_num", (num - 1));
							pEditor.commit();
							setData("cash5");

							numListView.setAdapter(cash5Adapter);
						}
					}
					else if (helper == 4)
					{
						pEditor.remove("power_power" + info.id);
						int num = (p.getInt("power_num", 0));

						if (num == 1)
						{
							power = null;
							pEditor.putInt("power_num", (num - 1));
							pEditor.commit();
							setData("power");

							numListView.setAdapter(null);

							Button powerButton = (Button)getActivity().findViewById(R.id.picks_PowerButton);
							powerButton.setBackgroundDrawable(powerDrawableTab);
						}
						else
						{
							pEditor.putInt("power_num", (num - 1));
							pEditor.commit();
							setData("power");

							numListView.setAdapter(powerAdapter);
						}
					}

					break;
				}
			case R.id.number_menu_remove_all:
				{
					if (helper == 1)
					{
						for (int i = 0; i < dailyPickNum; i++)
						{
							if (p.contains("daily_daily" + i + ""))
							{
								pEditor.remove("daily_daily" + i + "");
							}
						}
						pEditor.remove("daily_num");
						pEditor.commit();
						daily = null;
						numListView.setAdapter(null);
						
						Button dailyButton = (Button)getActivity().findViewById(R.id.picks_DailyButton);
						dailyButton.setBackgroundDrawable(dailyDrawableTab);
					}
					else if (helper == 2)
					{
						for (int i = 0; i < big4PickNum; i++)
						{
							if (p.contains("big4_big4" + i + ""))
							{
								pEditor.remove("big4_big4" + i + "");
							}
						}
						pEditor.remove("big4_num");
						pEditor.commit();
						big4 = null;
						numListView.setAdapter(null);
						
						Button big4Button = (Button)getActivity().findViewById(R.id.picks_Big4Button);
						big4Button.setBackgroundDrawable(big4DrawableTab);
					}
					else if (helper == 3)
					{
						for (int i = 0; i < cash5PickNum; i++)
						{
							if (p.contains("cash5_cash5" + i + ""))
							{
								pEditor.remove("cash5_cash5" + i + "");
							}
						}
						pEditor.remove("cash5_num");
						pEditor.commit();
						cash5 = null;
						numListView.setAdapter(null);
						
						Button cash5Button = (Button)getActivity().findViewById(R.id.picks_Cash5Button);
						cash5Button.setBackgroundDrawable(cash5DrawableTab);
					}
					else if (helper == 4)
					{
						for (int i = 0; i < powerPickNum; i++)
						{
							if (p.contains("power_power" + i + ""))
							{
								pEditor.remove("power_power" + i + "");
							}
						}
						pEditor.remove("power_num");
						pEditor.commit();
						power = null;
						numListView.setAdapter(null);

						Button powerButton = (Button)getActivity().findViewById(R.id.picks_PowerButton);
						powerButton.setBackgroundDrawable(powerDrawableTab);
					}
					break;
				}
		}
		
		if(daily == null || big4 == null || cash5 == null || power == null)
		{
			Button dailyButton = (Button)getActivity().findViewById(R.id.picks_DailyButton);
			dailyButton.setBackgroundDrawable(dailyDrawableTab);
			
			Button big4Button = (Button)getActivity().findViewById(R.id.picks_Big4Button);
			big4Button.setBackgroundDrawable(big4DrawableTab);
			
			Button cash5Button = (Button)getActivity().findViewById(R.id.picks_Cash5Button);
			cash5Button.setBackgroundDrawable(cash5DrawableTab);
			
			Button powerButton = (Button)getActivity().findViewById(R.id.picks_PowerButton);
			powerButton.setBackgroundDrawable(powerDrawableTab);
		}
		
		return false;
	}

	private View.OnClickListener dailyClickListener = new View.OnClickListener()
	{
		public void onClick(View p1)
		{
			numListView = (ListView)theLayout.findViewById(R.id.picks_number_ListView);
			setData("daily");
			if(daily != null)
			{
				numListView.setAdapter(dailyAdapter);
				numListView.setTextFilterEnabled(true);
				registerForContextMenu(numListView);

				Button dailyButton = (Button)getActivity().findViewById(R.id.picks_DailyButton);
				dailyButton.setBackgroundDrawable(dailyDrawableButton);
				
				Button big4Button = (Button)getActivity().findViewById(R.id.picks_Big4Button);
				Button cash5Button = (Button)getActivity().findViewById(R.id.picks_Cash5Button);
				Button powerButton = (Button)getActivity().findViewById(R.id.picks_PowerButton);
				if(helper == 2)
				{
					big4Button.setBackgroundDrawable(big4DrawableTab);
				}
				else if (helper == 3) 
				{
					cash5Button.setBackgroundDrawable(cash5DrawableTab);
				}
				else if(helper == 4)
				{
					powerButton.setBackgroundDrawable(powerDrawableTab);
				}
				
				helper = 1;
			}
		}
	};

	private View.OnClickListener big4ClickListener = new View.OnClickListener()
	{
		public void onClick(View p1)
		{
			numListView = (ListView)theLayout.findViewById(R.id.picks_number_ListView);
			setData("big4");
			if(big4 != null)
			{
				numListView.setAdapter(big4Adapter);
				numListView.setTextFilterEnabled(true);
				registerForContextMenu(numListView);

				Button big4Button = (Button)getActivity().findViewById(R.id.picks_Big4Button);
				big4Button.setBackgroundDrawable(big4DrawableButton);
				
				Button dailyButton = (Button)getActivity().findViewById(R.id.picks_DailyButton);
				Button cash5Button = (Button)getActivity().findViewById(R.id.picks_Cash5Button);
				Button powerButton = (Button)getActivity().findViewById(R.id.picks_PowerButton);
				if(helper == 1)
				{
					dailyButton.setBackgroundDrawable(dailyDrawableTab);
				}
				else if (helper == 3) 
				{
					cash5Button.setBackgroundDrawable(cash5DrawableTab);
				}
				else if(helper == 4)
				{
					powerButton.setBackgroundDrawable(powerDrawableTab);
				}
				
				helper = 2;
			}
		}
	};
	
	private View.OnClickListener cash5ClickListener = new View.OnClickListener()
	{
		public void onClick(View p1)
		{
			numListView = (ListView)theLayout.findViewById(R.id.picks_number_ListView);
			setData("cash5");
			if(cash5 != null)
			{
				numListView.setAdapter(cash5Adapter);
				numListView.setTextFilterEnabled(true);
				registerForContextMenu(numListView);
				
				
				Button cash5Button = (Button)getActivity().findViewById(R.id.picks_Cash5Button);
				cash5Button.setBackgroundDrawable(cash5DrawableButton);

				
				Button dailyButton = (Button)getActivity().findViewById(R.id.picks_DailyButton);
				Button big4Button = (Button)getActivity().findViewById(R.id.picks_Big4Button);
				Button powerButton = (Button)getActivity().findViewById(R.id.picks_PowerButton);
				if(helper == 1)
				{
					dailyButton.setBackgroundDrawable(dailyDrawableTab);
				}
				else if(helper == 2)
				{
					big4Button.setBackgroundDrawable(big4DrawableTab);
				}
				else if(helper == 4)
				{
					powerButton.setBackgroundDrawable(powerDrawableTab);
				}
				
				helper = 3;
			}
		}
	};

	private View.OnClickListener powerClickListener = new View.OnClickListener()
	{
		public void onClick(View p1)
		{
			numListView = (ListView)theLayout.findViewById(R.id.picks_number_ListView);
			setData("power");
			if(power != null)
			{
				numListView.setAdapter(powerAdapter);
				numListView.setTextFilterEnabled(true);
				registerForContextMenu(numListView);
				
				

				Button powerButton = (Button)getActivity().findViewById(R.id.picks_PowerButton);
				powerButton.setBackgroundDrawable(powerDrawableButton);

				Button cash5Button = (Button)getActivity().findViewById(R.id.picks_Cash5Button);
				Button dailyButton = (Button)getActivity().findViewById(R.id.picks_DailyButton);
				Button big4Button = (Button)getActivity().findViewById(R.id.picks_Big4Button);
				if(helper == 1)
				{
					dailyButton.setBackgroundDrawable(dailyDrawableTab);
				}
				else if(helper == 2)
				{
					big4Button.setBackgroundDrawable(big4DrawableTab);
				}
				else if(helper == 3)
				{
					cash5Button.setBackgroundDrawable(cash5DrawableTab);
				}

				helper = 4;
			}
		}
	};
	
	public void setData(String dataSet)
	{
		p = this.getActivity().getSharedPreferences(PREFS, 0);
		pEditor = p.edit();

		if (dataSet.equals("daily"))
		{
			int num = p.getInt("daily_num", 0);
			int counter = 0;
			
			if(num > dailyPickNum){num = dailyPickNum;}

			if (num > 0)
			{
				daily = new String[num];
			}
			else
			{daily = null;}

			for (int i = 0; i < dailyPickNum; i++)
			{
				if (counter >= num)
				{continue;}
				if (p.contains("daily_daily" + i + "") == true && num > 0)
				{
					daily[counter] = p.getString("daily_daily" + i + "", "0");
					pEditor.putString("daily_daily" + counter, daily[counter]);

					counter++;
				}
			}

			for (int i = num; i < dailyPickNum; i++)
			{
				pEditor.remove("daily_daily" + i);
				pEditor.commit();
			}

			if (daily != null)
			{
				dailyAdapter = new NumAdapter(getActivity(), daily, 8, 1);
			}
		}//end of if
		
		else if (dataSet.equals("big4"))
		{
			int num = p.getInt("big4_num", 0);
			int counter = 0;

			if (num > 0)
			{
				big4 = new String[num];
			}
			else
			{big4 = null;}

			for (int i = 0; i < big4PickNum; i++)
			{
				if (counter >= num)
				{continue;}
				if (p.contains("big4_big4" + i + "") == true && num > 0)
				{
					big4[counter] = p.getString("big4_big4" + i + "", "0");
					pEditor.putString("big4_big4" + counter, big4[counter]);

					counter++;
				}
			}

			for (int i = num; i < big4PickNum; i++)
			{
				pEditor.remove("big4_big4" + i);
				pEditor.commit();
			}

			if (big4 != null)
			{
				big4Adapter = new NumAdapter(getActivity(), big4, 9, 2);
			}
		}//end of if
		
		else if (dataSet.equals("cash5"))
		{
			int num = p.getInt("cash5_num", 0);
			int counter = 0;

			if (num > 0)
			{
				cash5 = new String[num];
			}
			else
			{cash5 = null;}

			for (int i = 0; i < cash5PickNum; i++)
			{
				if (counter >= num)
				{continue;}
				if (p.contains("cash5_cash5" + i + "") == true && num > 0)
				{
					cash5[counter] = p.getString("cash5_cash5" + i + "", "0");
					pEditor.putString("cash5_cash5" + counter, cash5[counter]);

					counter++;
				}
			}

			for (int i = num; i < cash5PickNum; i++)
			{
				pEditor.remove("cash5_cash5" + i);
				pEditor.commit();
			}

			if (cash5 != null)
			{
				cash5Adapter = new NumAdapter(getActivity(), cash5, 10, 3);
			}
		}//end of if
		
		else if (dataSet.equals("power"))
		{
			int num = p.getInt("power_num", 0);
			int counter = 0;

			if (num > 0)
			{
				power = new String[num];
			}
			else
			{power = null;}

			for (int i = 0; i < powerPickNum; i++)
			{
				if (counter >= num)
				{continue;}
				if (p.contains("power_power" + i + "") == true && num > 0)
				{
					power[counter] = p.getString("power_power" + i + "", "0");
					pEditor.putString("power_power" + counter, power[counter]);

					counter++;
				}
			}

			for (int i = num; i < powerPickNum; i++)
			{
				pEditor.remove("power_power" + i);
				pEditor.commit();
			}

			if (power != null)
			{
				powerAdapter = new NumAdapter(getActivity(), power, 11, 4);
			}
		}//end of if
	}
}
