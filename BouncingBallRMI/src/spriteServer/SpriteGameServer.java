package spriteServer;
import java.rmi.*;
import java.rmi.server.*;

public class SpriteGameServer {

	public static void main(String[] args) {
		try {
			System.setSecurityManager(new RMISecurityManager());
		}

	}

}
