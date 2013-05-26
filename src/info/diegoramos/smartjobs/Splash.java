package info.diegoramos.smartjobs;

import info.diegoramos.smartjobs2.R;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.widget.TextView;

public class Splash extends Activity implements Runnable{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

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
		h.postDelayed(this, 3000); //milis
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
