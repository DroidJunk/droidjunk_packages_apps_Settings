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



public class CustomToggleShowHideSettings extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener {
    

	private final String Junk_Settings = "JUNK_SETTINGS";
	private final String TOGGLES_4G_ON = "toggles_show_fourg";
	private final String TOGGLES_WIFI_ON = "toggles_show_wifi";
	private final String TOGGLES_GPS_ON = "toggles_show_gps";
	private final String TOGGLES_BLUETOOTH_ON = "toggles_show_bluetooth";
	private final String TOGGLES_SOUND_ON = "toggles_show_sound";
	private final String TOGGLES_AIRPLANE_ON = "toggles_show_airplane";
	private final String TOGGLES_BRIGHTNESS_ON = "toggles_show_brightness";
	private final String TOGGLES_ROTATE_ON = "toggles_show_rotate";
	private final String TOGGLES_SYNC_ON = "toggles_show_sync";
	private final String TOGGLES_DATA_ON = "toggles_show_data";
	
	private PreferenceManager prefMgr;
	private SharedPreferences sharedPref;
	private CheckBoxPreference mShowFourg;
	private CheckBoxPreference mShowWifi;
	private CheckBoxPreference mShowGps;
	private CheckBoxPreference mShowBluetooth;
	private CheckBoxPreference mShowSound;
	private CheckBoxPreference mShowAirplane;
	private CheckBoxPreference mShowBrightness;
	private CheckBoxPreference mShowRotate;
	private CheckBoxPreference mShowSync;
	private CheckBoxPreference mShowData;
	
	
	
    /** If there is no setting in the provider, use this. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        prefMgr = getPreferenceManager();
        prefMgr.setSharedPreferencesName("Junk_Settings");
        prefMgr.setSharedPreferencesMode(Context.MODE_WORLD_READABLE);

        addPreferencesFromResource(R.xml.custom_toggle_show_hide_settings);
      
        mShowFourg = (CheckBoxPreference) findPreference(TOGGLES_4G_ON);
        mShowFourg.setOnPreferenceChangeListener(this);
        mShowWifi = (CheckBoxPreference) findPreference(TOGGLES_WIFI_ON);
        mShowWifi.setOnPreferenceChangeListener(this);
        mShowGps = (CheckBoxPreference) findPreference(TOGGLES_GPS_ON);
        mShowGps.setOnPreferenceChangeListener(this);
        mShowBluetooth = (CheckBoxPreference) findPreference(TOGGLES_BLUETOOTH_ON);
        mShowBluetooth.setOnPreferenceChangeListener(this);
        mShowSound = (CheckBoxPreference) findPreference(TOGGLES_SOUND_ON);
        mShowSound.setOnPreferenceChangeListener(this);
        mShowAirplane = (CheckBoxPreference) findPreference(TOGGLES_AIRPLANE_ON);
        mShowAirplane.setOnPreferenceChangeListener(this);
        mShowBrightness = (CheckBoxPreference) findPreference(TOGGLES_BRIGHTNESS_ON);
        mShowBrightness.setOnPreferenceChangeListener(this);
        mShowRotate = (CheckBoxPreference) findPreference(TOGGLES_ROTATE_ON);
        mShowRotate.setOnPreferenceChangeListener(this);
        mShowSync = (CheckBoxPreference) findPreference(TOGGLES_SYNC_ON);
        mShowSync.setOnPreferenceChangeListener(this);
        mShowData = (CheckBoxPreference) findPreference(TOGGLES_DATA_ON);
        mShowData.setOnPreferenceChangeListener(this);
        
        
        
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
     	
     	if (TOGGLES_4G_ON.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Settings );
       	   	i.putExtra("ShowFourg", (Boolean) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;
        	   	
     	} else if (TOGGLES_WIFI_ON.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Settings );
       	   	i.putExtra("ShowWifi", (Boolean) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;
        	   	
    	} else if (TOGGLES_GPS_ON.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Settings );
       	   	i.putExtra("ShowGps", (Boolean) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;
       
        } else if (TOGGLES_BLUETOOTH_ON.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Settings );
       	   	i.putExtra("ShowBluetooth", (Boolean) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;
            
        } else if (TOGGLES_SOUND_ON.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Settings );
       	   	i.putExtra("ShowSound", (Boolean) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;
           
        } else if (TOGGLES_AIRPLANE_ON.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Settings );
       	   	i.putExtra("ShowAirplane", (Boolean) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;

        } else if (TOGGLES_BRIGHTNESS_ON.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Settings );
       	   	i.putExtra("ShowBrightness", (Boolean) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;

        } else if (TOGGLES_ROTATE_ON.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Settings );
       	   	i.putExtra("ShowRotate", (Boolean) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;
 
        } else if (TOGGLES_SYNC_ON.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Settings );
       	   	i.putExtra("ShowSync", (Boolean) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;

        } else if (TOGGLES_DATA_ON.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Settings );
       	   	i.putExtra("ShowData", (Boolean) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;
        	
    
        }
        
        return true;
	}
}
