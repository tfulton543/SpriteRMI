package spriteServer;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import spriteInterface.Constants;

public class SpriteGameServer 
{
	private int panelSizeX = 400;
	private int panelSizeY = 400;
	private ArrayList<Sprite> spriteList = new ArrayList<Sprite>();
	
	
	protected SpriteGameServer() throws RemoteException 
	{
		super();
	}

	
	
	
	
	public static void main(String[] args) throws RemoteException, AlreadyBoundException 
	{
		SpriteGameServer gameServer = new SpriteGameServer();		
		SpriteSession sprite = new SpriteSession(gameServer);
		Registry registry = LocateRegistry.createRegistry(Constants.RMI_PORT);
		registry.bind(Constants.RMI_ID, sprite);
		System.out.println("Server up up and away!!");
		
		
		gameServer.animateSprites();
	}


	//infinite loop to constantly update all the balls yo
	private void animateSprites() 
	{
		while(true)
		{
			for(Sprite sprite: spriteList)
			{
				try
				{ 
					move(sprite); 
					Thread.sleep(60);
				}
				catch(InterruptedException e)
				{
					e.printStackTrace();
				}	
			}
		}	
	}
	
	
	public void move(Sprite sprite)
	{
        // check for bounce and make the sprite bounce if necessary
        if (sprite.getX() < 0 && sprite.getDx() < 0){
            //bounce off the left wall 
            sprite.setX(0);
            sprite.setDx(-sprite.getDx());
        }
        if (sprite.getY() < 0 && sprite.getDy() < 0){
            //bounce off the top wall
            sprite.setY(0);
            sprite.setDy(-sprite.getDy());
        }
        if (sprite.getX() > panelSizeX - sprite.getSize() && sprite.getDx() > 0)
        {    
        	//bounce off the right wall
        	sprite.setX(panelSizeX - sprite.getSize());
        	sprite.setDx(-sprite.getDx());
        }       
        if (sprite.getY() > panelSizeY - sprite.getSize() && sprite.getDy() > 0)
        {
            //bounce off the bottom wall
        	sprite.setY(panelSizeY - sprite.getSize());
        	sprite.setDy(-sprite.getDy());
        }

        //make the ball move
        sprite.setX(sprite.getX() + sprite.getDx());
        sprite.setY(sprite.getY() + sprite.getDy());
    }


	
	
	

	/**
	 * @return the spriteList
	 */
	public ArrayList<Sprite> getSpriteList()
	{
		return spriteList;
	}





	/**
	 * @return the panelSizeX
	 */
	public int getPanelSizeX() {
		return panelSizeX;
	}





	/**
	 * @return the panelSizeY
	 */
	public int getPanelSizeY() {
		return panelSizeY;
	}
}
