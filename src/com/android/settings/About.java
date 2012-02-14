package com.android.settings;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;


/**
 * 
 * @author p3droid
 * 
 */
public class About extends Activity {

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		this.setContentView(R.layout.about);
		ChangeLog cl = new ChangeLog(this);
	    cl.getLogDialog().show();
	}
	
	public void Gotoemail ( View v ){
		switch( v.getId() ){
		case R.id.textView4:
			Intent i3 = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=3925479"));
	        startActivity(i3);
			break;
		}

/*		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
		String[] recipients = new String[]{"p3droid@mydroidworld.com", "",};
		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, recipients);
		emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "TranQuilIce Feedback");
		emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "");
		emailIntent.setType("text/plain");
		startActivity(Intent.createChooser(emailIntent, "Send mail...")); */
		finish();

		}

}
