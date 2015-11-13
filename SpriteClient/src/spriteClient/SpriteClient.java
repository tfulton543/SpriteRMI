package spriteClient;

import java.awt.BorderLayout;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import spriteInterface.Constants;
import spriteInterface.SpriteGateKeeperInterface;
import spriteInterface.SpriteSessionInterface;

/**
 * This class defines the functionality of the SpriteClient application. It has
 * methods which establish a connection to the sprite server, builds the display
 * panel/frame, and determines which colours are available.
 * 
 * @author Thomas Fulton
 * @author Erik Dennis
 */
public class SpriteClient {
	/**
	 * This is the application's main frame
	 */
	private JFrame frame;

	/**
	 * This is the local reference to the SpriteSessionInterface. The client
	 * uses this object to access the necessary methods on the server.
	 */
	private SpriteSessionInterface spriteSessionInterface;

	/**
	 * This panel is used to display sprites on screen, and responds to button
	 * clicks which create new sprites on the server
	 */
	private SpritePanel panel;

	/**
	 * Constructor. Interrogates the client for the server's IP, looks up the
	 * RMI registry, gets the reference to the remote objects, builds the view
	 * layer, and starts the animation routine (infinite loop) in the panel.
	 * 
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	public SpriteClient() throws RemoteException, NotBoundException {
		String registryIP = JOptionPane.showInputDialog("Enter Server IP");
		Registry registry = LocateRegistry.getRegistry(registryIP,
				Constants.RMI_PORT);
		SpriteGateKeeperInterface spriteGateKeeperInterface = (SpriteGateKeeperInterface) registry
				.lookup(Constants.RMI_ID);
		spriteSessionInterface = spriteGateKeeperInterface.getSession();

		// build the display frame and components
		initialize();

		panel.animate();
	}// end of constructor

	/**
	 * This method builds and displays the client view. It also contains the one
	 * actionListener (listSelection) which is called when the user selects
	 * their desired colour.
	 * 
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	private void initialize() throws RemoteException, NotBoundException {
		panel = new SpritePanel(spriteSessionInterface);
		frame = new JFrame("Bouncy Sprite");
		frame.setLayout(new BorderLayout());
		
		//sets the size of the view frame to that which is defined by the server
		//added minor correction to account for the width of the sprite
		frame.setSize(spriteSessionInterface.getPanelSizeX() + 6,
				spriteSessionInterface.getPanelSizeY() + 28);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(BorderLayout.CENTER, panel);
		frame.setResizable(false);
		frame.setVisible(true);
	}// end of initialize

	/**
	 * Main method. Instantiates an anonymous object of type SpriteClient.
	 * 
	 * @param args
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	public static void main(String[] args) throws RemoteException,
			NotBoundException {
		new SpriteClient();
	}// end of main

}// end of class

