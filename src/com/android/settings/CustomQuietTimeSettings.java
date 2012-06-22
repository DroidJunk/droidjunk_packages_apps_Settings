package com.android.settings;

import android.app.Fragment;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class CustomQuietTimeSettings extends Fragment {

	private final String Junk_QuietTime_Settings = "JUNK_QUIET_TIME_SETTINGS";
	private View mView;
	private boolean qtOn, sunOn, monOn, tueOn, wedOn, thurOn, friOn, satOn;

	private Button mbutQT, mbutAllDays, mbutSun, mbutMon, mbutTue, mbutWed;
	private Button mbutThur, mbutFri, mbutSat;
	
	private ImageView mQTOnOff, mSunOnOff, mMonOnOff, mTueOnOff;
	private ImageView mWedOnOff, mThurOnOff, mFriOnOff, mSatOnOff;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	mView = (View) inflater.inflate(R.layout.custom_quiet_time, container, false);
		return mView;
    }
	
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
        mbutQT = (Button) getActivity().findViewById(R.id.quiet_time);
        mbutAllDays = (Button) getActivity().findViewById(R.id.all_days);
        mbutSun = (Button) getActivity().findViewById(R.id.sunday);
        mbutMon = (Button) getActivity().findViewById(R.id.monday);
        mbutTue = (Button) getActivity().findViewById(R.id.tuesday);
        mbutWed = (Button) getActivity().findViewById(R.id.wednesday);
        mbutThur = (Button) getActivity().findViewById(R.id.thursday);
        mbutFri = (Button) getActivity().findViewById(R.id.friday);
        mbutSat = (Button) getActivity().findViewById(R.id.saturday);
        
        mQTOnOff = (ImageView) getActivity().findViewById(R.id.quiet_time_onoff);
        mSunOnOff = (ImageView) getActivity().findViewById(R.id.quiet_time_sunday_onoff);
        mMonOnOff = (ImageView) getActivity().findViewById(R.id.quiet_time_monday_onoff);
        mTueOnOff = (ImageView) getActivity().findViewById(R.id.quiet_time_tuesday_onoff);
        mWedOnOff = (ImageView) getActivity().findViewById(R.id.quiet_time_wednesday_onoff);
        mThurOnOff = (ImageView) getActivity().findViewById(R.id.quiet_time_thursday_onoff);
        mFriOnOff = (ImageView) getActivity().findViewById(R.id.quiet_time_friday_onoff);
        mSatOnOff = (ImageView) getActivity().findViewById(R.id.quiet_time_saturday_onoff);
        
        checkButtons();
        
        
		mbutQT.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
		        if (qtOn) {
		        	qtOn = false;
		        	mQTOnOff.setBackgroundResource(R.drawable.junk_off);
		        } else {
		        	qtOn = true;
		        	mQTOnOff.setBackgroundResource(R.drawable.junk_on);
		        }
		        updateDb("1");
		     	Intent i = new Intent();
		     	i.setAction(Junk_QuietTime_Settings );
		     	getActivity().sendBroadcast(i);
		     	i = null;   
			}
 		});
		
		mbutAllDays.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
			    Intent i = new Intent();
			    i.setAction("android.settings.CUSTOM_QT_SETTINGS");
			    i.putExtra("junk_qt_day", 1);
		        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        getActivity().getBaseContext().startActivity(i);
		        i = null;
			}
 		});
		
		mbutSun.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
			    Intent i = new Intent();
			    i.setAction("android.settings.CUSTOM_QT_SETTINGS");
			    i.putExtra("junk_qt_day", 2);
		        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        getActivity().getBaseContext().startActivity(i);
		        i = null;
			}
 		});

		mbutMon.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
			    Intent i = new Intent();
			    i.setAction("android.settings.CUSTOM_QT_SETTINGS");
			    i.putExtra("junk_qt_day", 3);
		        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        getActivity().getBaseContext().startActivity(i);
		        i = null;
			}
 		});

		mbutTue.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
			    Intent i = new Intent();
			    i.setAction("android.settings.CUSTOM_QT_SETTINGS");
			    i.putExtra("junk_qt_day", 4);
		        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        getActivity().getBaseContext().startActivity(i);
		        i = null;
			}
 		});		
		
		mbutWed.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
			    Intent i = new Intent();
			    i.setAction("android.settings.CUSTOM_QT_SETTINGS");
			    i.putExtra("junk_qt_day", 5);
		        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        getActivity().getBaseContext().startActivity(i);
		        i = null;
			}
 		});		
		
		mbutThur.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
			    Intent i = new Intent();
			    i.setAction("android.settings.CUSTOM_QT_SETTINGS");
			    i.putExtra("junk_qt_day", 6);
		        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        getActivity().getBaseContext().startActivity(i);
		        i = null;
			}
 		});
		
		mbutFri.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
			    Intent i = new Intent();
			    i.setAction("android.settings.CUSTOM_QT_SETTINGS");
			    i.putExtra("junk_qt_day", 7);
		        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        getActivity().getBaseContext().startActivity(i);
		        i = null;
			}
 		});
		
		mbutSat.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
			    Intent i = new Intent();
			    i.setAction("android.settings.CUSTOM_QT_SETTINGS");
			    i.putExtra("junk_qt_day", 8);
		        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        getActivity().getBaseContext().startActivity(i);
		        i = null;
			}
 		});


		
    }
    
				    		
    private void updateDb(String id){
		 ContentValues values = new ContentValues(1);
         // Write the quiet time values to the database           
         values.put(Settings.QuietTime.QT_ENABLED, qtOn);
         Settings.QuietTime.updateQT(getActivity().getBaseContext().getContentResolver(), values, id);
         
    }				
    
    @Override
    public void onResume() {
        super.onResume();
        checkButtons();
    }
				
    
    private void checkButtons() {
		Cursor cur = Settings.QuietTime.getCursor(getActivity().getBaseContext().getContentResolver(), "1");
		qtOn = cur.getInt(1) == 1;
		cur = Settings.QuietTime.getCursor(getActivity().getBaseContext().getContentResolver(), "2");
		sunOn = cur.getInt(1) == 1;
		cur = Settings.QuietTime.getCursor(getActivity().getBaseContext().getContentResolver(), "3");
		monOn = cur.getInt(1) == 1;
		cur = Settings.QuietTime.getCursor(getActivity().getBaseContext().getContentResolver(), "4");
		tueOn = cur.getInt(1) == 1;
		cur = Settings.QuietTime.getCursor(getActivity().getBaseContext().getContentResolver(), "5");
		wedOn = cur.getInt(1) == 1;
		cur = Settings.QuietTime.getCursor(getActivity().getBaseContext().getContentResolver(), "6");
		thurOn = cur.getInt(1) == 1;
		cur = Settings.QuietTime.getCursor(getActivity().getBaseContext().getContentResolver(), "7");
		friOn = cur.getInt(1) == 1;
		cur = Settings.QuietTime.getCursor(getActivity().getBaseContext().getContentResolver(), "8");
		satOn = cur.getInt(1) == 1;

        
        if (qtOn) {
        	mQTOnOff.setBackgroundResource(R.drawable.junk_on);
        } else {
        	mQTOnOff.setBackgroundResource(R.drawable.junk_off);
        }
        if (sunOn) {
        	mSunOnOff.setBackgroundResource(R.drawable.junk_on);
        } else {
        	mSunOnOff.setBackgroundResource(R.drawable.junk_off);
        }
        if (monOn) {
        	mMonOnOff.setBackgroundResource(R.drawable.junk_on);
        } else {
        	mMonOnOff.setBackgroundResource(R.drawable.junk_off);
        }
        if (tueOn) {
        	mTueOnOff.setBackgroundResource(R.drawable.junk_on);
        } else {
        	mTueOnOff.setBackgroundResource(R.drawable.junk_off);
        }
        if (wedOn) {
        	mWedOnOff.setBackgroundResource(R.drawable.junk_on);
        } else {
        	mWedOnOff.setBackgroundResource(R.drawable.junk_off);
        }
        if (thurOn) {
        	mThurOnOff.setBackgroundResource(R.drawable.junk_on);
        } else {
        	mThurOnOff.setBackgroundResource(R.drawable.junk_off);
        }
        if (friOn) {
        	mFriOnOff.setBackgroundResource(R.drawable.junk_on);
        } else {
        	mFriOnOff.setBackgroundResource(R.drawable.junk_off);
        }
        if (satOn) {
        	mSatOnOff.setBackgroundResource(R.drawable.junk_on);
        } else {
        	mSatOnOff.setBackgroundResource(R.drawable.junk_off);
        }
    }
}
