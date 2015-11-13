package spriteInterface;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * This class defines a sprite entity. This entity serves as the primary data
 * model in the sprite 'game' and is annotated so it can be persisted using
 * hibernate.
 * 
 * It has attributes which define the sprite's size, direction, colour, and
 * speed.
 * 
 * @author Thomas Fulton
 * @author Erik Dennis
 * 
 */
@Entity
public class Sprite implements Serializable {
	// this long is mandatory because the sprite implements serializable
	private static final long serialVersionUID = 1L;

	// this Random is used to randomly set the sprite's starting speed and
	// direction
	private final static Random random = new Random();

	// this int defines the size of the sprites that are created
	private final int SIZE = 10;

	// this int defines the maximum speed of a sprite object
	private final static int MAX_SPEED = 5;

	// this int serves as the primary key for a sprite on the database
	private int spriteId;

	// this int defines the x position of the sprite
	private int x;

	// this int defines the y position of the sprite
	private int y;

	// this int defines the speed at which the sprite is traveling in the x
	// plane
	private int dx;

	// this int defines the speed at which the sprite is traveling in the y
	// plane
	private int dy;

	// this Color defines the color of the sprite object
	private Color color;

	/**
	 * Default constructor. Sets the initial dx and dy (speed values) to an
	 * initil random value.
	 */
	public Sprite() {
		dx = random.nextInt(2 * MAX_SPEED) - MAX_SPEED;
		dy = random.nextInt(2 * MAX_SPEED) - MAX_SPEED;
	}

	/**
	 * @return the x
	 */
	@Column
	public int getX() {
		return x;
	}

	/**
	 * @param x
	 *            the x to set
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
	 * @param y
	 *            the y to set
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
	 * @param dx
	 *            the dx to set
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
	 * @param dy
	 *            the dy to set
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
	 * @param color
	 *            the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return the size
	 */
	@Transient
	public int getSize() {
		return SIZE;
	}

	/**
	 * Returns the sprite's ID value. This value serves as the primary key and
	 * is automatically generated.
	 * 
	 * @return int value of this sprite's spriteID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getSpriteId() {
		return spriteId;
	}

	/**
	 * 
	 * @param id
	 *            the value to set to the spriteID
	 */
	public void setSpriteId(int id) {
		this.spriteId = id;
	}

	/**
	 * This method draws an oval representing the location and size of the
	 * sprite object.
	 * 
	 * @param g
	 */
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(x, y, SIZE, SIZE);
	}

}// end of class
