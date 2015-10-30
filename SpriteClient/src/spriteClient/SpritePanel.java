package spriteClient;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JPanel;

public class SpritePanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	SpriteClient sprite;
	public Point point;
	
	public SpritePanel(){
		addMouseListener(new Mouse());
	}
	
	private Point newSprite(MouseEvent event) throws RemoteException, NotBoundException{
		//sprite = new SpriteClient();
		point = MouseInfo.getPointerInfo().getLocation();
		System.out.println("New Sprite Created");
		return point;
	}
	
	public void animate(){
		while(true){
		repaint();
		try {
			Thread.sleep(40);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
