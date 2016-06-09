package client;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

import gameDTO.azioniDTO.AcquistoTesseraPermessoDTO;
import gameDTO.azioniDTO.AzioneAcquistoDTO;
import gameDTO.azioniDTO.AzioneDTO;
import gameDTO.azioniDTO.AzioneOffertaDTO;
import gameDTO.azioniDTO.BonusTesseraAcquistataNDTO;
import gameDTO.azioniDTO.BonusTesseraPermessoNDTO;
import gameDTO.azioniDTO.CambioTesserePermessoDTO;
import gameDTO.azioniDTO.CostruzioneAiutoReDTO;
import gameDTO.azioniDTO.CostruzioneTesseraPermessoDTO;
import gameDTO.azioniDTO.ElezioneConsigliereDTO;
import gameDTO.azioniDTO.ElezioneConsigliereVeloceDTO;
import gameDTO.azioniDTO.ExitDTO;
import gameDTO.azioniDTO.IngaggioAiutanteDTO;
import gameDTO.azioniDTO.PassaDTO;
import gameDTO.azioniDTO.PescaCartaDTO;
import gameDTO.azioniDTO.SecondaAzionePrincipaleDTO;
import gameDTO.gameDTO.AiutanteDTO;
import gameDTO.gameDTO.BalconeDTO;
import gameDTO.gameDTO.CartaPoliticaDTO;
import gameDTO.gameDTO.CittàDTO;
import gameDTO.gameDTO.ConsigliereDTO;
import gameDTO.gameDTO.GameStateDTO;
import gameDTO.gameDTO.RegioneDTO;
import gameDTO.gameDTO.TesseraPermessoDTO;
import utility.Utils;

public class ClientOutHandler implements Runnable {

	private Connessione connessione;
	private GameStateDTO gameStateDTO;
	private boolean fine=false;

	public ClientOutHandler(Connessione connessione, GameStateDTO gameStateDTO) {
		this.connessione = connessione;
		this.gameStateDTO = gameStateDTO;
	}

