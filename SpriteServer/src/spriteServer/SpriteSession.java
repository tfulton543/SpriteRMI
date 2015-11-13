package spriteServer;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import spriteInterface.Sprite;
import spriteInterface.SpriteSessionInterface;


/**
 * This class defines the implementation of the SpriteSessionInterface. 
 * @author Thomas Fulton
 * @author Erik Dennis
 */
public class SpriteSession extends UnicastRemoteObject implements SpriteSessionInterface
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public int spriteLocationX = 0;
	public int spriteLocationY = 0;
	
	/**
	 * 
	 */
	private Color spriteColor = Color.blue;
	
	public SpriteSession() throws RemoteException
	{
		super();
	}

	public boolean createSprite(MouseEvent e) throws RemoteException 
	{
		Sprite newSprite = new Sprite();
		newSprite.setX(e.getX());
		newSprite.setY(e.getY());
		newSprite.setColor(spriteColor);
		SpriteGameServer.getSpriteList().add(newSprite);
		return true;
	}
	
	public int getPanelSizeX() throws RemoteException 
	{
		return SpriteGameServer.getPanelSizeX();
	}

	public int getPanelSizeY() throws RemoteException 
	{
		return SpriteGameServer.getPanelSizeY();
	}

	public ArrayList<Sprite> getSprites() throws RemoteException 
	{
		return SpriteGameServer.getSpriteList();
	}
	
	@Override
	public void setColor(Color color)
	{
		spriteColor = color;
	}

	@Override
	public ArrayList<Color> getAvailbleColours() throws RemoteException 
	{
		return SpriteGameServer.getAvailableColours();
	}

	@Override
	public boolean removeColor(Color colour) 
	{		
		return SpriteGameServer.getAvailableColours().remove(colour);
	}
}//end of class
