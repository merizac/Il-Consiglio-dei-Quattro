package client.connessione;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import client.grafica.Grafica;
import common.azioniDTO.AzioneDTO;
import common.gameDTO.GameStateDTO;
import server.view.clientNotify.ClientNotify;

public class ConnessioneSocket implements Connessione, Runnable {

	private Grafica grafica;
	private GameStateDTO gameStateDTO;
	private static final int PORT = 29999;
	private static final String IP = "127.0.0.1";
	private Socket socket;
	private ObjectOutputStream socketOut;
	private ObjectInputStream socketIn;
	private boolean fine = false;

	/**
	 * create the socket and the stream from the server and to the server
	 * then listen for update from the server
	 */
	@Override
	public void run() {
		try {
			this.socket = new Socket(IP, PORT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			socketOut = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			socketIn = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Connessione socket creata");

		try {
			socketOut.writeObject(gameStateDTO.getGiocatoreDTO());
			socketOut.reset();
			socketOut.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		listen();
	}
	
	private void listen() {
		while (!fine) {
			
				ClientNotify notify;
				try {
					notify = (ClientNotify) socketIn.readObject();
				} catch (ClassNotFoundException | IOException e) {
					disconnetti();
					return;
				}
				grafica.notify(notify);
		}
		
	}

	/**
	 * send an action to the server with the socketOut
	 */
	@Override
	public void inviaAzione(AzioneDTO azioneDTO) {
		try {
			this.socketOut.writeObject(azioneDTO);
			this.socketOut.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * close the socket of the client
	 */
	@Override
	public void disconnetti() {
		try {
			this.socket.close();
		} catch (IOException e) {
			System.out.println("Client chiuso");
		}
	}

	/**
	 * set the grafica
	 * @param grafica 
	 */
	@Override
	public void setGrafica(Grafica grafica) {
		this.grafica = grafica;
	}

	/**
	 * set the gameState
	 * @param gameStateDTO
	 */
	@Override
	public void setGameStateDTO(GameStateDTO gameStateDTO) {
		this.gameStateDTO = gameStateDTO;
	}

	/**
	 * submit this thread with a single thread executor
	 */
	@Override
	public void start() throws RemoteException {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(this);
		executor.shutdown();
	}


}
