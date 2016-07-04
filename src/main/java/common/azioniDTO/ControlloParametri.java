package common.azioniDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import common.gameDTO.BalconeDTO;
import common.gameDTO.CartaPoliticaDTO;
import common.gameDTO.CittàDTO;
import common.gameDTO.ConsigliereDTO;
import common.gameDTO.GiocatoreDTO;
import common.gameDTO.RegioneDTO;
import common.gameDTO.TesseraPermessoDTO;
import server.model.bonus.Bonus;
import server.model.bonus.BonusPuntiNobiltà;
import server.model.game.Balcone;
import server.model.game.CartaPolitica;
import server.model.game.Città;
import server.model.game.CittàBonus;
import server.model.game.Colore;
import server.model.game.Consigliere;
import server.model.game.Giocatore;
import server.model.game.Regione;
import server.model.game.TesseraPermesso;
import server.model.market.Offerta;
import utility.ParameterException;

public class ControlloParametri {


	/**
	 * constructor
	 */
	private ControlloParametri() {
		super();
	}

	/**
	 * check if region is a existing region
	 * 
	 * @param regione
	 * @param list
	 * @return
	 * @throws ParameterException
	 */
	public static Regione cercaRegione(RegioneDTO regione, List<Regione> list) throws ParameterException {
		for (Regione r : list) {
			if (r.getNome().equals(regione.getNome()))
				return r;
		}
		throw new ParameterException("La regione " + regione.getNome() + " è inesistente!");
	}

	
	/**
	 * check if the balcone is a existing balcone
	 * 
	 * @param balconeDTO
	 * @param balconeRe
	 * @param regioni
	 * @return
	 * @throws ParameterException
	 */
	public static Balcone cercaBalcone(BalconeDTO balconeDTO, Balcone balconeRe, List<Regione> regioni)
			throws ParameterException {
		for (Regione r : regioni) {
			if (controlloBalcone(balconeDTO, r.getBalcone())) {
				return r.getBalcone();
			}
		}
		if (controlloBalcone(balconeDTO, balconeRe)) {
			return balconeRe;
		} else {
			throw new ParameterException("Il balcone è inesistente!");
		}
	}

	/**
	 * check color of councilors in balcone
	 * 
	 * @param balconeDTO
	 * @param balcone
	 * @return
	 */
	private static boolean controlloBalcone(BalconeDTO balconeDTO, Balcone balcone) {
		List<Consigliere> consiglieri = new ArrayList<>(balcone.getConsigliere());
		for (int i = 0; i < balconeDTO.getConsiglieri().size(); i++) {
			if (!balconeDTO.getConsiglieri().get(i).getColoreConsigliere()
					.equals(consiglieri.get(i).getColore().getColore()))
				return false;
		}
		return true;
	}

	/**
	 * check if color of politic cards are the existing cards
	 * 
	 * @param carte
	 * @param cartePolitica
	 * @return
	 * @throws ParameterException
	 */
	public static List<CartaPolitica> cercaCartePolitica(List<CartaPoliticaDTO> carte,
			List<CartaPolitica> cartePolitica) throws ParameterException {

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
				throw new ParameterException("La carta " + c.getColore() + " è inesistente");
		}
		return carteGiocatore;
	}

	/**
	 * check if the city is existing
	 * 
	 * @param città
	 * @param cittàGameState
	 * @return
	 * @throws ParameterException
	 */
	public static Città cercaCittà(CittàDTO città, Set<Città> cittàGameState) throws ParameterException {
		for (Città c : cittàGameState) {
			if (c.getNome().equals(città.getNome()))
				return c;
		}
		throw new ParameterException("La città " + città.getNome() + " è inesistente!");
	}

	/**
	 * check if the city dto is a city with bonus and bonus aren't nobility
	 * points
	 * 
	 * @param città
	 * @param cittàGameState
	 * @return
	 * @throws ParameterException
	 */
	public static Città cercaCittàBonus(CittàDTO città, Set<Città> cittàGameState) throws ParameterException {

		for (Città c : cittàGameState) {
			if (c.getNome().equals(città.getNome()) && (c instanceof CittàBonus)) {
				for (Bonus b : ((CittàBonus) c).getBonus()) {
					if (b instanceof BonusPuntiNobiltà)
						throw new ParameterException("La città " + c.getNome() + " contiene un bonus punti nobiltà!");
				}
				return c;
			}
		}
		throw new ParameterException("La città " + città.getNome() + " è inesistente!");
	}

	/**
	 * check if permit tile dto are the same of permit tile in the model
	 * 
	 * @param tesseraPermesso
	 * @param tesserePermesso
	 * @return tessera permesso non DTO
	 * @throws ParameterException
	 * @throws IllegalArgumentException
	 */
	public static TesseraPermesso cercaTesseraPermesso(TesseraPermessoDTO tesseraPermesso,
			List<TesseraPermesso> tesserePermesso) throws ParameterException {
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

		throw new ParameterException("la tessera permesso\n" + tesseraPermesso + "\nè inesistente!");

	}

	/**
	 * order city in alphabetic order
	 * 
	 * @param cittàTessera
	 */
	private static void ordinaCittà(List<Città> cittàTessera) {
		Comparator<Città> comparator = (o1, o2) ->  o1.getNome().compareToIgnoreCase(o2.getNome());
		Collections.sort(cittàTessera, comparator);
	}

	/**
	 * order dto city in alphabetic order
	 * 
	 * @param città
	 */
	private static void ordinaCittàDTO(List<CittàDTO> città) {
		Comparator<CittàDTO> comparatorDTO = (o1, o2) ->  o1.getNome().compareToIgnoreCase(o2.getNome());
		Collections.sort(città, comparatorDTO);

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

	
	/**
	 * check if counsilior dto is the same councilior in the model
	 * 
	 * @param consigliereDTO
	 * @param consiglieri
	 * @return
	 * @throws ParameterException
	 */
	public static Consigliere cercaConsigliere(ConsigliereDTO consigliereDTO, List<Consigliere> consiglieri)
			throws ParameterException {
		for (Consigliere c : consiglieri) {
			if (c.getColore().getColore().equals(consigliereDTO.getColoreConsigliere()))
				return c;
		}
		throw new ParameterException("Il consigliere " + consigliereDTO.getColoreConsigliere() + " è inesistente!");
	}

	/**
	 * check if the offer are real offer in the model
	 * 
	 * @param offerteMarket
	 * @param offerta
	 * @return
	 * @throws ParameterException
	 */
	public static Offerta cercaOfferta(List<Offerta> offerteMarket, int offerta) throws ParameterException {
		if (offerta > 0 && offerta <= offerteMarket.size())
			return offerteMarket.get(offerta - 1);
		throw new ParameterException("L'offerta numero " + offerta + " è inesistente");
	}

	
	/**
	 * check if the dto player is an existing player in the model
	 * 
	 * @param giocatoreDTO
	 * @param giocatori
	 * @return
	 * @throws ParameterException
	 */
	public static Giocatore cercaGiocatore(GiocatoreDTO giocatoreDTO, List<Giocatore> giocatori)
			throws ParameterException {
		for (Giocatore g : giocatori) {
			if (g.getNome().equals(giocatoreDTO.getNome()))
				return g;
		}
		throw new ParameterException("Il giocatore " + giocatoreDTO.getNome() + " è inesistente");
	}

}
