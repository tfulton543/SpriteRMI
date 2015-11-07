package spriteClient;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.JPanel;
import spriteInterface.Sprite;
import spriteInterface.SpriteSessionInterface;

public class SpritePanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	SpriteSessionInterface spriteSessionInterface;
	
	public SpritePanel(SpriteSessionInterface SpriteSessionInterface)
	{
		addMouseListener(new Mouse());
		this.spriteSessionInterface = SpriteSessionInterface;
	}
	
	private void newSprite(MouseEvent event) throws RemoteException, NotBoundException
	{
		spriteSessionInterface.createSprite(event);
		System.out.println("New Sprite Created");
		
	}
	
	public void animate() throws RemoteException
	{
		while(true)
		{
			repaint();
		try 
		{
			Thread.sleep(40);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
	private class Mouse extends MouseAdapter 
	{
		@Override
		public void mousePressed(final MouseEvent event)
		{
			try 
			{
				newSprite(event);
			} catch (RemoteException | NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		
		try 
		{
			for(Sprite sprite: spriteSessionInterface.getSprites())
			{
				sprite.draw(g);
			}
		} catch (RemoteException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
