package com.aid.lottery;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NumAdapter extends ArrayAdapter<String>
{
	private final Context context; 
	private final String data[];
	private final int num;
	public static final String PREFS = "LotteryAid.prefs";
	private Drawable dailyDrawableAll;
	private Drawable dailyDrawableSel;
	private Drawable cash5DrawableAll;
	private Drawable cash5DrawableSel;
	private Drawable big4DrawableAll;
	private Drawable big4DrawableSel;
	private Drawable powerDrawableAll;
	private Drawable powerDrawableSel;
	private Drawable game5DrawableAll;
	private Drawable game5DrawableSel;
	private Drawable game6DrawableAll;
	private Drawable game6DrawableSel;
	private Drawable game7DrawableAll;
	private Drawable game7DrawableSel;
	private Drawable game8DrawableAll;
	private Drawable game8DrawableSel;
	SharedPreferences prefs;
	SharedPreferences preferences;
	SharedPreferences.Editor pEditor;
	private int dailyPickNum;
	private int big4PickNum;
	private int cash5PickNum;
	private int powerPickNum;
	private int game5PickNum;
	private int game6PickNum;
	private int game7PickNum;
	private int game8PickNum;
	private OrganizeEverything o_Everything;
	private int gameNum;
	private String split[];
	private ImageView iv[];
	int thisNumber = 0;

	public NumAdapter(Context context, String[] data, int num, int gameNum) 
	{ 
		super(context, R.layout.custom_adapt, data); 
		this.context = context; 
		this.data = data;
		this.num = num;
		this.gameNum = gameNum;
	}

	public View getView(int p, View v, ViewGroup vg)
	{
		LayoutInflater vi = 
			(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = vi.inflate(R.layout.custom_adapt, vg, false);

		TextView tv = (TextView)view.findViewById(R.id.cust_label);
		tv.setText(data[p]);
		//tv.setVisibility(View.GONE);
		
		prefs = context.getSharedPreferences(PREFS, 0);
		preferences = PreferenceManager.getDefaultSharedPreferences(context);
		
		dailyPickNum = 20;
		big4PickNum = 20;
		cash5PickNum = 20;
		powerPickNum = 20;
		game5PickNum = 20;
		game6PickNum = 20;
		game7PickNum = 20;
		game8PickNum = 20;
		
		initStyles();
		initIViews(view, p);
		
		if(num == 0 || num == 8)
		{			
			if(initGame(view, p, 0, 8, "daily_background", "2", dailyDrawableAll, dailyDrawableSel, 
					dailyPickNum, "daily_daily") == true)
			{
				return (view);
			}
		}
		else if(num == 1 || num == 9)
		{
			if(initGame(view, p, 1, 9, "big4_background", "1", big4DrawableAll, big4DrawableSel, 
					big4PickNum, "big4_big4") == true)
			{
				return (view);
			}
		}
		else if(num == 2 || num == 10)
		{
			if(initGame(view, p, 2, 10, "cash5_background", "0", cash5DrawableAll, cash5DrawableSel, 
					cash5PickNum, "cash5_cash5") == true)
			{
				return (view);
			}
		}
		else if(num == 3 || num == 11)
		{
			if(initGame(view, p, 3, 11, "power_background", "6", powerDrawableAll, powerDrawableSel, 
					powerPickNum, "power_power") == true)
			{
				return (view);
			}
		}
		else if(num == 4 || num == 12)
		{
			if(initGame(view, p, 4, 12, "game5_background", "4", game5DrawableAll, game5DrawableSel, 
					game5PickNum, "game5_game5") == true)
			{
				return (view);
			}
		}
		else if(num == 5 || num == 13)
		{
			if(initGame(view, p, 5, 13, "game6_background", "7", game6DrawableAll, game6DrawableSel, 
					game6PickNum, "game6_game6") == true)
			{
				return (view);
			}
		}
		else if(num == 6 || num == 14)
		{
			if(initGame(view, p, 6, 14, "game7_background", "8", game7DrawableAll, game7DrawableSel, 
					game7PickNum, "game7_game7") == true)
			{
				return (view);
			}
		}
		else if(num == 7 || num == 15)
		{
			if(initGame(view, p, 7, 15, "game8_background", "9", game8DrawableAll, game8DrawableSel, 
					game8PickNum, "game8_game8") == true)
			{
				return (view);
			}
		}
		
		return (view);
	}
	
	public void initStyles()
	{
		dailyDrawableAll = context.getResources().obtainTypedArray(R.array.numberStyleBackAll)
				.getDrawable(Integer.parseInt(preferences.getString("daily_background", "2")));
		dailyDrawableSel = context.getResources().obtainTypedArray(R.array.numberStyleListSelect)
				.getDrawable(Integer.parseInt(preferences.getString("daily_background", "2")));
		
		big4DrawableAll = context.getResources().obtainTypedArray(R.array.numberStyleBackAll)
				.getDrawable(Integer.parseInt(preferences.getString("big4_background", "1")));
		big4DrawableSel = context.getResources().obtainTypedArray(R.array.numberStyleListSelect)
				.getDrawable(Integer.parseInt(preferences.getString("big4_background", "1")));
		
		cash5DrawableAll = context.getResources().obtainTypedArray(R.array.numberStyleBackAll)
				.getDrawable(Integer.parseInt(preferences.getString("cash5_background", "0")));
		cash5DrawableSel = context.getResources().obtainTypedArray(R.array.numberStyleListSelect)
				.getDrawable(Integer.parseInt(preferences.getString("cash5_background", "0")));
		
		powerDrawableAll = context.getResources().obtainTypedArray(R.array.numberStyleBackAll)
				.getDrawable(Integer.parseInt(preferences.getString("power_background", "6")));
		powerDrawableSel = context.getResources().obtainTypedArray(R.array.numberStyleListSelect)
				.getDrawable(Integer.parseInt(preferences.getString("power_background", "6")));
		
		game5DrawableAll = context.getResources().obtainTypedArray(R.array.numberStyleBackAll)
				.getDrawable(Integer.parseInt(preferences.getString("game5_background", "4")));
		game5DrawableSel = context.getResources().obtainTypedArray(R.array.numberStyleListSelect)
				.getDrawable(Integer.parseInt(preferences.getString("game5_background", "4")));
		
		game6DrawableAll = context.getResources().obtainTypedArray(R.array.numberStyleBackAll)
				.getDrawable(Integer.parseInt(preferences.getString("game6_background", "7")));
		game6DrawableSel = context.getResources().obtainTypedArray(R.array.numberStyleListSelect)
				.getDrawable(Integer.parseInt(preferences.getString("game6_background", "7")));
		
		game7DrawableAll = context.getResources().obtainTypedArray(R.array.numberStyleBackAll)
				.getDrawable(Integer.parseInt(preferences.getString("game7_background", "8")));
		game7DrawableSel = context.getResources().obtainTypedArray(R.array.numberStyleListSelect)
				.getDrawable(Integer.parseInt(preferences.getString("game7_background", "8")));
		
		game8DrawableAll = context.getResources().obtainTypedArray(R.array.numberStyleBackAll)
				.getDrawable(Integer.parseInt(preferences.getString("game8_background", "9")));
		game8DrawableSel = context.getResources().obtainTypedArray(R.array.numberStyleListSelect)
				.getDrawable(Integer.parseInt(preferences.getString("game8_background", "9")));
	}
	
	public void initIViews(View view, int p)
	{
		o_Everything = new OrganizeEverything(context, gameNum);
		if(o_Everything.getGameType().equals("Standard"))
		{
			split = data[p].split("");
		}
		else if(o_Everything.getGameType().equals("Two-Digit") || o_Everything.getGameType().equals("#-by-#")
				|| o_Everything.getGameType().equals("#-by-Card"))
		{
			split = data[p].split(", ");
		}
		
		iv = new ImageView[]{(ImageView)view.findViewById(R.id.imageView1),
						  (ImageView)view.findViewById(R.id.imageView2),
						  (ImageView)view.findViewById(R.id.imageView3),
						  (ImageView)view.findViewById(R.id.imageView4),
						  (ImageView)view.findViewById(R.id.imageView5),
						  (ImageView)view.findViewById(R.id.imageView6),
						 };	
	
		for(int i = 0; i < 6; i++)
		{
			if(i >= o_Everything.getDraws())
			{
				iv[i].setVisibility(View.GONE);
			}
		}
	}
	
	public boolean initGame(View view, int p, int firstNum, int secNum, String stringGet, String defValue,
			Drawable theDrawableAll, Drawable theDrawableSel, int theNum, String theData)
	{
		for(int i = 0; i < o_Everything.getDraws(); i++)
		{
			if(o_Everything.getGameType().equals("Standard"))
			{
				
				int ivRes = getSResource(context.getResources().obtainTypedArray(R.array.numberStyleLotto)
						.getString(Integer.parseInt(preferences.getString(stringGet, defValue))) 
						+ split[i+1]);
				iv[i].setImageResource(ivRes);
			}
			else if(o_Everything.getGameType().equals("Two-Digit"))
			{
				int ivRes = getSResource(context.getResources().obtainTypedArray(R.array.numberStyleLotto)
						.getString(Integer.parseInt(preferences.getString(stringGet, defValue))) 
						+ split[i]);
				iv[i].setImageResource(ivRes);
			}
			else if(o_Everything.getGameType().equals("#-by-#"))
			{
				String numArrayString[] = o_Everything.getDrawsString().split("x");
				int numArray[] = new int[2];
				
				for(int j = 0; j < 2; j++)
				{
					numArray[j] = Integer.parseInt(numArrayString[j]);
				}
				
				if(i > numArray[0] - 1)
				{		
					int newNumber = opNumber(Integer.parseInt(preferences.getString(stringGet, defValue)));
					
					int ivRes = getSResource(context.getResources().obtainTypedArray(R.array.numberStyleLotto)
										.getString(newNumber) + split[i]);

					iv[i].setImageResource(ivRes);
				}
				else 
				{	
					int ivRes = getSResource(context.getResources().obtainTypedArray(R.array.numberStyleLotto)
											 .getString(Integer.parseInt(preferences.getString(stringGet, defValue))) 
											 + split[i]);
					iv[i].setImageResource(ivRes);
				}
			}
			else if(o_Everything.getGameType().equals("#-by-Card"))
			{
				String numArrayString[] = o_Everything.getDrawsString().split("x");
				int numArray[] = new int[2];
				
				for(int j = 0; j < 2; j++)
				{
					numArray[j] = Integer.parseInt(numArrayString[j]);
				}
				
				if(i > numArray[0] - 1)
				{	
					String splitString[] = split[i].split(" ");
					for (int j = 0; j < splitString.length; j++) 
					{
						splitString[j] = splitString[j].toLowerCase(); 
					}
					int ivRes = getSResource(splitString[0] + "_" + splitString[1] + "_" + splitString[2]);
					iv[i].setImageResource(ivRes);
				}
				else 
				{	
					int ivRes = getSResource(context.getResources().obtainTypedArray(R.array.numberStyleLotto)
											 .getString(Integer.parseInt(preferences.getString(stringGet, defValue))) 
											 + split[i]);
					iv[i].setImageResource(ivRes);
				}
			}
		}
		
		if(num == firstNum)
		{
			for(int i = 0; i < theNum; i++)
			{
				if(data[p].equals(prefs.getString(theData+i, "")))
				{
					view.setBackgroundDrawable(theDrawableAll);
					//tv.setTextColor(Color.BLACK);
					return true;
				}
				else
				{
					view.setBackgroundDrawable(theDrawableSel);
				}
			}
		}
		else if(num == secNum)
		{
			view.setBackgroundDrawable(theDrawableSel);
		}
		
		return false;
	}
	
	public int getSResource(String aString)
	{
		String packageName = "com.aid.lottery";
		int resId = context.getResources().getIdentifier(aString, "drawable", packageName);
		return resId;
	}
	
	public int opNumber(int i)
	{
		switch(i)
		{
			case 0:
			{
				i = 5;
				break;
			}
			case 1:
			{
				i = 2;
				break;
			}
			case 2:
			{
				i = 1;
				break;
			}
			case 3:
			{
				i = 4;
				break;
			}
			case 4:
			{
				i = 3;
				break;
			}
			case 5:
			{
				i = 0;
				break;
			}
			case 6:
			{
				i = 7;
				break;
			}
			case 7:
			{
				i = 6;
				break;
			}
			case 8:
			{
				i = 9;
				break;
			}
			case 9:
			{
				i = 8;
				break;
			}
			case 10:
			{
				i = 8;
				break;
			}
		}
		
		return i;
	}
	
//	public static Bitmap doInvert(Bitmap src)
//	{
//		Bitmap bmOut = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
//		
//		int A, R, G, B;
//		int pixelColor;
//		
//		int height = src.getHeight();
//		int width = src.getWidth();
//		
//		for(int y = 0; y < height; y+=2)
//		{
//			for(int x = 0; x < width; x+=2)
//			{
//				pixelColor = src.getPixel(x, y);
//				
//				A = Color.alpha(pixelColor);
//				R = 255 - Color.red(pixelColor);
//				G = 255 - Color.green(pixelColor);
//				B = 255 - Color.blue(pixelColor);
//				
//				bmOut.setPixel(x,y, Color.argb(A, R, G, B));
//			}
//		}
//		
//		return bmOut;
//	}
}
