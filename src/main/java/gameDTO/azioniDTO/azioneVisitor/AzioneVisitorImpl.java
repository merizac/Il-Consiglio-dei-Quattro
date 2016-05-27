package gameDTO.azioniDTO.azioneVisitor;

import game.azioni.AcquistoTesseraPermesso;
import game.azioni.CambioTesseraPermesso;
import game.azioni.CostruzioneAiutoRe;
import game.azioni.CostruzioneTesseraPermesso;
import game.azioni.ElezioneConsigliere;
import game.azioni.ElezioneConsigliereVeloce;
import game.azioni.IngaggioAiutante;
import game.azioni.SecondaAzionePrincipale;
import gameDTO.azioniDTO.AcquistoTesseraPermessoDTO;
import gameDTO.azioniDTO.CambioTesserePermessoDTO;
import gameDTO.azioniDTO.CostruzioneAiutoReDTO;
import gameDTO.azioniDTO.CostruzioneTesseraPermessoDTO;
import gameDTO.azioniDTO.ElezioneConsigliereDTO;
import gameDTO.azioniDTO.ElezioneConsigliereVeloceDTO;
import gameDTO.azioniDTO.IngaggioAiutanteDTO;
import gameDTO.azioniDTO.SecondaAzionePrincipaleDTO;

public class AzioneVisitorImpl implements AzioneVisitor {

	@Override
	public AcquistoTesseraPermesso visit(AcquistoTesseraPermessoDTO acquistoTesseraPermessoDTO) {
		AcquistoTesseraPermesso acquistoTesseraPermesso=new AcquistoTesseraPermesso();
		
		return acquistoTesseraPermesso;
	}

	@Override
	public CambioTesseraPermesso visit(CambioTesserePermessoDTO cambioTesserePermessoDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CostruzioneAiutoRe visit(CostruzioneAiutoReDTO costruzioneAiutoReDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CostruzioneTesseraPermesso visit(CostruzioneTesseraPermessoDTO costruzioneTesseraPermessoDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ElezioneConsigliere visit(ElezioneConsigliereDTO elezioneConsigliereDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ElezioneConsigliereVeloce visit(ElezioneConsigliereVeloceDTO elezioneConsigliereVeloceDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IngaggioAiutante visit(IngaggioAiutanteDTO ingaggioAiutanteDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SecondaAzionePrincipale visit(SecondaAzionePrincipaleDTO secondaAzionePrincipaleDTO) {
		// TODO Auto-generated method stub
		return null;
	}

}
