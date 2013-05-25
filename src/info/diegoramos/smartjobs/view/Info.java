package info.diegoramos.smartjobs.view;

import info.diegoramos.smartjobs.utils.AppInfo;
import info.diegoramos.smartjobs2.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.AbstractAction;

/**
 * 
 * @author Diego Ramos <rdiego26@gmail>
 *
 */
public class Info extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* Remove title bar */
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.info);
		
		configureActionBar();
		settingComponents();
	}
	
	/**
	 * Acessada pelo link do email do autor
	 */
	public void enviarEmail(View vw) {
    	Intent shareIntent = new Intent(Intent.ACTION_SEND);
		shareIntent.setType("text/plain");
		shareIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{this.getString(R.string.emailAuthor)});
		shareIntent.putExtra(Intent.EXTRA_SUBJECT, this.getString(R.string.app_name));
		startActivity(Intent.createChooser(shareIntent, "Send..."));		
	}
	
	/**
	 * Mapeia e seta o valor dos componentes
	 */
	private void settingComponents() {
		TextView txtAppVersion = (TextView) findViewById(R.id.txtAppVersion);
		TextView txtDeveloper = (TextView) findViewById(R.id.txtCodeBy);
		TextView txtLicense = (TextView) findViewById(R.id.txtLicense);
		
		String developer = this.getString(R.string.developer);
		String license = this.getString(R.string.licenseContract);
		
		txtAppVersion.setText(AppInfo.getVersion(this));
		txtDeveloper.setText(developer);
		txtLicense.setText(license);
	}
	
	/**
	 * Configura a ActionBar
	 */
	private void configureActionBar() {
		ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar_info);
		actionBar.setTitle(R.string.app_name);
		
		actionBar.setHomeAction(new MainAction());
		actionBar.addAction(new BackAction());
	}
	
    /* Abstraction Actions for ActionBar -- INICIO */
    private class MainAction extends AbstractAction {
    	 
        public MainAction() {
            super(R.drawable.home);
        }
 
        public void performAction(View view) {
        	Intent intent = new Intent( getApplicationContext(), MainActivity.class );
        	intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        			
        	startActivity(intent);
        	
        }
    }	

    private class BackAction extends AbstractAction {
   	 
        public BackAction() {
            super(R.drawable.arrow_left);
        }
 
        public void performAction(View view) {
        	Intent intent = new Intent( getApplicationContext(), MainActivity.class );
        	intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        			
        	startActivity(intent);
        }
    }    
    
}