package server.model.game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;
import server.model.bonus.Bonus;
import server.model.bonus.BonusAiutanti;
import server.model.bonus.BonusAzionePrincipale;
import server.model.bonus.BonusCartePolitica;
import server.model.bonus.BonusGettoneRicompensa;
import server.model.bonus.BonusMoneta;
import server.model.bonus.BonusPuntiNobiltà;
import server.model.bonus.BonusPuntiVittoria;
import server.model.bonus.BonusTesseraAcquistata;
import server.model.bonus.BonusTesseraPermesso;
import utility.Utils;

public class Reader {

	private List<Consigliere> consiglieri;
	private List<Regione> regioni;
	private List<Città> cities;

	public Reader() {
		this.consiglieri = new ArrayList<>();
		this.regioni = new ArrayList<>();
		this.cities = new ArrayList<>();
	}

	public PlanciaRe creazionePlanciaRe() throws IOException {
		List<Bonus> bonusRe = letturaBonusRe();
		Balcone balconeRe = new Balcone(4, consiglieri);
		PlanciaRe planciaRe = new PlanciaRe(balconeRe, bonusRe, letturaPunteggioNobiltà());

		return planciaRe;
	}

	/**
	 * create king
	 * 
	 * @return
	 */
	public Re creazioneRe() {
		Re re = new Re(findCittàRe());
		return re;
	}

	/**
	 * costruiscre la mappa e mette le tessere permesso alle regioni
	 * 
	 * @param configurazione
	 * @return
	 * @throws IOException
	 */
	public Mappa creazioneMappa(String configurazione) throws IOException {
		creazioneCittà(configurazione);
		Mappa mappa = new Mappa(new HashSet<>(cities));
		letturaTesserePermesso(cities, regioni);
		return mappa;
	}

	/**
	 * crea città e gli mette i bonus rotondi
	 * 
	 * @param configurazione
	 * @throws IOException
	 */
	public void creazioneCittà(String configurazione) throws IOException {
		cities = letturaCittà(configurazione);
		letturaBonusTondiCittà();

	}

	public Mazzo<CartaPolitica> letturaCartePolitica() throws IOException {

		List<CartaPolitica> cartaPoliticaList = new ArrayList<>();
		FileReader cartaPolitica = new FileReader("src/main/resources/coloriConsiglieriCartePolitica.txt");
		BufferedReader b;
		b = new BufferedReader(cartaPolitica);
		String stringaLetta;
		int quantità;
		Colore colore = null;
		stringaLetta = b.readLine();

		while (true) {
			if (stringaLetta == null)
				break;
			StringTokenizer st = new StringTokenizer(stringaLetta);

			String tmp = st.nextToken();
			if ("Colore".equals(tmp)) {
				colore = new Colore(st.nextToken());
			}

			if ("CartePolitica".equals(tmp)) {
				quantità = Integer.parseInt(st.nextToken());
				for (int i = 0; i < quantità; i++) {
					CartaPolitica carta = new CartaPolitica(colore);
					cartaPoliticaList.add(carta);
				}
			}
			stringaLetta = b.readLine();
		}
		b.close();
		cartaPolitica.close();
		Collections.shuffle(cartaPoliticaList);
		return new Mazzo<>(cartaPoliticaList);
	}

