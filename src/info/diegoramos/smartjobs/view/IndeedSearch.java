package info.diegoramos.smartjobs.view;

import info.diegoramos.smartjobs.external.IndeedResult;
import info.diegoramos.smartjobs.utils.JsonReader;
import info.diegoramos.smartjobs.utils.ToastManager;
import info.diegoramos.smartjobs2.R;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.AbstractAction;

public class IndeedSearch extends Activity {

	//Componentes
	private EditText txtTerm;
	private EditText txtLocation;
	private ListView lstResults;
	private List<IndeedResult> listaResultados = new ArrayList<IndeedResult>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        
		/* Remove title bar */
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.indeed_search);
		

		// Map Components
		txtTerm = (EditText) findViewById(R.id.txt_termo_filtro_indeed_search);
		txtLocation = (EditText) findViewById(R.id.txt_localizacao_filtro_indeed_search);
		lstResults = (ListView) findViewById(R.id.indeed_search_list);		
		configureActionBar();
	}
	
	
	/**
	 * Configura a ActionBar
	 */
	private void configureActionBar() {
		ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar_indeed_search);
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
	/* Abstraction Actions for ActionBar -- FIM */	
	
    /**
     * Acessado pelo botão Search da tela.
     * @param vw
     */
    public void efetuarBusca(View vw) {
    	StringBuilder url = new StringBuilder();
    	
    	url.append(
    			JsonReader.montaUrlIndeed(txtTerm.getText().toString(),
    					txtLocation.getText().toString())
    				);
    	
    	new IndeedSearchTask().execute(url.toString());
    }
    
    
    /**
     * Tratamento da busca no Indeed
     */

    private class IndeedSearchTask extends AsyncTask<String, Void, String[]> {
    	ProgressDialog dialog;
    	
    	@Override
    	protected void onPreExecute() {
    		// Esvazia lista
    		listaResultados.clear();
    		
    		dialog = new ProgressDialog(IndeedSearch.this);
    		dialog.show();
    		//dialog.show(IndeedSearch.this, "Indeed Search", "Aguarde...");
    	}

		@Override
		protected String[] doInBackground(String... params) {
			String urlRecebida = params[0];
			String[] result = null;
			
			if(TextUtils.isEmpty(urlRecebida)){ 
				return null;
			}
			
			JSONObject jsonObject = JsonReader.ReadJSONURL(urlRecebida);
			
			try {
				JSONArray jobsArray = jsonObject.getJSONArray("results");
				
				for (int i = 0; i < jobsArray.length(); i++) {
					IndeedResult resultado;
					
					JSONObject object = jobsArray.getJSONObject(i);
					
					//Monta um IndeedResult
					resultado = new IndeedResult(object.getString("jobtitle"),
							object.getString("company"), 
							object.getString("date"), 
							object.getString("snippet"), 
							object.getString("url")
							);
					
					listaResultados.add(resultado);
				}
				StringBuilder sb = new StringBuilder();
    			
				result = new String[listaResultados.size()];
				
				
				for(IndeedResult r : listaResultados) {
    				
    				sb.append(r.getSnippet() + "<>");
    			}
				
    			result = sb.toString().split("<>");
				
				
				
			} catch (Exception e) {
				ToastManager.show(IndeedSearch.this, e.toString(), 2);
			}
			
			return result;
		}
    	
    	@Override
    	protected void onPostExecute(String[] result) {
    		super.onPostExecute(result);
    		
    		if(result != null) {
    			ArrayAdapter<String> adaptder = new ArrayAdapter<String>
    				(getBaseContext(), android.R.layout.simple_list_item_1, result);
    			
    			lstResults.setAdapter(adaptder);
    		}
   			dialog.dismiss();
    	}
    	
    }


}
