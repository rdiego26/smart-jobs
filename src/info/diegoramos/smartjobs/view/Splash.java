package info.diegoramos.smartjobs.view;

import info.diegoramos.smartjobs.utils.AppInfo;
import info.diegoramos.smartjobs2.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.TextView;

public class Splash extends Activity implements Runnable{

	private long splashTime = 4000; /* 4 seconds */
	private Handler h;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
        /* Remove title bar */
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash);

		//PackageInfo pinfo = null;
		TextView txtVersaoApp = (TextView) findViewById(R.id.version_app);
		
		txtVersaoApp.setText(AppInfo.getVersion(getApplicationContext()));
		
		h = new Handler();
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

}