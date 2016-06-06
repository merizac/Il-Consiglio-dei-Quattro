package view;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import client.ConnessioneRMIRemota;
import game.GameState;
import game.Giocatore;
import game.azioni.Azione;
import game.notify.Notify;
import gameDTO.azioniDTO.AzioneDTO;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitor;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitorImpl;
import gameDTO.gameDTO.GiocatoreDTO;
import server.Server;

public class ServerRMIView extends View implements ServerRMIViewRemote, Runnable{

	private GameState gameState;
	private Server server;
	private Map<ConnessioneRMIRemota, Giocatore> giocatori;

	/**
	 * @param giocatore
	 * @param server
	 */
	public ServerRMIView(Server server) {
		this.server = server;
		this.giocatori=new HashMap<>();
	}

	@Override
	public void update(Notify o) {
		for(ConnessioneRMIRemota c: giocatori.keySet()){
			if(o.daInviare(this.giocatori.get(c))){
				try {
					c.aggiorna(o.notifyToClientNotify());
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				};
			}
		}
	}

	@Override
	public void eseguiAzione(AzioneDTO azioneDTO, ConnessioneRMIRemota connessioneRMIRemota) throws RemoteException {
		AzioneVisitor azioneVisitor = new AzioneVisitorImpl(gameState, this.giocatori.get(connessioneRMIRemota));
		Azione azione = azioneDTO.accept(azioneVisitor);
		try{
		if (azione.isTurno(this.giocatori.get(connessioneRMIRemota), gameState)) {
			this.notifyObserver(azione);
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}


	@Override
	public void run() {
		

	}

	@Override
	public ServerRMIViewRemote register(ConnessioneRMIRemota connessioneRMIRemota, GiocatoreDTO giocatoreDTO)
			throws RemoteException {
		Giocatore giocatore = new Giocatore(giocatoreDTO.getNome());
		this.giocatori.put(connessioneRMIRemota, giocatore);
		server.aggiungiGiocatoreRMI(giocatore, this);
		return this;
	}

	@Override
	public void setGameState(GameState gameState) {
		this.gameState=gameState;
	}

}
