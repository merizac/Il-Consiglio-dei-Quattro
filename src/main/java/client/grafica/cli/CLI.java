package client.grafica.cli;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import client.ControlloParametriDTO;
import client.connessione.Connessione;
import client.connessione.ConnessioneRMI;
import client.connessione.ConnessioneSocket;
import client.grafica.Grafica;
import common.azioniDTO.AzioneDTO;
import common.azioniDTO.AzioneParametri;
import common.azioniDTO.BonusTesseraAcquistataNDTO;
import common.gameDTO.AiutanteDTO;
import common.gameDTO.BalconeDTO;
import common.gameDTO.CartaPoliticaDTO;
import common.gameDTO.CittàBonusDTO;
import common.gameDTO.CittàDTO;
import common.gameDTO.ColoreDTO;
import common.gameDTO.ConsigliereDTO;
import common.gameDTO.GameStateDTO;
import common.gameDTO.GiocatoreDTO;
import common.gameDTO.MarketableDTO;
import common.gameDTO.OffertaDTO;
import common.gameDTO.RegioneDTO;
import common.gameDTO.TesseraPermessoDTO;
import server.model.bonus.Bonus;
import server.view.clientNotify.ClientNotify;
import utility.Utils;

public class CLI implements Grafica {

	private static final String SOCKET = "1";
	private static final String RMI = "2";
	private Connessione connessione;
	private GameStateDTO gameStateDTO;
	private Scanner stdIn;

