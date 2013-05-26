package info.diegoramos.smartjobs.view;

import info.diegoramos.smartjobs.model.TypeOfService;
import info.diegoramos.smartjobs.persistence.DAOTypeOfService;
import info.diegoramos.smartjobs.utils.ToastManager;
import info.diegoramos.smartjobs.utils.Validations;
import info.diegoramos.smartjobs2.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.AbstractAction;

public class TypeOfServiceUpdate extends Activity {

	private DAOTypeOfService DaoTOS;
	private TypeOfService T = null;
	private Validations Validate = null;
	
	//Components
	private EditText edtNome;
	
	//Messages
	String msgDuplicate = null;
	String msgBlank = null;
	String msgError = null; 
	String msgSucess = null;	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		/* Remove title bar */
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.type_of_service_update);
		
		edtNome = (EditText) findViewById(R.alt_tipo_servico.txt_nome_tipo_servico);
		loadContent();
		
		configureActionBar();

	}
	
	@Override
	protected void onStart() {
		super.onStart();
		loadContent();
	}
	
	public void update(View vw) {
		DaoTOS = DAOTypeOfService.getInstance(this); // Work with Singleton
		Validate = new Validations();
		T.setName(edtNome.getText().toString());
		
		msgSucess = this.getString(R.string.type_of_service_sucess);
		msgError = this.getString(R.string.type_of_service_error);
		msgBlank = this.getString(R.string.type_of_service_blank);
		msgDuplicate = this.getString(R.string.type_of_service_duplicate);
		
		if( Validate.isNull("Tipo de Serviço", edtNome) ) {
			
			ToastManager.show(getApplicationContext(), msgBlank, 2);
		} else if (!DaoTOS.checkBeforeUpdate(T)){
			ToastManager.show(getApplicationContext(), msgDuplicate, 2);
		}
		else if (DaoTOS.update(T) == -1) {
			ToastManager.show(getApplicationContext(), msgError, 2);
		}
		else {
			ToastManager.show(getApplicationContext(), msgSucess, 0);
			finish();
		}
		
	}
	
	public void loadContent() {
		DaoTOS = DAOTypeOfService.getInstance(this); // Work with Singleton
		
		//obtain type of service via Extra(param)
		Bundle extras = getIntent().getExtras();
		T = (TypeOfService) extras.getSerializable("_object_type_of_service");
		
		if( T != null) {
			edtNome.setText(T.getName());
			
		}
	}
	
	/**
	 * Configura a ActionBar
	 */
	private void configureActionBar() {
		ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar_type_of_service_update);
		actionBar.setTitle(R.string.type_of_service_title);
		
		
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
        	Intent intent = new Intent( getApplicationContext(), TypeOfServiceList.class );
        	intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        			
        	startActivity(intent);
        }
    }
	/* Abstraction Actions for ActionBar -- FIM */	
}