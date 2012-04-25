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
    
	private final String Junk_Settings = "JUNK_SETTINGS";
	private final String ICON_COLOR_ON = "color_icons";
	private final String ICON_COLOR = "icon_color";
	private final String ICON_COLOR_APPLY = "color_icons_apply";
	private final String CLOCK_COLOR = "clock_color";
    private final String PREF_NAV_COLOR = "nav_button_color";	
    
	private PreferenceManager prefMgr;
	private SharedPreferences sharedPref;
	private CheckBoxPreference mIconColorOn;
	private Preference mIconColor;
	private CheckBoxPreference mIconColorApply;
	private boolean iconColorApply = false;
	private int iconColor = 0;
    private Preference mClockColor;
    
	
    /** If there is no setting in the provider, use this. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        prefMgr = getPreferenceManager();
        prefMgr.setSharedPreferencesName("Junk_Settings");
        prefMgr.setSharedPreferencesMode(Context.MODE_WORLD_READABLE);
        prefMgr.getSharedPreferences();
        sharedPref = prefMgr.getSharedPreferences();
        
        addPreferencesFromResource(R.xml.custom_icon_settings);

		mIconColorOn = (CheckBoxPreference) findPreference(ICON_COLOR_ON);
		mIconColorOn.setOnPreferenceChangeListener(this);	
        mIconColor = (Preference) findPreference(ICON_COLOR);
		mIconColor.setOnPreferenceChangeListener(this);	
		mIconColorApply = (CheckBoxPreference) findPreference(ICON_COLOR_APPLY);
		mIconColorApply.setOnPreferenceChangeListener(this);	
		iconColor = prefMgr.getSharedPreferences().getInt(ICON_COLOR, 0);
		iconColorApply = prefMgr.getSharedPreferences().getBoolean(ICON_COLOR_APPLY, false);
        mClockColor = (Preference) findPreference(CLOCK_COLOR);
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

     	if (ICON_COLOR_ON.equals(key)) {
         	Intent i = new Intent();
         	i.setAction(Junk_Settings);
       	   	i.putExtra("IconColorOn", (Boolean) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;
     	
     	} else if (ICON_COLOR.equals(key)) {
     		iconColor = (Integer) objValue;
         	Intent i = new Intent();
         	i.setAction(Junk_Settings);
       	   	i.putExtra("IconColor", (Integer) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;
       	   	
       	   	if (iconColorApply) {
       	   		SharedPreferences.Editor editor = sharedPref.edit();
       			editor.putInt(CLOCK_COLOR, iconColor);
       			editor.putInt(PREF_NAV_COLOR, iconColor);
       			editor.commit();

       			i = new Intent();
         		i.setAction(Junk_Settings);
       	   		i.putExtra("IconColorApply", true);
       	   		i.putExtra("IconColor", iconColor);
       	   		getActivity().sendBroadcast(i);
       	   		i = null;
       	   	}
       	   	
   	   		
     	} else if (ICON_COLOR_APPLY.equals(key)) {
   	   		SharedPreferences.Editor editor = sharedPref.edit();
   			editor.putInt(CLOCK_COLOR, iconColor);
   			editor.putInt(PREF_NAV_COLOR, iconColor);
   			editor.commit();

       	   	iconColorApply = (Boolean) objValue;
       	   	if (iconColorApply) {
       	   		Intent i = new Intent();
       	   		i.setAction(Junk_Settings);
       	   		i.putExtra("IconColorApply", (Boolean) objValue);
       	   		i.putExtra("IconColor", iconColor);
       	   		getActivity().sendBroadcast(i);
       	   		i = null;
       	   	}
     	}
 
    

        return true;
    }
    
    
  
    
}
