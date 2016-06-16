package client.connessione;

import java.rmi.Remote;
import java.rmi.RemoteException;

import server.view.clientNotify.ClientNotify;

public interface ConnessioneRMIRemota extends Remote, Connessione {
	
	/**
	 * update the client
	 * @param notify
	 * @throws RemoteException
	 */
	public void aggiorna(ClientNotify notify) throws RemoteException;
	
}
