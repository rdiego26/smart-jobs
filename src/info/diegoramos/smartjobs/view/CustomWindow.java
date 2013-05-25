package info.diegoramos.smartjobs.view;

<<<<<<< HEAD
import info.diegoramos.smartjobs2.R;
=======
import info.diegoramos.smartjobs.R;
>>>>>>> ce5e6909afed8fac9b7e1284789a2206337555bd
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomWindow extends Activity{
	protected TextView title;
	protected ImageView icon;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.dashboard);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.maintitlebar);
		
	}
}
