package spriteServer;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import spriteInterface.Constants;

public class SpriteGameServer 
{
	
	protected SpriteGameServer() throws RemoteException 
	{
		super();
	}

	private ArrayList<Sprite> spriteList = new ArrayList<Sprite>();
	
	
	
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
				move(sprite);
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
        if (sprite.getX() > panel.getPanelSizeX() - sprite.getSize() && sprite.getDx() > 0)
        {    
        	//bounce off the right wall
        	sprite.setX() = panel.getPanelSizeX() - SIZE;
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


	
	
	

	/**
	 * @return the spriteList
	 */
	public ArrayList<Sprite> getSpriteList()
	{
		return spriteList;
	}
}
