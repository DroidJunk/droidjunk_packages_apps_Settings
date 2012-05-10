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
import android.preference.CheckBoxPreference;



public class CustomToggleSettings extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener {
    

	private final String Junk_Toggle_Settings = "JUNK_TOGGLE_SETTINGS";
	private final String TOGGLES_UPDATE = "toggles_update";
	private final String TOGGLES_ON = "toggles_show_toggles";
	private final String TOGGLES_TOP = "toggles_top";
	private final String TOGGLE_COLOR = "toggle_color";
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
	private CheckBoxPreference mShowToggles;
	private CheckBoxPreference mTogglesTop;
	private Preference mToggleColor;
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
    	mToggleColor = (Preference) findPreference(TOGGLE_COLOR);
    	mToggleColor.setOnPreferenceChangeListener(this);
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
        	i.setAction(Junk_Toggle_Settings);
       	   	i.putExtra(TOGGLES_ON, (Boolean) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;
       	   	
        	i = new Intent();
            i.setAction(Junk_Toggle_Settings);
            i.putExtra(TOGGLES_UPDATE, true);
            getActivity().sendBroadcast(i);
            i = null;

     	} else if (TOGGLES_TOP.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Toggle_Settings);
       	   	i.putExtra(TOGGLES_TOP, (Boolean) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;
       	   	
        	i = new Intent();
            i.setAction(Junk_Toggle_Settings);
            i.putExtra(TOGGLES_UPDATE, true);
            getActivity().sendBroadcast(i);
            i = null;

    	} else if (TOGGLE_COLOR.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Toggle_Settings);
       	   	i.putExtra(TOGGLE_COLOR, (Integer) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;

       
    	} else if (TOGGLE_ICON_ON_COLOR.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Toggle_Settings);
       	   	i.putExtra(TOGGLE_ICON_ON_COLOR, (Integer) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;

    	} else if (TOGGLE_ICON_INTER_COLOR.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Toggle_Settings);
       	   	i.putExtra(TOGGLE_ICON_INTER_COLOR, (Integer) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;
       	   	
    	} else if (TOGGLE_ICON_OFF_COLOR.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Toggle_Settings);
       	   	i.putExtra(TOGGLE_ICON_OFF_COLOR, (Integer) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;       	   	
       	   	
        } else if (TOGGLE_SHOW_INDICATOR.equals(key)) {
        	Intent i = new Intent();
            i.setAction(Junk_Toggle_Settings);
            i.putExtra(TOGGLE_SHOW_INDICATOR, (Boolean) objValue);
            if ((Boolean) objValue) {
            	i.putExtra(TOGGLE_IND_ON_COLOR, (Integer) prefMgr.getSharedPreferences().getInt(TOGGLE_IND_ON_COLOR, 0xffffffff));
            	i.putExtra(TOGGLE_IND_OFF_COLOR, (Integer) prefMgr.getSharedPreferences().getInt(TOGGLE_IND_OFF_COLOR, 0xff555555));
            	} else {
            		i.putExtra(TOGGLE_IND_ON_COLOR,0);
            		i.putExtra(TOGGLE_IND_OFF_COLOR,0);
            }
            getActivity().sendBroadcast(i);
            i = null;
            
        	i = new Intent();
            i.setAction(Junk_Toggle_Settings);
            i.putExtra(TOGGLES_UPDATE, true);
            getActivity().sendBroadcast(i);
            i = null;
            
            
        } else if (TOGGLE_IND_ON_COLOR.equals(key)) {
        	Intent i = new Intent();
            i.setAction(Junk_Toggle_Settings);
            i.putExtra(TOGGLE_IND_ON_COLOR, (Integer) objValue);
            getActivity().sendBroadcast(i);
            i = null;
            
        	i = new Intent();
            i.setAction(Junk_Toggle_Settings);
            i.putExtra(TOGGLES_UPDATE, true);
            getActivity().sendBroadcast(i);
            i = null;
            
        } else if (TOGGLE_IND_OFF_COLOR.equals(key)) {
        	Intent i = new Intent();
            i.setAction(Junk_Toggle_Settings);
            i.putExtra(TOGGLE_IND_OFF_COLOR, (Integer) objValue);
             getActivity().sendBroadcast(i);
            i = null;
            
        	i = new Intent();
            i.setAction(Junk_Toggle_Settings);
            i.putExtra(TOGGLES_UPDATE, true);
            getActivity().sendBroadcast(i);
            i = null;

        } else if (TOGGLE_SHOW_TEXT.equals(key)) {
        	Intent i = new Intent();
            i.setAction(Junk_Toggle_Settings);
            i.putExtra(TOGGLE_SHOW_TEXT, (Boolean) objValue);
            if ((Boolean) objValue) {
            	i.putExtra(TOGGLE_TEXT_ON_COLOR, (Integer) prefMgr.getSharedPreferences().getInt(TOGGLE_TEXT_ON_COLOR, 0xffffffff));
            	i.putExtra(TOGGLE_TEXT_OFF_COLOR, (Integer) prefMgr.getSharedPreferences().getInt(TOGGLE_TEXT_OFF_COLOR, 0xff555555));
            	} else {
            		i.putExtra(TOGGLE_TEXT_ON_COLOR,0);
            		i.putExtra(TOGGLE_TEXT_OFF_COLOR,0);
            }
            getActivity().sendBroadcast(i);
            i = null;
            
        	i = new Intent();
            i.setAction(Junk_Toggle_Settings);
            i.putExtra(TOGGLES_UPDATE, true);
            getActivity().sendBroadcast(i);
            i = null;

        } else if (TOGGLE_TEXT_ON_COLOR.equals(key)) {
        	Intent i = new Intent();
            i.setAction(Junk_Toggle_Settings);
            i.putExtra(TOGGLE_TEXT_ON_COLOR, (Integer) objValue);
            getActivity().sendBroadcast(i);
            i = null;        
            
        	i = new Intent();
            i.setAction(Junk_Toggle_Settings);
            i.putExtra(TOGGLES_UPDATE, true);
            getActivity().sendBroadcast(i);
            i = null;

        } else if (TOGGLE_TEXT_OFF_COLOR.equals(key)) {
        	Intent i = new Intent();
            i.setAction(Junk_Toggle_Settings);
            i.putExtra(TOGGLE_TEXT_OFF_COLOR, (Integer) objValue);
            getActivity().sendBroadcast(i);
            i = null;     
            
        	i = new Intent();
            i.setAction(Junk_Toggle_Settings);
            i.putExtra(TOGGLES_UPDATE, true);
            getActivity().sendBroadcast(i);
            i = null;
        
 
        } else if (TOGGLE_SHOW_DIVIDER.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Toggle_Settings);
        	i.putExtra(TOGGLE_SHOW_DIVIDER, (Boolean) objValue);
            if ((Boolean) objValue) {
            	i.putExtra(TOGGLE_DIVIDER_COLOR, (Integer) prefMgr.getSharedPreferences().getInt(TOGGLE_DIVIDER_COLOR, 0xff3b3b3b));
            	} else {
            		i.putExtra(TOGGLE_DIVIDER_COLOR,0);
            }
        	getActivity().sendBroadcast(i);
        	i = null;      
        	
        	i = new Intent();
            i.setAction(Junk_Toggle_Settings);
            i.putExtra(TOGGLES_UPDATE, true);
            getActivity().sendBroadcast(i);
            i = null;
    
        } else if (TOGGLE_DIVIDER_COLOR.equals(key)) {
        	Intent i = new Intent();
            i.setAction(Junk_Toggle_Settings);
            i.putExtra(TOGGLE_DIVIDER_COLOR, (Integer) objValue);
            getActivity().sendBroadcast(i);
            i = null;  
            
        	i = new Intent();
            i.setAction(Junk_Toggle_Settings);
            i.putExtra(TOGGLES_UPDATE, true);
            getActivity().sendBroadcast(i);
            i = null;
        }
        
        return true;
	}
}
