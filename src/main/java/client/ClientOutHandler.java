package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import gameDTO.azioniDTO.AcquistoTesseraPermessoDTO;
import gameDTO.azioniDTO.AzioneDTO;
import gameDTO.azioniDTO.CambioTesserePermessoDTO;
import gameDTO.azioniDTO.CostruzioneAiutoReDTO;
import gameDTO.azioniDTO.CostruzioneTesseraPermessoDTO;
import gameDTO.azioniDTO.ElezioneConsigliereDTO;
import gameDTO.azioniDTO.ElezioneConsigliereVeloceDTO;
import gameDTO.azioniDTO.IngaggioAiutanteDTO;
import gameDTO.azioniDTO.PassaDTO;
import gameDTO.azioniDTO.PescaCartaDTO;
import gameDTO.azioniDTO.SecondaAzionePrincipaleDTO;
import gameDTO.gameDTO.CartaPoliticaDTO;
import gameDTO.gameDTO.CittàDTO;
import gameDTO.gameDTO.ConsigliereDTO;
import gameDTO.gameDTO.GameStateDTO;
import gameDTO.gameDTO.GiocatoreDTO;
import gameDTO.gameDTO.RegioneDTO;
import gameDTO.gameDTO.TesseraPermessoDTO;

public class ClientOutHandler implements Runnable {

	private ObjectOutputStream socketOut;
	private GameStateDTO gameStateDTO;

	public ClientOutHandler(ObjectOutputStream socketOut, GameStateDTO gameStateDTO) {
		this.socketOut = socketOut;
		this.gameStateDTO=gameStateDTO;
	}

	@Override
	public void run() {

		System.out.println("RUNNING");
		Scanner stdIn = new Scanner(System.in);

		while (true) {
			AzioneDTO action = null;
			int indice;
			RegioneDTO regioneScelta;
			ArrayList<CartaPoliticaDTO> cartePolitica;
			TesseraPermessoDTO tesseraScelta;
			CittàDTO cittàScelta;
			ConsigliereDTO consigliereScelto;
			AzioniClient azioniClient = new AzioniClient();

			String inputLine = stdIn.nextLine();
			
			if (inputLine.equals("Pesca")) {
				action = new PescaCartaDTO();

			}
			
			else if(inputLine.equals("Passa")){
				action =new PassaDTO();
			}
			

			else if (inputLine.equals("P1")) {
				consigliereScelto = azioniClient.scegliConsigliere(gameStateDTO.getConsiglieri(), stdIn);
				System.out.println(consigliereScelto);
				regioneScelta = azioniClient.scegliRegione(gameStateDTO.getRegioni(), stdIn);
				System.out.println(regioneScelta);
				action = new ElezioneConsigliereDTO(consigliereScelto, regioneScelta);
			}

			else if (inputLine.equals("P2")) {
				regioneScelta = azioniClient.scegliRegione(gameStateDTO.getRegioni(), stdIn);
				cartePolitica = azioniClient.scegliCarte(gameStateDTO.getGiocatoreDTO().getCartePolitica(), stdIn);
				indice = azioniClient.scegliTesseraRegione(regioneScelta.getTesserePermessoScoperte(), stdIn);

				action = new AcquistoTesseraPermessoDTO(regioneScelta, cartePolitica, indice);

			}

			else if (inputLine.equals("P3")) {
				tesseraScelta = azioniClient.scegliTesseraGiocatore(gameStateDTO.getGiocatoreDTO().getTesserePermesso(), stdIn);
				cittàScelta = azioniClient.scegliCittà(tesseraScelta.getCittà(), gameStateDTO.getGiocatoreDTO().getColoreGiocatore(),
						stdIn);

				action = new CostruzioneTesseraPermessoDTO(tesseraScelta, cittàScelta);

			}

			else if (inputLine.equals("P$")) {
				cartePolitica = azioniClient.scegliCarte(gameStateDTO.getGiocatoreDTO().getCartePolitica(), stdIn);
				cittàScelta = azioniClient.scegliCittà(gameStateDTO.getCittà(), gameStateDTO.getGiocatoreDTO().getColoreGiocatore(),
						stdIn);

				action = new CostruzioneAiutoReDTO(cartePolitica, cittàScelta);
			}

			else if (inputLine.equals("V1")) {
				action = new IngaggioAiutanteDTO();
			}

			else if (inputLine.equals("V2")) {
				regioneScelta = azioniClient.scegliRegione(gameStateDTO.getRegioni(), stdIn);

				action = new CambioTesserePermessoDTO(regioneScelta);
			}

			else if (inputLine.equals("V3")) {
				consigliereScelto = azioniClient.scegliConsigliere(gameStateDTO.getConsiglieri(), stdIn);
				regioneScelta = azioniClient.scegliRegione(gameStateDTO.getRegioni(), stdIn);

				action = new ElezioneConsigliereVeloceDTO(regioneScelta, consigliereScelto);
			}
			else if (inputLine.equals("V4")) {
				new SecondaAzionePrincipaleDTO();
			}

			else
				System.out.println("L'azione non esiste \nInserire un'azione valida");

			try {
				if (action != null) {
					System.out.println("invio :"+action);
					socketOut.writeObject(action);
					socketOut.flush();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}
