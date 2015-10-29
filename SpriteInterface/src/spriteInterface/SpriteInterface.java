package spriteInterface;

import java.awt.Point;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SpriteInterface extends Remote {
	
	public boolean createSprite(String create) throws RemoteException;
	public int getPanelSizeX() throws RemoteException;
	public int getPanelSizeY() throws RemoteException;
	public Point getSpriteLocation(Point point) throws RemoteException;

}
