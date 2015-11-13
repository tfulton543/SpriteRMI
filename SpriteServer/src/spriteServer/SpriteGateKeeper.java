package spriteServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import spriteInterface.SpriteGateKeeperInterface;
import spriteInterface.SpriteSessionInterface;

/**
 * This class defines the implementation of the SpriteGateKeeperInterface. 
 * This class is the RMI remote object 
 * @author Thomas Fulton
 * @author Erik Dennis
 */
public class SpriteGateKeeper extends UnicastRemoteObject implements SpriteGateKeeperInterface
{
	private static final long serialVersionUID = 1L;

	protected SpriteGateKeeper() throws RemoteException
	{
		super();
	}//end of constructor
	
	@Override
	public SpriteSessionInterface getSession() throws RemoteException
	{	
		return (SpriteSessionInterface) new SpriteSession();
	}

}//end of class
