package spriteServer;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import spriteInterface.Sprite;
import spriteInterface.SpriteInterface;

public class SpriteSession extends UnicastRemoteObject implements SpriteInterface {

	private static final long serialVersionUID = 1L;
	//public Point spriteLocation;
	public int spriteLocationX = 0;
	public int spriteLocationY = 0;
	SpriteGameServer server;
	
	public SpriteSession(SpriteGameServer server) throws RemoteException 
	{
		super();
		this.server = server;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean createSprite(MouseEvent e) throws RemoteException 
	{
		Sprite newSprite = new Sprite(e, Color.BLUE);
		server.getSpriteList().add(newSprite);
		return true;
	}
	
	@Override
	public int getPanelSizeX() throws RemoteException {
		return server.getPanelSizeX();
	}

	@Override
	public int getPanelSizeY() throws RemoteException {
		return server.getPanelSizeY();
	}

	@Override
	public ArrayList<Sprite> getSprites() throws RemoteException 
	{
		return server.getSpriteList();
	}
}
