package gameDTO.azioniDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import bonus.Bonus;
import bonus.BonusPuntiNobiltà;
import game.Balcone;
import game.CartaPolitica;
import game.Città;
import game.CittàBonus;
import game.Colore;
import game.Consigliere;
import game.Giocatore;
import game.Regione;
import game.TesseraPermesso;
import game.market.Offerta;
import gameDTO.gameDTO.BalconeDTO;
import gameDTO.gameDTO.CartaPoliticaDTO;
import gameDTO.gameDTO.CittàBonusDTO;
import gameDTO.gameDTO.CittàDTO;
import gameDTO.gameDTO.ConsigliereDTO;
import gameDTO.gameDTO.GiocatoreDTO;
import gameDTO.gameDTO.RegioneDTO;
import gameDTO.gameDTO.TesseraPermessoDTO;

public class ControlloParametri {

	public static Regione cercaRegione(RegioneDTO regione, List<Regione> list) throws IllegalArgumentException {
		for (Regione r : list) {
			if (r.getNome().equals(regione.getNome()))
				return r;
		}
		throw new IllegalArgumentException("La regione è inesistente!");
	}

	public static Balcone cercaBalcone(BalconeDTO balconeDTO, Balcone balconeRe, List<Regione> regioni) {
		for (Regione r : regioni) {
			if (controlloBalcone(balconeDTO, r.getBalcone())) {
				return r.getBalcone();
			}
		}
		if (controlloBalcone(balconeDTO, balconeRe)) {
			return balconeRe;
		} else {
			throw new IllegalArgumentException("Il balcone è inesistente!");
		}
	}

	private static boolean controlloBalcone(BalconeDTO balconeDTO, Balcone balcone) {
		List<Consigliere> consiglieri = new ArrayList<>(balcone.getConsigliere());
		for (int i = 0; i < balconeDTO.getConsiglieri().size(); i++) {
			if (!balconeDTO.getConsiglieri().get(i).getColoreConsigliere()
					.equals(consiglieri.get(i).getColore().getColore()))
				return false;
		}
		return true;
	}

	public static List<CartaPolitica> cercaCartePolitica(List<CartaPoliticaDTO> carte,
			List<CartaPolitica> cartePolitica) throws IllegalArgumentException {

		List<CartaPolitica> carteGiocatore = new ArrayList<>();

		for (CartaPoliticaDTO c : carte) {
			if (cartePolitica.contains(new CartaPolitica(new Colore(c.getColore())))) {
				for (CartaPolitica cp : cartePolitica) {
					if (cp.getColore().getColore().equals(c.getColore())) {
						cartePolitica.remove(cp);
						carteGiocatore.add(cp);
						break;
					}
				}
			} else
				throw new IllegalArgumentException("Una delle carte passate è inesistente!");
		}
		System.out.println(carteGiocatore);
		return carteGiocatore;
	}

	public static Città cercaCittà(CittàDTO città, Set<Città> cittàGameState) throws IllegalArgumentException {
		for (Città c : cittàGameState) {
			if (c.getNome().equals(città.getNome()))
				return c;
		}
		throw new IllegalArgumentException("La città è inesistente!");
	}

	public static Città cercaCittàBonus(CittàDTO città, Set<Città> cittàGameState) throws IllegalArgumentException {
		if (città instanceof CittàBonusDTO) {
			System.out.println("test bonus :" + (((CittàBonusDTO) città).getBonus()));
			System.out.println("test bonus :" + (((CittàBonusDTO) città).getBonus()));
		}
		for (Città c : cittàGameState) {
			if (c.getNome().equals(città.getNome()) && (c instanceof CittàBonus)) {
				for (Bonus b : ((CittàBonus) c).getBonus()) {
					if (!(b instanceof BonusPuntiNobiltà))
						continue;
				}
				// else throw new IllegalArgumentException("Il gettone
				// ricompensa della città non può contenere un bonus avanzamento
				// nel Percorso Nobiltà!");
				return c;
			}

			// else throw new IllegalArgumentException("La città scelta non ha
			// gettone ricompensa!");
		}
		throw new IllegalArgumentException("La città è inesistente!");
	}

