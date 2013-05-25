package info.diegoramos.smartjobs.utils;

import android.content.Context;
import android.content.pm.PackageInfo;

/**
 * Classe que disponibiliza informações da aplicação 
 * @author Diego Ramos <rdiego26@gmail>
 *
 */
public class AppInfo {

	/**
	 * Obtem versão atual da aplicação ( versionName no Manifest )
	 * @param ctx
	 * @return String que representa a versão, exemplo 1.0.0
	 */
	public static String getVersion(Context ctx) {
		
		PackageInfo pinfo = null;

		try {
			   pinfo = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0);
			   return pinfo.versionName;
			 }
		catch (Exception e) {
			   ToastManager.show(ctx, e.getMessage(), 2);
			 }
		return null;
		
	}
}
