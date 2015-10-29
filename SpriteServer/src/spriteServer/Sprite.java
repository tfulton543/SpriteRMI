package spriteServer;
import java.rmi.*;
import java.rmi.server.*;

import spriteInterface.SpriteInterface;

public class Sprite extends UnicastRemoteObject implements SpriteInterface {

	protected Sprite() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean test(String username) throws RemoteException {
		if (username.equals("test")){
		return true;
		}
		return false;
	}

}
