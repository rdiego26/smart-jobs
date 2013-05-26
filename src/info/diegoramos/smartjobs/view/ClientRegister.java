package info.diegoramos.smartjobs.view;

import info.diegoramos.smartjobs.model.Client;
import info.diegoramos.smartjobs.persistence.DAOClient;
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

public class ClientRegister extends Activity {

	private DAOClient daoClient;
	private Client c;
	private Validations validate;
	
	//Components
	private EditText edtName;
	private EditText edtAddress;
	private EditText edtPhone;
	private EditText edtCelular;
	private EditText edtEmail;
	
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

		setContentView(R.layout.client_register);
		configureActionBar();
	}	
	
	public void register(View vw) {

		daoClient = DAOClient.getInstance(this); // Work with Singleton
		
		edtName = (EditText) findViewById(R.cad_cliente.txt_nome_cliente);
		edtAddress = (EditText) findViewById(R.cad_cliente.txt_endereco_cliente);
		edtPhone = (EditText) findViewById(R.cad_cliente.txt_telefone_cliente);
		edtCelular = (EditText) findViewById(R.cad_cliente.txt_celular_cliente);
		edtEmail = (EditText) findViewById(R.cad_cliente.txt_email_cliente);
		
		c = new Client(edtName.getText().toString(),
					edtAddress.getText().toString(),
					edtPhone.getText().toString(),
					edtCelular.getText().toString(),
					edtEmail.getText().toString()
			);
		validate = new Validations();
		
		msgSucess = this.getString(R.string.client_sucess);
		msgError = this.getString(R.string.client_error);
		msgBlank = this.getString(R.string.client_name_blank);
		msgDuplicate = this.getString(R.string.client_name_duplicate);
		
		/* Validate required fields */
		if( validate.isNull("Cliente", edtName) ) {
			ToastManager.show(getApplicationContext(), msgBlank, 2);
		} else if (daoClient.findByName(c.getName())){
			ToastManager.show(getApplicationContext(), msgDuplicate, 2);
		}
		else if (daoClient.save(c) == -1) {
			ToastManager.show(getApplicationContext(), msgError, 2);
		} else {
			ToastManager.show(getApplicationContext(), msgSucess, 0);
			finish();
		}
	}
	
	/**
	 * Configura a ActionBar
	 */
	private void configureActionBar() {
		ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar_client_register);
		actionBar.setTitle(R.string.client_title);
		
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
        	Intent intent = new Intent( getApplicationContext(), ClientList.class );
        	intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        			
        	startActivity(intent);
        }
    }
	/* Abstraction Actions for ActionBar -- FIM */		
	
}