	@Override
	public void run() {

		System.out.println("CIAO " + gameStateDTO.getGiocatoreDTO().getNome().toUpperCase()
				+ ", BENVENUTO IN UNA NUOVA PARTITA DEL *Consiglio dei Quattro* !");
		Scanner stdIn = new Scanner(System.in);

		while (!fine) {
			AzioneDTO action = null;
			int indice;
			RegioneDTO regioneScelta;
			BalconeDTO balconeScelto;
			ArrayList<CartaPoliticaDTO> cartePolitica;
			CartaPoliticaDTO cartaPolitica;
			TesseraPermessoDTO tesseraScelta;
			CittàDTO cittàScelta;
			ConsigliereDTO consigliereScelto;
			int prezzo;
			AzioniClient azioniClient = new AzioniClient();

			String inputLine = stdIn.nextLine();

			if (inputLine.equals("Pesca")) {
				action = new PescaCartaDTO();

			}

			else if ("Passa".equals(inputLine)) {
				action = new PassaDTO();
			}

			else if ("P1".equals(inputLine)) {
				consigliereScelto = azioniClient.scegliConsigliere(gameStateDTO.getConsiglieri(), stdIn);
				balconeScelto = azioniClient.scegliBalcone(gameStateDTO.getRegioni(), gameStateDTO.getPlanciaReDTO().getBalconeRe(), stdIn);
				ElezioneConsigliereDTO elezione = new ElezioneConsigliereDTO();
				elezione.setConsigliereDTO(consigliereScelto);
				elezione.setBalconeDTO(balconeScelto);
				action = elezione;

			}

			else if ("P2".equals(inputLine)) {
				regioneScelta = azioniClient.scegliRegione(gameStateDTO.getRegioni(), stdIn);
				cartePolitica = azioniClient
						.scegliCarte(new ArrayList<>(gameStateDTO.getGiocatoreDTO().getCartePolitica()), stdIn);
				tesseraScelta = azioniClient.scegliTesseraRegione(regioneScelta.getTesserePermessoScoperte(), stdIn);

				AcquistoTesseraPermessoDTO acquisto = new AcquistoTesseraPermessoDTO();
				acquisto.setRegione(regioneScelta);
				acquisto.setCarte(cartePolitica);
				acquisto.setTesseraPermesso(tesseraScelta);
				action = acquisto;
			}

			else if ("P3".equals(inputLine)) {
				tesseraScelta = azioniClient.scegliTesseraGiocatore(gameStateDTO.getGiocatoreDTO().getTesserePermesso(),
						stdIn);
				cittàScelta = azioniClient.scegliCittà(tesseraScelta.getCittà(),
						gameStateDTO.getGiocatoreDTO().getColoreGiocatore(), stdIn);

				CostruzioneTesseraPermessoDTO costruzione = new CostruzioneTesseraPermessoDTO();
				costruzione.setTesseraPermesso(tesseraScelta);
				costruzione.setCittà(cittàScelta);
				action = costruzione;
			}

			else if ("P4".equals(inputLine)) {

				cartePolitica = azioniClient
						.scegliCarte(new ArrayList<>(gameStateDTO.getGiocatoreDTO().getCartePolitica()), stdIn);
				cittàScelta = azioniClient.scegliCittà(gameStateDTO.getCittà(),
						gameStateDTO.getGiocatoreDTO().getColoreGiocatore(), stdIn);

				CostruzioneAiutoReDTO costruzioneRe = new CostruzioneAiutoReDTO();
				costruzioneRe.setCarteGiocatore(cartePolitica);
				costruzioneRe.setCittà(cittàScelta);
				action = costruzioneRe;
			}

			else if ("V1".equals(inputLine)) {
				action = new IngaggioAiutanteDTO();
			}

			else if ("V2".equals(inputLine)) {
				regioneScelta = azioniClient.scegliRegione(gameStateDTO.getRegioni(), stdIn);

				CambioTesserePermessoDTO cambio = new CambioTesserePermessoDTO();
				cambio.setRegione(regioneScelta);
				action = cambio;
			}

			else if ("V3".equals(inputLine)) {
				consigliereScelto = azioniClient.scegliConsigliere(gameStateDTO.getConsiglieri(), stdIn);
				regioneScelta = azioniClient.scegliRegione(gameStateDTO.getRegioni(), stdIn);
				ElezioneConsigliereVeloceDTO elezione= new ElezioneConsigliereVeloceDTO();
				elezione.setConsigliere(consigliereScelto);
				elezione.setRegione(regioneScelta);
				action = elezione;
				
			} else if ("V4".equals(inputLine)) {
				action = new SecondaAzionePrincipaleDTO();
				
			} else if ("B1".equals(inputLine)) {
				regioneScelta = azioniClient.scegliRegione(gameStateDTO.getRegioni(), stdIn);
				tesseraScelta = azioniClient.scegliTesseraRegione(regioneScelta.getTesserePermessoScoperte(), stdIn);
				BonusTesseraPermessoNDTO bonus = new BonusTesseraPermessoNDTO();
				bonus.setRegione(regioneScelta);
				bonus.setTesseraScoperta(tesseraScelta);
				
			
			} else if ("B2".equals(inputLine)){
				
				BonusTesseraAcquistataNDTO bonus = new BonusTesseraAcquistataNDTO();
				tesseraScelta = null;
				System.out.println("Vuoi prendere i bonus di una tessera già usata [1] o di una scoperta [2] ?");
				String input = stdIn.nextLine();
				boolean b = false;
				while (!b) {
					while (!Utils.isNumeric(input)) {
						System.out.println("valore non valido");
						input = stdIn.nextLine();}
					if (Integer.parseInt(input) == 1 || Integer.parseInt(input) == 2)
						b = true;}

				if ("1".equals(input)) {
					bonus.setUsata(true);
					System.out.println(gameStateDTO.getGiocatoreDTO().getTesserePermessoUsate());
					System.out.println("Scegli l'indice della tessera");
					input = stdIn.nextLine();
					tesseraScelta = azioniClient.scegliTesseraGiocatore(gameStateDTO.getGiocatoreDTO().getTesserePermessoUsate(), stdIn);
				} 
				else if ("2".equals(input)) {
					bonus.setUsata(false);
					System.out.println(gameStateDTO.getGiocatoreDTO().getTesserePermesso());
					System.out.println("Scegli l'indice della tessera");
					input = stdIn.nextLine();
					tesseraScelta = azioniClient.scegliTesseraGiocatore(gameStateDTO.getGiocatoreDTO().getTesserePermesso(), stdIn);
				}	
					bonus.setTesseraPermesso(tesseraScelta);
				}
			
			else if ("B3".equals(inputLine)){
				
			}

			else if ("Offerta".equals(inputLine)) {
				System.out.println("Cosa vuoi vendere?\n");
				System.out.println("Aiutante[1]\nCarta Politica[2]\nTesseraPermesso[3]");
				String comando = stdIn.nextLine();
				boolean ok = false;
				while (!ok) {

					while (!Utils.isNumeric(comando)) {
						System.out.println("valore non valido");
						comando = stdIn.nextLine();
					}

					if (Integer.parseInt(comando) == 1 || Integer.parseInt(comando) == 2
							|| Integer.parseInt(comando) == 3)
						ok = true;
				}

				if ("1".equals(comando)) {
					prezzo = azioniClient.scegliPrezzo(stdIn);
					action = new AzioneOffertaDTO(new AiutanteDTO(1), prezzo);

				} else if ("2".equals(comando)) {
					cartaPolitica = azioniClient
							.scegliCarta(new ArrayList<>(gameStateDTO.getGiocatoreDTO().getCartePolitica()), stdIn);
					prezzo = azioniClient.scegliPrezzo(stdIn);
					action = new AzioneOffertaDTO(cartaPolitica, prezzo);
				} else if ("3".equals(comando)) {
					tesseraScelta = azioniClient
							.scegliTesseraGiocatore(gameStateDTO.getGiocatoreDTO().getTesserePermesso(), stdIn);
					prezzo = azioniClient.scegliPrezzo(stdIn);
					action = new AzioneOffertaDTO(tesseraScelta, prezzo);
				}
			}

			else if ("Acquista".equals(inputLine)) {
				System.out.println("Che cosa vuoi acquistare?");
				String comando = stdIn.nextLine();
				while (!Utils.isNumeric(comando)) {
					System.out.println("Inserire numero offerta valido");
					comando = stdIn.nextLine();
				}
				action = new AzioneAcquistoDTO(Integer.parseInt(comando), gameStateDTO.getGiocatoreDTO());

			}
			
			else if("Exit".equals(inputLine)){
				action = new ExitDTO(gameStateDTO.getGiocatoreDTO());
			}

			else
				System.out.println("L'azione non esiste \nInserire un'azione valida");

			if(action!=null)
				try {
					connessione.inviaAzione(action);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	public void stop() {
		this.fine=true;
	}
}

