package spriteClient;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import spriteInterface.SpriteInterface;
import spriteInterface.Constants;

public class SpriteClient {

	public static void main(String[] args) throws RemoteException, NotBoundException {
		Registry registry = LocateRegistry.getRegistry("localhost", Constants.RMI_PORT);
		SpriteInterface sprite = (SpriteInterface) registry.lookup(Constants.RMI_ID);
		System.out.println(sprite.test("test"));
	}

}
