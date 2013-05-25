package info.diegoramos.smartjobs.model;

public class Validations {

	public final boolean isNull(String nome, Object obj) {
		if( obj.toString().trim().equalsIgnoreCase("") || obj.toString().trim().equalsIgnoreCase(null) || obj.toString().trim().equals(null) ) {
			return true;
		}
			
		return false;
	}
}
