package spriteInterface;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface SpriteSessionInterface extends Remote 
{	
	public boolean createSprite(MouseEvent e) throws RemoteException;
	public void setColor(Color color) throws RemoteException;
	public int getPanelSizeX() throws RemoteException;
	public int getPanelSizeY() throws RemoteException;
	public ArrayList<Sprite> getSprites() throws RemoteException;
}
