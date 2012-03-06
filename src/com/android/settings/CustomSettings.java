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
import android.preference.SeekBarPreference;
import android.util.Log;



public class CustomSettings extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener {
 
	private final String Tranq_Settings = "TRANQ_SETTINGS";
	private final String ICON_COLOR_HUE = "icon_color_hue";
	private final String ICON_COLOR_HUE_INTENS = "icon_color_hue_intens";
	private final String ICON_COLOR_SHADE = "icon_color_shade";
	private final String ICON_COLOR_SHADE_INTENS = "icon_color_shade_intens";
	
	
	private PreferenceManager prefMgr;
	private SeekBarPreference mSeekBarHue;
	private SeekBarPreference mSeekBarHueIntens;
	private Preference mIconColorShade;
	private SeekBarPreference mSeekBarShadeIntens;
	
    /** If there is no setting in the provider, use this. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        prefMgr = getPreferenceManager();
        prefMgr.setSharedPreferencesName("Tranquility_Settings");
        prefMgr.setSharedPreferencesMode(Context.MODE_WORLD_READABLE);
        prefMgr.getSharedPreferences();

        addPreferencesFromResource(R.xml.custom_settings);
        

		mSeekBarHue = (SeekBarPreference) findPreference(ICON_COLOR_HUE);
		mSeekBarHue.setOnPreferenceChangeListener(this);
		mSeekBarHueIntens = (SeekBarPreference) findPreference(ICON_COLOR_HUE_INTENS);
		mSeekBarHueIntens.setOnPreferenceChangeListener(this);
        mIconColorShade = (Preference) findPreference(ICON_COLOR_SHADE);
		mIconColorShade.setOnPreferenceChangeListener(this);	
		mSeekBarShadeIntens = (SeekBarPreference) findPreference(ICON_COLOR_SHADE_INTENS);
		mSeekBarShadeIntens.setOnPreferenceChangeListener(this);

    }

    
    @Override
    public void onResume() {
        super.onResume();
          
    }

    
    @Override
    public void onPause() {
        super.onPause();

    }

    public void onProgressChanged(SeekBarPreference seekBar, int progress,
            boolean fromUser) 
        {
        }
        public void onStartTrackingTouch(SeekBarPreference seekBar)
        {
        }
        public void onStopTrackingTouch(SeekBarPreference seekBar) {
        }

    
 
    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
    	

        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }
    

    public boolean onPreferenceChange(Preference preference, Object objValue) {
    	
    	final String key = preference.getKey();
    	
     	if (ICON_COLOR_HUE.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Tranq_Settings );
       	   	i.putExtra("IconColorHue", (Integer) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;
     	} else if 
     		(ICON_COLOR_HUE_INTENS.equals(key)) {
            	Intent i = new Intent();
            	i.setAction(Tranq_Settings );
           	   	i.putExtra("IconColorHueIntens", (Integer) objValue);
           	   	getActivity().sendBroadcast(i);
           	   	i = null;
     
     	} else if 
      		(ICON_COLOR_SHADE.equals(key)) {
             	Intent i = new Intent();
             	i.setAction(Tranq_Settings );
           	   	i.putExtra("IconColorShade", (Integer) objValue);
           	   	getActivity().sendBroadcast(i);
           	   	i = null;
           	   	
     	} else if 
  			(ICON_COLOR_SHADE_INTENS.equals(key)) {
         		Intent i = new Intent();
         		i.setAction(Tranq_Settings );
       	   		i.putExtra("IconColorShadeIntens", (Integer) objValue);
       	   		getActivity().sendBroadcast(i);
       	   		i = null;
     	}
 
        return true;
    }
    
    
    
}
