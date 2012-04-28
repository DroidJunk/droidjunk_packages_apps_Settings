package com.android.settings;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.util.Log;

public class CustomJunk extends SettingsPreferenceFragment  implements
Preference.OnPreferenceChangeListener {

	private final String Junk_Battery_Settings = "JUNK_BATTERY_SETTINGS";
	private final String BATTERY_ICONS = "battery_icon_num";
	private String BATTERY_BAR_BOTTOM = "battery_bar_bottom";
	private String BATTERY_BAR_RIGHT = "battery_bar_right";
	private String BATTERY_DEPLETED_COLOR = "battery_depleted_color";
	private String BATTERY_BAR_WIDTH = "battery_bar_width";
	private String BATTERY_LEVEL_ONE = "battery_levels_one";
	private String BATTERY_LEVEL_COLOR_ONE = "battery_levels_color_one";
	private String BATTERY_LEVEL_TWO = "battery_levels_two";
	private String BATTERY_LEVEL_COLOR_TWO = "battery_levels_color_two";
	private String BATTERY_LEVEL_THREE = "battery_levels_three";
	private String BATTERY_LEVEL_COLOR_THREE = "battery_levels_color_three";   
	
    private final String Junk_Icon_Settings = "JUNK_ICON_SETTINGS";
	private final String ICON_COLOR = "icon_color";
	
	
	private PreferenceManager prefMgr;
	private SharedPreferences sharedPref;
	private ListPreference mBatteryIcons;
	PreferenceScreen mOptionScreen;
	private int mBatteryIconValue = 0;
	
	private Preference mIconColor;


	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        prefMgr = getPreferenceManager();
        prefMgr.setSharedPreferencesName("Junk_Settings");
        prefMgr.setSharedPreferencesMode(Context.MODE_WORLD_READABLE);
        prefMgr.getSharedPreferences();
        sharedPref = prefMgr.getSharedPreferences();

        addPreferencesFromResource(R.xml.custom_junk_settings);
        
        mIconColor = (Preference) findPreference(ICON_COLOR);
		mIconColor.setOnPreferenceChangeListener(this);	
        mBatteryIcons = (ListPreference) findPreference(BATTERY_ICONS);
		mBatteryIcons.setOnPreferenceChangeListener(this);
		mBatteryIcons.setValue(sharedPref.getString(BATTERY_ICONS, "0"));
		mBatteryIconValue = Integer.valueOf(sharedPref.getString(BATTERY_ICONS, "0"));
		
		mOptionScreen = (PreferenceScreen) prefMgr.findPreference("custom_battery_options");



    }



	@Override
	public boolean onPreferenceChange(Preference preference, Object newValue) {
     	final String key = preference.getKey();
     	
     	if (BATTERY_ICONS.equals(key)) {
   			mBatteryIconValue = Integer.valueOf((String) newValue);
 		
   			if (mBatteryIconValue == 10) {
   				mOptionScreen.setEnabled(false);
   			} else {
   				mOptionScreen.setEnabled(true);
   			}
   			
   			Log.e("*****************************************","  INTENT  SENT  ");
        	Intent i = new Intent();
        	i.setAction(Junk_Battery_Settings );
       	   	i.putExtra(BATTERY_ICONS, (String) newValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;

            resendIntents();
       	   	
     	} else if (ICON_COLOR.equals(key)) {
         	Intent i = new Intent();
         	i.setAction(Junk_Icon_Settings);
       	   	i.putExtra(ICON_COLOR, (Integer) newValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;
   	   	
     		
     	}
     	return true;

	}
	
	
    private void resendIntents() {
    	
    	Intent i = new Intent();
    	i.setAction(Junk_Battery_Settings );
   	   	i.putExtra(BATTERY_BAR_BOTTOM, sharedPref.getBoolean(BATTERY_BAR_BOTTOM, false));
   	   	getActivity().sendBroadcast(i);
   	   	i = null;       	   	

    	i = new Intent();
    	i.setAction(Junk_Battery_Settings );
   	   	i.putExtra(BATTERY_BAR_RIGHT, sharedPref.getBoolean(BATTERY_BAR_RIGHT, false));
   	   	getActivity().sendBroadcast(i);
   	   	i = null;       	   	

        i = new Intent();
    	i.setAction(Junk_Battery_Settings );
   	   	i.putExtra(BATTERY_DEPLETED_COLOR, sharedPref.getInt(BATTERY_DEPLETED_COLOR, 0xffababab));
   	   	getActivity().sendBroadcast(i);
   	   	i = null;              	   	
   	   	
        i = new Intent();
    	i.setAction(Junk_Battery_Settings );
   	   	i.putExtra(BATTERY_BAR_WIDTH, sharedPref.getInt(BATTERY_BAR_WIDTH, 5));
   	   	getActivity().sendBroadcast(i);
   	   	i = null;       	   	
   	   	
        i = new Intent();
        i.setAction(Junk_Battery_Settings );
        i.putExtra(BATTERY_LEVEL_ONE, sharedPref.getInt(BATTERY_LEVEL_ONE, 10));
        getActivity().sendBroadcast(i);
        i = null;
    
        i = new Intent();
        i.setAction(Junk_Battery_Settings );
        i.putExtra(BATTERY_LEVEL_COLOR_ONE, sharedPref.getInt(BATTERY_LEVEL_COLOR_ONE, 0xffff0000));
        getActivity().sendBroadcast(i);
        i = null;
         
        i = new Intent();
        i.setAction(Junk_Battery_Settings );
        i.putExtra(BATTERY_LEVEL_TWO, sharedPref.getInt(BATTERY_LEVEL_TWO, 30));
        getActivity().sendBroadcast(i);
        i = null;
    
        i = new Intent();
        i.setAction(Junk_Battery_Settings );
        i.putExtra(BATTERY_LEVEL_COLOR_TWO, sharedPref.getInt(BATTERY_LEVEL_COLOR_TWO, 0xffff9000));
        getActivity().sendBroadcast(i);
        i = null;

        i = new Intent();
        i.setAction(Junk_Battery_Settings );
        i.putExtra(BATTERY_LEVEL_THREE, sharedPref.getInt(BATTERY_LEVEL_THREE, 70));
        getActivity().sendBroadcast(i);
        i = null;
    
        i = new Intent();
        i.setAction(Junk_Battery_Settings );
        i.putExtra(BATTERY_LEVEL_COLOR_THREE, sharedPref.getInt(BATTERY_LEVEL_COLOR_THREE, 0xff3792b4));
        getActivity().sendBroadcast(i);
        i = null;
	
}	
    
    

}
