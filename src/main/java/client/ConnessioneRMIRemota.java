package client;

import java.rmi.Remote;
import java.rmi.RemoteException;

import server.view.clientNotify.ClientNotify;

public interface ConnessioneRMIRemota extends Remote, Connessione {
	
	/**
	 * this method update the client
	 * @param notify
	 * @throws RemoteException
	 */
	public void aggiorna(ClientNotify notify) throws RemoteException;
	
	/**
	 * this method start the connessioneRMIRemota
	 */
	@Override
	public void start() throws RemoteException;
	
}
