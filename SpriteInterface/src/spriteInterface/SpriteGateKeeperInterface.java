package spriteInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This interface defines the behaviour of the RMI remote object. 
 * It functions as a gateway, and only exists to build and return an object
 * of the game session.
 * @author Erik Dennis
 * @author Thomas Fulton
 *
 */
public interface SpriteGateKeeperInterface extends Remote 
{	
	/**
	 * This method returns a new spriteSession object (represents a new player's connection to the server)
	 * @return A new SpriteSessionInterface object
	 * @throws RemoteException
	 */
	public SpriteSessionInterface getSession() throws RemoteException;
}
