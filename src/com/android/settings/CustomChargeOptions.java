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
import android.graphics.drawable.LevelListDrawable;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.preference.CheckBoxPreference;
import android.util.Log;



public class CustomChargeOptions extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener {
    
    private final String Junk_Battery_Settings = "JUNK_BATTERY_SETTINGS";

	private final String CHARGING_LEVEL_ONE = "charge_levels_one";
	private final String CHARGING_LEVEL_COLOR_ONE = "charge_levels_color_one";
	private final String CHARGING_LEVEL_TWO = "charge_levels_two";
	private final String CHARGING_LEVEL_COLOR_TWO = "charge_levels_color_two";
	private final String CHARGING_LEVEL_COLOR_THREE = "charge_levels_color_three";    
	


	private PreferenceManager prefMgr;
	private SharedPreferences sharedPref;
	private Preference mChargingLevelOne;
    private Preference mChargingLevelColorOne;
	private Preference mChargingLevelTwo;
    private Preference mChargingLevelColorTwo;
    private Preference mChargingLevelColorThree;
    
    public int chargeLevelOne = 10;
    public int chargeLevelTwo = 30;


	
	
	
    /** If there is no setting in the provider, use this. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        prefMgr = getPreferenceManager();
        prefMgr.setSharedPreferencesName("Junk_Settings");
        prefMgr.setSharedPreferencesMode(Context.MODE_WORLD_READABLE);
        prefMgr.getSharedPreferences();
        sharedPref = prefMgr.getSharedPreferences();
        
        addPreferencesFromResource(R.xml.custom_charging_settings);
 
		mChargingLevelOne = (Preference) findPreference(CHARGING_LEVEL_ONE);
		mChargingLevelOne.setOnPreferenceChangeListener(this);
		mChargingLevelColorOne = (Preference) findPreference(CHARGING_LEVEL_COLOR_ONE);
		mChargingLevelColorOne.setOnPreferenceChangeListener(this);
		mChargingLevelTwo = (Preference) findPreference(CHARGING_LEVEL_TWO);
		mChargingLevelTwo.setOnPreferenceChangeListener(this);
		mChargingLevelColorTwo = (Preference) findPreference(CHARGING_LEVEL_COLOR_TWO);
		mChargingLevelColorTwo.setOnPreferenceChangeListener(this);
		mChargingLevelColorThree = (Preference) findPreference(CHARGING_LEVEL_COLOR_THREE);
		mChargingLevelColorThree.setOnPreferenceChangeListener(this);
        
        resendIntents();
    }

    
    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
    	
    	if (preference == mChargingLevelOne) {
			new NumberPickerDialog(preferenceScreen.getContext(),
					chargingLevelOneListener,
                    chargeLevelOne,
                    0,
                    chargeLevelTwo - 1,
                    R.string.charging_level_max).show();
 		
    	} else if (preference == mChargingLevelTwo) {
			new NumberPickerDialog(preferenceScreen.getContext(),
					chargingLevelTwoListener,
                    chargeLevelTwo,
                    chargeLevelOne + 1,
                    99,
                    R.string.charging_level_max).show();
    	}
   
        return super.onPreferenceTreeClick(preferenceScreen, preference);

    }


    NumberPickerDialog.OnNumberSetListener chargingLevelOneListener =
            new NumberPickerDialog.OnNumberSetListener() {
        		public void onNumberSet(int limit) {
        			chargeLevelOne = (int) limit;
        			mChargingLevelOne.getOnPreferenceChangeListener().onPreferenceChange(mChargingLevelOne, (int) limit);
        		}
        	};      

        NumberPickerDialog.OnNumberSetListener chargingLevelTwoListener =
        	new NumberPickerDialog.OnNumberSetListener() {
            	public void onNumberSet(int limit) {
            		chargeLevelTwo = (int) limit;
            		mChargingLevelTwo.getOnPreferenceChangeListener().onPreferenceChange(mChargingLevelTwo, (int) limit);
            	}
            };      

                	

             
                 
                 
    public boolean onPreferenceChange(Preference preference, Object objValue) {
    	  
     	final String key = preference.getKey();


        if (CHARGING_LEVEL_ONE.equals(key)) {
        	sharedPref = prefMgr.getSharedPreferences();
        	SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(CHARGING_LEVEL_ONE, chargeLevelOne);
            editor.commit();
        	
            Intent i = new Intent();
            i.setAction(Junk_Battery_Settings);
            i.putExtra(CHARGING_LEVEL_ONE, chargeLevelOne);
            getActivity().sendBroadcast(i);
            i = null;
        
        } else if (CHARGING_LEVEL_COLOR_ONE.equals(key)) {
        	Intent i = new Intent();
            i.setAction(Junk_Battery_Settings);
            i.putExtra(CHARGING_LEVEL_COLOR_ONE, (Integer) objValue);
            getActivity().sendBroadcast(i);
            i = null;
             
        } else if (CHARGING_LEVEL_TWO.equals(key)) {
        	sharedPref = prefMgr.getSharedPreferences();
        	SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(CHARGING_LEVEL_TWO, chargeLevelTwo);
            editor.commit();
        	
            Intent i = new Intent();
            i.setAction(Junk_Battery_Settings);
            i.putExtra(CHARGING_LEVEL_TWO, chargeLevelTwo);
            getActivity().sendBroadcast(i);
            i = null;
        
        } else if (CHARGING_LEVEL_COLOR_TWO.equals(key)) {
        	Intent i = new Intent();
            i.setAction(Junk_Battery_Settings);
            i.putExtra(CHARGING_LEVEL_COLOR_TWO, (Integer) objValue);
            getActivity().sendBroadcast(i);
            i = null;

        } else if (CHARGING_LEVEL_COLOR_THREE.equals(key)) {
        	Intent i = new Intent();
            i.setAction(Junk_Battery_Settings);
            i.putExtra(CHARGING_LEVEL_COLOR_THREE, (Integer) objValue);
            getActivity().sendBroadcast(i);
            i = null;
        }
        return true;
    }
 
    

    
    private void resendIntents() {
    	
            Intent i = new Intent();
            i.setAction(Junk_Battery_Settings );
            i.putExtra(CHARGING_LEVEL_ONE, chargeLevelOne);
            getActivity().sendBroadcast(i);
            i = null;
        
            i = new Intent();
            i.setAction(Junk_Battery_Settings );
            i.putExtra(CHARGING_LEVEL_COLOR_ONE, sharedPref.getInt(CHARGING_LEVEL_COLOR_ONE, 0xffff0000));
            getActivity().sendBroadcast(i);
            i = null;
             
            i = new Intent();
            i.setAction(Junk_Battery_Settings );
            i.putExtra(CHARGING_LEVEL_TWO, chargeLevelTwo);
            getActivity().sendBroadcast(i);
            i = null;
        
            i = new Intent();
            i.setAction(Junk_Battery_Settings );
            i.putExtra(CHARGING_LEVEL_COLOR_TWO, sharedPref.getInt(CHARGING_LEVEL_COLOR_TWO, 0xffff9000));
            getActivity().sendBroadcast(i);
            i = null;

            i = new Intent();
            i.setAction(Junk_Battery_Settings );
            i.putExtra(CHARGING_LEVEL_COLOR_THREE, sharedPref.getInt(CHARGING_LEVEL_COLOR_THREE, 0xff3792b4));
            getActivity().sendBroadcast(i);
            i = null;
    	
    }
    
} 