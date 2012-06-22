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



import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.preference.CheckBoxPreference;
import android.widget.TimePicker;
import android.provider.Settings;






public class CustomQuietSettings extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener {
    
	private final String Junk_QuietTime_Settings = "JUNK_QUIET_TIME_SETTINGS";
	private final String QUIET_TIME = "quiet_time_on";
	private final String START_HOUR = "qt_start_hour";
	private final String START_MIN = "qt_start_min";
	private final String STOP_HOUR = "qt_stop_hour";
	private final String STOP_MIN = "qt_stop_min";
	private final String NOTIF_LED_ON = "qt_led_on";
	private final String NOTIF_SOUND_ON = "qt_sound_on";
	private final String NOTIF_VIBRATE_ON = "qt_vibrate_on";
    
	private PreferenceManager prefMgr;
	private SharedPreferences sharedPref;
	private CheckBoxPreference mQuietTimeOn;
    private Preference mQtStartHour;
    private Preference mQtStartMin;
    private Preference mQtStopHour;
    private Preference mQtStopMin;
    private CheckBoxPreference mQtNotifLedOn;
    private CheckBoxPreference mQtNotifSoundOn;
    private CheckBoxPreference mQtNotifVibrateOn;
  
    public int QtStartTime = 21;
    public int QtStartHour = 0;
    public int QtStartMin = 0;
    public int QtStopTime = 7;
    public int QtStopHour = 0;
    public int QtStopMin = 0;
    
    public int day;
    private String mTitle, mSummaryOn, mSummaryOff;
    
