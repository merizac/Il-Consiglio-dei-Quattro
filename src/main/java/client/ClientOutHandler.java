package client;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;
import gameDTO.azioniDTO.AcquistoTesseraPermessoDTO;
import gameDTO.azioniDTO.AzioneAcquistoDTO;
import gameDTO.azioniDTO.AzioneDTO;
import gameDTO.azioniDTO.AzioneOffertaDTO;
import gameDTO.azioniDTO.CambioTesserePermessoDTO;
import gameDTO.azioniDTO.CostruzioneAiutoReDTO;
import gameDTO.azioniDTO.CostruzioneTesseraPermessoDTO;
import gameDTO.azioniDTO.ElezioneConsigliereDTO;
import gameDTO.azioniDTO.ElezioneConsigliereVeloceDTO;
import gameDTO.azioniDTO.IngaggioAiutanteDTO;
import gameDTO.azioniDTO.PassaDTO;
import gameDTO.azioniDTO.PescaCartaDTO;
import gameDTO.azioniDTO.SecondaAzionePrincipaleDTO;
import gameDTO.gameDTO.AiutanteDTO;
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

	public ClientOutHandler(Connessione connessione, GameStateDTO gameStateDTO) {
		this.connessione = connessione;
		this.gameStateDTO = gameStateDTO;
	}

	@Override
	public void run() {

		System.out.println("CIAO " + gameStateDTO.getGiocatoreDTO().getNome().toUpperCase()
				+ ", BENVENUTO IN UNA NUOVA PARTITA DEL *Consiglio dei Quattro* !");
		Scanner stdIn = new Scanner(System.in);

		while (true) {
			AzioneDTO action = null;
			int indice;
			RegioneDTO regioneScelta;
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
				regioneScelta = azioniClient.scegliRegione(gameStateDTO.getRegioni(), stdIn);
				ElezioneConsigliereDTO elezione = new ElezioneConsigliereDTO();
				elezione.setConsigliereDTO(consigliereScelto);
				elezione.setRegioneDTO(regioneScelta);
				action = elezione;

			}

			else if ("P2".equals(inputLine)) {
				regioneScelta = azioniClient.scegliRegione(gameStateDTO.getRegioni(), stdIn);
				cartePolitica = azioniClient
						.scegliCarte(new ArrayList<>(gameStateDTO.getGiocatoreDTO().getCartePolitica()), stdIn);
				indice = azioniClient.scegliTesseraRegione(regioneScelta.getTesserePermessoScoperte(), stdIn);

				AcquistoTesseraPermessoDTO acquisto = new AcquistoTesseraPermessoDTO();
				acquisto.setRegione(regioneScelta);
				acquisto.setCarte(cartePolitica);
				acquisto.setIndiceTessera(indice);
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
				
			} else if ("B".equals(inputLine)) {
				/*
				 * for(int i=0;
				 * i<gameStateDTO.getGiocatoreDTO().getBonusNobiltà().size()-1;
				 * i++) action =
				 * (gameStateDTO.getGiocatoreDTO().getBonusNobiltà().get(i).
				 * getAzioneDTO());
				 */
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
}
