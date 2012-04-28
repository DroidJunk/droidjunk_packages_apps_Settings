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
	private final String BATTERY_DEPLETED_COLOR = "battery_depleted_color";
	private final String BATTERY_LEVEL_ONE = "battery_levels_one";
	private final String BATTERY_LEVEL_COLOR_ONE = "battery_levels_color_one";
	private final String BATTERY_LEVEL_TWO = "battery_levels_two";
	private final String BATTERY_LEVEL_COLOR_TWO = "battery_levels_color_two";
	private final String BATTERY_LEVEL_THREE = "battery_levels_three";
	private final String BATTERY_LEVEL_COLOR_THREE = "battery_levels_color_three";    
	
	
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
	private Preference mBatteryDepletedColor;
	private Preference mBatteryLevelOne;
    private Preference mBatteryLevelColorOne;
	private Preference mBatteryLevelTwo;
    private Preference mBatteryLevelColorTwo;
	private Preference mBatteryLevelThree;
    private Preference mBatteryLevelColorThree;
    
    public int batBarWidth = 5;
    public int batLevelOne = 10;
    public int batLevelTwo = 30;
    public int batLevelThree = 70;

	
	
	
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
    		mBatteryLevelThree = (Preference) findPreference(BATTERY_LEVEL_THREE);
    		mBatteryLevelThree.setOnPreferenceChangeListener(this);
    		mBatteryLevelColorThree = (Preference) findPreference(BATTERY_LEVEL_COLOR_THREE);
    		mBatteryLevelColorThree.setOnPreferenceChangeListener(this);
    		
    		batLevelOne = sharedPref.getInt(BATTERY_LEVEL_ONE, 10);
    		batLevelTwo = sharedPref.getInt(BATTERY_LEVEL_TWO, 30);
    		batLevelThree = sharedPref.getInt(BATTERY_LEVEL_THREE, 70);
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
    		mBatteryLevelThree = (Preference) findPreference(BATTERY_LEVEL_THREE);
    		mBatteryLevelThree.setOnPreferenceChangeListener(this);
    		mBatteryLevelColorThree = (Preference) findPreference(BATTERY_LEVEL_COLOR_THREE);
    		mBatteryLevelColorThree.setOnPreferenceChangeListener(this);
    		
    		batLevelOne = sharedPref.getInt(BATTERY_LEVEL_ONE, 10);
    		batLevelTwo = sharedPref.getInt(BATTERY_LEVEL_TWO, 30);
    		batLevelThree = sharedPref.getInt(BATTERY_LEVEL_THREE, 70);
    		batBarWidth = sharedPref.getInt(BATTERY_BAR_WIDTH, 5);
    		
            mBatteryBarBottom = (CheckBoxPreference) findPreference(BATTERY_BAR_BOTTOM);
    		mBatteryBarBottom.setOnPreferenceChangeListener(this);
            mBatteryBarRight = (CheckBoxPreference) findPreference(BATTERY_BAR_RIGHT);
    		mBatteryBarRight.setOnPreferenceChangeListener(this);
    		mBatteryDepletedColor = (Preference) findPreference(BATTERY_DEPLETED_COLOR);
    		mBatteryDepletedColor.setOnPreferenceChangeListener(this);
            mBatteryBarWidth = (Preference) findPreference(BATTERY_BAR_WIDTH);
    		mBatteryBarWidth.setOnPreferenceChangeListener(this);
            break;

        case BATTERY_CIRCLE:
        	addPreferencesFromResource(R.xml.custom_bat_stock_settings);
    		mBatteryLevelOne = (Preference) findPreference(BATTERY_LEVEL_ONE);
    		mBatteryLevelOne.setOnPreferenceChangeListener(this);
    		mBatteryLevelColorOne = (Preference) findPreference(BATTERY_LEVEL_COLOR_ONE);
    		mBatteryLevelColorOne.setOnPreferenceChangeListener(this);
    		mBatteryLevelTwo = (Preference) findPreference(BATTERY_LEVEL_TWO);
    		mBatteryLevelTwo.setOnPreferenceChangeListener(this);
    		mBatteryLevelColorTwo = (Preference) findPreference(BATTERY_LEVEL_COLOR_TWO);
    		mBatteryLevelColorTwo.setOnPreferenceChangeListener(this);
    		mBatteryLevelThree = (Preference) findPreference(BATTERY_LEVEL_THREE);
    		mBatteryLevelThree.setOnPreferenceChangeListener(this);
    		mBatteryLevelColorThree = (Preference) findPreference(BATTERY_LEVEL_COLOR_THREE);
    		mBatteryLevelColorThree.setOnPreferenceChangeListener(this);
    		
    		mBatteryDepletedColor = (Preference) findPreference(BATTERY_DEPLETED_COLOR);
    		mBatteryDepletedColor.setOnPreferenceChangeListener(this);
            break;

        case BATTERY_PIE:
        	addPreferencesFromResource(R.xml.custom_bat_stock_settings);
    		mBatteryLevelOne = (Preference) findPreference(BATTERY_LEVEL_ONE);
    		mBatteryLevelOne.setOnPreferenceChangeListener(this);
    		mBatteryLevelColorOne = (Preference) findPreference(BATTERY_LEVEL_COLOR_ONE);
    		mBatteryLevelColorOne.setOnPreferenceChangeListener(this);
    		mBatteryLevelTwo = (Preference) findPreference(BATTERY_LEVEL_TWO);
    		mBatteryLevelTwo.setOnPreferenceChangeListener(this);
    		mBatteryLevelColorTwo = (Preference) findPreference(BATTERY_LEVEL_COLOR_TWO);
    		mBatteryLevelColorTwo.setOnPreferenceChangeListener(this);
    		mBatteryLevelThree = (Preference) findPreference(BATTERY_LEVEL_THREE);
    		mBatteryLevelThree.setOnPreferenceChangeListener(this);
    		mBatteryLevelColorThree = (Preference) findPreference(BATTERY_LEVEL_COLOR_THREE);
    		mBatteryLevelColorThree.setOnPreferenceChangeListener(this);
    		
    		mBatteryDepletedColor = (Preference) findPreference(BATTERY_DEPLETED_COLOR);
    		mBatteryDepletedColor.setOnPreferenceChangeListener(this);
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
                    batLevelThree - 1,
                    R.string.battery_level_max).show();
      
    	} else if (preference == mBatteryLevelThree) {
			new NumberPickerDialog(preferenceScreen.getContext(),
                    batteryLevelThreeListener,
                    batLevelThree,
                    batLevelTwo + 1,
                    99,
                    R.string.battery_level_max).show();
        
    	} else if (preference == mBatteryBarWidth) {
			new NumberPickerDialog(preferenceScreen.getContext(),
                    batBarWidthListener,
                    batBarWidth,
                    1,
                    8,
                    R.string.battery_bar_width).show();
			
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

        NumberPickerDialog.OnNumberSetListener batteryLevelThreeListener =
        	new NumberPickerDialog.OnNumberSetListener() {
            	public void onNumberSet(int limit) {
            		batLevelThree = (int) limit;
            		mBatteryLevelThree.getOnPreferenceChangeListener().onPreferenceChange(mBatteryLevelThree, (int) limit);
            	}
            };      
                	
         NumberPickerDialog.OnNumberSetListener batBarWidthListener =
               	new NumberPickerDialog.OnNumberSetListener() {
                   	public void onNumberSet(int limit) {
                   		batBarWidth = (int) limit;
                   		mBatteryBarWidth.getOnPreferenceChangeListener().onPreferenceChange(mBatteryBarWidth, (int) limit);
                   	}
                 }; 

             
                 
                 
    public boolean onPreferenceChange(Preference preference, Object objValue) {
    	  
     	final String key = preference.getKey();


     	if (BATTERY_BAR_BOTTOM.equals(key)) {
        	if ((Boolean) objValue == true) {
        		mBatteryBarRight.setChecked(false);
        	}
        	
        	Intent i = new Intent();
        	i.setAction(Junk_Battery_Settings);
       	   	i.putExtra(BATTERY_BAR_BOTTOM, (Boolean) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;       	   	
 
        } else if (BATTERY_BAR_RIGHT.equals(key)) {
        	if ((Boolean) objValue == true) {
        		mBatteryBarBottom.setChecked(false);
        	}
        	
        	Intent i = new Intent();
        	i.setAction(Junk_Battery_Settings);
       	   	i.putExtra(BATTERY_BAR_RIGHT, (Boolean) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;       	   	

        } else if (BATTERY_DEPLETED_COLOR.equals(key)) {
        	Intent i = new Intent();
            i.setAction(Junk_Battery_Settings);
            i.putExtra(BATTERY_DEPLETED_COLOR, (Integer) objValue);
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

        } else if (BATTERY_LEVEL_THREE.equals(key)) {
        	sharedPref = prefMgr.getSharedPreferences();
        	SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(BATTERY_LEVEL_THREE, batLevelThree);
            editor.commit();
        	
            Intent i = new Intent();
            i.setAction(Junk_Battery_Settings);
            i.putExtra(BATTERY_LEVEL_THREE, batLevelThree);
            getActivity().sendBroadcast(i);
            i = null;
        
        } else if (BATTERY_LEVEL_COLOR_THREE.equals(key)) {
        	Intent i = new Intent();
            i.setAction(Junk_Battery_Settings);
            i.putExtra(BATTERY_LEVEL_COLOR_THREE, (Integer) objValue);
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
       	   	i.putExtra(BATTERY_DEPLETED_COLOR, sharedPref.getInt(BATTERY_DEPLETED_COLOR, 0xffababab));
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
            i.putExtra(BATTERY_LEVEL_THREE, batLevelThree);
            getActivity().sendBroadcast(i);
            i = null;
        
            i = new Intent();
            i.setAction(Junk_Battery_Settings );
            i.putExtra(BATTERY_LEVEL_COLOR_THREE, sharedPref.getInt(BATTERY_LEVEL_COLOR_THREE, 0xff3792b4));
            getActivity().sendBroadcast(i);
            i = null;
    	
    }
    
} 