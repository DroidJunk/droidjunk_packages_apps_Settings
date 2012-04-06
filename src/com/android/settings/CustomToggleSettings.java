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



public class CustomToggleSettings extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener {
    

	private final String Junk_Settings = "JUNK_SETTINGS";
	private final String TOGGLES_ON = "toggles_show_toggles";
	private final String TOGGLES_TOP = "toggles_top";
	private final String QUICK_SETTINGS_ON = "toggles_show_quicksettings";
	private final String QUICK_SETTINGS_BOTTOM = "quicksettings_bottom";
	private final String TOGGLE_COLOR = "toggle_color";
	private final String TOGGLE_CUSTOM_ICON_COLORS = "toggles_custom_icon_colors";
	private final String TOGGLE_ICON_ON_COLOR = "toggles_icon_on_color";
	private final String TOGGLE_ICON_INTER_COLOR = "toggles_icon_inter_color";
	private final String TOGGLE_ICON_OFF_COLOR = "toggles_icon_off_color";
	private final String TOGGLE_SHOW_INDICATOR = "toggle_show_indicator";
	private final String TOGGLE_IND_ON_COLOR = "toggle_ind_on_color";
	private final String TOGGLE_IND_OFF_COLOR = "toggle_ind_off_color";
	private final String TOGGLE_SHOW_TEXT = "toggle_show_text";
	private final String TOGGLE_TEXT_ON_COLOR = "toggle_text_on_color";
	private final String TOGGLE_TEXT_OFF_COLOR = "toggle_text_off_color";
	private final String TOGGLE_SHOW_DIVIDER = "toggle_show_divider";
	private final String TOGGLE_DIVIDER_COLOR = "toggle_divider_color";

	private PreferenceManager prefMgr;
	private SharedPreferences sharedPref;
	private CheckBoxPreference mShowToggles;
	private CheckBoxPreference mShowQuickSettings;
	private CheckBoxPreference mTogglesTop;
	private CheckBoxPreference mQuickSettingsBottom;
	private Preference mToggleColor;
	private CheckBoxPreference mToggleCustomIconColors;
	private Preference mToggleIconOnColor;
	private Preference mToggleIconInterColor;
	private Preference mToggleIconOffColor;
	private CheckBoxPreference mShowIndicator;
    private Preference mToggleIndOnColor;
    private Preference mToggleIndOffColor;
    private CheckBoxPreference mToggleShowText;
    private Preference mToggleTextOnColor;
    private Preference mToggleTextOffColor;
    private CheckBoxPreference mToggleShowDivider;
    private Preference mToggleDividerColor;

    public int mSize = 17;	

