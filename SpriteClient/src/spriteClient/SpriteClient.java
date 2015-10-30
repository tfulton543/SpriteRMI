package spriteClient;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JFrame;
import spriteInterface.SpriteInterface;
import spriteInterface.Constants;

public class SpriteClient  
{
	
	Registry registry = LocateRegistry.getRegistry("localhost", Constants.RMI_PORT);
	SpriteInterface spriteInterface = (SpriteInterface) registry.lookup(Constants.RMI_ID);
	private JFrame frame;
	private SpritePanel panel = new SpritePanel(spriteInterface);

	public SpriteClient() throws RemoteException, NotBoundException
	{
		
		frame = new JFrame("Bouncy Sprite");
		frame.setSize(spriteInterface.getPanelSizeX(), spriteInterface.getPanelSizeY());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
        frame.setVisible(true);
        
	}
	
	

	public static void main(String[] args) throws RemoteException, NotBoundException 
	{
		new SpriteClient().panel.animate();
		
	}
}


