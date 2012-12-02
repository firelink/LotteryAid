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

public class Picks2 extends Fragment
{
	//fields
	String game5[];
	NumAdapter game5Adapter;
	Drawable game5DrawableButton;
	Drawable game5DrawableTab;
	int game5PickNum;

	String game6[];
	NumAdapter game6Adapter;
	Drawable game6DrawableButton;
	Drawable game6DrawableTab;
	private int game6PickNum;
	
	String game7[];
	NumAdapter game7Adapter;
	Drawable game7DrawableButton;
	Drawable game7DrawableTab;
	private int game7PickNum;
	
	String game8[];
	NumAdapter game8Adapter;
	Drawable game8DrawableButton;
	Drawable game8DrawableTab;
	private int game8PickNum;
	
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

		o_Everything = new OrganizeEverything(getActivity(), 5);
		Button game5Button = (Button)theLayout.findViewById(R.id.picks_DailyButton);
		game5Button.setOnClickListener(game5ClickListener);
		game5Button.setBackgroundDrawable(game5DrawableTab);
		game5Button.setText(o_Everything.getGameName());
		
		if(o_Everything.getNumberOfGames() > 5)
			o_Everything = new OrganizeEverything(getActivity(), 6);
		Button game6Button = (Button)theLayout.findViewById(R.id.picks_Big4Button);
		game6Button.setOnClickListener(game6ClickListener);
		game6Button.setBackgroundDrawable(game6DrawableTab);
		game6Button.setText(o_Everything.getGameName());
		if(o_Everything.getNumberOfGames() < 6)
			game6Button.setVisibility(View.GONE);
		
		if(o_Everything.getNumberOfGames() > 6)
			o_Everything = new OrganizeEverything(getActivity(), 7);
		Button game7Button = (Button)theLayout.findViewById(R.id.picks_Cash5Button);
		game7Button.setOnClickListener(game7ClickListener);
		game7Button.setBackgroundDrawable(game7DrawableTab);
		game7Button.setText(o_Everything.getGameName());
		if(o_Everything.getNumberOfGames() < 7)
			game7Button.setVisibility(View.GONE);
		
		if(o_Everything.getNumberOfGames() > 7)
			o_Everything = new OrganizeEverything(getActivity(), 8);
		Button game8Button = (Button)theLayout.findViewById(R.id.picks_PowerButton);
		game8Button.setOnClickListener(game8ClickListener);
		game8Button.setBackgroundDrawable(game8DrawableTab);
		game8Button.setText(o_Everything.getGameName());
		if(o_Everything.getNumberOfGames() < 8)
			game8Button.setVisibility(View.GONE);
		
