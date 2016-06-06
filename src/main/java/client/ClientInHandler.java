package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import gameDTO.gameDTO.GameStateDTO;
import gameDTO.gameDTO.GiocatoreDTO;
import view.clientNotify.ClientNotify;

public class ClientInHandler implements Runnable {

private Connessione connessione;
private GameStateDTO gameStateDTO;
	
	public ClientInHandler(Connessione connessione,GameStateDTO gameStateDTO){
		this.connessione=connessione;
		this.gameStateDTO=gameStateDTO;
		
	}

	@Override
	public void run() {

		//while(true){
			//try {
				//ClientNotify notify=(ClientNotify) socketIn.readObject();
				//notify.update(gameStateDTO);
				//notify.stamp();
			//} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
			//}
		//}
	}
	
	public void aggiorna(){
		
	}
}
