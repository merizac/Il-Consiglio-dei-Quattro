package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import game.Giocatore;
import game.azioni.PescaCarta;
import mapping.azioniDTO.AcquistoTesseraPermessoDTO;
import mapping.azioniDTO.AzioneDTO;
import mapping.azioniDTO.CambioTesserePermessoDTO;
import mapping.azioniDTO.CostruzioneAiutoRe;
import mapping.azioniDTO.CostruzioneTesseraPermessoDTO;
import mapping.azioniDTO.ElezioneConsigliereDTO;
import mapping.azioniDTO.ElezioneConsigliereVeloceDTO;
import mapping.azioniDTO.SecondaAzionePrincipaleDTO;
import mapping.gameToMap.CartaPoliticaDTO;
import mapping.gameToMap.CittàDTO;
import mapping.gameToMap.ConsigliereDTO;
import mapping.gameToMap.GameStateDTO;
import mapping.gameToMap.GiocatoreDTO;
import mapping.gameToMap.IngaggioAiutanteDTO;
import mapping.gameToMap.RegioneDTO;
import mapping.gameToMap.TesseraPermessoDTO;


public class ClientOutHandler implements Runnable {

private ObjectOutputStream socketOut;
private GameStateDTO gameState;
private GiocatoreDTO giocatore;
	
	public ClientOutHandler(ObjectOutputStream socketOut, GameStateDTO gameState, GiocatoreDTO giocatore) {
		this.socketOut=socketOut;
		this.gameState=gameState;
		this.giocatore=giocatore;
	}
	
