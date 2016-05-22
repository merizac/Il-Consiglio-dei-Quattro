package client;

import java.io.IOException;
import java.io.ObjectInputStream;

import game.notify.Notify;

public class ClientInHandler implements Runnable {

private ObjectInputStream socketIn;
	
	public ClientInHandler(ObjectInputStream socketIn){
		this.socketIn=socketIn;
	}

	@Override
	public void run() {

		while(true){
			
			try {
				Notify line;
				line=(Notify) socketIn.readObject();
				line.stamp();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
