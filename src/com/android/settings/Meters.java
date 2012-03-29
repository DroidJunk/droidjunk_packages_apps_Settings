package com.android.settings;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.provider.Settings;
import android.preference.ListPreference;
import com.android.settings.R;

    public class Meters extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener {

        private static final String ALT_METERS = "pref_alt_meters";
	private static final String BATTERY_BAR = "battery_bar";
	private static final String BATTERY_BAR_COLOR = "battery_bar_color";

        CheckBoxPreference mBatteryBar;
	ColorPickerPreference mBatteryBarColor;
        ListPreference mMeterOptions;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.custom_meters);

            mMeterOptions = (ListPreference) findPreference(ALT_METERS);
            mMeterOptions.setOnPreferenceChangeListener(this);
            mMeterOptions.setValue(Settings.System.getInt(
                    getActivity().getContentResolver(), Settings.System.BATTERY_ICON,
                    0) + "");

            mBatteryBar = (CheckBoxPreference) findPreference(BATTERY_BAR);
            mBatteryBar.setChecked(Settings.System.getInt(getActivity()
                    .getContentResolver(), Settings.System.STATUSBAR_BATTERY_BAR,
                    0) == 1);

            mBatteryBarColor = (ColorPickerPreference) findPreference(BATTERY_BAR_COLOR);
            mBatteryBarColor.setOnPreferenceChangeListener(this);

          }


       public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
                Preference preference) {
           if (preference == mBatteryBar) {
                Settings.System.putInt(getActivity().getContentResolver(),
                        Settings.System.STATUSBAR_BATTERY_BAR,
                        ((CheckBoxPreference) preference).isChecked() ? 1 : 0);
                return true;
          }

            return super.onPreferenceTreeClick(preferenceScreen, preference);
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            if (preference == mMeterOptions) {
                int val = Integer.parseInt((String) newValue);
                Settings.System.putInt(getActivity().getContentResolver(),
                        Settings.System.BATTERY_ICON, val);
                return true;               
           } else if (preference == mBatteryBarColor) {
                String hex = ColorPickerPreference.convertToARGB(Integer.valueOf(String
                        .valueOf(newValue)));
                preference.setSummary(hex);

                int intHex = ColorPickerPreference.convertToColorInt(hex);
                Settings.System.putInt(getActivity().getContentResolver(),
                        Settings.System.STATUSBAR_BATTERY_BAR_COLOR, intHex);
                return true;

            }
            return false;
        }

}
