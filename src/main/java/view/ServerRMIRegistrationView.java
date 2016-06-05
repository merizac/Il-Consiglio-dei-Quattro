package view;

import java.rmi.RemoteException;

import client.ConnessioneRMI;
import game.Giocatore;
import server.Server;

public class ServerRMIRegistrationView implements ServerRMIRegistrationViewRemote {
	
	private Server server;
	
	/**
	 * @param server
	 */
	public ServerRMIRegistrationView(Server server) {
		this.server = server;
	}


	@Override
	public ServerRMIViewRemote register(ConnessioneRMI connessioneRMI) throws RemoteException {
		ServerRMIView serverRMIView=new ServerRMIView(this.server, connessioneRMI);
		server.aggiungiGiocatore(new Giocatore(connessioneRMI.getGiocatoreDTO().getNome()), serverRMIView);
		return serverRMIView;
	}

}
