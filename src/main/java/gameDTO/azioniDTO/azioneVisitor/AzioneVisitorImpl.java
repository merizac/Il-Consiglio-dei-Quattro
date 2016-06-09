package gameDTO.azioniDTO.azioneVisitor;

import java.util.ArrayList;
import java.util.HashSet;

import game.Balcone;
import game.CartaPolitica;
import game.Città;
import game.CittàBonus;
import game.Consigliere;
import game.GameState;
import game.Giocatore;
import game.Regione;
import game.TesseraPermesso;
import game.azioni.AcquistoTesseraPermesso;
import game.azioni.AzioneAcquisto;
import game.azioni.AzioneOfferta;
import game.azioni.BonusGettoneN;
import game.azioni.BonusTesseraAcquistataN;
import game.azioni.BonusTesseraPermessoN;
import game.azioni.CambioTesseraPermesso;
import game.azioni.CostruzioneAiutoRe;
import game.azioni.CostruzioneTesseraPermesso;
import game.azioni.ElezioneConsigliere;
import game.azioni.ElezioneConsigliereVeloce;
import game.azioni.IngaggioAiutante;
import game.azioni.Passa;
import game.azioni.PescaCarta;
import game.azioni.SecondaAzionePrincipale;
import game.market.Marketable;
import game.market.Offerta;
import gameDTO.azioniDTO.AcquistoTesseraPermessoDTO;
import gameDTO.azioniDTO.AzioneAcquistoDTO;
import gameDTO.azioniDTO.AzioneOffertaDTO;
import gameDTO.azioniDTO.BonusGettoneNDTO;
import gameDTO.azioniDTO.BonusTesseraAcquistataNDTO;
import gameDTO.azioniDTO.BonusTesseraPermessoNDTO;
import gameDTO.azioniDTO.CambioTesserePermessoDTO;
import gameDTO.azioniDTO.ControlloParametri;
import gameDTO.azioniDTO.CostruzioneAiutoReDTO;
import gameDTO.azioniDTO.CostruzioneTesseraPermessoDTO;
import gameDTO.azioniDTO.ElezioneConsigliereDTO;
import gameDTO.azioniDTO.ElezioneConsigliereVeloceDTO;
import gameDTO.azioniDTO.IngaggioAiutanteDTO;
import gameDTO.azioniDTO.PassaDTO;
import gameDTO.azioniDTO.PescaCartaDTO;
import gameDTO.azioniDTO.SecondaAzionePrincipaleDTO;

public class AzioneVisitorImpl implements AzioneVisitor {

	private GameState gameState;
	private Giocatore giocatore;

	/**
	 * @param gameState
	 */
	public AzioneVisitorImpl(GameState gameState, Giocatore giocatore) {
		this.gameState = gameState;
		this.giocatore = giocatore;
	}

	@Override
	public AcquistoTesseraPermesso visit(AcquistoTesseraPermessoDTO acquistoTesseraPermessoDTO) {
		AcquistoTesseraPermesso acquistoTesseraPermesso = new AcquistoTesseraPermesso();
		Regione regione = ControlloParametri.cercaRegione(acquistoTesseraPermessoDTO.getRegione(),
				gameState.getRegioni());
		TesseraPermesso tessera = ControlloParametri.cercaTesseraPermesso(
				acquistoTesseraPermessoDTO.getTesseraPermesso(), regione.getTesserePermessoScoperte());
		ArrayList<CartaPolitica> carte = ControlloParametri.cercaCartePolitica(acquistoTesseraPermessoDTO.getCarte(),
				new ArrayList<>(giocatore.getCartePolitica()));
		acquistoTesseraPermesso.setRegione(regione);
		acquistoTesseraPermesso.setCarteGiocatore(carte);
		acquistoTesseraPermesso.setTesseraScoperta(tessera);

		return acquistoTesseraPermesso;
	}

	@Override
	public CambioTesseraPermesso visit(CambioTesserePermessoDTO cambioTesserePermessoDTO) {
		CambioTesseraPermesso cambioTesseraPermesso = new CambioTesseraPermesso();

		Regione regione = ControlloParametri.cercaRegione(cambioTesserePermessoDTO.getRegione(),
				gameState.getRegioni());
		cambioTesseraPermesso.setRegione(regione);

		return cambioTesseraPermesso;
	}

	@Override
	public CostruzioneAiutoRe visit(CostruzioneAiutoReDTO costruzioneAiutoReDTO) {
		CostruzioneAiutoRe costruzioneAiutoRe = new CostruzioneAiutoRe();
		Città città = ControlloParametri.cercaCittà(costruzioneAiutoReDTO.getCittà(), gameState.getCittà());
		costruzioneAiutoRe.setCittàCostruzione(città);

		ArrayList<CartaPolitica> carte = ControlloParametri.cercaCartePolitica(
				costruzioneAiutoReDTO.getCarteGiocatore(), new ArrayList<>(giocatore.getCartePolitica()));
		costruzioneAiutoRe.setCarteGiocatore(carte);

		return costruzioneAiutoRe;
	}

	@Override
	public CostruzioneTesseraPermesso visit(CostruzioneTesseraPermessoDTO costruzioneTesseraPermessoDTO) {
		CostruzioneTesseraPermesso costruzioneTesseraPermesso = new CostruzioneTesseraPermesso();
		TesseraPermesso tesseraPermesso = ControlloParametri.cercaTesseraPermesso(
				costruzioneTesseraPermessoDTO.getTesseraPermesso(), giocatore.getTesserePermesso());
		costruzioneTesseraPermesso.setTesseraPermessoScoperta(tesseraPermesso);

		Città città = ControlloParametri.cercaCittà(costruzioneTesseraPermessoDTO.getCittà(),
				new HashSet<>(tesseraPermesso.getCittà()));
		costruzioneTesseraPermesso.setCittàCostruzione(città);

		return costruzioneTesseraPermesso;
	}