    /** If there is no setting in the provider, use this. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        prefMgr = getPreferenceManager();
        prefMgr.setSharedPreferencesName("Junk_Settings");
        prefMgr.setSharedPreferencesMode(Context.MODE_WORLD_READABLE);

        addPreferencesFromResource(R.xml.custom_toggle_settings);
      
    	mShowToggles = (CheckBoxPreference) findPreference(TOGGLES_ON);
    	mShowToggles.setOnPreferenceChangeListener(this);
    	mTogglesTop = (CheckBoxPreference) findPreference(TOGGLES_TOP);
    	mTogglesTop.setOnPreferenceChangeListener(this);
    	mShowQuickSettings = (CheckBoxPreference) findPreference(QUICK_SETTINGS_ON);
    	mShowQuickSettings.setOnPreferenceChangeListener(this);
    	mQuickSettingsBottom = (CheckBoxPreference) findPreference(QUICK_SETTINGS_BOTTOM);
    	mQuickSettingsBottom.setOnPreferenceChangeListener(this);
    	mToggleColor = (Preference) findPreference(TOGGLE_COLOR);
    	mToggleColor.setOnPreferenceChangeListener(this);
    	mToggleCustomIconColors = (CheckBoxPreference) findPreference(TOGGLE_CUSTOM_ICON_COLORS);
    	mToggleCustomIconColors.setOnPreferenceChangeListener(this);
    	mToggleIconOnColor = (Preference) findPreference(TOGGLE_ICON_ON_COLOR);
    	mToggleIconOnColor.setOnPreferenceChangeListener(this);
    	mToggleIconInterColor = (Preference) findPreference(TOGGLE_ICON_INTER_COLOR);
    	mToggleIconInterColor.setOnPreferenceChangeListener(this);
    	mToggleIconOffColor = (Preference) findPreference(TOGGLE_ICON_OFF_COLOR);
    	mToggleIconOffColor.setOnPreferenceChangeListener(this);
    	mShowIndicator = (CheckBoxPreference) findPreference(TOGGLE_SHOW_INDICATOR);
    	mShowIndicator.setOnPreferenceChangeListener(this);
        mToggleIndOnColor = (Preference) findPreference(TOGGLE_IND_ON_COLOR);
        mToggleIndOnColor.setOnPreferenceChangeListener(this);
        mToggleIndOffColor = (Preference) findPreference(TOGGLE_IND_OFF_COLOR);
        mToggleIndOffColor.setOnPreferenceChangeListener(this);
        mToggleShowText = (CheckBoxPreference) findPreference(TOGGLE_SHOW_TEXT);
        mToggleShowText.setOnPreferenceChangeListener(this);
        mToggleTextOnColor = (Preference) findPreference(TOGGLE_TEXT_ON_COLOR);
        mToggleTextOnColor.setOnPreferenceChangeListener(this);
        mToggleTextOffColor = (Preference) findPreference(TOGGLE_TEXT_OFF_COLOR);
        mToggleTextOffColor.setOnPreferenceChangeListener(this);
        mToggleShowDivider = (CheckBoxPreference) findPreference(TOGGLE_SHOW_DIVIDER);
        mToggleShowDivider.setOnPreferenceChangeListener(this);
        mToggleDividerColor = (Preference) findPreference(TOGGLE_DIVIDER_COLOR);
        mToggleDividerColor.setOnPreferenceChangeListener(this);
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
     	
     	if (TOGGLES_ON.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Settings );
       	   	i.putExtra("TogglesOn", (Boolean) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;
       	   	
        	i = new Intent();
            i.setAction(Junk_Settings );
            i.putExtra("UpdateToggles", true);
            getActivity().sendBroadcast(i);
            i = null;

     	} else if (QUICK_SETTINGS_ON.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Settings );
       	   	i.putExtra("SettingsOn", (Boolean) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;
       	   	
        	i = new Intent();
            i.setAction(Junk_Settings );
            i.putExtra("UpdateSettings", true);
            getActivity().sendBroadcast(i);
            i = null;

     	} else if (QUICK_SETTINGS_BOTTOM.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Settings );
       	   	i.putExtra("SettingsBottom", (Boolean) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;
       	   	
        	i = new Intent();
            i.setAction(Junk_Settings );
            i.putExtra("UpdateSettings", true);
            getActivity().sendBroadcast(i);
            i = null;
             
            
     	} else if (TOGGLES_TOP.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Settings );
       	   	i.putExtra("TogglesTop", (Boolean) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;
       	   	
        	i = new Intent();
            i.setAction(Junk_Settings );
            i.putExtra("UpdateToggles", true);
            getActivity().sendBroadcast(i);
            i = null;

    	} else if (TOGGLE_COLOR.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Settings );
       	   	i.putExtra("ToggleColor", (Integer) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;

    	} else if (TOGGLE_CUSTOM_ICON_COLORS.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Settings );
       	   	i.putExtra("ToggleCustomIconColors", (Boolean) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;
       
    	} else if (TOGGLE_ICON_ON_COLOR.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Settings );
       	   	i.putExtra("ToggleIconOnColor", (Integer) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;

    	} else if (TOGGLE_ICON_INTER_COLOR.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Settings );
       	   	i.putExtra("ToggleIconInterColor", (Integer) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;
       	   	
    	} else if (TOGGLE_ICON_OFF_COLOR.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Settings );
       	   	i.putExtra("ToggleIconOffColor", (Integer) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;       	   	
       	   	
        } else if (TOGGLE_SHOW_INDICATOR.equals(key)) {
        	Intent i = new Intent();
            i.setAction(Junk_Settings );
            i.putExtra("ToggleShowIndicator", (Boolean) objValue);
            if ((Boolean) objValue) {
            	i.putExtra("ToggleIndOnColor", (Integer) prefMgr.getSharedPreferences().getInt("toggle_ind_on_color", 0xffffbb33));
            	i.putExtra("ToggleIndOffColor", (Integer) prefMgr.getSharedPreferences().getInt("toggle_ind_off_color", 0xffba7b00));
            	} else {
            		i.putExtra("ToggleIndOnColor",0);
            		i.putExtra("ToggleIndOffColor",0);
            }
            getActivity().sendBroadcast(i);
            i = null;
            
        	i = new Intent();
            i.setAction(Junk_Settings );
            i.putExtra("UpdateToggles", true);
            getActivity().sendBroadcast(i);
            i = null;
            
            
        } else if (TOGGLE_IND_ON_COLOR.equals(key)) {
        	Intent i = new Intent();
            i.setAction(Junk_Settings );
            i.putExtra("ToggleIndOnColor", (Integer) objValue);
            getActivity().sendBroadcast(i);
            i = null;
            
        	i = new Intent();
            i.setAction(Junk_Settings );
            i.putExtra("UpdateToggles", true);
            getActivity().sendBroadcast(i);
            i = null;
            
        } else if (TOGGLE_IND_OFF_COLOR.equals(key)) {
        	Intent i = new Intent();
            i.setAction(Junk_Settings );
            i.putExtra("ToggleIndOffColor", (Integer) objValue);
             getActivity().sendBroadcast(i);
            i = null;
            
        	i = new Intent();
            i.setAction(Junk_Settings );
            i.putExtra("UpdateToggles", true);
            getActivity().sendBroadcast(i);
            i = null;

        } else if (TOGGLE_SHOW_TEXT.equals(key)) {
        	Intent i = new Intent();
            i.setAction(Junk_Settings );
            i.putExtra("ToggleShowText", (Boolean) objValue);
            if ((Boolean) objValue) {
            	i.putExtra("ToggleTextOnColor", (Integer) prefMgr.getSharedPreferences().getInt("toggle_text_on_color", 0xffffbb33));
            	i.putExtra("ToggleTextOffColor", (Integer) prefMgr.getSharedPreferences().getInt("toggle_text_off_color", 0xffba7b00));
            	} else {
            		i.putExtra("ToggleTextOnColor",0);
            		i.putExtra("ToggleTextOffColor",0);
            }
            getActivity().sendBroadcast(i);
            i = null;
            
        	i = new Intent();
            i.setAction(Junk_Settings );
            i.putExtra("UpdateToggles", true);
            getActivity().sendBroadcast(i);
            i = null;

        } else if (TOGGLE_TEXT_ON_COLOR.equals(key)) {
        	Intent i = new Intent();
            i.setAction(Junk_Settings );
            i.putExtra("ToggleTextOnColor", (Integer) objValue);
            getActivity().sendBroadcast(i);
            i = null;        
            
        	i = new Intent();
            i.setAction(Junk_Settings );
            i.putExtra("UpdateToggles", true);
            getActivity().sendBroadcast(i);
            i = null;

        } else if (TOGGLE_TEXT_OFF_COLOR.equals(key)) {
        	Intent i = new Intent();
            i.setAction(Junk_Settings );
            i.putExtra("ToggleTextOffColor", (Integer) objValue);
            getActivity().sendBroadcast(i);
            i = null;     
            
        	i = new Intent();
            i.setAction(Junk_Settings );
            i.putExtra("UpdateToggles", true);
            getActivity().sendBroadcast(i);
            i = null;
        
 
        } else if (TOGGLE_SHOW_DIVIDER.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Settings );
        	i.putExtra("ToggleShowDivider", (Boolean) objValue);
            if ((Boolean) objValue) {
            	i.putExtra("ToggleDividerColor", (Integer) prefMgr.getSharedPreferences().getInt("toggle_divider_color", 0xff535252));
            	} else {
            		i.putExtra("ToggleDividerColor",0);
            }
        	getActivity().sendBroadcast(i);
        	i = null;      
        	
        	i = new Intent();
            i.setAction(Junk_Settings );
            i.putExtra("UpdateToggles", true);
            getActivity().sendBroadcast(i);
            i = null;
    
        } else if (TOGGLE_DIVIDER_COLOR.equals(key)) {
        	Intent i = new Intent();
            i.setAction(Junk_Settings );
            i.putExtra("ToggleDividerColor", (Integer) objValue);
            getActivity().sendBroadcast(i);
            i = null;  
            
        	i = new Intent();
            i.setAction(Junk_Settings );
            i.putExtra("UpdateToggles", true);
            getActivity().sendBroadcast(i);
            i = null;
        }
        
        return true;
	}
}
