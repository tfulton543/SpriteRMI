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

public class SpriteClient  
{
	private JFrame frame;
	private JComboBox<Color> colorComboBox;
	SpriteSessionInterface spriteSessionInterface;
	SpritePanel panel;

	public SpriteClient() throws RemoteException, NotBoundException
	{
		String registryIP = JOptionPane.showInputDialog("Enter Server IP");
		Registry registry = LocateRegistry.getRegistry(registryIP, Constants.RMI_PORT);
		SpriteGateKeeperInterface spriteGateKeeperInterface = (SpriteGateKeeperInterface) registry.lookup(Constants.RMI_ID);
		spriteSessionInterface = spriteGateKeeperInterface.getSession();
		
		//build the display frame and components
		initialize();

        panel.animate();
	}
	
	

	private void initialize() throws RemoteException, NotBoundException
	{
		panel = new SpritePanel(spriteSessionInterface);
		frame = new JFrame("Bouncy Sprite");
		
		//this method instantiates a JComboBox object and fills it with Color options
		//this allows users to select the color of their sprites on the fly
		colorComboBox = buildColorComboBox();
		
		//actionListener for the colorComboBox
		colorComboBox.addItemListener(new ItemListener()
		{		
			//then the user selects a new color, this method changes the color attribute
			//stored on the remote game session removes the colour from the available colours list, 
			//and disables the selection
			@Override
			public void itemStateChanged(ItemEvent e) 
			{
				try
				{
					spriteSessionInterface.setColor((Color) colorComboBox.getSelectedItem());
					spriteSessionInterface.removeColor((Color) colorComboBox.getSelectedItem());
					colorComboBox.setEnabled(false);
				}
				catch (RemoteException e1) 
				{
					e1.printStackTrace();
				}
			}//end of itemStateChanges
		});//end of addItemListener
	        
		frame.setLayout(new BorderLayout());
		frame.setSize(spriteSessionInterface.getPanelSizeX(), spriteSessionInterface.getPanelSizeY());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(BorderLayout.CENTER, panel);
		frame.add(BorderLayout.SOUTH, colorComboBox);
        frame.setResizable(false);
        frame.setVisible(true);	
	}


	/**
	 * This method builds a JComboBox<Color> and fills it with color options
	 * @throws RemoteException 
	 */
	private JComboBox<Color> buildColorComboBox() throws RemoteException
	{
		JComboBox<Color> colourComboBox = new JComboBox<Color>();
		
		//loops through the colours that are available on the server and adds them to the comboBox
		for (Color color : spriteSessionInterface.getAvailbleColours())
		{
			colourComboBox.addItem(color);
		}
		
		return colourComboBox;
	}//end of buildColorComboBox



	public static void main(String[] args) throws RemoteException, NotBoundException 
	{
		new SpriteClient();	
	}//end of main
	
	
}//end of class


