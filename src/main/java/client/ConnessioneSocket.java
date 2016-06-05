package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import gameDTO.azioniDTO.AzioneDTO;
import gameDTO.gameDTO.GameStateDTO;
import gameDTO.gameDTO.GiocatoreDTO;
import view.clientNotify.ClientNotify;

public class ConnessioneSocket implements Connessione {

	private GameStateDTO gameStateDTO;
	private final static int PORT = 29999;
	private final static String IP = "127.0.0.1";
	private ObjectOutputStream socketOut;
	private ObjectInputStream socketIn;

	public ConnessioneSocket(String giocatore) {
		this.gameStateDTO = new GameStateDTO();
		GiocatoreDTO giocatoreDTO = new GiocatoreDTO();
		giocatoreDTO.setNome(giocatore);
		this.gameStateDTO.setGiocatoreDTO(giocatoreDTO);
	}

	@Override
	public void start() {
		Socket socket = null;
		try {
			socket = new Socket(IP, PORT);
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
		System.out.println("Connection create");

		try {
			socketOut.writeObject(gameStateDTO.getGiocatoreDTO());
			socketOut.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ExecutorService executor = Executors.newFixedThreadPool(1);
		executor.submit(new ClientOutHandler(this, gameStateDTO));
		//executor.submit(new ClientInHandler(this, gameStateDTO));
		listen();
	}

	private void listen() {
		while(true){
			try {
				ClientNotify notify=(ClientNotify) socketIn.readObject();
				notify.update(gameStateDTO);
				notify.stamp();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}		
	}

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

}
