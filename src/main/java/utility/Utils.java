package utility;

import java.util.ArrayList;

public class Utils {
	
	public static boolean isNumeric(String numero) {
		try{
			Integer.parseInt(numero);
			return true;
		}
		catch(NumberFormatException e){
			return false;
		}
	}

	
}
