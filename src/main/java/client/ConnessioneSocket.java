package client;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
			socketOut.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		listen();
	}
	
	/**
	 * listen for updating from the server
	 */
	private void listen() {
		while (!fine) {
			try {
				ClientNotify notify = (ClientNotify) socketIn.readObject();
				notify.update(gameStateDTO);
				notify.stamp(grafica);
			} catch (EOFException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param grafica the grafica to set
	 */
	@Override
	public void setGrafica(Grafica grafica) {
		this.grafica = grafica;
	}

	/**
	 * @param gameStateDTO the gameStateDTO to set
	 */
	@Override
	public void setGameStateDTO(GameStateDTO gameStateDTO) {
		this.gameStateDTO = gameStateDTO;
	}

	@Override
	public void start() throws RemoteException {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(this);
	}


}
