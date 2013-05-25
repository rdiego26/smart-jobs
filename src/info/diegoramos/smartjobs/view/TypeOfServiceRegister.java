package info.diegoramos.smartjobs.view;

<<<<<<< HEAD
=======
import info.diegoramos.smartjobs.R;
>>>>>>> ce5e6909afed8fac9b7e1284789a2206337555bd
import info.diegoramos.smartjobs.model.TypeOfService;
import info.diegoramos.smartjobs.persistence.DAOTypeOfService;
import info.diegoramos.smartjobs.utils.ToastManager;
import info.diegoramos.smartjobs.utils.Validations;
<<<<<<< HEAD
import info.diegoramos.smartjobs2.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.AbstractAction;

=======
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

>>>>>>> ce5e6909afed8fac9b7e1284789a2206337555bd
public class TypeOfServiceRegister extends Activity{

	private DAOTypeOfService DaoTOS;
	private TypeOfService T;
	private Validations Validate;
	
	//Components
	private EditText edtName;
	
	//Messages
	String msgDuplicate = null;
	String msgBlank = null;
	String msgError = null; 
	String msgSucess = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
<<<<<<< HEAD
		
		/* Remove title bar */
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.type_of_service_register);
		configureActionBar();
=======
		setContentView(R.layout.type_of_service_register);
>>>>>>> ce5e6909afed8fac9b7e1284789a2206337555bd
	}
	
	public void register(View vw) {

		DaoTOS = DAOTypeOfService.getInstance(this); // Work with Singleton
		edtName = (EditText) findViewById(R.cad_tipo_servico.txt_nome_tipo_servico);
		T = new TypeOfService(edtName.getText().toString());
		Validate = new Validations();
		
		msgSucess = this.getString(R.string.type_of_service_sucess);
		msgError = this.getString(R.string.type_of_service_error);
		msgBlank = this.getString(R.string.type_of_service_blank);
		msgDuplicate = this.getString(R.string.type_of_service_duplicate);
		
		if( Validate.isNull("Tipo de Serviço", edtName) ) {
		
			ToastManager.show(getApplicationContext(), msgBlank, 2);
		} else if (DaoTOS.findByName(T.getName())){
			ToastManager.show(getApplicationContext(), msgDuplicate, 2);
		}
		else if (DaoTOS.save(T) == -1) {
			ToastManager.show(getApplicationContext(), msgError, 2);
		}
		else {
			ToastManager.show(getApplicationContext(), msgSucess, 0);
			finish();
		}
		
	}
<<<<<<< HEAD
	
	/**
	 * Configura a ActionBar
	 */
	private void configureActionBar() {
		ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar_type_of_service_register);
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
	
=======
>>>>>>> ce5e6909afed8fac9b7e1284789a2206337555bd
}
