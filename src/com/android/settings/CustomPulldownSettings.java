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




public class CustomPulldownSettings extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener {
    
	private static final String TAG = "CustomSettings";
    
	private static final String SHOW_CARRIER = "show_carrier";
	private static final String CARRIER_COLOR = "carrier_color";
	private static final String CARRIER_CUSTOM = "carrier_custom";
	private static final String CARRIER_CUSTOM_TEXT = "carrier_custom_text";
    
    private CheckBoxPreference mShowCarrier;
    private Preference mCarrierColor;
    private CheckBoxPreference mCarrierCustom;
    private Preference mCarrierCustomText;


	
	
	
	
    /** If there is no setting in the provider, use this. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        addPreferencesFromResource(R.xml.custom_pulldown_settings);
        
        mShowCarrier = (CheckBoxPreference) findPreference(SHOW_CARRIER);
		mShowCarrier.setOnPreferenceChangeListener(this);
        mCarrierColor = (Preference) findPreference(CARRIER_COLOR);
		mCarrierColor.setOnPreferenceChangeListener(this);
        mCarrierCustom = (CheckBoxPreference) findPreference(CARRIER_CUSTOM);
		mCarrierCustom.setOnPreferenceChangeListener(this);
        mCarrierCustomText = (Preference) findPreference(CARRIER_CUSTOM_TEXT);
		mCarrierCustomText.setOnPreferenceChangeListener(this);

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
        if (SHOW_CARRIER.equals(key)) {
           int value = (Boolean) objValue? 1 : 0;
            try {
                Settings.System.putInt(getContentResolver(), Settings.System.STATUSBAR_CARRIER_SHOW, value);
            } catch (NumberFormatException e) {
                Log.e(TAG, "could not save SHOW_CARRIER value", e);
            }
        } else if (CARRIER_COLOR.equals(key)) {
            int value = (Integer) objValue;
            try {
                Settings.System.putInt(getContentResolver(), Settings.System.STATUSBAR_CARRIER_COLOR, value);
            } catch (NumberFormatException e) {
                Log.e(TAG, "could not save CARRIER_COLOR value", e);
            }
        } else if (CARRIER_CUSTOM.equals(key)) {
            int value = (Boolean) objValue? 1 : 0;
            try {
                Settings.System.putInt(getContentResolver(), Settings.System.STATUSBAR_CARRIER_CUSTOM, value);
            } catch (NumberFormatException e) {
                Log.e(TAG, "could not save CARRIER_CUSTOM value", e);
            }
        } else if (CARRIER_CUSTOM_TEXT.equals(key)) {
            try {
                Settings.System.putString(getContentResolver(), Settings.System.STATUSBAR_CARRIER_CUSTOM_TEXT, (String) objValue);
            } catch (NumberFormatException e) {
                Log.e(TAG, "could not save CARRIER_CUSTOM_TEXT value", e);
            }   
        	
        }
 
    

        return true;
    }
    
    
    
}
