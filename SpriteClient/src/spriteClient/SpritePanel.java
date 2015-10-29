package spriteClient;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JPanel;

public class SpritePanel extends JPanel{

	private static final long serialVersionUID = 1L;
	SpriteClient sprite;
	
	public SpritePanel(){
		addMouseListener(new Mouse());
	}
	
	private void newSprite(MouseEvent event) throws RemoteException, NotBoundException{
		sprite = new SpriteClient();
		System.out.println("New Sprite Created");
	}
	
	public void animate(){
		while(true){
		repaint();
		}
	}
	
	private class Mouse extends MouseAdapter {
		@Override
		public void mousePressed(final MouseEvent event){
			try {
				newSprite(event);
			} catch (RemoteException | NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
//	@Override 
//	public void paintComponent(Graphics g){
//		super.paintComponent(g);
//		if (sprite != null){
//			sprite.draw(g);
//		}
//	}

}
