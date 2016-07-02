package utility;

public class Utils {
	/**
	 * check if is a number
	 * 
	 * @param numero
	 * @return
	 */
	public static boolean isNumeric(String numero) {
		try {
			Integer.parseInt(numero);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
