/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings;



import com.android.settings.R;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.preference.CheckBoxPreference;
import android.util.Log;



public class CustomBatteryOptions extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener {
    
    private final String Junk_Battery_Settings = "JUNK_BATTERY_SETTINGS";
	private final String BATTERY_ICONS = "battery_icon_num";
	private final String BATTERY_BAR_BOTTOM = "battery_bar_bottom";
	private final String BATTERY_BAR_RIGHT = "battery_bar_right";
	private final String BATTERY_BAR_WIDTH = "battery_bar_width";
	private final String BATTERY_LEVEL_ONE = "battery_levels_one";
	private final String BATTERY_LEVEL_COLOR_ONE = "battery_levels_color_one";
	private final String BATTERY_LEVEL_TWO = "battery_levels_two";
	private final String BATTERY_LEVEL_COLOR_TWO = "battery_levels_color_two";
	private final String BATTERY_LEVEL_COLOR_THREE = "battery_levels_color_three";    
	
	private final String DEPLETED_LEVEL_ONE = "depleted_levels_one";
	private final String DEPLETED_LEVEL_COLOR_ONE = "depleted_levels_color_one";
	private final String DEPLETED_LEVEL_TWO = "depleted_levels_two";
	private final String DEPLETED_LEVEL_COLOR_TWO = "depleted_levels_color_two";
	private final String DEPLETED_LEVEL_COLOR_THREE = "depleted_levels_color_three";    

	
	
	private final int BATTERY_STOCK = 0;
    private final int BATTERY_NUMBER = 1;
    private final int BATTERY_CIRCLE = 2;
    private final int BATTERY_PIE = 3;
    private final int BATTERY_NONE = 10;
	


	private PreferenceManager prefMgr;
	private SharedPreferences sharedPref;
	private int mBatteryIconValue = 0;
	private CheckBoxPreference mBatteryBarBottom;
	private CheckBoxPreference mBatteryBarRight;
	private Preference mBatteryBarWidth;
	private Preference mBatteryLevelOne;
    private Preference mBatteryLevelColorOne;
	private Preference mBatteryLevelTwo;
    private Preference mBatteryLevelColorTwo;
    private Preference mBatteryLevelColorThree;
    
	private Preference mDepletedLevelOne;
    private Preference mDepletedLevelColorOne;
	private Preference mDepletedLevelTwo;
    private Preference mDepletedLevelColorTwo;
    private Preference mDepletedLevelColorThree;
    
    private int batBarWidth = 5;
    private int batLevelOne = 10;
    private int batLevelTwo = 30;

    private int depletedLevelOne = 10;
    private int depletedLevelTwo = 30;
	
	
	
