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
    
	private final String Tranq_Settings = "TRANQ_SETTINGS";
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
	private final String SAVED_THEME = "saved_theme";
	

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
    private final String SHOW_SEARCH_BUTTON = "search_button";
	private final String SHOW_LEFT_MENU_BUTTON = "left_menu_button";
	private final String SHOW_RIGHT_MENU_BUTTON = "right_menu_button";
    private final String SHOW_SEARCH_BUTTON_LAND = "search_button_land";
	private final String SHOW_TOP_MENU_BUTTON_LAND = "top_menu_button_land";
	private final String SHOW_BOT_MENU_BUTTON_LAND = "bottom_menu_button_land";
	private final String ICON_COLOR_ON = "color_icons";
	private final String ICON_COLOR = "icon_color";
	private final String NAVIGATION_BAR_TINT = "nav_bar_tint";
	private final String BATTERY_ICON = "battery_icon";
	private final String BATTERY_BAR = "battery_bar";
	private final String BATTERY_BAR_COLOR = "battery_bar_color";
	private final String QUICK_SETTINGS_ON = "toggles_show_quicksettings";
	private final String QUICK_SETTINGS_BOTTOM = "quicksettings_bottom";
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
    private boolean iconColorOn, showClock, clockAmPm, togglesShowToggles, togglesTop, toggleCustomIconColors ; 
    private boolean togglesShowIndicator, toggleShowText, toggleShowDivider;
    private boolean showFourg, showWifi, showGps, showBluetooth, showSound, showAirplane;
    private boolean showBrightness, showRotate, showSync, showData;
    private boolean showSearchButton, showLeftMenuButton, showRightMenuButton, showSearchButtonLand;
    private boolean showTopMenuButtonLand, showBotMenuButtonLand, quickSettingsOn, quickSettingsBottom;
    private boolean quickClock, quickMeter, quickNav, quickLock, quickPullDown, quickToggle, quickQuiet, quickLed, quickIcon;
    private int carrierColor, iconColor, clockColor, clockSize, toggleColor, toggleIconOnColor;
    private int toggleIconInterColor, toggleIconOffColor, toggleIndOnColor, toggleIndOffColor;
    private int toggleTextOnColor, toggleTextOffColor, toggleDividerColor, navigationBarColor;
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
		
		
        themeMgr = getPreferenceManager();
        themeMgr.setSharedPreferencesName("Tranquility_Theme_One");
        themeMgr.setSharedPreferencesMode(Context.MODE_WORLD_READABLE);
        themeMgr.getSharedPreferences();
		mApplyThemeOne.setEnabled(themeMgr.getSharedPreferences().getBoolean(SAVED_THEME,false));

        themeMgr = getPreferenceManager();
        themeMgr.setSharedPreferencesName("Tranquility_Theme_Two");
        themeMgr.setSharedPreferencesMode(Context.MODE_WORLD_READABLE);
        themeMgr.getSharedPreferences();
		mApplyThemeTwo.setEnabled(prefMgr.getSharedPreferences().getBoolean(SAVED_THEME,false));

        themeMgr = getPreferenceManager();
        themeMgr.setSharedPreferencesName("Tranquility_Theme_Three");
        themeMgr.setSharedPreferencesMode(Context.MODE_WORLD_READABLE);
        themeMgr.getSharedPreferences();
		mApplyThemeThree.setEnabled(prefMgr.getSharedPreferences().getBoolean(SAVED_THEME,false));

        
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
    	editor.putBoolean(SAVED_THEME, true);
        editor.putBoolean(SHOW_CARRIER, showCarrier);  
        editor.putBoolean(CARRIER_CUSTOM, carrierCustom);
        editor.putInt(CARRIER_SIZE, carrierSize);
        editor.putInt(DATE_COLOR, dateColor);
        editor.putInt(DATE_SIZE, dateSize);
        editor.putBoolean(ICON_COLOR_ON, iconColorOn);
        editor.putBoolean(SHOW_CLOCK, showClock);
        editor.putBoolean(CLOCK_AMPM, clockAmPm);
        editor.putBoolean(TOGGLES_ON, togglesShowToggles);
        editor.putBoolean(TOGGLES_TOP, togglesTop);
        editor.putBoolean(TOGGLE_CUSTOM_ICON_COLORS, toggleCustomIconColors);
        editor.putBoolean(TOGGLE_SHOW_INDICATOR, togglesShowIndicator);
        editor.putBoolean(TOGGLE_SHOW_TEXT, toggleShowText);
        editor.putBoolean(TOGGLE_SHOW_DIVIDER, toggleShowDivider);
        editor.putBoolean(TOGGLES_4G_ON, showFourg);
        editor.putBoolean(TOGGLES_WIFI_ON, showWifi);
        editor.putBoolean(TOGGLES_GPS_ON, showGps);
        editor.putBoolean(TOGGLES_BLUETOOTH_ON, showBluetooth);
        editor.putBoolean(TOGGLES_SOUND_ON, showSound);
        editor.putBoolean(TOGGLES_AIRPLANE_ON, showAirplane);
        editor.putBoolean(TOGGLES_BRIGHTNESS_ON, showBrightness);
        editor.putBoolean(TOGGLES_ROTATE_ON, showRotate);
        editor.putBoolean(TOGGLES_SYNC_ON, showSync);
        editor.putBoolean(TOGGLES_DATA_ON, showData);
        editor.putBoolean(SHOW_SEARCH_BUTTON, showSearchButton);
        editor.putBoolean(SHOW_LEFT_MENU_BUTTON, showLeftMenuButton);
        editor.putBoolean(SHOW_RIGHT_MENU_BUTTON, showRightMenuButton);
        editor.putBoolean(SHOW_SEARCH_BUTTON_LAND, showSearchButtonLand);
        editor.putBoolean(SHOW_TOP_MENU_BUTTON_LAND, showTopMenuButtonLand);
        editor.putBoolean(SHOW_BOT_MENU_BUTTON_LAND, showBotMenuButtonLand);
        editor.putInt(CARRIER_COLOR, carrierColor);
        editor.putInt(ICON_COLOR, iconColor);
        editor.putInt(CLOCK_COLOR, clockColor);
        editor.putInt(CLOCK_SIZE, clockSize);
        editor.putInt(TOGGLE_COLOR, toggleColor);
        editor.putInt(TOGGLE_ICON_ON_COLOR, toggleIconOnColor);
        editor.putInt(TOGGLE_ICON_INTER_COLOR, toggleIconInterColor);
        editor.putInt(TOGGLE_ICON_OFF_COLOR, toggleIconOffColor);
        editor.putInt(TOGGLE_IND_ON_COLOR, toggleIndOnColor);
        editor.putInt(TOGGLE_IND_OFF_COLOR, toggleIndOffColor);
        editor.putInt(TOGGLE_TEXT_ON_COLOR, toggleTextOnColor);
        editor.putInt(TOGGLE_TEXT_OFF_COLOR, toggleTextOffColor);
        editor.putInt(TOGGLE_DIVIDER_COLOR, toggleDividerColor);
        editor.putString(CARRIER_CUSTOM_TEXT, carrierCustomText);
        editor.putInt(CARRIER_SIZE, carrierSize);
        editor.putInt(DATE_COLOR, dateColor);
        editor.putInt(DATE_SIZE, dateSize);
        editor.putInt(NAVIGATION_BAR_TINT, navigationBarColor);
        editor.putInt(BATTERY_ICON, meterOptions);
        editor.putInt(BATTERY_BAR, batteryBar);
        editor.putInt(BATTERY_BAR_COLOR, batteryBarColor);
        editor.putBoolean(QUICK_SETTINGS_ON, quickSettingsOn);
        editor.putBoolean(QUICK_SETTINGS_BOTTOM, quickSettingsBottom);
        editor.putBoolean(SETTINGS_CLOCK, quickClock);
        editor.putBoolean(SETTINGS_METER, quickMeter);
        editor.putBoolean(SETTINGS_NAV, quickNav);
        editor.putBoolean(SETTINGS_LOCKSCREEN, quickLock);
        editor.putBoolean(SETTINGS_PULLDOWN, quickPullDown);
        editor.putBoolean(SETTINGS_TOGGLE, quickToggle);
        editor.putBoolean(SETTINGS_QUIETTIME, quickQuiet);
        editor.putBoolean(SETTINGS_LED, quickLed);
        editor.putBoolean(SETTINGS_ICON, quickIcon);
        editor.commit();
        
    }
    
    
    private void ApplyTheme() {
    	editor = themeEditor.edit();
        editor.putBoolean(SHOW_CARRIER, showCarrier);  
        editor.putBoolean(CARRIER_CUSTOM, carrierCustom);
        editor.putInt(CARRIER_SIZE, carrierSize);
        editor.putInt(DATE_COLOR, dateColor);
        editor.putInt(DATE_SIZE, dateSize);
        editor.putBoolean(ICON_COLOR_ON, iconColorOn);
        editor.putBoolean(SHOW_CLOCK, showClock);
        editor.putBoolean(CLOCK_AMPM, clockAmPm);
        editor.putBoolean(TOGGLES_ON, togglesShowToggles);
        editor.putBoolean(TOGGLES_TOP, togglesTop);
        editor.putBoolean(TOGGLE_CUSTOM_ICON_COLORS, toggleCustomIconColors);
        editor.putBoolean(TOGGLE_SHOW_INDICATOR, togglesShowIndicator);
        editor.putBoolean(TOGGLE_SHOW_TEXT, toggleShowText);
        editor.putBoolean(TOGGLE_SHOW_DIVIDER, toggleShowDivider);
        editor.putBoolean(TOGGLES_4G_ON, showFourg);
        editor.putBoolean(TOGGLES_WIFI_ON, showWifi);
        editor.putBoolean(TOGGLES_GPS_ON, showGps);
        editor.putBoolean(TOGGLES_BLUETOOTH_ON, showBluetooth);
        editor.putBoolean(TOGGLES_SOUND_ON, showSound);
        editor.putBoolean(TOGGLES_AIRPLANE_ON, showAirplane);
        editor.putBoolean(TOGGLES_BRIGHTNESS_ON, showBrightness);
        editor.putBoolean(TOGGLES_ROTATE_ON, showRotate);
        editor.putBoolean(TOGGLES_SYNC_ON, showSync);
        editor.putBoolean(TOGGLES_DATA_ON, showData);
        editor.putBoolean(SHOW_SEARCH_BUTTON, showSearchButton);
        editor.putBoolean(SHOW_LEFT_MENU_BUTTON, showLeftMenuButton);
        editor.putBoolean(SHOW_RIGHT_MENU_BUTTON, showRightMenuButton);
        editor.putBoolean(SHOW_SEARCH_BUTTON_LAND, showSearchButtonLand);
        editor.putBoolean(SHOW_TOP_MENU_BUTTON_LAND, showTopMenuButtonLand);
        editor.putBoolean(SHOW_BOT_MENU_BUTTON_LAND, showBotMenuButtonLand);
        editor.putInt(CARRIER_COLOR, carrierColor);
        editor.putInt(ICON_COLOR, iconColor);
        editor.putInt(CLOCK_COLOR, clockColor);
        editor.putInt(CLOCK_SIZE, clockSize);
        editor.putInt(TOGGLE_COLOR, toggleColor);
        editor.putInt(TOGGLE_ICON_ON_COLOR, toggleIconOnColor);
        editor.putInt(TOGGLE_ICON_INTER_COLOR, toggleIconInterColor);
        editor.putInt(TOGGLE_ICON_OFF_COLOR, toggleIconOffColor);
        editor.putInt(TOGGLE_IND_ON_COLOR, toggleIndOnColor);
        editor.putInt(TOGGLE_IND_OFF_COLOR, toggleIndOffColor);
        editor.putInt(TOGGLE_TEXT_ON_COLOR, toggleTextOnColor);
        editor.putInt(TOGGLE_TEXT_OFF_COLOR, toggleTextOffColor);
        editor.putInt(TOGGLE_DIVIDER_COLOR, toggleDividerColor);
        editor.putString(CARRIER_CUSTOM_TEXT, carrierCustomText);
        editor.putInt(CARRIER_SIZE, carrierSize);
        editor.putInt(DATE_COLOR, dateColor);
        editor.putInt(DATE_SIZE, dateSize);
        editor.putBoolean(QUICK_SETTINGS_ON, quickSettingsOn);
        editor.putBoolean(QUICK_SETTINGS_BOTTOM, quickSettingsBottom);
        editor.putBoolean(SETTINGS_CLOCK, quickClock);
        editor.putBoolean(SETTINGS_METER, quickMeter);
        editor.putBoolean(SETTINGS_NAV, quickNav);
        editor.putBoolean(SETTINGS_LOCKSCREEN, quickLock);
        editor.putBoolean(SETTINGS_PULLDOWN, quickPullDown);
        editor.putBoolean(SETTINGS_TOGGLE, quickToggle);
        editor.putBoolean(SETTINGS_QUIETTIME, quickQuiet);
        editor.putBoolean(SETTINGS_LED, quickLed);
        editor.putBoolean(SETTINGS_ICON, quickIcon);
 
        editor.commit();
       
        Settings.System.putInt(getActivity().getContentResolver(),Settings.System.NAVIGATION_BAR_TINT, navigationBarColor);        
        Settings.System.putInt(getActivity().getContentResolver(), Settings.System.BATTERY_ICON, meterOptions);
        Settings.System.putInt(getActivity().getContentResolver(), Settings.System.STATUSBAR_BATTERY_BAR, batteryBar);
        Settings.System.putInt(getActivity().getContentResolver(), Settings.System.STATUSBAR_BATTERY_BAR_COLOR, batteryBarColor);
        
        
        SendIntents();

        
    }    
    
    


    private void GetSettings(boolean readSystem) {
    	showClock = prefMgr.getSharedPreferences().getBoolean(SHOW_CLOCK, true);
    	clockAmPm = prefMgr.getSharedPreferences().getBoolean(CLOCK_AMPM, false);
    	clockColor = prefMgr.getSharedPreferences().getInt(CLOCK_COLOR, 0xff3F9BBF);
    	clockSize = prefMgr.getSharedPreferences().getInt(CLOCK_SIZE, 17);
    	togglesShowToggles = prefMgr.getSharedPreferences().getBoolean(TOGGLES_ON, true);
    	togglesTop = prefMgr.getSharedPreferences().getBoolean(TOGGLES_TOP, true);
    	toggleCustomIconColors = prefMgr.getSharedPreferences().getBoolean(TOGGLE_CUSTOM_ICON_COLORS, false);
    	toggleColor = prefMgr.getSharedPreferences().getInt(TOGGLE_COLOR, 0xff000000);
    	toggleIconOnColor = prefMgr.getSharedPreferences().getInt(TOGGLE_ICON_ON_COLOR, 0xff33b5e5);
        toggleIconInterColor = prefMgr.getSharedPreferences().getInt(TOGGLE_ICON_INTER_COLOR, 0xffff0000);
        toggleIconOffColor = prefMgr.getSharedPreferences().getInt(TOGGLE_ICON_OFF_COLOR, 0xff5d5d5d);
        togglesShowIndicator = prefMgr.getSharedPreferences().getBoolean(TOGGLE_SHOW_INDICATOR, false);
        toggleIndOnColor = prefMgr.getSharedPreferences().getInt(TOGGLE_IND_ON_COLOR, 0xfffbb33);
        toggleIndOffColor = prefMgr.getSharedPreferences().getInt(TOGGLE_IND_OFF_COLOR, 0xff757575);
        toggleShowText = prefMgr.getSharedPreferences().getBoolean(TOGGLE_SHOW_TEXT, false);
        toggleTextOnColor = prefMgr.getSharedPreferences().getInt(TOGGLE_TEXT_ON_COLOR, 0xffffbb33);
        toggleTextOffColor = prefMgr.getSharedPreferences().getInt(TOGGLE_TEXT_OFF_COLOR, 0xff757575);
        toggleShowDivider = prefMgr.getSharedPreferences().getBoolean(TOGGLE_SHOW_DIVIDER, true);
        toggleDividerColor = prefMgr.getSharedPreferences().getInt(TOGGLE_DIVIDER_COLOR, 0xff757575);
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
        quickSettingsOn = prefMgr.getSharedPreferences().getBoolean(QUICK_SETTINGS_ON, true);
        quickSettingsBottom = prefMgr.getSharedPreferences().getBoolean(QUICK_SETTINGS_BOTTOM, true);
        quickClock = prefMgr.getSharedPreferences().getBoolean(SETTINGS_CLOCK, true);
        quickMeter = prefMgr.getSharedPreferences().getBoolean(SETTINGS_METER, true);
        quickNav = prefMgr.getSharedPreferences().getBoolean(SETTINGS_NAV, true);
        quickLock = prefMgr.getSharedPreferences().getBoolean(SETTINGS_LOCKSCREEN, true);
        quickPullDown = prefMgr.getSharedPreferences().getBoolean(SETTINGS_PULLDOWN, true);
        quickToggle = prefMgr.getSharedPreferences().getBoolean(SETTINGS_TOGGLE, true);
        quickQuiet = prefMgr.getSharedPreferences().getBoolean(SETTINGS_QUIETTIME, true);
        quickLed = prefMgr.getSharedPreferences().getBoolean(SETTINGS_LED, true);
        quickIcon = prefMgr.getSharedPreferences().getBoolean(SETTINGS_ICON, true); 
    	showSearchButton = prefMgr.getSharedPreferences().getBoolean(SHOW_SEARCH_BUTTON, false);
    	showLeftMenuButton = prefMgr.getSharedPreferences().getBoolean(SHOW_LEFT_MENU_BUTTON, true);
    	showRightMenuButton = prefMgr.getSharedPreferences().getBoolean(SHOW_RIGHT_MENU_BUTTON, true);
    	showSearchButtonLand = prefMgr.getSharedPreferences().getBoolean(SHOW_SEARCH_BUTTON_LAND, false);
    	showTopMenuButtonLand = prefMgr.getSharedPreferences().getBoolean(SHOW_TOP_MENU_BUTTON_LAND, true);
    	showBotMenuButtonLand = prefMgr.getSharedPreferences().getBoolean(SHOW_BOT_MENU_BUTTON_LAND, true);
    	showCarrier = prefMgr.getSharedPreferences().getBoolean(SHOW_CARRIER, true);
    	carrierColor = prefMgr.getSharedPreferences().getInt(CARRIER_COLOR, 0xff3F9BBF);
    	carrierSize = prefMgr.getSharedPreferences().getInt(CARRIER_SIZE, 15);
    	carrierCustom = prefMgr.getSharedPreferences().getBoolean(CARRIER_CUSTOM, false);
        carrierCustomText = prefMgr.getSharedPreferences().getString(CARRIER_CUSTOM_TEXT, "T r a n Q u i l    I c e");
    	dateColor = prefMgr.getSharedPreferences().getInt(DATE_COLOR, 0xff3F9BBF);
    	dateSize = prefMgr.getSharedPreferences().getInt(DATE_SIZE, 17);
    	iconColorOn = prefMgr.getSharedPreferences().getBoolean(ICON_COLOR_ON, false);
    	iconColor = prefMgr.getSharedPreferences().getInt(ICON_COLOR, 0xff3F9BBF);
        
        if (!readSystem) {
        navigationBarColor = prefMgr.getSharedPreferences().getInt(NAVIGATION_BAR_TINT, 0xffffffff);
        meterOptions = prefMgr.getSharedPreferences().getInt(BATTERY_ICON, 15);
        batteryBar = prefMgr.getSharedPreferences().getInt(BATTERY_BAR, 15);
        batteryBarColor = prefMgr.getSharedPreferences().getInt(BATTERY_BAR_COLOR, 15);
        
        } else {
        	try {
        		navigationBarColor = Settings.System.getInt(getActivity().getContentResolver(),Settings.System.NAVIGATION_BAR_TINT);
        	} catch (SettingNotFoundException e) {
        		e.printStackTrace();
        	}

        	try {
        		meterOptions = Settings.System.getInt(getActivity().getContentResolver(), Settings.System.BATTERY_ICON);
        	} catch (SettingNotFoundException e) {
        		e.printStackTrace();
        	}

        	try {
        		batteryBar = Settings.System.getInt(getActivity().getContentResolver(), Settings.System.STATUSBAR_BATTERY_BAR);
        	} catch (SettingNotFoundException e) {
        		e.printStackTrace();
        	}

        	try {
        		batteryBarColor = Settings.System.getInt(getActivity().getContentResolver(), Settings.System.STATUSBAR_BATTERY_BAR_COLOR);
        	} catch (SettingNotFoundException e) {
        		e.printStackTrace();
        	}
        }
    }    
    

    private void SendIntents() {
    	Intent i = new Intent();
    	i.setAction(Tranq_Settings );
   	   	i.putExtra("ShowClock", showClock);
   	   	getActivity().sendBroadcast(i);
   	   	i = null;
   
     	i = new Intent();
        i.setAction(Tranq_Settings );
        i.putExtra("ClockAmPm", (Boolean) clockAmPm);
        getActivity().sendBroadcast(i);
        i = null;
    
    	i = new Intent();
        i.setAction(Tranq_Settings );
        i.putExtra("ClockColor", (Integer) clockColor);
        getActivity().sendBroadcast(i);
        i = null;

    	i = new Intent();
        i.setAction(Tranq_Settings );
        i.putExtra("ClockSize", (Integer) clockSize);
        getActivity().sendBroadcast(i);
        i = null;
        
        i = new Intent();
        i.setAction("tranq_icon_color");
       	i.putExtra("IconColorOn", (Boolean) iconColorOn);
       	getActivity().sendBroadcast(i);
       	i = null;

        i = new Intent();
        i.setAction("tranq_icon_color");
       	i.putExtra("IconColor", (Integer) iconColor);
       	getActivity().sendBroadcast(i);
       	i = null;
        
       	i = new Intent();
       	i.setAction(Tranq_Settings );
   	   	i.putExtra("TogglesOn", (Boolean) togglesShowToggles);
   	   	getActivity().sendBroadcast(i);
   	   	i = null;
       	   	
       	i = new Intent();
        i.setAction(Tranq_Settings );
       	i.putExtra("TogglesTop", (Boolean) togglesTop);
       	getActivity().sendBroadcast(i);
       	i = null;
       	   	
       	i = new Intent();
       	i.setAction(Tranq_Settings );
   	   	i.putExtra("ToggleColor", (Integer) toggleColor);
   	   	getActivity().sendBroadcast(i);
   	   	i = null;

       	i = new Intent();
        i.setAction(Tranq_Settings );
       	i.putExtra("ToggleCustomIconColors", (Boolean) toggleCustomIconColors);
       	getActivity().sendBroadcast(i);
       	i = null;
       
       	i = new Intent();
       	i.setAction(Tranq_Settings );
   	   	i.putExtra("ToggleIconOnColor", (Integer) toggleIconOnColor);
   	   	getActivity().sendBroadcast(i);
   	   	i = null;

       	i = new Intent();
       	i.setAction(Tranq_Settings );
   	   	i.putExtra("ToggleIconInterColor", (Integer) toggleIconInterColor);
   	   	getActivity().sendBroadcast(i);
   	   	i = null;
       	   	
       	i = new Intent();
      	i.setAction(Tranq_Settings );
   	   	i.putExtra("ToggleIconOffColor", (Integer) toggleIconOffColor);
   	   	getActivity().sendBroadcast(i);
   	   	i = null;       	   	
       	   	
       	i = new Intent();
        i.setAction(Tranq_Settings );
        i.putExtra("ToggleShowIndicator", (Boolean) togglesShowIndicator);
        	if ((Boolean) togglesShowIndicator) {
        		i.putExtra("ToggleIndOnColor", toggleIndOnColor);
            	i.putExtra("ToggleIndOffColor", toggleIndOffColor);
            	} else {
            		i.putExtra("ToggleIndOnColor",0);
            		i.putExtra("ToggleIndOffColor",0);
            }
        getActivity().sendBroadcast(i);
        i = null;

       	i = new Intent();
        i.setAction(Tranq_Settings );
        i.putExtra("ToggleShowText", (Boolean) toggleShowText);
        	if ((Boolean) toggleShowText) {
        		i.putExtra("ToggleTextOnColor", (Integer) toggleTextOnColor);
            	i.putExtra("ToggleTextOffColor", (Integer) toggleTextOffColor);
            	} else {
            		i.putExtra("ToggleTextOnColor",0);
            		i.putExtra("ToggleTextOffColor",0);
            }
        getActivity().sendBroadcast(i);
        i = null;

       	i = new Intent();
       	i.setAction(Tranq_Settings );
       	i.putExtra("ToggleShowDivider", (Boolean) toggleShowDivider);
        if ((Boolean) toggleShowDivider) {
           	i.putExtra("ToggleDividerColor", (Integer) toggleDividerColor);
           	} else {
           		i.putExtra("ToggleDividerColor",0);
            }
       	getActivity().sendBroadcast(i);
       	i = null;      
        
       	i = new Intent();
        i.setAction(Tranq_Settings );
        i.putExtra("UpdateToggles", true);
        getActivity().sendBroadcast(i);
        i = null;

      	i = new Intent();
      	i.setAction(Tranq_Settings );
   	   	i.putExtra("ShowFourg", (Boolean) showFourg);
   	   	getActivity().sendBroadcast(i);
   	   	i = null;
        	   	
       	i = new Intent();
       	i.setAction(Tranq_Settings );
   	   	i.putExtra("ShowWifi", (Boolean) showWifi);
   	   	getActivity().sendBroadcast(i);
   	   	i = null;
        	   	
       	i = new Intent();
      	i.setAction(Tranq_Settings );
   	   	i.putExtra("ShowGps", (Boolean) showGps);
   	   	getActivity().sendBroadcast(i);
   	   	i = null;
       
       	i = new Intent();
       	i.setAction(Tranq_Settings );
   	   	i.putExtra("ShowBluetooth", (Boolean) showBluetooth);
   	   	getActivity().sendBroadcast(i);
   	   	i = null;
            
       	i = new Intent();
       	i.setAction(Tranq_Settings );
   	   	i.putExtra("ShowSound", (Boolean) showSound);
   	   	getActivity().sendBroadcast(i);
   	   	i = null;
           
       	i = new Intent();
      	i.setAction(Tranq_Settings );
   	   	i.putExtra("ShowAirplane", (Boolean) showAirplane);
   	   	getActivity().sendBroadcast(i);
   	   	i = null;

       	i = new Intent();
       	i.setAction(Tranq_Settings );
   	   	i.putExtra("ShowBrightness", (Boolean) showBrightness);
   	   	getActivity().sendBroadcast(i);
   	   	i = null;

       	i = new Intent();
      	i.setAction(Tranq_Settings );
   	   	i.putExtra("ShowRotate", (Boolean) showRotate);
   	   	getActivity().sendBroadcast(i);
   	   	i = null;
 
       	i = new Intent();
      	i.setAction(Tranq_Settings );
   	   	i.putExtra("ShowSync", (Boolean) showSync);
   	   	getActivity().sendBroadcast(i);
   	   	i = null;

       	i = new Intent();
       	i.setAction(Tranq_Settings );
   	   	i.putExtra("ShowData", (Boolean) showData);
   	   	getActivity().sendBroadcast(i);
   	   	i = null;            
   	   	
      	i = new Intent();
       	i.setAction(Tranq_Settings );
   	   	i.putExtra("ShowCarrier", (Boolean) showCarrier);
   	   	getActivity().sendBroadcast(i);
   	   	i = null;
       
      	i = new Intent();
        i.setAction(Tranq_Settings );
        i.putExtra("CarrierColor", (Integer) carrierColor);
        getActivity().sendBroadcast(i);
        i = null;
            
       	i = new Intent();
        i.setAction(Tranq_Settings );
        i.putExtra("CustomCarrier", (Boolean) carrierCustom);
        getActivity().sendBroadcast(i);
        i = null;
              
       	i = new Intent();
        i.setAction(Tranq_Settings );
        i.putExtra("CustomCarrierText", (String) carrierCustomText);
        getActivity().sendBroadcast(i);
        i = null;
        	
       	i = new Intent();
        i.setAction(Tranq_Settings );
        i.putExtra("CarrierSize", (Integer) carrierSize);
        getActivity().sendBroadcast(i);
        i = null;
        	
      	i = new Intent();
      	i.setAction(Tranq_Settings );
       	i.putExtra("DateColor", (Integer) dateColor);
       	getActivity().sendBroadcast(i);
       	i = null;        

       	i = new Intent();
        i.setAction(Tranq_Settings );
        i.putExtra("DateSize", (Integer) dateSize);
        getActivity().sendBroadcast(i);
        i = null;       	   		
   	   	
       	i = new Intent();
       	i.setAction(Tranq_Settings );
   	   	i.putExtra("ShowClockButton", (Boolean) quickClock);
   	   	getActivity().sendBroadcast(i);
   	   	i = null;
        	   	
       	i = new Intent();
       	i.setAction(Tranq_Settings );
   	   	i.putExtra("ShowMeterButton", (Boolean) quickMeter);
   	   	getActivity().sendBroadcast(i);
   	   	i = null;
        	   	
       	i = new Intent();
       	i.setAction(Tranq_Settings );
   	   	i.putExtra("ShowNavButton", (Boolean) quickNav);
  	   	getActivity().sendBroadcast(i);
   	   	i = null;
       
       	i = new Intent();
       	i.setAction(Tranq_Settings );
   	   	i.putExtra("ShowLockscreenButton", (Boolean) quickLock);
   	   	getActivity().sendBroadcast(i);
   	   	i = null;
            
       	i = new Intent();
       	i.setAction(Tranq_Settings );
   	   	i.putExtra("ShowPulldownButton", (Boolean) quickPullDown);
   	   	getActivity().sendBroadcast(i);
   	   	i = null;
           
       	i = new Intent();
       	i.setAction(Tranq_Settings );
   	   	i.putExtra("ShowToggleButton", (Boolean) quickToggle);
   	   	getActivity().sendBroadcast(i);
   	   	i = null;

       	i = new Intent();
       	i.setAction(Tranq_Settings );
   	   	i.putExtra("ShowQuietButton", (Boolean) quickQuiet);
   	   	getActivity().sendBroadcast(i);
   	   	i = null;
            
       	i = new Intent();
      	i.setAction(Tranq_Settings );
   	   	i.putExtra("ShowLedButton", (Boolean) quickLed);
   	   	getActivity().sendBroadcast(i);
   	   	i = null;
           
       	i = new Intent();
       	i.setAction(Tranq_Settings );
   	   	i.putExtra("ShowIconButton", (Boolean) quickIcon);
   	   	getActivity().sendBroadcast(i);
   	   	i = null;   
        
    	i = new Intent();
    	i.setAction(Tranq_Settings );
   	   	i.putExtra("SettingsOn", (Boolean) quickSettingsOn);
   	   	getActivity().sendBroadcast(i);
   	   	i = null;

    	i = new Intent();
    	i.setAction(Tranq_Settings );
   	   	i.putExtra("SettingsBottom", (Boolean) quickSettingsBottom);
   	   	getActivity().sendBroadcast(i);
   	   	i = null;
   	   	
    	i = new Intent();
        i.setAction(Tranq_Settings );
        i.putExtra("UpdateSettings", true);
        getActivity().sendBroadcast(i);
        i = null;
        
        
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
				        
				        GetSettings(true);
				        
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
				        mApplyThemeOne.setEnabled(true);
				        
					} else if (whichTheme == 2 ) {
				        prefMgr = getPreferenceManager();
				        prefMgr.setSharedPreferencesName("Tranquility_Settings");
				        prefMgr.setSharedPreferencesMode(Context.MODE_WORLD_READABLE);
				        prefMgr.getSharedPreferences();
				        
				        GetSettings(true);
				        
				        themeMgr = getPreferenceManager();
				        themeMgr.setSharedPreferencesName("Tranquility_Theme_Two");
				        themeMgr.setSharedPreferencesMode(Context.MODE_WORLD_READABLE);
				        themeMgr.getSharedPreferences();
				        themeEditor = themeMgr.getSharedPreferences();
				      
				        SaveTheme();
				        
				        prefMgr = getPreferenceManager();
				        prefMgr.setSharedPreferencesName("Tranquility_Settings");
				        prefMgr.setSharedPreferencesMode(Context.MODE_WORLD_READABLE);
				        prefMgr.getSharedPreferences();
				        prefMgr.getSharedPreferencesName();
				        
						Toast.makeText(getActivity().getBaseContext(), themeTwoName + " has been saved", Toast.LENGTH_SHORT).show();
						mApplyThemeTwo.setEnabled(true);
						
					} else if (whichTheme == 3 ) {
				        prefMgr = getPreferenceManager();
				        prefMgr.setSharedPreferencesName("Tranquility_Settings");
				        prefMgr.setSharedPreferencesMode(Context.MODE_WORLD_READABLE);
				        prefMgr.getSharedPreferences();
				        
				        GetSettings(true);
				        
				        themeMgr = getPreferenceManager();
				        themeMgr.setSharedPreferencesName("Tranquility_Theme_Three");
				        themeMgr.setSharedPreferencesMode(Context.MODE_WORLD_READABLE);
				        themeMgr.getSharedPreferences();
				        themeEditor = themeMgr.getSharedPreferences();
				      
				        SaveTheme();
				        
				        prefMgr = getPreferenceManager();
				        prefMgr.setSharedPreferencesName("Tranquility_Settings");
				        prefMgr.setSharedPreferencesMode(Context.MODE_WORLD_READABLE);
				        prefMgr.getSharedPreferences();
				        prefMgr.getSharedPreferencesName();
				        
						Toast.makeText(getActivity().getBaseContext(), themeThreeName + " has been saved", Toast.LENGTH_SHORT).show();
						mApplyThemeThree.setEnabled(true);
					}
				}
			};
			
    DialogInterface.OnClickListener applyDialogPositiveListener =
            new DialogInterface.OnClickListener() {
						
				@Override
				public void onClick(DialogInterface dialog, int which) {
					if (whichTheme == 1 ) {
				        prefMgr = getPreferenceManager();
				        prefMgr.setSharedPreferencesName("Tranquility_Theme_One");
				        prefMgr.setSharedPreferencesMode(Context.MODE_WORLD_READABLE);
				        prefMgr.getSharedPreferences();
						
				        GetSettings(false);
				        
				        themeMgr = getPreferenceManager();
				        themeMgr.setSharedPreferencesName("Tranquility_Settings");
				        themeMgr.setSharedPreferencesMode(Context.MODE_WORLD_READABLE);
				        themeMgr.getSharedPreferences();
				        themeEditor = themeMgr.getSharedPreferences();
				        
				        ApplyTheme();
				        
				        prefMgr = getPreferenceManager();
				        prefMgr.setSharedPreferencesName("Tranquility_Settings");
				        prefMgr.setSharedPreferencesMode(Context.MODE_WORLD_READABLE);
				        prefMgr.getSharedPreferences();
				        prefMgr.getSharedPreferencesName();
				        
						Toast.makeText(getActivity().getBaseContext(), themeOneName + " has been applied", Toast.LENGTH_SHORT).show();
						
					} else if (whichTheme == 2 ) {
				        prefMgr = getPreferenceManager();
				        prefMgr.setSharedPreferencesName("Tranquility_Theme_Two");
				        prefMgr.setSharedPreferencesMode(Context.MODE_WORLD_READABLE);
				        prefMgr.getSharedPreferences();
						
				        GetSettings(false);
				        
				        themeMgr = getPreferenceManager();
				        themeMgr.setSharedPreferencesName("Tranquility_Settings");
				        themeMgr.setSharedPreferencesMode(Context.MODE_WORLD_READABLE);
				        themeMgr.getSharedPreferences();
				        themeEditor = themeMgr.getSharedPreferences();
				        
				        ApplyTheme();
				        
				        prefMgr = getPreferenceManager();
				        prefMgr.setSharedPreferencesName("Tranquility_Settings");
				        prefMgr.setSharedPreferencesMode(Context.MODE_WORLD_READABLE);
				        prefMgr.getSharedPreferences();
				        prefMgr.getSharedPreferencesName();
				        
	
						Toast.makeText(getActivity().getBaseContext(), themeTwoName + " has been applied", Toast.LENGTH_SHORT).show();
						
					} else if (whichTheme == 3 ) {
				        prefMgr = getPreferenceManager();
				        prefMgr.setSharedPreferencesName("Tranquility_Theme_Three");
				        prefMgr.setSharedPreferencesMode(Context.MODE_WORLD_READABLE);
				        prefMgr.getSharedPreferences();
						
				        GetSettings(false);
				        
				        themeMgr = getPreferenceManager();
				        themeMgr.setSharedPreferencesName("Tranquility_Settings");
				        themeMgr.setSharedPreferencesMode(Context.MODE_WORLD_READABLE);
				        themeMgr.getSharedPreferences();
				        themeEditor = themeMgr.getSharedPreferences();
				        
				        ApplyTheme();
				        
				        prefMgr = getPreferenceManager();
				        prefMgr.setSharedPreferencesName("Tranquility_Settings");
				        prefMgr.setSharedPreferencesMode(Context.MODE_WORLD_READABLE);
				        prefMgr.getSharedPreferences();
				        prefMgr.getSharedPreferencesName();
				        
						Toast.makeText(getActivity().getBaseContext(), themeThreeName + " has been applied", Toast.LENGTH_SHORT).show();
					}
				}
			};
			
    
    
	
					
					
}