	public List<PunteggioNobiltà> letturaPunteggioNobiltà() throws NumberFormatException, IOException {

		FileReader punteggioNobiltà = new FileReader("src/main/resources/punteggioNobiltà.txt");
		BufferedReader b;
		b = new BufferedReader(punteggioNobiltà);
		String stringaLetta;
		int lunghezza = Integer.parseInt(b.readLine());
		List<PunteggioNobiltà> nobiltà = new ArrayList<>(lunghezza);

		for (int i = 0; i < lunghezza; i++) {
			List<Bonus> bonus = new ArrayList<>();
			stringaLetta = b.readLine();

			if (!"no".equals(stringaLetta)) {
				StringTokenizer st = new StringTokenizer(stringaLetta);
				while (st.hasMoreTokens()) {
					String tmp = st.nextToken();
					if ("BonusAiutanti".equals(tmp)) {
						int quantità = Integer.parseInt(st.nextToken());
						bonus.add(new BonusAiutanti(quantità));
					} else if ("BonusGettoneRicompensa".equals(tmp)) {
						int quantità = Integer.parseInt(st.nextToken());
						bonus.add(new BonusGettoneRicompensa(quantità));
					} else if ("BonusTesseraPermesso".equals(tmp)) {
						bonus.add(new BonusTesseraPermesso());
					} else if ("BonusTesseraPermessoUsata".equals(tmp)) {
						bonus.add(new BonusTesseraAcquistata());
					} else if ("BonusAzionePrincipale".equals(tmp)) {
						int quantità = Integer.parseInt(st.nextToken());
						bonus.add(new BonusAzionePrincipale(quantità));
					} else if ("BonusPuntiVittoria".equals(tmp)) {
						int quantità = Integer.parseInt(st.nextToken());
						bonus.add(new BonusPuntiVittoria(quantità));
					} else if ("BonusCartePolitica".equals(tmp)) {
						int quantità = Integer.parseInt(st.nextToken());
						bonus.add(new BonusCartePolitica(quantità));
					} else if ("BonusMoneta".equals(tmp)) {
						int quantità = Integer.parseInt(st.nextToken());
						bonus.add(new BonusCartePolitica(quantità));
					}
				}
			}
			nobiltà.add(new PunteggioNobiltà(i, bonus));
		}
		b.close();
		punteggioNobiltà.close();
		return nobiltà;
	}

	public List<Consigliere> letturaConsigliere() throws IOException {

		FileReader cons = new FileReader("src/main/resources/coloriConsiglieriCartePolitica.txt");

		BufferedReader b;
		b = new BufferedReader(cons);
		String stringaLetta;
		int quantità;
		Colore colore = null;
		stringaLetta = b.readLine();

		while (true) {
			if (stringaLetta == null)
				break;
			StringTokenizer st = new StringTokenizer(stringaLetta);

			String tmp = st.nextToken();
			if ("Colore".equals(tmp)) {
				colore = new Colore(st.nextToken());
			}

			if ("Consiglieri".equals(tmp)) {
				quantità = Integer.parseInt(st.nextToken());
				for (int i = 0; i < quantità; i++) {
					Consigliere consigliere = new Consigliere(colore);
					consiglieri.add(consigliere);
				}
			}
			stringaLetta = b.readLine();
		}
		b.close();
		cons.close();
		Collections.shuffle(consiglieri);
		return consiglieri;
	}

	public List<Regione> letturaRegioni() throws IOException {

		FileReader reg = new FileReader("src/main/resources/regioni.txt");
		BufferedReader b;
		b = new BufferedReader(reg);
		String stringaLetta;

		while (true) {
			stringaLetta = b.readLine();
			if (stringaLetta == null)
				break;
			StringTokenizer st = new StringTokenizer(stringaLetta);
			while (st.hasMoreTokens()) {
				String nomeregione = st.nextToken();
				int nbonus = 0;
				if (st.hasMoreTokens())
					nbonus = Integer.parseInt(st.nextToken());
				Mazzo<TesseraPermesso> mazzo = new Mazzo<>();
				Balcone balcone = new Balcone(4, consiglieri);
				BonusPuntiVittoria bonusPuntiVittoria = new BonusPuntiVittoria(nbonus);
				Regione regione = new Regione(nomeregione, mazzo, bonusPuntiVittoria, balcone);
				regioni.add(regione);
			}
		}
		b.close();
		reg.close();
		return regioni;
	}

