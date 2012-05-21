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
import android.preference.CheckBoxPreference;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.util.Log;


public class CustomPulldownSettings extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener {
    
	private final String Junk_Pulldown_Settings = "JUNK_PULLDOWN_SETTINGS";
	private final String SHOW_BATTERY_LABEL = "show_battery_label";
	private final String BATTERY_LABEL_COLOR = "battery_label_color";
	private final String BATTERY_LABEL_SIZE = "battery_label_size";
	private final String SHOW_TEMP_LABEL = "show_temp_label";
	private final String TEMP_LABEL_COLOR = "temp_label_color";
	private final String TEMP_LABEL_SIZE = "temp_label_size";
	private final String SHOW_CARRIER = "show_carrier";
	private final String CARRIER_COLOR = "carrier_color";
	private final String CARRIER_CUSTOM = "carrier_custom";
	private final String CARRIER_CUSTOM_TEXT = "carrier_custom_text";
	private final String CARRIER_SIZE = "carrier_size";
	private final String DATE_BAR_COLOR = "date_bar_color";
	private final String DATE_COLOR = "date_color";
	private final String DATE_SIZE = "date_size";
	private final String CLEAR_BUTTON_COLOR = "clear_button_color";
	private final String PD_HANDLE_COLOR = "pd_handle_color";
	private final String PD_SHADE_COLOR = "pd_shade_color";
	private final String PD_GRIP_COLOR = "pd_grip_color";
	private final String PD_CARRIER_FRAME_COLOR = "pd_carrier_frame_color";
//	private final String PD_NOTIF_ICON_COLOR = "pd_notif_icon_color";
	private final String PD_NOTIF_ICON_BG_COLOR = "pd_notif_icon_bg_color";
	private final String PD_NOTIF_TEXT_COLOR = "pd_notif_text_color";
	private final String PD_NOTIF_TEXT_BG_COLOR = "pd_notif_text_bg_color";
    
