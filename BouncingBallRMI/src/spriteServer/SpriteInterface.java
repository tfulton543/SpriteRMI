package spriteServer;

import java.rmi.*;

public interface SpriteInterface extends Remote {
	public int add(int a) throws RemoteException;
	public int test(int a) throws RemoteException; 
}
