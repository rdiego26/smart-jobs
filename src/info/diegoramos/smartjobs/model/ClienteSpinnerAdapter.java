package info.diegoramos.smartjobs.model;

import info.diegoramos.smartjobs2.R;
import info.diegoramos.smartjobs.persistence.DAOClient;
import info.diegoramos.smartjobs.persistence.DAOTypeOfService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.widget.SimpleAdapter;

public class ClienteSpinnerAdapter {

	
	public static final SimpleAdapter getAdapter(Context ctx) {
		DAOClient daoC = DAOClient.getInstance(ctx);
		List<Client> list = daoC.getAll();
		
		ArrayList<HashMap<String, String>> alist = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map = new HashMap<String, String>();
        
        for(int i=0; i < list.size(); i++){
 
            map = new HashMap<String,String>();
            map.put("nomeClient", list.get(i).getName());
             
            alist.add(map);
        }        
        
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                ctx, 
                alist,
                R.layout.client_spinner_item, 
                new String[] {
                    "nomeClient"
                }, 
                new int[] {
                    R.id.SpinnerClientItem_name
                }
            );
        
            return simpleAdapter;        
	}	
}
