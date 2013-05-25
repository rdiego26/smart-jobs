package info.diegoramos.smartjobs.view;

<<<<<<< HEAD
=======
import info.diegoramos.smartjobs.R;
>>>>>>> ce5e6909afed8fac9b7e1284789a2206337555bd
import info.diegoramos.smartjobs.model.Client;
import info.diegoramos.smartjobs.persistence.DAOClient;
import info.diegoramos.smartjobs.utils.ToastManager;
import info.diegoramos.smartjobs.utils.Validations;
<<<<<<< HEAD
import info.diegoramos.smartjobs2.R;
=======
>>>>>>> ce5e6909afed8fac9b7e1284789a2206337555bd
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
<<<<<<< HEAD
import android.view.Window;
import android.widget.EditText;

import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.AbstractAction;

=======
import android.widget.EditText;

>>>>>>> ce5e6909afed8fac9b7e1284789a2206337555bd
public class ClientUpdate extends Activity {

	private DAOClient daoC = null;
	private Client c = null;
	private Validations validate = null;
	
	//Components
	private EditText txtClient;
	private EditText txtAddress;
	private EditText txtPhone;
	private EditText txtCelular;
	private EditText txtEmail;
	
	//Messages
	private String msgDuplicate = null;
	private String msgBlank = null;
	private String msgError = null; 
	private String msgSucess = null;	
	private String msgPhoneBlank = null;
	private String msgCelularBlank = null;
	private String msgEmailBlank = null;
	private String msgSendEmail = null;
	private String msgAddressBlank = null; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
<<<<<<< HEAD
		/* Remove title bar */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.client_update);
		
		configureActionBar();
		
=======
		setContentView(R.layout.client_update);
		
>>>>>>> ce5e6909afed8fac9b7e1284789a2206337555bd
		//Components
		txtClient = (EditText) findViewById(R.alt_cliente.txt_nome_cliente);
		txtAddress = (EditText) findViewById(R.alt_cliente.txt_endereco_cliente);
		txtPhone = (EditText) findViewById(R.alt_cliente.txt_telefone_cliente);
		txtCelular = (EditText) findViewById(R.alt_cliente.txt_celular_cliente);
		txtEmail = (EditText) findViewById(R.alt_cliente.txt_email_cliente);
		
		loadContent();
		
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		loadContent();
	}

	private void loadContent() {
		
		//obtain client via Extra
		Bundle extras = getIntent().getExtras();
		c = (Client) extras.getSerializable("_object_client");
		
		if ( c != null) {
			txtClient.setText(c.getName());
			txtAddress.setText(c.getAddress());
			txtPhone.setText(c.getPhone());
			txtCelular.setText(c.getCelular());
			txtEmail.setText(c.getEmail());
		}
		
	}
	
	public void update(View vw) {
		daoC = DAOClient.getInstance(this); // Work with Singleton
		validate = new Validations();
		
		//Settings values
		c.setName(txtClient.getText().toString());
		c.setAddress(txtAddress.getText().toString());
		c.setPhone(txtPhone.getText().toString());
		c.setCelular(txtCelular.getText().toString());
		c.setEmail(txtEmail.getText().toString());
		
		msgSucess = this.getString(R.string.client_sucess);
		msgError = this.getString(R.string.client_error);
		msgBlank = this.getString(R.string.client_name_blank);
		msgDuplicate = this.getString(R.string.client_name_duplicate);		
		
		/* Validate required fields */
		if( validate.isNull("Cliente", txtClient) ) {
			ToastManager.show(getApplicationContext(), msgBlank, 2);
		} else if (!daoC.checkBeforeUpdate(c)) { 
			ToastManager.show(getApplicationContext(), msgDuplicate, 2);
	    }   else if (daoC.update(c) == -1) {
			ToastManager.show(getApplicationContext(), msgError, 2);
		}
		else {
			ToastManager.show(getApplicationContext(), msgSucess, 0);
			finish();
		}		
		
	}
	
	public void contato(View vw) {
		msgPhoneBlank = this.getString(R.string.client_phone_blank);
		msgCelularBlank = this.getString(R.string.client_celular_blank);
		msgEmailBlank = this.getString(R.string.client_email_blank);
		msgAddressBlank = this.getString(R.string.client_address_blank);
		
		Intent intent = null;
		
		switch (vw.getId()) {
		case R.alt_cliente.btn_ligar_telefone:
			if(c.getPhone() == null) {
				ToastManager.show(getApplicationContext(), msgPhoneBlank, 2);
			}
			else {
				intent = new Intent(Intent.ACTION_DIAL);
				intent.setData(Uri.parse("tel:" + c.getPhone()));
				startActivity(intent);
			}
			break;

		case R.alt_cliente.btn_ligar_celular:
			if(c.getCelular() == null) {
				ToastManager.show(getApplicationContext(), msgCelularBlank, 2);
			}
			else {
				intent = new Intent(Intent.ACTION_DIAL);
				intent.setData(Uri.parse("tel:" + c.getCelular()));
				startActivity(intent);
			}			
			break;			
			
		case R.alt_cliente.btn_enviar_email:
			if(c.getEmail() == null) {
				ToastManager.show(getApplicationContext(), msgEmailBlank, 2);
			}
			else {
				msgSendEmail = this.getString(R.string.send_email);
				
				intent = new Intent(Intent.ACTION_SEND);
				intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Assunto");
				intent.setType("html/text");
				intent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{c.getEmail()});
				startActivity(Intent.createChooser(intent, msgSendEmail));
			}			
			break;
		
		case R.alt_cliente.btn_endereco:
			if(c.getAddress() == null){
				ToastManager.show(getApplicationContext(), msgAddressBlank, 2);
			} else {
				intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("geo:0,0?q="+c.getAddress()));
				startActivity(intent);
			}
			break;
			
		default:
			break;
		}
	}
	
<<<<<<< HEAD
	/**
	 * Configura a ActionBar
	 */
	private void configureActionBar() {
		ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar_client_update);
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
=======
	
}
>>>>>>> ce5e6909afed8fac9b7e1284789a2206337555bd
