package spriteServer;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import spriteInterface.Constants;
import spriteInterface.Sprite;

public class SpriteGameServer 
{
	private static int panelSizeX = 400;
	private static int panelSizeY = 400;
	private static ArrayList<Sprite> spriteList = new ArrayList<Sprite>();
	
	
		
	protected SpriteGameServer() throws RemoteException 
	{
		super();
	}

	public static void main(String[] args) throws RemoteException, AlreadyBoundException 
	{
		
		SpriteGameServer gameServer = new SpriteGameServer();		
		SpriteGateKeeper gateKeeper = new SpriteGateKeeper();		
		Registry registry = LocateRegistry.createRegistry(Constants.RMI_PORT);
		registry.bind(Constants.RMI_ID, gateKeeper);
		System.out.println("Server up up and away!!");
		
		gameServer.animateSprites();
	}


	//infinite loop to constantly update all the balls yo
	@SuppressWarnings("deprecation")
	private void animateSprites() 
	{
		Configuration config = new Configuration()
				.addAnnotatedClass(spriteInterface.Sprite.class)
				.configure("hibernate.cfg.xml");
		
		new SchemaExport(config).create(true, true);
		StandardServiceRegistryBuilder sRBuilder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
		ServiceRegistry sR = sRBuilder.build();
		SessionFactory factory = config.buildSessionFactory(sR);
		Session s = factory.getCurrentSession();
		
		while(true)
		{
			s.beginTransaction();
			for(Sprite sprite: spriteList)
			{ 
				
			    s.save(sprite);
				
				move(sprite);
			}
			s.getTransaction().commit();
			factory.close();
			StandardServiceRegistryBuilder.destroy(sR);
			
			try
			{ 
				Thread.sleep(60);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
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
	public static ArrayList<Sprite> getSpriteList()
	{
		return spriteList;
	}





	/**
	 * @return the panelSizeX
	 */
	public static int getPanelSizeX() {
		return panelSizeX;
	}





	/**
	 * @return the panelSizeY
	 */
	public static int getPanelSizeY() {
		return panelSizeY;
	}
}