	private PreferenceManager prefMgr;
	private SharedPreferences sharedPref;
	private CheckBoxPreference mShowCarrier;
	private CheckBoxPreference mShowBattery;
	private CheckBoxPreference mShowTemp;
    private Preference mCarrierColor;
    private Preference mBatteryColor;
    private Preference mTempColor;
    private CheckBoxPreference mCarrierCustom;
    private Preference mCarrierCustomText;
    private Preference mCarrierSize;
    private Preference mBatterySize;
    private Preference mTempSize;
    public int carrierSize = 15;
    public int batterySize = 12;
    public int tempSize = 12;
    private Preference mDateBarColor;
    private Preference mDateColor;
    private Preference mDateSize;
    private Preference mCloseBarColor;
    private Preference mClearButtonColor;
    private Preference mShadeColor;
    private Preference mGripColor;
    private Preference mCarrierFrameColor;
//    private Preference mNotifIconColor;
    private Preference mNotifIconBgColor;
    private Preference mNotifTextColor;
    private Preference mNotifTextBgColor;
    public int dateSize = 17;
    
    
    /** If there is no setting in the provider, use this. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefMgr = getPreferenceManager();
        prefMgr.setSharedPreferencesName("Junk_Settings");
        prefMgr.setSharedPreferencesMode(Context.MODE_WORLD_READABLE);
        prefMgr.getSharedPreferences();
       
        addPreferencesFromResource(R.xml.custom_pulldown_settings);
        
        mShowCarrier = (CheckBoxPreference) findPreference(SHOW_CARRIER);
		mShowCarrier.setOnPreferenceChangeListener(this);
        mShowBattery = (CheckBoxPreference) findPreference(SHOW_BATTERY_LABEL);
		mShowBattery.setOnPreferenceChangeListener(this);
	    mBatteryColor = (Preference) findPreference(BATTERY_LABEL_COLOR);
		mBatteryColor.setOnPreferenceChangeListener(this);
	    mBatterySize = (Preference) findPreference(BATTERY_LABEL_SIZE);
		mBatterySize.setOnPreferenceChangeListener(this);
		batterySize = prefMgr.getSharedPreferences().getInt(BATTERY_LABEL_SIZE, batterySize);  
        mShowTemp = (CheckBoxPreference) findPreference(SHOW_TEMP_LABEL);
		mShowTemp.setOnPreferenceChangeListener(this);
	    mTempColor = (Preference) findPreference(TEMP_LABEL_COLOR);
		mTempColor.setOnPreferenceChangeListener(this);
	    mTempSize = (Preference) findPreference(TEMP_LABEL_SIZE);
		mTempSize.setOnPreferenceChangeListener(this);
		tempSize = prefMgr.getSharedPreferences().getInt(TEMP_LABEL_SIZE, tempSize);
		mCarrierColor = (Preference) findPreference(CARRIER_COLOR);
		mCarrierColor.setOnPreferenceChangeListener(this);
        mCarrierCustom = (CheckBoxPreference) findPreference(CARRIER_CUSTOM);
		mCarrierCustom.setOnPreferenceChangeListener(this);
        mCarrierCustomText = (Preference) findPreference(CARRIER_CUSTOM_TEXT);
		mCarrierCustomText.setOnPreferenceChangeListener(this);
	    mCarrierSize = (Preference) findPreference(CARRIER_SIZE);
		mCarrierSize.setOnPreferenceChangeListener(this);
		carrierSize = prefMgr.getSharedPreferences().getInt(CARRIER_SIZE, carrierSize);  
        mDateBarColor = (Preference) findPreference(DATE_BAR_COLOR);
		mDateBarColor.setOnPreferenceChangeListener(this);
        mDateColor = (Preference) findPreference(DATE_COLOR);
		mDateColor.setOnPreferenceChangeListener(this);
		mDateSize = (Preference) findPreference(DATE_SIZE);
		mDateSize.setOnPreferenceChangeListener(this);
		dateSize = prefMgr.getSharedPreferences().getInt(DATE_SIZE, dateSize);  
        mCloseBarColor = (Preference) findPreference(PD_HANDLE_COLOR);
		mCloseBarColor.setOnPreferenceChangeListener(this);
        mClearButtonColor = (Preference) findPreference(CLEAR_BUTTON_COLOR);
		mClearButtonColor.setOnPreferenceChangeListener(this);
        mShadeColor = (Preference) findPreference(PD_SHADE_COLOR);
		mShadeColor.setOnPreferenceChangeListener(this);        
        mGripColor = (Preference) findPreference(PD_GRIP_COLOR);
		mGripColor.setOnPreferenceChangeListener(this);        
        mCarrierFrameColor = (Preference) findPreference(PD_CARRIER_FRAME_COLOR);
		mCarrierFrameColor.setOnPreferenceChangeListener(this);        
//      mNotifIconColor = (Preference) findPreference(PD_NOTIF_ICON_COLOR);
//		mNotifIconColor.setOnPreferenceChangeListener(this);        
        mNotifIconBgColor = (Preference) findPreference(PD_NOTIF_ICON_BG_COLOR);
        mNotifIconBgColor.setOnPreferenceChangeListener(this);        
        mNotifTextColor = (Preference) findPreference(PD_NOTIF_TEXT_COLOR);
        mNotifTextColor.setOnPreferenceChangeListener(this);        
        mNotifTextBgColor = (Preference) findPreference(PD_NOTIF_TEXT_BG_COLOR);
        mNotifTextBgColor.setOnPreferenceChangeListener(this);        

		
		
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

    	if (preference == mCarrierSize) {
			new NumberPickerDialog(preferenceScreen.getContext(),
                    carrierSizeListener,
            		(int) carrierSize,
                    5,
                    30,
                    R.string.carrier_size).show();
			
        } else if (preference == mDateSize) {
			new NumberPickerDialog(preferenceScreen.getContext(),
                    dateSizeListener,
            		(int) dateSize,
                    5,
                    30,
                    R.string.date_size).show();
     
        } else if (preference == mBatterySize) {
			new NumberPickerDialog(preferenceScreen.getContext(),
                    batterySizeListener,
            		(int) batterySize,
                    5,
                    30,
                    R.string.battery_size).show();
			
        } else if (preference == mTempSize) {
			new NumberPickerDialog(preferenceScreen.getContext(),
                    tempSizeListener,
            		(int) tempSize,
                    5,
                    30,
                    R.string.temp_size).show();
			
        }
    	
    	
    	
    	
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }
    

    public boolean onPreferenceChange(Preference preference, Object objValue) {
  
     	final String key = preference.getKey();
        if (SHOW_CARRIER.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Pulldown_Settings );
       	   	i.putExtra(SHOW_CARRIER, (Boolean) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;
       	   	
        } else if (SHOW_BATTERY_LABEL.equals(key)) {
            	Intent i = new Intent();
            	i.setAction(Junk_Pulldown_Settings );
           	   	i.putExtra(SHOW_BATTERY_LABEL, (Boolean) objValue);
           	   	getActivity().sendBroadcast(i);
           	   	i = null;       	
           	   	
        } else if (BATTERY_LABEL_COLOR.equals(key)) {
        	Intent i = new Intent();
            i.setAction(Junk_Pulldown_Settings );
            i.putExtra(BATTERY_LABEL_COLOR, (Integer) objValue);
            getActivity().sendBroadcast(i);
            i = null;           	  

        } else if (BATTERY_LABEL_SIZE.equals(key)) {
        	sharedPref = prefMgr.getSharedPreferences();
        	SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(BATTERY_LABEL_SIZE, batterySize);
            editor.commit();

        	Intent i = new Intent();
            i.setAction(Junk_Pulldown_Settings );
            i.putExtra(BATTERY_LABEL_SIZE, (Integer) batterySize);
            getActivity().sendBroadcast(i);
            i = null;            

        } else if (TEMP_LABEL_COLOR.equals(key)) {
        	Intent i = new Intent();
            i.setAction(Junk_Pulldown_Settings );
            i.putExtra(TEMP_LABEL_COLOR, (Integer) objValue);
            getActivity().sendBroadcast(i);
            i = null;           	  

        } else if (TEMP_LABEL_SIZE.equals(key)) {
        	sharedPref = prefMgr.getSharedPreferences();
        	SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(TEMP_LABEL_SIZE, tempSize);
            editor.commit();

        	Intent i = new Intent();
            i.setAction(Junk_Pulldown_Settings );
            i.putExtra(TEMP_LABEL_SIZE, (Integer) tempSize);
            getActivity().sendBroadcast(i);
            i = null;             
            
        } else if (SHOW_TEMP_LABEL.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Pulldown_Settings );
       	   	i.putExtra(SHOW_TEMP_LABEL, (Boolean) objValue);
       	   	getActivity().sendBroadcast(i);
       	   	i = null;               	   	
           	   	
        } else if (CARRIER_COLOR.equals(key)) {
        	Intent i = new Intent();
            i.setAction(Junk_Pulldown_Settings );
            i.putExtra(CARRIER_COLOR, (Integer) objValue);
            getActivity().sendBroadcast(i);
            i = null;
            
        } else if (CARRIER_CUSTOM.equals(key)) {
        	Intent i = new Intent();
            i.setAction(Junk_Pulldown_Settings );
            i.putExtra(CARRIER_CUSTOM, (Boolean) objValue);
            getActivity().sendBroadcast(i);
            i = null;
              
        } else if (CARRIER_CUSTOM_TEXT.equals(key)) {
        	Intent i = new Intent();
            i.setAction(Junk_Pulldown_Settings );
            i.putExtra(CARRIER_CUSTOM_TEXT, (String) objValue);
            getActivity().sendBroadcast(i);
            i = null;
        	
        } else if (CARRIER_SIZE.equals(key)) {
        	sharedPref = prefMgr.getSharedPreferences();
        	SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(CARRIER_SIZE, carrierSize);
            editor.commit();

        	Intent i = new Intent();
            i.setAction(Junk_Pulldown_Settings );
            i.putExtra(CARRIER_SIZE, (Integer) carrierSize);
            getActivity().sendBroadcast(i);
            i = null;
        	
        } else if (DATE_COLOR.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Pulldown_Settings );
        	i.putExtra(DATE_COLOR, (Integer) objValue);
        	getActivity().sendBroadcast(i);
        	i = null;        

        } else if (DATE_BAR_COLOR.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Pulldown_Settings );
        	i.putExtra(DATE_BAR_COLOR, (Integer) objValue);
        	getActivity().sendBroadcast(i);
        	i = null;        
        	
        	
        } else if (DATE_SIZE.equals(key)) {
        	sharedPref = prefMgr.getSharedPreferences();
        	SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(DATE_SIZE, dateSize);
            editor.commit();

        	Intent i = new Intent();
            i.setAction(Junk_Pulldown_Settings );
            i.putExtra(DATE_SIZE, (Integer) dateSize);
            getActivity().sendBroadcast(i);
            i = null;
            
        } else if (CLEAR_BUTTON_COLOR.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Pulldown_Settings );
        	i.putExtra(CLEAR_BUTTON_COLOR, (Integer) objValue);
        	getActivity().sendBroadcast(i);
        	i = null;      
        	
        } else if (PD_HANDLE_COLOR.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Pulldown_Settings );
        	i.putExtra(PD_HANDLE_COLOR, (Integer) objValue);
        	getActivity().sendBroadcast(i);
        	i = null;              	
        	
        } else if (PD_SHADE_COLOR.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Pulldown_Settings );
        	i.putExtra(PD_SHADE_COLOR, (Integer) objValue);
        	getActivity().sendBroadcast(i);
        	i = null;         	
 
        } else if (PD_GRIP_COLOR.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Pulldown_Settings );
        	i.putExtra(PD_GRIP_COLOR, (Integer) objValue);
        	getActivity().sendBroadcast(i);
        	i = null;           	

        } else if (PD_CARRIER_FRAME_COLOR.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Pulldown_Settings );
        	i.putExtra(PD_CARRIER_FRAME_COLOR, (Integer) objValue);
        	getActivity().sendBroadcast(i);
        	i = null;           	

/*        } else if (PD_NOTIF_ICON_COLOR.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Pulldown_Settings );
        	i.putExtra(PD_NOTIF_ICON_COLOR, (Integer) objValue);
        	getActivity().sendBroadcast(i);
        	i = null; */         	

        } else if (PD_NOTIF_ICON_BG_COLOR.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Pulldown_Settings );
        	i.putExtra(PD_NOTIF_ICON_BG_COLOR, (Integer) objValue);
        	getActivity().sendBroadcast(i);
        	i = null;          	
 
        } else if (PD_NOTIF_TEXT_COLOR.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Pulldown_Settings );
        	i.putExtra(PD_NOTIF_TEXT_COLOR, (Integer) objValue);
        	getActivity().sendBroadcast(i);
        	i = null;          	
        	
        } else if (PD_NOTIF_TEXT_BG_COLOR.equals(key)) {
        	Intent i = new Intent();
        	i.setAction(Junk_Pulldown_Settings );
        	i.putExtra(PD_NOTIF_TEXT_BG_COLOR, (Integer) objValue);
        	getActivity().sendBroadcast(i);
        	i = null;          	

        	
        }
        
        return true;
    }
    
    
    NumberPickerDialog.OnNumberSetListener carrierSizeListener =
            new NumberPickerDialog.OnNumberSetListener() {
    	public void onNumberSet(int limit) {
    		carrierSize = (int) limit;
    		mCarrierSize.getOnPreferenceChangeListener().onPreferenceChange(mCarrierSize, (int) limit);
    	}
    	};    

    NumberPickerDialog.OnNumberSetListener dateSizeListener =
    	new NumberPickerDialog.OnNumberSetListener() {
  			public void onNumberSet(int limit) {
  				dateSize = (int) limit;
  				mDateSize.getOnPreferenceChangeListener().onPreferenceChange(mDateSize, (int) limit);
  			}
    	};    
    
    NumberPickerDialog.OnNumberSetListener batterySizeListener =
    	new NumberPickerDialog.OnNumberSetListener() {
        	public void onNumberSet(int limit) {
        		batterySize = (int) limit;
        		mBatterySize.getOnPreferenceChangeListener().onPreferenceChange(mBatterySize, (int) limit);
        	}
        };
        	
    NumberPickerDialog.OnNumberSetListener tempSizeListener =
        new NumberPickerDialog.OnNumberSetListener() {
        	public void onNumberSet(int limit) {
          		tempSize = (int) limit;
           		mTempSize.getOnPreferenceChangeListener().onPreferenceChange(mTempSize, (int) limit);
          	}
       	};        	
    
    
}
