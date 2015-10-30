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
	SpriteInterface sprite = (SpriteInterface) registry.lookup(Constants.RMI_ID);
	private JFrame frame;
	private SpritePanel panel = new SpritePanel();

	public SpriteClient() throws RemoteException, NotBoundException
	{
		
		frame = new JFrame("Bouncy Sprite");
		frame.setSize(sprite.getPanelSizeX(), sprite.getPanelSizeY());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
        frame.setVisible(true);
        
        sprite.getSpriteLocation(panel.point);
        
        
	}
	
	

	public static void main(String[] args) throws RemoteException, NotBoundException {
		new SpriteClient().panel.animate();
		
	}
}


