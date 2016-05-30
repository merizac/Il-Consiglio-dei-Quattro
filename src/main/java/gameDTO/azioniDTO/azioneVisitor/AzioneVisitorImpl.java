package gameDTO.azioniDTO.azioneVisitor;

import java.util.ArrayList;
import java.util.HashSet;

import game.CartaPolitica;
import game.Città;
import game.Consigliere;
import game.GameState;
import game.Giocatore;
import game.Regione;
import game.TesseraPermesso;
import game.azioni.AcquistoTesseraPermesso;
import game.azioni.CambioTesseraPermesso;
import game.azioni.CostruzioneAiutoRe;
import game.azioni.CostruzioneTesseraPermesso;
import game.azioni.ElezioneConsigliere;
import game.azioni.ElezioneConsigliereVeloce;
import game.azioni.IngaggioAiutante;
import game.azioni.Passa;
import game.azioni.PescaCarta;
import game.azioni.SecondaAzionePrincipale;
import gameDTO.azioniDTO.AcquistoTesseraPermessoDTO;
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
	}

	@Override
	public AcquistoTesseraPermesso visit(AcquistoTesseraPermessoDTO acquistoTesseraPermessoDTO) {
		AcquistoTesseraPermesso acquistoTesseraPermesso=new AcquistoTesseraPermesso();
		
		Regione regione=ControlloParametri.cercaRegione(acquistoTesseraPermessoDTO.getRegione(), gameState.getRegioni());
		acquistoTesseraPermesso.setRegione(regione);
		
		ArrayList<CartaPolitica> carte=
				ControlloParametri.cercaCartePolitica(acquistoTesseraPermessoDTO.getCarte(), new ArrayList<>(giocatore.getCartePolitica()));
		acquistoTesseraPermesso.setCarteGiocatore(carte);
		
		acquistoTesseraPermesso.setIndiceTesseraScoperta(acquistoTesseraPermesso.getIndiceTesseraScoperta());
		
		return acquistoTesseraPermesso;
	}

	@Override
	public CambioTesseraPermesso visit(CambioTesserePermessoDTO cambioTesserePermessoDTO) {
		CambioTesseraPermesso cambioTesseraPermesso=new CambioTesseraPermesso();
		
		Regione regione=ControlloParametri.cercaRegione(cambioTesserePermessoDTO.getRegione(), gameState.getRegioni());
		cambioTesseraPermesso.setRegione(regione);
		
		return cambioTesseraPermesso;
	}

	@Override
	public CostruzioneAiutoRe visit(CostruzioneAiutoReDTO costruzioneAiutoReDTO) {
		CostruzioneAiutoRe costruzioneAiutoRe = new CostruzioneAiutoRe();
		Città città = ControlloParametri.cercaCittà(costruzioneAiutoReDTO.getCittà(), gameState.getCittà());
		costruzioneAiutoRe.setCittàCostruzione(città);
		
		ArrayList<CartaPolitica> carte=
				ControlloParametri.cercaCartePolitica(costruzioneAiutoReDTO.getCarteGiocatore(), new ArrayList<>(giocatore.getCartePolitica()));
		costruzioneAiutoRe.setCarteGiocatore(carte);
		
		return costruzioneAiutoRe;
	}

	@Override
	public CostruzioneTesseraPermesso visit(CostruzioneTesseraPermessoDTO costruzioneTesseraPermessoDTO) {
		CostruzioneTesseraPermesso costruzioneTesseraPermesso = new CostruzioneTesseraPermesso();
		TesseraPermesso tesseraPermesso = ControlloParametri.cercaTesseraPermesso(costruzioneTesseraPermessoDTO.getTesseraPermesso(), giocatore.getTesserePermesso());
		costruzioneTesseraPermesso.setTesseraPermessoScoperta(tesseraPermesso);
		
		Città città= ControlloParametri.cercaCittà(costruzioneTesseraPermessoDTO.getCittà(), new HashSet<>(tesseraPermesso.getCittà()));
		costruzioneTesseraPermesso.setCittàCostruzione(città);
		
		return costruzioneTesseraPermesso;
	}

	@Override
	public ElezioneConsigliere visit(ElezioneConsigliereDTO elezioneConsigliereDTO) {
		ElezioneConsigliere elezioneConsigliere =new ElezioneConsigliere();
		Regione regione=ControlloParametri.cercaRegione(elezioneConsigliereDTO.getRegioneDTO(), gameState.getRegioni());
		elezioneConsigliere.setRegione(regione);
		
		Consigliere consigliere=ControlloParametri.cercaConsigliere(elezioneConsigliereDTO.getConsigliereDTO(), gameState.getConsiglieri());
		elezioneConsigliere.setConsigliere(consigliere);
		return elezioneConsigliere;
	}

	@Override
	public ElezioneConsigliereVeloce visit(ElezioneConsigliereVeloceDTO elezioneConsigliereVeloceDTO) {
		ElezioneConsigliereVeloce elezioneConsigliereVeloce = new ElezioneConsigliereVeloce();
		Regione regione=ControlloParametri.cercaRegione(elezioneConsigliereVeloceDTO.getRegione(), gameState.getRegioni());
		elezioneConsigliereVeloce.setRegione(regione);
		
		Consigliere consigliere=ControlloParametri.cercaConsigliere(elezioneConsigliereVeloceDTO.getConsigliere(), gameState.getConsiglieri());
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

}
