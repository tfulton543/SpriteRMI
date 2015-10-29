package spriteServer;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import spriteInterface.Constants;

public class SpriteGameServer {

	public static void main(String[] args) throws RemoteException, AlreadyBoundException {
		Sprite sprite = new Sprite();
		Registry registry = LocateRegistry.createRegistry(Constants.RMI_PORT);
		registry.bind(Constants.RMI_ID, sprite);
		System.out.println("Server up up and away!!");
	}

}
