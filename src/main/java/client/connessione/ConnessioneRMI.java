package client.connessione;

import java.io.Serializable;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

import client.grafica.Grafica;
import common.azioniDTO.AzioneDTO;
import common.gameDTO.GameStateDTO;
import server.view.ServerRMIViewRemote;
import server.view.clientNotify.ClientNotify;

public class ConnessioneRMI extends UnicastRemoteObject implements Serializable, ConnessioneRMIRemota {

	private static final long serialVersionUID = 5904563829768967721L;
	private Grafica grafica;
	private GameStateDTO gameStateDTO;
	private transient ServerRMIViewRemote view;
	private static final int PORT = 1099;
	private static final String IP = "127.0.0.1";

	public ConnessioneRMI() throws RemoteException {
		super();
	}

	@Override
	public void start() throws RemoteException {
		String name = "GIOCa";
		Registry registry = null;
		try {
			registry = LocateRegistry.getRegistry(IP, PORT);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		if (registry != null) {
			try {
				view = (ServerRMIViewRemote) registry.lookup(name);
			} catch (NotBoundException e) {
				e.printStackTrace();
			} catch (AccessException e) {
				e.printStackTrace();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			try {
				view.register(this, gameStateDTO.getGiocatoreDTO());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			System.out.println("Connessione RMI creata");
		}
	}

	/**
	 * this method send the action to the server executing the method
	 * eseguiAzione of the ServerRMIViewRemote
	 */
	@Override
	public void inviaAzione(AzioneDTO azioneDTO) throws RemoteException {
		try {
			this.view.eseguiAzione(azioneDTO, this);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * this method update the client
	 */
	@Override
	public void aggiorna(ClientNotify notify) throws RemoteException {
		notify.update(gameStateDTO);
		notify.stamp(grafica);
	}

	/**
	 * this method disconnect the client
	 */
	@Override
	public void disconnetti() throws RemoteException {

	}

	/**
	 * @param grafica
	 *            the grafica to set
	 */
	@Override
	public void setGrafica(Grafica grafica) throws RemoteException {
		this.grafica = grafica;
	}

	/**
	 * @param gameStateDTO
	 *            the gameStateDTO to set
	 */
	@Override
	public void setGameStateDTO(GameStateDTO gameStateDTO) throws RemoteException {
		this.gameStateDTO = gameStateDTO;
	}

}
