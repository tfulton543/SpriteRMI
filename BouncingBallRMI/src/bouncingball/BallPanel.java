package bouncingball;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * This class defines the ball panel. 
 * It holds an array of balls, as wall as methods
 * that stop/start ball threads based on the number of 
 * balls inside of the box. 
 * @author Todd Kelley (revised by Thomas Fulton)
 */
@SuppressWarnings("serial")
public final class BallPanel extends JPanel
{
	ArrayList<Ball> balls = new ArrayList<Ball>();
	private int flashAvail = 2;
	private int ballCount = 0;
	private int boxX = 0;
	private int boxY = 0;
	private int boxWidth = 0;
	private int boxHeight = 0;
	
	/**
	 * Default constructor. 
	 * Builds a ball panel and adds a mouseListener to it. 
	 */
	public BallPanel()
	{
		addMouseListener(new Mouse());
	}
	
	/**
	 * This method creates an additional ball and adds it to the ball array. 
	 */
	private void newBall ()
	{
		Ball ball = new Ball(this);
		balls.add(ball);
		ballCount++;
		new Thread(ball).start();
		System.out.println("Number of balls: " + ballCount);
	}
	
    /**
     * This method should be called whenever a box leaves the box. 
     * It increments the number of available flashlights and resets the 
     * balls colour. It also prevents a ball from leaving the box when 
     * it would leave the box empty (ie: it is the last ball in box). 
     * @param ball The ball object which is leaving the box. 
     * @throws InterruptedException 
     */
	public synchronized void produce(Ball ball) throws InterruptedException
	{
		while(flashAvail > 0)
		{
			wait();
		}
		ball.setColor(Color.BLUE);
		ball.setSize(8);
		flashAvail++;
	    notifyAll();
	}
	
	/**
	 * This method consumes a flashlight.
	 * It should be called whenever a ball enters the box.
	 * It will force the thread to wait if there are no more 
	 * flashlights available (there are already 2 balls moving
	 * inside the box).
	 * @param ball The ball that executed this method.
	 * @throws InterruptedException
	 */
	public synchronized void consume(Ball ball) throws InterruptedException 
	{
		while(flashAvail < 1)
		{
			ball.setColor(Color.RED);
			wait();
		}
		ball.setColor(Color.WHITE);
		ball.setSize(24);
		flashAvail--;
	    notifyAll();
	}
	

	/**
     * Small method which determines if the ball is located inside of
     * the box which has been drawn on the frame. 
	 * @param ball The ball you wish to examine.
	 * @param x The x coordinate of the ball.
	 * @param y The y coordinate of the ball. 
	 * @return True if the ball is located inside of the box, otherwise
     * returns False
	 */
    public boolean inBox(int x, int y)
    {
    	if(x > getBoxX() 
    			&& y > getBoxY() 
    			&& x < (getBoxX() + getBoxWidth())
    			&& y < (getBoxY() + getBoxHeight()))
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }//end of inBox()
	
    /**
     * This method has an infinite loop which will draw (animate) the 
     * balls on the panel. 
     */
	public void animate()
	{
	    while (true)
	    {    	    
		    repaint();
	        
	        //sleep while waiting to display the next frame of the animation
	        try 
	        {
	            Thread.sleep(16);  // wake up roughly 60 frames per second
	        }
	        catch ( InterruptedException exception ) 
	        {
	            exception.printStackTrace();
	        }
	    }
	}//end of animate()
	
	private class Mouse extends MouseAdapter {
		@Override
	    public void mousePressed( final MouseEvent event ){
	        newBall();
	    }
	}
	
	/**
	 * This method determines the size of the box which will be drawn on the panel.
	 */
	private void determineBoxSize()
	{
		boxX = getX() + (getWidth() / 6);
		boxY = getY() + (getHeight() / 6);
		boxWidth = getWidth() - (boxX * 2);
		boxHeight = getHeight() - (boxY * 2);
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		determineBoxSize();
		g.fillRect(boxX, boxY, boxWidth, boxHeight);
		for(Ball ball: balls)
		{
			ball.draw(g);
		}
	}

	/**
	 * @return the flashAvail
	 */
	public int getFlashAvail() {
		return flashAvail;
	}

	/**
	 * @return the boxX
	 */
	public int getBoxX() {
		return boxX;
	}

	/**
	 * @return the boxY
	 */
	public int getBoxY() {
		return boxY;
	}

	/**
	 * @return the boxWidth
	 */
	public int getBoxWidth() {
		return boxWidth;
	}

	/**
	 * @return the boxHeight
	 */
	public int getBoxHeight() {
		return boxHeight;
	}
}
