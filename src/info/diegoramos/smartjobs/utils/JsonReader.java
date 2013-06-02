package info.diegoramos.smartjobs.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JsonReader {

	
    public static JSONObject ReadJSONURL(String URL) {
        JSONObject jArray = null;
        
    	StringBuilder stringBuilder = new StringBuilder();
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(URL);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                InputStream inputStream = entity.getContent();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inputStream));
                String line;
                
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                
                inputStream.close();
            } else {
                Log.d("JSON", "Failed to download file");
            }
        } catch (Exception e) {
            Log.d("readJSONFeed", e.getLocalizedMessage());
        }        
        
        try {
			jArray = new JSONObject(stringBuilder.toString());
		} catch (JSONException e) {
			Log.e("JSONEXCEPTION", "Error parsing data "+ e.toString());
		}
        
        return jArray;
    }	

    public static String montaUrlIndeed(String filterTerm, String filterLocalion) {
    	//TODO considerar utilizar Preferencias
    	String limit = "10";
    	
    	@SuppressWarnings("deprecation")
		StringBuilder stringBuilder = new StringBuilder()
    		.append("http://api.indeed.com/ads/apisearch?publisher=1271650249569682&sort=&radius=&st=&jt=&start=")
    		.append("&limit=" + limit + "&fromage=&latlong=1&co=us&chnl=&v=2&format=json")
    		.append("&q=" + URLEncoder.encode(filterTerm))
    		.append("&l=" + URLEncoder.encode(filterLocalion));
    	
    		//Using URI to encode HTML chars
    	return stringBuilder.toString();
    }
    
}