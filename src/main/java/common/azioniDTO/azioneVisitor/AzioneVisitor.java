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
	public AzioneOfferta visit(AzioneOffertaDTO azioneOffertaDTO);
	public AzioneAcquisto visit(AzioneAcquistoDTO azioneAcquistoDTO);
	public BonusGettoneN visit(BonusGettoneNDTO bonusGettoneDTO);
	public BonusTesseraAcquistataN visit(BonusTesseraAcquistataNDTO bonusTesseraAcquistataDTO);
	public BonusTesseraPermessoN visit(BonusTesseraPermessoNDTO bonusTesseraPermessoDTO);
	public Exit visit(ExitDTO exitDTO);
	
}
