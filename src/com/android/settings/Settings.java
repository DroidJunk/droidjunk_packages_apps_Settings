/*
 * Copyright (C) 2008 The Android Open Source Project
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

import com.android.settings.accounts.AccountSyncSettings;
import com.android.settings.bluetooth.BluetoothEnabler;
import com.android.settings.fuelgauge.PowerUsageSummary;
import com.android.settings.wifi.WifiEnabler;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.Switch;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Top-level settings activity to handle single pane and double pane UI layout.
 */
public class Settings extends PreferenceActivity implements ButtonBarHandler {

    private static final String LOG_TAG = "Settings";
    private static final String META_DATA_KEY_HEADER_ID =
        "com.android.settings.TOP_LEVEL_HEADER_ID";
    private static final String META_DATA_KEY_FRAGMENT_CLASS =
        "com.android.settings.FRAGMENT_CLASS";
    private static final String META_DATA_KEY_PARENT_TITLE =
        "com.android.settings.PARENT_FRAGMENT_TITLE";
    private static final String META_DATA_KEY_PARENT_FRAGMENT_CLASS =
        "com.android.settings.PARENT_FRAGMENT_CLASS";

    private static final String EXTRA_CLEAR_UI_OPTIONS = "settings:remove_ui_options";

    private static final String SAVE_KEY_CURRENT_HEADER = "com.android.settings.CURRENT_HEADER";
    private static final String SAVE_KEY_PARENT_HEADER = "com.android.settings.PARENT_HEADER";

    private String mFragmentClass;
    private int mTopLevelHeaderId;
    private Header mFirstHeader;
    private Header mCurrentHeader;
    private Header mParentHeader;
    private boolean mInLocalHeaderSwitch;

    // TODO: Update Call Settings based on airplane mode state.

    protected HashMap<Integer, Integer> mHeaderIndexMap = new HashMap<Integer, Integer>();
    private List<Header> mHeaders;
    
    
    
    
    
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
	private final String DATE_COLOR = "date_color";
	private final String DATE_SIZE = "date_size";
	private final String CLEAR_BUTTON_COLOR = "clear_button_color";
	private final String CLOSE_BAR_COLOR = "close_bar_color";

    private final String Junk_Battery_Settings = "JUNK_BATTERY_SETTINGS";	
	private final String BATTERY_ICONS = "battery_icon_num";
	private final String BATTERY_BAR_BOTTOM = "battery_bar_bottom";
	private final String BATTERY_BAR_RIGHT = "battery_bar_right";
	private final String BATTERY_BAR_WIDTH = "battery_bar_width";
	private final String BATTERY_LEVEL_ONE = "battery_levels_one";
	private final String BATTERY_LEVEL_COLOR_ONE = "battery_levels_color_one";
	private final String BATTERY_LEVEL_TWO = "battery_levels_two";
	private final String BATTERY_LEVEL_COLOR_TWO = "battery_levels_color_two";
	private final String BATTERY_LEVEL_COLOR_THREE = "battery_levels_color_three";    
	private final String DEPLETED_LEVEL_ONE = "depleted_levels_one";
	private final String DEPLETED_LEVEL_COLOR_ONE = "depleted_levels_color_one";
	private final String DEPLETED_LEVEL_TWO = "depleted_levels_two";
	private final String DEPLETED_LEVEL_COLOR_TWO = "depleted_levels_color_two";
	private final String DEPLETED_LEVEL_COLOR_THREE = "depleted_levels_color_three";    
	private final String CHARGING_LEVEL_ONE = "charge_levels_one";
	private final String CHARGING_LEVEL_COLOR_ONE = "charge_levels_color_one";
	private final String CHARGING_LEVEL_TWO = "charge_levels_two";
	private final String CHARGING_LEVEL_COLOR_TWO = "charge_levels_color_two";
	private final String CHARGING_LEVEL_COLOR_THREE = "charge_levels_color_three";    
	
