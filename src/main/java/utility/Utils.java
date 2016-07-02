package utility;


public class Utils {
	
	public Utils(){
	}
	
	public static boolean isNumeric(String numero) {
		try{
			Integer.parseInt(numero);
			return true;
		}
		catch(NumberFormatException e){
			return false;
		}
	}
	
	public static void print(String messaggio){
		System.out.println(messaggio);
	}
	
}
