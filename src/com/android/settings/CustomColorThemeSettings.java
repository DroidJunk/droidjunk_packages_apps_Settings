package com.android.settings;

import android.os.Bundle;
import android.preference.PreferenceScreen;

public class CustomColorThemeSettings extends SettingsPreferenceFragment {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.custom_color_theme_settings);


    }
}
