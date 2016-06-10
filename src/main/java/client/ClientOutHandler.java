package client;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import common.azioniDTO.AcquistoTesseraPermessoDTO;
import common.azioniDTO.AzioneAcquistoDTO;
import common.azioniDTO.AzioneDTO;
import common.azioniDTO.AzioneOffertaDTO;
import common.azioniDTO.BonusGettoneNDTO;
import common.azioniDTO.BonusTesseraAcquistataNDTO;
import common.azioniDTO.BonusTesseraPermessoNDTO;
import common.azioniDTO.CambioTesserePermessoDTO;
import common.azioniDTO.CostruzioneAiutoReDTO;
import common.azioniDTO.CostruzioneTesseraPermessoDTO;
import common.azioniDTO.ElezioneConsigliereDTO;
import common.azioniDTO.ElezioneConsigliereVeloceDTO;
import common.azioniDTO.ExitDTO;
import common.azioniDTO.IngaggioAiutanteDTO;
import common.azioniDTO.PassaDTO;
import common.azioniDTO.PescaCartaDTO;
import common.azioniDTO.SecondaAzionePrincipaleDTO;
import common.gameDTO.AiutanteDTO;
import common.gameDTO.BalconeDTO;
import common.gameDTO.CartaPoliticaDTO;
import common.gameDTO.CittàBonusDTO;
import common.gameDTO.CittàDTO;
import common.gameDTO.ConsigliereDTO;
import common.gameDTO.GameStateDTO;
import common.gameDTO.RegioneDTO;
import common.gameDTO.TesseraPermessoDTO;

import utility.Utils;

public class ClientOutHandler implements Runnable {

	private Connessione connessione;
	private GameStateDTO gameStateDTO;
	private boolean fine = false;

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
			RegioneDTO regioneScelta;

			BalconeDTO balconeScelto;
			List<CartaPoliticaDTO> cartePolitica;
			CartaPoliticaDTO cartaPolitica;
			TesseraPermessoDTO tesseraScelta;
			CittàDTO cittàScelta;
			ConsigliereDTO consigliereScelto;
			int prezzo;
			AzioniClient azioniClient = new AzioniClient();

			String inputLine = stdIn.nextLine();

			if ("Pesca".equals(inputLine)) {
				action = new PescaCartaDTO();

			}

			else if ("Passa".equals(inputLine)) {
				action = new PassaDTO();
			}

			else if ("P1".equals(inputLine)) {
				consigliereScelto = azioniClient.scegliConsigliere(gameStateDTO.getConsiglieri(), stdIn);
				balconeScelto = azioniClient.scegliBalcone(gameStateDTO.getRegioni(),
						gameStateDTO.getPlanciaReDTO().getBalconeRe(), stdIn);
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
				balconeScelto = azioniClient.scegliBalcone(gameStateDTO.getRegioni(),
						gameStateDTO.getPlanciaReDTO().getBalconeRe(), stdIn);
				ElezioneConsigliereVeloceDTO elezione = new ElezioneConsigliereVeloceDTO();
				elezione.setConsigliere(consigliereScelto);
				elezione.setBalcone(balconeScelto);
				action = elezione;

			} else if ("V4".equals(inputLine)) {
				action = new SecondaAzionePrincipaleDTO();

			} else if ("B1".equals(inputLine)) {
				regioneScelta = azioniClient.scegliRegione(gameStateDTO.getRegioni(), stdIn);
				tesseraScelta = azioniClient.scegliTesseraRegione(regioneScelta.getTesserePermessoScoperte(), stdIn);
				BonusTesseraPermessoNDTO bonus = new BonusTesseraPermessoNDTO();
				bonus.setRegione(regioneScelta);
				bonus.setTesseraScoperta(tesseraScelta);

			} else if ("B2".equals(inputLine)) {

				BonusTesseraAcquistataNDTO bonus = new BonusTesseraAcquistataNDTO();
				tesseraScelta = null;
				System.out.println("Vuoi prendere i bonus di una tessera già usata [1] o di una scoperta [2] ?");
				String input = stdIn.nextLine();
				boolean b = false;
				while (!b) {
					while (!Utils.isNumeric(input)) {
						System.out.println("valore non valido");
						input = stdIn.nextLine();
					}
					if (Integer.parseInt(input) == 1 || Integer.parseInt(input) == 2) {
						b = true;
					}
				}

				if ("1".equals(input)) {
					bonus.setUsata(true);
					System.out.println(gameStateDTO.getGiocatoreDTO().getTesserePermessoUsate());
					System.out.println("Scegli l'indice della tessera");
					input = stdIn.nextLine();
					tesseraScelta = azioniClient
							.scegliTesseraGiocatore(gameStateDTO.getGiocatoreDTO().getTesserePermessoUsate(), stdIn);
				} else if ("2".equals(input)) {
					bonus.setUsata(false);
					System.out.println(gameStateDTO.getGiocatoreDTO().getTesserePermesso());
					System.out.println("Scegli l'indice della tessera");
					input = stdIn.nextLine();

					tesseraScelta = azioniClient
							.scegliTesseraGiocatore(gameStateDTO.getGiocatoreDTO().getTesserePermesso(), stdIn);
				}

			}