    /** If there is no setting in the provider, use this. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        prefMgr = getPreferenceManager();
        prefMgr.setSharedPreferencesName("Junk_Settings");
        prefMgr.setSharedPreferencesMode(Context.MODE_WORLD_READABLE);
        prefMgr.getSharedPreferences();
        sharedPref = prefMgr.getSharedPreferences();
        
        mBatteryIconValue = Integer.valueOf(sharedPref.getString(BATTERY_ICONS, "0"));
 
        // Set the preferences based on what battery is chosen

        switch (mBatteryIconValue) {

        case BATTERY_STOCK:
        	addPreferencesFromResource(R.xml.custom_bat_stock_settings);
    		mBatteryLevelOne = (Preference) findPreference(BATTERY_LEVEL_ONE);
    		mBatteryLevelOne.setOnPreferenceChangeListener(this);
    		mBatteryLevelColorOne = (Preference) findPreference(BATTERY_LEVEL_COLOR_ONE);
    		mBatteryLevelColorOne.setOnPreferenceChangeListener(this);
    		mBatteryLevelTwo = (Preference) findPreference(BATTERY_LEVEL_TWO);
    		mBatteryLevelTwo.setOnPreferenceChangeListener(this);
    		mBatteryLevelColorTwo = (Preference) findPreference(BATTERY_LEVEL_COLOR_TWO);
    		mBatteryLevelColorTwo.setOnPreferenceChangeListener(this);
    		mBatteryLevelColorThree = (Preference) findPreference(BATTERY_LEVEL_COLOR_THREE);
    		mBatteryLevelColorThree.setOnPreferenceChangeListener(this);
    		
    		batLevelOne = sharedPref.getInt(BATTERY_LEVEL_ONE, 10);
    		batLevelTwo = sharedPref.getInt(BATTERY_LEVEL_TWO, 30);
    		batBarWidth = sharedPref.getInt(BATTERY_BAR_WIDTH, 5);
            break;
            
        case BATTERY_NUMBER:
        	addPreferencesFromResource(R.xml.custom_bat_number_settings);
    		mBatteryLevelOne = (Preference) findPreference(BATTERY_LEVEL_ONE);
    		mBatteryLevelOne.setOnPreferenceChangeListener(this);
    		mBatteryLevelColorOne = (Preference) findPreference(BATTERY_LEVEL_COLOR_ONE);
    		mBatteryLevelColorOne.setOnPreferenceChangeListener(this);
    		mBatteryLevelTwo = (Preference) findPreference(BATTERY_LEVEL_TWO);
    		mBatteryLevelTwo.setOnPreferenceChangeListener(this);
    		mBatteryLevelColorTwo = (Preference) findPreference(BATTERY_LEVEL_COLOR_TWO);
    		mBatteryLevelColorTwo.setOnPreferenceChangeListener(this);
    		mBatteryLevelColorThree = (Preference) findPreference(BATTERY_LEVEL_COLOR_THREE);
    		mBatteryLevelColorThree.setOnPreferenceChangeListener(this);
    		
    		batLevelOne = sharedPref.getInt(BATTERY_LEVEL_ONE, 10);
    		batLevelTwo = sharedPref.getInt(BATTERY_LEVEL_TWO, 30);
    		batBarWidth = sharedPref.getInt(BATTERY_BAR_WIDTH, 5);
    		
            mBatteryBarBottom = (CheckBoxPreference) findPreference(BATTERY_BAR_BOTTOM);
    		mBatteryBarBottom.setOnPreferenceChangeListener(this);
            mBatteryBarRight = (CheckBoxPreference) findPreference(BATTERY_BAR_RIGHT);
    		mBatteryBarRight.setOnPreferenceChangeListener(this);
            mBatteryBarWidth = (Preference) findPreference(BATTERY_BAR_WIDTH);
    		mBatteryBarWidth.setOnPreferenceChangeListener(this);
    		
    		mDepletedLevelOne = (Preference) findPreference(DEPLETED_LEVEL_ONE);
    		mDepletedLevelOne.setOnPreferenceChangeListener(this);
    		mDepletedLevelColorOne = (Preference) findPreference(DEPLETED_LEVEL_COLOR_ONE);
    		mDepletedLevelColorOne.setOnPreferenceChangeListener(this);
    		mDepletedLevelTwo = (Preference) findPreference(DEPLETED_LEVEL_TWO);
    		mDepletedLevelTwo.setOnPreferenceChangeListener(this);
    		mDepletedLevelColorTwo = (Preference) findPreference(DEPLETED_LEVEL_COLOR_TWO);
    		mDepletedLevelColorTwo.setOnPreferenceChangeListener(this);
    		mDepletedLevelColorThree = (Preference) findPreference(DEPLETED_LEVEL_COLOR_THREE);
    		mDepletedLevelColorThree.setOnPreferenceChangeListener(this);
            break;

        case BATTERY_CIRCLE:
        	addPreferencesFromResource(R.xml.custom_bat_circle_settings);
    		mBatteryLevelOne = (Preference) findPreference(BATTERY_LEVEL_ONE);
    		mBatteryLevelOne.setOnPreferenceChangeListener(this);
    		mBatteryLevelColorOne = (Preference) findPreference(BATTERY_LEVEL_COLOR_ONE);
    		mBatteryLevelColorOne.setOnPreferenceChangeListener(this);
    		mBatteryLevelTwo = (Preference) findPreference(BATTERY_LEVEL_TWO);
    		mBatteryLevelTwo.setOnPreferenceChangeListener(this);
    		mBatteryLevelColorTwo = (Preference) findPreference(BATTERY_LEVEL_COLOR_TWO);
    		mBatteryLevelColorTwo.setOnPreferenceChangeListener(this);
    		mBatteryLevelColorThree = (Preference) findPreference(BATTERY_LEVEL_COLOR_THREE);
    		mBatteryLevelColorThree.setOnPreferenceChangeListener(this);
    		
    		mDepletedLevelOne = (Preference) findPreference(DEPLETED_LEVEL_ONE);
    		mDepletedLevelOne.setOnPreferenceChangeListener(this);
    		mDepletedLevelColorOne = (Preference) findPreference(DEPLETED_LEVEL_COLOR_ONE);
    		mDepletedLevelColorOne.setOnPreferenceChangeListener(this);
    		mDepletedLevelTwo = (Preference) findPreference(DEPLETED_LEVEL_TWO);
    		mDepletedLevelTwo.setOnPreferenceChangeListener(this);
    		mDepletedLevelColorTwo = (Preference) findPreference(DEPLETED_LEVEL_COLOR_TWO);
    		mDepletedLevelColorTwo.setOnPreferenceChangeListener(this);
    		mDepletedLevelColorThree = (Preference) findPreference(DEPLETED_LEVEL_COLOR_THREE);
    		mDepletedLevelColorThree.setOnPreferenceChangeListener(this);
    		
            break;

        case BATTERY_PIE:
        	addPreferencesFromResource(R.xml.custom_bat_circle_settings);
    		mBatteryLevelOne = (Preference) findPreference(BATTERY_LEVEL_ONE);
    		mBatteryLevelOne.setOnPreferenceChangeListener(this);
    		mBatteryLevelColorOne = (Preference) findPreference(BATTERY_LEVEL_COLOR_ONE);
    		mBatteryLevelColorOne.setOnPreferenceChangeListener(this);
    		mBatteryLevelTwo = (Preference) findPreference(BATTERY_LEVEL_TWO);
    		mBatteryLevelTwo.setOnPreferenceChangeListener(this);
    		mBatteryLevelColorTwo = (Preference) findPreference(BATTERY_LEVEL_COLOR_TWO);
    		mBatteryLevelColorTwo.setOnPreferenceChangeListener(this);
    		mBatteryLevelColorThree = (Preference) findPreference(BATTERY_LEVEL_COLOR_THREE);
    		mBatteryLevelColorThree.setOnPreferenceChangeListener(this);
    		
    		mDepletedLevelOne = (Preference) findPreference(DEPLETED_LEVEL_ONE);
    		mDepletedLevelOne.setOnPreferenceChangeListener(this);
    		mDepletedLevelColorOne = (Preference) findPreference(DEPLETED_LEVEL_COLOR_ONE);
    		mDepletedLevelColorOne.setOnPreferenceChangeListener(this);
    		mDepletedLevelTwo = (Preference) findPreference(DEPLETED_LEVEL_TWO);
    		mDepletedLevelTwo.setOnPreferenceChangeListener(this);
    		mDepletedLevelColorTwo = (Preference) findPreference(DEPLETED_LEVEL_COLOR_TWO);
    		mDepletedLevelColorTwo.setOnPreferenceChangeListener(this);
    		mDepletedLevelColorThree = (Preference) findPreference(DEPLETED_LEVEL_COLOR_THREE);
    		mDepletedLevelColorThree.setOnPreferenceChangeListener(this);
    		
            break;            
            
        case BATTERY_NONE:

        	
            break;           
            
       default:
            break;
        } 
        
        

		
        
        resendIntents();
        
        
    }

    
    @Override
    public void onResume() {
        super.onResume();
    }

    
    @Override
    public void onPause() {
        super.onPause();
    }

 
    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
    	
    	if (preference == mBatteryLevelOne) {
			new NumberPickerDialog(preferenceScreen.getContext(),
                    batteryLevelOneListener,
                    batLevelOne,
                    0,
                    batLevelTwo - 1,
                    R.string.battery_level_max).show();
        
    	} else if (preference == mBatteryLevelTwo) {
			new NumberPickerDialog(preferenceScreen.getContext(),
                    batteryLevelTwoListener,
                    batLevelTwo,
                    batLevelOne + 1,
                    99,
                    R.string.battery_level_max).show();
      
    	} else if (preference == mBatteryBarWidth) {
			new NumberPickerDialog(preferenceScreen.getContext(),
                    batBarWidthListener,
                    batBarWidth,
                    1,
                    8,
                    R.string.battery_bar_width).show();

    	} else if (preference == mDepletedLevelOne) {
			new NumberPickerDialog(preferenceScreen.getContext(),
					depletedLevelOneListener,
                depletedLevelOne,
                0,
                depletedLevelTwo - 1,
                R.string.depleted_level_max).show();
	        
    	} else if (preference == mDepletedLevelTwo) {
			new NumberPickerDialog(preferenceScreen.getContext(),
				depletedLevelTwoListener,
	            depletedLevelTwo,
	            depletedLevelOne + 1,
	            99,
	            R.string.depleted_level_max).show();
	      
        }	
    
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }

    NumberPickerDialog.OnNumberSetListener batteryLevelOneListener =
            new NumberPickerDialog.OnNumberSetListener() {
        		public void onNumberSet(int limit) {
        			batLevelOne = (int) limit;
        			mBatteryLevelOne.getOnPreferenceChangeListener().onPreferenceChange(mBatteryLevelOne, (int) limit);
        		}
        	};      

        NumberPickerDialog.OnNumberSetListener batteryLevelTwoListener =
        	new NumberPickerDialog.OnNumberSetListener() {
            	public void onNumberSet(int limit) {
            		batLevelTwo = (int) limit;
            		mBatteryLevelTwo.getOnPreferenceChangeListener().onPreferenceChange(mBatteryLevelTwo, (int) limit);
            	}
            };      

         	
         NumberPickerDialog.OnNumberSetListener batBarWidthListener =
               	new NumberPickerDialog.OnNumberSetListener() {
                   	public void onNumberSet(int limit) {
                   		batBarWidth = (int) limit;
                   		mBatteryBarWidth.getOnPreferenceChangeListener().onPreferenceChange(mBatteryBarWidth, (int) limit);
                   	}
                 }; 

          NumberPickerDialog.OnNumberSetListener depletedLevelOneListener =
        		 new NumberPickerDialog.OnNumberSetListener() {
                 	public void onNumberSet(int limit) {
                    depletedLevelOne = (int) limit;
                    mDepletedLevelOne.getOnPreferenceChangeListener().onPreferenceChange(mDepletedLevelOne, (int) limit);
                 	}
                 };      

          NumberPickerDialog.OnNumberSetListener depletedLevelTwoListener =
               	new NumberPickerDialog.OnNumberSetListener() {
                   	public void onNumberSet(int limit) {
               		depletedLevelTwo = (int) limit;
               		mDepletedLevelTwo.getOnPreferenceChangeListener().onPreferenceChange(mDepletedLevelTwo, (int) limit);
                   	}
                };      

                 
                 
    public boolean onPreferenceChange(Preference preference, Object objValue) {
    	  
     	final String key = preference.getKey();


     	if (BATTERY_BAR_BOTTOM.equals(key)) {
        	if ((Boolean) objValue == true) {
        		mBatteryBarRight.setChecked(false);
        		
            	Intent i = new Intent();
            	i.setAction(Junk_Battery_Settings);
           	   	i.putExtra(BATTERY_BAR_RIGHT, false);
           	   	getActivity().sendBroadcast(i);
           	   	i = null;  
        	}
        	
        	Intent i = new Intent();
        	i.setAction(Junk_Battery_Settings);
       	   	i.putExtra(BATTERY_BAR_BOTTOM, (Boolean) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;    
 
        } else if (BATTERY_BAR_RIGHT.equals(key)) {
        	if ((Boolean) objValue == true) {
        		mBatteryBarBottom.setChecked(false);
        		
            	Intent i = new Intent();
            	i.setAction(Junk_Battery_Settings);
           	   	i.putExtra(BATTERY_BAR_BOTTOM, false);
           	   	getActivity().sendBroadcast(i);
           	   	i = null; 
        	}
        	
        	Intent i = new Intent();
        	i.setAction(Junk_Battery_Settings);
       	   	i.putExtra(BATTERY_BAR_RIGHT, (Boolean) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;       	   	
       	   	
        } else if (BATTERY_BAR_WIDTH.equals(key)) {
        	sharedPref = prefMgr.getSharedPreferences();
        	SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(BATTERY_BAR_WIDTH, batBarWidth);
            editor.commit();

        	Intent i = new Intent();
        	i.setAction(Junk_Battery_Settings);
       	   	i.putExtra(BATTERY_BAR_WIDTH, (Integer) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;       	   	
       	   	
        } else if (BATTERY_LEVEL_ONE.equals(key)) {
        	sharedPref = prefMgr.getSharedPreferences();
        	SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(BATTERY_LEVEL_ONE, batLevelOne);
            editor.commit();
        	
            Intent i = new Intent();
            i.setAction(Junk_Battery_Settings);
            i.putExtra(BATTERY_LEVEL_ONE, batLevelOne);
            getActivity().sendBroadcast(i);
            i = null;
        
        } else if (BATTERY_LEVEL_COLOR_ONE.equals(key)) {
        	Intent i = new Intent();
            i.setAction(Junk_Battery_Settings);
            i.putExtra(BATTERY_LEVEL_COLOR_ONE, (Integer) objValue);
            getActivity().sendBroadcast(i);
            i = null;
             
        } else if (BATTERY_LEVEL_TWO.equals(key)) {
        	sharedPref = prefMgr.getSharedPreferences();
        	SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(BATTERY_LEVEL_TWO, batLevelTwo);
            editor.commit();
        	
            Intent i = new Intent();
            i.setAction(Junk_Battery_Settings);
            i.putExtra(BATTERY_LEVEL_TWO, batLevelTwo);
            getActivity().sendBroadcast(i);
            i = null;
        
        } else if (BATTERY_LEVEL_COLOR_TWO.equals(key)) {
        	Intent i = new Intent();
            i.setAction(Junk_Battery_Settings);
            i.putExtra(BATTERY_LEVEL_COLOR_TWO, (Integer) objValue);
            getActivity().sendBroadcast(i);
            i = null;

        } else if (BATTERY_LEVEL_COLOR_THREE.equals(key)) {
        	Intent i = new Intent();
            i.setAction(Junk_Battery_Settings);
            i.putExtra(BATTERY_LEVEL_COLOR_THREE, (Integer) objValue);
            getActivity().sendBroadcast(i);
            i = null;
        } else if (DEPLETED_LEVEL_ONE.equals(key)) {
        	sharedPref = prefMgr.getSharedPreferences();
        	SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(DEPLETED_LEVEL_ONE, depletedLevelOne);
            editor.commit();
        	
            Intent i = new Intent();
            i.setAction(Junk_Battery_Settings);
            i.putExtra(DEPLETED_LEVEL_ONE, depletedLevelOne);
            getActivity().sendBroadcast(i);
            i = null;
        
        } else if (DEPLETED_LEVEL_COLOR_ONE.equals(key)) {
        	Intent i = new Intent();
            i.setAction(Junk_Battery_Settings);
            i.putExtra(DEPLETED_LEVEL_COLOR_ONE, (Integer) objValue);
            getActivity().sendBroadcast(i);
            i = null;
             
        } else if (DEPLETED_LEVEL_TWO.equals(key)) {
        	sharedPref = prefMgr.getSharedPreferences();
        	SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(DEPLETED_LEVEL_TWO, depletedLevelTwo);
            editor.commit();
        	
            Intent i = new Intent();
            i.setAction(Junk_Battery_Settings);
            i.putExtra(DEPLETED_LEVEL_TWO, depletedLevelTwo);
            getActivity().sendBroadcast(i);
            i = null;
        
        } else if (DEPLETED_LEVEL_COLOR_TWO.equals(key)) {
        	Intent i = new Intent();
            i.setAction(Junk_Battery_Settings);
            i.putExtra(DEPLETED_LEVEL_COLOR_TWO, (Integer) objValue);
            getActivity().sendBroadcast(i);
            i = null;

        } else if (DEPLETED_LEVEL_COLOR_THREE.equals(key)) {
        	Intent i = new Intent();
            i.setAction(Junk_Battery_Settings);
            i.putExtra(DEPLETED_LEVEL_COLOR_THREE, (Integer) objValue);
            getActivity().sendBroadcast(i);
            i = null;
        }
     	
     	
     	
        return true;
    }
 
    

    
    private void resendIntents() {
    	
    		Intent i = new Intent();
    		i.setAction(Junk_Battery_Settings );
   	   		i.putExtra(BATTERY_ICONS, sharedPref.getString(BATTERY_ICONS, "0"));
   	   		getActivity().sendBroadcast(i);
   	   		i = null;
    	
        	i = new Intent();
        	i.setAction(Junk_Battery_Settings );
       	   	i.putExtra(BATTERY_BAR_BOTTOM, sharedPref.getBoolean(BATTERY_BAR_BOTTOM, false));
       	   	getActivity().sendBroadcast(i);
       	   	i = null;       	   	
 
        	i = new Intent();
        	i.setAction(Junk_Battery_Settings );
       	   	i.putExtra(BATTERY_BAR_RIGHT, sharedPref.getBoolean(BATTERY_BAR_RIGHT, false));
       	   	getActivity().sendBroadcast(i);
       	   	i = null;       	   	

            i = new Intent();
        	i.setAction(Junk_Battery_Settings );
       	   	i.putExtra(BATTERY_BAR_WIDTH, sharedPref.getInt(BATTERY_BAR_WIDTH, 5));
       	   	getActivity().sendBroadcast(i);
       	   	i = null;       	   	
       	   	
            i = new Intent();
            i.setAction(Junk_Battery_Settings );
            i.putExtra(BATTERY_LEVEL_ONE, batLevelOne);
            getActivity().sendBroadcast(i);
            i = null;
        
            i = new Intent();
            i.setAction(Junk_Battery_Settings );
            i.putExtra(BATTERY_LEVEL_COLOR_ONE, sharedPref.getInt(BATTERY_LEVEL_COLOR_ONE, 0xffff0000));
            getActivity().sendBroadcast(i);
            i = null;
             
            i = new Intent();
            i.setAction(Junk_Battery_Settings );
            i.putExtra(BATTERY_LEVEL_TWO, batLevelTwo);
            getActivity().sendBroadcast(i);
            i = null;
        
            i = new Intent();
            i.setAction(Junk_Battery_Settings );
            i.putExtra(BATTERY_LEVEL_COLOR_TWO, sharedPref.getInt(BATTERY_LEVEL_COLOR_TWO, 0xffff9000));
            getActivity().sendBroadcast(i);
            i = null;

            i = new Intent();
            i.setAction(Junk_Battery_Settings );
            i.putExtra(BATTERY_LEVEL_COLOR_THREE, sharedPref.getInt(BATTERY_LEVEL_COLOR_THREE, 0xff3792b4));
            getActivity().sendBroadcast(i);
            i = null;
            
            i = new Intent();
            i.setAction(Junk_Battery_Settings );
            i.putExtra(DEPLETED_LEVEL_ONE, depletedLevelOne);
            getActivity().sendBroadcast(i);
            i = null;            

            i = new Intent();
            i.setAction(Junk_Battery_Settings);
            i.putExtra(DEPLETED_LEVEL_COLOR_ONE, sharedPref.getInt(DEPLETED_LEVEL_COLOR_ONE, 0xff800000));
            getActivity().sendBroadcast(i);
            i = null;

            i = new Intent();
            i.setAction(Junk_Battery_Settings);
            i.putExtra(DEPLETED_LEVEL_TWO, depletedLevelTwo);
            getActivity().sendBroadcast(i);
            i = null;
    
            i = new Intent();
            i.setAction(Junk_Battery_Settings);
            i.putExtra(DEPLETED_LEVEL_COLOR_TWO, sharedPref.getInt(DEPLETED_LEVEL_COLOR_TWO, 0xffba6900));
            getActivity().sendBroadcast(i);
            i = null;

            i = new Intent();
            i.setAction(Junk_Battery_Settings);
            i.putExtra(DEPLETED_LEVEL_COLOR_THREE, sharedPref.getInt(DEPLETED_LEVEL_COLOR_THREE, 0xff296c85));
            getActivity().sendBroadcast(i);
            i = null;
    }
    
} 