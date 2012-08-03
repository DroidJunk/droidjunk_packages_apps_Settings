package com.android.settings;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class CustomQuietTimeSettings extends Fragment {
	private final String Junk_QuietTime_Settings_Activity = "android.settings.CUSTOM_QT_SETTINGS";
	private final String Junk_QuietTime_Settings = "JUNK_QUIET_TIME_SETTINGS";
	private final String QUIET_TIME = "quiet_time_on";
	private final String QUIET_TIME_NOTIF = "quiet_time_notif_on";
	private final String START_HOUR = "qt_start_hour";
	private final String START_MIN = "qt_start_min";
	private final String STOP_HOUR = "qt_stop_hour";
	private final String STOP_MIN = "qt_stop_min";
	private final String NOTIF_LED_ON = "qt_led_on";
	private final String NOTIF_SOUND_ON = "qt_sound_on";
	private final String NOTIF_VIBRATE_ON = "qt_vibrate_on";
	private final String QT_DAILY = "_daily";
	private final String QT_SUN = "_sun";
	private final String QT_MON = "_mon";
	private final String QT_TUE = "_tue";
	private final String QT_WED = "_wed";
	private final String QT_THUR = "_thur";
	private final String QT_FRI = "_fri";
	private final String QT_SAT = "_sat";
	
	private View mView;
	
	private SharedPreferences sp;
	
	private boolean qtOn, sunOn, monOn, tueOn, wedOn, thurOn, friOn, satOn;
	
    private int mStopMin = 0;
    private int mStopHour = 0;
    private int mStartMin = 0;
    private int mStartHour = 0;    
	private boolean qtMode;
    private boolean mTurnOffLed = false;
    private boolean mTurnOffSound = false;
    private boolean mTurnOffVibrate = false;
    private boolean qtUseNotif = false;
	

	private Button mbutQT, mbutUseNotif, mbutAllDays, mbutSun, mbutMon, mbutTue;
	private Button mbutWed, mbutThur, mbutFri, mbutSat;
	
	private ImageView mQTOnOff, mQTUseNotif, mQTDailyOnOff, mSunOnOff, mMonOnOff, mTueOnOff;
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
        sp = getActivity().getBaseContext().getSharedPreferences("Junk_Settings", Context.MODE_WORLD_READABLE);
    
        mbutQT = (Button) getActivity().findViewById(R.id.quiet_time);
        mbutUseNotif = (Button) getActivity().findViewById(R.id.quiet_time_use_notif);
        mbutAllDays = (Button) getActivity().findViewById(R.id.all_days);
        mbutSun = (Button) getActivity().findViewById(R.id.sunday);
        mbutMon = (Button) getActivity().findViewById(R.id.monday);
        mbutTue = (Button) getActivity().findViewById(R.id.tuesday);
        mbutWed = (Button) getActivity().findViewById(R.id.wednesday);
        mbutThur = (Button) getActivity().findViewById(R.id.thursday);
        mbutFri = (Button) getActivity().findViewById(R.id.friday);
        mbutSat = (Button) getActivity().findViewById(R.id.saturday);
        
        mQTOnOff = (ImageView) getActivity().findViewById(R.id.quiet_time_onoff);
        mQTUseNotif = (ImageView) getActivity().findViewById(R.id.quiet_time_notif_onoff);
        mQTDailyOnOff = (ImageView) getActivity().findViewById(R.id.quiet_time_daily_onoff);
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
				if (qtMode == false) {
					qtMode = true;
					mQTOnOff.setBackgroundResource(R.drawable.junk_on);
		    		mbutQT.setText(Html.fromHtml("Quiet Time Mode<br/><small>ON</small>"));
		           	SharedPreferences.Editor editor = sp.edit();
		           	editor.putBoolean(QUIET_TIME, qtMode);
		            editor.commit();
				} else {
					qtMode = false;
					mQTOnOff.setBackgroundResource(R.drawable.junk_off);
		    		mbutQT.setText(Html.fromHtml("Quiet Time Mode<br/><small>OFF</small>"));
		           	SharedPreferences.Editor editor = sp.edit();
		           	editor.putBoolean(QUIET_TIME, qtMode);
		            editor.commit();
				}
		     	Intent i = new Intent();
		     	i.setAction(Junk_QuietTime_Settings);
		     	i.putExtra(QUIET_TIME, qtMode);
		     	getActivity().sendBroadcast(i);
		     	i = null;   
			}
 		});
		
		mbutUseNotif.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
				if (qtUseNotif) {
					qtUseNotif = false;
					mQTUseNotif.setBackgroundResource(R.drawable.junk_off);
		    		mbutUseNotif.setText(Html.fromHtml("Show Notifications<br/><small>OFF</small>"));
		           	SharedPreferences.Editor editor = sp.edit();
		           	editor.putBoolean(QUIET_TIME_NOTIF, qtUseNotif);
		            editor.commit();
				} else {
					qtUseNotif = true;
					mQTUseNotif.setBackgroundResource(R.drawable.junk_on);
		    		mbutUseNotif.setText(Html.fromHtml("Show Notifications<br/><small>ON</small>"));
		           	SharedPreferences.Editor editor = sp.edit();
		           	editor.putBoolean(QUIET_TIME_NOTIF, qtUseNotif);
		            editor.commit();
				}
		     	Intent i = new Intent();
		     	i.setAction(Junk_QuietTime_Settings);
		     	i.putExtra(QUIET_TIME_NOTIF, qtUseNotif);
		     	getActivity().sendBroadcast(i);
		     	i = null;   
			}
 		});		
		
		mbutAllDays.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
			    Intent i = new Intent();
			    i.setAction(Junk_QuietTime_Settings_Activity);
			    i.putExtra("junk_qt_day", 0);
		        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        getActivity().getBaseContext().startActivity(i);
		        i = null;
			}
 		});
		
		mbutSun.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
			    Intent i = new Intent();
			    i.setAction(Junk_QuietTime_Settings_Activity);
			    i.putExtra("junk_qt_day", 1);
		        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        getActivity().getBaseContext().startActivity(i);
		        i = null;
			}
 		});

		mbutMon.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
			    Intent i = new Intent();
			    i.setAction(Junk_QuietTime_Settings_Activity);
			    i.putExtra("junk_qt_day", 2);
		        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        getActivity().getBaseContext().startActivity(i);
		        i = null;
			}
 		});

		mbutTue.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
			    Intent i = new Intent();
			    i.setAction(Junk_QuietTime_Settings_Activity);
			    i.putExtra("junk_qt_day", 3);
		        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        getActivity().getBaseContext().startActivity(i);
		        i = null;
			}
 		});		
		
		mbutWed.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
			    Intent i = new Intent();
			    i.setAction(Junk_QuietTime_Settings_Activity);
			    i.putExtra("junk_qt_day", 4);
		        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        getActivity().getBaseContext().startActivity(i);
		        i = null;
			}
 		});		
		
		mbutThur.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
			    Intent i = new Intent();
			    i.setAction(Junk_QuietTime_Settings_Activity);
			    i.putExtra("junk_qt_day", 5);
		        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        getActivity().getBaseContext().startActivity(i);
		        i = null;
			}
 		});
		
		mbutFri.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
			    Intent i = new Intent();
			    i.setAction(Junk_QuietTime_Settings_Activity);
			    i.putExtra("junk_qt_day", 6);
		        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        getActivity().getBaseContext().startActivity(i);
		        i = null;
			}
 		});
		
		mbutSat.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
			    Intent i = new Intent();
			    i.setAction(Junk_QuietTime_Settings_Activity);
			    i.putExtra("junk_qt_day", 7);
		        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        getActivity().getBaseContext().startActivity(i);
		        i = null;
			}
 		});


		
    }
    
    
    @Override
    public void onResume() {
        super.onResume();
        checkButtons();
    }
				
    
    private void checkButtons() {
    	qtMode = sp.getBoolean(QUIET_TIME, qtMode);
    	qtUseNotif = sp.getBoolean(QUIET_TIME_NOTIF, qtUseNotif);
    	qtOn = sp.getBoolean(QUIET_TIME + QT_DAILY, qtOn);
    	sunOn = sp.getBoolean(QUIET_TIME + QT_SUN, sunOn);
    	monOn = sp.getBoolean(QUIET_TIME + QT_MON, monOn);
    	tueOn = sp.getBoolean(QUIET_TIME + QT_TUE, tueOn);
    	wedOn = sp.getBoolean(QUIET_TIME + QT_WED, wedOn);
    	thurOn = sp.getBoolean(QUIET_TIME + QT_THUR, thurOn);
    	friOn = sp.getBoolean(QUIET_TIME + QT_FRI, friOn);
    	satOn = sp.getBoolean(QUIET_TIME + QT_SAT, satOn);
    	

        if (!qtMode) {
    		mQTOnOff.setBackgroundResource(R.drawable.junk_off);
    		mbutQT.setText(Html.fromHtml("Quiet Time Mode<br/><small>OFF</small>"));
        } else {	
    		mQTOnOff.setBackgroundResource(R.drawable.junk_on);
    		mbutQT.setText(Html.fromHtml("Quiet Time Mode<br/><small>ON</small>"));
        }
        if (qtUseNotif) {
        	mQTUseNotif.setBackgroundResource(R.drawable.junk_on);
        	mbutUseNotif.setText(Html.fromHtml("Show Notifications<br/><small>ON</small>"));
        } else {
        	mQTUseNotif.setBackgroundResource(R.drawable.junk_off);
        	mbutUseNotif.setText(Html.fromHtml("Show Notifications<br/><small>OFF</small>"));
        }        
        if (qtOn) {
        	mQTDailyOnOff.setBackgroundResource(R.drawable.junk_on);
        	mbutAllDays.setText(Html.fromHtml("Daily<br/><small>"+ getSettingString(QT_DAILY) +"</small>"));
        } else {
        	mQTDailyOnOff.setBackgroundResource(R.drawable.junk_off);
        	mbutAllDays.setText(Html.fromHtml("Daily<br/><small>"+ getSettingString(QT_DAILY) +"</small>"));
        }
        if (sunOn) {
        	mSunOnOff.setBackgroundResource(R.drawable.junk_on);
        	mbutSun.setText(Html.fromHtml("Sunday<br/><small>"+ getSettingString(QT_SUN) +"</small>"));
        } else {
        	mSunOnOff.setBackgroundResource(R.drawable.junk_off);
        	mbutSun.setText(Html.fromHtml("Sunday<br/><small>"+ getSettingString(QT_SUN) +"</small>"));
        }
        if (monOn) {
        	mMonOnOff.setBackgroundResource(R.drawable.junk_on);
        	mbutMon.setText(Html.fromHtml("Monday<br/><small>"+ getSettingString(QT_MON) +"</small>"));
        } else {
        	mMonOnOff.setBackgroundResource(R.drawable.junk_off);
        	mbutMon.setText(Html.fromHtml("Monday<br/><small>"+ getSettingString(QT_MON) +"</small>"));
        }
        if (tueOn) {
        	mTueOnOff.setBackgroundResource(R.drawable.junk_on);
        	mbutTue.setText(Html.fromHtml("Tuesday<br/><small>"+ getSettingString(QT_TUE) +"</small>"));
        } else {
        	mTueOnOff.setBackgroundResource(R.drawable.junk_off);
        	mbutTue.setText(Html.fromHtml("Tuesday<br/><small>"+ getSettingString(QT_TUE) +"</small>"));
        }
        if (wedOn) {
        	mWedOnOff.setBackgroundResource(R.drawable.junk_on);
        	mbutWed.setText(Html.fromHtml("Wednesday<br/><small>"+ getSettingString(QT_WED) +"</small>"));
        } else {
        	mWedOnOff.setBackgroundResource(R.drawable.junk_off);
        	mbutWed.setText(Html.fromHtml("Wednesday<br/><small>"+ getSettingString(QT_WED) +"</small>"));
        }
        if (thurOn) {
        	mThurOnOff.setBackgroundResource(R.drawable.junk_on);
        	mbutThur.setText(Html.fromHtml("Thursday<br/><small>"+ getSettingString(QT_THUR) +"</small>"));
        } else {
        	mThurOnOff.setBackgroundResource(R.drawable.junk_off);
        	mbutThur.setText(Html.fromHtml("Thursday<br/><small>"+ getSettingString(QT_THUR) +"</small>"));
        }
        if (friOn) {
        	mFriOnOff.setBackgroundResource(R.drawable.junk_on);
        	mbutFri.setText(Html.fromHtml("Friday<br/><small>"+ getSettingString(QT_FRI) +"</small>"));
        } else {
        	mFriOnOff.setBackgroundResource(R.drawable.junk_off);
        	mbutFri.setText(Html.fromHtml("Friday<br/><small>"+ getSettingString(QT_FRI) +"</small>"));
        }
        if (satOn) {
        	mSatOnOff.setBackgroundResource(R.drawable.junk_on);
        	mbutSat.setText(Html.fromHtml("Saturday<br/><small>"+ getSettingString(QT_SAT) +"</small>"));
        } else {
        	mSatOnOff.setBackgroundResource(R.drawable.junk_off);
        	mbutSat.setText(Html.fromHtml("Saturday<br/><small>"+ getSettingString(QT_SAT) +"</small>"));
        }
    }
    
    
    private String getSettingString(String id) {
        String minPad = "";
        String notifStop = "";
        String notifStart = "";
        String turnOffLed = "";
        String turnOffSound = "";
        String turnOffVibrate = "";
        
        mTurnOffLed = sp.getBoolean(NOTIF_LED_ON + id, false);
        mTurnOffSound = sp.getBoolean(NOTIF_SOUND_ON + id, false);
        mTurnOffVibrate = sp.getBoolean(NOTIF_VIBRATE_ON + id, false);
		mStartHour = sp.getInt(START_HOUR + id, 21);
		mStartMin = sp.getInt(START_MIN + id, 0);
		mStopHour = sp.getInt(STOP_HOUR + id, 7);
		mStopMin = sp.getInt(STOP_MIN + id, 0);
        
        
      	if (mStopMin < 10) minPad = "0";
        if (mStopHour > 12) {
           	notifStop = String.valueOf(mStopHour - 12) + ":" + minPad + String.valueOf(mStopMin) + "pm";;
        } else {
           	notifStop = String.valueOf(mStopHour) + ":" + minPad + String.valueOf(mStopMin) + "am";;
        }
        minPad = "";
      	if (mStartMin < 10) minPad = "0";
        if (mStartHour > 12) {
           	notifStart = String.valueOf(mStartHour - 12) + ":" + minPad + String.valueOf(mStartMin) + "pm";
        } else {
           	notifStart = String.valueOf(mStartHour) + ":" + minPad + String.valueOf(mStartMin) + "am";;
        }
        
        if (mTurnOffLed) {
        	turnOffLed = "<br/>Led:Off  -  ";
        } else {
        	turnOffLed = "<br/>Led:On  -  ";
        }

        if (mTurnOffSound) {
        	turnOffSound = "Sound:Off  -  ";
        } else {
        	turnOffSound = "Sound:On  -  ";
        }
        if (mTurnOffVibrate) {
        	turnOffVibrate = "Vibrate:Off";
        } else {
        	turnOffVibrate = "Vibrate:On";
        }

        
    	return notifStart + " - " + notifStop +  turnOffLed + turnOffSound + turnOffVibrate;
    }
    
    

    
}
