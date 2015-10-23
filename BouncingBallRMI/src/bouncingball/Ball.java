package bouncingball;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * @author Todd Kelley (revised by Thomas Fulton)
 * This class defines a ball object. 
 * Test1234565678900
 * It has attributes which define size, colour, and speed, and direction
 * It contains methods to move a ball and to determine where a ball is located.
 * It implements runnable. 
 */
public class Ball implements Runnable
{
	public final static Random random = new Random();
	
	private int size = 8;
	final static int MAX_SPEED = 3;
	
	private BallPanel panel;
	private Color color = Color.BLUE;
	
	private int x;
	private int y;
	
	private int dx;
	private int dy;
	boolean ballWasInBox;

	/**
	 * Default constructor. Determines the initial location of the ball,
	 * its size, and its direction.
	 * @param panel The panel to which you will add the ball.
	 */
    public Ball (BallPanel panel)
    {
    	this.panel = panel;
        x = random.nextInt(panel.getWidth());
        y = random.nextInt(panel.getHeight());
        dx = random.nextInt(2*MAX_SPEED) + 1;
        dy = random.nextInt(2*MAX_SPEED) + 1;
        ballWasInBox = false;
    }//end of constructor()

    /**
     * This method draws the ball onto the panel.
     * @param g The graphics object which will be used to draw the ball. 
     */
    public void draw(Graphics g)
    {
    	g.setColor(color);
	    g.fillOval(x, y, size, size);
    }//end of draw()
    
    /**
     * This method moves the ball based on its speed.
     * Its also determines if the ball has hit a "wall"
     * (the edge of the frame) and causes it to bounce
     * as necessary.
     */
    public void move(){

        // check for bounce and make the ball bounce if necessary
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
        if (x > panel.getWidth() - size && dx > 0){
            //bounce off the right wall
        	x = panel.getWidth() - size;
        	dx = - dx;
        }       
        if (y > panel.getHeight() - size && dy > 0){
            //bounce off the bottom wall
        	y = panel.getHeight() - size;
        	dy = -dy;
        }

        //make the ball move
        x += dx;
        y += dy;
    }//end of move()
 
    

    /**
     * This method contains an infinite loop which moves the ball,
     * determines if the ball is in the box, and calls appropriate 
     * produce/consume methods in the ballPanel. 
     */
	@Override
	public void run() 
	{
		while (true)
		{
			move();
			
			if(!ballWasInBox && panel.inBox(x, y))
			{
				try 
				{
					panel.consume(this);
					ballWasInBox = panel.inBox(x, y);
				} 
				catch (InterruptedException e) 
				{	
					e.printStackTrace();
				}
			}
			else if(ballWasInBox && !panel.inBox(x, y))
			{
				try
				{
					panel.produce(this);
					ballWasInBox = panel.inBox(x, y);
				}
				catch (InterruptedException e) 
				{	
					e.printStackTrace();
				}
			}

			
	        //sleep while waiting to display the next frame of the animation
	        try 
	        {
	            Thread.sleep(16);  // wake up roughly 60 frames per second
	        }
	        catch ( InterruptedException exception ) 
	        {
	            exception.printStackTrace();
	        }
	    }//end of infinite loop
	}//end of run()

	/**
	 * @return the sIZE
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param sIZE the sIZE to set
	 */
	public void setSize(int size) {
		this.size = size;
	}
	
    public void setColor(Color color)
    {
    	this.color = color;
    }
}//end of class
