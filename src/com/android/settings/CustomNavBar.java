package com.android.settings;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.provider.Settings;
import com.android.settings.R;


public class CustomNavBar extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener {

	private final String Junk_Settings = "JUNK_SETTINGS";
	private final String NAV_BAR_COLOR_ON = "nav_button_color_on";
    private final String NAV_BAR_COLOR = "nav_button_color";
    private final String SHOW_SEARCH_BUTTON = "search_button";
	private final String SHOW_LEFT_MENU_BUTTON = "left_menu_button";
	private final String SHOW_RIGHT_MENU_BUTTON = "right_menu_button";
    private final String SHOW_SEARCH_BUTTON_LAND = "search_button_land";
	private final String SHOW_TOP_MENU_BUTTON_LAND = "top_menu_button_land";
	private final String SHOW_BOT_MENU_BUTTON_LAND = "bottom_menu_button_land";

	private PreferenceManager prefMgr;

	SwitchPreference mNavBarColorOn;
	ColorPickerPreference mNavigationBarColor;
    SwitchPreference mShowSearchButton;
    SwitchPreference mShowLeftMenuButton;
    SwitchPreference mShowRightMenuButton;
    SwitchPreference mShowSearchButtonLand;
    SwitchPreference mShowTopMenuButtonLand;
    SwitchPreference mShowBotMenuButtonLand;
	

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            prefMgr = getPreferenceManager();
            prefMgr.setSharedPreferencesName("Junk_Settings");
            prefMgr.setSharedPreferencesMode(Context.MODE_WORLD_READABLE);
            prefMgr.getSharedPreferences();
            
            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.custom_navbar_settings);

            mNavBarColorOn = (SwitchPreference) findPreference(NAV_BAR_COLOR_ON);
            mNavBarColorOn.setOnPreferenceChangeListener(this);
            mNavigationBarColor = (ColorPickerPreference) findPreference(NAV_BAR_COLOR);
            mNavigationBarColor.setOnPreferenceChangeListener(this);
            mShowSearchButton = (SwitchPreference) findPreference(SHOW_SEARCH_BUTTON);
            mShowSearchButton.setOnPreferenceChangeListener(this);
            mShowLeftMenuButton = (SwitchPreference) findPreference(SHOW_LEFT_MENU_BUTTON);
            mShowLeftMenuButton.setOnPreferenceChangeListener(this);
            mShowRightMenuButton = (SwitchPreference) findPreference(SHOW_RIGHT_MENU_BUTTON);
            mShowRightMenuButton.setOnPreferenceChangeListener(this);
            mShowSearchButtonLand = (SwitchPreference) findPreference(SHOW_SEARCH_BUTTON_LAND);
            mShowSearchButtonLand.setOnPreferenceChangeListener(this);
            mShowTopMenuButtonLand = (SwitchPreference) findPreference(SHOW_TOP_MENU_BUTTON_LAND);
            mShowTopMenuButtonLand.setOnPreferenceChangeListener(this);
            mShowBotMenuButtonLand = (SwitchPreference) findPreference(SHOW_BOT_MENU_BUTTON_LAND);
            mShowBotMenuButtonLand.setOnPreferenceChangeListener(this);
        }


       public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
                Preference preference) {

            return super.onPreferenceTreeClick(preferenceScreen, preference);
        }
       
       

        @Override
        public boolean onPreferenceChange(Preference preference, Object objValue) {
 
        	final String key = preference.getKey();
        	
            if (NAV_BAR_COLOR_ON.equals(key)) {
            	Intent i = new Intent();
            	i.setAction(Junk_Settings );
           	   	i.putExtra("NavColorOn", (Boolean) objValue);
           	   	getActivity().sendBroadcast(i);
           	   	i = null;

            } else  if (NAV_BAR_COLOR.equals(key)) {
            	Intent i = new Intent();
            	i.setAction(Junk_Settings );
           	   	i.putExtra("GlowColor", (Integer) objValue);
           	   	getActivity().sendBroadcast(i);
           	   	i = null;
           
            } else if (SHOW_SEARCH_BUTTON.equals(key)) {
            	Intent i = new Intent();
            	i.setAction(Junk_Settings );
            	i.putExtra("NavShowSearch", (Boolean) objValue);
            	getActivity().sendBroadcast(i);
            	i = null;        

            } else if (SHOW_LEFT_MENU_BUTTON.equals(key)) {
            	Intent i = new Intent();
            	i.setAction(Junk_Settings );
            	i.putExtra("NavShowLeftMenu", (Boolean) objValue);
            	getActivity().sendBroadcast(i);
            	i = null;        

            } else if (SHOW_RIGHT_MENU_BUTTON.equals(key)) {
            	Intent i = new Intent();
            	i.setAction(Junk_Settings );
            	i.putExtra("NavShowRightMenu", (Boolean) objValue);
            	getActivity().sendBroadcast(i);
            	i = null;        

            } else if (SHOW_SEARCH_BUTTON_LAND.equals(key)) {
            	Intent i = new Intent();
            	i.setAction(Junk_Settings );
            	i.putExtra("NavShowSearchLand", (Boolean) objValue);
            	getActivity().sendBroadcast(i);
            	i = null;        

            } else if (SHOW_TOP_MENU_BUTTON_LAND.equals(key)) {
            	Intent i = new Intent();
            	i.setAction(Junk_Settings );
            	i.putExtra("NavShowTopMenuLand", (Boolean) objValue);
            	getActivity().sendBroadcast(i);
            	i = null;        

            } else if (SHOW_BOT_MENU_BUTTON_LAND.equals(key)) {
            	Intent i = new Intent();
            	i.setAction(Junk_Settings );
            	i.putExtra("NavShowBotMenuLand", (Boolean) objValue);
            	getActivity().sendBroadcast(i);
            	i = null;        

            }
            
			return true;
        }

}
