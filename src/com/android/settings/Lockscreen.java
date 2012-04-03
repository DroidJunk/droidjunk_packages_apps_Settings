package com.android.settings;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.preference.ListPreference;
import android.preference.Preference.OnPreferenceChangeListener;

import com.android.settings.R;

    public class Lockscreen extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener {

        private static final String LOCKSCREEN_LAYOUT = "pref_alt_layout";
        private static final String LOCKSCREEN_LANDSCAPE = "pref_landscape";

        ListPreference mLockscreenOption;
	CheckBoxPreference mLockscreenLandscape;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);


            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.custom_lockscreen);

        mLockscreenLandscape = (CheckBoxPreference) findPreference(LOCKSCREEN_LANDSCAPE);
        mLockscreenLandscape.setChecked(Settings.System.getInt(getActivity()
                .getContentResolver(), Settings.System.LOCKSCREEN_LANDSCAPE,
                0) == 1);

            mLockscreenOption = (ListPreference) findPreference(LOCKSCREEN_LAYOUT);
            mLockscreenOption.setOnPreferenceChangeListener(this);
            mLockscreenOption.setValue(Settings.System.getInt(
                    getActivity().getContentResolver(), Settings.System.ALT_LAYOUTS,
                    0) + "");
        }

	@Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
            Preference preference) {
        if (preference == mLockscreenLandscape) {
            Settings.System.putInt(getActivity().getContentResolver(),
                    Settings.System.LOCKSCREEN_LANDSCAPE,
                    ((CheckBoxPreference) preference).isChecked() ? 1 : 0);
            return true;

		}
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            if (preference == mLockscreenOption) {
                int val = Integer.parseInt((String) newValue);
                Settings.System.putInt(getActivity().getContentResolver(),
                        Settings.System.ALT_LAYOUTS, val);
                return true;
                
            }
            return false;
        }

}
