package spriteClient;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JFrame;

import spriteInterface.Constants;
import spriteInterface.SpriteGateKeeperInterface;
import spriteInterface.SpriteSessionInterface;

public class SpriteClient  
{
	private JFrame frame;

	public SpriteClient() throws RemoteException, NotBoundException
	{
		Registry registry = LocateRegistry.getRegistry("localhost", Constants.RMI_PORT);
		SpriteGateKeeperInterface spriteGateKeeperInterface = (SpriteGateKeeperInterface) registry.lookup(Constants.RMI_ID);
		SpriteSessionInterface spriteSessionInterface = spriteGateKeeperInterface.getSession();
		SpritePanel panel = new SpritePanel(spriteSessionInterface);
		frame = new JFrame("Bouncy Sprite");
		frame.setSize(spriteSessionInterface.getPanelSizeX(), spriteSessionInterface.getPanelSizeY());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
        frame.setResizable(false);
        frame.setVisible(true);
        panel.animate();
	}
	
	

	public static void main(String[] args) throws RemoteException, NotBoundException 
	{
		new SpriteClient();	
	}
}


