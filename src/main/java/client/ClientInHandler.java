package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import game.notify.GameNotify;
import game.notify.GiocatoreDTONotify;
import game.notify.Notify;
import gameDTO.gameDTO.GameStateDTO;
import gameDTO.gameDTO.GiocatoreDTO;

public class ClientInHandler implements Runnable {

private ObjectInputStream socketIn;
private GameStateDTO gameStateDTO;
private GiocatoreDTO giocatoreDTO;
	
	public ClientInHandler(ObjectInputStream socketIn,GameStateDTO gameStateDTO, GiocatoreDTO giocatoreDTO){
		this.socketIn=socketIn;
		this.giocatoreDTO=giocatoreDTO;
		this.gameStateDTO=gameStateDTO;
		
	}

	@Override
	public void run() {

		while(true){
			
			try {
				Notify line=(Notify) socketIn.readObject();
				if(line instanceof GameNotify){
					System.out.println("gamenotify");
					line.update(gameStateDTO);
					line.stamp(gameStateDTO);
				}
				if(line instanceof GiocatoreDTONotify){
					System.out.println("ricevuta");
					((GiocatoreDTONotify) line).update(giocatoreDTO);
					((GiocatoreDTONotify) line).stamp(giocatoreDTO);
				}
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