    /** If there is no setting in the provider, use this. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        day = getActivity().getIntent().getIntExtra("junk_qt_day", 1); 
        
        prefMgr = getPreferenceManager();
        prefMgr.setSharedPreferencesName("Junk_Settings");
        prefMgr.setSharedPreferencesMode(Context.MODE_WORLD_READABLE);
        prefMgr.getSharedPreferences();
        
        addPreferencesFromResource(R.xml.custom_quiet_time_settings);
        
        mQuietTimeOn = (CheckBoxPreference) findPreference(QUIET_TIME);
        mQuietTimeOn.setOnPreferenceChangeListener(this);
        mQtStartHour = (Preference) findPreference(START_HOUR);
        mQtStartHour.setOnPreferenceChangeListener(this);
        mQtStartMin = (Preference) findPreference(START_MIN);
        mQtStopHour = (Preference) findPreference(STOP_HOUR);
        mQtStopHour.setOnPreferenceChangeListener(this);
        mQtStopMin = (Preference) findPreference(STOP_MIN);
        mQtNotifLedOn = (CheckBoxPreference) findPreference(NOTIF_LED_ON);
		mQtNotifLedOn.setOnPreferenceChangeListener(this);
		mQtNotifSoundOn = (CheckBoxPreference) findPreference(NOTIF_SOUND_ON);
		mQtNotifSoundOn.setOnPreferenceChangeListener(this);
		mQtNotifVibrateOn = (CheckBoxPreference) findPreference(NOTIF_VIBRATE_ON);
		mQtNotifVibrateOn.setOnPreferenceChangeListener(this);
		
		
		Cursor cur = Settings.QuietTime.getCursor(getActivity().getBaseContext().getContentResolver(), String.valueOf(day));
		sharedPref = prefMgr.getSharedPreferences();
    	SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(QUIET_TIME, cur.getInt(1) == 1);
        editor.putInt(START_HOUR, cur.getInt(2));
        editor.putInt(START_MIN, cur.getInt(3));
        editor.putInt(STOP_HOUR, cur.getInt(4));
        editor.putInt(STOP_MIN, cur.getInt(5));
        editor.putBoolean(NOTIF_LED_ON, cur.getInt(6) == 1);
        editor.putBoolean(NOTIF_SOUND_ON, cur.getInt(7) == 1);
        editor.putBoolean(NOTIF_VIBRATE_ON, cur.getInt(8) == 1);
        editor.commit();

        if (day == 1) {
        	mTitle = "Quiet Time";
        	mSummaryOn = "Quiet Time is on";
        	mSummaryOff = "Quiet Time is off";
        } else if (day == 2) {
        	mTitle = "Sunday";
        	mSummaryOn = "Sunday is on";
        	mSummaryOff = "Sunday is off";
        } else if (day == 3) {
        	mTitle = "Monday";
        	mSummaryOn = "Monday is on";
        	mSummaryOff = "Monday is off";
        } else if (day == 4) {
        	mTitle = "Tuesday";
        	mSummaryOn = "Tuesday is on";
        	mSummaryOff = "Tuesday is off";
        } else if (day == 5) {
        	mTitle = "Wednesday";
        	mSummaryOn = "Wednesday is on";
        	mSummaryOff = "Wednesday is off";
        } else if (day == 6) {
        	mTitle = "Thursday";
        	mSummaryOn = "Thursday is on";
        	mSummaryOff = "Thursday is off";
        } else if (day == 7) {
        	mTitle = "Friday";
        	mSummaryOn = "Friday is on";
        	mSummaryOff = "Friday is off";
        } else if (day == 8) {
        	mTitle = "Saturday";
        	mSummaryOn = "Saturday is on";
        	mSummaryOff = "Saturday is off";
        };
        
        
        
        
        mQuietTimeOn.setChecked(cur.getInt(1) == 1);
        mQuietTimeOn.setTitle(mTitle);
        mQuietTimeOn.setSummaryOn(mSummaryOn);
        mQuietTimeOn.setSummaryOff(mSummaryOff);
        mQtNotifLedOn.setChecked(cur.getInt(6) == 1);
        mQtNotifSoundOn.setChecked(cur.getInt(7) == 1);
        mQtNotifVibrateOn.setChecked(cur.getInt(8) == 1);
		QtStartHour = prefMgr.getSharedPreferences().getInt(START_HOUR, 21);
		QtStartMin = prefMgr.getSharedPreferences().getInt(START_MIN, 0);
		QtStopHour = prefMgr.getSharedPreferences().getInt(STOP_HOUR, 7);
		QtStopMin = prefMgr.getSharedPreferences().getInt(STOP_MIN, 0);
		
    }

    

    private void updateDb(){
		 ContentValues values = new ContentValues(8);
         // Write the quiet time values to the database           
         values.put(Settings.QuietTime.QT_ENABLED, prefMgr.getSharedPreferences().getBoolean(QUIET_TIME, false));
         values.put(Settings.QuietTime.QT_START_HOUR, prefMgr.getSharedPreferences().getInt(START_HOUR, 21));
         values.put(Settings.QuietTime.QT_START_MIN, prefMgr.getSharedPreferences().getInt(START_MIN, 0));
         values.put(Settings.QuietTime.QT_STOP_HOUR, prefMgr.getSharedPreferences().getInt(STOP_HOUR, 7));
         values.put(Settings.QuietTime.QT_STOP_MIN, prefMgr.getSharedPreferences().getInt(STOP_MIN, 0));
         values.put(Settings.QuietTime.QT_LED_ON, prefMgr.getSharedPreferences().getBoolean(NOTIF_LED_ON, true));
         values.put(Settings.QuietTime.QT_SOUND_ON, prefMgr.getSharedPreferences().getBoolean(NOTIF_SOUND_ON, true));
         values.put(Settings.QuietTime.QT_VIBRATE_ON, prefMgr.getSharedPreferences().getBoolean(NOTIF_VIBRATE_ON, true));

         Settings.QuietTime.updateQT(getActivity().getBaseContext().getContentResolver(), values, String.valueOf(day));
         
        
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

    	if (preference == mQtStartHour) {
			new TimePickerDialog(preferenceScreen.getContext(),
                    startTimeListener,
            		QtStartHour,
                    QtStartMin,
                    false).show();
        } else if (preference == mQtStopHour) {
        	new TimePickerDialog(preferenceScreen.getContext(),
                    stopTimeListener,
            		QtStopHour,
                    QtStopMin,
                    false).show();
        }
 
    	return super.onPreferenceTreeClick(preferenceScreen, preference);
    }
  
    

    public boolean onPreferenceChange(Preference preference, Object objValue) {
  
     	final String key = preference.getKey();
     	if (QUIET_TIME.equals(key)) {
        	sharedPref = prefMgr.getSharedPreferences();
        	SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean(QUIET_TIME, (Boolean) objValue);
            editor.commit();
            
        } else if (START_HOUR.equals(key)) {
        	sharedPref = prefMgr.getSharedPreferences();
        	SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(START_HOUR, QtStartHour);
            editor.putInt(START_MIN, QtStartMin);
            editor.commit();

        } else if (STOP_HOUR.equals(key)) {
          	sharedPref = prefMgr.getSharedPreferences();
           	SharedPreferences.Editor editor = sharedPref.edit();
           	editor.putInt(STOP_HOUR, QtStopHour);
            editor.putInt(STOP_MIN, QtStopMin);
            editor.commit();
                
        }  else if (NOTIF_LED_ON.equals(key)) {
        	sharedPref = prefMgr.getSharedPreferences();
        	SharedPreferences.Editor editor = sharedPref.edit();
        	editor.putBoolean(NOTIF_LED_ON, (Boolean) objValue);
            editor.commit();
            
        } else if (NOTIF_SOUND_ON.equals(key)) {
        	sharedPref = prefMgr.getSharedPreferences();
        	SharedPreferences.Editor editor = sharedPref.edit();
        	editor.putBoolean(NOTIF_SOUND_ON, (Boolean) objValue);
            editor.commit();
            
        } else if (NOTIF_VIBRATE_ON.equals(key)) {
        	sharedPref = prefMgr.getSharedPreferences();
        	SharedPreferences.Editor editor = sharedPref.edit();
        	editor.putBoolean(NOTIF_VIBRATE_ON, (Boolean) objValue);
            editor.commit();
        }
    	
     	
     	updateDb();     
     	Intent i = new Intent();
     	i.setAction(Junk_QuietTime_Settings );
     	getActivity().sendBroadcast(i);
     	i = null;   
        return true;
    }
    
    
    
    TimePickerDialog.OnTimeSetListener startTimeListener =
            new TimePickerDialog.OnTimeSetListener() {
				
				@Override
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				
					QtStartHour = hourOfDay;
					QtStartMin = minute;
		    		mQtStartHour.getOnPreferenceChangeListener().onPreferenceChange(mQtStartHour, hourOfDay);
				}
			}; 
   
			
    TimePickerDialog.OnTimeSetListener stopTimeListener =
            new TimePickerDialog.OnTimeSetListener() {
						
			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
							
				QtStopHour = hourOfDay;
				QtStopMin = minute;
				mQtStopHour.getOnPreferenceChangeListener().onPreferenceChange(mQtStopHour, hourOfDay);
			}
		}; 
    
    
		
		
		
    
}
