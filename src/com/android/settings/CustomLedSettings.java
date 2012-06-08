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



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.DJSeekBarPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



public class CustomLedSettings extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener {
 

	private final String Junk_Settings = "JUNK_SETTINGS";
	private final String DEFAULT_LED_COLOR = "default_led_color";	
	private final String DEFAULT_LED_ON_MS = "default_led_on_ms";
	private final String DEFAULT_LED_OFF_MS = "default_led_off_ms";
	private final String PULSE_LED_SCREEN_ON = "pulse_led_screen_on";
	
	private final String INCOMING_CALL_LED_COLOR = "incoming_call_color";
	private final String INCOMING_CALL_LED_PULSE = "incoming_call_pulse";
	private final String INCOMING_CALL_LED_ON_MS = "incoming_led_on_ms";
	private final String INCOMING_CALL_LED_OFF_MS = "incoming_led_off_ms";
	
	private final String MISSED_CALL_LED_COLOR = "missed_call_color";
	private final String MISSED_CALL_LED_PULSE = "missed_call_pulse";
	private final String MISSED_CALL_LED_ON_MS = "missed_call_led_on_ms";
	private final String MISSED_CALL_LED_OFF_MS = "missed_call_led_off_ms";
	
	private final String VOICE_MAIL_LED_COLOR = "voice_mail_color";
	private final String VOICE_MAIL_LED_PULSE = "voice_mail_pulse";
	private final String VOICE_MAIL_LED_ON_MS = "voice_mail_led_on_ms";
	private final String VOICE_MAIL_LED_OFF_MS = "voice_mail_led_off_ms";
	
	private PreferenceManager prefMgr;
	private SharedPreferences sharedPref;

	private Preference mDefaultLedColor;
	private DJSeekBarPreference mDefaultLedOnMs;
	private DJSeekBarPreference mDefaultLedOffMs;
	private CheckBoxPreference mPulseLedScreenOn;
	private Boolean LedOverrideApps;
	private Boolean LedOverrideEmail;
	private Boolean LedOverrideMms;
    private Boolean PulseLedScreenOn;
    
    
    private Preference mIncomingCallColor;
	private CheckBoxPreference mIncomingCallPulse;
	private DJSeekBarPreference mIncomingCallLedOnMs;
	private DJSeekBarPreference mIncomingCallLedOffMs;
	private Boolean IncomingCallPulse;
    

    private Preference mMissedCallColor;
	private CheckBoxPreference mMissedCallPulse;
	private DJSeekBarPreference mMissedCallLedOnMs;
	private DJSeekBarPreference mMissedCallLedOffMs;
	private Boolean MissedCallPulse;
	
    private Preference mVoiceMailColor;
	private CheckBoxPreference mVoiceMailPulse;
	private DJSeekBarPreference mVoiceMailLedOnMs;
	private DJSeekBarPreference mVoiceMailLedOffMs;
	private Boolean VoiceMailPulse;
	
	private Preference mAppList;
	
