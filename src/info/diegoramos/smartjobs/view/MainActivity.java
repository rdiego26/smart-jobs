package info.diegoramos.smartjobs.view;

import info.diegoramos.smartjobs.utils.AppRater;
import info.diegoramos.smartjobs.utils.ToastManager;
import info.diegoramos.smartjobs2.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.AbstractAction;

public class MainActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        /* Remove title bar */
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dashboard);
        
        /* Apply ActionBar */
        configureActionbar();
        
        AppRater.app_launched(this);
        
        startActivity(new Intent(this, IndeedSearch.class));
     
    }

    public void trataOpcao(View vw) {
    	
    	switch( vw.getId() ) {
    		case R.id.customers:
    			startActivity(new Intent(this, ClientList.class));
    			break;
    		
    		case R.id.services:
    			startActivity(new Intent(this, ServiceList.class));
    			break;

    		case R.id.type_of_service:
    			startActivity(new Intent(this, TypeOfServiceList.class));
    			break;
    		
    		case R.id.config:
    			String title = this.getString(R.string.under_constructior_msg);
    			
    			ToastManager.show(this, title, 0);
    			
    			break;
    	}
    }

    private void configureActionbar() {
    	ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar_mainactivity);
    	actionBar.setTitle(R.string.app_name);
    	
    	actionBar.setHomeAction(new MainAction());
    	actionBar.addAction(new InfoAction());
    	actionBar.addAction(new ShareAction());
    }
    
    /* Abstraction Actions for ActionBar -- INICIO */
    private class MainAction extends AbstractAction {
    	 
        public MainAction() {
            super(R.drawable.home);
        }
 
        public void performAction(View view) {
        	/**/
        }
    }
    
    private class InfoAction extends AbstractAction {
    	 
        public InfoAction() {
            super(R.drawable.info);
        }
 
        @Override
        public void performAction(View view) {
        	startActivity(new Intent(getApplicationContext(), Info.class));
        	
        }
    }

    private class ShareAction extends AbstractAction {
   	 
        public ShareAction() {
            super(R.drawable.share);
        }
 
        @Override
        public void performAction(View view) {
        	Intent shareIntent = new Intent(Intent.ACTION_SEND);
			shareIntent.setType("text/plain");
			shareIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=info.diegoramos.smartjobs2");
			startActivity(Intent.createChooser(shareIntent, "Share..."));
        }
    }    
    
    /* Abstraction Actions for ActionBar -- FIM */
}         