	public void start() {

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
			TesseraPermessoDTO tesseraScelta;
			CittàDTO cittàScelta;
			AzioniClient azioniClient = new AzioniClient();

			String inputLine = stdIn.nextLine();
			
			AzioneDTO action=gameStateDTO.getAzioniDisponibili()
					.stream()
					.filter(a -> a.toString().contains(inputLine))
					.findAny()
					.orElse(null);

			if (action == null)
				System.out.println("L'azione non esiste \nInserire un'azione valida");

			else {

				if (action instanceof AzioneParametri)
					((AzioneParametri) action).parametri().setParametri(this, gameStateDTO);
				try {
					connessione.inviaAzione(action);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		/*	if ("B2".equals(inputLine)) {

				BonusTesseraAcquistataNDTO bonus = new BonusTesseraAcquistataNDTO();
				bonus.parametri().setParametri(this, gameStateDTO);
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

			else if ("Exit".equals(inputLine)) {
				action = new ExitDTO(gameStateDTO.getGiocatoreDTO());
			}

			if (action != null)
				try {
					connessione.inviaAzione(action);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
		}
	}

	/**
	 * set the connection
	 */
	@Override
	public void setConnessione(Connessione connessione) {
		this.connessione = connessione;
	}

	/**
	 * set the gameStateDTO
	 */
	@Override
	public void setGameStateDTO(GameStateDTO gameStateDTO) {
		this.gameStateDTO = gameStateDTO;
	}

	/**
	 * let the client chose the name
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
	 * let the player chose the connection between socket and rmi
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

	/**
	 * show the actions available
	 */
	@Override
	public void mostraAzioni(List<AzioneDTO> azioni) {
		for (AzioneDTO a : azioni) {
			System.out.println(a.toString());
		}
	}

	/**
	 * show the placement of the players
	 */
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

	/**
	 * show the game
	 */
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

	/**
	 * show the currant state of the player
	 */
	@Override
	public void mostraGiocatore(GiocatoreDTO giocatoreDTO) {
		System.out.println(giocatoreDTO.toString());
	}

	/**
	 * show a message from the server
	 */
	@Override
	public void mostraMessaggio(String messaggio) {
		System.out.println(messaggio);
	}

	/**
	 * show object in sale in the market state
	 */
	@Override
	public void mostraOfferte(List<OffertaDTO> offerte) {
		for (OffertaDTO o : offerte) {
			int i=1;
			System.out.println("\n" + o.getMarketableDTO() + " prezzo: " + o.getPrezzo()+ " ["+i+"]");
			i++;
		}
	}
	
	@Override
	public int scegliOfferta(List<OffertaDTO> offerte){
		System.out.println("Che cosa vuoi acquistare?");
		String comando = stdIn.nextLine();
		boolean ok = false;
		while (!ok) {

			while (!Utils.isNumeric(comando)) {
				System.out.println("Inserire numero offerta valido");
				comando = stdIn.nextLine();
			}

			if (Integer.parseInt(comando)>0 && Integer.parseInt(comando)<=offerte.size())
				ok = true;
		}
		
		return Integer.parseInt(comando);
		
	}

	@Override
	public MarketableDTO scegliMarketable() {
		System.out.println("Cosa vuoi vendere?\n");
		System.out.println("Aiutante[1]\nCarta Politica[2]\nTesseraPermesso[3]");
		String comando = stdIn.nextLine();
		boolean ok = false;
		while (!ok) {

			while (!Utils.isNumeric(comando)) {
				System.out.println("valore non valido");
				comando = stdIn.nextLine();
			}

			if (Integer.parseInt(comando) == 1 || Integer.parseInt(comando) == 2 || Integer.parseInt(comando) == 3)
				ok = true;
		}
		if ("1".equals(comando))
			return new AiutanteDTO(1);
		if ("2".equals(comando)) {
			return this.scegliCarta(new ArrayList<>(gameStateDTO.getGiocatoreDTO().getCartePolitica()));
		} else
			return this.scegliTesseraGiocatore(gameStateDTO.getGiocatoreDTO().getTesserePermesso());

	}

	/**
	 * this method let the player choose a counselor which want to add into the
	 * balcony if the player insert a counselor that not exists, he will insert
	 * again the counselor
	 * 
	 * @return ConsigliereDTO choose from the player
	 */
	@Override
	public ConsigliereDTO scegliConsigliere(List<ConsigliereDTO> consiglieri) {
		System.out.println("scegli consigliere");
		System.out.println(consiglieri.toString());
		String comando = stdIn.nextLine();
		ConsigliereDTO consigliereScelto = ControlloParametriDTO.consiglieri(comando, consiglieri);
		while (consigliereScelto == null) {
			System.out.println("consigliere non esistente, inserire un valore valido");
			comando = stdIn.nextLine();
			consigliereScelto = ControlloParametriDTO.consiglieri(comando, consiglieri);
		}
		return consigliereScelto;
	}

	/**
	 * this method let the player choose the region if the player insert a
	 * region that not exists, he will insert again the region
	 * 
	 * @return RegioneDTO choose from the player
	 */
	@Override
	public RegioneDTO scegliRegione(List<RegioneDTO> regioni) {

		System.out.println("Scegli la regione");
		System.out.println(regioni.toString());
		String comando = stdIn.nextLine();
		RegioneDTO regioneScelta = ControlloParametriDTO.regioni(comando, regioni);
		while (regioneScelta == null) {
			System.out.println("la regione selezionata non è esistente! \nInserire di nuovo:");
			comando = stdIn.nextLine();
			regioneScelta = ControlloParametriDTO.regioni(comando, regioni);
		}
		return regioneScelta;
	}

	/**
	 * this method let the player chose the balcony where he want to elect a
	 * counselor
	 * 
	 * @param regioni
	 * @param balconeRe
	 * @param stdIn
	 * @return the balcony DTO selected
	 */
	@Override
	public BalconeDTO scegliBalcone(List<RegioneDTO> regioni, BalconeDTO balconeRe) {
		BalconeDTO balconeScelto = null;
		boolean ok = false;
		System.out.println("Scegli il balcone");
		for (RegioneDTO regione : regioni) {
			System.out.println(
					"Balcone " + regione.getNome() + ": " + regione.getBalcone() + "[" + regione.getNome() + "]");
		}
		System.out.println("Balcone re: " + balconeRe + "[Re]");

		while (!ok) {
			String comando = stdIn.nextLine();
			if (comando.equals("Mare")) {
				ok = true;
				balconeScelto = regioni.get(0).getBalcone();
			} else if ("Collina".equals(comando)) {
				ok = true;
				balconeScelto = regioni.get(1).getBalcone();
			} else if ("Montagna".equals(comando)) {
				ok = true;
				balconeScelto = regioni.get(2).getBalcone();
			} else if ("Re".equals(comando)) {
				ok = true;
				balconeScelto = balconeRe;
			} else {
				System.out.println("il balcone scelto non è esistente! \nInserire di nuovo!");
			}
		}
		return balconeScelto;
	}

	/**
	 * this method let the player chose cards that hew want to use
	 * 
	 * @param carteGiocatore
	 * @param stdIn
	 * @return cards DTO selected
	 */
	@Override
	public List<CartaPoliticaDTO> scegliCarte(List<CartaPoliticaDTO> carteGiocatore) {
		List<CartaPoliticaDTO> cartePolitica = new ArrayList<>();
		int numeroCarte = 4;
		while (numeroCarte != 0) {
			numeroCarte--;
			System.out.println("Scegli il colore delle carte politica nella tua mano");
			System.out.println(carteGiocatore.toString());
			String comando = stdIn.nextLine();
			CartaPoliticaDTO cartaScelta = ControlloParametriDTO.carteGiocatore(comando, carteGiocatore);
			while (cartaScelta == null) {
				System.out.println("la carta selezionanata non è esistente!\n Inserire di nuovo");
				comando = stdIn.nextLine();
				cartaScelta = ControlloParametriDTO.carteGiocatore(comando, carteGiocatore);
			}
			cartePolitica.add(cartaScelta);
			if (numeroCarte != 0) {
				System.out.println("Vuoi aggiungere un'altra carta [Y/Other]");
				comando = stdIn.nextLine();
				if ("Y".equals(comando)) {
					continue;
				} else
					break;
			}
		}
		return cartePolitica;
	}

	/**
	 * this method let the player to chose a permit tile of the region selected
	 * 
	 * @param tessere
	 * @param stdIn
	 * @return the permit tile DTO selected
	 */
	@Override
	public TesseraPermessoDTO scegliTesseraRegione(List<TesseraPermessoDTO> tessere) {

		System.out.println("Seleziona tessera permesso[1/2]");
		for (TesseraPermessoDTO t : tessere)
			System.out.println(t.toString());
		String comando = stdIn.nextLine();
		while (!"1".equals(comando) && !"2".equals(comando)) {
			System.out.println("tessera selezionata non è esistente|\n Inserire di nuovo");
			comando = stdIn.nextLine();
		}

		return tessere.get(Integer.parseInt(comando) - 1);
	}

	/**
	 * this method let the player to chose a permit tile from his permit tiles
	 * not yet used
	 * 
	 * @param list
	 * @param stdIn
	 * @return the permit tile DTO selected
	 */
	@Override
	public TesseraPermessoDTO scegliTesseraGiocatore(List<TesseraPermessoDTO> list) {
		System.out.println("Seleziona l'indice di una tessera permesso non ancora usata");
		System.out.println(list.toString());
		String comando = stdIn.nextLine();
		TesseraPermessoDTO tesseraScelta = ControlloParametriDTO.tessereGiocatore(comando, list);
		while (tesseraScelta == null) {
			System.out.println("la tessera selezionanata non è esistente!\n Inserire di nuovo");
			comando = stdIn.nextLine();
			tesseraScelta = ControlloParametriDTO.tessereGiocatore(comando, list);
		}
		return tesseraScelta;
	}

	/**
	 * this method let the player chose a city where he want to build an
	 * emporium
	 * 
	 * @param città
	 * @param coloreGiocatore
	 * @param stdIn
	 * @return the city DTO selected
	 */
	@Override
	public CittàDTO scegliCittà(Set<? extends CittàDTO> città, ColoreDTO coloreGiocatore) {
		System.out.println("Seleziona la città");
		System.out.println(città.toString());
		String comando = stdIn.nextLine();
		CittàDTO cittàScelta = ControlloParametriDTO.città(comando, città, coloreGiocatore);
		while (cittàScelta == null) {
			System.out.println("la città selezionata non è corretta!\n Inserire di nuovo");
			comando = stdIn.nextLine();
			cittàScelta = ControlloParametriDTO.città(comando, città, coloreGiocatore);
		}
		return cittàScelta;
	}

	/**
	 * this method let the player to chose a politic card from the politic cards
	 * of his hand
	 * 
	 * @param cartePolitica
	 * @param stdIn
	 * @return the politic card DTO
	 */
	@Override
	public CittàDTO scegliCittàBonus(Set<CittàBonusDTO> città, ColoreDTO coloreGiocatore) {
		/*
		 * CittàDTO cittàScelta = ControlloParametriDTO.cittàBonus(input, città,
		 * coloreGiocatore); while (cittàScelta == null) { System.out.println(
		 * "la città selezionata non è corretta!\n Inserire di nuovo"); String
		 * comando = stdIn.nextLine(); cittàScelta =
		 * ControlloParametriDTO.cittàBonus(comando, città, coloreGiocatore); }
		 * return cittàScelta;
		 */
		return null;
	}

	@Override
	public CartaPoliticaDTO scegliCarta(List<CartaPoliticaDTO> cartePolitica) {
		System.out.println("Seleziona la carta politica");
		System.out.println(cartePolitica.toString());
		String comando = stdIn.nextLine();
		CartaPoliticaDTO cartaScelta = ControlloParametriDTO.carteGiocatore(comando, cartePolitica);
		while (cartaScelta == null) {
			System.out.println("la carta selezionata non è esistente!\n Inserire di nuovo");
			comando = stdIn.nextLine();
			cartaScelta = ControlloParametriDTO.carteGiocatore(comando, cartePolitica);
		}
		return cartaScelta;
	}

	/**
	 * this method let the player chose the price for an offer
	 * 
	 * @param stdIn
	 * @return the price
	 */
	@Override
	public int scegliPrezzo() {
		System.out.println("A quale prezzo?");
		String comando = stdIn.nextLine();
		while (!Utils.isNumeric(comando)) {
			System.out.println("inserire un numero");
			comando = stdIn.nextLine();
		}
		return Integer.parseInt(comando);
	}

	/**
	 * update the client after receiving a notify
	 */
	@Override
	public void notify(ClientNotify notify) {
		notify.update(gameStateDTO);
		try {
			notify.stamp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		CLI cli = new CLI();
		cli.start();
	}

}
