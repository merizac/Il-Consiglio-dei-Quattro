package common.azioniDTO.azioneVisitor;

import common.azioniDTO.AcquistoTesseraPermessoDTO;
import common.azioniDTO.AzioneAcquistoDTO;
import common.azioniDTO.AzioneOffertaDTO;
import common.azioniDTO.BonusGettoneNDTO;
import common.azioniDTO.BonusTesseraAcquistataNDTO;
import common.azioniDTO.BonusTesseraPermessoNDTO;
import common.azioniDTO.CambioTesserePermessoDTO;
import common.azioniDTO.CostruzioneAiutoReDTO;
import common.azioniDTO.CostruzioneTesseraPermessoDTO;
import common.azioniDTO.ElezioneConsigliereDTO;
import common.azioniDTO.ElezioneConsigliereVeloceDTO;
import common.azioniDTO.ExitDTO;
import common.azioniDTO.IngaggioAiutanteDTO;
import common.azioniDTO.PassaDTO;
import common.azioniDTO.PescaCartaDTO;
import common.azioniDTO.SecondaAzionePrincipaleDTO;
import server.model.azioni.Exit;
import server.model.azioni.Passa;
import server.model.azioni.PescaCarta;
import server.model.azioni.azioniBonus.BonusGettoneN;
import server.model.azioni.azioniBonus.BonusTesseraAcquistataN;
import server.model.azioni.azioniBonus.BonusTesseraPermessoN;
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
import utility.ParameterException;

public interface AzioneVisitor {
	
	public PescaCarta visit(PescaCartaDTO pescaCartaDTO);
	public AcquistoTesseraPermesso visit(AcquistoTesseraPermessoDTO acquistoTesseraPermessoDTO) throws ParameterException;
	public CambioTesseraPermesso visit(CambioTesserePermessoDTO cambioTesserePermessoDTO) throws ParameterException;
	public CostruzioneAiutoRe visit(CostruzioneAiutoReDTO costruzioneAiutoReDTO) throws ParameterException;
	public CostruzioneTesseraPermesso visit(CostruzioneTesseraPermessoDTO costruzioneTesseraPermessoDTO) throws ParameterException;
	public ElezioneConsigliere visit(ElezioneConsigliereDTO elezioneConsigliereDTO) throws ParameterException;
	public ElezioneConsigliereVeloce visit(ElezioneConsigliereVeloceDTO elezioneConsigliereVeloceDTO) throws ParameterException;
	public IngaggioAiutante visit(IngaggioAiutanteDTO ingaggioAiutanteDTO);
	public SecondaAzionePrincipale visit(SecondaAzionePrincipaleDTO secondaAzionePrincipaleDTO);
	public Passa visit(PassaDTO passaDTO);
	public AzioneOfferta visit(AzioneOffertaDTO azioneOffertaDTO) throws ParameterException;
	public AzioneAcquisto visit(AzioneAcquistoDTO azioneAcquistoDTO) throws ParameterException;
	public BonusTesseraAcquistataN visit(BonusTesseraAcquistataNDTO bonusTesseraAcquistataDTO) throws ParameterException;
	public BonusTesseraPermessoN visit(BonusTesseraPermessoNDTO bonusTesseraPermessoDTO) throws ParameterException;
	public Exit visit(ExitDTO exitDTO) throws ParameterException;
	public BonusGettoneN visit(BonusGettoneNDTO bonusGettoneNDTO) throws ParameterException;
	
}
