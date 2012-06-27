package com.android.settings;

import android.app.Fragment;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class CustomQuietTimeSettings extends Fragment {

	private final String Junk_QuietTime_Settings = "JUNK_QUIET_TIME_SETTINGS";
	private View mView;
	private int qtMode;
	private final int MODE_OFF = 0;
	private final int MODE_ON = 1;
	private boolean qtOn, sunOn, monOn, tueOn, wedOn, thurOn, friOn, satOn;
	
    private int mStopMin = 0;
    private int mStopHour = 0;
    private int mStartMin = 0;
    private int mStartHour = 0;    
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
				if (qtMode == 0) {
					qtMode = 1;
				} else {
					qtMode = 0;
				}
		        switch (qtMode) {
		    	case MODE_OFF:
		    		mQTOnOff.setBackgroundResource(R.drawable.junk_off);
		    		mbutQT.setText(Html.fromHtml("Quiet Time Mode<br/><small>OFF</small>"));
		    		break;

		    	case MODE_ON:
		    		mQTOnOff.setBackgroundResource(R.drawable.junk_on);
		    		mbutQT.setText(Html.fromHtml("Quiet Time Mode<br/><small>ON</small>"));
		    		break;

		        }
		        
		        updateDb("1");
		     	Intent i = new Intent();
		     	i.setAction(Junk_QuietTime_Settings );
		     	getActivity().sendBroadcast(i);
		     	i = null;   
			}
 		});
		
		mbutUseNotif.setOnClickListener(new Button.OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
				if (qtUseNotif) {
					qtUseNotif = false;
				} else {
					qtUseNotif = true;
				}
		        if (qtUseNotif) {
		    		mQTUseNotif.setBackgroundResource(R.drawable.junk_on);
		    		mbutUseNotif.setText(Html.fromHtml("Show Notifications<br/><small>ON</small>"));
		        } else {
		    		mQTUseNotif.setBackgroundResource(R.drawable.junk_off);
		    		mbutUseNotif.setText(Html.fromHtml("Show Notifications<br/><small>OFF</small>"));
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
		 ContentValues values = new ContentValues(2);
         // Write the quiet time values to the database           
         values.put(Settings.QuietTime.QT_MODE, qtMode);
         values.put(Settings.QuietTime.QT_EXTRA_1, qtUseNotif);
         Settings.QuietTime.updateQT(getActivity().getBaseContext().getContentResolver(), values, id);
         
    }				
    
    @Override
    public void onResume() {
        super.onResume();
        checkButtons();
    }
				
    
    private void checkButtons() {
    	
		Cursor cur = Settings.QuietTime.getCursor(getActivity().getBaseContext().getContentResolver(), "1");
		qtMode = cur.getInt(9);
		qtOn = cur.getInt(1) == 1;
		qtUseNotif = cur.getInt(11) == 1;
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
		cur.close();

        switch (qtMode) {
    	case MODE_OFF:
    		mQTOnOff.setBackgroundResource(R.drawable.junk_off);
    		mbutQT.setText(Html.fromHtml("Quiet Time Mode<br/><small>OFF</small>"));
    		break;

    	case MODE_ON:
    		mQTOnOff.setBackgroundResource(R.drawable.junk_on);
    		mbutQT.setText(Html.fromHtml("Quiet Time Mode<br/><small>ON</small>"));
    		break;
        }

        if (qtUseNotif) {
        	Log.e("**********************","True");
        	mQTUseNotif.setBackgroundResource(R.drawable.junk_on);
        	mbutUseNotif.setText(Html.fromHtml("Show Notifications<br/><small>ON</small>"));
        } else {
        	Log.e("**********************","False");
        	mQTUseNotif.setBackgroundResource(R.drawable.junk_off);
        	mbutUseNotif.setText(Html.fromHtml("Show Notifications<br/><small>OFF</small>"));
        }        
        
        if (qtOn) {
        	mQTDailyOnOff.setBackgroundResource(R.drawable.junk_on);
        	mbutAllDays.setText(Html.fromHtml("Daily<br/><small>"+ getSettingString("1") +"</small>"));
        } else {
        	mQTDailyOnOff.setBackgroundResource(R.drawable.junk_off);
        	mbutAllDays.setText(Html.fromHtml("Daily<br/><small>"+ getSettingString("1") +"</small>"));
        }
        
        if (sunOn) {
        	mSunOnOff.setBackgroundResource(R.drawable.junk_on);
        	mbutSun.setText(Html.fromHtml("Sunday<br/><small>"+ getSettingString("2") +"</small>"));
        } else {
        	mSunOnOff.setBackgroundResource(R.drawable.junk_off);
        	mbutSun.setText(Html.fromHtml("Sunday<br/><small>"+ getSettingString("2") +"</small>"));
        }
        if (monOn) {
        	mMonOnOff.setBackgroundResource(R.drawable.junk_on);
        	mbutMon.setText(Html.fromHtml("Monday<br/><small>"+ getSettingString("3") +"</small>"));
        } else {
        	mMonOnOff.setBackgroundResource(R.drawable.junk_off);
        	mbutMon.setText(Html.fromHtml("Monday<br/><small>"+ getSettingString("3") +"</small>"));
        }
        if (tueOn) {
        	mTueOnOff.setBackgroundResource(R.drawable.junk_on);
        	mbutTue.setText(Html.fromHtml("Tuesday<br/><small>"+ getSettingString("4") +"</small>"));
        } else {
        	mTueOnOff.setBackgroundResource(R.drawable.junk_off);
        	mbutTue.setText(Html.fromHtml("Tuesday<br/><small>"+ getSettingString("4") +"</small>"));
        }
        if (wedOn) {
        	mWedOnOff.setBackgroundResource(R.drawable.junk_on);
        	mbutWed.setText(Html.fromHtml("Wednesday<br/><small>"+ getSettingString("5") +"</small>"));
        } else {
        	mWedOnOff.setBackgroundResource(R.drawable.junk_off);
        	mbutWed.setText(Html.fromHtml("Wednesday<br/><small>"+ getSettingString("5") +"</small>"));
        }
        if (thurOn) {
        	mThurOnOff.setBackgroundResource(R.drawable.junk_on);
        	mbutThur.setText(Html.fromHtml("Thursday<br/><small>"+ getSettingString("6") +"</small>"));
        } else {
        	mThurOnOff.setBackgroundResource(R.drawable.junk_off);
        	mbutThur.setText(Html.fromHtml("Thursday<br/><small>"+ getSettingString("6") +"</small>"));
        }
        if (friOn) {
        	mFriOnOff.setBackgroundResource(R.drawable.junk_on);
        	mbutFri.setText(Html.fromHtml("Friday<br/><small>"+ getSettingString("7") +"</small>"));
        } else {
        	mFriOnOff.setBackgroundResource(R.drawable.junk_off);
        	mbutFri.setText(Html.fromHtml("Friday<br/><small>"+ getSettingString("7") +"</small>"));
        }
        if (satOn) {
        	mSatOnOff.setBackgroundResource(R.drawable.junk_on);
        	mbutSat.setText(Html.fromHtml("Saturday<br/><small>"+ getSettingString("8") +"</small>"));
        } else {
        	mSatOnOff.setBackgroundResource(R.drawable.junk_off);
        	mbutSat.setText(Html.fromHtml("Saturday<br/><small>"+ getSettingString("8") +"</small>"));
        }
    }
    
    
    private String getSettingString(String id) {
    	getQuietTimeDaySettings(id);
        String minPad = "";
        String notifStop = "";
        String notifStart = "";
        String turnOffLed = "";
        String turnOffSound = "";
        String turnOffVibrate = "";
        
        
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
    
    
    // Get the settings    
    private void getQuietTimeDaySettings(String id){
    	Cursor cur = Settings.QuietTime.getCursor(getActivity().getContentResolver(), id);
       	mStartHour = cur.getInt(2);
      	mStartMin = cur.getInt(3);
       	mStopHour = cur.getInt(4);
       	mStopMin = cur.getInt(5);
       	mTurnOffLed = cur.getInt(6) == 1;
       	mTurnOffSound = cur.getInt(7) == 1;
       	mTurnOffVibrate = cur.getInt(8) == 1;
        cur.close();
    }
    
}
