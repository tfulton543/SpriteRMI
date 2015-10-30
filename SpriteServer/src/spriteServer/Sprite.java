package spriteServer;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Sprite 
{

	public final static Random random = new Random();
	
	final int SIZE = 10;
	final static int MAX_SPEED = 5;

	//SpriteSession panel;
	private int x;
	private int y;
	private int dx;
	private int dy;
	private Color color;

    public Sprite (MouseEvent e, Color c)
    {
        x = e.getX();
        y = e.getY();
        dx = random.nextInt(2*MAX_SPEED) - MAX_SPEED;
        dy = random.nextInt(2*MAX_SPEED) - MAX_SPEED;
        color = c;
    }

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the dx
	 */
	public int getDx() {
		return dx;
	}

	/**
	 * @param dx the dx to set
	 */
	public void setDx(int dx) {
		this.dx = dx;
	}

	/**
	 * @return the dy
	 */
	public int getDy() {
		return dy;
	}

	/**
	 * @param dy the dy to set
	 */
	public void setDy(int dy) {
		this.dy = dy;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return SIZE;
	}
    
//    public void draw(Graphics g){
//        g.setColor(color);
//	    g.fillOval(x, y, SIZE, SIZE);
//    }
//
//    
}
