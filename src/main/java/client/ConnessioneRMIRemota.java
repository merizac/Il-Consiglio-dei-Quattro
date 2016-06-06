package client;

import java.rmi.Remote;
import java.rmi.RemoteException;

import gameDTO.gameDTO.GiocatoreDTO;
import view.clientNotify.ClientNotify;

public interface ConnessioneRMIRemota extends Remote, Connessione {
	
	public void aggiorna(ClientNotify notify) throws RemoteException;

}
