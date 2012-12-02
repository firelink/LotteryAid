package com.aid.lottery;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("FloatMath")
public class InitGames extends Fragment 
{
	//Private fields
	private final int pickNum = 20;
	private String game[] = new String[pickNum];
	private int num = 0;
	private String number[] = null;
	private SensorManager aSensorManager;
	private ListView gameList;
	private float aAccel, aAccelCur, aAccelLast;
	public static final String PREFS = "LotteryAid.prefs";
	private SharedPreferences p;
	private SharedPreferences.Editor pEditor;
	private SharedPreferences prefs;
	private LinearLayout theLayout;
	private Drawable drawable;
	private Drawable drawable2;
	OrganizeEverything o_Everything;
	
	//Variables to init
	private String prefString1;
	private String prefString2;
	private String prefStringNum;
	private String prefStringBack;
	private int gNumber;
	private String defValue;

	//end of fields

	
	//ShapeDrawable draw;
	//draw = new ShapeDrawable();
	
	//GradientDrawable g = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, 
	//	new int[] {Color.parseColor("#FF0000"), 
	//				Color.parseColor("#AA0000"),
	//				Color.parseColor("#550000")}); 
	//g.setGradientType(GradientDrawable.LINEAR_GRADIENT); 
	
	//GradientDrawable g2 = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, 
	//										  new int[] {Color.parseColor("#000000"), 
	//											  Color.parseColor("#000000"),
	//										  Color.parseColor("#ff0000")}); 
	//g2.setGradientType(GradientDrawable.LINEAR_GRADIENT); 
	
	//ColorDrawable c = new ColorDrawable(Color.parseColor("#ff0000"));
	
	//StateListDrawable states = new StateListDrawable(); 
	
	//states.addState(new int[] {android.R.attr.state_pressed}, c);
	//states.addState(new int[] {android.R.attr.state_selected}, g);
	//states.addState(new int[]{}, g2);
	
	//LayerDrawable layerD = new LayerDrawable(Layers);
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		if(container == null)
			return null;
		
		theLayout = (LinearLayout)inflater.inflate(R.layout.game,  container, false);
		
		Bundle bundle = getArguments();
		
		try 
		{
			this.prefString1 = bundle.getString("string1");
			this.prefString2 = bundle.getString("string2");
			this.prefStringNum = bundle.getString("stringNum");
			this.prefStringBack = bundle.getString("stringBack");
			this.gNumber = bundle.getInt("gameNum");
			this.defValue = bundle.getString("defString");
			
		} catch (Exception e){
			Log.d("com.aid.lottery", e.getMessage());
			System.exit(0);
		}
		
		prefs = PreferenceManager.getDefaultSharedPreferences(this.getActivity());
		
		Button appButton = (Button)theLayout.findViewById(R.id.game_roll);
		appButton.setOnClickListener(pickNumberListener);

		p = this.getActivity().getSharedPreferences(PREFS, 0);

		if(p.contains(prefString1 + "0"))
		{
			number = new String[5];
			for (int i = 0; i < 5; i++)
			{
				number[i] = p.getString(prefString1 + i + "", "0");
			}
		}
		
		if(p.contains(prefString2 + "0"))
		{
			for (int i = 0; i < pickNum; i++) 
			{
				game[i] = p.getString(prefString2 + i + "", "");
			}
		}

		initExtras();


		gameList.setOnItemClickListener(gameItemListener);

		if (prefs.getBoolean("shake_enable", true) == true)
		{
			aSensorManager = (SensorManager) this.getActivity().getSystemService(Context.SENSOR_SERVICE);
			aSensorManager.registerListener(aSensorEvent, aSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
			aAccel = 0.00f;
			aAccelCur = SensorManager.GRAVITY_EARTH;
			aAccelLast = aAccelCur;
		}
		
		return theLayout;
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
    }

    @Override
	public void onConfigurationChanged(Configuration c)
	{
		super.onConfigurationChanged(c);
		
		Button appButton = (Button)theLayout.findViewById(R.id.game_roll);
		appButton.setOnClickListener(pickNumberListener);
		
		initExtras();

		gameList.setOnItemClickListener(gameItemListener);
	}

	public void onPause()
	{
		super.onPause();

		p = this.getActivity().getSharedPreferences(PREFS, 0);
		pEditor = p.edit();

		if(number != null)
		{
			for (int i = 0; i < 5; i++)
			{
				pEditor.putString(prefString1 + i + "", number[i]);
			}
		}		

		for (int i = 0; i < pickNum; i++)
		{
			if (game[i] != null)
			{
				pEditor.putString(prefString2 + i + "", game[i]);
			}
		}

		pEditor.putInt(prefStringNum, num);

		pEditor.commit();

	}

