package view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import game.GameState;
import game.Giocatore;
import game.azioni.Azione;
import game.notify.Notify;
import gameDTO.azioniDTO.AzioneDTO;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitor;
import gameDTO.azioniDTO.azioneVisitor.AzioneVisitorImpl;
import gameDTO.gameDTO.GiocatoreDTO;
import server.Server;
import view.clientNotify.ErrorClientNotify;

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
		if (o.daInviare(giocatore)) {
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
		server.aggiungiGiocatore(giocatore);
		AzioneVisitor azioneVisitor = new AzioneVisitorImpl(gameState, giocatore);

		while (true) {

			try {
				Object object = socketIn.readObject();
				if (object instanceof AzioneDTO) {

					AzioneDTO action = (AzioneDTO) object;

					Azione azione = action.accept(azioneVisitor);
					System.out.println("VIEW: received the action " + azione);
					if (azione.isTurno(giocatore, gameState)){
						System.out.println("azione da eseguire: "+azione.isTurno(giocatore, gameState));
						this.notifyObserver(azione);
					}
					else {
						this.socketOut.writeObject(new ErrorClientNotify("Non è il tuo turno"));
						this.socketOut.flush();
					}

				}
			} catch (ClassNotFoundException | IOException e1) {
				e1.printStackTrace();
			}
		}

	}

}
