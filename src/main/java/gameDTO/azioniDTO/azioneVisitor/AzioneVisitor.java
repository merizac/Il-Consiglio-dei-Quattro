package gameDTO.azioniDTO.azioneVisitor;

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
import gameDTO.azioniDTO.CostruzioneAiutoReDTO;
import gameDTO.azioniDTO.CostruzioneTesseraPermessoDTO;
import gameDTO.azioniDTO.ElezioneConsigliereDTO;
import gameDTO.azioniDTO.ElezioneConsigliereVeloceDTO;
import gameDTO.azioniDTO.IngaggioAiutanteDTO;
import gameDTO.azioniDTO.PassaDTO;
import gameDTO.azioniDTO.PescaCartaDTO;
import gameDTO.azioniDTO.SecondaAzionePrincipaleDTO;

public interface AzioneVisitor {
	
	public PescaCarta visit(PescaCartaDTO pescaCartaDTO);
	public AcquistoTesseraPermesso visit(AcquistoTesseraPermessoDTO acquistoTesseraPermessoDTO);
	public CambioTesseraPermesso visit(CambioTesserePermessoDTO cambioTesserePermessoDTO);
	public CostruzioneAiutoRe visit(CostruzioneAiutoReDTO costruzioneAiutoReDTO);
	public CostruzioneTesseraPermesso visit(CostruzioneTesseraPermessoDTO costruzioneTesseraPermessoDTO);
	public ElezioneConsigliere visit(ElezioneConsigliereDTO elezioneConsigliereDTO);
	public ElezioneConsigliereVeloce visit(ElezioneConsigliereVeloceDTO elezioneConsigliereVeloceDTO);
	public IngaggioAiutante visit(IngaggioAiutanteDTO ingaggioAiutanteDTO);
	public SecondaAzionePrincipale visit(SecondaAzionePrincipaleDTO secondaAzionePrincipaleDTO);
	public Passa visit(PassaDTO passaDTO);

}
