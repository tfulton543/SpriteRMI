package spriteServer;

import java.awt.Color;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.exception.SQLGrammarException;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import spriteInterface.Constants;
import spriteInterface.Sprite;

/**
 * This class is a server application for a sprite game. It allows users to connect via RMI
 * and create sprite objects which will bounce around in a box. The server will save sprites 
 * to a database and read them back in the next time the server is started.
 * @author Thomas Fulton/Erik Dennis
 *
 */
public class SpriteGameServer 
{
	/**
	 * This fixed attribute defines the height of the client window
	 */
	private final static int panelSizeX = 400;
	
	/**
	 * This fixed attribute defines the width of the client window
	 */
	private final static int panelSizeY = 400;
	
	/**
	 * This list store the available colours which clients can use
	 */
	private static ArrayList<Color> availableColours;
	
	/**
	 * This list holds the sprite objects that are created by clients and read in from the database
	 */
	private static ArrayList<Sprite> spriteList = new ArrayList<Sprite>();
	
	/**
	 * This is the reference to the hibernate Session which is used to perform database I/O
	 */
	private static Session hibernateSession;
		
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
		final StandardServiceRegistry hibernateRegistry = new StandardServiceRegistryBuilder().configure().build();
		MetadataImplementor meta = (MetadataImplementor) new MetadataSources(hibernateRegistry).addAnnotatedClass(Sprite.class).buildMetadata();
		SessionFactory factory = meta.buildSessionFactory();
		hibernateSession = factory.openSession();
			
		try
		{
			//here we try to load in all the sprites from the database
			System.out.println("Attempting to load saved sprites...");
			gameServer.loadSprites();
			System.out.println("Loading complete");
		}
		//we should fall into this catch block if the tables do not exist on the database (first time the server is run)
		catch(SQLGrammarException e)
		{
			//create the tables
			System.out.println("Database tables not found... Creating tables...");
			new SchemaExport(meta).create(true, true);
			System.out.println("Tables have been built");
		}
		
		gameServer.buildColourList();
			
		//start the animation loop
		System.out.println("Server online... Users may now connect");
		gameServer.animateSprites();
	}//end of main

	
	/**
	 * This method instantiates the availableColours array and adds all the colours to it. 
	 */
	private void buildColourList() 
	{
		availableColours = new ArrayList<Color>();
		
		availableColours.add(Color.BLACK);
		availableColours.add(Color.BLUE);
		availableColours.add(Color.CYAN);
		availableColours.add(Color.GRAY);
		availableColours.add(Color.GREEN);
		availableColours.add(Color.MAGENTA);
		availableColours.add(Color.ORANGE);
		availableColours.add(Color.PINK);
		availableColours.add(Color.RED);
		availableColours.add(Color.WHITE);
		availableColours.add(Color.YELLOW);	
	}//end of buildColourList

	/**
	 * This method loads all the Sprites that have been persisted in the database
	 * It adds them all to the local ArrayList<Sprite> and should be called upon startup
	 */
	private void loadSprites()
	{
		hibernateSession.beginTransaction();
		Query allSprites = hibernateSession.createQuery("FROM Sprite");
		hibernateSession.getTransaction().commit();
		spriteList = (ArrayList<Sprite>) allSprites.list();
		
		//here we give the server admin the option to clear the database tables (effectively start a new game)
		if(JOptionPane.showConfirmDialog(null, "Would you like to start a new game?") == JOptionPane.OK_OPTION)
		{
			System.out.println("Deleting saved sprites... ");
			hibernateSession.beginTransaction();
			for(Sprite sprite : spriteList)
			{
				hibernateSession.delete(sprite);
			}
			hibernateSession.getTransaction().commit();
			spriteList.clear();
			System.out.println("Sprites have been deleted");
		}//end of JoptionPane OK confirmation
	}//end of loadSprites
	

	//infinite loop which infinitely moves the sprite objects and then saves them to the database
	private void animateSprites() 
	{	
		while(true)
		{
			hibernateSession.beginTransaction();
			for(Sprite sprite: spriteList)
			{ 		
				move(sprite);
			    hibernateSession.save(sprite);				
			}
			
			hibernateSession.getTransaction().commit();
			
			try
			{ 
				Thread.sleep(60);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}	
		}	
	}//end of animateSprites()
	
	
	/**
	 * This method defines the logic which is responsible for moving the sprites.
	 * It moves a single sprite around the play area, and bounces it off the walls as necessary.
	 * @param sprite Sprite object that you wish to move
	 */
	private void move(Sprite sprite)
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

	/**
	 * @return the availableColours
	 */
	public static ArrayList<Color> getAvailableColours() {
		return availableColours;
	}
}//end of class
