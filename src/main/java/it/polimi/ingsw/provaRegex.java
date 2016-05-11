package it.polimi.ingsw;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class provaRegex {

	public static void main(String[] args) {
		String cartePolitica="((\\d)*)";
		Pattern p= Pattern.compile(cartePolitica);
		Scanner scanner=new Scanner(System.in);
		String prova=scanner.nextLine();
		Matcher matcher = p.matcher(prova);
		System.out.println(matcher.matches());
		scanner.close();
	}

}