	public List<Città> letturaCittà(String configurazione) throws IOException {

		List<Colore> coloriCittà = new ArrayList<>();
		FileReader città = new FileReader("src/main/resources/" + configurazione + "Città.txt");
		BufferedReader b;
		b = new BufferedReader(città);
		String stringaLetta;
		stringaLetta = b.readLine();

		// Creo coloricittà salvati in un arraylist
		while (!"CITTA".equals(stringaLetta)) {
			StringTokenizer st = new StringTokenizer(stringaLetta);
			String colore = st.nextToken();
			ColoreCittà colorecittà;
			ColoreRe coloreRe;
			int puntiBonus;
			if (!"Re".equals(colore)) {
				if (st.hasMoreTokens()) {
					puntiBonus = Integer.parseInt(st.nextToken());
					colorecittà = new ColoreCittà(colore, new BonusPuntiVittoria(puntiBonus));
					coloriCittà.add(colorecittà);
				}
			} else {
				coloreRe = new ColoreRe(colore);
				coloriCittà.add(coloreRe);
			}
			stringaLetta = b.readLine();
		}

		// Ciclo le regioni poi i colori e setto la città
		for (Regione regione : regioni) {
			String numero = b.readLine();
			if (Utils.isNumeric(numero)) {
				int numerocittà = Integer.parseInt(numero);
				for (int i = 0; i < numerocittà; i++) {
					String tmp = b.readLine();
					StringTokenizer st = new StringTokenizer(tmp);
					while (st.hasMoreTokens()) {
						String nome = st.nextToken();
						String col = st.nextToken();
						for (Colore color : coloriCittà) {
							if (col.equals(color.getColore())) {
								if (!col.equals("Re")) {
									List<Bonus> bonus = new ArrayList<>();
									ColoreCittà colore = (ColoreCittà) color;
									CittàBonus c = new CittàBonus(nome, regione, colore, bonus);
									cities.add(c);
								} else {
									ColoreRe colore = (ColoreRe) color;
									Città c = new Città(nome, regione, colore);
									cities.add(c);
								}
							}

						}
					}
				}
			}
		}
		String stringa = b.readLine();
		if (stringa != null) {
			if ("CITTACOLLEGATE".equals(stringa)) {
				for (Città c : cities) {
					StringTokenizer st = new StringTokenizer(b.readLine());
					if (st.hasMoreTokens()) {
						String nome = st.nextToken();
						if (nome.equals(c.getNome())) {
							while (st.hasMoreTokens()) {
								String cittàCollegata = st.nextToken();
								Città cittàToFind = findCittà(cittàCollegata);
								c.getCittàCollegate().add(cittàToFind);
							}
						} else
							continue;
					}
				}
			}
		}

		b.close();
		città.close();
		return cities;
	}

	public List<Bonus> letturaBonusRe() throws IOException {

		List<Bonus> bonusRe = new ArrayList<>();

		FileReader bonus = new FileReader("src/main/resources/bonusRe.txt");
		BufferedReader b;
		b = new BufferedReader(bonus);

		while (true) {
			String letta = b.readLine();
			if (letta == null)
				break;
			bonusRe.add(new BonusPuntiVittoria(Integer.parseInt(letta)));
		}
		b.close();
		bonus.close();
		return bonusRe;
	}

	public void letturaBonusTondiCittà() throws IOException {

		List<List<Bonus>> listaBonusTondi = new ArrayList<>();
		FileReader bonusDelleCittà = new FileReader("src/main/resources/bonusDelleCittà.txt");
		BufferedReader b;
		b = new BufferedReader(bonusDelleCittà);
		String stringaLetta;

		while (true) {
			// legge riga e controlla ci sia scritto qualcosa
			stringaLetta = b.readLine();
			if (stringaLetta == null)
				break;

			// spezzo la riga letta in token e leggo nome bonus e intero per i
			// bonus di cui ne ho bisogno
			StringTokenizer st = new StringTokenizer(stringaLetta);
			List<Bonus> bonus = new ArrayList<>();
			int quantità;

			while (st.hasMoreTokens()) {

				String nomeBonus = st.nextToken();

				if ("BonusCartePolitica".equals(nomeBonus)) {
					quantità = Integer.parseInt(st.nextToken());
					bonus.add(new BonusCartePolitica(quantità));
				} else if ("BonusMoneta".equals(nomeBonus)) {
					quantità = Integer.parseInt(st.nextToken());
					bonus.add(new BonusMoneta(quantità));
				} else if ("BonusPuntiNobiltà".equals(nomeBonus)) {
					quantità = Integer.parseInt(st.nextToken());
					bonus.add(new BonusPuntiNobiltà(quantità));
				} else if ("BonusAiutanti".equals(nomeBonus)) {
					quantità = Integer.parseInt(st.nextToken());
					bonus.add(new BonusAiutanti(quantità));
				} else if ("BonusPuntiVittoria".equals(nomeBonus)) {
					quantità = Integer.parseInt(st.nextToken());
					bonus.add(new BonusPuntiVittoria(quantità));
				}
			}
			listaBonusTondi.add(bonus);

		}
		b.close();
		bonusDelleCittà.close();
		Collections.shuffle(listaBonusTondi);
		for (Città c : cities) {
			if (c instanceof CittàBonus && !listaBonusTondi.isEmpty()) {
				((CittàBonus) c).setBonus(listaBonusTondi.remove(0));
			}

		}
	}

