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



import java.io.IOException;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.widget.Toast;



public class CustomThemeSettings extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener {

	private final String THEME_PRESETS = "theme_presets";

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
	

	private SharedPreferences prefMgr;
	private SharedPreferences.Editor editor = null;
    private ListPreference mThemePresets;
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

    
    
    /** If there is no setting in the provider, use this. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   
        SharedPreferences prefMgr = getActivity().getBaseContext().getSharedPreferences("Junk_Settings", Context.MODE_PRIVATE);
        
        addPreferencesFromResource(R.xml.custom_theme_settings);

        mThemePresets = (ListPreference) findPreference(THEME_PRESETS);
        mThemePresets.setOnPreferenceChangeListener(this);
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
		
		mThemeOne.setTitle(prefMgr.getString(THEME_ONE_NAME,"Theme One"));
		mThemeTwo.setTitle(prefMgr.getString(THEME_TWO_NAME,"Theme Two"));
		mThemeThree.setTitle(prefMgr.getString(THEME_THREE_NAME,"Theme Three"));
		
        prefMgr = getActivity().getBaseContext().getSharedPreferences(
        		"Junk_Theme_One", Context.MODE_PRIVATE);
		mApplyThemeOne.setEnabled(prefMgr.getBoolean(SAVED_THEME,false));

        prefMgr = getActivity().getBaseContext().getSharedPreferences(
        		"Junk_Theme_Two", Context.MODE_PRIVATE);
        mApplyThemeTwo.setEnabled(prefMgr.getBoolean(SAVED_THEME,false));

        prefMgr = getActivity().getBaseContext().getSharedPreferences(
        		"Junk_Theme_Three", Context.MODE_PRIVATE);
		mApplyThemeThree.setEnabled(prefMgr.getBoolean(SAVED_THEME,false));
		

		boolean copied = false;
    	try {
				copied = AssetUtils.copyAsset(getActivity().getBaseContext(), "Theme_Junk.xml",
						"data/data/com.android.settings/shared_prefs/Theme_Junk.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}

    	try {
				copied = AssetUtils.copyAsset(getActivity().getBaseContext(), "Theme_Blue.xml",
						"data/data/com.android.settings/shared_prefs/Theme_Blue.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}

    	try {
				copied = AssetUtils.copyAsset(getActivity().getBaseContext(), "Theme_Green.xml",
						"data/data/com.android.settings/shared_prefs/Theme_Green.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}

    	try {
				copied = AssetUtils.copyAsset(getActivity().getBaseContext(), "Theme_Red.xml",
						"data/data/com.android.settings/shared_prefs/Theme_Red.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}

    	try {
				copied = AssetUtils.copyAsset(getActivity().getBaseContext(), "Theme_Orange.xml",
						"data/data/com.android.settings/shared_prefs/Theme_Orange.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
    	try {
				copied = AssetUtils.copyAsset(getActivity().getBaseContext(), "Theme_Purple.xml",
						"data/data/com.android.settings/shared_prefs/Theme_Purple.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		
    }


 
    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
    	
        prefMgr = getActivity().getBaseContext().getSharedPreferences(
        		"Junk_Settings", Context.MODE_PRIVATE);
        
    	if (preference == mSaveThemeOne) {
    		themeOneName = prefMgr.getString(THEME_ONE_NAME,"Theme One");
    		SaveDialog(1);
    	} else if (preference == mApplyThemeOne) {
    		themeOneName = prefMgr.getString(THEME_ONE_NAME,"Theme One");
    		ApplyDialog(1);
    	} else if (preference == mSaveThemeTwo) {
    		themeTwoName = prefMgr.getString(THEME_TWO_NAME,"Theme Two");
    		SaveDialog(2);
    	} else if (preference == mApplyThemeTwo) {
    		themeTwoName = prefMgr.getString(THEME_TWO_NAME,"Theme Two");
    		ApplyDialog(2);
    	} else if (preference == mSaveThemeThree) {
    		themeThreeName = prefMgr.getString(THEME_THREE_NAME,"Theme Three");
    		SaveDialog(3);
    	} else if (preference == mApplyThemeThree) {
    		themeThreeName = prefMgr.getString(THEME_THREE_NAME,"Theme Three");
    		ApplyDialog(3);	
    	}
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }
    
 
    
    
    public boolean onPreferenceChange(Preference preference, Object objValue) {
  
     	final String key = preference.getKey();

     	if (THEME_ONE_NAME.equals(key)) {
     		mThemeOne.setTitle((String) objValue);
     		themeOneName = (String) objValue;
     		
	        prefMgr = getActivity().getBaseContext().getSharedPreferences(
	        		"Junk_Settings", Context.MODE_PRIVATE);
	    	editor = prefMgr.edit();
	    	editor.putString(THEME_ONE_NAME, themeOneName);
	    	editor.commit();
     	
     	} else if (THEME_TWO_NAME.equals(key)) {
     		mThemeTwo.setTitle((String) objValue);
     		themeTwoName = (String) objValue;
     		
	        prefMgr = getActivity().getBaseContext().getSharedPreferences(
	        		"Junk_Settings", Context.MODE_PRIVATE);
	        editor = prefMgr.edit();
	    	editor.putString(THEME_TWO_NAME, themeTwoName);
	    	editor.commit();
     	
     	} else if (THEME_THREE_NAME.equals(key)) {
     		mThemeThree.setTitle((String) objValue);
     		themeThreeName = (String) objValue;

	        prefMgr = getActivity().getBaseContext().getSharedPreferences(
	        		"Junk_Settings", Context.MODE_PRIVATE);
	        editor = prefMgr.edit();
	    	editor.putString(THEME_THREE_NAME, themeThreeName);
	    	editor.commit();

     	} else if (preference == mThemePresets) {
            prefMgr = getActivity().getBaseContext().getSharedPreferences(
            		"Theme_" + (String) objValue, Context.MODE_PRIVATE);
            CustomSettingsUtils.GetSettings(prefMgr);
	        
            prefMgr = getActivity().getBaseContext().getSharedPreferences(
            		"Junk_Settings", Context.MODE_PRIVATE);

	        CustomSettingsUtils.WriteSettings(prefMgr);
	        CustomSettingsUtils.SendIntents(getActivity().getBaseContext());

	        prefMgr = getActivity().getBaseContext().getSharedPreferences(
            		"Junk_Settings", Context.MODE_PRIVATE);
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
    

    
    


 
    
    DialogInterface.OnClickListener saveDialogPositiveListener =
            new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					if (whichTheme == 1 ) {
			            prefMgr = getActivity().getBaseContext().getSharedPreferences(
			            		"Junk_Settings", Context.MODE_PRIVATE);
				        CustomSettingsUtils.GetSettings(prefMgr);
				        
			            prefMgr = getActivity().getBaseContext().getSharedPreferences(
			            		"Junk_Theme_One", Context.MODE_PRIVATE);
			            editor= prefMgr.edit();
			            editor.putBoolean(SAVED_THEME, true);
			            editor.commit();
			            CustomSettingsUtils.WriteSettings(prefMgr);
				        
			            prefMgr = getActivity().getBaseContext().getSharedPreferences(
			            		"Junk_Settings", Context.MODE_PRIVATE);
				        Toast.makeText(getActivity().getBaseContext(), themeOneName + " has been saved", Toast.LENGTH_SHORT).show();
				        mApplyThemeOne.setEnabled(true);
				        
					} else if (whichTheme == 2 ) {				        
				        prefMgr = getActivity().getBaseContext().getSharedPreferences(
			            		"Junk_Settings", Context.MODE_PRIVATE);
				        CustomSettingsUtils.GetSettings(prefMgr);
				        
			            prefMgr = getActivity().getBaseContext().getSharedPreferences(
			            		"Junk_Theme_Two", Context.MODE_PRIVATE);
			            editor= prefMgr.edit();
			            editor.putBoolean(SAVED_THEME, true);
			            editor.commit();
			            CustomSettingsUtils.WriteSettings(prefMgr);
				        
			            prefMgr = getActivity().getBaseContext().getSharedPreferences(
			            		"Junk_Settings", Context.MODE_PRIVATE);
						Toast.makeText(getActivity().getBaseContext(), themeTwoName + " has been saved", Toast.LENGTH_SHORT).show();
						mApplyThemeTwo.setEnabled(true);
						
					} else if (whichTheme == 3 ) {
			            prefMgr = getActivity().getBaseContext().getSharedPreferences(
			            		"Junk_Settings", Context.MODE_PRIVATE);
			            CustomSettingsUtils.GetSettings(prefMgr);
				        
			            prefMgr = getActivity().getBaseContext().getSharedPreferences(
			            		"Junk_Theme_Three", Context.MODE_PRIVATE);
			            editor= prefMgr.edit();
			            editor.putBoolean(SAVED_THEME, true);
			            editor.commit();
			            CustomSettingsUtils.WriteSettings(prefMgr);
				        
			            prefMgr = getActivity().getBaseContext().getSharedPreferences(
			            		"Junk_Settings", Context.MODE_PRIVATE);
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
			            prefMgr = getActivity().getBaseContext().getSharedPreferences(
			            		"Junk_Theme_One", Context.MODE_PRIVATE);
			            CustomSettingsUtils.GetSettings(prefMgr);
				        
			            prefMgr = getActivity().getBaseContext().getSharedPreferences(
			            		"Junk_Settings", Context.MODE_PRIVATE);
				        CustomSettingsUtils.WriteSettings(prefMgr);
				        CustomSettingsUtils.SendIntents(getActivity().getBaseContext());
				        
						Toast.makeText(getActivity().getBaseContext(), themeOneName + " has been applied", Toast.LENGTH_SHORT).show();
						
					} else if (whichTheme == 2 ) {
			            prefMgr = getActivity().getBaseContext().getSharedPreferences(
			            		"Junk_Theme_Two", Context.MODE_PRIVATE);
			            CustomSettingsUtils.GetSettings(prefMgr);
				        
			            prefMgr = getActivity().getBaseContext().getSharedPreferences(
			            		"Junk_Settings", Context.MODE_PRIVATE);
			            CustomSettingsUtils.WriteSettings(prefMgr);
				        CustomSettingsUtils.SendIntents(getActivity().getBaseContext());
	
						Toast.makeText(getActivity().getBaseContext(), themeTwoName + " has been applied", Toast.LENGTH_SHORT).show();
						
					} else if (whichTheme == 3 ) {
			            prefMgr = getActivity().getBaseContext().getSharedPreferences(
			            		"Junk_Theme_Three", Context.MODE_PRIVATE);
			            CustomSettingsUtils.GetSettings(prefMgr);
				        
			            prefMgr = getActivity().getBaseContext().getSharedPreferences(
			            		"Junk_Settings", Context.MODE_PRIVATE);
			            CustomSettingsUtils.WriteSettings(prefMgr);
				        CustomSettingsUtils.SendIntents(getActivity().getBaseContext());
				        
						Toast.makeText(getActivity().getBaseContext(), themeThreeName + " has been applied", Toast.LENGTH_SHORT).show();
					}
				}
			};
			
    
    
	
					
					
}
