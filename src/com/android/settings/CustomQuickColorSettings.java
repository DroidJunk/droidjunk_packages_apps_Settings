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
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.preference.CheckBoxPreference;
import android.util.Log;



public class CustomQuickColorSettings extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener {

	
	
	//Icons - Signal/Wifi
	private final String ICON_COLOR = "icon_color";
	
	// Battery
    private final String Junk_Battery_Settings = "JUNK_BATTERY_SETTINGS";
	private final String BATTERY_DEPLETED_COLOR = "battery_depleted_color";
	private final String BATTERY_LEVEL_COLOR_ONE = "battery_levels_color_one";
	private final String BATTERY_LEVEL_COLOR_TWO = "battery_levels_color_two";
	private final String BATTERY_LEVEL_COLOR_THREE = "battery_levels_color_three";    
	
	// LED
	private final String DEFAULT_LED_COLOR = "default_led_color";	
	private final String INCOMING_CALL_LED_COLOR = "incoming_call_color";
	private final String MISSED_CALL_LED_COLOR = "missed_call_color";
	private final String VOICE_MAIL_LED_COLOR = "voice_mail_color";

	//Navbar
	private final String Junk_NavBar_Settings = "JUNK_NAVBAR_SETTINGS";
    private final String NAV_BAR_COLOR = "nav_button_color";
    
    //Pulldown
    private final String Junk_Pulldown_Settings = "JUNK_PULLDOWN_SETTINGS";
	private final String BATTERY_LABEL_COLOR = "battery_label_color";
	private final String TEMP_LABEL_COLOR = "temp_label_color";
	private final String CARRIER_COLOR = "carrier_color";
	private final String DATE_COLOR = "date_color";

	//Clock
	private final String Junk_StatusBar_Settings = "JUNK_STATUSBAR_SETTINGS";
	private final String CLOCK_COLOR = "clock_color";
	
	//Toggles
	private final String Junk_Toggle_Settings = "JUNK_TOGGLE_SETTINGS";
	private final String TOGGLE_COLOR = "toggle_color";
	private final String TOGGLE_ICON_ON_COLOR = "toggles_icon_on_color";
	private final String TOGGLE_ICON_INTER_COLOR = "toggles_icon_inter_color";
	private final String TOGGLE_ICON_OFF_COLOR = "toggles_icon_off_color";
	private final String TOGGLE_IND_ON_COLOR = "toggle_ind_on_color";
	private final String TOGGLE_IND_OFF_COLOR = "toggle_ind_off_color";
	private final String TOGGLE_TEXT_ON_COLOR = "toggle_text_on_color";
	private final String TOGGLE_TEXT_OFF_COLOR = "toggle_text_off_color";
	private final String TOGGLE_DIVIDER_COLOR = "toggle_divider_color";


	private PreferenceManager prefMgr;
	private SharedPreferences sharedPref;

	private Preference mIconColor;
	
	private Preference mBatteryDepletedColor;
    private Preference mBatteryLevelColorOne;
    private Preference mBatteryLevelColorTwo;
    private Preference mBatteryLevelColorThree;
	
    private Preference mDefaultLedColor;
    private Preference mIncomingCallColor;
    private Preference mMissedCallColor;
    private Preference mVoiceMailColor;
	private Preference mNavigationBarColor;
	
    private Preference mCarrierColor;
    private Preference mBatteryColor;
    private Preference mTempColor;
    private Preference mDateColor;
    
    private Preference mClockColor;
    
	private Preference mToggleColor;
	private Preference mToggleIconOnColor;
	private Preference mToggleIconInterColor;
	private Preference mToggleIconOffColor;
    private Preference mToggleIndOnColor;
    private Preference mToggleIndOffColor;
    private Preference mToggleTextOnColor;
    private Preference mToggleTextOffColor;
    private Preference mToggleDividerColor;

	
	
	
    /** If there is no setting in the provider, use this. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        prefMgr = getPreferenceManager();
        prefMgr.setSharedPreferencesName("Junk_Settings");
        prefMgr.setSharedPreferencesMode(Context.MODE_WORLD_READABLE);
        prefMgr.getSharedPreferences();
        sharedPref = prefMgr.getSharedPreferences();
 
       	addPreferencesFromResource(R.xml.custom_quickcolor_settings);
        
        
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


        return true;
    }
 
    

    
 
} 