package client.connessione;

import java.io.IOException;
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
	private transient Grafica grafica;
	private GameStateDTO gameStateDTO;
	private transient ServerRMIViewRemote view;
	private static final int PORT = 1099;
	private static final String IP = "127.0.0.1";
	private static final Logger log= Logger.getLogger( ConnessioneRMI.class.getName() );

	/**
	 * constructor of ConnessioneRMI
	 * 
	 * @throws RemoteException
	 */
	public ConnessioneRMI() throws RemoteException {
		super();
	}

	/*
	 * set the registry, look on the server for the ServerRMIView , register the
	 * player
	 */
	@Override
	public void start() {
		String name = "GIOCO";
		Registry registry = null;
		try {
			registry = LocateRegistry.getRegistry(IP, PORT);
		} catch (RemoteException e) {
			log.log(Level.SEVERE, "Problema nella localizzazione del registry", e);
		}
		if (registry != null) {
			try {
				view = (ServerRMIViewRemote) registry.lookup(name);
			} catch (NotBoundException e) {
				log.log(Level.SEVERE, "L'oggetto remoto GIOCO non è stato trovato sul registry", e);
			} catch (AccessException e) {
				log.log(Level.SEVERE, "Non puoi eseguire questa operazione", e);
			} catch (RemoteException e) {
				log.log(Level.SEVERE, "Problema nell'invocazione del metodo", e);
			}
			try {
				view.register(this, gameStateDTO.getGiocatoreDTO());
			} catch (RemoteException e) {
				log.log(Level.SEVERE, "Problema nell'invocazione del metodo", e);
			}
		}
	}

	/**
	 * send the action to the server executing the method eseguiAzione of the
	 * ServerRMIViewRemote
	 */
	@Override
	public void inviaAzione(AzioneDTO azioneDTO) throws RemoteException {
		try {
			this.view.eseguiAzione(azioneDTO, this);
		} catch (RemoteException e) {
			log.log(Level.SEVERE, "Problema nell'invocazione del metodo", e);
		}
	}

	/**
	 * update the model on the client
	 */
	@Override
	public void aggiorna(ClientNotify notify) throws RemoteException {
		try {
			notify.stamp(grafica, gameStateDTO);
		} catch (IOException e) {
			log.log(Level.SEVERE, "Problema nell'apertura nell'apertura dell'Applicazione", e);
		}
	}

	/**
	 * this method disconnect the client
	 */
	@Override
	public void disconnetti() throws RemoteException {

	}

	/**
	 * set the grafica
	 * 
	 * @param grafica
	 */
	@Override
	public void setGrafica(Grafica grafica) throws RemoteException {
		this.grafica = grafica;
	}

	/**
	 * set the gameState
	 * 
	 * @param gameStateDTO
	 */
	@Override
	public void setGameStateDTO(GameStateDTO gameStateDTO) throws RemoteException {
		this.gameStateDTO = gameStateDTO;
	}

}
