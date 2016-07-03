package common.azioniDTO.azioneVisitor;

import common.azioniDTO.AcquistoTesseraPermessoDTO;
import common.azioniDTO.AzioneAcquistoDTO;
import common.azioniDTO.AzioneOffertaDTO;
import common.azioniDTO.BonusGettoneNDTO;
import common.azioniDTO.BonusTesseraAcquistataNDTO;
import common.azioniDTO.BonusTesseraPermessoNDTO;
import common.azioniDTO.CambioTesserePermessoDTO;
import common.azioniDTO.ChatDTO;
import common.azioniDTO.CostruzioneAiutoReDTO;
import common.azioniDTO.CostruzioneTesseraPermessoDTO;
import common.azioniDTO.ElezioneConsigliereDTO;
import common.azioniDTO.ElezioneConsigliereVeloceDTO;
import common.azioniDTO.ExitDTO;
import common.azioniDTO.IngaggioAiutanteDTO;
import common.azioniDTO.PassaDTO;
import common.azioniDTO.PescaCartaDTO;
import common.azioniDTO.SecondaAzionePrincipaleDTO;
import server.model.azioni.Chat;
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

	/**
	 * parsing from PescaCartaDTO to PescaCarta
	 */
	public PescaCarta visit(PescaCartaDTO pescaCartaDTO);

	/**
	 * parsing from AcquistoTesseraPErmessoDTO to AcquistoTesseraPermesso for
	 * each parameter check if is an existing parameter, and set to the action
	 */
	public AcquistoTesseraPermesso visit(AcquistoTesseraPermessoDTO acquistoTesseraPermessoDTO)
			throws ParameterException;

	/**
	 * parsing from CambioTesserePermessoDTO to CambioTesserePermesso . for each
	 * parameter check if is an existing parameter, and set to the action
	 */
	public CambioTesseraPermesso visit(CambioTesserePermessoDTO cambioTesserePermessoDTO) throws ParameterException;

	/**
	 * parsing from costruzioneAiutoReDTO to CostruzioneAiutoRe for each
	 * parameter check if is an existing parameter, and set to the action
	 */
	public CostruzioneAiutoRe visit(CostruzioneAiutoReDTO costruzioneAiutoReDTO) throws ParameterException;

	/**
	 * parsing from CostruzioneTesseraPermessoDTO to CostruzioneTesseraPermesso
	 * for each parameter check if is an existing parameter, and set to the
	 * action
	 */
	public CostruzioneTesseraPermesso visit(CostruzioneTesseraPermessoDTO costruzioneTesseraPermessoDTO)
			throws ParameterException;

	/**
	 * parsing from ElezioneConsigliereDTO to ElezioneConsigliere for each
	 * parameter check if is an existing parameter, and set to the action
	 */
	public ElezioneConsigliere visit(ElezioneConsigliereDTO elezioneConsigliereDTO) throws ParameterException;

	/**
	 * parsing from ElezioneConsigliereVeloceDTO to ElezioneConsigliereVeloce
	 * for each parameter check if is an existing parameter, and set to the
	 * action
	 */
	public ElezioneConsigliereVeloce visit(ElezioneConsigliereVeloceDTO elezioneConsigliereVeloceDTO)
			throws ParameterException;

	/**
	 * parsing from IngaggioAiutanteDTO to IngaggioAiutante
	 */
	public IngaggioAiutante visit(IngaggioAiutanteDTO ingaggioAiutanteDTO);

	/**
	 * parsing from SecondaAzionePrincipaleDTO to SecondaAzionePrincipale
	 */
	public SecondaAzionePrincipale visit(SecondaAzionePrincipaleDTO secondaAzionePrincipaleDTO);

	/**
	 * parsing from PassaDTO to Passa
	 */
	public Passa visit(PassaDTO passaDTO);

	/**
	 * parsing from AzioneOffertaDTO to AzioneOfferta set parameters to the
	 * action
	 */
	public AzioneOfferta visit(AzioneOffertaDTO azioneOffertaDTO) throws ParameterException;

	/**
	 * parsing from AzioneAcquistoDTO to AzioneAcquisto and set parameters to
	 * the action
	 */
	public AzioneAcquisto visit(AzioneAcquistoDTO azioneAcquistoDTO) throws ParameterException;

	/**
	 * parsing from BonusTesseraAcquistataNDTO to BonusTesseraAcquistataN for
	 * each parameter check if is an existing parameter, and set to the action
	 */
	public BonusTesseraAcquistataN visit(BonusTesseraAcquistataNDTO bonusTesseraAcquistataDTO)
			throws ParameterException;

	/**
	 * parsing from BonusTesseraPermessoNDTO to BonusTesseraPErmessoN for each
	 * parameter check if is an existing parameter, and set to the action
	 */
	public BonusTesseraPermessoN visit(BonusTesseraPermessoNDTO bonusTesseraPermessoDTO) throws ParameterException;

	/**
	 * parsing from ExitDTO to Exit check if the player is an existing player
	 * and set to the action
	 */
	public Exit visit(ExitDTO exitDTO) throws ParameterException;

	/**
	 * parsing from BonusGettoneNDTO to BonusGettoneN for each parameter check
	 * if is an existing parameter, and set to the action
	 */
	public BonusGettoneN visit(BonusGettoneNDTO bonusGettoneNDTO) throws ParameterException;

	/**
	 * parsing from ElezioneConsigliereVeloceDTO to ElezioneConsigliereVeloce
	 * for each parameter check if is an existing parameter, and set to the
	 * action
	 */
	public Chat visit(ChatDTO chatDTO);
}
