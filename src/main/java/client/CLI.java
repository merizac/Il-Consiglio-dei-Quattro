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
import common.azioniDTO.PassaBonusDTO;
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
import common.gameDTO.GiocatoreDTO;
import common.gameDTO.OffertaDTO;
import common.gameDTO.RegioneDTO;
import common.gameDTO.TesseraPermessoDTO;
import server.model.bonus.Bonus;
import utility.Utils;

public class CLI implements Grafica, Runnable {

	private static final String SOCKET = "1";
	private static final String RMI = "2";
	private Connessione connessione;
	private GameStateDTO gameStateDTO;
	private Scanner stdIn;

	@Override
	public void setConnessione(Connessione connessione) {
		this.connessione = connessione;
	}

	@Override
	public void setGameStateDTO(GameStateDTO gameStateDTO) {
		this.gameStateDTO = gameStateDTO;
	}

	@Override
	public void run() {

		stdIn = new Scanner(System.in);

		this.scegliNome();

		System.out.println("CIAO " + gameStateDTO.getGiocatoreDTO().getNome().toUpperCase()
				+ ", BENVENUTO IN UNA NUOVA PARTITA DEL *Consiglio dei Quattro* !");
		try {
			this.connessione = scegliConnessione();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		try {
			this.connessione.setGrafica(this);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}

		try {
			this.connessione.setGameStateDTO(this.gameStateDTO);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		try {
			this.connessione.start();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}

		while (true) {
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

			else if ("Passa Bonus".equals(inputLine)) {
				action = new PassaBonusDTO();
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
				action = bonus;

			} else if ("B2".equals(inputLine)) {

				BonusTesseraAcquistataNDTO bonus = new BonusTesseraAcquistataNDTO();
				if (gameStateDTO.getGiocatoreDTO().getTesserePermesso().isEmpty()
						&& gameStateDTO.getGiocatoreDTO().getTesserePermessoUsate().isEmpty()) {
					System.out.println("Non hai tessere permesso! \nNon puoi utilizzare il bonus!");
					PassaBonusDTO passaBonus = new PassaBonusDTO();
					passaBonus.setBonus(new BonusTesseraAcquistataNDTO());
					action = passaBonus;
				} else {
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
						System.out.println(gameStateDTO.getGiocatoreDTO().getTesserePermessoUsate().toString());
						System.out.println("Scegli l'indice della tessera");
						input = stdIn.nextLine();
						tesseraScelta = azioniClient.scegliTesseraGiocatore(
								gameStateDTO.getGiocatoreDTO().getTesserePermessoUsate(), stdIn);
						action = bonus;
					} else if ("2".equals(input)) {
						bonus.setUsata(false);
						System.out.println(gameStateDTO.getGiocatoreDTO().getTesserePermesso().toString());
						System.out.println("Scegli l'indice della tessera");
						input = stdIn.nextLine();

						tesseraScelta = azioniClient
								.scegliTesseraGiocatore(gameStateDTO.getGiocatoreDTO().getTesserePermesso(), stdIn);
						action = bonus;
					}
				}
			}

			else if ("B3".equals(inputLine)) {
				Set<CittàBonusDTO> città = new HashSet<>();
				for (CittàDTO c : gameStateDTO.getCittà()) {
					if (c.getEmpori().contains(gameStateDTO.getGiocatoreDTO().getColoreGiocatore().getColore())
							&& (c instanceof CittàBonusDTO)) {
						città.add((CittàBonusDTO) c);
					}
				}
				if (!città.isEmpty()) {
					System.out.println("Scegli una città");
					System.out.println(città.toString());
					String input = stdIn.nextLine();
					cittàScelta = azioniClient.scegliCittàBonus(città,
							gameStateDTO.getGiocatoreDTO().getColoreGiocatore(), stdIn, input);
					List<CittàBonusDTO> cittàb = new ArrayList<>();
					cittàb.add((CittàBonusDTO) cittàScelta);
					BonusGettoneNDTO bonus = new BonusGettoneNDTO();
					bonus.setCittà(cittàb);
					action = bonus;
				} else {
					System.out.println("Non hai costruito in nessuna città! \nNon puoi utilizzare il bonus!");
					PassaBonusDTO passaBonus = new PassaBonusDTO();
					passaBonus.setBonus(new BonusGettoneNDTO());
					action = passaBonus;
				}
			}

			else if ("B4".equals(inputLine)) {
				Set<CittàBonusDTO> città = new HashSet<>();
				for (CittàDTO c : gameStateDTO.getCittà()) {
					if (c.getEmpori().contains(gameStateDTO.getGiocatoreDTO().getColoreGiocatore().getColore())
							&& (c instanceof CittàBonusDTO)) {
						System.out.println(c.toString());
						città.add((CittàBonusDTO) c);
					}
				}
				if (!città.isEmpty()) {
					System.out.println("Scegli una città");
					String input = stdIn.nextLine();
					List<CittàBonusDTO> cittàb = new ArrayList<>();
					for (int i = 0; i < 2; i++) {
						input = stdIn.nextLine();
						cittàScelta = azioniClient.scegliCittàBonus(città,
								gameStateDTO.getGiocatoreDTO().getColoreGiocatore(), stdIn, input);
						cittàb.add((CittàBonusDTO) cittàScelta);
						System.out.println("Scegli un'altra città con gettone dei bonus diverso dalla prima");
					}

					BonusGettoneNDTO bonus = new BonusGettoneNDTO();
					bonus.setCittà(cittàb);
					action = bonus;
				} else {
					System.out.println("Non hai costruito in nessuna città! \nNon puoi utilizzare il bonus!");
					PassaBonusDTO passaBonus = new PassaBonusDTO();
					passaBonus.setBonus(new BonusGettoneNDTO());
					action = passaBonus;
				}

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
					AzioneOffertaDTO azioneOfferta = new AzioneOffertaDTO();
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
				AzioneAcquistoDTO azioneAcquistoDTO = new AzioneAcquistoDTO();
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

	/**
	 * this method let the client chose the name
	 * 
	 * @return the name of the player
	 */
	public void scegliNome() {
		String nome = null;
		while (nome == null || "".equals(nome)) {
			System.out.println("Inserisci il nome");
			nome = stdIn.nextLine();
		}

		GiocatoreDTO giocatoreDTO = new GiocatoreDTO();
		giocatoreDTO.setNome(nome);
		this.gameStateDTO = new GameStateDTO();
		this.gameStateDTO.setGiocatoreDTO(giocatoreDTO);
	}

	/**
	 * this method let the player chose the connection between socket and rmi
	 * 
	 * @param giocatore
	 * @return the connection selected
	 * @throws RemoteException
	 */
	public Connessione scegliConnessione() throws RemoteException {
		System.out.println("Inserisci connessione\nSocket[1]\nRMI[2]");
		String connessioneClient = null;
		while (true) {
			{
				connessioneClient = stdIn.nextLine();
				if (SOCKET.equals(connessioneClient)) {
					return new ConnessioneSocket();

				} else if (RMI.equals(connessioneClient))
					try {
						return new ConnessioneRMI();
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				else
					System.out.println("Inserisci un valore valido");
			}
		}
	}

	@Override
	public void mostraAzioni(List<AzioneDTO> azioni) {
		for (AzioneDTO a : azioni) {
			System.out.println(a.toString());
		}
	}

	@Override
	public void mostraClassifica(List<GiocatoreDTO> vincenti, List<GiocatoreDTO> perdenti) {
		for (GiocatoreDTO g : vincenti) {
			System.out.println(
					"Giocatore :" + g.getNome().toUpperCase() + " Punteggio " + g.getPunteggioVittoria() + " punti");
		}
		for (GiocatoreDTO g : perdenti) {
			System.out.println(
					"Giocatore :" + g.getNome().toUpperCase() + " Punteggio " + g.getPunteggioVittoria() + " punti");
		}
	}

	@Override
	public void mostraGame(GameStateDTO gameStateDTO) {
		System.out.println("\nCittà");
		for (CittàDTO c : gameStateDTO.getCittà()) {
			System.out.println(c.toString());
		}
		System.out.println("\nRegioni");
		for (RegioneDTO r : gameStateDTO.getRegioni()) {
			System.out.println(r.toString());
			String balcone = "Balcone [ ";
			for (ConsigliereDTO cons : r.getBalcone().getConsiglieri()) {
				balcone = balcone + cons + " ";
			}
			balcone = balcone + "]";
			System.out.println(balcone);
			System.out.println("Tessere Permesso Scoperte nella regione " + r.getNome());
			for (TesseraPermessoDTO t : r.getTesserePermessoScoperte()) {
				String cittàTessera = " Città [ ";
				String bonusTessera = ", Bonus [ ";
				for (CittàDTO ci : t.getCittà()) {
					cittàTessera = cittàTessera + ci.getNome() + " ";
				}
				cittàTessera = cittàTessera + "] ";
				for (Bonus bonus : t.getBonus()) {
					bonusTessera = bonusTessera + bonus + " ";
				}
				bonusTessera = bonusTessera + "]";
				System.out.println("Tessera [" + cittàTessera + bonusTessera + " ]");

			}
			System.out.println("BonusRegione [" + r.getBonusRegione() + " ]");

		}
		String balconeRe = "Balcone Re [ ";
		for (ConsigliereDTO consRe : gameStateDTO.getPlanciaReDTO().getBalconeRe().getConsiglieri()) {
			balconeRe = balconeRe + consRe + " ";
		}
		balconeRe = balconeRe + "]";
		System.out.println(balconeRe);
		System.out.println(gameStateDTO.getPedinaRE().toString());

		String bonusRe = "BonusRe\n";

		for (Bonus b : gameStateDTO.getPlanciaReDTO().getBonusPremioRe()) {
			bonusRe = bonusRe + b + "\n";
		}
		System.out.println(bonusRe);
		String riserva = "Consiglieri [ ";
		for (ConsigliereDTO c : gameStateDTO.getConsiglieri()) {
			riserva = riserva + c.getColoreConsigliere() + " ";
		}
		riserva = riserva + "]";
		System.out.println(riserva);

	}

	@Override

	public void mostraGiocatore(GiocatoreDTO giocatoreDTO) {
		System.out.println(giocatoreDTO.toString());
	}

	@Override
	public void mostraMessaggio(String messaggio) {
		System.out.println(messaggio);
	}

	@Override
	public void mostraOfferte(List<OffertaDTO> offerte) {
		for (OffertaDTO o : offerte) {
			System.out.println("\n" + o.getMarketableDTO() + " prezzo: " + o.getPrezzo());
		}
	}

}
