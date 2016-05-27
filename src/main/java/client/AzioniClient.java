package client;

import java.util.ArrayList;
import java.util.Scanner;

import mapping.gameToMap.GameStateDTO;
import mapping.gameToMap.GiocatoreDTO;
import mapping.gameToMap.CartaPoliticaDTO;
import mapping.gameToMap.CittàDTO;
import mapping.gameToMap.ConsigliereDTO;
import mapping.gameToMap.RegioneDTO;
import mapping.gameToMap.TesseraPermessoDTO;

public class AzioniClient {
	
	private GiocatoreDTO giocatore;
	private Scanner stdIn; 
	private GameStateDTO gameState;
	
	String comando;
	RegioneDTO regioneScelta;
	ArrayList<CartaPoliticaDTO> cartePolitica;
	CartaPoliticaDTO cartaScelta;
	TesseraPermessoDTO tesseraScelta;
	CittàDTO cittàScelta;
	ConsigliereDTO consigliereScelto;
	
	public ConsigliereDTO scegliConsigliere(){
		System.out.println("scegli consigliere");
		System.out.println(gameState.getConsiglieri());
		comando=stdIn.nextLine();
		consigliereScelto=ControlloParametri.consiglieri(comando, gameState.getConsiglieri());
		while(consigliereScelto==null){
				System.out.println("consigliere non esistente, inserire un valore valido");
				comando=stdIn.nextLine();
				consigliereScelto=ControlloParametri.consiglieri(comando, gameState.getConsiglieri());
		}
		return consigliereScelto;
	}
	
	public RegioneDTO scegliRegione(){

		System.out.println("Scegli la regione");
		System.out.println(gameState.getRegioni());
		comando = stdIn.nextLine();
		regioneScelta = ControlloParametri.regioni(comando, gameState.getRegioni());
		while(regioneScelta == null){
			System.out.println("la regione selezionata non è esistente! \nInserire di nuovo:");							
			comando=stdIn.nextLine();
			regioneScelta= ControlloParametri.regioni(comando, gameState.getRegioni());
		}
		return regioneScelta;
	}
	
	public ArrayList<CartaPoliticaDTO> scegliCarte(){
		cartePolitica = new ArrayList<>();
		int numeroCarte=4;
		while (numeroCarte!=0){
			System.out.println("Scegli il colore delle carte politica nella tua mano");
			System.out.println(giocatore.getCartePolitica());
			comando = stdIn.nextLine();
			cartaScelta = ControlloParametri.carteGiocatore(comando, giocatore.getCartePolitica());
			while(cartaScelta==null){
				System.out.println("la carta selezionanata non è esistente!\n Inserire di nuovo");
				comando = stdIn.nextLine();
				cartaScelta = ControlloParametri.carteGiocatore(comando, giocatore.getCartePolitica());
			}
			cartePolitica.add(cartaScelta);
			System.out.println("Vuoi aggiungere un'altra carta [Y/Other]");
			comando=stdIn.nextLine();
			if(comando.equals("Y")){
				numeroCarte--;
				continue;
			}
			else
				break;
			}
		return cartePolitica;
	}
	
	public int scegliTesseraRegione(){

		System.out.println("Seleziona tessera permesso[1/2]");
		comando=stdIn.nextLine();
		while(!comando.equals("1") || !comando.equals("2")){
			System.out.println("tessera selezionata non è esistente|\n Inserire di nuovo");
			comando=stdIn.nextLine();
		}
		return Integer.parseInt(comando);
	}

	public TesseraPermessoDTO scegliTesseraGiocatore() {

		System.out.println("Seleziona l'indice di una tessera permesso non ancora usata");
		System.out.println(giocatore.getTesserePermesso());
		comando= stdIn.nextLine();
		tesseraScelta = ControlloParametri.tessereGiocatore(comando, giocatore.getTesserePermesso());
		while(tesseraScelta == null){
			System.out.println("la tessera selezionanata non è esistente!\n Inserire di nuovo");
			comando = stdIn.nextLine();
			tesseraScelta = ControlloParametri.tessereGiocatore(comando, giocatore.getTesserePermesso());
		}
		return tesseraScelta;
	}

	public CittàDTO scegliCittà() {
		System.out.println("Seleziona la città in cui costruire");
		System.out.println(tesseraScelta.getCittà());
		comando= stdIn.nextLine();
		cittàScelta= ControlloParametri.città(comando, tesseraScelta.getCittà(), giocatore.getColoreGiocatore());
		while(cittàScelta==null){
			System.out.println("la città selezionata non è esistente o contiente già un emporio!\n Inserire di nuovo");
			comando = stdIn.nextLine();
			cittàScelta= ControlloParametri.città(comando, tesseraScelta.getCittà(), giocatore.getColoreGiocatore());
		}
		return cittàScelta;
	}
	
}
