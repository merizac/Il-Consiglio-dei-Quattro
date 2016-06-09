package view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import game.GameState;
import game.Giocatore;
import game.azioni.Azione;
import game.azioni.Exit;
import game.notify.Notify;
import gameDTO.azioniDTO.AzioneDTO;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitor;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitorImpl;
import gameDTO.gameDTO.GiocatoreDTO;
import server.Server;
import view.clientNotify.MessageClientNotify;

public class ServerSocketView extends View implements Runnable {

	private Socket socket;
	private ObjectInputStream socketIn;
	private ObjectOutputStream socketOut;
	private GameState gameState;
	private Giocatore giocatore;

	public ServerSocketView(Socket socket) throws IOException {
		this.socket = socket;
		this.socketIn = new ObjectInputStream(socket.getInputStream());
		this.socketOut = new ObjectOutputStream(socket.getOutputStream());
	}

	@Override
	public void update(Notify o) {
		if (o.daInviare(giocatore)) {
			System.out.println("[SERVER] Inviata notifica " + o + " al giocatore " + giocatore.getNome());
			try {
				this.socketOut.writeObject(o.notifyToClientNotify());
				this.socketOut.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {

		try {
			GiocatoreDTO giocatoreDTO = (GiocatoreDTO) socketIn.readObject();
			this.giocatore = new Giocatore(giocatoreDTO.getNome());

		} catch (ClassNotFoundException | IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			Server.getInstance().aggiungiGiocatore(giocatore, this);
		} catch (Exception e) {
			e.printStackTrace();
		}

		while (true) {

			try {
				Object object = socketIn.readObject();
				if (object instanceof AzioneDTO) {

					AzioneDTO action = (AzioneDTO) object;
					AzioneVisitor azioneVisitor = new AzioneVisitorImpl(gameState, giocatore);
					Azione azione = action.accept(azioneVisitor);
					System.out
							.println("[SERVER] Ricevuta l'azione " + azione + " dal giocatore " + giocatore.getNome());
					if (azione instanceof Exit) {
						disconnetti();
						this.notifyObserver(azione);
					} else if (azione.isTurno(giocatore, gameState)
							&& gameState.getStato().getAzioni().contains(azione)) {
						this.notifyObserver(azione);
						System.out.println("[SERVER] Inviata l'azione " + azione);
					} else {
						this.socketOut.writeObject(new MessageClientNotify("Non è il tuo turno"));
						this.socketOut.flush();
					}

				}
			} catch (SocketException e) {
				disconnetti();
			} catch (ClassNotFoundException | IOException e1) {
				e1.printStackTrace();
			}

		}
	}

	/**
	 * @param gameState
	 *            the gameState to set
	 */
	@Override
	public void setGameState(GameState gameState) {
		this.gameState = gameState;
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
