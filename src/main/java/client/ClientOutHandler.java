package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
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
import mapping.gameToMap.IngaggioAiutanteDTO;
import mapping.gameToMap.RegioneDTO;
import mapping.gameToMap.TesseraPermessoDTO;


public class ClientOutHandler implements Runnable {

private ObjectOutputStream socketOut;
	
	public ClientOutHandler(ObjectOutputStream socketOut) {
		this.socketOut=socketOut;
	}
	
	@Override
	public void run() {

		System.out.println("RUNNING");
		//System.out.println("OutHandler :"+gameState);
		Scanner stdIn=new Scanner(System.in);
		
		while(true){
			AzioneDTO action=null;
			int indice;
			String inputLine=stdIn.nextLine();
			RegioneDTO regioneScelta;
			ArrayList<CartaPoliticaDTO> cartePolitica;
			TesseraPermessoDTO tesseraScelta;
			CittàDTO cittàScelta;
			ConsigliereDTO consigliereScelto;
			AzioniClient azioniClient = new AzioniClient();
			
			//if(giocatore.equals(gameState.getGiocatoreCorrente())){
				if(inputLine.equals("pesca")){
					
				}
					//action=new PescaCarta();
				if(inputLine.equals("elezione consigliere")){
					consigliereScelto  = azioniClient.scegliConsigliere();
					regioneScelta = azioniClient.scegliRegione();
					
					action = new ElezioneConsigliereDTO(consigliereScelto, regioneScelta);
					
				if(inputLine.equals("acquisto una tessera permesso")){
					regioneScelta = azioniClient.scegliRegione();
					cartePolitica = azioniClient.scegliCarte();
					indice = azioniClient.scegliTesseraRegione();
					
					action=new AcquistoTesseraPermessoDTO(regioneScelta, cartePolitica, indice);
					
					}
			
				if(inputLine.equals("Costruire un emporio")){
					tesseraScelta = azioniClient.scegliTesseraGiocatore();
					cittàScelta = azioniClient.scegliCittà();
					
					action=new CostruzioneTesseraPermessoDTO(tesseraScelta, cittàScelta);
					
				}
				
				if(inputLine.equals("Aiuto del re")){
					cartePolitica = azioniClient.scegliCarte();
					cittàScelta = azioniClient.scegliCittà();
					
					action=new CostruzioneAiutoRe(cartePolitica, cittàScelta);
				}
				
				if(inputLine.equals("Ingaggiare un aiutante")){
					action = new IngaggioAiutanteDTO();
				}
				
				if(inputLine.equals("cambiare le tessere permesso")){
					regioneScelta = azioniClient.scegliRegione();
					
					action = new CambioTesserePermessoDTO(regioneScelta);
				}
				
				if(inputLine.equals("elezione consigliere veloce")){
					consigliereScelto = azioniClient.scegliConsigliere();
					regioneScelta= azioniClient.scegliRegione();
					
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
