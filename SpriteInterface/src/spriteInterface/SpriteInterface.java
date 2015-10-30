package spriteInterface;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SpriteInterface extends Remote {
	
	public boolean createSprite(MouseEvent e) throws RemoteException;
	public int getPanelSizeX() throws RemoteException;
	public int getPanelSizeY() throws RemoteException;
	public Point getSpriteLocation(Point point) throws RemoteException;

}
