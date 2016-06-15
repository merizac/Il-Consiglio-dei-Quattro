package server.view;

import java.rmi.Remote;
import java.rmi.RemoteException;

import client.connessione.ConnessioneRMIRemota;
import common.azioniDTO.AzioneDTO;
import common.gameDTO.GiocatoreDTO;

public interface ServerRMIViewRemote extends Remote {

	public void eseguiAzione(AzioneDTO azioneDTO, ConnessioneRMIRemota connessioneRMIRemota) throws RemoteException;

	public ServerRMIViewRemote register(ConnessioneRMIRemota connessioneRMIRemota, GiocatoreDTO giocatoreDTO)
			throws RemoteException;

	public void unregister(ConnessioneRMIRemota connessioneRMIRemota) throws RemoteException;

}
