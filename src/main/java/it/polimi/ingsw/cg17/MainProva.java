package it.polimi.ingsw.cg17;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import controller.Partita;
import game.Città;
import game.CittàBonus;
import game.Colore;
import game.Emporio;
import game.GameState;
import game.ParserAzione;
import view.View;
import view.ViewCLI;

public class MainProva {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameState gameState=null;
		Reader reader=new Reader();
		try{
		gameState=reader.inizializzatore();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		ParserAzione parser=new ParserAzione(gameState);
		View view= new ViewCLI(gameState, parser);
		new Partita(gameState, view);
		Scanner scanner = new Scanner(System.in);
		 view.input(scanner);
		 
		 while(true){
			 System.out.println("Inserisci azione");
			 String command=scanner.nextLine();
		 }
	}

}
