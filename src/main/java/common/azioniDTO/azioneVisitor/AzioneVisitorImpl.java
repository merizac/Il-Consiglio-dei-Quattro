package common.azioniDTO.azioneVisitor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import common.azioniDTO.AcquistoTesseraPermessoDTO;
import common.azioniDTO.AzioneAcquistoDTO;
import common.azioniDTO.AzioneOffertaDTO;
import common.azioniDTO.BonusGettoneNDTO;
import common.azioniDTO.BonusTesseraAcquistataNDTO;
import common.azioniDTO.BonusTesseraPermessoNDTO;
import common.azioniDTO.CambioTesserePermessoDTO;
import common.azioniDTO.ControlloParametri;
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
import server.model.azioni.Exit;
import server.model.azioni.Passa;
import server.model.azioni.PescaCarta;
import server.model.azioni.azioniBonus.BonusGettoneN;
import server.model.azioni.azioniBonus.BonusTesseraAcquistataN;
import server.model.azioni.azioniBonus.BonusTesseraPermessoN;
import server.model.azioni.azioniBonus.PassaBonus;
import server.model.azioni.azioniMarket.AzioneAcquisto;
import server.model.azioni.azioniMarket.AzioneOfferta;
import server.model.azioni.azioniPrincipali.AcquistoTesseraPermesso;
import server.model.azioni.azioniPrincipali.CostruzioneAiutoRe;
import server.model.azioni.azioniPrincipali.CostruzioneTesseraPermesso;
import server.model.azioni.azioniPrincipali.ElezioneConsigliere;
import server.model.azioni.azioniVeloci.CambioTesseraPermesso;
import server.model.azioni.azioniVeloci.ElezioneConsigliereVeloce;
import server.model.azioni.azioniVeloci.IngaggioAiutante;
import server.model.azioni.azioniVeloci.SecondaAzionePrincipale;
import server.model.game.Balcone;
import server.model.game.CartaPolitica;
import server.model.game.Città;
import server.model.game.CittàBonus;
import server.model.game.Consigliere;
import server.model.game.GameState;
import server.model.game.Giocatore;
import server.model.game.Regione;
import server.model.game.TesseraPermesso;
import server.model.market.Marketable;
import server.model.market.Offerta;
import utility.ParameterException;

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
	public AcquistoTesseraPermesso visit(AcquistoTesseraPermessoDTO acquistoTesseraPermessoDTO) throws ParameterException {
		AcquistoTesseraPermesso acquistoTesseraPermesso = new AcquistoTesseraPermesso();
		Regione regione = ControlloParametri.cercaRegione(acquistoTesseraPermessoDTO.getRegione(),
				gameState.getRegioni());
		TesseraPermesso tessera = ControlloParametri.cercaTesseraPermesso(
				acquistoTesseraPermessoDTO.getTesseraPermesso(), regione.getTesserePermessoScoperte());
		List<CartaPolitica> carte = ControlloParametri.cercaCartePolitica(acquistoTesseraPermessoDTO.getCarte(),
				new ArrayList<>(giocatore.getCartePolitica()));
		acquistoTesseraPermesso.setRegione(regione);
		acquistoTesseraPermesso.setCarteGiocatore(carte);
		acquistoTesseraPermesso.setTesseraScoperta(tessera);

		return acquistoTesseraPermesso;
	}

	@Override
	public CambioTesseraPermesso visit(CambioTesserePermessoDTO cambioTesserePermessoDTO) throws ParameterException {
		CambioTesseraPermesso cambioTesseraPermesso = new CambioTesseraPermesso();

		Regione regione = ControlloParametri.cercaRegione(cambioTesserePermessoDTO.getRegione(),
				gameState.getRegioni());
		cambioTesseraPermesso.setRegione(regione);

		return cambioTesseraPermesso;
	}

	@Override
	public CostruzioneAiutoRe visit(CostruzioneAiutoReDTO costruzioneAiutoReDTO) throws ParameterException {
		CostruzioneAiutoRe costruzioneAiutoRe = new CostruzioneAiutoRe();
		Città città = ControlloParametri.cercaCittà(costruzioneAiutoReDTO.getCittà(), gameState.getCittà());
		costruzioneAiutoRe.setCittàCostruzione(città);

		List<CartaPolitica> carte = ControlloParametri.cercaCartePolitica(
				costruzioneAiutoReDTO.getCarteGiocatore(), new ArrayList<>(giocatore.getCartePolitica()));
		costruzioneAiutoRe.setCarteGiocatore(carte);

		return costruzioneAiutoRe;
	}

	@Override
	public CostruzioneTesseraPermesso visit(CostruzioneTesseraPermessoDTO costruzioneTesseraPermessoDTO) throws ParameterException {
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
	public ElezioneConsigliere visit(ElezioneConsigliereDTO elezioneConsigliereDTO) throws ParameterException {
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
	public ElezioneConsigliereVeloce visit(ElezioneConsigliereVeloceDTO elezioneConsigliereVeloceDTO) throws ParameterException {
		ElezioneConsigliereVeloce elezioneConsigliereVeloce = new ElezioneConsigliereVeloce();
		Balcone balcone = ControlloParametri.cercaBalcone(elezioneConsigliereVeloceDTO.getBalcone(), 
				gameState.getPlanciaRe().getBalconeRe(), gameState.getRegioni());
		elezioneConsigliereVeloce.setBalcone(balcone);

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
	public PassaBonus visit(PassaBonusDTO passaBonusDTO) throws ParameterException {
		PassaBonus passaBonus = new PassaBonus();
		passaBonus.setBonus(passaBonusDTO.getBonus().accept(this));
		return passaBonus;
	}

	@Override
	public AzioneOfferta visit(AzioneOffertaDTO azioneOffertaDTO) throws ParameterException {
		AzioneOfferta azioneOfferta = new AzioneOfferta();
		Marketable marketable = azioneOffertaDTO.getMarketableDTO().creaMarketable(giocatore);
		azioneOfferta.setMarketable(marketable);
		azioneOfferta.setPrezzo(azioneOffertaDTO.getPrezzo());
		return azioneOfferta;
	}

	@Override
	public AzioneAcquisto visit(AzioneAcquistoDTO azioneAcquistoDTO) throws ParameterException {
		AzioneAcquisto azioneAcquisto = new AzioneAcquisto();
		Offerta offerta = ControlloParametri.cercaOfferta(gameState.getOfferteMarket(), azioneAcquistoDTO.getOfferta());
		Giocatore giocatoreAcquisto = ControlloParametri.cercaGiocatore(azioneAcquistoDTO.getGiocatoreDTO(), gameState.getGiocatori()
				);
		azioneAcquisto.setOfferta(offerta);
		azioneAcquisto.setAcquirente(giocatoreAcquisto);
		return azioneAcquisto;
	}

	@Override
	public BonusGettoneN visit(BonusGettoneNDTO bonusGettoneDTO) throws ParameterException {
		BonusGettoneN bonusGettone = new BonusGettoneN();
		bonusGettone.setCittà(new ArrayList<>());
		for (int i = 0; i < bonusGettoneDTO.getNumeroGettoni(); i++) {
			CittàBonus città = (CittàBonus) ControlloParametri.cercaCittàBonus(bonusGettoneDTO.getCittà().get(i),
					gameState.getCittà());
			bonusGettone.getCittà().add(città);
		}
		bonusGettone.setNumeroGettoni(bonusGettoneDTO.getNumeroGettoni());
		return bonusGettone;

	}

	@Override
	public BonusTesseraAcquistataN visit(BonusTesseraAcquistataNDTO bonusTesseraAcquistataDTO) throws ParameterException {
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
	public BonusTesseraPermessoN visit(BonusTesseraPermessoNDTO bonusTesseraPermessoDTO) throws ParameterException {
		BonusTesseraPermessoN bonusTesseraPermesso = new BonusTesseraPermessoN();
		Regione regione = ControlloParametri.cercaRegione(bonusTesseraPermessoDTO.getRegione(), gameState.getRegioni());
		TesseraPermesso tesseraPermesso = ControlloParametri.cercaTesseraPermesso(
				bonusTesseraPermessoDTO.getTesseraScoperta(), regione.getTesserePermessoScoperte());
		bonusTesseraPermesso.setRegione(regione);
		bonusTesseraPermesso.setTesseraScoperta(tesseraPermesso);
		return bonusTesseraPermesso;
	}

	@Override
	public Exit visit(ExitDTO exitDTO) throws ParameterException {
		Exit exit=new Exit();
		Giocatore giocatoreDisconnesso=ControlloParametri.cercaGiocatore(exitDTO.getGiocatoreDTO(), gameState.getGiocatori());
		exit.setGiocatore(giocatoreDisconnesso);
		return exit;
	}
}