			else if ("B3".equals(inputLine)) {
				Set<CittàBonusDTO> città = new HashSet<>();
				for (CittàDTO c : gameStateDTO.getCittà()) {
					if (c.getColoreDTO().getColore()
							.equals(gameStateDTO.getGiocatoreDTO().getColoreGiocatore().getColore())
							&& (c instanceof CittàBonusDTO)) {
						System.out.println(c);
						città.add((CittàBonusDTO) c);
					}
				}
				System.out.println("Scegli una città");
				String input = stdIn.nextLine();
				input = stdIn.nextLine();
				cittàScelta = azioniClient.scegliCittà(città, gameStateDTO.getGiocatoreDTO().getColoreGiocatore(),
						stdIn);
				città.add((CittàBonusDTO) cittàScelta);
				List<CittàBonusDTO> cittàb = new ArrayList<>(città);
				BonusGettoneNDTO bonus = new BonusGettoneNDTO();
				bonus.setCittà(cittàb);
			}

			else if ("B4".equals(inputLine)) {
				Set<CittàBonusDTO> città = new HashSet<>();
				for (CittàDTO c : gameStateDTO.getCittà()) {
					if (c.getColoreDTO().getColore()
							.equals(gameStateDTO.getGiocatoreDTO().getColoreGiocatore().getColore())
							&& (c instanceof CittàBonusDTO)) {
						System.out.println(c);
						città.add((CittàBonusDTO) c);
					}
				}
				System.out.println("Scegli una città");
				String input = stdIn.nextLine();
				for (int i = 0; i < 2; i++) {
					input = stdIn.nextLine();
					cittàScelta = azioniClient.scegliCittà(città, gameStateDTO.getGiocatoreDTO().getColoreGiocatore(),
							stdIn);
					città.add((CittàBonusDTO) cittàScelta);
					System.out.println("Scegli un'altra città con gettone dei bonus diverso dalla prima");
				}
				List<CittàBonusDTO> cittàb = new ArrayList<>(città);
				BonusGettoneNDTO bonus = new BonusGettoneNDTO();
				bonus.setCittà(cittàb);
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
					AzioneOffertaDTO azioneOfferta = new AzioneOffertaDTO();
					azioneOfferta.setMarketableDTO(new AiutanteDTO(1));
					azioneOfferta.setPrezzo(prezzo);
					action = azioneOfferta;

				} else if ("2".equals(comando)) {
					cartaPolitica = azioniClient
							.scegliCarta(new ArrayList<>(gameStateDTO.getGiocatoreDTO().getCartePolitica()), stdIn);
					prezzo = azioniClient.scegliPrezzo(stdIn);
					AzioneOffertaDTO azioneOffertaDTO = new AzioneOffertaDTO();
					azioneOffertaDTO.setMarketableDTO(cartaPolitica);
					azioneOffertaDTO.setPrezzo(prezzo);
					action = azioneOffertaDTO;
				} else if ("3".equals(comando)) {
					tesseraScelta = azioniClient
							.scegliTesseraGiocatore(gameStateDTO.getGiocatoreDTO().getTesserePermesso(), stdIn);
					prezzo = azioniClient.scegliPrezzo(stdIn);
					AzioneOffertaDTO azioneOfferta=new AzioneOffertaDTO();
					azioneOfferta.setMarketableDTO(tesseraScelta);
					azioneOfferta.setPrezzo(prezzo);
					action = azioneOfferta;
				}
			}

			else if ("Acquista".equals(inputLine)) {
				System.out.println("Che cosa vuoi acquistare?");
				String comando = stdIn.nextLine();
				while (!Utils.isNumeric(comando)) {
					System.out.println("Inserire numero offerta valido");
					comando = stdIn.nextLine();
				}
				AzioneAcquistoDTO azioneAcquistoDTO=new AzioneAcquistoDTO();
				azioneAcquistoDTO.setOfferta(Integer.parseInt(comando));
				azioneAcquistoDTO.setGiocatoreDTO(gameStateDTO.getGiocatoreDTO());
				action = azioneAcquistoDTO;

			}

			else if ("Exit".equals(inputLine)) {
				action = new ExitDTO(gameStateDTO.getGiocatoreDTO());
			}

			else
				System.out.println("L'azione non esiste \nInserire un'azione valida");

			if (action != null)
				try {
					connessione.inviaAzione(action);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

	}

	public void stop() {
		this.fine = true;
	}
}
