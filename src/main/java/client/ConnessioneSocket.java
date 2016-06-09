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
	private static final int PORT = 29999;
	private static final String IP = "127.0.0.1";
	private Socket socket;
	private ObjectOutputStream socketOut;
	private ObjectInputStream socketIn;
	private boolean fine = false;

	public ConnessioneSocket(String giocatore) {
		this.gameStateDTO = new GameStateDTO();
		GiocatoreDTO giocatoreDTO = new GiocatoreDTO();
		giocatoreDTO.setNome(giocatore);
		this.gameStateDTO.setGiocatoreDTO(giocatoreDTO);
	}

	@Override
	public void start() {
		try {
			this.socket = new Socket(IP, PORT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (socket != null) {
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
			listen();
		}
	}

	private void listen() {
		while (!fine) {
			try {
				ClientNotify notify = (ClientNotify) socketIn.readObject();
				notify.update(gameStateDTO);
				notify.stamp();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
				disconnetti();
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

	@Override
	public void disconnetti() {
		try {
			this.socket.close();
			this.socketIn.close();
			this.socketOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
