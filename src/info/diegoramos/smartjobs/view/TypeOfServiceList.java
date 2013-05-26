package info.diegoramos.smartjobs.view;

import info.diegoramos.smartjobs.model.TypeOfService;
import info.diegoramos.smartjobs.persistence.DAOTypeOfService;
import info.diegoramos.smartjobs.utils.ToastManager;
import info.diegoramos.smartjobs2.R;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.AbstractAction;

public class TypeOfServiceList extends Activity{

	private DAOTypeOfService DAOT;
	private List<TypeOfService> list;
	private String display[];
	private ListView listV;
	private int posicao;
	
	//Messages
	String msgError = null;
	String msgSucess = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)  {
		super.onCreate(savedInstanceState);
		
		/* Remove title bar */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.type_of_service_list);
		listV = (ListView) findViewById(R.id.type_of_service_list_list);
		//Simple tap in ListView item
		listV.setOnItemClickListener(new AdapterView.OnItemClickListener()  {

			@Override
			public void onItemClick(AdapterView<?> parent, View wv, int position,
					long id) {
				TypeOfService ts = (TypeOfService) list.get(position);
    			Intent intent = new Intent(getApplicationContext(), TypeOfServiceUpdate.class);
    			Bundle param = new Bundle();
    			
    			param.putSerializable("_object_type_of_service", ts);
    			intent.putExtras(param);
    			startActivity(intent);
			}
		});
		
		
		registerForContextMenu(listV);
		configureActionBar();
		DAOT = DAOTypeOfService.getInstance(this);
		loadList();
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		loadList();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		loadList();
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		loadList();
	}
    
    //ContextMenu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View vw, ContextMenuInfo menuInfo) {
    	super.onCreateContextMenu(menu, vw, menuInfo);
    	getMenuInflater().inflate(R.menu.context_menu, menu);
    	
    	/* Position selected on ContextMenu */
    	AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
    	posicao = info.position;
    	
    }
	
    @Override
    public boolean onContextItemSelected(MenuItem item) {
    	
    	switch (item.getItemId()) {
    	
    	case R.id.context_menu_register:
    			startActivity(new Intent(this, TypeOfServiceRegister.class));
    		break;
    	
    	case R.id.context_menu_delete:
    			TypeOfService ts = (TypeOfService) list.get(posicao);
    			msgError = this.getString(R.string.type_of_service_error);
    			msgSucess = this.getString(R.string.type_of_service_sucess);
    			
    			if ( DAOT.delete(ts.get_id(), getApplicationContext()) == -1 ) {
    				ToastManager.show(getApplicationContext(), msgError, 2);
    			}
    			else {
    				ToastManager.show(getApplicationContext(), msgSucess, 0);
    				loadList();
    			}
    			
    			break;
    	case R.id.context_menu_update:
    			TypeOfService tos = (TypeOfService) list.get(posicao);	
    			Intent intent = new Intent(getApplicationContext(), TypeOfServiceUpdate.class);
    			Bundle param = new Bundle();
    			
    			param.putSerializable("_object_type_of_service", tos);
    			intent.putExtras(param);
    			startActivity(intent);
    			break;
		
    	default:
				break;
		}
    	
    	return true;
    }
    
    /**
     * Responsável por porpular o ListView
     */
	public void loadList() {
		ListView lstVw = (ListView)findViewById(R.id.type_of_service_list_list);
		TextView txtNoRecords = (TextView) findViewById(R.id.type_of_service_empty_list);
		list = DAOT.getAll();
		
		
		StringBuilder sb = new StringBuilder();
		
		if(list.size() > 0) {
			txtNoRecords.setVisibility(View.INVISIBLE);
			
			for(TypeOfService t : list)
			{
				sb.append(t.getName()  + "<>"); //TAG "<>", to split
			}
			
			display = sb.toString().split("<>");
			ArrayAdapter<String> addTypeOfService = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, display);
			lstVw.setAdapter(addTypeOfService);
	} else {
		
		display = "".split("");
		ArrayAdapter<String> addTypeOfService = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, display);
		lstVw.setAdapter(addTypeOfService);		
		
		txtNoRecords.setVisibility(View.VISIBLE);
	}
	
	}
 
	/**
	 * Configura a ActionBar
	 */
	private void configureActionBar() {
		ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar_type_of_service_list);
		actionBar.setTitle(R.string.type_of_service_title);
		
		
		actionBar.setHomeAction(new MainAction());
		actionBar.addAction(new RegisterAction());
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

    private class RegisterAction extends AbstractAction {
     	 
        public RegisterAction() {
            super(R.drawable.add_actionbar);
        }
 
        public void performAction(View view) {
        	Intent intent = new Intent( getApplicationContext(), TypeOfServiceRegister.class );
        			
        	startActivity(intent);
        }
    }    
	/* Abstraction Actions for ActionBar -- FIM */
	
 }