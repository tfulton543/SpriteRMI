package spriteInterface;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * This class defines a game session. It contains methods which enable the client
 * to interact with the game server. 
 * @author Thomas Fulton
 * @author Erik Dennis
 */
public interface SpriteSessionInterface extends Remote 
{	
	/**
	 * This method creates a new sprite object on the server with a starting position
	 * which corresponds to the client's mouse click
	 * @param e The MouseEvent created by the client clicking in the client panel
	 * @return True if the sprite was created successfully, otherwise returns false
	 * @throws RemoteException
	 */
	public boolean createSprite(MouseEvent e) throws RemoteException;
	
	/**
	 * This method allows the user to set the colour property of their unique spriteSession. 
	 * All sprites created by their spriteSession will use this colour.  
	 * @param color The colour which you wish to use
	 * @throws RemoteException
	 */
	public void setColor(Color color) throws RemoteException;
	
	/**
	 * This method gets the X (width) value of the playing area from the server. 
	 * It uses this value to setup the width of the display panel correctly. 
	 * @return int representing the width of the play area. 
	 * @throws RemoteException
	 */
	public int getPanelSizeX() throws RemoteException;
	
	/**
	 * This method gets the Y (height) value of the playing area from the server. 
	 * It uses this value to setup the height of the display panel correctly. 
	 * @return int representing the height of the play area. 
	 * @throws RemoteException
	 */
	public int getPanelSizeY() throws RemoteException;
	
	/**
	 * This method returns a list of all the sprite objects being managed by the server 
	 * @return ArrayList<Sprite> containing all the sprites being managed by the server
	 * @throws RemoteException
	 */
	public ArrayList<Sprite> getSprites() throws RemoteException;
	
	/**
	 * This method queries the game server to get the list of available colours
	 * @return ArrayList<Color> corresponding to the list of colours that are available to be used
	 * @throws RemoteException
	 */
	public ArrayList<Color> getAvailbleColours() throws RemoteException;

	/**
	 * This method removes a colour from the available colours list on the server. 
	 * @param colour The colour which you wish to remove from the list.
	 * @return
	 */
	public boolean removeColor(Color colour) throws RemoteException;
}//end of interface
