package spriteClient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JComboBox;
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
	 * This comboBox is used by the client to select their colour
	 */
	private JComboBox<Color> colorComboBox;

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

		// this method instantiates a JComboBox object and fills it with Color
		// options
		// this allows users to select the color of their sprites on the fly
		colorComboBox = buildColorComboBox();

		// actionListener for the colorComboBox
		colorComboBox.addItemListener(new ItemListener() {
			// then the user selects a new color, this method changes the color
			// attribute
			// stored on the remote game session removes the colour from the
			// available colours list,
			// and disables the selection
			@Override
			public void itemStateChanged(ItemEvent e) {
				try {
					spriteSessionInterface.setColor((Color) colorComboBox
							.getSelectedItem());
					spriteSessionInterface.removeColor((Color) colorComboBox
							.getSelectedItem());
					colorComboBox.setEnabled(false);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}// end of itemStateChanges
		});// end of addItemListener

		frame.setLayout(new BorderLayout());
		frame.setSize(spriteSessionInterface.getPanelSizeX(),
				spriteSessionInterface.getPanelSizeY());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(BorderLayout.CENTER, panel);
		frame.add(BorderLayout.SOUTH, colorComboBox);
		frame.setResizable(false);
		frame.setVisible(true);
	}// end of initialize

	/**
	 * This method builds a JComboBox<Color> and fills it with color options
	 * 
	 * @throws RemoteException
	 */
	private JComboBox<Color> buildColorComboBox() throws RemoteException {
		JComboBox<Color> colourComboBox = new JComboBox<Color>();

		// loops through the colours that are available on the server and adds
		// them to the comboBox
		for (Color color : spriteSessionInterface.getAvailbleColours()) {
			colourComboBox.addItem(color);
		}

		return colourComboBox;
	}// end of buildColorComboBox

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

