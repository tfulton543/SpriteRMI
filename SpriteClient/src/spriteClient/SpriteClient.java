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

import spriteInterface.Constants;
import spriteInterface.SpriteGateKeeperInterface;
import spriteInterface.SpriteSessionInterface;

public class SpriteClient  
{
	private JFrame frame;
	private JComboBox<Color> colorComboBox;

	public SpriteClient() throws RemoteException, NotBoundException
	{
		Registry registry = LocateRegistry.getRegistry("localhost", Constants.RMI_PORT);
		SpriteGateKeeperInterface spriteGateKeeperInterface = (SpriteGateKeeperInterface) registry.lookup(Constants.RMI_ID);
		final SpriteSessionInterface spriteSessionInterface = spriteGateKeeperInterface.getSession();
		SpritePanel panel = new SpritePanel(spriteSessionInterface);
		frame = new JFrame("Bouncy Sprite");
		colorComboBox = new JComboBox<Color>();
		colorComboBox.addItem(Color.BLACK);
		colorComboBox.addItem(Color.RED);
		
		colorComboBox.addItemListener(new ItemListener()
		{		
			@Override
			public void itemStateChanged(ItemEvent e) 
			{
				try
				{
					System.out.print("hank");
					spriteSessionInterface.setColor((Color) colorComboBox.getSelectedItem());
				} catch (RemoteException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	        
		frame.setLayout(new BorderLayout());
		frame.setSize(spriteSessionInterface.getPanelSizeX(), spriteSessionInterface.getPanelSizeY());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(BorderLayout.CENTER, panel);
		frame.add(BorderLayout.SOUTH, colorComboBox);
        frame.setResizable(false);
        frame.setVisible(true);
        panel.animate();
	}
	
	

	public static void main(String[] args) throws RemoteException, NotBoundException 
	{
		new SpriteClient();	
	}
}


