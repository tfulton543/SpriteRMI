package spriteServer;
import java.awt.Point;
import java.rmi.*;
import java.rmi.server.*;

import spriteInterface.SpriteInterface;

public class SpriteSession extends UnicastRemoteObject implements SpriteInterface {

	private static final long serialVersionUID = 1L;
	private int panelSizeX = 400;
	private int panelSizeY = 400;
	public Point spriteLocation;
	public int spriteLocationX = 0;
	public int spriteLocationY = 0;
	
	protected SpriteSession() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean createSprite(String create) throws RemoteException {
		//while (true){
			if (create.equals("create")){
				
		
		return true;
		}
		return false;
	}
	
	@Override
	public int getPanelSizeX() throws RemoteException {
		return panelSizeX;
	}

	@Override
	public int getPanelSizeY() throws RemoteException {
		return panelSizeY;
	}

	@Override
	public Point getSpriteLocation(Point point) throws RemoteException {
		if (point != null){
			spriteLocationX = point.x;
			spriteLocationY = point.y;
		}
		return null;
	}
}
