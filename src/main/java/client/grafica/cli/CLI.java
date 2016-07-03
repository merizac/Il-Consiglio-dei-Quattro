package client.grafica.cli;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import client.ControlloParametriDTO;
import client.connessione.Connessione;
import client.connessione.ConnessioneRMI;
import client.connessione.ConnessioneSocket;
import client.grafica.Grafica;
import common.azioniDTO.AzioneDTO;
import common.azioniDTO.AzioneMappaDTO;
import common.azioniDTO.AzioneParametri;
import common.azioniDTO.ChatDTO;
import common.azioniDTO.ExitDTO;
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
import utility.AzioneNonEseguibile;
import utility.Utils;

public class CLI implements Grafica {

	private static final String SOCKET = "1";
	private static final String RMI = "2";
	private Connessione connessione;
	private GameStateDTO gameStateDTO;
	private Scanner stdIn;
	private String inputLine;
	private final int timeout = 60000;
	private Timer timer;
	private TimerTask task;
	private static final Logger log = Logger.getLogger(CLI.class.getName());

	/**
	 * start game. set name and start connection
	 */
	public void start() {

		stdIn = new Scanner(System.in);

		this.scegliNome();

		Utils.print("CIAO " + gameStateDTO.getGiocatoreDTO().getNome().toUpperCase()
				+ ", BENVENUTO IN UNA NUOVA PARTITA DEL *Consiglio dei Quattro* !");

		this.connessione = scegliConnessione();

		try {
			this.connessione.setGrafica(this);
		} catch (RemoteException e) {
			log.log(Level.SEVERE, "Errore nel settare la grafica alla connessione", e);
		}

		try {
			this.connessione.setGameStateDTO(this.gameStateDTO);
		} catch (RemoteException e) {
			log.log(Level.SEVERE, "Errore nel settare il gamestate alla connessione", e);
		}
		try {
			this.connessione.start();
		} catch (RemoteException e) {
			log.log(Level.SEVERE, "Errore nella start della connessione", e);
		}

		while (true) {

			inputLine = stdIn.nextLine();

			AzioneDTO action = gameStateDTO.getAzioniDisponibili().stream()
					.filter(a -> a.toString().contains(inputLine)).findAny().orElse(null);

			if ("Exit".equals(inputLine)) {
				action = new ExitDTO();
			}

			else if ("Chat".equals(inputLine)) {
				Utils.print("Inserisci il messaggio");
				inputLine = stdIn.nextLine();
				action = new ChatDTO();
				((ChatDTO) action).setMessaggio(inputLine);
			}

			else if (action == null)
				Utils.print("L'azione non esiste \nInserire un'azione valida");

			if (action instanceof AzioneParametri)
				try {
					((AzioneParametri) action).parametri().setParametri(this, gameStateDTO);
				} catch (AzioneNonEseguibile e) {
					log.log(Level.INFO, "Azione non eseguibile", e);
					this.mostraMessaggio(e.getMessage());
					continue;
				}
			try {
				connessione.inviaAzione(action);
				task.cancel();
				timer.cancel();

			} catch (RemoteException e) {
				log.log(Level.SEVERE, "Errore nell'invio dell'azione al server", e);

			}

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
			Utils.print("Inserisci il nome");
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
	public Connessione scegliConnessione() {
		Utils.print("Inserisci connessione\nSocket[1]\nRMI[2]");
		String connessioneClient;
		while (true) {
			{
				connessioneClient = stdIn.nextLine();
				if (SOCKET.equals(connessioneClient)) {
					return new ConnessioneSocket();

				} else if (RMI.equals(connessioneClient))
					try {
						return new ConnessioneRMI();
					} catch (RemoteException e) {
						log.log(Level.SEVERE, "Errore nella creazione della connessione RMI", e);
					}
				else
					Utils.print("Inserisci un valore valido");
			}
		}
	}

	/**
	 * show the actions available
	 */
	@Override
	public void mostraAzioni(List<AzioneDTO> azioni) {

		timer = new Timer();
		task = new TimerTask() {

			@Override
			public void run() {
				try {
					connessione.inviaAzione(new ExitDTO());
				} catch (RemoteException e) {
					log.log(Level.SEVERE, "Errore nell'invio dell'azione al server", e);
				}
			}
		};
		timer.schedule(task, timeout);

		for (AzioneDTO a : azioni) {
			Utils.print(a.toString());
		}
	}

	/**
	 * show the placement of the players
	 */
	@Override
	public void mostraClassifica(List<GiocatoreDTO> vincenti, List<GiocatoreDTO> perdenti) {
		for (GiocatoreDTO g : vincenti) {
			Utils.print(
					"Giocatore :" + g.getNome().toUpperCase() + " Punteggio " + g.getPunteggioVittoria() + " punti");
		}
		for (GiocatoreDTO g : perdenti) {
			Utils.print(
					"Giocatore :" + g.getNome().toUpperCase() + " Punteggio " + g.getPunteggioVittoria() + " punti");
		}
	}

	/**
	 * show the game
	 */
	@Override
	public void mostraGame(GameStateDTO gameStateDTO) {
		Utils.print("\nCittà");
		for (CittàDTO c : gameStateDTO.getCittà()) {
			Utils.print(c.toString());
		}
		Utils.print("\nRegioni");
		for (RegioneDTO r : gameStateDTO.getRegioni()) {
			Utils.print(r.toString());
			String balcone = "Balcone [ ";
			for (ConsigliereDTO cons : r.getBalcone().getConsiglieri()) {
				balcone = balcone + cons + " ";
			}
			balcone = balcone + "]";
			Utils.print(balcone);
			Utils.print("Tessere Permesso Scoperte nella regione " + r.getNome());
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
				Utils.print("Tessera [" + cittàTessera + bonusTessera + " ]");

			}
			Utils.print("BonusRegione [" + r.getBonusRegione() + " ]");

		}
		String balconeRe = "Balcone Re [ ";
		for (ConsigliereDTO consRe : gameStateDTO.getPlanciaReDTO().getBalconeRe().getConsiglieri()) {
			balconeRe = balconeRe + consRe + " ";
		}
		balconeRe = balconeRe + "]";
		Utils.print(balconeRe);
		Utils.print(gameStateDTO.getPedinaRE().toString());

		String bonusRe = "BonusRe\n";

		for (Bonus b : gameStateDTO.getPlanciaReDTO().getBonusPremioRe()) {
			bonusRe = bonusRe + b + "\n";
		}
		Utils.print(bonusRe);
		String riserva = "Consiglieri [ ";
		for (ConsigliereDTO c : gameStateDTO.getConsiglieri()) {
			riserva = riserva + c.getColoreConsigliere() + " ";
		}
		riserva = riserva + "]";
		Utils.print(riserva);

	}

	/**
	 * show the currant state of the player
	 */
	@Override
	public void mostraGiocatore(GiocatoreDTO giocatoreDTO) {
		Utils.print(giocatoreDTO.toString());
	}

	/**
	 * show a message from the server
	 */
	@Override
	public void mostraMessaggio(String messaggio) {
		Utils.print(messaggio);
	}

	/**
	 * show object in sale in the market state
	 */
	@Override
	public void mostraOfferte(List<OffertaDTO> offerte) {
		int i = 1;
		for (OffertaDTO o : offerte) {
			Utils.print("\n" + o.getMarketableDTO() + " prezzo: " + o.getPrezzo() + " [" + i + "]");
			i++;
		}
	}

	/**
	 * choose offer
	 */
	@Override
	public int scegliOfferta(List<OffertaDTO> offerte) {
		Utils.print("Che cosa vuoi acquistare?");
		String comando = stdIn.nextLine();
		boolean ok = false;
		while (!ok) {

			while (!Utils.isNumeric(comando)) {
				Utils.print("Inserire numero offerta valido");
				comando = stdIn.nextLine();
			}

			if (Integer.parseInt(comando) > 0 && Integer.parseInt(comando) <= offerte.size())
				ok = true;
		}

		return Integer.parseInt(comando);

	}

	/**
	 * player choose which object want to buy
	 */
	@Override
	public MarketableDTO scegliMarketable() {
		Utils.print("Cosa vuoi vendere?\n");
		Utils.print("Aiutante[1]\nCarta Politica[2]\nTesseraPermesso[3]");
		String comando = stdIn.nextLine();
		boolean ok = false;
		while (!ok) {

			while (!Utils.isNumeric(comando)) {
				Utils.print("valore non valido");
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
		Utils.print("scegli consigliere");
		Utils.print(consiglieri.toString());
		String comando = stdIn.nextLine();
		ConsigliereDTO consigliereScelto = ControlloParametriDTO.consiglieri(comando, consiglieri);
		while (consigliereScelto == null) {
			Utils.print("consigliere non esistente, inserire un valore valido");
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

		Utils.print("Scegli la regione");
		Utils.print(regioni.toString());
		String comando = stdIn.nextLine();
		RegioneDTO regioneScelta = ControlloParametriDTO.regioni(comando, regioni);
		while (regioneScelta == null) {
			Utils.print("la regione selezionata non è esistente! \nInserire di nuovo:");
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
		Utils.print("Scegli il balcone");
		for (RegioneDTO regione : regioni) {
			Utils.print("Balcone " + regione.getNome() + ": " + regione.getBalcone() + "[" + regione.getNome() + "]");
		}
		Utils.print("Balcone re: " + balconeRe + "[Re]");

		while (!ok) {
			String comando = stdIn.nextLine();
			if ("Mare".equals(comando)) {
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
				Utils.print("il balcone scelto non è esistente! \nInserire di nuovo!");
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
			Utils.print("Scegli il colore delle carte politica nella tua mano");
			Utils.print(carteGiocatore.toString());
			String comando = stdIn.nextLine();
			CartaPoliticaDTO cartaScelta = ControlloParametriDTO.carteGiocatore(comando, carteGiocatore);
			while (cartaScelta == null) {
				Utils.print("la carta selezionanata non è esistente!\nInserire di nuovo");
				comando = stdIn.nextLine();
				cartaScelta = ControlloParametriDTO.carteGiocatore(comando, carteGiocatore);
			}
			cartePolitica.add(cartaScelta);
			if (numeroCarte != 0) {
				Utils.print("Vuoi aggiungere un'altra carta [Y/Other]");
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
	public TesseraPermessoDTO scegliTesseraRegione(List<TesseraPermessoDTO> tessere, RegioneDTO regioneDTO) {

		Utils.print("Seleziona tessera permesso[1/2]");
		for (TesseraPermessoDTO t : tessere)
			Utils.print(t.toString());
		String comando = stdIn.nextLine();
		while (!"1".equals(comando) && !"2".equals(comando)) {
			Utils.print("tessera selezionata non è esistente|\n Inserire di nuovo");
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
		Utils.print("Seleziona l'indice di una tessera permesso non ancora usata");
		Utils.print(list.toString());
		String comando = stdIn.nextLine();
		TesseraPermessoDTO tesseraScelta = ControlloParametriDTO.tessereGiocatore(comando, list);
		while (tesseraScelta == null) {
			Utils.print("la tessera selezionata non è esistente!\n Inserire di nuovo");
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
		Utils.print("Seleziona la città");
		Utils.print(città.toString());
		String comando = stdIn.nextLine();
		CittàDTO cittàScelta = ControlloParametriDTO.città(comando, città, coloreGiocatore);
		while (cittàScelta == null) {
			Utils.print("la città selezionata non è corretta!\n Inserire di nuovo");
			comando = stdIn.nextLine();
			cittàScelta = ControlloParametriDTO.città(comando, città, coloreGiocatore);
		}
		return cittàScelta;
	}

	/**
	 * choose city with bonus
	 * @param città
	 * @param coloreGiocatore
	 * @param input
	 * @return
	 */
	private CittàDTO scegliCittàBonus(Set<CittàBonusDTO> città, ColoreDTO coloreGiocatore, String input) {

		CittàDTO cittàScelta = ControlloParametriDTO.cittàBonus(input, città, coloreGiocatore);
		while (cittàScelta == null) {
			Utils.print("la città selezionata non è corretta!\n Inserire di nuovo");
			String comando = stdIn.nextLine();
			cittàScelta = ControlloParametriDTO.cittàBonus(comando, città, coloreGiocatore);
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
	public CartaPoliticaDTO scegliCarta(List<CartaPoliticaDTO> cartePolitica) {
		Utils.print("Seleziona la carta politica");
		Utils.print(cartePolitica.toString());
		String comando = stdIn.nextLine();
		CartaPoliticaDTO cartaScelta = ControlloParametriDTO.carteGiocatore(comando, cartePolitica);
		while (cartaScelta == null) {
			Utils.print("la carta selezionata non è esistente!\n Inserire di nuovo");
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
		Utils.print("A quale prezzo?");
		String comando = stdIn.nextLine();
		while (!Utils.isNumeric(comando)) {
			Utils.print("inserire un numero");
			comando = stdIn.nextLine();
		}
		return Integer.parseInt(comando);
	}

	/**
	 * show players that aren't the current player
	 */
	@Override
	public void mostraAvversario(GiocatoreDTO avversario) {
		Utils.print("\nGiocatore :" + avversario.getNome());
		Utils.print("Punti vittoria " + avversario.getPunteggioVittoria());
		Utils.print("Punti ricchezza " + avversario.getPunteggioRicchezza());
		Utils.print("Punti nobiltà " + avversario.getPunteggioNobiltà());
		Utils.print("Empori " + avversario.getEmpori());
		Utils.print("Aiutanti " + avversario.getAiutanti());
		Utils.print("Tessere permesso " + avversario.getTesserePermesso() + "\n");
	}

	/**
	 * start cli
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		CLI cli = new CLI();
		cli.start();
	}

	/**
	 * choose one city with bonus where the player has build. for interactive
	 * nobility bonus
	 */
	@Override
	public List<CittàBonusDTO> scegliUnaCittà() {

		Set<CittàBonusDTO> città = new HashSet<>();
		for (CittàDTO c : gameStateDTO.getCittà()) {
			if (c.getEmpori().contains(gameStateDTO.getGiocatoreDTO().getColoreGiocatore().getColore())
					&& (c instanceof CittàBonusDTO)) {
				città.add((CittàBonusDTO) c);
			}
		}

		Utils.print("Scegli una città");
		Utils.print(città.toString());
		inputLine = stdIn.nextLine();
		CittàDTO cittàScelta = this.scegliCittàBonus(città, gameStateDTO.getGiocatoreDTO().getColoreGiocatore(),
				inputLine);
		List<CittàBonusDTO> cittàb = new ArrayList<>();
		cittàb.add((CittàBonusDTO) cittàScelta);

		return cittàb;

	}

	/**
	 * choose two city with bonus where the player has build. bonus can not be
	 * the same. for interactive bonus of nobility track
	 */
	@Override
	public List<CittàBonusDTO> scegliDueCittà() {

		Set<CittàBonusDTO> città = new HashSet<>();
		for (CittàDTO c : gameStateDTO.getCittà()) {
			if (c.getEmpori().contains(gameStateDTO.getGiocatoreDTO().getColoreGiocatore().getColore())
					&& (c instanceof CittàBonusDTO)) {
				Utils.print(c.toString());
				città.add((CittàBonusDTO) c);
			}
		}

		Utils.print("Scegli una città");
		inputLine = stdIn.nextLine();
		List<CittàBonusDTO> cittàb = new ArrayList<>();
		for (int i = 0; i < 2; i++) {
			inputLine = stdIn.nextLine();
			CittàDTO cittàScelta = this.scegliCittàBonus(città, gameStateDTO.getGiocatoreDTO().getColoreGiocatore(),
					inputLine);
			cittàb.add((CittàBonusDTO) cittàScelta);
			Utils.print("Scegli un'altra città con gettone dei bonus diverso dalla prima");
		}

		return cittàb;
	}

	/**
	 * notify start of market state
	 */
	@Override
	public void startMarket() {
		Utils.print("Il market è iniziato!\n");
	}

	@Override
	public void mostraGiocatoreMarket(GiocatoreDTO giocatoreDTO) {
		// TODO Auto-generated method stub

	}

	/**
	 * notify the end of market state
	 */
	@Override
	public void fineMarket() {
		Utils.print("Il market è finito!\n");
	}

	/**
	 * choose map for start game
	 */
	@Override
	public void scegliMappa() {
		Utils.print("Scelta mappa1");
		try {
			connessione.inviaAzione(new AzioneMappaDTO("mappa1"));
		} catch (RemoteException e) {
			log.log(Level.SEVERE, "Errore nell'invio dell'azione al server", e);
		}
	}

	/**
	 * player can choose if win bonus from one of his discovery or covery permit
	 * tile. For interactive bonus in nobility track
	 */
	@Override
	public TesseraPermessoDTO scegliTesseraPermessoUsataONonUsata(List<TesseraPermessoDTO> tessere,
			List<TesseraPermessoDTO> tessereUsate) {
		TesseraPermessoDTO tesseraPermessoDTO;

		Utils.print("Vuoi prendere i bonus di una tessera già usata [1] o di una scoperta [2] ?");

		boolean b = false;
		if (gameStateDTO.getGiocatoreDTO().getTesserePermesso().isEmpty()) {
			inputLine = "1";
		}
		if (gameStateDTO.getGiocatoreDTO().getTesserePermessoUsate().isEmpty()) {
			inputLine = "2";
		} else {
			inputLine = stdIn.nextLine();
			while (!b) {
				while (!Utils.isNumeric(inputLine)) {
					Utils.print("valore non valido");
					inputLine = stdIn.nextLine();
				}
				if (Integer.parseInt(inputLine) == 1 || Integer.parseInt(inputLine) == 2) {
					b = true;
				}
			}
		}

		if ("1".equals(inputLine))
			tesseraPermessoDTO = this.scegliTesseraGiocatore(tessereUsate);
		else
			tesseraPermessoDTO = this.scegliTesseraGiocatore(tessere);

		return tesseraPermessoDTO;
	}

	/**
	 * shoe message of market
	 */
	@Override
	public void mostraMessaggioMarket(String messaggio) {
		mostraMessaggio(messaggio);
	}
}