	private final String Junk_Clock_Settings = "JUNK_CLOCK_SETTINGS";
    private final String SHOW_CLOCK = "show_clock";
	private final String CLOCK_AMPM = "clock_ampm";
	private final String CLOCK_COLOR = "clock_color";
	private final String CLOCK_SIZE = "clock_size";
	
	private final String Junk_Toggle_Settings = "JUNK_TOGGLE_SETTINGS";
	private final String TOGGLES_UPDATE = "toggles_update";
	private final String TOGGLES_ON = "toggles_show_toggles";
	private final String TOGGLES_TOP = "toggles_top";
	private final String TOGGLE_COLOR = "toggle_color";
	private final String TOGGLE_ICON_ON_COLOR = "toggles_icon_on_color";
	private final String TOGGLE_ICON_INTER_COLOR = "toggles_icon_inter_color";
	private final String TOGGLE_ICON_OFF_COLOR = "toggles_icon_off_color";
	private final String TOGGLE_SHOW_INDICATOR = "toggle_show_indicator";
	private final String TOGGLE_IND_ON_COLOR = "toggle_ind_on_color";
	private final String TOGGLE_IND_OFF_COLOR = "toggle_ind_off_color";
	private final String TOGGLE_SHOW_TEXT = "toggle_show_text";
	private final String TOGGLE_TEXT_ON_COLOR = "toggle_text_on_color";
	private final String TOGGLE_TEXT_OFF_COLOR = "toggle_text_off_color";
	private final String TOGGLE_SHOW_DIVIDER = "toggle_show_divider";
	private final String TOGGLE_DIVIDER_COLOR = "toggle_divider_color";
	private final String TOGGLES_TORCH_ON = "toggles_show_torch";
	private final String TOGGLES_4G_ON = "toggles_show_fourg";
	private final String TOGGLES_WIFI_ON = "toggles_show_wifi";
	private final String TOGGLES_GPS_ON = "toggles_show_gps";
	private final String TOGGLES_BLUETOOTH_ON = "toggles_show_bluetooth";
	private final String TOGGLES_SOUND_ON = "toggles_show_sound";
	private final String TOGGLES_AIRPLANE_ON = "toggles_show_airplane";
	private final String TOGGLES_BRIGHTNESS_ON = "toggles_show_brightness";
	private final String TOGGLES_ROTATE_ON = "toggles_show_rotate";
	private final String TOGGLES_SYNC_ON = "toggles_show_sync";
	private final String TOGGLES_DATA_ON = "toggles_show_data";
	
	private final String Junk_Icon_Settings = "JUNK_ICON_SETTINGS";
	private final String ICON_COLOR = "icon_color";
	
	private final String Junk_NavBar_Settings = "JUNK_NAVBAR_SETTINGS";
    private final String NAV_BAR_COLOR = "nav_button_color";
    private final String SHOW_SEARCH_BUTTON = "search_button";
	private final String SHOW_LEFT_MENU_BUTTON = "left_menu_button";
	private final String SHOW_RIGHT_MENU_BUTTON = "right_menu_button";
    private final String SHOW_SEARCH_BUTTON_LAND = "search_button_land";
	private final String SHOW_TOP_MENU_BUTTON_LAND = "top_menu_button_land";
	private final String SHOW_BOT_MENU_BUTTON_LAND = "bottom_menu_button_land";

	private SharedPreferences prefMgr ;
	
	
	
