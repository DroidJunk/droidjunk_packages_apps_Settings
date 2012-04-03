package com.android.settings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;

import java.util.List;

public class Tranquility extends SettingsPreferenceFragment {

    private static final String STATUSBAR = "statusbar_settings";
    private static final String NOTIFICATION = "notification_settings";
    private static final String TOOLBOX = "toolbox_settings";
    private static final String LOCKSCREEN = "lockscreen_settings";
    private static final String NAVIGATION = "navigation_settings";

    PreferenceScreen mStatusbar;
    PreferenceScreen mLockscreen;
    PreferenceScreen mNotification;
    PreferenceScreen mNavigation;
    PreferenceScreen mToolbox;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.tranquility_settings);

        mStatusbar = (PreferenceScreen) findPreference(STATUSBAR);
        mNotification = (PreferenceScreen) findPreference(NOTIFICATION);
        mToolbox = (PreferenceScreen) findPreference(TOOLBOX);
        mLockscreen = (PreferenceScreen) findPreference(LOCKSCREEN);
        mNavigation = (PreferenceScreen) findPreference(NAVIGATION);

    }
}