	public void onResume()
	{
		super.onResume();

		SharedPreferences p = this.getActivity().getSharedPreferences(PREFS, 0);
		prefs = PreferenceManager.getDefaultSharedPreferences(this.getActivity());
		
		drawable = getResources().obtainTypedArray(R.array.numberStyleBack)
				.getDrawable(Integer.parseInt(prefs.getString(prefStringBack, defValue)));
		theLayout.setBackgroundDrawable(drawable);

		num = p.getInt(prefStringNum, 0);

		if(p.contains(prefString1 + "0"))
		{
			number = new String[5];
			for (int i = 0; i < 5; i++)
			{
				number[i] = p.getString(prefString1 + i + "", "0");
			}
		}
		else 
		{
			number = null;
		}
		
		for (int i = 0; i < pickNum; i++)
		{
			game[i] = p.getString(prefString2 + i + "", "");
		}

		if (prefs.getBoolean("shake_enable", true) == true)
		{
			aSensorManager.registerListener(aSensorEvent, aSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
		}
		
		initExtras();
	}

	public void onStop()
	{
		if (prefs.getBoolean("shake_enable", true) == true)
		{
			aSensorManager.unregisterListener(aSensorEvent);
		}

		super.onStop();
	}

	private final SensorEventListener aSensorEvent = new SensorEventListener()
	{
		public void onSensorChanged(SensorEvent p1)
		{
			if (prefs.getBoolean("shake_enable", true) == true)
			{
				float x = p1.values[0];
				float y = p1.values[1];
				float z = p1.values[2];
				aAccelLast = aAccelCur;
				aAccelCur = (float)Math.sqrt((double) (x * x + y * y + z * z));
				float delta = aAccelCur - aAccelLast;
				aAccel = aAccel * 0.9f + delta;

				if (aAccel >= 2)
				{

					roll();

					initExtras();
				}
			}
		}

		public void onAccuracyChanged(Sensor p1, int p2)
		{
			// TODO: Implement this method
		}
	};

	private View.OnClickListener pickNumberListener = new View.OnClickListener()
	{

		public void onClick(View p1)
		{
			roll();
			initExtras();
		}
	};

	private AdapterView.OnItemClickListener gameItemListener = new AdapterView.OnItemClickListener()
	{
		public void onItemClick(AdapterView<?> parent, View p1, int position, long id)
		{
			if (((TextView)p1.findViewById(R.id.cust_label)).getText().toString() == "  ")
			{
				Toast.makeText(getActivity(), "You need to roll first", Toast.LENGTH_SHORT).show();
			}
			else
			{
				if (num < pickNum)
				{
					int check = 0;
					for (int q = 0; q < pickNum; q++)
					{
						if (((TextView) p1.findViewById(R.id.cust_label)).getText().toString().equals(game[q]))
						{
							Toast.makeText(getActivity(), "You already picked that number", Toast.LENGTH_LONG).show();
						}
						else
						{check++;}
					}
					if (pickNum == check)
					{
						game[num] = ((TextView) p1.findViewById(R.id.cust_label)).getText().toString();

						Toast.makeText(getActivity(), "You saved " + ((TextView) p1.findViewById(R.id.cust_label)).getText().toString() + 
									   " to your list of numbers. You can save " + ((pickNum - 1) - num) + " more.", Toast.LENGTH_SHORT).show();

						num++;
						
						TextView tv = ((TextView) p1.findViewById(R.id.cust_label));
						tv.setBackgroundDrawable(drawable2);
					    TransitionDrawable drawable = (TransitionDrawable)tv.getBackground();
					    drawable.startTransition(500);
					    //tv.setTextColor(Color.BLACK);
					}
				}
				else
				{
					o_Everything = new OrganizeEverything(getActivity(), gNumber);
					Toast.makeText(getActivity(), "You cannot save any more " + o_Everything.getGameName() + " Numbers", Toast.LENGTH_LONG).show();
				}
			}
		}
	};
	
	public void initExtras()
	{
		gameList = (ListView)theLayout.findViewById(R.id.game_List); 
		if(number != null)
		{
			gameList.setAdapter(new NumAdapter(getActivity(), number, gNumber - 1, gNumber));
			gameList.setTextFilterEnabled(true);
		}
		else 
		{
			gameList.setAdapter(null);
		}
		drawable2 = getResources().obtainTypedArray(R.array.numberStyleTransition)
				.getDrawable(Integer.parseInt(prefs.getString(prefStringBack, defValue)));
		
	}
	
	public void roll()
	{
		number = new String[5];
		o_Everything = new OrganizeEverything(this.getActivity(), gNumber);
		number = o_Everything.roll();
	}
}