	/**
	 * 
	 * @param tesseraPermesso
	 * @param tesserePermesso
	 * @return tessera permesso non DTO
	 * @throws IllegalArgumentException
	 */
	public static TesseraPermesso cercaTesseraPermesso(TesseraPermessoDTO tesseraPermesso,
			List<TesseraPermesso> tesserePermesso) throws IllegalArgumentException {
		List<CittàDTO> cittàTesseraDTO = new ArrayList<>(tesseraPermesso.getCittà());
		ordinaCittàDTO(cittàTesseraDTO);

		for (TesseraPermesso t : tesserePermesso) {
			List<Città> cittàTessera = new ArrayList<>(t.getCittà());
			ordinaCittà(cittàTessera);
			if (tesseraPermesso.getBonus().size() == t.getBonus().size()
					&& tesseraPermesso.getCittà().size() == cittàTessera.size()
					&& stessiBonus(tesseraPermesso.getBonus(), t.getBonus())
					&& stesseCittà(cittàTesseraDTO, cittàTessera))
				return t;
		}

		throw new IllegalArgumentException("la tessera permesso è inesistente!");

	}

	private static void ordinaCittà(List<Città> cittàTessera) {
		Collections.sort(cittàTessera, new Comparator<Città>() {

			@Override
			public int compare(Città o1, Città o2) {
				return o1.getNome().compareToIgnoreCase(o2.getNome());
			}

		});

	}

	private static void ordinaCittàDTO(List<CittàDTO> città) {
		Collections.sort(città, new Comparator<CittàDTO>() {

			@Override
			public int compare(CittàDTO o1, CittàDTO o2) {
				return o1.getNome().compareToIgnoreCase(o2.getNome());
			}

		});

	}

	/**
	 * This method check if bonus on tesseraPermesso DTO are the same of bonus
	 * on tesseraPermesso no DTO. IT's suppose that the size of two arrays is
	 * the same, because it's already check on 'cercaTesserePermesso'
	 * 
	 * @param bonusTesseraDTO
	 * @param bonus
	 * @return true if bonus check, false if are not the same
	 */
	private static boolean stessiBonus(List<Bonus> bonusTesseraDTO, List<Bonus> bonus) {
		int i;
		for (i = 0; i <= bonusTesseraDTO.size() - 1; i++) {
			System.out.println("indice :" + i);
			if (!bonusTesseraDTO.get(i).getClass().getName().equals(bonus.get(i).getClass().getName()))
				break;
			else {
				if (i == bonusTesseraDTO.size() - 1) {
					return true;
				} else
					continue;
			}
		}
		return false;
	}

	/**
	 * This method check if cities on tesseraPermesso DTO are the same of cities
	 * on tesseraPermesso no DTO. IT's suppose that the size of two arrays is
	 * the same, because it's already check on 'cercaTesserePermesso'
	 * 
	 * @param cittàTesseraDTO
	 * @param città
	 * @return
	 */
	private static boolean stesseCittà(List<CittàDTO> cittàTesseraDTO, List<Città> città) {
		int i;
		for (i = 0; i <= cittàTesseraDTO.size() - 1; i++) {
			System.out.println("Città tesseraDTO: " + cittàTesseraDTO.get(i).getNome());
			System.out.println("Città tesseragiocatore: " + città.get(i).getNome());
			if (!cittàTesseraDTO.get(i).getNome().equals(città.get(i).getNome()))
				break;
			else {
				if (i == cittàTesseraDTO.size() - 1) {
					return true;
				} else
					continue;
			}
		}
		return false;
	}

	public static Consigliere cercaConsigliere(ConsigliereDTO consigliereDTO, List<Consigliere> consiglieri)
			throws IllegalArgumentException {
		for (Consigliere c : consiglieri) {
			if (c.getColore().getColore().equals(consigliereDTO.getColoreConsigliere()))
				return c;
		}
		throw new IllegalArgumentException("Il consigliere è inesistente!");
	}

	public static Offerta cercaOfferta(List<Offerta> offerteMarket, int offerta) throws IllegalArgumentException {
		if (offerta > 0 && offerta <= offerteMarket.size())
			return offerteMarket.get(offerta - 1);
		throw new IllegalArgumentException("L'offerta selezionata è inesistente");
	}

	public static Giocatore cercaGiocatore(GiocatoreDTO giocatoreDTO, List<Giocatore> giocatori) {
		for (Giocatore g : giocatori) {
			if (g.getNome().equals(giocatoreDTO.getNome()))
				return g;
		}
		throw new IllegalArgumentException("Il giocatore è inesistente");
	}

}
