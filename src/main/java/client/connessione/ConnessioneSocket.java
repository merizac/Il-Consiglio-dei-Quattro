package client.connessione;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

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
	private static final Logger log = Logger.getLogger(ConnessioneSocket.class.getName());

	/**
	 * create the socket and the stream from the server and to the server then
	 * listen for update from the server
	 */
	@Override
	public void run() {
		try {
			this.socket = new Socket(IP, PORT);
		} catch (IOException e) {
			log.log(Level.SEVERE, "Errore nella creazione del socket", e);
		}
		try {
			socketOut = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			log.log(Level.SEVERE, "Errore nella creazione dell'ObjectOutputStream", e);
		}
		try {
			socketIn = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			log.log(Level.SEVERE, "Errore nella creazione dell'ObjectInputStream", e);
		}

		try {
			socketOut.writeObject(gameStateDTO.getGiocatoreDTO());
			socketOut.reset();
			socketOut.flush();
		} catch (IOException e) {
			log.log(Level.SEVERE, "Errore nell'invio del giocatore al server", e);
		}
		listen();
		
	}

	private void listen() {
		while (!fine) {

			try {
				ClientNotify notify = (ClientNotify) socketIn.readObject();
				notify.stamp(grafica, gameStateDTO);
			} catch (ClassNotFoundException e) {
				log.log(Level.SEVERE, "Errore nel cast della notify, ricevuta un'oggetto di tipo diverso da ClientNotify", e);
			} catch (IOException e) {
				disconnetti();
				break;
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
			this.socketOut.reset();
		} catch (IOException e) {
			log.log(Level.SEVERE, "Errore nell'invio dell'azione al server", e);
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
			grafica.mostraMessaggio("Connessione chiusa");
		}
	}

	/**
	 * set the grafica
	 * 
	 * @param grafica
	 */
	@Override
	public void setGrafica(Grafica grafica) {
		this.grafica = grafica;
	}

	/**
	 * set the gameState
	 * 
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
	public void start() {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(this);
		executor.shutdown();
	}

}
