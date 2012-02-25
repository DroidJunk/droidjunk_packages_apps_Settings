package com.android.settings;

import android.app.Activity;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.provider.Settings;
import android.preference.ListPreference;
import android.preference.Preference.OnPreferenceChangeListener;
import net.margaritov.preference.colorpicker.ColorPickerPreference;

import com.android.settings.R;

    public class NavBar extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener {

        private static final String PREF_NAV_COLOR = "nav_button_color";
	private static final String SHOW_SEARCH_BUTTON = "search_button";

        CheckBoxPreference mShowSearchButton;
	ColorPickerPreference mNavigationBarColor;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.navbar);

 	    mShowSearchButton = (CheckBoxPreference) findPreference(SHOW_SEARCH_BUTTON);
	    mShowSearchButton.setChecked(Settings.System.getInt(getActivity()
                    .getContentResolver(), Settings.System.SHOW_SEARCH_BUTTON,
                    0) == 1);

            mNavigationBarColor = (ColorPickerPreference) findPreference(PREF_NAV_COLOR);
            mNavigationBarColor.setOnPreferenceChangeListener(this);
	}


       public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
                Preference preference) {
           if (preference == mShowSearchButton) {
                Settings.System.putInt(getActivity().getContentResolver(),
                        Settings.System.SHOW_SEARCH_BUTTON,
                        ((CheckBoxPreference) preference).isChecked() ? 1 : 0);
                return true;
          }

            return super.onPreferenceTreeClick(preferenceScreen, preference);
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
           if (preference == mNavigationBarColor) {
                String hex = ColorPickerPreference.convertToARGB(Integer.valueOf(String
                        .valueOf(newValue)));
                preference.setSummary(hex);

                int intHex = ColorPickerPreference.convertToColorInt(hex);
                Settings.System.putInt(getActivity().getContentResolver(),
                        Settings.System.NAVIGATION_BAR_TINT, intHex);
                return true;

            }
            return false;
        }

}