    /** If there is no setting in the provider, use this. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefMgr = getPreferenceManager();
        prefMgr.setSharedPreferencesName("Junk_Settings");
        prefMgr.setSharedPreferencesMode(Context.MODE_WORLD_READABLE);
        prefMgr.getSharedPreferences();
        
        addPreferencesFromResource(R.xml.custom_led_settings);
        
        mDefaultLedColor = (Preference) findPreference(DEFAULT_LED_COLOR);
        mDefaultLedColor.setOnPreferenceChangeListener(this);
        mDefaultLedOnMs = (DJSeekBarPreference) findPreference(DEFAULT_LED_ON_MS);
        mDefaultLedOnMs.setOnPreferenceChangeListener(this);
        mDefaultLedOffMs = (DJSeekBarPreference) findPreference(DEFAULT_LED_OFF_MS);
        mDefaultLedOffMs.setOnPreferenceChangeListener(this);
        mPulseLedScreenOn = (CheckBoxPreference) findPreference(PULSE_LED_SCREEN_ON);
        mPulseLedScreenOn.setOnPreferenceChangeListener(this);


        mIncomingCallColor = (Preference) findPreference(INCOMING_CALL_LED_COLOR);
        mIncomingCallColor.setOnPreferenceChangeListener(this);
        mIncomingCallPulse = (CheckBoxPreference) findPreference(INCOMING_CALL_LED_PULSE);
        mIncomingCallPulse.setOnPreferenceChangeListener(this);
        mIncomingCallLedOnMs = (DJSeekBarPreference) findPreference(INCOMING_CALL_LED_ON_MS);
        mIncomingCallLedOnMs.setOnPreferenceChangeListener(this);
        mIncomingCallLedOffMs = (DJSeekBarPreference) findPreference(INCOMING_CALL_LED_OFF_MS);
        mIncomingCallLedOffMs.setOnPreferenceChangeListener(this);        
        
        mMissedCallColor = (Preference) findPreference(MISSED_CALL_LED_COLOR);
        mMissedCallColor.setOnPreferenceChangeListener(this);
        mMissedCallPulse = (CheckBoxPreference) findPreference(MISSED_CALL_LED_PULSE);
        mMissedCallPulse.setOnPreferenceChangeListener(this);
        mMissedCallLedOnMs = (DJSeekBarPreference) findPreference(MISSED_CALL_LED_ON_MS);
        mMissedCallLedOnMs.setOnPreferenceChangeListener(this);
        mMissedCallLedOffMs = (DJSeekBarPreference) findPreference(MISSED_CALL_LED_OFF_MS);
        mMissedCallLedOffMs.setOnPreferenceChangeListener(this);                
        
   
        mVoiceMailColor = (Preference) findPreference(VOICE_MAIL_LED_COLOR);
        mVoiceMailColor.setOnPreferenceChangeListener(this);
        mVoiceMailPulse = (CheckBoxPreference) findPreference(VOICE_MAIL_LED_PULSE);
        mVoiceMailPulse.setOnPreferenceChangeListener(this);
        mVoiceMailLedOnMs = (DJSeekBarPreference) findPreference(VOICE_MAIL_LED_ON_MS);
        mVoiceMailLedOnMs.setOnPreferenceChangeListener(this);
        mVoiceMailLedOffMs = (DJSeekBarPreference) findPreference(VOICE_MAIL_LED_OFF_MS);
        mVoiceMailLedOffMs.setOnPreferenceChangeListener(this);            
        
        
        mAppList = (Preference) findPreference("led_override_list");
        mAppList.setOnPreferenceChangeListener(this);
        
        
        
		Cursor cur = Settings.NotifOptions.getDefaultLed(getActivity().getBaseContext().getContentResolver());
		sharedPref = prefMgr.getSharedPreferences();
    	SharedPreferences.Editor editor = sharedPref.edit();
    	editor.putBoolean(PULSE_LED_SCREEN_ON, cur.getString(2).equals("PulseScreenOn=true"));
        editor.putInt(DEFAULT_LED_COLOR, cur.getInt(3));
        editor.putInt(DEFAULT_LED_ON_MS, cur.getInt(4));
        editor.putInt(DEFAULT_LED_OFF_MS, cur.getInt(5));
        editor.commit();

		cur = Settings.NotifOptions.getIncomingCallLed(getActivity().getBaseContext().getContentResolver());
    	IncomingCallPulse = cur.getString(2).equals("true");
		editor.putBoolean(INCOMING_CALL_LED_PULSE, IncomingCallPulse);
        editor.putInt(INCOMING_CALL_LED_COLOR, cur.getInt(3));
        editor.putInt(INCOMING_CALL_LED_ON_MS, cur.getInt(4));
        editor.putInt(INCOMING_CALL_LED_OFF_MS, cur.getInt(5));
        editor.commit();        
        
        
		cur = Settings.NotifOptions.getMissedCallLed(getActivity().getBaseContext().getContentResolver());
    	MissedCallPulse = cur.getString(2).equals("true");
		editor.putBoolean(MISSED_CALL_LED_PULSE, MissedCallPulse);
        editor.putInt(MISSED_CALL_LED_COLOR, cur.getInt(3));
        editor.putInt(MISSED_CALL_LED_ON_MS, cur.getInt(4));
        editor.putInt(MISSED_CALL_LED_OFF_MS, cur.getInt(5));
        editor.commit();             
        
		cur = Settings.NotifOptions.getVoiceMailLed(getActivity().getBaseContext().getContentResolver());
    	VoiceMailPulse = cur.getString(2).equals("true");
		editor.putBoolean(VOICE_MAIL_LED_PULSE, VoiceMailPulse);
        editor.putInt(VOICE_MAIL_LED_COLOR, cur.getInt(3));
        editor.putInt(VOICE_MAIL_LED_ON_MS, cur.getInt(4));
        editor.putInt(VOICE_MAIL_LED_OFF_MS, cur.getInt(5));
        editor.commit();          
        
        
        mDefaultLedOnMs.setMax(50);
        mDefaultLedOnMs.setDefaultValue(prefMgr.getSharedPreferences().getInt(DEFAULT_LED_ON_MS, 3));
        mDefaultLedOnMs.setProgress(prefMgr.getSharedPreferences().getInt(DEFAULT_LED_ON_MS, 3));      
        mDefaultLedOffMs.setMax(50);
        mDefaultLedOffMs.setDefaultValue(prefMgr.getSharedPreferences().getInt(DEFAULT_LED_OFF_MS, 3));
        mDefaultLedOffMs.setProgress(prefMgr.getSharedPreferences().getInt(DEFAULT_LED_OFF_MS, 3));        
        
        
        mIncomingCallLedOnMs.setMax(50);
        mIncomingCallLedOnMs.setDefaultValue(prefMgr.getSharedPreferences().getInt(INCOMING_CALL_LED_ON_MS, 3));
        mIncomingCallLedOnMs.setProgress(prefMgr.getSharedPreferences().getInt(INCOMING_CALL_LED_ON_MS, 3)); 
        mIncomingCallLedOffMs.setMax(50);
        mIncomingCallLedOffMs.setDefaultValue(prefMgr.getSharedPreferences().getInt(INCOMING_CALL_LED_OFF_MS, 3));
        mIncomingCallLedOffMs.setProgress(prefMgr.getSharedPreferences().getInt(INCOMING_CALL_LED_OFF_MS, 3)); 

        mMissedCallLedOnMs.setMax(50);
        mMissedCallLedOnMs.setDefaultValue(prefMgr.getSharedPreferences().getInt(MISSED_CALL_LED_ON_MS, 3));
        mMissedCallLedOnMs.setProgress(prefMgr.getSharedPreferences().getInt(MISSED_CALL_LED_ON_MS, 3)); 
        mMissedCallLedOffMs.setMax(50);
        mMissedCallLedOffMs.setDefaultValue(prefMgr.getSharedPreferences().getInt(MISSED_CALL_LED_OFF_MS, 3));
        mMissedCallLedOffMs.setProgress(prefMgr.getSharedPreferences().getInt(MISSED_CALL_LED_OFF_MS, 3)); 
        
        mVoiceMailLedOnMs.setMax(50);
        mVoiceMailLedOnMs.setDefaultValue(prefMgr.getSharedPreferences().getInt(VOICE_MAIL_LED_ON_MS, 3));
        mVoiceMailLedOnMs.setProgress(prefMgr.getSharedPreferences().getInt(VOICE_MAIL_LED_ON_MS, 3)); 
        mVoiceMailLedOffMs.setMax(50);
        mVoiceMailLedOffMs.setDefaultValue(prefMgr.getSharedPreferences().getInt(VOICE_MAIL_LED_OFF_MS, 3));
        mVoiceMailLedOffMs.setProgress(prefMgr.getSharedPreferences().getInt(VOICE_MAIL_LED_OFF_MS, 3)); 
        
        PulseLedScreenOn = prefMgr.getSharedPreferences().getBoolean(PULSE_LED_SCREEN_ON, false);
        IncomingCallPulse = prefMgr.getSharedPreferences().getBoolean(INCOMING_CALL_LED_PULSE, true);
        MissedCallPulse = prefMgr.getSharedPreferences().getBoolean(MISSED_CALL_LED_PULSE, true);
        VoiceMailPulse = prefMgr.getSharedPreferences().getBoolean(VOICE_MAIL_LED_PULSE, true);
		
        

        
        
    }

    private void updateDb(){
 		 ContentValues values = new ContentValues(4);
         // Write the default led option values to the database     
 		 values.put(Settings.NotifOptions.PKG_NAME, "PulseScreenOn=" + PulseLedScreenOn.toString());
 		 values.put(Settings.NotifOptions.LED_COLOR, prefMgr.getSharedPreferences().getInt(DEFAULT_LED_COLOR, -1));
         values.put(Settings.NotifOptions.LED_ON_MS, mDefaultLedOnMs.getProgress());
         values.put(Settings.NotifOptions.LED_OFF_MS, mDefaultLedOffMs.getProgress());
          
         Settings.NotifOptions.updateDefaultLed(getActivity().getBaseContext().getContentResolver(), values);
         values = null;
         
 		 values = new ContentValues(4);
         // Write the default led option values to the database     
 		 values.put(Settings.NotifOptions.PKG_NAME, IncomingCallPulse.toString());
 		 values.put(Settings.NotifOptions.LED_COLOR, prefMgr.getSharedPreferences().getInt(INCOMING_CALL_LED_COLOR, -1));
         values.put(Settings.NotifOptions.LED_ON_MS, mIncomingCallLedOnMs.getProgress());
         values.put(Settings.NotifOptions.LED_OFF_MS, mIncomingCallLedOffMs.getProgress());
          
         Settings.NotifOptions.updateIncomingCallLed(getActivity().getBaseContext().getContentResolver(), values);   
         values = null;
         
 		 values = new ContentValues(4);
         // Write the default led option values to the database     
 		 values.put(Settings.NotifOptions.PKG_NAME, MissedCallPulse.toString());
 		 values.put(Settings.NotifOptions.LED_COLOR, prefMgr.getSharedPreferences().getInt(MISSED_CALL_LED_COLOR, -1));
         values.put(Settings.NotifOptions.LED_ON_MS, mMissedCallLedOnMs.getProgress());
         values.put(Settings.NotifOptions.LED_OFF_MS, mMissedCallLedOffMs.getProgress());
          
         Settings.NotifOptions.updateMissedCallLed(getActivity().getBaseContext().getContentResolver(), values);   
         values = null;         
         
         
 		 values = new ContentValues(4);
         // Write the default led option values to the database     
 		 values.put(Settings.NotifOptions.PKG_NAME, VoiceMailPulse.toString());
 		 values.put(Settings.NotifOptions.LED_COLOR, prefMgr.getSharedPreferences().getInt(VOICE_MAIL_LED_COLOR, -1));
         values.put(Settings.NotifOptions.LED_ON_MS, mVoiceMailLedOnMs.getProgress());
         values.put(Settings.NotifOptions.LED_OFF_MS, mVoiceMailLedOffMs.getProgress());
          
         Settings.NotifOptions.updateVoiceMailLed(getActivity().getBaseContext().getContentResolver(), values);   
         values = null;     
       
         
         
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
       
    	
    	if (preference == mAppList) AppListDialog(); 
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }
    

    public boolean onPreferenceChange(Preference preference, Object objValue) {
    	
    	final String key = preference.getKey();
     	if (DEFAULT_LED_COLOR.equals(key)) {
        	sharedPref = prefMgr.getSharedPreferences();
        	SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(DEFAULT_LED_COLOR, (Integer) objValue);
            editor.commit();
            
        } else if (DEFAULT_LED_ON_MS.equals(key)) {
        	sharedPref = prefMgr.getSharedPreferences();
        	SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(DEFAULT_LED_ON_MS, mDefaultLedOnMs.getProgress());
            editor.commit();

        } else if (DEFAULT_LED_OFF_MS.equals(key)) {
          	sharedPref = prefMgr.getSharedPreferences();
           	SharedPreferences.Editor editor = sharedPref.edit();
           	editor.putInt(DEFAULT_LED_OFF_MS, mDefaultLedOffMs.getProgress());
            editor.commit();
            
        } else if (PULSE_LED_SCREEN_ON.equals(key)) {
          	sharedPref = prefMgr.getSharedPreferences();
           	SharedPreferences.Editor editor = sharedPref.edit();
           	editor.putBoolean(PULSE_LED_SCREEN_ON, (Boolean) objValue);
           	PulseLedScreenOn = (Boolean) objValue;
            editor.commit();

        } else if (INCOMING_CALL_LED_PULSE.equals(key)) {
          	sharedPref = prefMgr.getSharedPreferences();
           	SharedPreferences.Editor editor = sharedPref.edit();
           	editor.putBoolean(INCOMING_CALL_LED_PULSE, (Boolean) objValue);
           	IncomingCallPulse = (Boolean) objValue;
            editor.commit();
        
        } else if (INCOMING_CALL_LED_COLOR.equals(key)) {
          	sharedPref = prefMgr.getSharedPreferences();
           	SharedPreferences.Editor editor = sharedPref.edit();
           	editor.putInt(INCOMING_CALL_LED_COLOR, (Integer) objValue);
            editor.commit();
        
        } else if (INCOMING_CALL_LED_ON_MS.equals(key)) {
          	sharedPref = prefMgr.getSharedPreferences();
           	SharedPreferences.Editor editor = sharedPref.edit();
           	editor.putInt(INCOMING_CALL_LED_ON_MS, mIncomingCallLedOnMs.getProgress());
            editor.commit();
       
        } else if (INCOMING_CALL_LED_OFF_MS.equals(key)) {
          	sharedPref = prefMgr.getSharedPreferences();
           	SharedPreferences.Editor editor = sharedPref.edit();
           	editor.putInt(INCOMING_CALL_LED_OFF_MS, mIncomingCallLedOffMs.getProgress());
            editor.commit();
            
        } else if (MISSED_CALL_LED_PULSE.equals(key)) {
          	sharedPref = prefMgr.getSharedPreferences();
           	SharedPreferences.Editor editor = sharedPref.edit();
           	editor.putBoolean(MISSED_CALL_LED_PULSE, (Boolean) objValue);
           	MissedCallPulse = (Boolean) objValue;
            editor.commit();
        
        } else if (MISSED_CALL_LED_COLOR.equals(key)) {
          	sharedPref = prefMgr.getSharedPreferences();
           	SharedPreferences.Editor editor = sharedPref.edit();
           	editor.putInt(MISSED_CALL_LED_COLOR, (Integer) objValue);
            editor.commit();
        
        } else if (MISSED_CALL_LED_ON_MS.equals(key)) {
          	sharedPref = prefMgr.getSharedPreferences();
           	SharedPreferences.Editor editor = sharedPref.edit();
           	editor.putInt(MISSED_CALL_LED_ON_MS, mMissedCallLedOnMs.getProgress());
            editor.commit();
       
        } else if (MISSED_CALL_LED_OFF_MS.equals(key)) {
          	sharedPref = prefMgr.getSharedPreferences();
           	SharedPreferences.Editor editor = sharedPref.edit();
           	editor.putInt(MISSED_CALL_LED_OFF_MS, mMissedCallLedOffMs.getProgress());
            editor.commit();
      
        }  else if (VOICE_MAIL_LED_PULSE.equals(key)) {
          	sharedPref = prefMgr.getSharedPreferences();
           	SharedPreferences.Editor editor = sharedPref.edit();
           	editor.putBoolean(VOICE_MAIL_LED_PULSE, (Boolean) objValue);
           	VoiceMailPulse = (Boolean) objValue;
            editor.commit();
        
        } else if (VOICE_MAIL_LED_COLOR.equals(key)) {
          	sharedPref = prefMgr.getSharedPreferences();
           	SharedPreferences.Editor editor = sharedPref.edit();
           	editor.putInt(VOICE_MAIL_LED_COLOR, (Integer) objValue);
            editor.commit();
        
        } else if (VOICE_MAIL_LED_ON_MS.equals(key)) {
          	sharedPref = prefMgr.getSharedPreferences();
           	SharedPreferences.Editor editor = sharedPref.edit();
           	editor.putInt(VOICE_MAIL_LED_ON_MS, mVoiceMailLedOnMs.getProgress());
            editor.commit();
       
        } else if (VOICE_MAIL_LED_OFF_MS.equals(key)) {
          	sharedPref = prefMgr.getSharedPreferences();
           	SharedPreferences.Editor editor = sharedPref.edit();
           	editor.putInt(VOICE_MAIL_LED_OFF_MS, mVoiceMailLedOffMs.getProgress());
            editor.commit();
            
            
            
            
        } else if ("led_override_list".equals(key)) {
        	AppListDialog();       
      
        }
     	
        updateDb();    
        return true;
    }
    
  
    private void AppListDialog() {
    	Builder alertDialog = new AlertDialog.Builder(getActivity());
    	alertDialog.setTitle("Theme Presets");
    	alertDialog.setNegativeButton("Cancel", null);
    	alertDialog.setPositiveButton("Select", null);
    	final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
    	mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

    	List<String> listItems = getAppNames(false);



    	final CharSequence[] cs = listItems.toArray(new CharSequence[listItems.size()]);
    	alertDialog.setMultiChoiceItems(cs, null, null);
    	alertDialog.show();
    	
    }    
    
    
    
    class PInfo {
        private String appname = "";
        private String pname = "";
        private String versionName = "";
        private int versionCode = 0;
        private Drawable icon;
//        private void prettyPrint() {
            //Log.v(appname + "\t" + pname + "\t" + versionName + "\t" + versionCode);
//        }
    }

 
 
    
    private ArrayList<PInfo> getPackages() {
        ArrayList<PInfo> apps = getInstalledApps(false); /* false = no system packages */
        final int max = apps.size();
        for (int i=0; i<max; i++) {
            //apps.get(i).prettyPrint();
        	
        }
        return apps;
    }

    private ArrayList<PInfo> getInstalledApps(boolean getSysPackages) {
        ArrayList<PInfo> res = new ArrayList<PInfo>();        
        List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
        for(int i=0;i<packs.size();i++) {
            PackageInfo p = packs.get(i);
            if ((!getSysPackages) && (p.versionName == null)) {
                continue ;
            }
            PInfo newInfo = new PInfo();
            newInfo.appname = p.applicationInfo.loadLabel(getPackageManager()).toString();
            newInfo.pname = p.packageName;
            newInfo.versionName = p.versionName;
            newInfo.versionCode = p.versionCode;
            newInfo.icon = p.applicationInfo.loadIcon(getPackageManager());
            res.add(newInfo);
        }
        return res; 
    }    
    
    
    private ArrayList<String> getAppNames(boolean getSysPackages) {
        ArrayList<String> res = new ArrayList<String>();        
        List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
        for(int i=0;i<packs.size();i++) {
            PackageInfo p = packs.get(i);
            if ((p.applicationInfo.flags & p.applicationInfo.FLAG_SYSTEM) !=0) {
            	continue;
            }
//            if ((!getSysPackages) && (p.versionName == null)) {
//                continue ;
//            }
            String newInfo = p.applicationInfo.loadLabel(getPackageManager()).toString();
            res.add(newInfo);
        }
        Collections.sort(res);
        return res; 
    }     
    
    
    
    
    
    
                
}
