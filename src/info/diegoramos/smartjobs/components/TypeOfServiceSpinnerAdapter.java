package info.diegoramos.smartjobs.components;

<<<<<<< HEAD
import info.diegoramos.smartjobs2.R;
=======
import info.diegoramos.smartjobs.R;
>>>>>>> ce5e6909afed8fac9b7e1284789a2206337555bd
import info.diegoramos.smartjobs.model.TypeOfService;
import info.diegoramos.smartjobs.persistence.DAOTypeOfService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.widget.SimpleAdapter;

public class TypeOfServiceSpinnerAdapter {

	public static final SimpleAdapter getAdapter(Context ctx) {
		DAOTypeOfService daoT = DAOTypeOfService.getInstance(ctx);
		List<TypeOfService> list = daoT.getAll();
		
		ArrayList<HashMap<String, String>> alist = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map = new HashMap<String, String>();
        
        for(int i=0; i < list.size(); i++){
 
            map = new HashMap<String,String>();
            map.put("nomeTypeOfService", list.get(i).getName());
             
            alist.add(map);
        }        
        
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                ctx, 
                alist,
                R.layout.type_of_service_spinner_item, 
                new String[] {
                    "nomeTypeOfService"
                }, 
                new int[] {
                    R.id.SpinnerTypeOfServiceItem_name
                }
            );
                     
            return simpleAdapter;        
	}
	
}
