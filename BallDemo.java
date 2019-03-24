import java.awt.Color;
import java.util.HashSet;
import java.util.Random;

/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Bill Crosbie
 * @version 2015-March-BB
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class BallDemo   
{
    private Canvas myCanvas;
    private Random rand = new Random();
    private Color color;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
    }

    /**
     * Simulate two bouncing balls
     */
    public void bounce()
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.drawLine(50, ground, 550, ground);

        // crate and show the balls
        BouncingBall ball = new BouncingBall(50, 50, 16, Color.BLUE, ground, myCanvas);
        ball.draw();
        BouncingBall ball2 = new BouncingBall(70, 80, 20, Color.RED, ground, myCanvas);
        ball2.draw();

        // make them bounce
        boolean finished =  false;
        while(!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();
            ball2.move();
            // stop once ball has travelled a certain distance on x axis
            if(ball.getXPosition() >= 550 || ball2.getXPosition() >= 550) {
                finished = true;
            }
        }
    }
    
    /**
     * 
     */
    public void boxBounce(int amount)
    {
        myCanvas.erase();
        HashSet<BoxBall> boxBalls = new HashSet<>();
        
        int numberOfBalls = 0;
        int finishedBalls = 0;  // counter for balls done bouncing
        int ground = 450;       // position of the bottom of the box
        int top = 25;           // position of the top of the box
        int left = 50;          // position of the left wall
        int right = 550;        // position of the right wall
        
        int diameter;       
        int xPos;
        int yPos;
        int red;
        int green;
        int blue;
        
        if(amount < 5 || amount > 30) {
            System.out.println("Total number of balls must be between 5 and 30!");
        } else {
            myCanvas.setVisible(true);
            
            //draw the box
            myCanvas.drawLine(left, ground, right, ground);
            myCanvas.drawLine(left, top, right, top);
            myCanvas.drawLine(left, top, left, ground);
            myCanvas.drawLine(right, top, right, ground);
        
            // create balls
            while (numberOfBalls != amount) {
                numberOfBalls ++;
                diameter = rand.nextInt(20) + 10;                       // random diameter
                xPos = rand.nextInt((right - left) - diameter) + left;  //random initial x position
                yPos = rand.nextInt((ground - top) - diameter) + top;   //random initial y position
                
                //make a random color
                red = rand.nextInt(220);
                green = rand.nextInt(220);
                blue = rand.nextInt(220);
                color = new Color(red, green, blue);
                
                boxBalls.add(new BoxBall(xPos, yPos, diameter, color, ground, top, left, right, myCanvas));
            }
            
            //Draw the balls and wait a bit
            for(BoxBall boxBall : boxBalls) {
                boxBall.draw();
            }
            myCanvas.wait(50);
            
            // Make them bounce
            boolean finished = false;
            while(!finished) {
                for(BoxBall boxBall : boxBalls) {
                    myCanvas.wait(5);
                    boxBall.move();
                    if(boxBall.getYSpeed() == 0 && boxBall.getXSpeed() == 0) {
                        finishedBalls ++;
                    }
                }
                if(finishedBalls == boxBalls.size()) {
                    finished = true;
                }
            }
        
       }
    }
}
