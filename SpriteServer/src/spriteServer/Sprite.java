package spriteServer;

import java.awt.Color;
import java.awt.Graphics;
import java.rmi.RemoteException;
import java.util.Random;

public class Sprite {

	public final static Random random = new Random();
	
	final static int SIZE = 10;
	final static int MAX_SPEED = 5;

	SpriteSession panel;
	private int x;
	private int y;
	private int dx;
	private int dy;
	private Color color = Color.BLUE;

    public Sprite (SpriteSession panel)
    {
    	this.panel = panel;
        x = panel.spriteLocationX;
        y = panel.spriteLocationY;
        dx = random.nextInt(2*MAX_SPEED) - MAX_SPEED;
        dy = random.nextInt(2*MAX_SPEED) - MAX_SPEED;
    }
    
    public void draw(Graphics g){
        g.setColor(color);
	    g.fillOval(x, y, SIZE, SIZE);
    }

    public void move() throws RemoteException{

        // check for bounce and make the sprite bounce if necessary
        //
        if (x < 0 && dx < 0){
            //bounce off the left wall 
            x = 0;
            dx = -dx;
        }
        if (y < 0 && dy < 0){
            //bounce off the top wall
            y = 0;
            dy = -dy;
        }
        if (x > panel.getPanelSizeX() - SIZE && dx > 0){
            //bounce off the right wall
        	x = panel.getPanelSizeX() - SIZE;
        	dx = - dx;
        }       
        if (y > panel.getPanelSizeY() - SIZE && dy > 0){
            //bounce off the bottom wall
        	y = panel.getPanelSizeY() - SIZE;
        	dy = -dy;
        }

        //make the ball move
        x += dx;
        y += dy;
    }
}
