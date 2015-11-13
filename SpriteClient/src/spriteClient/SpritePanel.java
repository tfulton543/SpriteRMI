package spriteClient;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.JPanel;
import spriteInterface.Sprite;
import spriteInterface.SpriteSessionInterface;

/**
 * This class defines the behaviours of the Sprite panel which serves as a view
 * layer for the client
 * 
 * @author Thomas Fulton
 * @author Erik Dennis
 */
public class SpritePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Local object of the SpriteSessionInterface
	 */
	SpriteSessionInterface spriteSessionInterface;

	/**
	 * Constructor. Accepts a SpriteSessionInterface argument from the client
	 * and assigns it to a local reference. Also adds the mouseListener which
	 * watched for clicks and creates new sprites at that location.
	 * 
	 * @param SpriteSessionInterface
	 *            object of type SpriteSessionInterface which has been retrieved
	 *            from the RMI registry
	 */
	public SpritePanel(SpriteSessionInterface SpriteSessionInterface) {
		addMouseListener(new Mouse());
		this.spriteSessionInterface = SpriteSessionInterface;
	}

	/**
	 * This method creates a new sprite on the server by way of the
	 * SpriteSessionInterface.
	 * 
	 * @param event
	 *            MouseEvent containing x and y coordinates which will be used
	 *            to generate the new sprite.
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	private void newSprite(MouseEvent event) throws RemoteException,
			NotBoundException {
		spriteSessionInterface.createSprite(event);
		System.out.println("New Sprite Created");
	}

	/**
	 * This method defines an infinite loop which continuously redraws the
	 * sprites which are being managed/moved by the server (refreshes
	 * 24times/second
	 * 
	 * @throws RemoteException
	 */
	public void animate() throws RemoteException {
		// infinite loop, calls the repaint method then waits for
		// 40 milliseconds
		while (true) {
			repaint();
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}// end of infinite loop
	}// end of animate

	/**
	 * This inner class defines the actions which will be performed upon
	 * mouseclick
	 */
	private class Mouse extends MouseAdapter {
		@Override
		public void mousePressed(final MouseEvent event) {
			try {
				// call the method which creates a new sprite on the server
				newSprite(event);
			} catch (RemoteException | NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}// end of inner class

	/**
	 * This method redraws each sprite which is being managed by the server onto
	 * the spritePanel
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		try {
			for (Sprite sprite : spriteSessionInterface.getSprites()) {
				sprite.draw(g);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}// end of paintComponent()

}// end of class
