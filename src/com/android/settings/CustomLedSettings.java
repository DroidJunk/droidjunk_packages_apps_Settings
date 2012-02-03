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



import java.text.DecimalFormat;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.util.Log;



public class CustomLedSettings extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener {
 

	private final String Tranq_Settings = "TRANQ_SETTINGS";
	private final String DEFAULT_LED_COLOR = "default_led_color";	
	private final String DEFAULT_LED_ON_MS = "default_led_on_ms";
	private final String DEFAULT_LED_OFF_MS = "default_led_off_ms";
	
	private PreferenceManager prefMgr;
	private SharedPreferences sharedPref;
	private Preference mDefaultLedColor;
	private Preference mDefaultLedOnMs;
	private Preference mDefaultLedOffMs;
    private static int DefaultLedOnMs;
    private static int DefaultLedOffMs;
	
	
    /** If there is no setting in the provider, use this. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefMgr = getPreferenceManager();
        prefMgr.setSharedPreferencesName("Tranquility_Settings");
        prefMgr.setSharedPreferencesMode(Context.MODE_WORLD_READABLE);
        prefMgr.getSharedPreferences();
        
        addPreferencesFromResource(R.xml.custom_led_settings);
        
        mDefaultLedColor = (Preference) findPreference(DEFAULT_LED_COLOR);
        mDefaultLedColor.setOnPreferenceChangeListener(this);
        mDefaultLedOnMs = (Preference) findPreference(DEFAULT_LED_ON_MS);
        mDefaultLedOnMs.setOnPreferenceChangeListener(this);
        mDefaultLedOffMs = (Preference) findPreference(DEFAULT_LED_OFF_MS);
        mDefaultLedOffMs.setOnPreferenceChangeListener(this);
        
		Cursor cur = Settings.NotifOptions.getDefaultLed(getActivity().getBaseContext().getContentResolver());
		sharedPref = prefMgr.getSharedPreferences();
    	SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(DEFAULT_LED_COLOR, cur.getInt(3));
        editor.putInt(DEFAULT_LED_ON_MS, cur.getInt(4));
        editor.putInt(DEFAULT_LED_OFF_MS, cur.getInt(5));
        editor.commit();

        DefaultLedOnMs = prefMgr.getSharedPreferences().getInt(DEFAULT_LED_ON_MS, 3);
        DefaultLedOffMs = prefMgr.getSharedPreferences().getInt(DEFAULT_LED_OFF_MS, 3);
		
        
    }

    private void updateDb(){
 		 ContentValues values = new ContentValues(3);
          // Write the default led option values to the database           
          values.put(Settings.NotifOptions.LED_COLOR, prefMgr.getSharedPreferences().getInt(DEFAULT_LED_COLOR, -1));
          values.put(Settings.NotifOptions.LED_ON_MS, DefaultLedOnMs);
          values.put(Settings.NotifOptions.LED_OFF_MS, DefaultLedOffMs);
          
          Settings.NotifOptions.updateDefaultLed(getActivity().getBaseContext().getContentResolver(), values);
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
       
    	if (preference == mDefaultLedOnMs) {
            new NumberPickerDialog(preference.getContext(),
                    mDefaultLedOnListener,
                    DefaultLedOnMs,
                    1,
                    50,
                    R.string.led_on_ms).show();
        } else if (preference == mDefaultLedOffMs) {
            new NumberPickerDialog(preference.getContext(),
                    mDefaultLedOffListener,
                    DefaultLedOffMs,
                    1,
                    50,
                    R.string.led_off_ms).show();
        }

        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }
    

    public boolean onPreferenceChange(Preference preference, Object objValue) {
    	
    	final String key = preference.getKey();
     	if (DEFAULT_LED_COLOR.equals(key)) {
        	sharedPref = prefMgr.getSharedPreferences();
        	SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(DEFAULT_LED_COLOR, (Integer) objValue);
            editor.commit();
            
        } else if (DEFAULT_LED_ON_MS.equals(key)) {
        	sharedPref = prefMgr.getSharedPreferences();
        	SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(DEFAULT_LED_ON_MS, DefaultLedOnMs);
            editor.commit();

        } else if (DEFAULT_LED_OFF_MS.equals(key)) {
          	sharedPref = prefMgr.getSharedPreferences();
           	SharedPreferences.Editor editor = sharedPref.edit();
           	editor.putInt(DEFAULT_LED_OFF_MS, DefaultLedOffMs);
            editor.commit();
        }
     	
        updateDb();    
        return true;
    }
    
    NumberPickerDialog.OnNumberSetListener mDefaultLedOnListener =
            new NumberPickerDialog.OnNumberSetListener() {
                public void onNumberSet(int limit) {
                    SharedPreferences.Editor editor =
                            PreferenceManager.getDefaultSharedPreferences(getActivity().getBaseContext()).edit();
                    editor.putInt(DEFAULT_LED_ON_MS, limit);
                    editor.apply();
                    DecimalFormat numf = new DecimalFormat("#.##");
                    double mTime = ((double) (limit) / (double)(10));
                    mDefaultLedOnMs.setSummary(numf.format(mTime)+ " seconds");
                    DefaultLedOnMs = limit;
                    mDefaultLedOnMs.getOnPreferenceChangeListener().onPreferenceChange(mDefaultLedOnMs, DefaultLedOnMs);
                }
        };

            
    
    NumberPickerDialog.OnNumberSetListener mDefaultLedOffListener =
                new NumberPickerDialog.OnNumberSetListener() {
                    public void onNumberSet(int limit) {
                        SharedPreferences.Editor editor =
                                PreferenceManager.getDefaultSharedPreferences(getActivity().getBaseContext()).edit();

                        editor.putInt(DEFAULT_LED_OFF_MS, limit);
                        editor.apply();
                        DecimalFormat numf = new DecimalFormat("#.##");
                        double mTime = ((double) (limit) / (double)(10));
                        mDefaultLedOffMs.setSummary(numf.format(mTime) + " seconds");
                        DefaultLedOffMs = limit;
                        mDefaultLedOffMs.getOnPreferenceChangeListener().onPreferenceChange(mDefaultLedOffMs, DefaultLedOnMs);
                    }
            };       
    

    
    
    
}
