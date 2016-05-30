package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import game.notify.GameStateStartNotify;
import game.notify.GiocatoreDTONotify;
import game.notify.Notify;
import game.notify.NotifyGiocatoreCorrente;
import game.notify.NotifyGiocatori;
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
				if(line instanceof GameStateStartNotify){
					((GameStateStartNotify) line).update(giocatoreDTO);
				}
				
				if(line instanceof NotifyGiocatori){
					((NotifyGiocatori) line).update(gameStateDTO);
					((NotifyGiocatori) line).stamp();
				}
				
				if(line instanceof GiocatoreDTONotify){
					((GiocatoreDTONotify) line).update(giocatoreDTO);
				}
				if(line instanceof NotifyGiocatoreCorrente){
					System.out.println(line);
					((NotifyGiocatoreCorrente) line).stamp();
				}
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
