package com.android.settings;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.preference.CheckBoxPreference;
import android.provider.Settings;
import com.android.settings.R;


public class CustomNavBarSettings extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener {

	private final String Junk_NavBar_Settings = "JUNK_NAVBAR_SETTINGS";
    private final String NAV_BAR_COLOR = "nav_button_color";
    private final String SHOW_SEARCH_BUTTON = "search_button";
	private final String SHOW_LEFT_MENU_BUTTON = "left_menu_button";
	private final String SHOW_RIGHT_MENU_BUTTON = "right_menu_button";
    private final String SHOW_SEARCH_BUTTON_LAND = "search_button_land";
	private final String SHOW_TOP_MENU_BUTTON_LAND = "top_menu_button_land";
	private final String SHOW_BOT_MENU_BUTTON_LAND = "bottom_menu_button_land";

	private PreferenceManager prefMgr;

	ColorPickerPreference mNavigationBarColor;
    CheckBoxPreference mShowSearchButton;
    CheckBoxPreference mShowLeftMenuButton;
    CheckBoxPreference mShowRightMenuButton;
    CheckBoxPreference mShowSearchButtonLand;
    CheckBoxPreference mShowTopMenuButtonLand;
    CheckBoxPreference mShowBotMenuButtonLand;
	

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            prefMgr = getPreferenceManager();
            prefMgr.setSharedPreferencesName("Junk_Settings");
            prefMgr.setSharedPreferencesMode(Context.MODE_WORLD_READABLE);
            prefMgr.getSharedPreferences();
            
            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.custom_navbar_settings);

            mNavigationBarColor = (ColorPickerPreference) findPreference(NAV_BAR_COLOR);
            mNavigationBarColor.setOnPreferenceChangeListener(this);
            mShowSearchButton = (CheckBoxPreference) findPreference(SHOW_SEARCH_BUTTON);
            mShowSearchButton.setOnPreferenceChangeListener(this);
            mShowLeftMenuButton = (CheckBoxPreference) findPreference(SHOW_LEFT_MENU_BUTTON);
            mShowLeftMenuButton.setOnPreferenceChangeListener(this);
            mShowRightMenuButton = (CheckBoxPreference) findPreference(SHOW_RIGHT_MENU_BUTTON);
            mShowRightMenuButton.setOnPreferenceChangeListener(this);
            mShowSearchButtonLand = (CheckBoxPreference) findPreference(SHOW_SEARCH_BUTTON_LAND);
            mShowSearchButtonLand.setOnPreferenceChangeListener(this);
            mShowTopMenuButtonLand = (CheckBoxPreference) findPreference(SHOW_TOP_MENU_BUTTON_LAND);
            mShowTopMenuButtonLand.setOnPreferenceChangeListener(this);
            mShowBotMenuButtonLand = (CheckBoxPreference) findPreference(SHOW_BOT_MENU_BUTTON_LAND);
            mShowBotMenuButtonLand.setOnPreferenceChangeListener(this);
        }


       public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
                Preference preference) {

            return super.onPreferenceTreeClick(preferenceScreen, preference);
        }
       
       

        @Override
        public boolean onPreferenceChange(Preference preference, Object objValue) {
 
        	final String key = preference.getKey();
        	
        	if (NAV_BAR_COLOR.equals(key)) {
            	Intent i = new Intent();
            	i.setAction(Junk_NavBar_Settings );
           	   	i.putExtra(NAV_BAR_COLOR, (Integer) objValue);
           	   	getActivity().sendBroadcast(i);
           	   	i = null;
           
            } else if (SHOW_SEARCH_BUTTON.equals(key)) {
            	Intent i = new Intent();
            	i.setAction(Junk_NavBar_Settings );
            	i.putExtra(SHOW_SEARCH_BUTTON, (Boolean) objValue);
            	getActivity().sendBroadcast(i);
            	i = null;        

            } else if (SHOW_LEFT_MENU_BUTTON.equals(key)) {
            	Intent i = new Intent();
            	i.setAction(Junk_NavBar_Settings );
            	i.putExtra(SHOW_LEFT_MENU_BUTTON, (Boolean) objValue);
            	getActivity().sendBroadcast(i);
            	i = null;        

            } else if (SHOW_RIGHT_MENU_BUTTON.equals(key)) {
            	Intent i = new Intent();
            	i.setAction(Junk_NavBar_Settings );
            	i.putExtra(SHOW_RIGHT_MENU_BUTTON, (Boolean) objValue);
            	getActivity().sendBroadcast(i);
            	i = null;        

            } else if (SHOW_SEARCH_BUTTON_LAND.equals(key)) {
            	Intent i = new Intent();
            	i.setAction(Junk_NavBar_Settings );
            	i.putExtra(SHOW_SEARCH_BUTTON_LAND, (Boolean) objValue);
            	getActivity().sendBroadcast(i);
            	i = null;        

            } else if (SHOW_TOP_MENU_BUTTON_LAND.equals(key)) {
            	Intent i = new Intent();
            	i.setAction(Junk_NavBar_Settings );
            	i.putExtra(SHOW_TOP_MENU_BUTTON_LAND, (Boolean) objValue);
            	getActivity().sendBroadcast(i);
            	i = null;        

            } else if (SHOW_BOT_MENU_BUTTON_LAND.equals(key)) {
            	Intent i = new Intent();
            	i.setAction(Junk_NavBar_Settings );
            	i.putExtra(SHOW_BOT_MENU_BUTTON_LAND, (Boolean) objValue);
            	getActivity().sendBroadcast(i);
            	i = null;        

            }
            
			return true;
        }

}