	@Override
	public void run() {

		System.out.println("RUNNING");
		//System.out.println("OutHandler :"+gameState);
		Scanner stdIn=new Scanner(System.in);
		
		while(true){
			AzioneDTO action=null;
			String inputLine=stdIn.nextLine();
			String comando;
			RegioneDTO regioneScelta;
			ArrayList<CartaPoliticaDTO> cartePolitica;
			CartaPoliticaDTO cartaScelta;
			TesseraPermessoDTO tesseraScelta;
			CittàDTO cittàScelta;
			ConsigliereDTO consigliereScelto;
			
			//if(giocatore.equals(gameState.getGiocatoreCorrente())){
				if(inputLine.equals("pesca")){
					
				}
					//action=new PescaCarta();
				if(inputLine.equals("elezione consigliere")){
					System.out.println("scegli consigliere");
					System.out.println(gameState.getConsiglieri());
					String consigliere=stdIn.nextLine();
					consigliereScelto=ControlloParametri.consiglieri(consigliere, gameState.getConsiglieri());
					while(consigliereScelto==null){
							System.out.println("consigliere non esistente, inserire un valore valido");
							consigliere=stdIn.nextLine();
							consigliereScelto=ControlloParametri.consiglieri(consigliere, gameState.getConsiglieri());
					}

					System.out.println("Scegli la regione");
					System.out.println(gameState.getRegioni());
					comando = stdIn.nextLine();
					regioneScelta = ControlloParametri.regioni(comando, gameState.getRegioni());
					while(regioneScelta == null){
						System.out.println("la regione selezionata non è esistente! \nInserire di nuovo:");							
						comando=stdIn.nextLine();
						regioneScelta= ControlloParametri.regioni(comando, gameState.getRegioni());
					}
					action = new ElezioneConsigliereDTO(consigliereScelto, regioneScelta);
					
				if(inputLine.equals("acquisto una tessera permesso")){
					System.out.println("Scegli la regione");
					System.out.println(gameState.getRegioni());
					comando = stdIn.nextLine();
					regioneScelta= ControlloParametri.regioni(comando, gameState.getRegioni());
					while(regioneScelta == null){
						System.out.println("la regione selezionata non è esistente! \nInserire di nuovo:");							
						comando=stdIn.nextLine();
						regioneScelta= ControlloParametri.regioni(comando, gameState.getRegioni());
						}
				
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
					
					System.out.println("Seleziona tessera permesso[1/2]");
					comando=stdIn.nextLine();
					while(!comando.equals("1") || !comando.equals("2")){
						System.out.println("tessera selezionata non è esistente|\n Inserire di nuovo");
						comando=stdIn.nextLine();
					}
					
					action=new AcquistoTesseraPermessoDTO(regioneScelta, cartePolitica, Integer.parseInt(comando));
					
					}
			
				if(inputLine.equals("Costruire un emporio")){
				
					System.out.println("Seleziona l'indice di una tessera permesso non ancora usata");
					System.out.println(giocatore.getTesserePermesso());
					comando= stdIn.nextLine();
					tesseraScelta = ControlloParametri.tessereGiocatore(comando, giocatore.getTesserePermesso());
					while(tesseraScelta == null){
						System.out.println("la tessera selezionanata non è esistente!\n Inserire di nuovo");
						comando = stdIn.nextLine();
						tesseraScelta = ControlloParametri.tessereGiocatore(comando, giocatore.getTesserePermesso());
					}
					System.out.println("Seleziona la città in cui costruire");
					System.out.println(tesseraScelta.getCittà());
					comando= stdIn.nextLine();
					cittàScelta= ControlloParametri.città(comando, tesseraScelta.getCittà(), giocatore.getColoreGiocatore());
					while(cittàScelta==null){
						System.out.println("la città selezionata non è esistente o contiente già un emporio!\n Inserire di nuovo");
						comando = stdIn.nextLine();
						cittàScelta= ControlloParametri.città(comando, tesseraScelta.getCittà(), giocatore.getColoreGiocatore());
					}
					action=new CostruzioneTesseraPermessoDTO(tesseraScelta, cittàScelta);
					
				}
				
				if(inputLine.equals("Aiuto del re")){
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
					System.out.println("Seleziona la città in cui costruire");
					System.out.println(gameState.getCittà());
					comando= stdIn.nextLine();
					cittàScelta= ControlloParametri.città(comando, gameState.getCittà(), giocatore.getColoreGiocatore());
					while(cittàScelta==null){
						System.out.println("la città selezionata non è esistente o contiente già un emporio!\n Inserire di nuovo");
						comando = stdIn.nextLine();
						cittàScelta= ControlloParametri.città(comando, gameState.getCittà(), giocatore.getColoreGiocatore());
					}
					action=new CostruzioneAiutoRe(cartePolitica, cittàScelta);
				}
				
				if(inputLine.equals("Ingaggiare un aiutante")){
					action = new IngaggioAiutanteDTO();
				}
				
				if(inputLine.equals("cambiare le tessere permesso")){
					System.out.println("Scegli la regione");
					System.out.println(gameState.getRegioni());
					comando = stdIn.nextLine();
					regioneScelta= ControlloParametri.regioni(comando, gameState.getRegioni());
					while(regioneScelta == null){
						System.out.println("la regione selezionata non è esistente! \nInserire di nuovo:");							
						comando=stdIn.nextLine();
						regioneScelta= ControlloParametri.regioni(comando, gameState.getRegioni());
						}
					action = new CambioTesserePermessoDTO(regioneScelta);
				}
				
				if(inputLine.equals("elezione consigliere veloce")){
					System.out.println("scegli consigliere");
					System.out.println(gameState.getConsiglieri());
					comando=stdIn.nextLine();
					consigliereScelto=ControlloParametri.consiglieri(consigliere, gameState.getConsiglieri());
					while(consigliereScelto==null){
							System.out.println("consigliere non esistente, inserire un valore valido");
							consigliere=stdIn.nextLine();
							consigliereScelto=ControlloParametri.consiglieri(consigliere, gameState.getConsiglieri());
					}

					System.out.println("Scegli la regione");
					System.out.println(gameState.getRegioni());
					comando = stdIn.nextLine();
					regioneScelta = ControlloParametri.regioni(comando, gameState.getRegioni());
					while(regioneScelta == null){
						System.out.println("la regione selezionata non è esistente! \nInserire di nuovo:");							
						comando=stdIn.nextLine();
						regioneScelta= ControlloParametri.regioni(comando, gameState.getRegioni());
					}
					action = new ElezioneConsigliereVeloceDTO(regioneScelta, consigliereScelto);
				}
				if (inputLine.equals("azione principale")){
					new SecondaAzionePrincipaleDTO();
				}
				
				else 
					System.out.println("L'azione selezionata non è fra quelle disponibili"); //while?
				
				System.out.println("SENDING THE ACTION");
				try{
					socketOut.writeObject(action);
					socketOut.flush();
				}catch(IOException e){
					e.printStackTrace();
				}
			
			//}
			
			//else
				//System.out.println("Non è il tuo turno");
			
			
			
				}
		}
	}

}
