package spriteServer;
import java.rmi.*;
import java.rmi.server.*;

public class Sprite extends UnicastRemoteObject implements SpriteInterface {
	public Sprite () throws RemoteException {}
	
	public int add(int a) throws RemoteException {
		int result = a+1;
		return result;
	}
	
	public int test(int a) throws RemoteException {
		int result = a+1;
		return result;
	}

}
