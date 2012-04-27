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
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.preference.CheckBoxPreference;



public class CustomIconSettings extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener {
    
    private final String Junk_Icon_Settings = "JUNK_ICON_SETTINGS";
	private final String ICON_COLOR = "icon_color";

    
	private PreferenceManager prefMgr;
	private Preference mIconColor;
	
    /** If there is no setting in the provider, use this. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        prefMgr = getPreferenceManager();
        prefMgr.setSharedPreferencesName("Junk_Settings");
        prefMgr.setSharedPreferencesMode(Context.MODE_WORLD_READABLE);
        prefMgr.getSharedPreferences();
        
        addPreferencesFromResource(R.xml.custom_icon_settings);
	
        mIconColor = (Preference) findPreference(ICON_COLOR);
		mIconColor.setOnPreferenceChangeListener(this);	

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

     	if (ICON_COLOR.equals(key)) {
         	Intent i = new Intent();
         	i.setAction(Junk_Icon_Settings);
       	   	i.putExtra(ICON_COLOR, (Integer) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;
   	   	}
        return true;
    }
    
    
  
    
}
