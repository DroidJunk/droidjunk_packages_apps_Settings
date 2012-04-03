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



import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.preference.SeekBarPreference;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.util.Log;
import android.widget.Toast;



public class CustomThemeSettings extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener {
    
	private final String Tranq_Icon_Color = "TRANQ_SETTINGS";
	private final String THEME_ONE = "theme_one";
	private final String THEME_TWO = "theme_two";
	private final String THEME_THREE = "theme_three";
	private final String THEME_ONE_NAME = "theme_one_name";
	private final String THEME_TWO_NAME = "theme_two_name";
	private final String THEME_THREE_NAME = "theme_three_name";
	private final String THEME_SAVE_ONE = "save_theme_one";
	private final String THEME_APPLY_ONE = "apply_theme_one";
	private final String THEME_SAVE_TWO = "save_theme_two";
	private final String THEME_APPLY_TWO = "apply_theme_two";
	private final String THEME_SAVE_THREE = "save_theme_three";
	private final String THEME_APPLY_THREE = "apply_theme_three";
	

	private final String SHOW_CARRIER = "show_carrier";
	private final String CARRIER_COLOR = "carrier_color";
	private final String CARRIER_CUSTOM = "carrier_custom";
	private final String CARRIER_CUSTOM_TEXT = "carrier_custom_text";
	private final String CARRIER_SIZE = "carrier_size";
	private final String DATE_COLOR = "date_color";
	private final String DATE_SIZE = "date_size";
	private final String SHOW_CLOCK = "show_clock";
	private final String CLOCK_AMPM = "clock_ampm";
	private final String CLOCK_COLOR = "clock_color";
	private final String CLOCK_SIZE = "clock_size";
	private final String TOGGLES_ON = "toggles_show_toggles";
	private final String TOGGLES_TOP = "toggles_top";
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
    private static final String PREF_NAV_COLOR = "nav_button_color";
    private static final String SHOW_SEARCH_BUTTON = "search_button";
	private static final String SHOW_LEFT_MENU_BUTTON = "left_menu_button";
	private static final String SHOW_RIGHT_MENU_BUTTON = "right_menu_button";
    private static final String SHOW_SEARCH_BUTTON_LAND = "search_button_land";
	private static final String SHOW_TOP_MENU_BUTTON_LAND = "top_menu_button_land";
	private static final String SHOW_BOT_MENU_BUTTON_LAND = "bottom_menu_button_land";
    private static final String ALT_METERS = "pref_alt_meters";
	private static final String BATTERY_BAR = "battery_bar";
	private static final String BATTERY_BAR_COLOR = "battery_bar_color";
	private final String ICON_COLOR_ON = "color_icons";
	private final String ICON_COLOR = "icon_color";
	
    
	private PreferenceManager prefMgr;
	private PreferenceManager themeMgr;
	private SharedPreferences themeEditor;
	private SharedPreferences.Editor editor = null;
	private PreferenceCategory mThemeOne;
	private PreferenceCategory mThemeTwo;
	private PreferenceCategory mThemeThree;
	private Preference mThemeOneName;
	private Preference mThemeTwoName;
	private Preference mThemeThreeName;
	private Preference mSaveThemeOne;
	private Preference mApplyThemeOne;
	private Preference mSaveThemeTwo;
	private Preference mApplyThemeTwo;
	private Preference mSaveThemeThree;
	private Preference mApplyThemeThree;
	private String themeOneName, themeTwoName, themeThreeName;
    private int whichTheme;
    private boolean showCarrier, carrierCustom;
    private boolean iconColorOn, showClock, clockAmPm, showToggles, togglesTop, toggleCustomIconColors ; 
    private boolean showIndicator, toggleShowText, toggleShowDivider;
    private boolean showFourg, showWifi, showGps, showBluetooth, showSound, showAirplane;
    private boolean showBrightness, showRotate, showSync, showData;
    private boolean showSearchButton, showLeftMenuButton, showRightMenuButton, showSearchButtonLand;
    private boolean showTopMenuButtonLand, showBotMenuButtonLand;
    private int carrierColor, iconColor, clockColor, clockSize, toggleColor, toggleIconOnColor;
    private int toggleIconInterColor, toggleIconOffColor, toggleIndOnColor, toggleIndOffColor;
    private int toggleTextOnColor, toggleTextOffColor, toggleDividerColor, NavigationBarColor;
    private int carrierSize, dateColor, dateSize, meterOptions, batteryBar, batteryBarColor;
	private String carrierCustomText;
    
    
    /** If there is no setting in the provider, use this. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        prefMgr = getPreferenceManager();
        prefMgr.setSharedPreferencesName("Tranquility_Settings");
        prefMgr.setSharedPreferencesMode(Context.MODE_WORLD_READABLE);
        prefMgr.getSharedPreferences();
 
        addPreferencesFromResource(R.xml.custom_theme_settings);
        
        mThemeOne = (PreferenceCategory) findPreference(THEME_ONE);
        mThemeTwo = (PreferenceCategory) findPreference(THEME_TWO);
        mThemeThree = (PreferenceCategory) findPreference(THEME_THREE);
        mThemeOneName = (Preference) findPreference(THEME_ONE_NAME);
		mThemeOneName.setOnPreferenceChangeListener(this);
        mThemeTwoName = (Preference) findPreference(THEME_TWO_NAME);
		mThemeTwoName.setOnPreferenceChangeListener(this);
        mThemeThreeName = (Preference) findPreference(THEME_THREE_NAME);
		mThemeThreeName.setOnPreferenceChangeListener(this);
		mSaveThemeOne = (Preference) findPreference(THEME_SAVE_ONE);
		mApplyThemeOne = (Preference) findPreference(THEME_APPLY_ONE);
		mSaveThemeTwo = (Preference) findPreference(THEME_SAVE_TWO);
		mApplyThemeTwo = (Preference) findPreference(THEME_APPLY_TWO);
		mSaveThemeThree = (Preference) findPreference(THEME_SAVE_THREE);
		mApplyThemeThree = (Preference) findPreference(THEME_APPLY_THREE);
		
		mThemeOne.setTitle(prefMgr.getSharedPreferences().getString(THEME_ONE_NAME,"Theme One"));
		mThemeTwo.setTitle(prefMgr.getSharedPreferences().getString(THEME_TWO_NAME,"Theme Two"));
		mThemeThree.setTitle(prefMgr.getSharedPreferences().getString(THEME_THREE_NAME,"Theme Three"));

        
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
    	
    	if (preference == mSaveThemeOne) {
    		themeOneName = prefMgr.getSharedPreferences().getString(THEME_ONE_NAME,"Theme One");
    		SaveDialog(1);
    	} else if (preference == mApplyThemeOne) {
    		themeOneName = prefMgr.getSharedPreferences().getString(THEME_ONE_NAME,"Theme One");
    		ApplyDialog(1);
    	} else if (preference == mSaveThemeTwo) {
    		themeTwoName = prefMgr.getSharedPreferences().getString(THEME_TWO_NAME,"Theme Two");
    		SaveDialog(2);
    	} else if (preference == mApplyThemeTwo) {
    		themeTwoName = prefMgr.getSharedPreferences().getString(THEME_TWO_NAME,"Theme Two");
    		ApplyDialog(2);
    	} else if (preference == mSaveThemeThree) {
    		themeThreeName = prefMgr.getSharedPreferences().getString(THEME_THREE_NAME,"Theme Three");
    		SaveDialog(3);
    	} else if (preference == mApplyThemeThree) {
    		themeThreeName = prefMgr.getSharedPreferences().getString(THEME_THREE_NAME,"Theme Three");
    		ApplyDialog(3);	
    	}
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }
    
 
    
    
    public boolean onPreferenceChange(Preference preference, Object objValue) {
  
     	final String key = preference.getKey();

     	if (THEME_ONE_NAME.equals(key)) {
     		mThemeOne.setTitle((String) objValue);
     		themeOneName = (String) objValue;
     	
     	} else if (THEME_TWO_NAME.equals(key)) {
     		mThemeTwo.setTitle((String) objValue);
     		themeTwoName = (String) objValue;
     	
     	} else if (THEME_THREE_NAME.equals(key)) {
     		mThemeThree.setTitle((String) objValue);
     		themeThreeName = (String) objValue;
     	}
        return true;
    }
    
    

    private void SaveDialog(int theme)	{
		
    	whichTheme = theme;
    	Builder alertDialog = new AlertDialog.Builder(getPreferenceScreen().getContext());
    	alertDialog.setTitle("Save Theme");
    	alertDialog.setMessage("Save the current colors to this theme?");
    	alertDialog.setNegativeButton("Cancel", null);
    	alertDialog.setPositiveButton("Save", saveDialogPositiveListener);
    	alertDialog.show();
    }

    private void ApplyDialog(int theme)	{
		
    	whichTheme = theme;
    	Builder alertDialog = new AlertDialog.Builder(getPreferenceScreen().getContext());
    	alertDialog.setTitle("Apply Saved Theme");
    	alertDialog.setMessage("Apply the saved theme?");
    	alertDialog.setNegativeButton("Cancel", null);
    	alertDialog.setPositiveButton("Apply", applyDialogPositiveListener);
    	alertDialog.show();
    }    
    

    private void SaveTheme() {
    	editor = themeEditor.edit();
        editor.putBoolean(SHOW_CARRIER, showCarrier);  
        editor.commit();
        
    }
    
    
    private void ApplyTheme() {
    	editor = themeEditor.edit();
        editor.putBoolean(SHOW_CARRIER, showCarrier);  
        editor.commit();
        
    }    

    private void GetSettings() {
    	showCarrier = prefMgr.getSharedPreferences().getBoolean(SHOW_CARRIER, false);
    	carrierCustom = prefMgr.getSharedPreferences().getBoolean(CARRIER_CUSTOM, false);
    	carrierSize = prefMgr.getSharedPreferences().getInt(CARRIER_SIZE, 15);
    	dateColor = prefMgr.getSharedPreferences().getInt(DATE_COLOR, 0xff3F9BBF);
    	dateSize = prefMgr.getSharedPreferences().getInt(DATE_SIZE, 17);
    	iconColorOn = prefMgr.getSharedPreferences().getBoolean(TOGGLE_ICON_ON_COLOR, true);
    	showClock = prefMgr.getSharedPreferences().getBoolean(SHOW_CLOCK, true);
    	clockAmPm = prefMgr.getSharedPreferences().getBoolean(CLOCK_AMPM, true);
    	showToggles = prefMgr.getSharedPreferences().getBoolean(TOGGLES_ON, true);
    	togglesTop = prefMgr.getSharedPreferences().getBoolean(TOGGLES_TOP, true);
    	toggleCustomIconColors = prefMgr.getSharedPreferences().getBoolean(TOGGLE_CUSTOM_ICON_COLORS, true);
    	showIndicator = prefMgr.getSharedPreferences().getBoolean(TOGGLE_SHOW_INDICATOR, true);
    	toggleShowText = prefMgr.getSharedPreferences().getBoolean(TOGGLE_SHOW_TEXT, true);
    	toggleShowDivider = prefMgr.getSharedPreferences().getBoolean(TOGGLE_SHOW_DIVIDER, true);
    	showFourg = prefMgr.getSharedPreferences().getBoolean(TOGGLES_4G_ON, true);
    	showWifi = prefMgr.getSharedPreferences().getBoolean(TOGGLES_WIFI_ON, true);
    	showGps = prefMgr.getSharedPreferences().getBoolean(TOGGLES_GPS_ON, true);
    	showBluetooth = prefMgr.getSharedPreferences().getBoolean(TOGGLES_BLUETOOTH_ON, true);
    	showSound = prefMgr.getSharedPreferences().getBoolean(TOGGLES_SOUND_ON, true);
    	showAirplane = prefMgr.getSharedPreferences().getBoolean(TOGGLES_AIRPLANE_ON, true);
    	showBrightness = prefMgr.getSharedPreferences().getBoolean(TOGGLES_BRIGHTNESS_ON, true);
    	showRotate = prefMgr.getSharedPreferences().getBoolean(TOGGLES_ROTATE_ON, true);
    	showSync = prefMgr.getSharedPreferences().getBoolean(TOGGLES_SYNC_ON, true);
    	showData = prefMgr.getSharedPreferences().getBoolean(TOGGLES_DATA_ON, true);
    	showSearchButton = prefMgr.getSharedPreferences().getBoolean(SHOW_SEARCH_BUTTON, true);
    	showLeftMenuButton = prefMgr.getSharedPreferences().getBoolean(SHOW_LEFT_MENU_BUTTON, true);
    	showRightMenuButton = prefMgr.getSharedPreferences().getBoolean(SHOW_RIGHT_MENU_BUTTON, true);
    	showSearchButtonLand = prefMgr.getSharedPreferences().getBoolean(SHOW_SEARCH_BUTTON_LAND, true);
    	showTopMenuButtonLand = prefMgr.getSharedPreferences().getBoolean(SHOW_TOP_MENU_BUTTON_LAND, true);
    	showBotMenuButtonLand = prefMgr.getSharedPreferences().getBoolean(SHOW_BOT_MENU_BUTTON_LAND, true);
        carrierColor = prefMgr.getSharedPreferences().getInt(CARRIER_COLOR, 0xff3F9BBF);
        iconColor = prefMgr.getSharedPreferences().getInt(ICON_COLOR, 0xff3F9BBF);
        clockColor = prefMgr.getSharedPreferences().getInt(CLOCK_COLOR, 0xff3F9BBF);
        clockSize = prefMgr.getSharedPreferences().getInt(CLOCK_SIZE, 15);
        toggleColor = prefMgr.getSharedPreferences().getInt(TOGGLE_COLOR, 15);
        toggleIconOnColor = prefMgr.getSharedPreferences().getInt(TOGGLE_ICON_ON_COLOR, 15);
        toggleIconInterColor = prefMgr.getSharedPreferences().getInt(TOGGLE_ICON_INTER_COLOR, 15);
        toggleIconOffColor = prefMgr.getSharedPreferences().getInt(TOGGLE_ICON_OFF_COLOR, 15);
        toggleIndOnColor = prefMgr.getSharedPreferences().getInt(TOGGLE_IND_ON_COLOR, 15);
        toggleIndOffColor = prefMgr.getSharedPreferences().getInt(TOGGLE_IND_OFF_COLOR, 15);
        toggleTextOnColor = prefMgr.getSharedPreferences().getInt(TOGGLE_TEXT_ON_COLOR, 15);
        toggleTextOffColor = prefMgr.getSharedPreferences().getInt(TOGGLE_TEXT_OFF_COLOR, 15);
        toggleDividerColor = prefMgr.getSharedPreferences().getInt(TOGGLE_DIVIDER_COLOR, 15);
        carrierCustomText = prefMgr.getSharedPreferences().getString(CARRIER_CUSTOM_TEXT, "T r a n Q u i l    I c e");
        carrierSize = prefMgr.getSharedPreferences().getInt(CARRIER_SIZE, 15);
        dateColor = prefMgr.getSharedPreferences().getInt(DATE_COLOR, 15);
        dateSize = prefMgr.getSharedPreferences().getInt(DATE_SIZE, 15);
        try {
			NavigationBarColor = Settings.System.getInt(getActivity().getContentResolver(),Settings.System.NAVIGATION_BAR_TINT);
		} catch (SettingNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        try {
			meterOptions = Settings.System.getInt(getActivity().getContentResolver(), Settings.System.BATTERY_ICON);
		} catch (SettingNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			batteryBar = Settings.System.getInt(getActivity().getContentResolver(), Settings.System.STATUSBAR_BATTERY_BAR);
		} catch (SettingNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			batteryBarColor = Settings.System.getInt(getActivity().getContentResolver(), Settings.System.STATUSBAR_BATTERY_BAR_COLOR);
		} catch (SettingNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        
    }    
    
    
    DialogInterface.OnClickListener saveDialogPositiveListener =
            new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					if (whichTheme == 1 ) {
				        prefMgr = getPreferenceManager();
				        prefMgr.setSharedPreferencesName("Tranquility_Settings");
				        prefMgr.setSharedPreferencesMode(Context.MODE_WORLD_READABLE);
				        prefMgr.getSharedPreferences();

				        
				        GetSettings();
				        
				        themeMgr = getPreferenceManager();
				        themeMgr.setSharedPreferencesName("Tranquility_Theme_One");
				        themeMgr.setSharedPreferencesMode(Context.MODE_WORLD_READABLE);
				        themeMgr.getSharedPreferences();
				        themeEditor = themeMgr.getSharedPreferences();
				      
				        SaveTheme();
				        
				        
				        
				        prefMgr = getPreferenceManager();
				        prefMgr.setSharedPreferencesName("Tranquility_Settings");
				        prefMgr.setSharedPreferencesMode(Context.MODE_WORLD_READABLE);
				        prefMgr.getSharedPreferences();
				        prefMgr.getSharedPreferencesName();
						Toast.makeText(getActivity().getBaseContext(), themeOneName + " has been saved", Toast.LENGTH_SHORT).show();
					
					} else if (whichTheme == 2 ) {
				        themeEditor = themeMgr.getSharedPreferences();
				        SaveTheme();
						Toast.makeText(getActivity().getBaseContext(), themeTwoName + " has been saved", Toast.LENGTH_SHORT).show();
						
					} else if (whichTheme == 3 ) {
				        themeEditor = themeMgr.getSharedPreferences();
				        SaveTheme();
						Toast.makeText(getActivity().getBaseContext(), themeThreeName + " has been saved", Toast.LENGTH_SHORT).show();
					}
				}
			};
			
    DialogInterface.OnClickListener applyDialogPositiveListener =
            new DialogInterface.OnClickListener() {
						
				@Override
				public void onClick(DialogInterface dialog, int which) {
					if (whichTheme == 1 ) {
				        themeEditor = prefMgr.getSharedPreferences();
				        ApplyTheme();
						Toast.makeText(getActivity().getBaseContext(), themeOneName + " has been applied", Toast.LENGTH_SHORT).show();
						
					} else if (whichTheme == 2 ) {
				        themeEditor = prefMgr.getSharedPreferences();
				        ApplyTheme();
						Toast.makeText(getActivity().getBaseContext(), themeTwoName + " has been applied", Toast.LENGTH_SHORT).show();
						
					} else if (whichTheme == 3 ) {
				        themeEditor = prefMgr.getSharedPreferences();
				        ApplyTheme();
						Toast.makeText(getActivity().getBaseContext(), themeThreeName + " has been applied", Toast.LENGTH_SHORT).show();
					}
				}
			};
			
    
    
	
					
					
}
