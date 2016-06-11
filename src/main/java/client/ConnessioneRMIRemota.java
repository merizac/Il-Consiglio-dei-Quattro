package client;

import java.rmi.Remote;
import java.rmi.RemoteException;

import server.view.clientNotify.ClientNotify;

public interface ConnessioneRMIRemota extends Remote, Connessione {
	
	public void aggiorna(ClientNotify notify) throws RemoteException;
	
	@Override
	public void start() throws RemoteException;
	
}
