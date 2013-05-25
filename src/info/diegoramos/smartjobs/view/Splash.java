package info.diegoramos.smartjobs.view;

<<<<<<< HEAD
import info.diegoramos.smartjobs.utils.AppInfo;
import info.diegoramos.smartjobs2.R;
import android.app.Activity;
import android.content.Intent;
=======
import info.diegoramos.smartjobs.R;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
>>>>>>> ce5e6909afed8fac9b7e1284789a2206337555bd
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.TextView;

public class Splash extends Activity implements Runnable{

	private long splashTime = 4000; /* 4 seconds */
<<<<<<< HEAD
	private Handler h;
=======
>>>>>>> ce5e6909afed8fac9b7e1284789a2206337555bd
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
        /* Remove title bar */
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash);

<<<<<<< HEAD
		//PackageInfo pinfo = null;
		TextView txtVersaoApp = (TextView) findViewById(R.id.version_app);
		
		txtVersaoApp.setText(AppInfo.getVersion(getApplicationContext()));
		
		h = new Handler();
=======
		PackageInfo pinfo = null;
		TextView txtVersaoApp = (TextView) findViewById(R.id.version_app);
		
		try {
			   pinfo = getPackageManager().getPackageInfo(getPackageName(), 0);
			   txtVersaoApp.setText(pinfo.versionName);
			 }
		catch (Exception e) {
			   e.printStackTrace();
			 }		
		
		
		Handler h = new Handler();
>>>>>>> ce5e6909afed8fac9b7e1284789a2206337555bd
		h.postDelayed(this, splashTime);
		}
	
	public void run(){
			startApp();
		}
	
	/*
	 * Caso o usuário deseja pular a Splash
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		startApp();
		
		return true;
	}
	
	private void startApp() {
		startActivity(new Intent(this, MainActivity.class));
		finish();	
	}

<<<<<<< HEAD
}
=======
}
>>>>>>> ce5e6909afed8fac9b7e1284789a2206337555bd