	public void letturaTesserePermesso(List<Città> cities, List<Regione> regioni) throws IOException {

		FileReader t = new FileReader("src/main/resources/tesseraPermesso.txt");
		BufferedReader b;
		b = new BufferedReader(t);
		String stringaLetta;
		stringaLetta = b.readLine();

		while (stringaLetta != null) {

			for (Regione r : regioni) {
				if (stringaLetta == null)
					break;
				while (!"FINEREGIONE".equals(stringaLetta)) {
					StringTokenizer st = new StringTokenizer(stringaLetta);
					List<Città> cit = new ArrayList<>();
					// aggiunge le citta all'arraylist
					while (st.hasMoreTokens()) {
						cit.add(findCittà(st.nextToken()));
					}

					// aggiunge i bonus all'arraylist
					stringaLetta = b.readLine();
					List<Bonus> bonus = new ArrayList<>();
					StringTokenizer str = new StringTokenizer(stringaLetta);
					int quantità;

					String tmp = str.nextToken();
					while (true) {
						if ("BonusAiutanti".equals(tmp)) {
							quantità = Integer.parseInt(str.nextToken());
							bonus.add(new BonusAiutanti(quantità));
						} else if ("BonusAzionePrincipale".equals(tmp)) {
							quantità = Integer.parseInt(str.nextToken());
							bonus.add(new BonusAzionePrincipale(quantità));
						} else if ("BonusMoneta".equals(tmp)) {
							quantità = Integer.parseInt(str.nextToken());
							bonus.add(new BonusMoneta(quantità));
						} else if ("BonusPuntiVittoria".equals(tmp)) {
							quantità = Integer.parseInt(str.nextToken());
							bonus.add(new BonusPuntiVittoria(quantità));
						} else if ("BonusCartePolitica".equals(tmp)) {
							quantità = Integer.parseInt(str.nextToken());
							bonus.add(new BonusCartePolitica(quantità));
						} else if ("BonusPuntiNobiltà".equals(tmp)) {
							quantità = Integer.parseInt(str.nextToken());
							bonus.add(new BonusPuntiNobiltà(quantità));
						}
						if (str.hasMoreTokens())
							tmp = str.nextToken();
						else
							break;
					}

					new TesseraPermesso(cit, bonus, r);
					stringaLetta = b.readLine();
				}
				stringaLetta = b.readLine();
				r.getMazzoTesserePermesso().mescolaCarte();
				r.getTesserePermessoScoperte().add(r.getMazzoTesserePermesso().pescaCarte());
				r.getTesserePermessoScoperte().add(r.getMazzoTesserePermesso().pescaCarte());
			}
		}
		b.close();
		t.close();
	}

	public Città findCittà(String c) {
		for (Città i : cities) {
			if (i.getNome().equals(c)) {

				return i;
			}
		}
		return null;
	}

	// manca eccezione
	public Regione findRegione(String c) {
		for (Regione i : regioni) {
			if (i.getNome().equals(c)) {

				return i;
			}
		}
		return null;
	}

	public Città findCittàRe() {
		for (Città c : cities) {
			if ("Re".equals(c.getColoreCittà().getColore())) {
				return c;
			}
		}
		return null;
	}

}