    private boolean batteryBarBottom, batteryBarRight;
    private boolean showCarrier, carrierCustom, showBatteryLabel, showTempLabel;
    private boolean showClock, clockAmPm, togglesShowToggles, togglesTop; 
    private boolean togglesShowIndicator, toggleShowText, toggleShowDivider;
    private boolean showTorch, showFourg, showWifi, showGps, showBluetooth, showSound, showAirplane;
    private boolean showBrightness, showRotate, showSync, showData;
    private boolean showSearchButton, showLeftMenuButton, showLeftMenuButtonLand;
    private boolean showRightMenuButton, showRightMenuButtonLand, showSearchButtonLand;
    private boolean showTopMenuButtonLand, showBotMenuButtonLand;
    private int batteryBarWidth, batteryLevelOne, batteryLevelOneColor;
    private int batteryLevelTwo, batteryLevelTwoColor, batteryLevelThreeColor, depletedLevelOne;
    private int depletedLevelOneColor, depletedLevelTwo, depletedLevelTwoColor, depletedLevelThreeColor;
    private int chargingLevelOne, chargingLevelOneColor, chargingLevelTwo, chargingLevelTwoColor, chargingLevelThreeColor;
    private int batteryLabelLevelColor, batteryLabelLevelSize, batteryLabelTempColor, batteryLabelTempSize;
    private int carrierColor, iconColor, clockColor, clockSize, toggleColor, toggleIconOnColor;
    private int toggleIconInterColor, toggleIconOffColor, toggleIndOnColor, toggleIndOffColor;
    private int toggleTextOnColor, toggleTextOffColor, toggleDividerColor, navBarColor;
    private int carrierSize, dateColor, dateSize, clearButtonColor, closeBarColor;
	private String carrierCustomText, batteryIconNum;

    
    
    
    
    
    
    
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getIntent().getBooleanExtra(EXTRA_CLEAR_UI_OPTIONS, false)) {
            getWindow().setUiOptions(0);
        }

        File junkBackupDir = new File("/sdcard/.junk/backup/");
        junkBackupDir.mkdirs();        

        boolean settingsRestored = false;
        try {
        	if (!BackupUtils.settingsExist() & BackupUtils.backupExist(
        			"sdcard/.junk/backup/Junk_Settings.xml")) {
        		BackupUtils.copyBackup("sdcard/.junk/backup/Junk_Settings.xml",
						"data/data/com.android.settings/shared_prefs/Junk_Settings.xml");
        		Log.v("JUNK: Settings Backup Found: ","Restoring Junk Settings");
        		settingsRestored = true;
        	} else {
        		Log.v("JUNK: Settings Restore: ","Settings exist or there is not a backup");	
        	}
        } catch (IOException e) {
        	Log.e("JUNK: Settings Restore: ","ERROR JUNK SETTINGS");
        };	

        try {        
        	if (!BackupUtils.themeOneExist() & BackupUtils.backupExist(
        			"sdcard/.junk/backup/Junk_Theme_One.xml")) {
        		BackupUtils.copyBackup("sdcard/.junk/backup/Junk_Theme_One.xml",
						"data/data/com.android.settings/shared_prefs/Junk_Theme_One.xml");
        	}
        } catch (IOException e) {
        	Log.e("JUNK: Settings Restore: ","ERROR THEME ONE");
        };	
        	
        try {        
        	if (!BackupUtils.themeTwoExist() & BackupUtils.backupExist(
        			"sdcard/.junk/backup/Junk_Theme_Two.xml")) {
        		BackupUtils.copyBackup("sdcard/.junk/backup/Junk_Theme_Two.xml",
						"data/data/com.android.settings/shared_prefs/Junk_Theme_Two.xml");
        	}
        } catch (IOException e) {
        	Log.e("JUNK: Settings Restore: ","ERROR THEME TWO");
        };	

        try {        
        	if (!BackupUtils.themeThreeExist() & BackupUtils.backupExist(
        			"sdcard/.junk/backup/Junk_Theme_Three.xml")) {
        		BackupUtils.copyBackup("sdcard/.junk/backup/Junk_Theme_Three.xml",
						"data/data/com.android.settings/shared_prefs/Junk_Theme_Three.xml");
        	}
        } catch (IOException e) {
        	Log.e("JUNK: Settings Restore: ","ERROR THEME THREE");
        };
        
        if (settingsRestored) {
            SharedPreferences prefMgr = getBaseContext().getSharedPreferences(
            		"Junk_Settings", Context.MODE_PRIVATE);

        	CustomSettingsUtils.GetSettings(prefMgr);
        	CustomSettingsUtils.SendIntents(getBaseContext());
        	
        }
      
		
        getMetaData();
        mInLocalHeaderSwitch = true;
        super.onCreate(savedInstanceState);
        mInLocalHeaderSwitch = false;

        if (!onIsHidingHeaders() && onIsMultiPane()) {
            highlightHeader();
            // Force the title so that it doesn't get overridden by a direct launch of
            // a specific settings screen.
            setTitle(R.string.settings_label);
        }

        // Retrieve any saved state
        if (savedInstanceState != null) {
            mCurrentHeader = savedInstanceState.getParcelable(SAVE_KEY_CURRENT_HEADER);
            mParentHeader = savedInstanceState.getParcelable(SAVE_KEY_PARENT_HEADER);
        }

        // If the current header was saved, switch to it
        if (savedInstanceState != null && mCurrentHeader != null) {
            //switchToHeaderLocal(mCurrentHeader);
            showBreadCrumbs(mCurrentHeader.title, null);
        }

        if (mParentHeader != null) {
            setParentTitle(mParentHeader.title, null, new OnClickListener() {
                public void onClick(View v) {
                    switchToParent(mParentHeader.fragment);
                }
            });
        }

        // TODO Add support for android.R.id.home in all Setting's onOptionsItemSelected
        // getActionBar().setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP,
        // ActionBar.DISPLAY_HOME_AS_UP);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current fragment, if it is the same as originally launched
        if (mCurrentHeader != null) {
            outState.putParcelable(SAVE_KEY_CURRENT_HEADER, mCurrentHeader);
        }
        if (mParentHeader != null) {
            outState.putParcelable(SAVE_KEY_PARENT_HEADER, mParentHeader);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        ListAdapter listAdapter = getListAdapter();
        if (listAdapter instanceof HeaderAdapter) {
            ((HeaderAdapter) listAdapter).resume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        ListAdapter listAdapter = getListAdapter();
        if (listAdapter instanceof HeaderAdapter) {
            ((HeaderAdapter) listAdapter).pause();
        }
    }

    private void switchToHeaderLocal(Header header) {
        mInLocalHeaderSwitch = true;
        switchToHeader(header);
        mInLocalHeaderSwitch = false;
    }

    @Override
    public void switchToHeader(Header header) {
        if (!mInLocalHeaderSwitch) {
            mCurrentHeader = null;
            mParentHeader = null;
        }
        super.switchToHeader(header);
    }

    /**
     * Switch to parent fragment and store the grand parent's info
     * @param className name of the activity wrapper for the parent fragment.
     */
    private void switchToParent(String className) {
        final ComponentName cn = new ComponentName(this, className);
        try {
            final PackageManager pm = getPackageManager();
            final ActivityInfo parentInfo = pm.getActivityInfo(cn, PackageManager.GET_META_DATA);

            if (parentInfo != null && parentInfo.metaData != null) {
                String fragmentClass = parentInfo.metaData.getString(META_DATA_KEY_FRAGMENT_CLASS);
                CharSequence fragmentTitle = parentInfo.loadLabel(pm);
                Header parentHeader = new Header();
                parentHeader.fragment = fragmentClass;
                parentHeader.title = fragmentTitle;
                mCurrentHeader = parentHeader;

                switchToHeaderLocal(parentHeader);
                highlightHeader();

                mParentHeader = new Header();
                mParentHeader.fragment
                        = parentInfo.metaData.getString(META_DATA_KEY_PARENT_FRAGMENT_CLASS);
                mParentHeader.title = parentInfo.metaData.getString(META_DATA_KEY_PARENT_TITLE);
            }
        } catch (NameNotFoundException nnfe) {
            Log.w(LOG_TAG, "Could not find parent activity : " + className);
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        // If it is not launched from history, then reset to top-level
        if ((intent.getFlags() & Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY) == 0
                && mFirstHeader != null && !onIsHidingHeaders() && onIsMultiPane()) {
            switchToHeaderLocal(mFirstHeader);
        }
    }

    private void highlightHeader() {
        if (mTopLevelHeaderId != 0) {
            Integer index = mHeaderIndexMap.get(mTopLevelHeaderId);
            if (index != null) {
                getListView().setItemChecked(index, true);
                getListView().smoothScrollToPosition(index);
            }
        }
    }

    @Override
    public Intent getIntent() {
        Intent superIntent = super.getIntent();
        String startingFragment = getStartingFragmentClass(superIntent);
        // This is called from super.onCreate, isMultiPane() is not yet reliable
        // Do not use onIsHidingHeaders either, which relies itself on this method
        if (startingFragment != null && !onIsMultiPane()) {
            Intent modIntent = new Intent(superIntent);
            modIntent.putExtra(EXTRA_SHOW_FRAGMENT, startingFragment);
            Bundle args = superIntent.getExtras();
            if (args != null) {
                args = new Bundle(args);
            } else {
                args = new Bundle();
            }
            args.putParcelable("intent", superIntent);
            modIntent.putExtra(EXTRA_SHOW_FRAGMENT_ARGUMENTS, superIntent.getExtras());
            return modIntent;
        }
        return superIntent;
    }

    /**
     * Checks if the component name in the intent is different from the Settings class and
     * returns the class name to load as a fragment.
     */
    protected String getStartingFragmentClass(Intent intent) {
        if (mFragmentClass != null) return mFragmentClass;

        String intentClass = intent.getComponent().getClassName();
        if (intentClass.equals(getClass().getName())) return null;

        if ("com.android.settings.ManageApplications".equals(intentClass)
                || "com.android.settings.RunningServices".equals(intentClass)
                || "com.android.settings.applications.StorageUse".equals(intentClass)) {
            // Old names of manage apps.
            intentClass = com.android.settings.applications.ManageApplications.class.getName();
        }

        return intentClass;
    }

    /**
     * Override initial header when an activity-alias is causing Settings to be launched
     * for a specific fragment encoded in the android:name parameter.
     */
    @Override
    public Header onGetInitialHeader() {
        String fragmentClass = getStartingFragmentClass(super.getIntent());
        if (fragmentClass != null) {
            Header header = new Header();
            header.fragment = fragmentClass;
            header.title = getTitle();
            header.fragmentArguments = getIntent().getExtras();
            mCurrentHeader = header;
            return header;
        }

        return mFirstHeader;
    }

    @Override
    public Intent onBuildStartFragmentIntent(String fragmentName, Bundle args,
            int titleRes, int shortTitleRes) {
        Intent intent = super.onBuildStartFragmentIntent(fragmentName, args,
                titleRes, shortTitleRes);

        // some fragments want to avoid split actionbar
        if (DataUsageSummary.class.getName().equals(fragmentName) ||
                PowerUsageSummary.class.getName().equals(fragmentName) ||
                AccountSyncSettings.class.getName().equals(fragmentName) ||
                UserDictionarySettings.class.getName().equals(fragmentName)) {
            intent.putExtra(EXTRA_CLEAR_UI_OPTIONS, true);
        }

        intent.setClass(this, SubSettings.class);
        return intent;
    }
    
    /**
     * Populate the activity with the top-level headers.
     */
    @Override
    public void onBuildHeaders(List<Header> headers) {
        loadHeadersFromResource(R.xml.settings_headers, headers);

        updateHeaderList(headers);

        mHeaders = headers;
    }

    private void updateHeaderList(List<Header> target) {
        int i = 0;
        while (i < target.size()) {
            Header header = target.get(i);
            // Ids are integers, so downcasting
            int id = (int) header.id;
            if (id == R.id.dock_settings) {
                if (!needsDockSettings())
                    target.remove(header);
            } else if (id == R.id.operator_settings || id == R.id.manufacturer_settings) {
                Utils.updateHeaderToSpecificActivityFromMetaDataOrRemove(this, target, header);
            } else if (id == R.id.wifi_settings) {
                // Remove WiFi Settings if WiFi service is not available.
                if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_WIFI)) {
                    target.remove(header);
                }
            } else if (id == R.id.bluetooth_settings) {
                // Remove Bluetooth Settings if Bluetooth service is not available.
                if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH)) {
                    target.remove(header);
                }
            }

            // Increment if the current one wasn't removed by the Utils code.
            if (target.get(i) == header) {
                // Hold on to the first header, when we need to reset to the top-level
                if (mFirstHeader == null &&
                        HeaderAdapter.getHeaderType(header) != HeaderAdapter.HEADER_TYPE_CATEGORY) {
                    mFirstHeader = header;
                }
                mHeaderIndexMap.put(id, i);
                i++;
            }
        }
    }

    private boolean needsDockSettings() {
        return getResources().getBoolean(R.bool.has_dock_settings);
    }

    private void getMetaData() {
        try {
            ActivityInfo ai = getPackageManager().getActivityInfo(getComponentName(),
                    PackageManager.GET_META_DATA);
            if (ai == null || ai.metaData == null) return;
            mTopLevelHeaderId = ai.metaData.getInt(META_DATA_KEY_HEADER_ID);
            mFragmentClass = ai.metaData.getString(META_DATA_KEY_FRAGMENT_CLASS);
            
            // Check if it has a parent specified and create a Header object
            final int parentHeaderTitleRes = ai.metaData.getInt(META_DATA_KEY_PARENT_TITLE);
            String parentFragmentClass = ai.metaData.getString(META_DATA_KEY_PARENT_FRAGMENT_CLASS);
            if (parentFragmentClass != null) {
                mParentHeader = new Header();
                mParentHeader.fragment = parentFragmentClass;
                if (parentHeaderTitleRes != 0) {
                    mParentHeader.title = getResources().getString(parentHeaderTitleRes);
                }
            }
        } catch (NameNotFoundException nnfe) {
            // No recovery
        }
    }

    @Override
    public boolean hasNextButton() {
        return super.hasNextButton();
    }

    @Override
    public Button getNextButton() {
        return super.getNextButton();
    }

    private static class HeaderAdapter extends ArrayAdapter<Header> {
        static final int HEADER_TYPE_CATEGORY = 0;
        static final int HEADER_TYPE_NORMAL = 1;
        static final int HEADER_TYPE_SWITCH = 2;
        private static final int HEADER_TYPE_COUNT = HEADER_TYPE_SWITCH + 1;

        private final WifiEnabler mWifiEnabler;
        private final BluetoothEnabler mBluetoothEnabler;

        private static class HeaderViewHolder {
            ImageView icon;
            TextView title;
            TextView summary;
            Switch switch_;
        }

        private LayoutInflater mInflater;

        static int getHeaderType(Header header) {
            if (header.fragment == null && header.intent == null) {
                return HEADER_TYPE_CATEGORY;
            } else if (header.id == R.id.wifi_settings || header.id == R.id.bluetooth_settings) {
                return HEADER_TYPE_SWITCH;
            } else {
                return HEADER_TYPE_NORMAL;
            }
        }

        @Override
        public int getItemViewType(int position) {
            Header header = getItem(position);
            return getHeaderType(header);
        }

        @Override
        public boolean areAllItemsEnabled() {
            return false; // because of categories
        }

        @Override
        public boolean isEnabled(int position) {
            return getItemViewType(position) != HEADER_TYPE_CATEGORY;
        }

        @Override
        public int getViewTypeCount() {
            return HEADER_TYPE_COUNT;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        public HeaderAdapter(Context context, List<Header> objects) {
            super(context, 0, objects);
            mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            
            // Temp Switches provided as placeholder until the adapter replaces these with actual
            // Switches inflated from their layouts. Must be done before adapter is set in super
            mWifiEnabler = new WifiEnabler(context, new Switch(context));
            mBluetoothEnabler = new BluetoothEnabler(context, new Switch(context));
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            HeaderViewHolder holder;
            Header header = getItem(position);
            int headerType = getHeaderType(header);
            View view = null;

            if (convertView == null) {
                holder = new HeaderViewHolder();
                switch (headerType) {
                    case HEADER_TYPE_CATEGORY:
                        view = new TextView(getContext(), null,
                                android.R.attr.listSeparatorTextViewStyle);
                        holder.title = (TextView) view;
                        break;

                    case HEADER_TYPE_SWITCH:
                        view = mInflater.inflate(R.layout.preference_header_switch_item, parent,
                                false);
                        holder.icon = (ImageView) view.findViewById(R.id.icon);
                        holder.title = (TextView)
                                view.findViewById(com.android.internal.R.id.title);
                        holder.summary = (TextView)
                                view.findViewById(com.android.internal.R.id.summary);
                        holder.switch_ = (Switch) view.findViewById(R.id.switchWidget);
                        break;

                    case HEADER_TYPE_NORMAL:
                        view = mInflater.inflate(
                                com.android.internal.R.layout.preference_header_item, parent,
                                false);
                        holder.icon = (ImageView) view.findViewById(com.android.internal.R.id.icon);
                        holder.title = (TextView)
                                view.findViewById(com.android.internal.R.id.title);
                        holder.summary = (TextView)
                                view.findViewById(com.android.internal.R.id.summary);
                        break;
                }
                view.setTag(holder);
            } else {
                view = convertView;
                holder = (HeaderViewHolder) view.getTag();
            }

            // All view fields must be updated every time, because the view may be recycled
            switch (headerType) {
                case HEADER_TYPE_CATEGORY:
                    holder.title.setText(header.getTitle(getContext().getResources()));
                    break;

                case HEADER_TYPE_SWITCH:
                    // Would need a different treatment if the main menu had more switches
                    if (header.id == R.id.wifi_settings) {
                        mWifiEnabler.setSwitch(holder.switch_);
                    } else {
                        mBluetoothEnabler.setSwitch(holder.switch_);
                    }
                    // No break, fall through on purpose to update common fields

                    //$FALL-THROUGH$
                case HEADER_TYPE_NORMAL:
                    holder.icon.setImageResource(header.iconRes);
                    holder.title.setText(header.getTitle(getContext().getResources()));
                    CharSequence summary = header.getSummary(getContext().getResources());
                    if (!TextUtils.isEmpty(summary)) {
                        holder.summary.setVisibility(View.VISIBLE);
                        holder.summary.setText(summary);
                    } else {
                        holder.summary.setVisibility(View.GONE);
                    }
                    break;
            }

            return view;
        }

        public void resume() {
            mWifiEnabler.resume();
            mBluetoothEnabler.resume();
        }
        
        public void pause() {
            mWifiEnabler.pause();
            mBluetoothEnabler.pause();
        }
    }

    @Override
    public boolean onPreferenceStartFragment(PreferenceFragment caller, Preference pref) {
        // Override the fragment title for Wallpaper settings
        int titleRes = pref.getTitleRes();
        if (pref.getFragment().equals(WallpaperTypeSettings.class.getName())) {
            titleRes = R.string.wallpaper_settings_fragment_title;
        }

        if (pref.getFragment().equals(CustomBatteryOptions.class.getName())) {
            titleRes = R.string.junk_settings_battery_options;
        }
        
        if (pref.getFragment().equals(CustomChargeOptions.class.getName())) {
            titleRes = R.string.junk_settings_charge_options;    
        }        

        if (pref.getFragment().equals(CustomClockSettings.class.getName())) {
            titleRes = R.string.junk_settings_clock;
        }

        if (pref.getFragment().equals(CustomLedSettings.class.getName())) {
            titleRes = R.string.junk_settings_led;
        }
        
        
        if (pref.getFragment().equals(CustomNavBarSettings.class.getName())) {
            titleRes = R.string.junk_settings_navigation;
        }

        if (pref.getFragment().equals(CustomPulldownSettings.class.getName())) {
            titleRes = R.string.junk_settings_pulldown;
        }
        
        if (pref.getFragment().equals(CustomColorSettings.class.getName())) {
            titleRes = R.string.junk_settings_color;
        }
        if (pref.getFragment().equals(CustomQuickColorSettings.class.getName())) {
            titleRes = R.string.junk_settings_quickcolor;
        }
        
        if (pref.getFragment().equals(CustomQuietSettings.class.getName())) {
            titleRes = R.string.junk_settings_quiettime;
        }
       
        if (pref.getFragment().equals(CustomThemeSettings.class.getName())) {
            titleRes = R.string.junk_settings_themes;
        }
        
        if (pref.getFragment().equals(CustomToggleSettings.class.getName())) {
            titleRes = R.string.junk_settings_toggles;
        }
       
        if (pref.getFragment().equals(CustomBackup.class.getName())) {
            titleRes = R.string.junk_settings_backup;
        }
        

        
        
        
        
        
        
        startPreferencePanel(pref.getFragment(), pref.getExtras(), titleRes, "test", null, 0);
        return true;
    }

    @Override
    public void setListAdapter(ListAdapter adapter) {
        if (mHeaders == null) {
            mHeaders = new ArrayList<Header>();
            // When the saved state provides the list of headers, onBuildHeaders is not called
            // Copy the list of Headers from the adapter, preserving their order
            for (int i = 0; i < adapter.getCount(); i++) {
                mHeaders.add((Header) adapter.getItem(i));
            }
        }

        // Ignore the adapter provided by PreferenceActivity and substitute ours instead
        super.setListAdapter(new HeaderAdapter(this, mHeaders));
    }


    
    /*
     * Settings subclasses for launching independently.
     */
    public static class BluetoothSettingsActivity extends Settings { /* empty */ }
    public static class WirelessSettingsActivity extends Settings { /* empty */ }
    public static class TetherSettingsActivity extends Settings { /* empty */ }
    public static class VpnSettingsActivity extends Settings { /* empty */ }
    public static class DateTimeSettingsActivity extends Settings { /* empty */ }
    public static class StorageSettingsActivity extends Settings { /* empty */ }
    public static class WifiSettingsActivity extends Settings { /* empty */ }
    public static class WifiP2pSettingsActivity extends Settings { /* empty */ }
    public static class InputMethodAndLanguageSettingsActivity extends Settings { /* empty */ }
    public static class InputMethodAndSubtypeEnablerActivity extends Settings { /* empty */ }
    public static class SpellCheckersSettingsActivity extends Settings { /* empty */ }
    public static class LocalePickerActivity extends Settings { /* empty */ }
    public static class UserDictionarySettingsActivity extends Settings { /* empty */ }
    public static class SoundSettingsActivity extends Settings { /* empty */ }
    public static class DisplaySettingsActivity extends Settings { /* empty */ }
    public static class DeviceInfoSettingsActivity extends Settings { /* empty */ }
    public static class ApplicationSettingsActivity extends Settings { /* empty */ }
    public static class ManageApplicationsActivity extends Settings { /* empty */ }
    public static class StorageUseActivity extends Settings { /* empty */ }
    public static class DevelopmentSettingsActivity extends Settings { /* empty */ }
    public static class AccessibilitySettingsActivity extends Settings { /* empty */ }
    public static class SecuritySettingsActivity extends Settings { /* empty */ }
    public static class LocationSettingsActivity extends Settings { /* empty */ }
    public static class PrivacySettingsActivity extends Settings { /* empty */ }
    public static class DockSettingsActivity extends Settings { /* empty */ }
    public static class RunningServicesActivity extends Settings { /* empty */ }
    public static class ManageAccountsSettingsActivity extends Settings { /* empty */ }
    public static class PowerUsageSummaryActivity extends Settings { /* empty */ }
    public static class AccountSyncSettingsActivity extends Settings { /* empty */ }
    public static class AccountSyncSettingsInAddAccountActivity extends Settings { /* empty */ }
    public static class CryptKeeperSettingsActivity extends Settings { /* empty */ }
    public static class DeviceAdminSettingsActivity extends Settings { /* empty */ }
    public static class DataUsageSummaryActivity extends Settings { /* empty */ }
    public static class AdvancedWifiSettingsActivity extends Settings { /* empty */ }
    public static class TextToSpeechSettingsActivity extends Settings { /* empty */ }
    public static class AndroidBeamSettingsActivity extends Settings { /* empty */ }
    public static class CustomJunkSettingsActivity extends Settings { /* empty */ }
    
    

    
    
}
