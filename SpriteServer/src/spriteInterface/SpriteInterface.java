package spriteInterface;

import java.rmi.*;

public interface SpriteInterface extends Remote {
	
	public boolean test(String username) throws RemoteException;

}
