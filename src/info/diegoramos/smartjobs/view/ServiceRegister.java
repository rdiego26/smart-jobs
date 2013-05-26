package info.diegoramos.smartjobs.view;

import info.diegoramos.smartjobs.components.ClienteSpinnerAdapter;
import info.diegoramos.smartjobs.components.TypeOfServiceSpinnerAdapter;
import info.diegoramos.smartjobs.model.Client;
import info.diegoramos.smartjobs.model.Service;
import info.diegoramos.smartjobs.model.TypeOfService;
import info.diegoramos.smartjobs.persistence.DAOClient;
import info.diegoramos.smartjobs.persistence.DAOService;
import info.diegoramos.smartjobs.persistence.DAOTypeOfService;
import info.diegoramos.smartjobs.utils.ToastManager;
import info.diegoramos.smartjobs.utils.Validations;
import info.diegoramos.smartjobs2.R;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.AbstractAction;

public class ServiceRegister extends Activity {

	
	private DAOService daoS;
	private DAOTypeOfService daoT;
	private DAOClient daoC;
	
	private Calendar dateTime = Calendar.getInstance();
	
	//Components
	private Spinner spnClient;
	private Spinner spnTypeOfService;
	private EditText txtNameService;
	private Button btnData;
	private EditText txtPrice;
	private EditText txtDetails;
	
	//Messages
	private String msgDateBlank = null;
	private String msgNameBlank = null;
	private String msgClientBlank = null;
	private String msgTypeOfServiceBlank = null;
	private String msgError = null; 
	private String msgSucess = null;
	private String msgLblDate = null;
	private String msgPromptTypeofService = null;
	private String msgPromptClient = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* Remove title bar */
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.service_register);
		configureActionBar();
		
		//Initializing components
		spnClient = (Spinner) findViewById(R.cad_servico.spn_cliente);
		spnTypeOfService = (Spinner) findViewById(R.cad_servico.spn_tipo_servico);
		txtNameService = (EditText) findViewById(R.cad_servico.txt_nome_servico);
		btnData = (Button) findViewById(R.cad_servico.btn_data);
		txtPrice = (EditText) findViewById(R.cad_servico.txt_preco_servico);
		txtDetails = (EditText) findViewById(R.cad_servico.txt_detalhes_servico);
		
		//Load data Spinner's
		loadClientSpinner();
		loadTypeOfServiceSpinner();
		
	}
	
	private void loadTypeOfServiceSpinner() {
		msgPromptTypeofService = this.getString(R.string.type_of_service_spinner_prompt);
		
		spnTypeOfService.setAdapter(TypeOfServiceSpinnerAdapter.getAdapter(this));	
		spnTypeOfService.setPrompt(msgPromptTypeofService);
	}
	
	private TypeOfService getTypeOFServiceSpinner() {
		TypeOfService tos = daoT.getByName( spnTypeOfService.getSelectedItem().toString().replace("{nomeTypeOfService=", "").replace("}", "") );
		
		return tos;
	}
	
	private void loadClientSpinner() {
		msgPromptClient = this.getString(R.string.client_spinner_prompt);
		
		spnClient.setAdapter(ClienteSpinnerAdapter.getAdapter(this));
		spnClient.setPrompt(msgPromptClient);
	}
	
	private Client getSpinnerClient() {
		Client c = daoC.getByName( spnClient.getSelectedItem().toString().replace("{nomeClient=", "").replace("}", "") );
		
		return c;
	}
	
	public void setServiceDate(View vw){
		Calendar calendar = Calendar.getInstance();
		new DatePickerDialog(this, dateListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
		
	}
	
    DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
			dateTime.set(Calendar.YEAR,year);
			dateTime.set(Calendar.MONTH, monthOfYear);
			dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			
			StringBuilder sB = new StringBuilder();
			
			dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			dateTime.set(Calendar.YEAR, year);
			dateTime.set(Calendar.MONTH, monthOfYear);
			
			monthOfYear += 1;
			
			//TODO Verificar a data pela configuração
			sB.append(dayOfMonth + "/");
			sB.append(monthOfYear + "/");
			sB.append(year);
			
			btnData.setText(sB);
		}
	};
	
	
	public void register(View vw) {
		daoS = DAOService.getInstance(this);
		Validations validate = new Validations();
		
		msgError = this.getString(R.string.service_error);
		msgDateBlank = this.getString(R.string.service_date_blank);
		msgSucess = this.getString(R.string.service_sucess);
		msgNameBlank = this.getString(R.string.service_name_blank);
		msgLblDate = this.getString(R.string.lbl_service_date);
		msgClientBlank = this.getString(R.string.service_client_blank);
		msgTypeOfServiceBlank = this.getString(R.string.service_type_of_service_blank);
		
		if( validate.isNull("Serviço", txtNameService.getText().toString()) ) {
			ToastManager.show(getApplicationContext(), msgNameBlank, 2);
		} else if( validate.isNull("Cliente", spnClient.getSelectedItem().toString().replace("{nomeClient=", "").replace("}", "")) ){
			ToastManager.show(getApplicationContext(), msgClientBlank, 2);
		} else if( validate.isNull("Tipo de Serviço", spnTypeOfService.getSelectedItem().toString().replace("{nomeTypeOfService=", "").replace("}", "")) ){
			ToastManager.show(getApplicationContext(), msgTypeOfServiceBlank, 2);
		}  else if( validate.isNull("Data", btnData) || btnData.getText().toString().equals(msgLblDate) ) {
			ToastManager.show(getApplicationContext(), msgDateBlank, 2);
		}  else {
			// Construct Service
			daoC = DAOClient.getInstance(this);
			daoT = DAOTypeOfService.getInstance(this);
			
			TypeOfService t = getTypeOFServiceSpinner();
			Client c = getSpinnerClient();
			float price = 0;
			
			
			try {
				price = Float.valueOf(txtPrice.getText().toString());
			} catch (Exception e) {
				
			}

			Service s = new Service(c.get_id(), t.get_id(), 
					txtNameService.getText().toString(),
					dateTime.getTimeInMillis(),
					price,
					txtDetails.getText().toString()
					);
			if( daoS.save(s) == -1 ) {
				ToastManager.show(getApplicationContext(), msgError, 2);
			} else {
				ToastManager.show(getApplicationContext(), msgSucess, 0);
				finish();
			}
		}
	}

	/**
	 * Configura a ActionBar
	 */
	private void configureActionBar() {
		ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar_service_register);
		actionBar.setTitle(R.string.service_title);
		
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
        	Intent intent = new Intent( getApplicationContext(), ServiceList.class );
        	intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        			
        	startActivity(intent);
        }
    }
	/* Abstraction Actions for ActionBar -- FIM */
}