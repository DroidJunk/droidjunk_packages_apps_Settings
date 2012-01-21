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
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.widget.TimePicker;




public class CustomQuietSettings extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener {
    
	private final String Tranq_Settings = "TRANQ_SETTINGS";
	private final String QUIET_TIME = "quiet_time_on";
	private final String START_TIME = "qt_start_time";
	private final String STOP_TIME = "qt_stop_time";
	private final String NOTIF_LED_ON = "qt_led_on";
	private final String NOTIF_SOUND_ON = "qt_sound_on";
	private final String NOTIF_VIBRATE_ON = "qt_vibrate_on";
    
	private PreferenceManager prefMgr;
	private SharedPreferences sharedPref;
	private CheckBoxPreference mQuietTimeOn;
    private Preference mQtStartTime;
    private Preference mQtStopTime;
    private CheckBoxPreference mQtNotifLedOn;
    private CheckBoxPreference mQtNotifSoundOn;
    private CheckBoxPreference mQtNotifVibrateOn;
  
    public int QtStartTime = 21;
    public int QtStartHour = 0;
    public int QtStartMin = 0;
    public int QtStopTime = 7;
    public int QtStopHour = 0;
    public int QtStopMin = 0;
    
    
    
    /** If there is no setting in the provider, use this. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefMgr = getPreferenceManager();
        prefMgr.setSharedPreferencesName("Tranquility_Settings");
        prefMgr.setSharedPreferencesMode(Context.MODE_WORLD_READABLE);
        prefMgr.getSharedPreferences();
       
        addPreferencesFromResource(R.xml.custom_pulldown_settings);
        
        mQuietTimeOn = (CheckBoxPreference) findPreference(QUIET_TIME);
        mQuietTimeOn.setOnPreferenceChangeListener(this);
        mQtStartTime = (Preference) findPreference(START_TIME);
        mQtStartTime.setOnPreferenceChangeListener(this);
        mQtStopTime = (Preference) findPreference(STOP_TIME);
        mQtStopTime.setOnPreferenceChangeListener(this);
		mQtNotifLedOn = (CheckBoxPreference) findPreference(NOTIF_LED_ON);
		mQtNotifLedOn.setOnPreferenceChangeListener(this);
		mQtNotifSoundOn = (CheckBoxPreference) findPreference(NOTIF_SOUND_ON);
		mQtNotifSoundOn.setOnPreferenceChangeListener(this);
		mQtNotifVibrateOn = (CheckBoxPreference) findPreference(NOTIF_VIBRATE_ON);
		mQtNotifVibrateOn.setOnPreferenceChangeListener(this);
		QtStartTime = prefMgr.getSharedPreferences().getInt(START_TIME, 21);
		QtStartHour = QtStartTime / 100;
		QtStartMin = QtStartTime - ((QtStartTime / 100) * 100);
		QtStopTime = prefMgr.getSharedPreferences().getInt(STOP_TIME, 7);
		QtStopHour = QtStopTime / 100;
		QtStopMin = QtStopMin = QtStopTime - ((QtStopTime / 100) * 100);
		


    
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

    	if (preference == mQtStartTime) {
			new TimePickerDialog(preferenceScreen.getContext(),
                    startTimeListener,
            		QtStartHour,
                    QtStartMin,
                    false).show();
        }
    	
    	
    	
    	
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }
    

    public boolean onPreferenceChange(Preference preference, Object objValue) {
  
     	final String key = preference.getKey();
        if (QUIET_TIME.equals(key)) {

        } 
        
        return true;
    }
    
    
    TimePickerDialog.OnTimeSetListener startTimeListener =
            new TimePickerDialog.OnTimeSetListener() {
				
				@Override
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
					// TODO Auto-generated method stub
					QtStartTime = hourOfDay + minute;
		    		mQtStartTime.getOnPreferenceChangeListener().onPreferenceChange(mQtStartTime, (int) hourOfDay + minute);
					
				}
			}; 
   

    
    
    
}