		return theLayout;

	}
	
	@Override
	public void onResume() 
	{
		super.onResume();
		
		initStyles();
		
		theLayout.setBackgroundDrawable(drawablePicks);

		Button game5Button = (Button)theLayout.findViewById(R.id.picks_DailyButton);
		game5Button.setBackgroundDrawable(game5DrawableTab);
		
		Button game6Button = (Button)theLayout.findViewById(R.id.picks_Big4Button);
		game6Button.setBackgroundDrawable(game6DrawableTab);
		
		Button game7Button = (Button)theLayout.findViewById(R.id.picks_Cash5Button);
		game7Button.setBackgroundDrawable(game7DrawableTab);
		
		Button game8Button = (Button)theLayout.findViewById(R.id.picks_PowerButton);
		game8Button.setBackgroundDrawable(game8DrawableTab);
		
	}

	private void initStyles() 
	{
		prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
		
		game5PickNum = 20;
		game6PickNum = 20;
		game7PickNum = 20;
		game8PickNum = 20;
		
		drawablePicks = getResources().obtainTypedArray(R.array.numberStyleBackPicks).getDrawable(Integer.parseInt(prefs.getString("picks_background", "5")));
		
		game5DrawableButton = getResources().obtainTypedArray(R.array.numberStyleButton).getDrawable(Integer.parseInt(prefs.getString("game5_background", "4")));
		game5DrawableTab = getResources().obtainTypedArray(R.array.numberStyleTab).getDrawable(Integer.parseInt(prefs.getString("game5_background", "4")));
		
		game6DrawableButton = getResources().obtainTypedArray(R.array.numberStyleButton).getDrawable(Integer.parseInt(prefs.getString("game6_background", "7")));
		game6DrawableTab = getResources().obtainTypedArray(R.array.numberStyleTab).getDrawable(Integer.parseInt(prefs.getString("game6_background", "7")));
		
		game7DrawableButton = getResources().obtainTypedArray(R.array.numberStyleButton).getDrawable(Integer.parseInt(prefs.getString("game7_background", "8")));
		game7DrawableTab = getResources().obtainTypedArray(R.array.numberStyleTab).getDrawable(Integer.parseInt(prefs.getString("game7_background", "8")));
		
		game8DrawableButton = getResources().obtainTypedArray(R.array.numberStyleButton).getDrawable(Integer.parseInt(prefs.getString("game8_background", "9")));
		game8DrawableTab = getResources().obtainTypedArray(R.array.numberStyleTab).getDrawable(Integer.parseInt(prefs.getString("game8_background", "9")));
		
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
						pEditor.remove("game5_game5" + info.id);
						int num = (p.getInt("game5_num", 0));

						if (num == 1)
						{
							game5 = null;
							pEditor.putInt("game5_num", (num - 1));
							pEditor.commit();
							setData("game5");

							numListView.setAdapter(null);
							
							Button game5Button = (Button)getActivity().findViewById(R.id.picks_DailyButton);
							game5Button.setBackgroundDrawable(game5DrawableTab);
						}
						else
						{
							pEditor.putInt("game5_num", (num - 1));
							pEditor.commit();
							setData("game5");

							numListView.setAdapter(game5Adapter);
							
						}
					}
					else if (helper == 2)
					{
						pEditor.remove("game6_game6" + info.id);
						int num = (p.getInt("game6_num", 0));

						if (num == 1)
						{
							game6 = null;
							pEditor.putInt("game6_num", (num - 1));
							pEditor.commit();
							setData("game6");

							numListView.setAdapter(null);
							
							Button game6Button = (Button)getActivity().findViewById(R.id.picks_Big4Button);
							game6Button.setBackgroundDrawable(game6DrawableTab);
						}
						else
						{
							pEditor.putInt("game6_num", (num - 1));
							pEditor.commit();
							setData("game6");

							numListView.setAdapter(game6Adapter);
						}
					}
					else if (helper == 3)
					{
						pEditor.remove("game7_game7" + info.id);
						int num = (p.getInt("game7_num", 0));

						if (num == 1)
						{
							game7 = null;
							pEditor.putInt("game7_num", (num - 1));
							pEditor.commit();
							setData("game7");

							numListView.setAdapter(null);
							
							Button game7Button = (Button)getActivity().findViewById(R.id.picks_Cash5Button);
							game7Button.setBackgroundDrawable(game7DrawableTab);
						}
						else
						{
							pEditor.putInt("game7_num", (num - 1));
							pEditor.commit();
							setData("game7");

							numListView.setAdapter(game7Adapter);
						}
					}
					else if (helper == 4)
					{
						pEditor.remove("game8_game8" + info.id);
						int num = (p.getInt("game8_num", 0));

						if (num == 1)
						{
							game8 = null;
							pEditor.putInt("game8_num", (num - 1));
							pEditor.commit();
							setData("game8");

							numListView.setAdapter(null);

							Button game8Button = (Button)getActivity().findViewById(R.id.picks_PowerButton);
							game8Button.setBackgroundDrawable(game8DrawableTab);
						}
						else
						{
							pEditor.putInt("game8_num", (num - 1));
							pEditor.commit();
							setData("game8");

							numListView.setAdapter(game8Adapter);
						}
					}

					break;
				}
			case R.id.number_menu_remove_all:
				{
					if (helper == 1)
					{
						for (int i = 0; i < game5PickNum; i++)
						{
							if (p.contains("game5_game5" + i + ""))
							{
								pEditor.remove("game5_game5" + i + "");
							}
						}
						pEditor.remove("game5_num");
						pEditor.commit();
						game5 = null;
						numListView.setAdapter(null);
						
						Button game5Button = (Button)getActivity().findViewById(R.id.picks_DailyButton);
						game5Button.setBackgroundDrawable(game5DrawableTab);
					}
					else if (helper == 2)
					{
						for (int i = 0; i < game6PickNum; i++)
						{
							if (p.contains("game6_game6" + i + ""))
							{
								pEditor.remove("game6_game6" + i + "");
							}
						}
						pEditor.remove("game6_num");
						pEditor.commit();
						game6 = null;
						numListView.setAdapter(null);
						
						Button game6Button = (Button)getActivity().findViewById(R.id.picks_Big4Button);
						game6Button.setBackgroundDrawable(game6DrawableTab);
					}
					else if (helper == 3)
					{
						for (int i = 0; i < game7PickNum; i++)
						{
							if (p.contains("game7_game7" + i + ""))
							{
								pEditor.remove("game7_game7" + i + "");
							}
						}
						pEditor.remove("game7_num");
						pEditor.commit();
						game7 = null;
						numListView.setAdapter(null);
						
						Button game7Button = (Button)getActivity().findViewById(R.id.picks_Cash5Button);
						game7Button.setBackgroundDrawable(game7DrawableTab);
					}
					else if (helper == 4)
					{
						for (int i = 0; i < game8PickNum; i++)
						{
							if (p.contains("game8_game8" + i + ""))
							{
								pEditor.remove("game8_game8" + i + "");
							}
						}
						pEditor.remove("game8_num");
						pEditor.commit();
						game8 = null;
						numListView.setAdapter(null);

						Button game8Button = (Button)getActivity().findViewById(R.id.picks_PowerButton);
						game8Button.setBackgroundDrawable(game8DrawableTab);
					}
					break;
				}
		}
		
		if(game5 == null || game6 == null || game7 == null || game8 == null)
		{
			Button game5Button = (Button)getActivity().findViewById(R.id.picks_DailyButton);
			game5Button.setBackgroundDrawable(game5DrawableTab);
			
			Button game6Button = (Button)getActivity().findViewById(R.id.picks_Big4Button);
			game6Button.setBackgroundDrawable(game6DrawableTab);
			
			Button game7Button = (Button)getActivity().findViewById(R.id.picks_Cash5Button);
			game7Button.setBackgroundDrawable(game7DrawableTab);
			
			Button game8Button = (Button)getActivity().findViewById(R.id.picks_PowerButton);
			game8Button.setBackgroundDrawable(game8DrawableTab);
		}
		
		return false;
	}

	private View.OnClickListener game5ClickListener = new View.OnClickListener()
	{
		public void onClick(View p1)
		{
			numListView = (ListView)theLayout.findViewById(R.id.picks_number_ListView);
			setData("game5");
			if(game5 != null)
			{
				numListView.setAdapter(game5Adapter);
				numListView.setTextFilterEnabled(true);
				registerForContextMenu(numListView);

				Button game5Button = (Button)getActivity().findViewById(R.id.picks_DailyButton);
				game5Button.setBackgroundDrawable(game5DrawableButton);
				
				Button game6Button = (Button)getActivity().findViewById(R.id.picks_Big4Button);
				Button game7Button = (Button)getActivity().findViewById(R.id.picks_Cash5Button);
				Button game8Button = (Button)getActivity().findViewById(R.id.picks_PowerButton);
				if(helper == 2)
				{
					game6Button.setBackgroundDrawable(game6DrawableTab);
				}
				else if (helper == 3) 
				{
					game7Button.setBackgroundDrawable(game7DrawableTab);
				}
				else if(helper == 4)
				{
					game8Button.setBackgroundDrawable(game8DrawableTab);
				}
				
				helper = 1;
			}
		}
	};

	private View.OnClickListener game6ClickListener = new View.OnClickListener()
	{
		public void onClick(View p1)
		{
			numListView = (ListView)theLayout.findViewById(R.id.picks_number_ListView);
			setData("game6");
			if(game6 != null)
			{
				numListView.setAdapter(game6Adapter);
				numListView.setTextFilterEnabled(true);
				registerForContextMenu(numListView);

				Button game6Button = (Button)getActivity().findViewById(R.id.picks_Big4Button);
				game6Button.setBackgroundDrawable(game6DrawableButton);
				
				Button game5Button = (Button)getActivity().findViewById(R.id.picks_DailyButton);
				Button game7Button = (Button)getActivity().findViewById(R.id.picks_Cash5Button);
				Button game8Button = (Button)getActivity().findViewById(R.id.picks_PowerButton);
				if(helper == 1)
				{
					game5Button.setBackgroundDrawable(game5DrawableTab);
				}
				else if (helper == 3) 
				{
					game7Button.setBackgroundDrawable(game7DrawableTab);
				}
				else if(helper == 4)
				{
					game8Button.setBackgroundDrawable(game8DrawableTab);
				}
				
				helper = 2;
			}
		}
	};
	
	private View.OnClickListener game7ClickListener = new View.OnClickListener()
	{
		public void onClick(View p1)
		{
			numListView = (ListView)theLayout.findViewById(R.id.picks_number_ListView);
			setData("game7");
			if(game7 != null)
			{
				numListView.setAdapter(game7Adapter);
				numListView.setTextFilterEnabled(true);
				registerForContextMenu(numListView);
				
				
				Button game7Button = (Button)getActivity().findViewById(R.id.picks_Cash5Button);
				game7Button.setBackgroundDrawable(game7DrawableButton);

				
				Button game5Button = (Button)getActivity().findViewById(R.id.picks_DailyButton);
				Button game6Button = (Button)getActivity().findViewById(R.id.picks_Big4Button);
				Button game8Button = (Button)getActivity().findViewById(R.id.picks_PowerButton);
				if(helper == 1)
				{
					game5Button.setBackgroundDrawable(game5DrawableTab);
				}
				else if(helper == 2)
				{
					game6Button.setBackgroundDrawable(game6DrawableTab);
				}
				else if(helper == 4)
				{
					game8Button.setBackgroundDrawable(game8DrawableTab);
				}
				
				helper = 3;
			}
		}
	};

	private View.OnClickListener game8ClickListener = new View.OnClickListener()
	{
		public void onClick(View p1)
		{
			numListView = (ListView)theLayout.findViewById(R.id.picks_number_ListView);
			setData("game8");
			if(game8 != null)
			{
				numListView.setAdapter(game8Adapter);
				numListView.setTextFilterEnabled(true);
				registerForContextMenu(numListView);
				
				

				Button game8Button = (Button)getActivity().findViewById(R.id.picks_PowerButton);
				game8Button.setBackgroundDrawable(game8DrawableButton);

				Button game7Button = (Button)getActivity().findViewById(R.id.picks_Cash5Button);
				Button game5Button = (Button)getActivity().findViewById(R.id.picks_DailyButton);
				Button game6Button = (Button)getActivity().findViewById(R.id.picks_Big4Button);
				if(helper == 1)
				{
					game5Button.setBackgroundDrawable(game5DrawableTab);
				}
				else if(helper == 2)
				{
					game6Button.setBackgroundDrawable(game6DrawableTab);
				}
				else if(helper == 3)
				{
					game7Button.setBackgroundDrawable(game7DrawableTab);
				}

				helper = 4;
			}
		}
	};
	
	public void setData(String dataSet)
	{
		p = this.getActivity().getSharedPreferences(PREFS, 0);
		pEditor = p.edit();

		if (dataSet.equals("game5"))
		{
			int num = p.getInt("game5_num", 0);
			int counter = 0;
			
			if(num > game5PickNum){num = game5PickNum;}

			if (num > 0)
			{
				game5 = new String[num];
			}
			else
			{game5 = null;}

			for (int i = 0; i < game5PickNum; i++)
			{
				if (counter >= num)
				{continue;}
				if (p.contains("game5_game5" + i + "") == true && num > 0)
				{
					game5[counter] = p.getString("game5_game5" + i + "", "0");
					pEditor.putString("game5_game5" + counter, game5[counter]);

					counter++;
				}
			}

			for (int i = num; i < game5PickNum; i++)
			{
				pEditor.remove("game5_game5" + i);
				pEditor.commit();
			}

			if (game5 != null)
			{
				game5Adapter = new NumAdapter(getActivity(), game5, 12, 5);
			}
		}//end of if
		
		else if (dataSet.equals("game6"))
		{
			int num = p.getInt("game6_num", 0);
			int counter = 0;

			if (num > 0)
			{
				game6 = new String[num];
			}
			else
			{game6 = null;}

			for (int i = 0; i < game6PickNum; i++)
			{
				if (counter >= num)
				{continue;}
				if (p.contains("game6_game6" + i + "") == true && num > 0)
				{
					game6[counter] = p.getString("game6_game6" + i + "", "0");
					pEditor.putString("game6_game6" + counter, game6[counter]);

					counter++;
				}
			}

			for (int i = num; i < game6PickNum; i++)
			{
				pEditor.remove("game6_game6" + i);
				pEditor.commit();
			}

			if (game6 != null)
			{
				game6Adapter = new NumAdapter(getActivity(), game6, 13, 6);
			}
		}//end of if
		
		else if (dataSet.equals("game7"))
		{
			int num = p.getInt("game7_num", 0);
			int counter = 0;

			if (num > 0)
			{
				game7 = new String[num];
			}
			else
			{game7 = null;}

			for (int i = 0; i < game7PickNum; i++)
			{
				if (counter >= num)
				{continue;}
				if (p.contains("game7_game7" + i + "") == true && num > 0)
				{
					game7[counter] = p.getString("game7_game7" + i + "", "0");
					pEditor.putString("game7_game7" + counter, game7[counter]);

					counter++;
				}
			}

			for (int i = num; i < game7PickNum; i++)
			{
				pEditor.remove("game7_game7" + i);
				pEditor.commit();
			}

			if (game7 != null)
			{
				game7Adapter = new NumAdapter(getActivity(), game7, 14, 7);
			}
		}//end of if
		
		else if (dataSet.equals("game8"))
		{
			int num = p.getInt("game8_num", 0);
			int counter = 0;

			if (num > 0)
			{
				game8 = new String[num];
			}
			else
			{game8 = null;}

			for (int i = 0; i < game8PickNum; i++)
			{
				if (counter >= num)
				{continue;}
				if (p.contains("game8_game8" + i + "") == true && num > 0)
				{
					game8[counter] = p.getString("game8_game8" + i + "", "0");
					pEditor.putString("game8_game8" + counter, game8[counter]);

					counter++;
				}
			}

			for (int i = num; i < game8PickNum; i++)
			{
				pEditor.remove("game8_game8" + i);
				pEditor.commit();
			}

			if (game8 != null)
			{
				game8Adapter = new NumAdapter(getActivity(), game8, 15, 8);
			}
		}//end of if
	}
}
