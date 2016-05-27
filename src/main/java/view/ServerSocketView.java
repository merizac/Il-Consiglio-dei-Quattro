package view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import game.GameState;
import game.Giocatore;
import game.azioni.Azione;
import game.notify.ErrorNotify;
import game.notify.Notify;
import game.notify.NotifyGiocatoreCorrente;
import game.notify.NotifyGiocatori;
import gameDTO.azioniDTO.AzioneDTO;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitor;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitorImpl;
import server.Server;

public class ServerSocketView extends View implements Runnable {

	private Socket socket;
	private ObjectInputStream socketIn;
	private ObjectOutputStream socketOut;
	private GameState gameState;
	private Giocatore giocatore;
	private Server server;

	public ServerSocketView(Socket socket, GameState gameState, Server server) throws IOException {
		this.socket = socket;
		this.gameState = gameState;
		this.socketIn = new ObjectInputStream(socket.getInputStream());
		this.socketOut = new ObjectOutputStream(socket.getOutputStream());
		this.server = server;
	}

	@Override
	public void update(Notify o) {

		if ((o instanceof NotifyGiocatoreCorrente) && giocatore.equals(gameState.getGiocatoreCorrente())
				|| o instanceof NotifyGiocatori)

			try {
				System.out.println("Sending to the client " + o);
				this.socketOut.writeObject(o);
				this.socketOut.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}

	}

	@Override
	public void run() {
		

		server.aggiungiGiocatore(giocatore);

		while (true) {

			try {
				Object object = socketIn.readObject();
				if (object instanceof AzioneDTO) {
					AzioneVisitor azioneVisitor = new AzioneVisitorImpl(gameState, giocatore);
					AzioneDTO action = (AzioneDTO) object;
					try {
						Azione azione = action.accept(azioneVisitor);
						System.out.println("VIEW: received the action " + azione);
						this.notifyObserver(azione);
					} catch (IllegalArgumentException e) {
						this.socketOut.writeObject(new ErrorNotify(e.getMessage()));
					}
				}

			} catch (ClassNotFoundException | IOException e1) {
				e1.printStackTrace();
			}
		}
		
		
	}

	@Override
	public void input(Scanner input) {

	}
}
