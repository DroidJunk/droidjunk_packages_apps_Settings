package com.android.settings;

import android.os.Bundle;
import android.preference.PreferenceScreen;

public class CustomJunk extends SettingsPreferenceFragment {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.custom_junk_settings);


    }
}
