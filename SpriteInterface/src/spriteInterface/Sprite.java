package spriteInterface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.Random;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Sprite implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public final static Random random = new Random();
	
	final int SIZE = 10;
	final static int MAX_SPEED = 5;

	//SpriteSession panel;
	private int spriteId;
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
    @Column 
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
	@Column 
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
	@Column 
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
	@Column 
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
	@Column 
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
	@Column 
	public int getSize() {
		return SIZE;
	}
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	public int getSpriteId(){
		return spriteId;
	}
	
	public void setSpriteId(int id){
		this.spriteId = id;
	}
    
    public void draw(Graphics g)
    {
        g.setColor(color);
	    g.fillOval(x, y, SIZE, SIZE);
    }

    
}
