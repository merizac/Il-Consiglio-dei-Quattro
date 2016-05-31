package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import gameDTO.gameDTO.GameStateDTO;
import gameDTO.gameDTO.GiocatoreDTO;
import view.clientNotify.ClientNotify;

public class ClientInHandler implements Runnable {

private ObjectInputStream socketIn;
private GameStateDTO gameStateDTO;
	
	public ClientInHandler(ObjectInputStream socketIn,GameStateDTO gameStateDTO){
		this.socketIn=socketIn;
		this.gameStateDTO=gameStateDTO;
		
	}

	@Override
	public void run() {

		while(true){
			try {
				ClientNotify notify=(ClientNotify) socketIn.readObject();
				notify.update(gameStateDTO);
				notify.stamp();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