	@Override
	public ElezioneConsigliere visit(ElezioneConsigliereDTO elezioneConsigliereDTO) {
		ElezioneConsigliere elezioneConsigliere = new ElezioneConsigliere();
		Balcone balcone = ControlloParametri.cercaBalcone(elezioneConsigliereDTO.getBalconeDTO(), 
				gameState.getPlanciaRe().getBalconeRe(), gameState.getRegioni());
		elezioneConsigliere.setBalcone(balcone);
		Consigliere consigliere = ControlloParametri.cercaConsigliere(elezioneConsigliereDTO.getConsigliereDTO(),
				gameState.getConsiglieri());
		elezioneConsigliere.setConsigliere(consigliere);
		return elezioneConsigliere;
	}

	@Override
	public ElezioneConsigliereVeloce visit(ElezioneConsigliereVeloceDTO elezioneConsigliereVeloceDTO) {
		ElezioneConsigliereVeloce elezioneConsigliereVeloce = new ElezioneConsigliereVeloce();
		Regione regione = ControlloParametri.cercaRegione(elezioneConsigliereVeloceDTO.getRegione(),
				gameState.getRegioni());
		elezioneConsigliereVeloce.setRegione(regione);

		Consigliere consigliere = ControlloParametri.cercaConsigliere(elezioneConsigliereVeloceDTO.getConsigliere(),
				gameState.getConsiglieri());
		elezioneConsigliereVeloce.setConsigliere(consigliere);

		return elezioneConsigliereVeloce;
	}

	@Override
	public IngaggioAiutante visit(IngaggioAiutanteDTO ingaggioAiutanteDTO) {
		return new IngaggioAiutante();
	}

	@Override
	public SecondaAzionePrincipale visit(SecondaAzionePrincipaleDTO secondaAzionePrincipaleDTO) {
		return new SecondaAzionePrincipale();
	}

	@Override
	public PescaCarta visit(PescaCartaDTO pescaCartaDTO) {
		return new PescaCarta();
	}

	@Override
	public Passa visit(PassaDTO passaDTO) {
		return new Passa();
	}

	@Override
	public AzioneOfferta visit(AzioneOffertaDTO azioneOffertaDTO) {
		AzioneOfferta azioneOfferta = new AzioneOfferta();
		Marketable marketable = azioneOffertaDTO.getMarketableDTO().creaMarketable(giocatore);
		azioneOfferta.setMarketable(marketable);
		azioneOfferta.setPrezzo(azioneOffertaDTO.getPrezzo());
		return azioneOfferta;
	}

	@Override
	public AzioneAcquisto visit(AzioneAcquistoDTO azioneAcquistoDTO) {
		AzioneAcquisto azioneAcquisto = new AzioneAcquisto();
		Offerta offerta = ControlloParametri.cercaOfferta(gameState.getOfferteMarket(), azioneAcquistoDTO.getOfferta());
		Giocatore giocatore = ControlloParametri.carcaGiocatore(gameState.getGiocatori(),
				azioneAcquistoDTO.getGiocatoreDTO());
		azioneAcquisto.setOfferta(offerta);
		azioneAcquisto.setAcquirente(giocatore);
		return azioneAcquisto;
	}

	@Override
	public BonusGettoneN visit(BonusGettoneNDTO bonusGettoneDTO) {
		BonusGettoneN bonusGettone = new BonusGettoneN();
		bonusGettone.setCittà(new ArrayList<>());
		for (int i = 0; i < bonusGettone.getNumeroGettoni(); i++) {
			CittàBonus città = (CittàBonus) ControlloParametri.cercaCittàBonus(bonusGettoneDTO.getCittà().get(i),
					gameState.getCittà());
			bonusGettone.getCittà().add(città);
		}
		return bonusGettone;

	}

	@Override
	public BonusTesseraAcquistataN visit(BonusTesseraAcquistataNDTO bonusTesseraAcquistataDTO) {
		BonusTesseraAcquistataN bonusTesseraAcquistata = new BonusTesseraAcquistataN();
		TesseraPermesso tesseraPermesso;
		if (bonusTesseraAcquistataDTO.isUsata()) {
			tesseraPermesso = ControlloParametri.cercaTesseraPermesso(bonusTesseraAcquistataDTO.getTesseraPermesso(),
					giocatore.getTesserePermessoUsate());
		} else
			tesseraPermesso = ControlloParametri.cercaTesseraPermesso(bonusTesseraAcquistataDTO.getTesseraPermesso(),
					giocatore.getTesserePermesso());

		bonusTesseraAcquistata.setTesseraPermesso(tesseraPermesso);
		return bonusTesseraAcquistata;
	}

	@Override
	public BonusTesseraPermessoN visit(BonusTesseraPermessoNDTO bonusTesseraPermessoDTO) {
		BonusTesseraPermessoN bonusTesseraPermesso = new BonusTesseraPermessoN();
		Regione regione = ControlloParametri.cercaRegione(bonusTesseraPermessoDTO.getRegione(), gameState.getRegioni());
		TesseraPermesso tesseraPermesso = ControlloParametri.cercaTesseraPermesso(
				bonusTesseraPermessoDTO.getTesseraScoperta(), regione.getTesserePermessoScoperte());
		bonusTesseraPermesso.setRegione(regione);
		bonusTesseraPermesso.setTesseraScoperta(tesseraPermesso);
		return bonusTesseraPermesso;
	}


}
