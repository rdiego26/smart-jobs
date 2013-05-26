package info.diegoramos.smartjobs.utils;

import info.diegoramos.smartjobs2.R;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/*
 * @see http://www.androidsnippets.com/prompt-engaged-users-to-rate-your-app-in-the-android-market-appirater
 * 
 * */

public class AppRater {
    private static String APP_TITLE = null;
    private static String APP_PNAME = null;
    
    private final static int DAYS_UNTIL_PROMPT = 2;
    private final static int LAUNCHES_UNTIL_PROMPT = 5;
    
    public static void app_launched(Context mContext) {
        //Gets AppName an PackgName
    	final PackageManager pm = mContext.getApplicationContext().getPackageManager();
    	ApplicationInfo ai;
    	try {
    	    ai = pm.getApplicationInfo( mContext.getPackageName(), 0);
    	} catch (final NameNotFoundException e) {
    	    ai = null;
    	}
    	
    	APP_TITLE = pm.getApplicationLabel(ai).toString();
    	APP_PNAME = mContext.getPackageName();
    	
    	
    	SharedPreferences prefs = mContext.getSharedPreferences("apprater", 0);
        if (prefs.getBoolean("dontshowagain", false)) { return ; }
        
        SharedPreferences.Editor editor = prefs.edit();
        
        // Increment launch counter
        long launch_count = prefs.getLong("launch_count", 0) + 1;
        editor.putLong("launch_count", launch_count);

        // Get date of first launch
        Long date_firstLaunch = prefs.getLong("date_firstlaunch", 0);
        if (date_firstLaunch == 0) {
            date_firstLaunch = System.currentTimeMillis();
            editor.putLong("date_firstlaunch", date_firstLaunch);
        }
        
        // Wait at least n days before opening
        if (launch_count >= LAUNCHES_UNTIL_PROMPT) {
            if (System.currentTimeMillis() >= date_firstLaunch + 
                    (DAYS_UNTIL_PROMPT * 24 * 60 * 60 * 1000)) {
                showRateDialog(mContext, editor);
            }
        }
        
        editor.commit();
    }   
    
    public static void showRateDialog(final Context mContext, final SharedPreferences.Editor editor) {
        //Strings rate message
    	String title = mContext.getString(R.string.rate_msg_title);
    	String msg1 = mContext.getString(R.string.rate_msg1);
    	String msg2 = mContext.getString(R.string.rate_msg2);
    	
    	final Dialog dialog = new Dialog(mContext);
        dialog.setTitle(title + " " + APP_TITLE);

        LinearLayout ll = new LinearLayout(mContext);
        ll.setOrientation(LinearLayout.VERTICAL);
        
        TextView tv = new TextView(mContext);
        tv.setText(msg1 + " " + APP_TITLE+ " " + msg2);
        tv.setWidth(240);
        tv.setPadding(4, 0, 4, 10);
        tv.setTextColor(-1);
        ll.addView(tv);
        
        Button btnRate = new Button(mContext);
        btnRate.setText(title +" "+ APP_TITLE);
        btnRate.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + APP_PNAME)));
                dialog.dismiss();
            }
        });        
        ll.addView(btnRate);

        //Button Remind message
        String msgRemind = mContext.getString(R.string.rate_msg_btn_remind);
        
        Button btnRemind = new Button(mContext);
        btnRemind.setText(msgRemind);
        btnRemind.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        ll.addView(btnRemind);

        //Button NO message
        String msgNo = mContext.getString(R.string.rate_msg_btn_no);        
        Button btnNo = new Button(mContext);
        btnNo.setText(msgNo);
        btnNo.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (editor != null) {
                    editor.putBoolean("dontshowagain", true);
                    editor.commit();
                }
                dialog.dismiss();
            }
        });
        ll.addView(btnNo);

        dialog.setContentView(ll);        
        dialog.show();        
    }
    
	
}
