package spriteInterface;

import java.awt.event.MouseEvent;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface SpriteInterface extends Remote 
{	
	public boolean createSprite(MouseEvent e) throws RemoteException;
	public int getPanelSizeX() throws RemoteException;
	public int getPanelSizeY() throws RemoteException;
	public ArrayList<Sprite> getSprites() throws RemoteException;
}
