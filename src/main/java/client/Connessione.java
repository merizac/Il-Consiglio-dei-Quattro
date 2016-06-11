package client;

import java.rmi.RemoteException;

import common.azioniDTO.AzioneDTO;

public interface Connessione {
	/**
	 * this method start the connection
	 * @throws RemoteException
	 */
	public void start() throws RemoteException;

	/**
	 * this method send the action to the server
	 * @param action
	 * @throws RemoteException
	 */
	public void inviaAzione(AzioneDTO action) throws RemoteException;
	
	/**
	 * this method disconnect the client
	 * @throws RemoteException
	 */
	public void disconnetti() throws RemoteException;

}
