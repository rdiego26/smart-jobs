package info.diegoramos.smartjobs.utils;

import android.content.Context;
import android.net.ConnectivityManager;

public class NetUtils {

	public static boolean isConnected(Context ctx) {
		
		try
		{
			ConnectivityManager cm = (ConnectivityManager)
			ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
	
			if (cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected() ||
					cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected()
					)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (Exception e)
		{
			ToastManager.show(ctx, e.toString(), 2);
		}
		return false;		
	}
	
}