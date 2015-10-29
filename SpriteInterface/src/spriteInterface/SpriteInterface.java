package spriteInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SpriteInterface extends Remote {
	public boolean test(String username) throws RemoteException;
}
