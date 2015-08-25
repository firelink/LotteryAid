package com.aid.lottery;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class OrganizeEverything 
{
	private String GAME_TYPE;
	private String GAME_NAME;
	private int GAME_RANGE_START;
	private int GAME_RANGE_END;
	private int GAME_NUMBER_OF_DRAWS;
	private int GAME_NUMBER_OF_GAMES;
	private int LOTTO_INDEX;
	private int GAME_NUMBER;
	private String GAME_NUMBER_OF_DRAWS_STRING;
	private String GAME_RANGE_END_STRING;
	private Context context;
	private SharedPreferences prefs;
	private DecimalFormat exZero;
	private Random r;
	
	public OrganizeEverything(Context context, int gameNumber) 
	{
		this.context = context;
		this.GAME_NUMBER = gameNumber;
		initVars();
	}
		
	private void initVars()
	{
		prefs = PreferenceManager.getDefaultSharedPreferences(context);
		
		if(prefs.contains("state_chosen"))
		{
			LOTTO_INDEX = Integer.parseInt(prefs.getString("state_chosen", "0"));
			
			GAME_NAME = getTheGameName(GAME_NUMBER);
			GAME_TYPE = getTheGameType(GAME_NUMBER);
			GAME_RANGE_START = getTheRange(GAME_NUMBER, "Start"); 
			GAME_RANGE_END = getTheRange(GAME_NUMBER, "End");
			GAME_RANGE_END_STRING = getTheRangeAgain(GAME_NUMBER, "End");
			GAME_NUMBER_OF_DRAWS = getTheDraw(GAME_NUMBER);
			GAME_NUMBER_OF_DRAWS_STRING = getTheDrawAgain(GAME_NUMBER);
			GAME_NUMBER_OF_GAMES = Integer.parseInt(context.getResources().obtainTypedArray(R.array.stateLottoGameNum)
					.getString(LOTTO_INDEX));
		}
		else 
		{
			Toast.makeText(context, "Something went wrong...", Toast.LENGTH_LONG).show();
		}
	}
	
	private String getTheGameName(int gN)
	{
		TypedArray game = null;
		
		switch(gN)
		{
			case 1:
			{
				game = context.getResources().obtainTypedArray(R.array.stateLottoFirstGameName);
				break;
			}
			case 2:
			{
				game = context.getResources().obtainTypedArray(R.array.stateLottoSecondGameName);
				break;
			}
			case 3:
			{
				game = context.getResources().obtainTypedArray(R.array.stateLottoThirdGameName);
				break;
			}
			case 4:
			{
				game = context.getResources().obtainTypedArray(R.array.stateLottoFourthGameName);
				break;
			}
			case 5:
			{
				game = context.getResources().obtainTypedArray(R.array.stateLottoFifthGameName);
				break;
			}
			case 6:
			{
				game = context.getResources().obtainTypedArray(R.array.stateLottoSixthGameName);
				break;
			}
			case 7:
			{
				game = context.getResources().obtainTypedArray(R.array.stateLottoSeventhGameName);
				break;
			}
			
			case 8:
			{
				game = context.getResources().obtainTypedArray(R.array.stateLottoEighthGameName);
				break;
			}
		}
		
		return game.getString(LOTTO_INDEX);
	}
	
	private String getTheGameType(int gN)
	{
		TypedArray game = null;
		
		switch(gN)
		{
			case 1:
			{
				game = context.getResources().obtainTypedArray(R.array.stateLottoFirstGameType);
				break;
			}
			case 2:
			{
				game = context.getResources().obtainTypedArray(R.array.stateLottoSecondGameType);
				break;
			}
			case 3:
			{
				game = context.getResources().obtainTypedArray(R.array.stateLottoThirdGameType);
				break;
			}
			case 4:
			{
				game = context.getResources().obtainTypedArray(R.array.stateLottoFourthGameType);
				break;
			}
			
			case 5:
			{
				game = context.getResources().obtainTypedArray(R.array.stateLottoFifthGameType);
				break;
			}
			
			case 6:
			{
				game = context.getResources().obtainTypedArray(R.array.stateLottoSixthGameType);
				break;
			}
			
			case 7:
			{
				game = context.getResources().obtainTypedArray(R.array.stateLottoSeventhGameType);
				break;
			}
			
			case 8:
			{
				game = context.getResources().obtainTypedArray(R.array.stateLottoEighthGameType);
				break;
			}
		}
		
		return game.getString(LOTTO_INDEX);
	}
	
	private int getTheRange(int gN, String type)
	{
		TypedArray game = null;
		
		if(type.equals("Start"))
		{
			switch(gN)
			{
				case 1:
				{
					game = context.getResources().obtainTypedArray(R.array.stateLottoFirstGameRangeStart);
					break;
				}
				case 2:
				{
					game = context.getResources().obtainTypedArray(R.array.stateLottoSecondGameRangeStart);
					break;
				}
				case 3:
				{
					game = context.getResources().obtainTypedArray(R.array.stateLottoThirdGameRangeStart);
					break;
				}
				case 4:
				{
					game = context.getResources().obtainTypedArray(R.array.stateLottoFourthGameRangeStart);
					break;
				}
				case 5:
				{
					game = context.getResources().obtainTypedArray(R.array.stateLottoFifthGameRangeStart);
					break;
				}
				case 6:
				{
					game = context.getResources().obtainTypedArray(R.array.stateLottoSixthGameRangeStart);
					break;
				}
				case 7:
				{
					game = context.getResources().obtainTypedArray(R.array.stateLottoSeventhGameRangeStart);
					break;
				}
				case 8:
				{
					game = context.getResources().obtainTypedArray(R.array.stateLottoEighthGameRangeStart);
					break;
				}
			}
		}
		else if(type.equals("End"))
		{
			switch(gN)
			{
				case 1:
				{
					game = context.getResources().obtainTypedArray(R.array.stateLottoFirstGameRangeEnd);
					break;
				}
				case 2:
				{
					game = context.getResources().obtainTypedArray(R.array.stateLottoSecondGameRangeEnd);
					break;
				}
				case 3:
				{
					game = context.getResources().obtainTypedArray(R.array.stateLottoThirdGameRangeEnd);
					break;
				}
				case 4:
				{
					game = context.getResources().obtainTypedArray(R.array.stateLottoFourthGameRangeEnd);
					break;
				}
				case 5:
				{
					game = context.getResources().obtainTypedArray(R.array.stateLottoFifthGameRangeEnd);
					break;
				}
				case 6:
				{
					game = context.getResources().obtainTypedArray(R.array.stateLottoSixthGameRangeEnd);
					break;
				}
				case 7:
				{
					game = context.getResources().obtainTypedArray(R.array.stateLottoSeventhGameRangeEnd);
					break;
				}
				case 8:
				{
					game = context.getResources().obtainTypedArray(R.array.stateLottoEighthGameRangeEnd);
					break;
				}
			}
		}
		
		try {
			Integer.parseInt(game.getString(LOTTO_INDEX));
		} catch (Exception e) {
			
			String numArrayString[] = game.getString(LOTTO_INDEX).split("x");
			int numArray[] = new int[2];
			
			for(int i = 0; i < 2; i++)
			{
				numArray[i] = Integer.parseInt(numArrayString[i]);
			}
			
			return numArray[0];
		}
		
		return Integer.parseInt(game.getString(LOTTO_INDEX));
	}

	
	private String getTheRangeAgain(int gN, String type)
	{
		TypedArray game = null;
		
		if(type.equals("Start"))
		{
			switch(gN)
			{
				case 1:
				{
					game = context.getResources().obtainTypedArray(R.array.stateLottoFirstGameRangeStart);
					break;
				}
				case 2:
				{
					game = context.getResources().obtainTypedArray(R.array.stateLottoSecondGameRangeStart);
					break;
				}
				case 3:
				{
					game = context.getResources().obtainTypedArray(R.array.stateLottoThirdGameRangeStart);
					break;
				}
				case 4:
				{
					game = context.getResources().obtainTypedArray(R.array.stateLottoFourthGameRangeStart);
					break;
				}
				case 5:
				{
					game = context.getResources().obtainTypedArray(R.array.stateLottoFifthGameRangeStart);
					break;
				}
				case 6:
				{
					game = context.getResources().obtainTypedArray(R.array.stateLottoSixthGameRangeStart);
					break;
				}
				case 7:
				{
					game = context.getResources().obtainTypedArray(R.array.stateLottoSeventhGameRangeStart);
					break;
				}
				case 8:
				{
					game = context.getResources().obtainTypedArray(R.array.stateLottoEighthGameRangeStart);
					break;
				}
			}
		}
		else if(type.equals("End"))
		{
			switch(gN)
			{
				case 1:
				{
					game = context.getResources().obtainTypedArray(R.array.stateLottoFirstGameRangeEnd);
					break;
				}
				case 2:
				{
					game = context.getResources().obtainTypedArray(R.array.stateLottoSecondGameRangeEnd);
					break;
				}
				case 3:
				{
					game = context.getResources().obtainTypedArray(R.array.stateLottoThirdGameRangeEnd);
					break;
				}
				case 4:
				{
					game = context.getResources().obtainTypedArray(R.array.stateLottoFourthGameRangeEnd);
					break;
				}
				case 5:
				{
					game = context.getResources().obtainTypedArray(R.array.stateLottoFifthGameRangeEnd);
					break;
				}
				case 6:
				{
					game = context.getResources().obtainTypedArray(R.array.stateLottoSixthGameRangeEnd);
					break;
				}
				case 7:
				{
					game = context.getResources().obtainTypedArray(R.array.stateLottoSeventhGameRangeEnd);
					break;
				}
				case 8:
				{
					game = context.getResources().obtainTypedArray(R.array.stateLottoEighthGameRangeEnd);
					break;
				}
			}
		}
		
		return game.getString(LOTTO_INDEX);
	}
	
	private int getTheDraw(int gN)
	{
		TypedArray game = null;
		
		switch(gN)
		{
			case 1:
			{
				game = context.getResources().obtainTypedArray(R.array.stateLottoFirstGameDraws);
				break;
			}
			case 2:
			{
				game = context.getResources().obtainTypedArray(R.array.stateLottoSecondGameDraws);
				break;
			}
			case 3:
			{
				game = context.getResources().obtainTypedArray(R.array.stateLottoThirdGameDraws);
				break;
			}
			case 4:
			{
				game = context.getResources().obtainTypedArray(R.array.stateLottoFourthGameDraws);
				break;
			}
			case 5:
			{
				game = context.getResources().obtainTypedArray(R.array.stateLottoFifthGameDraws);
				break;
			}
			case 6:
			{
				game = context.getResources().obtainTypedArray(R.array.stateLottoSixthGameDraws);
				break;
			}
			case 7:
			{
				game = context.getResources().obtainTypedArray(R.array.stateLottoSeventhGameDraws);
				break;
			}
			case 8:
			{
				game = context.getResources().obtainTypedArray(R.array.stateLottoEighthGameDraws);
				break;
			}
		}
		
		try {
			Integer.parseInt(game.getString(LOTTO_INDEX));
		} catch (Exception e) {
			String numArrayString[] = game.getString(LOTTO_INDEX).split("x");
			int numArray[] = new int[2];
			for(int i = 0; i < 2; i++)
			{
				numArray[i] = Integer.parseInt(numArrayString[i]);
			}
			
			return numArray[0] + numArray[1];
		}
		
		return Integer.parseInt(game.getString(LOTTO_INDEX));
	}
	
	private String getTheDrawAgain(int gN)
	{
		TypedArray game = null;
		
		switch(gN)
		{
			case 1:
			{
				game = context.getResources().obtainTypedArray(R.array.stateLottoFirstGameDraws);
				break;
			}
			case 2:
			{
				game = context.getResources().obtainTypedArray(R.array.stateLottoSecondGameDraws);
				break;
			}
			case 3:
			{
				game = context.getResources().obtainTypedArray(R.array.stateLottoThirdGameDraws);
				break;
			}
			case 4:
			{
				game = context.getResources().obtainTypedArray(R.array.stateLottoFourthGameDraws);
				break;
			}
			case 5:
			{
				game = context.getResources().obtainTypedArray(R.array.stateLottoFifthGameDraws);
				break;
			}
			case 6:
			{
				game = context.getResources().obtainTypedArray(R.array.stateLottoSixthGameDraws);
				break;
			}
			case 7:
			{
				game = context.getResources().obtainTypedArray(R.array.stateLottoSeventhGameDraws);
				break;
			}
			case 8:
			{
				game = context.getResources().obtainTypedArray(R.array.stateLottoEighthGameDraws);
				break;
			}
		}
		
		return game.getString(LOTTO_INDEX);
	}
	
	public String getGameName() 
	{
		return GAME_NAME;
	}
	
	public String getGameType() 
	{
		return GAME_TYPE;
	}

	public int getRangeStart() 
	{
		return GAME_RANGE_START;
	}
	public int getRangeEnd() 
	{
		return GAME_RANGE_END;
	}
	
	public String getRangeEndString() 
	{
		return GAME_RANGE_END_STRING;
	}
	
	public int getDraws() 
	{
		return GAME_NUMBER_OF_DRAWS;
	}
	
	public String getDrawsString() 
	{
		return GAME_NUMBER_OF_DRAWS_STRING;
	}
	
	public int getNumberOfGames() 
	{
		return GAME_NUMBER_OF_GAMES;
	}

	public String[] roll()
	{
		r = new Random();
		String number[] = new String[5];

		if(getGameType().equals("Standard") && getRangeStart() == 0 && getRangeEnd() == 9)
		{
			String decFormat = "0";	
			for(int i = 0; i < getDraws()-1; i++)
			{
				decFormat = decFormat + "0";
			}
			exZero = new DecimalFormat(decFormat);
			number[0] = exZero.format(r.nextInt((int)Math.pow(10, getDraws())));
			number[1] = exZero.format(r.nextInt((int)Math.pow(10, getDraws())));
			number[2] = exZero.format(r.nextInt((int)Math.pow(10, getDraws())));
			number[3] = exZero.format(r.nextInt((int)Math.pow(10, getDraws())));
			number[4] = exZero.format(r.nextInt((int)Math.pow(10, getDraws())));
		}
		else if(getGameType().equals("Two-Digit"))
		{
			if(getRangeStart() <= 1)
			{
				number[0] = "" + (r.nextInt(getRangeEnd()) + getRangeStart());
				number[1] = "" + (r.nextInt(getRangeEnd()) + getRangeStart());
				number[2] = "" + (r.nextInt(getRangeEnd()) + getRangeStart());
				number[3] = "" + (r.nextInt(getRangeEnd()) + getRangeStart());
				number[4] = "" + (r.nextInt(getRangeEnd()) + getRangeStart());
				
				for(int i = 0; i < 5; i++)
				{
					for(int j = 0; j < getDraws()-1; j++)
					{
						number[i] = number[i] + ", " + (r.nextInt(getRangeEnd()) + getRangeStart());
						if(checkDupes(number[i]) == true)
						{
							number[i] = fix(number[i]);
							j--;
						}
						else
						{}
					}
					
					number[i] = sortNumbers(number[i]);
				}
			}
		}
		else if(getGameType().equals("#-by-#") || getGameType().equals("#-by-Card"))
		{
			if(getRangeStart() <= 1)
			{
				String numArrayString[] = GAME_NUMBER_OF_DRAWS_STRING.split("x");
				String numArrayString2[] = GAME_RANGE_END_STRING.split("x");
				int numArray[] = new int[2];
				int numArray2[] = new int[2];
				String cards[] = null;
				
				
				for(int i = 0; i < 2; i++)
				{
					numArray[i] = Integer.parseInt(numArrayString[i]);
					numArray2[i] = Integer.parseInt(numArrayString2[i]);
				}
				
				number[0] = "" + (r.nextInt(getRangeEnd()) + getRangeStart());
				number[1] = "" + (r.nextInt(getRangeEnd()) + getRangeStart());
				number[2] = "" + (r.nextInt(getRangeEnd()) + getRangeStart());
				number[3] = "" + (r.nextInt(getRangeEnd()) + getRangeStart());
				number[4] = "" + (r.nextInt(getRangeEnd()) + getRangeStart());
				

				for(int i = 0; i < 5; i++)
				{
					for(int j = 0; j < numArray[0]-1; j++)
					{
						number[i] = number[i] + ", " + (r.nextInt(getRangeEnd()) + getRangeStart());
						if(checkDupes(number[i]) == true)
						{
							number[i] = fix(number[i]);
							j--;
						}
						else
						{}
					}

					number[i] = sortNumbers(number[i]);
				}
				
				String temp = "";
				
				if(getGameType().equals("#-by-Card"))
				{
					cards = new String[]{
							"Ace of Spades",
							"Ace of Clubs",
							"Ace of Hearts",
							"Ace of Diamonds",
							"King of Spades",
							"King of Clubs",
							"King of Hearts",
							"King of Diamonds",
							"Queen of Spades",
							"Queen of Clubs",
							"Queen of Hearts",
							"Queen of Diamonds",
							"Jack of Spades",
							"Jack of Clubs",
							"Jack of Hearts",
							"Jack of Diamonds" };				
				}
				
				for(int i = 0; i < 5; i++)
				{
					temp = number[i];
					
					if(getGameType().equals("#-by-Card"))
					{
						number[i] = "" + cards[(r.nextInt(numArray2[1]) + getRangeStart())];
					}
					else
					{
						number[i] = "" + (r.nextInt(numArray2[1]) + getRangeStart());
					}
					
					for(int j = 0; j < numArray[1]-1; j++)
					{
						number[i] = number[i] + ", " + (r.nextInt(numArray2[1]) + getRangeStart());
						if(checkDupes(number[i]) == true)
						{
							number[i] = fix(number[i]);
							j--;
						}
						else
						{}
					}
					
					if(getGameType().equals("#-by-Card"))
					{
						number[i] = temp + ", " + number[i];
					}
					
					else
					{
						number[i] = sortNumbers(number[i]);
						
						number[i] = temp + ", " + number[i];
					}
					
				}
			}
		}	
		return number;
	}
	
	private boolean checkDupes(String number)
	{
		String numArray[] = number.split(", ");
		//Toast.makeText(context, numArray[0] + " , " + numArray[1], Toast.LENGTH_LONG).show();
		for(int i = 0; i < numArray.length; i++)
		{
			if(numArray[numArray.length-1].equals(numArray[i]) && (numArray.length - 1 != i))
			{
				numArray[numArray.length-1] = "";
				
				return true;
			}
		}
		
		return false;
	}
	
	private String fix(String number)
	{
		String numArray[] = number.split(", ");
		//Toast.makeText(context, numArray[0] + " , " + numArray[1], Toast.LENGTH_LONG).show();
		for(int i = 0; i < numArray.length; i++)
		{
			if(i == (numArray.length - 1))
			{}
			else
			{
				if(0 == i)
				{
					number = numArray[i];
				}
				else 
				{
					number = number + ", " + numArray[i];
				}
			}
		}
		
		return number;
	}
	
	private String sortNumbers(String number)
	{
		String numArray[] = number.split(", ");
		int numArrayInt[] = new int[numArray.length];
		
		for(int i = 0; i < numArray.length; i++){numArrayInt[i] = Integer.parseInt(numArray[i]);}
		
		Arrays.sort(numArrayInt);
		
		for(int i = 0; i < numArray.length; i++)
		{
			if(0 == i){number = numArrayInt[i]+"";}
			else{number = number + ", " + numArrayInt[i];}
		}
		
		
		return number;
	}
}
