package com.android.settings;

import java.io.File;
import java.io.IOException;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;

public class CustomJunk extends Fragment {

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
	private String BATTERY_LEVEL_COLOR_THREE = "battery_levels_color_three";   
	
	private final String THEME_PRESETS = "theme_presets";
	private final String THEME_ONE_NAME = "theme_one_name";
	private final String THEME_TWO_NAME = "theme_two_name";
	private final String THEME_THREE_NAME = "theme_three_name";
	private final String SAVED_THEME = "saved_theme";


	private View mView;
	private Button mBatterySettings, mChargingSettings;
	private Button mThemeOneApply, mThemeTwoApply, mThemeThreeApply;
	private TextView mThemeOneText, mThemeTwoText, mThemeThreeText;
	private int BatteryIconValue = 0;
	private int ThemePresetValue = 0;
	private int SelectedThemePresetValue = 0;
	private int mThemeNum = 0;
	private EditText mEditText;
	private SharedPreferences prefMgr;
	private SharedPreferences.Editor editor = null;



	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	mView = (View) inflater.inflate(R.layout.custom_junk, container, false);

		return mView;

    }
	
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    	TabHost host = (TabHost) getActivity().findViewById(android.R.id.tabhost);
    	host.setup();
        TabSpec spec = host.newTabSpec("tab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Junk Settings");
        host.addTab(spec);

        spec = host.newTabSpec("tab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Junk Colors");
        host.addTab(spec);
        
        spec = host.newTabSpec("tab3");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Junk Themes");
        host.addTab(spec);
        
        Button mBackup = (Button) getActivity().findViewById(R.id.but_backup);
        Button mRestore = (Button) getActivity().findViewById(R.id.but_restore);
        Button mClockSettings = (Button) getActivity().findViewById(R.id.but_clock);
        Button mBatteryIconSettings = (Button) getActivity().findViewById(R.id.but_battery_icons);
               mBatterySettings = (Button) getActivity().findViewById(R.id.but_battery);
               mChargingSettings = (Button) getActivity().findViewById(R.id.but_charging);
        Button mPulldownSettings = (Button) getActivity().findViewById(R.id.but_pulldown);
        Button mToggleSettings = (Button) getActivity().findViewById(R.id.but_toggle);
        Button mNavBarSettings = (Button) getActivity().findViewById(R.id.but_navbar);
        Button mQuietTimeSettings = (Button) getActivity().findViewById(R.id.but_quiettime);
        Button mNotifLedSettings = (Button) getActivity().findViewById(R.id.but_led);
        
        Button mQuickColorSettings = (Button) getActivity().findViewById(R.id.but_color_quick);
        Button mColorSettings = (Button) getActivity().findViewById(R.id.but_color_color);
        Button mSignalColorSettings = (Button) getActivity().findViewById(R.id.but_color_signal);
        Button mBatteryColorSettings = (Button) getActivity().findViewById(R.id.but_color_battery);
        Button mChargingColorSettings = (Button) getActivity().findViewById(R.id.but_color_charging);
        Button mClockColorSettings = (Button) getActivity().findViewById(R.id.but_color_clock);
        Button mPulldownColorSettings = (Button) getActivity().findViewById(R.id.but_color_statpull);
        Button mToggleColorSettings = (Button) getActivity().findViewById(R.id.but_color_toggle);
        Button mNavBarColorSettings = (Button) getActivity().findViewById(R.id.but_color_navbar);
      
        Button mThemePresets = (Button) getActivity().findViewById(R.id.but_theme_preset);
        Button mThemeOneSave = (Button) getActivity().findViewById(R.id.but_theme1_save);
        	   mThemeOneApply = (Button) getActivity().findViewById(R.id.but_theme1_apply);
        Button mThemeOneName = (Button) getActivity().findViewById(R.id.but_theme1_name);        	   
        Button mThemeTwoSave = (Button) getActivity().findViewById(R.id.but_theme2_save);
        	   mThemeTwoApply = (Button) getActivity().findViewById(R.id.but_theme2_apply);
        Button mThemeTwoName = (Button) getActivity().findViewById(R.id.but_theme2_name);        	   
        Button mThemeThreeSave = (Button) getActivity().findViewById(R.id.but_theme3_save);
        	   mThemeThreeApply = (Button) getActivity().findViewById(R.id.but_theme3_apply);
        Button mThemeThreeName = (Button) getActivity().findViewById(R.id.but_theme3_name);

        
        mThemeOneText = (TextView) getActivity().findViewById(R.id.theme1text);
        mThemeTwoText = (TextView) getActivity().findViewById(R.id.theme2text);
        mThemeThreeText = (TextView) getActivity().findViewById(R.id.theme3text);
    		
    		
    		
    		
        prefMgr = getActivity().getBaseContext().getSharedPreferences(
        		"Junk_Theme_One", Context.MODE_WORLD_READABLE);    		
    	mThemeOneApply.setEnabled(prefMgr.getBoolean(SAVED_THEME,false));
    	mThemeOneText.setText(prefMgr.getString(THEME_ONE_NAME,"Theme One"));

        prefMgr = getActivity().getBaseContext().getSharedPreferences(
           		"Junk_Theme_Two", Context.MODE_WORLD_READABLE);
        mThemeTwoApply.setEnabled(prefMgr.getBoolean(SAVED_THEME,false));
        mThemeTwoText.setText(prefMgr.getString(THEME_TWO_NAME,"Theme Two"));

        prefMgr = getActivity().getBaseContext().getSharedPreferences(
           		"Junk_Theme_Three", Context.MODE_WORLD_READABLE);
        mThemeThreeApply.setEnabled(prefMgr.getBoolean(SAVED_THEME,false));
        mThemeThreeText.setText(prefMgr.getString(THEME_THREE_NAME,"Theme Three"));


        
    	try {
			if (!BackupUtils.backupExist("data/data/com.android.settings/shared_prefs/Theme_Junk.xml")) {

				try {
					AssetUtils.copyAsset(getActivity().getBaseContext(), "Theme_Junk.xml",
							"data/data/com.android.settings/shared_prefs/Theme_Junk.xml");
				} catch (IOException e) {
				}
				try {
					AssetUtils.copyAsset(getActivity().getBaseContext(), "Theme_Blue.xml",
							"data/data/com.android.settings/shared_prefs/Theme_Blue.xml");
				} catch (IOException e) {
				}
				try {
					AssetUtils.copyAsset(getActivity().getBaseContext(), "Theme_Green.xml",
							"data/data/com.android.settings/shared_prefs/Theme_Green.xml");
				} catch (IOException e) {
				}
				try {
					AssetUtils.copyAsset(getActivity().getBaseContext(), "Theme_Red.xml",
							"data/data/com.android.settings/shared_prefs/Theme_Red.xml");
				} catch (IOException e) {
				}
				try {
					AssetUtils.copyAsset(getActivity().getBaseContext(), "Theme_Orange.xml",
							"data/data/com.android.settings/shared_prefs/Theme_Orange.xml");
				} catch (IOException e) {
				}		
				try {
					AssetUtils.copyAsset(getActivity().getBaseContext(), "Theme_Purple.xml",
							"data/data/com.android.settings/shared_prefs/Theme_Purple.xml");
				} catch (IOException e) {
				}
			
			}
		} catch (IOException e) {
		}// Assets

        prefMgr = getActivity().getBaseContext().getSharedPreferences("Junk_Settings", Context.MODE_WORLD_READABLE);
 
        
        // Keeping these values as strings for backward compatibility
        BatteryIconValue = Integer.valueOf(prefMgr.getString(BATTERY_ICONS, "0"));
        if (BatteryIconValue == 6) {
   			mBatterySettings.setEnabled(false);
   			mChargingSettings.setEnabled(false);
   		} else if (BatteryIconValue == 0) {
   			mChargingSettings.setEnabled(false);
   		} else {
   			mBatterySettings.setEnabled(true);
   			mChargingSettings.setEnabled(true);
   		}        
        
        // Keeping these values as strings for backward compatibility     
		String ThemeString = prefMgr.getString(THEME_PRESETS, "Junk");
        if (ThemeString.equals("Junk")) {
			ThemePresetValue = 0;
		} else if (ThemeString.equals("Blue")) {
			ThemePresetValue = 1;
		} else if (ThemeString.equals("Green")) {
			ThemePresetValue = 2;
		} else if (ThemeString.equals("Red")) {
			ThemePresetValue = 3;
		} else if (ThemeString.equals("Orange")) {
			ThemePresetValue = 4;
		} else if (ThemeString.equals("Purple")) {
			ThemePresetValue = 5;
		}
       
        
        
		mBackup.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
				BackupDialog();
			}
 		});
		
		mRestore.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
				RestoreDialog();
			}
 		});
		
		mClockSettings.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
			    Intent i = new Intent();
			    i.setAction("android.settings.CUSTOM_CLOCK_SETTINGS");
		        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        getActivity().getBaseContext().startActivity(i);
		        i = null;
			}
 		});

		mBatteryIconSettings.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
				BatteryIconsDialog();
			}
 		});

		mBatterySettings.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
			    Intent i = new Intent();
			    i.setAction("android.settings.CUSTOM_BATTERY_SETTINGS");
		        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        getActivity().getBaseContext().startActivity(i);
		        i = null;
			}
 		});		
		
		mChargingSettings.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
			    Intent i = new Intent();
			    i.setAction("android.settings.CUSTOM_CHARGING_SETTINGS");
		        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        getActivity().getBaseContext().startActivity(i);
		        i = null;
			}
 		});		
		
		mPulldownSettings.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
			    Intent i = new Intent();
			    i.setAction("android.settings.CUSTOM_PULLDOWN_SETTINGS");
		        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        getActivity().getBaseContext().startActivity(i);
		        i = null;
			}
 		});
		
		mToggleSettings.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
			    Intent i = new Intent();
			    i.setAction("android.settings.CUSTOM_TOGGLE_SETTINGS");
		        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        getActivity().getBaseContext().startActivity(i);
		        i = null;
			}
 		});
		
		mNavBarSettings.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
			    Intent i = new Intent();
			    i.setAction("android.settings.CUSTOM_NAVBAR_SETTINGS");
		        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        getActivity().getBaseContext().startActivity(i);
		        i = null;
			}
 		});

		mQuietTimeSettings.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
			    Intent i = new Intent();
			    i.setAction("android.settings.CUSTOM_QUIETTIME_SETTINGS");
		        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        getActivity().getBaseContext().startActivity(i);
		        i = null;
			}
 		});
        
		mNotifLedSettings.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
			    Intent i = new Intent();
			    i.setAction("android.settings.CUSTOM_NOTIFLED_SETTINGS");
		        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        getActivity().getBaseContext().startActivity(i);
		        i = null;
			}
 		});
		

		// *******************  COLORS
		
		mQuickColorSettings.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
			    Intent i = new Intent();
			    i.setAction("android.settings.CUSTOM_QUICK_COLOR_SETTINGS");
		        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        getActivity().getBaseContext().startActivity(i);
		        i = null;
			}
 		});		

		mColorSettings.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
			    Intent i = new Intent();
			    i.setAction("android.settings.CUSTOM_COLOR_SETTINGS");
			    i.putExtra("junk_color_rowid", 0);
		        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        getActivity().getBaseContext().startActivity(i);
		        i = null;
			}
 		});		
		
		
		mSignalColorSettings.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
			    Intent i = new Intent();
			    i.setAction("android.settings.CUSTOM_COLOR_SETTINGS");
			    i.putExtra("junk_color_rowid", 0);
		        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        getActivity().getBaseContext().startActivity(i);
		        i = null;
			}
 		});		
		
		mBatteryColorSettings.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
			    Intent i = new Intent();
			    i.setAction("android.settings.CUSTOM_COLOR_SETTINGS");
			    i.putExtra("junk_color_rowid", 2);
		        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        getActivity().getBaseContext().startActivity(i);
		        i = null;
			}
 		});		
		
		mChargingColorSettings.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
			    Intent i = new Intent();
			    i.setAction("android.settings.CUSTOM_COLOR_SETTINGS");
			    i.putExtra("junk_color_rowid", 16);
		        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        getActivity().getBaseContext().startActivity(i);
		        i = null;
			}
 		});		
		
		
		mClockColorSettings.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
			    Intent i = new Intent();
			    i.setAction("android.settings.CUSTOM_COLOR_SETTINGS");
			    i.putExtra("junk_color_rowid", 24);
		        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        getActivity().getBaseContext().startActivity(i);
		        i = null;
			}
 		});		
		
		mPulldownColorSettings.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
			    Intent i = new Intent();
			    i.setAction("android.settings.CUSTOM_COLOR_SETTINGS");
			    i.putExtra("junk_color_rowid", 26);
		        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        getActivity().getBaseContext().startActivity(i);
		        i = null;
			}
 		});		
		
		mToggleColorSettings.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
			    Intent i = new Intent();
			    i.setAction("android.settings.CUSTOM_COLOR_SETTINGS");
			    i.putExtra("junk_color_rowid", 40);
		        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        getActivity().getBaseContext().startActivity(i);
		        i = null;
			}
 		});		
		
		mNavBarColorSettings.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
			    Intent i = new Intent();
			    i.setAction("android.settings.CUSTOM_COLOR_SETTINGS");
			    i.putExtra("junk_color_rowid", 20);
		        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        getActivity().getBaseContext().startActivity(i);
		        i = null;
			}
 		});		
		
		
		// *******************  PRESETS & THEMES
		
		mThemePresets.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
				ThemePresetsDialog();
			}
 		});		

		mThemeOneSave.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
				ThemeSaveDialog(1);
			}
 		});			
		
		mThemeTwoSave.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
				ThemeSaveDialog(2);
			}
 		});			

		mThemeThreeSave.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
				ThemeSaveDialog(3);
			}
 		});			

		mThemeOneApply.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
				ThemeApplyDialog(1);
			}
 		});			
		
		mThemeTwoApply.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
				ThemeApplyDialog(2);
			}
 		});			

		mThemeThreeApply.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
				ThemeApplyDialog(3);
			}
 		});			
		
		mThemeOneName.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
				ThemeNameDialog(1);
			}
 		});			
		
		mThemeTwoName.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
				ThemeNameDialog(2);
			}
 		});			

		mThemeThreeName.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
				ThemeNameDialog(3);
			}
 		});			
		
		
    }
    // ****************************
    
    private void ThemePresetsDialog() {
    	Builder alertDialog = new AlertDialog.Builder(getActivity());
    	alertDialog.setTitle("Theme Presets");
    	alertDialog.setNegativeButton("Cancel", null);
    	alertDialog.setPositiveButton("Select", themePresetDialogPositiveListener);
    	alertDialog.setSingleChoiceItems(R.array.theme_presets, ThemePresetValue, PresetListListener);
    	alertDialog.show();
    	
    }
    
    
    private void ThemeSaveDialog(int whichTheme)	{
		mThemeNum = whichTheme;
    	Builder alertDialog = new AlertDialog.Builder(getActivity());
    	alertDialog.setTitle("Save Theme");
    	alertDialog.setMessage("Save the current colors?");
    	alertDialog.setNegativeButton("Cancel", null);
    	alertDialog.setPositiveButton("Save", themeSaveDialogPositiveListener);
    	alertDialog.show();
    }		

   private void ThemeApplyDialog(int whichTheme)	{
	   	mThemeNum = whichTheme;
    	Builder alertDialog = new AlertDialog.Builder(getActivity());
    	alertDialog.setTitle("Apply Theme");
    	alertDialog.setMessage("Apply this theme?");
    	alertDialog.setNegativeButton("Cancel", null);
    	alertDialog.setPositiveButton("Apply", themeApplyDialogPositiveListener);
    	alertDialog.show();
    }		

   private void ThemeNameDialog(int whichTheme)	{
	   mThemeNum = whichTheme;
	   mEditText = new EditText(getActivity());
	   mEditText.setId(0);
	   if (mThemeNum == 1) mEditText.setText(mThemeOneText.getText().toString());
	   if (mThemeNum == 2) mEditText.setText(mThemeTwoText.getText().toString());
	   if (mThemeNum == 3) mEditText.setText(mThemeThreeText.getText().toString());
	   Builder alertDialog = new AlertDialog.Builder(getActivity());
	   alertDialog.setTitle("Theme Name");
	   alertDialog.setMessage("Give this theme a name.");
	   alertDialog.setView(mEditText);
	   alertDialog.setNegativeButton("Cancel", null);
	   alertDialog.setPositiveButton("Save", themeNameDialogPositiveListener);
	   alertDialog.show();
   	}		
    
    
    private void BackupDialog()	{
		
    	Builder alertDialog = new AlertDialog.Builder(getActivity());
    	alertDialog.setTitle("Backup Junk Settings");
    	alertDialog.setMessage("Backup Junk settings and themes?");
    	alertDialog.setNegativeButton("Cancel", null);
    	alertDialog.setPositiveButton("Backup", backupDialogPositiveListener);
    	alertDialog.show();
    }		

    private void RestoreDialog() {
		
    	Builder alertDialog = new AlertDialog.Builder(getActivity());
    	alertDialog.setTitle("Restore Junk Settings");
    	alertDialog.setMessage("Restore Junk settings and themes?");
    	alertDialog.setNegativeButton("Cancel", null);
    	alertDialog.setPositiveButton("Restore", restoreDialogPositiveListener);
    	alertDialog.show();
    }       
    
    private void BatteryIconsDialog() {
    	Builder alertDialog = new AlertDialog.Builder(getActivity());
    	alertDialog.setTitle("Battery Icons");
    	alertDialog.setNegativeButton("Cancel", null);
    	alertDialog.setPositiveButton("Select", batteryDialogPositiveListener);
    	alertDialog.setSingleChoiceItems(R.array.battery_icons, BatteryIconValue, BatteryListListener);
    	alertDialog.show();
    	
    }		    
    
    
    DialogInterface.OnClickListener PresetListListener =
    		new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					SelectedThemePresetValue = which;
				}
			};

	// Theme Presets
	DialogInterface.OnClickListener themePresetDialogPositiveListener =
	   		new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					String mPreset = "";
					ThemePresetValue = SelectedThemePresetValue;
					
					if (ThemePresetValue == 0) {
						mPreset = "Junk";
					} else if (ThemePresetValue == 1) {
						mPreset = "Blue";
					} else if (ThemePresetValue == 2) {
						mPreset = "Green";
					} else if (ThemePresetValue == 3) {
						mPreset = "Red";
					} else if (ThemePresetValue == 4) {
						mPreset = "Orange";
					} else if (ThemePresetValue == 5) {
						mPreset = "Purple";
					}
						
					editor = prefMgr.edit();
			    	editor.putString(THEME_PRESETS, String.valueOf(ThemePresetValue));
			    	editor.commit();
			    	
		            prefMgr = getActivity().getBaseContext().getSharedPreferences(
		            		"Theme_" + mPreset, Context.MODE_WORLD_READABLE);
		            CustomSettingsUtils.GetSettings(prefMgr);
			        
		            prefMgr = getActivity().getBaseContext().getSharedPreferences(
		            		"Junk_Settings", Context.MODE_WORLD_READABLE);

			        CustomSettingsUtils.WriteSettings(prefMgr);
			        CustomSettingsUtils.SendIntents(getActivity().getBaseContext());

			        prefMgr = getActivity().getBaseContext().getSharedPreferences(
		            		"Junk_Settings", Context.MODE_WORLD_READABLE);

				}
			};    
    
	// Save Themes
	DialogInterface.OnClickListener themeSaveDialogPositiveListener =
	  		new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					if (mThemeNum == 1 ) {
			            prefMgr = getActivity().getBaseContext().getSharedPreferences(
			            		"Junk_Settings", Context.MODE_WORLD_READABLE);
				        CustomSettingsUtils.GetSettings(prefMgr);
				        
			            prefMgr = getActivity().getBaseContext().getSharedPreferences(
			            		"Junk_Theme_One", Context.MODE_WORLD_READABLE);
			            editor= prefMgr.edit();
			            editor.putBoolean(SAVED_THEME, true);
			            editor.commit();
			            CustomSettingsUtils.WriteSettings(prefMgr);
				        
			            prefMgr = getActivity().getBaseContext().getSharedPreferences(
			            		"Junk_Settings", Context.MODE_WORLD_READABLE);
				        Toast.makeText(getActivity().getBaseContext(), mThemeOneText.getText() +
				        		" has been saved", Toast.LENGTH_SHORT).show();
				        mThemeOneApply.setEnabled(true);
				        
					} else if (mThemeNum == 2 ) {				        
				        prefMgr = getActivity().getBaseContext().getSharedPreferences(
			            		"Junk_Settings", Context.MODE_WORLD_READABLE);
				        CustomSettingsUtils.GetSettings(prefMgr);
				        
			            prefMgr = getActivity().getBaseContext().getSharedPreferences(
			            		"Junk_Theme_Two", Context.MODE_WORLD_READABLE);
			            editor= prefMgr.edit();
			            editor.putBoolean(SAVED_THEME, true);
			            editor.commit();
			            CustomSettingsUtils.WriteSettings(prefMgr);
				        
			            prefMgr = getActivity().getBaseContext().getSharedPreferences(
			            		"Junk_Settings", Context.MODE_WORLD_READABLE);
						Toast.makeText(getActivity().getBaseContext(), mThemeTwoText.getText() +
								" has been saved", Toast.LENGTH_SHORT).show();
						mThemeTwoApply.setEnabled(true);
						
					} else if (mThemeNum == 3 ) {
			            prefMgr = getActivity().getBaseContext().getSharedPreferences(
			            		"Junk_Settings", Context.MODE_WORLD_READABLE);
			            CustomSettingsUtils.GetSettings(prefMgr);
				        
			            prefMgr = getActivity().getBaseContext().getSharedPreferences(
			            		"Junk_Theme_Three", Context.MODE_WORLD_READABLE);
			            editor= prefMgr.edit();
			            editor.putBoolean(SAVED_THEME, true);
			            editor.commit();
			            CustomSettingsUtils.WriteSettings(prefMgr);
				        
			            prefMgr = getActivity().getBaseContext().getSharedPreferences(
			            		"Junk_Settings", Context.MODE_WORLD_READABLE);
						Toast.makeText(getActivity().getBaseContext(), mThemeThreeText.getText() +
								" has been saved", Toast.LENGTH_SHORT).show();
						mThemeThreeApply.setEnabled(true);
					}
					}
				};
			
		// Apply Themes
	    DialogInterface.OnClickListener themeApplyDialogPositiveListener =
	            new DialogInterface.OnClickListener() {
									
					@Override
					public void onClick(DialogInterface dialog, int which) {
						if (mThemeNum == 1 ) {
				            prefMgr = getActivity().getBaseContext().getSharedPreferences(
				            		"Junk_Theme_One", Context.MODE_WORLD_READABLE);
				            CustomSettingsUtils.GetSettings(prefMgr);
							        
				            prefMgr = getActivity().getBaseContext().getSharedPreferences(
				            		"Junk_Settings", Context.MODE_WORLD_READABLE);
					        CustomSettingsUtils.WriteSettings(prefMgr);
					        CustomSettingsUtils.SendIntents(getActivity().getBaseContext());
							        
							Toast.makeText(getActivity().getBaseContext(), mThemeOneText.getText() +
									" has been applied", Toast.LENGTH_SHORT).show();
									
						} else if (mThemeNum == 2 ) {
				            prefMgr = getActivity().getBaseContext().getSharedPreferences(
				            		"Junk_Theme_Two", Context.MODE_WORLD_READABLE);
				            CustomSettingsUtils.GetSettings(prefMgr);
							        
				            prefMgr = getActivity().getBaseContext().getSharedPreferences(
				            		"Junk_Settings", Context.MODE_WORLD_READABLE);
				            CustomSettingsUtils.WriteSettings(prefMgr);
					        CustomSettingsUtils.SendIntents(getActivity().getBaseContext());
				
							Toast.makeText(getActivity().getBaseContext(), mThemeTwoText.getText() +
									" has been applied", Toast.LENGTH_SHORT).show();
									
						} else if (mThemeNum == 3 ) {
				            prefMgr = getActivity().getBaseContext().getSharedPreferences(
				            		"Junk_Theme_Three", Context.MODE_WORLD_READABLE);
				            CustomSettingsUtils.GetSettings(prefMgr);
							        
				            prefMgr = getActivity().getBaseContext().getSharedPreferences(
				            		"Junk_Settings", Context.MODE_WORLD_READABLE);
				            CustomSettingsUtils.WriteSettings(prefMgr);
					        CustomSettingsUtils.SendIntents(getActivity().getBaseContext());
							        
							Toast.makeText(getActivity().getBaseContext(), mThemeThreeText.getText() +
									" has been applied", Toast.LENGTH_SHORT).show();
						}
					}
				};
    
		// Theme Names
	    DialogInterface.OnClickListener themeNameDialogPositiveListener =
	            new DialogInterface.OnClickListener() {
											
					@Override
					public void onClick(DialogInterface dialog, int which) {
						if (mThemeNum == 1 ) {
							
							mThemeOneText.setText(mEditText.getText().toString());
					        prefMgr = getActivity().getBaseContext().getSharedPreferences(
					        		"Junk_Theme_One", Context.MODE_WORLD_READABLE);   
					        editor = prefMgr.edit();
					    	editor.putString(THEME_ONE_NAME, mEditText.getText().toString());
					    	editor.commit();
										
						} else if (mThemeNum == 2 ) {
							
							mThemeTwoText.setText(mEditText.getText().toString());
					        prefMgr = getActivity().getBaseContext().getSharedPreferences(
					        		"Junk_Theme_Two", Context.MODE_WORLD_READABLE);   
					        editor = prefMgr.edit();
					    	editor.putString(THEME_TWO_NAME, mEditText.getText().toString());
					    	editor.commit();
											
						} else if (mThemeNum == 3 ) {
							
							mThemeThreeText.setText(mEditText.getText().toString());
					        prefMgr = getActivity().getBaseContext().getSharedPreferences(
					        		"Junk_Theme_Three", Context.MODE_WORLD_READABLE);   
					        editor = prefMgr.edit();
					    	editor.putString(THEME_THREE_NAME, mEditText.getText().toString());
					    	editor.commit();

						}
					}
				};
				
				
	// Battery Icons			
    DialogInterface.OnClickListener BatteryListListener =
    		new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					BatteryIconValue = which;
				}
			};

	DialogInterface.OnClickListener batteryDialogPositiveListener =
	   		new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					editor = prefMgr.edit();
			    	editor.putString(BATTERY_ICONS, String.valueOf(BatteryIconValue));
			    	editor.commit();
			    	resendBatteryIntents();
		  			if (BatteryIconValue == 6) {
		   				mBatterySettings.setEnabled(false);
		   				mChargingSettings.setEnabled(false);
		   			} else if (BatteryIconValue == 0) {
		   				mChargingSettings.setEnabled(false);
		   			} else {
		   				mBatterySettings.setEnabled(true);
		   				mChargingSettings.setEnabled(true);
		   			}

				}
			};

	// Backup Settings
    DialogInterface.OnClickListener backupDialogPositiveListener =
    		new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
			        try {
			        	if (BackupUtils.settingsExist()) {
			        		BackupUtils.copyBackup("data/data/com.android.settings/shared_prefs/Junk_Settings.xml",
			        				"sdcard/.junk/backup/Junk_Settings.xml");
			        		Toast.makeText(getActivity().getBaseContext(), "Backup Successful", Toast.LENGTH_SHORT).show();
			        	}
			        } catch (IOException e) {
			        	Toast.makeText(getActivity().getBaseContext(), "ERROR Backing up settings", Toast.LENGTH_SHORT).show();
			        	Log.e("JUNK: ","ERROR BACKING UP SETTINGS");
			        };	
			        
			        
			        try {        
			        	if (BackupUtils.themeOneExist()) {
			        		BackupUtils.copyBackup("data/data/com.android.settings/shared_prefs/Junk_Theme_One.xml",
			        				"sdcard/.junk/backup/Junk_Theme_One.xml");
			        	}
			        } catch (IOException e) {
			        	Log.e("JUNK: ","ERROR BACKING UP SETTINGS - Theme One");
			        };	
			        	
			        try {        
			        	if (BackupUtils.themeTwoExist()) {
			        		BackupUtils.copyBackup("data/data/com.android.settings/shared_prefs/Junk_Theme_Two.xml",
									"sdcard/.junk/backup/Junk_Theme_Two.xml");
			        	}
			        } catch (IOException e) {
			        	Log.e("JUNK: ","ERROR BACKING UP SETTINGS - Theme Two");
			        };	

			        try {        
			        	if (BackupUtils.themeThreeExist()) {
			        		BackupUtils.copyBackup("data/data/com.android.settings/shared_prefs/Junk_Theme_Three.xml",
									"sdcard/.junk/backup/Junk_Theme_Three.xml");
			        	}
			        } catch (IOException e) {
			        	Log.e("JUNK: ","ERROR BACKING UP SETTINGS - Theme Three");
			        };
				}
			};    
    
		// Restore Settings
		DialogInterface.OnClickListener restoreDialogPositiveListener =
            new DialogInterface.OnClickListener() {
								
				@Override
				public void onClick(DialogInterface dialog, int which) {
							
			        try {
			        	if (BackupUtils.backupExist(
			        			"sdcard/.junk/backup/Junk_Settings.xml")) {
			        		BackupUtils.copyBackup("sdcard/.junk/backup/Junk_Settings.xml",
									"data/data/com.android.settings/shared_prefs/Junk_Settings_Temp.xml");
			        		Toast.makeText(getActivity().getBaseContext(), "Restore Successful", Toast.LENGTH_SHORT).show();

			        		SharedPreferences prefTemp = getActivity().getBaseContext().getSharedPreferences("Junk_Settings_Temp", Context.MODE_WORLD_READABLE);
			        		SharedPreferences prefMgr = getActivity().getBaseContext().getSharedPreferences("Junk_Settings", Context.MODE_WORLD_READABLE);
			        		CustomSettingsUtils.GetSettings(prefTemp);
			        		CustomSettingsUtils.WriteSettings(prefMgr);
					        		
			        		CustomSettingsUtils.SendIntents(getActivity().getBaseContext());
					        		
			        	} else {
			        		Toast.makeText(getActivity().getBaseContext(), "No backup exists!", Toast.LENGTH_SHORT).show();	
			        	}
			        } catch (IOException e) {
			        	Log.e("JUNK: ","ERROR RESTORING SETTINGS");
			        };
					        
					        
			        try {        
			        	if (BackupUtils.backupExist("sdcard/.junk/backup/Junk_Theme_One.xml")) {
			        		BackupUtils.copyBackup("sdcard/.junk/backup/Junk_Theme_One.xml",
									"data/data/com.android.settings/shared_prefs/Junk_Theme_One.xml");
			        	}
			        } catch (IOException e) {
			        	Log.e("JUNK: ","ERROR RESTORING Theme One");
			        };	
					        	
			        try {        
			        	if (BackupUtils.backupExist("sdcard/.junk/backup/Junk_Theme_Two.xml")) {
			        		BackupUtils.copyBackup("sdcard/.junk/backup/Junk_Theme_Two.xml",
									"data/data/com.android.settings/shared_prefs/Junk_Theme_Two.xml");
			        	}
			        } catch (IOException e) {
			        	Log.e("JUNK: ","ERROR RESTORING Theme Two");
			        };	

			        try {        
			        	if (BackupUtils.backupExist("sdcard/.junk/backup/Junk_Theme_Three.xml")) {
			        		BackupUtils.copyBackup("sdcard/.junk/backup/Junk_Theme_Three.xml",
									"data/data/com.android.settings/shared_prefs/Junk_Theme_Three.xml");
			        	}
			        } catch (IOException e) {
			        	Log.e("JUNK: ","ERROR RESTORING Theme Three");
			        };
					
				}
			};			
			
					
	// Send Battery Intents
    private void resendBatteryIntents() {

    	Intent i = new Intent();
    	i.setAction(Junk_Battery_Settings );
   	   	i.putExtra(BATTERY_ICONS, (String) Integer.toString(BatteryIconValue));
   	   	getActivity().sendBroadcast(i);
   	   	i = null;

   	   	i = new Intent();
    	i.setAction(Junk_Battery_Settings );
   	   	i.putExtra(BATTERY_BAR_BOTTOM, prefMgr.getBoolean(BATTERY_BAR_BOTTOM, false));
   	   	getActivity().sendBroadcast(i);
   	   	i = null;       	   	

    	i = new Intent();
    	i.setAction(Junk_Battery_Settings );
  	   	i.putExtra(BATTERY_BAR_RIGHT, prefMgr.getBoolean(BATTERY_BAR_RIGHT, false));
   	   	getActivity().sendBroadcast(i);
  	   	i = null;       	   	

        i = new Intent();
    	i.setAction(Junk_Battery_Settings );
   	   	i.putExtra(BATTERY_DEPLETED_COLOR, prefMgr.getInt(BATTERY_DEPLETED_COLOR, 0xffababab));
   	   	getActivity().sendBroadcast(i);
  	   	i = null;              	   	
				   	   	
        i = new Intent();
    	i.setAction(Junk_Battery_Settings );
  	   	i.putExtra(BATTERY_BAR_WIDTH, prefMgr.getInt(BATTERY_BAR_WIDTH, 5));
   	   	getActivity().sendBroadcast(i);
  	   	i = null;       	   	
				   	   	
        i = new Intent();
        i.setAction(Junk_Battery_Settings );
        i.putExtra(BATTERY_LEVEL_ONE, prefMgr.getInt(BATTERY_LEVEL_ONE, 10));
        getActivity().sendBroadcast(i);
		i = null;
				    
		i = new Intent();
		i.setAction(Junk_Battery_Settings );
		i.putExtra(BATTERY_LEVEL_COLOR_ONE, prefMgr.getInt(BATTERY_LEVEL_COLOR_ONE, 0xffff0000));
		getActivity().sendBroadcast(i);
		i = null;
				         
		i = new Intent();
		i.setAction(Junk_Battery_Settings );
		i.putExtra(BATTERY_LEVEL_TWO, prefMgr.getInt(BATTERY_LEVEL_TWO, 30));
		getActivity().sendBroadcast(i);
		i = null;
				    
		i = new Intent();
		i.setAction(Junk_Battery_Settings );
		i.putExtra(BATTERY_LEVEL_COLOR_TWO, prefMgr.getInt(BATTERY_LEVEL_COLOR_TWO, 0xffff9000));
		getActivity().sendBroadcast(i);
		i = null;
			    
		i = new Intent();
		i.setAction(Junk_Battery_Settings );
		i.putExtra(BATTERY_LEVEL_COLOR_THREE, prefMgr.getInt(BATTERY_LEVEL_COLOR_THREE, 0xff3792b4));
		getActivity().sendBroadcast(i);
		i = null;
					
    }
				    		
    @Override
    public void onResume() {
        super.onResume();
        
    }							
					
}
