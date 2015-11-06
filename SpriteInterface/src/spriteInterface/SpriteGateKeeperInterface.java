package spriteInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SpriteGateKeeperInterface extends Remote 
{	
	public SpriteSessionInterface getSession() throws RemoteException;
}
