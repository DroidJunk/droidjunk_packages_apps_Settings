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



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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



public class CustomQuickSettingsShowHideSettings extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener {
    

	private final String Tranq_Settings = "TRANQ_SETTINGS";
	private final String SETTINGS_CLOCK = "quick_setting_clock";
	private final String SETTINGS_METER = "quick_setting_meter";
	private final String SETTINGS_NAV = "quick_setting_nav";
	private final String SETTINGS_LOCKSCREEN = "quick_setting_lockscreen";
	private final String SETTINGS_PULLDOWN = "quick_setting_pulldown";
	private final String SETTINGS_TOGGLE = "quick_setting_toggle";
	private final String SETTINGS_QUIETTIME = "quick_setting_quiet";
	private final String SETTINGS_LED = "quick_setting_led";
	private final String SETTINGS_ICON = "quick_setting_iconcolor";
	
	private PreferenceManager prefMgr;
	private SharedPreferences sharedPref;
	private CheckBoxPreference mShowClockButton;
	private CheckBoxPreference mShowMeterButton;
	private CheckBoxPreference mShowLockscreenButton;
	private CheckBoxPreference mShowNavButton;
	private CheckBoxPreference mShowPulldownButton;
	private CheckBoxPreference mShowToggleButton;
	private CheckBoxPreference mShowQuietButton;
	private CheckBoxPreference mShowLedButton;
	private CheckBoxPreference mShowIconButton;
	
	
    /** If there is no setting in the provider, use this. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        prefMgr = getPreferenceManager();
        prefMgr.setSharedPreferencesName("Tranquility_Settings");
        prefMgr.setSharedPreferencesMode(Context.MODE_WORLD_READABLE);

        addPreferencesFromResource(R.xml.custom_quick_show_hide_settings);
      
        mShowClockButton = (CheckBoxPreference) findPreference(SETTINGS_CLOCK);
        mShowClockButton.setOnPreferenceChangeListener(this);
        mShowMeterButton = (CheckBoxPreference) findPreference(SETTINGS_METER);
        mShowMeterButton.setOnPreferenceChangeListener(this);
        mShowNavButton = (CheckBoxPreference) findPreference(SETTINGS_NAV);
        mShowNavButton.setOnPreferenceChangeListener(this);
        mShowLockscreenButton = (CheckBoxPreference) findPreference(SETTINGS_LOCKSCREEN);
        mShowLockscreenButton.setOnPreferenceChangeListener(this);
        mShowPulldownButton = (CheckBoxPreference) findPreference(SETTINGS_PULLDOWN);
        mShowPulldownButton.setOnPreferenceChangeListener(this);
        mShowToggleButton = (CheckBoxPreference) findPreference(SETTINGS_TOGGLE);
        mShowToggleButton.setOnPreferenceChangeListener(this);
        mShowQuietButton = (CheckBoxPreference) findPreference(SETTINGS_QUIETTIME);
        mShowQuietButton.setOnPreferenceChangeListener(this);
        mShowLedButton = (CheckBoxPreference) findPreference(SETTINGS_LED);
        mShowLedButton.setOnPreferenceChangeListener(this);
        mShowIconButton = (CheckBoxPreference) findPreference(SETTINGS_ICON);
        mShowIconButton.setOnPreferenceChangeListener(this);
        
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

        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }
    

    public boolean onPreferenceChange(Preference preference, Object objValue) {
  
     	final String key = preference.getKey();
     	
     	if (SETTINGS_CLOCK.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Tranq_Settings );
       	   	i.putExtra("ShowClockButton", (Boolean) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;
        	   	
     	} else if (SETTINGS_METER.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Tranq_Settings );
       	   	i.putExtra("ShowMeterButton", (Boolean) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;
        	   	
    	} else if (SETTINGS_NAV.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Tranq_Settings );
       	   	i.putExtra("ShowNavButton", (Boolean) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;
       
        } else if (SETTINGS_LOCKSCREEN.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Tranq_Settings );
       	   	i.putExtra("ShowLockscreenButton", (Boolean) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;
            
        } else if (SETTINGS_PULLDOWN.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Tranq_Settings );
       	   	i.putExtra("ShowPulldownButton", (Boolean) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;
           
        } else if (SETTINGS_TOGGLE.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Tranq_Settings );
       	   	i.putExtra("ShowToggleButton", (Boolean) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;

        } else if (SETTINGS_QUIETTIME.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Tranq_Settings );
       	   	i.putExtra("ShowQuietButton", (Boolean) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;
            
        } else if (SETTINGS_LED.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Tranq_Settings );
       	   	i.putExtra("ShowLedButton", (Boolean) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;
           
        } else if (SETTINGS_ICON.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Tranq_Settings );
       	   	i.putExtra("ShowIconButton", (Boolean) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;       	
    
        }
        
        return true;
	}
}
