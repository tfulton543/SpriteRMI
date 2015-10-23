package bouncingball;
//%W%	%G%
/*
 This app bounces a blue ball inside a JPanel.  The ball is created and begins
 moving with a mousePressed event.  When the ball hits the edge of
 the JPanel, it bounces off the edge and continues in the opposite
 direction.  
*/
import javax.swing.JFrame;

public class BouncingBall 
{
    private JFrame frame;
    private BallPanel panel = new BallPanel();
    
    public BouncingBall()
    {
        frame = new JFrame("Bouncing Ball");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setVisible(true);
        frame.setResizable(false);
    }//end of constructor

    public static void main(String[] args) 
    {
        new BouncingBall().panel.animate();
    }//end of main
}//end of class
