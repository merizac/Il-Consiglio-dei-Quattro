package view;

import java.rmi.RemoteException;

import client.ConnessioneRMI;
import game.GameState;
import game.Giocatore;
import game.azioni.Azione;
import game.notify.Notify;
import gameDTO.azioniDTO.AzioneDTO;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitor;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitorImpl;
import gameDTO.gameDTO.GiocatoreDTO;
import server.Server;

public class ServerRMIView extends View implements ServerRMIViewRemote, Runnable {

	private  GameState gameState;
	private Giocatore giocatore;
	private Server server;
	private ConnessioneRMI connessione;

	/**
	 * @param giocatore
	 * @param server
	 */
	public ServerRMIView(Server server, ConnessioneRMI connessione) {
		this.server = server;
		this.connessione = connessione;
	}

	@Override
	public void update(Notify o) {
		if (o.daInviare(giocatore))
				this.connessione.aggiorna(o.notifyToClientNotify());
	}

	@Override
	public void eseguiAzione(AzioneDTO azioneDTO) throws RemoteException {
		AzioneVisitor azioneVisitor = new AzioneVisitorImpl(gameState, giocatore);
		Azione azione = azioneDTO.accept(azioneVisitor);
		if (azione.isTurno(giocatore, gameState)) {
			this.notifyObserver(azione);
		}

	}

	@Override
	public void creaGiocatore(GiocatoreDTO giocatoreDTO) throws RemoteException {
		this.giocatore = new Giocatore(giocatoreDTO.getNome());
	}

	@Override
	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	@Override
	public void run() {

	}

}
