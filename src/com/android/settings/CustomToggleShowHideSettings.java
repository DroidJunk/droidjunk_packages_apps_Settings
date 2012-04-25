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
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;



public class CustomToggleShowHideSettings extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener {
    

	private final String Junk_Toggle_Settings = "JUNK_TOGGLE_SETTINGS";
	private final String TOGGLES_TORCH_ON = "toggles_show_torch";
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
	private SwitchPreference mShowTorch;
	private SwitchPreference mShowFourg;
	private SwitchPreference mShowWifi;
	private SwitchPreference mShowGps;
	private SwitchPreference mShowBluetooth;
	private SwitchPreference mShowSound;
	private SwitchPreference mShowAirplane;
	private SwitchPreference mShowBrightness;
	private SwitchPreference mShowRotate;
	private SwitchPreference mShowSync;
	private SwitchPreference mShowData;
	
	
	
    /** If there is no setting in the provider, use this. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        prefMgr = getPreferenceManager();
        prefMgr.setSharedPreferencesName("Junk_Settings");
        prefMgr.setSharedPreferencesMode(Context.MODE_WORLD_READABLE);

        addPreferencesFromResource(R.xml.custom_toggle_show_hide_settings);

        mShowTorch = (SwitchPreference) findPreference(TOGGLES_TORCH_ON);
        mShowTorch.setOnPreferenceChangeListener(this);
        mShowFourg = (SwitchPreference) findPreference(TOGGLES_4G_ON);
        mShowFourg.setOnPreferenceChangeListener(this);
        mShowWifi = (SwitchPreference) findPreference(TOGGLES_WIFI_ON);
        mShowWifi.setOnPreferenceChangeListener(this);
        mShowGps = (SwitchPreference) findPreference(TOGGLES_GPS_ON);
        mShowGps.setOnPreferenceChangeListener(this);
        mShowBluetooth = (SwitchPreference) findPreference(TOGGLES_BLUETOOTH_ON);
        mShowBluetooth.setOnPreferenceChangeListener(this);
        mShowSound = (SwitchPreference) findPreference(TOGGLES_SOUND_ON);
        mShowSound.setOnPreferenceChangeListener(this);
        mShowAirplane = (SwitchPreference) findPreference(TOGGLES_AIRPLANE_ON);
        mShowAirplane.setOnPreferenceChangeListener(this);
        mShowBrightness = (SwitchPreference) findPreference(TOGGLES_BRIGHTNESS_ON);
        mShowBrightness.setOnPreferenceChangeListener(this);
        mShowRotate = (SwitchPreference) findPreference(TOGGLES_ROTATE_ON);
        mShowRotate.setOnPreferenceChangeListener(this);
        mShowSync = (SwitchPreference) findPreference(TOGGLES_SYNC_ON);
        mShowSync.setOnPreferenceChangeListener(this);
        mShowData = (SwitchPreference) findPreference(TOGGLES_DATA_ON);
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
     	
     	if (TOGGLES_TORCH_ON.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Toggle_Settings);
       	   	i.putExtra(TOGGLES_TORCH_ON, (Boolean) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;
        	   	
     	} else if (TOGGLES_4G_ON.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Toggle_Settings);
       	   	i.putExtra(TOGGLES_4G_ON, (Boolean) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;
        	   	
     	} else if (TOGGLES_WIFI_ON.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Toggle_Settings);
       	   	i.putExtra(TOGGLES_WIFI_ON, (Boolean) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;
        	   	
    	} else if (TOGGLES_GPS_ON.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Toggle_Settings);
       	   	i.putExtra(TOGGLES_GPS_ON, (Boolean) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;
       
        } else if (TOGGLES_BLUETOOTH_ON.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Toggle_Settings);
       	   	i.putExtra(TOGGLES_BLUETOOTH_ON, (Boolean) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;
            
        } else if (TOGGLES_SOUND_ON.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Toggle_Settings);
       	   	i.putExtra(TOGGLES_SOUND_ON, (Boolean) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;
           
        } else if (TOGGLES_AIRPLANE_ON.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Toggle_Settings);
       	   	i.putExtra(TOGGLES_AIRPLANE_ON, (Boolean) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;

        } else if (TOGGLES_BRIGHTNESS_ON.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Toggle_Settings);
       	   	i.putExtra(TOGGLES_BRIGHTNESS_ON, (Boolean) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;

        } else if (TOGGLES_ROTATE_ON.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Toggle_Settings);
       	   	i.putExtra(TOGGLES_ROTATE_ON, (Boolean) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;
 
        } else if (TOGGLES_SYNC_ON.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Toggle_Settings);
       	   	i.putExtra(TOGGLES_SYNC_ON, (Boolean) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;

        } else if (TOGGLES_DATA_ON.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Toggle_Settings);
       	   	i.putExtra(TOGGLES_DATA_ON, (Boolean) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;
        	
    
        }
        
        return true;
	}
}
