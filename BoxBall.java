import java.awt.*;
import java.awt.geom.*;
import java.util.Random;

/**
 * Class BoxBall - A simulation of a ball bouncing inside of a box with gravity effect. 
 * The properties and movement of the ball is determined by the ball itself. The ball 
 * is given a random vertical and lateral speed and will move in that direction and 
 * loose speed over time. The ball will bounce of the walls of a box.
 *
 * @author Claire Iroudayassamy
 * @version 2019.03.24
 */

public class BoxBall
{
    private static final int GRAVITY = 3;  // effect of gravity
    
    private Random rand = new Random();

    private int ballDegradation = 2;
    private Ellipse2D.Double circle;
    private Color color;
    private int diameter;
    private int xPosition;
    private int yPosition;
    private final int bottomPosition;               // y position of bottom of box
    private final int topPosition;                  // y postiion of top of box
    private final int leftPosition;                 // x position of left wall
    private final int rightPosition;                // x position of right wall
    private Canvas canvas;
    private int ySpeed = rand.nextInt(14) - 7;      // initial vertical speed
    private int xSpeed = rand.nextInt(14) - 7;      // initial lateral speed
    
    /**
     * Constructor for objects of class BouncingBall
     *
     * @param xPos  the horizontal coordinate of the ball
     * @param yPos  the vertical coordinate of the ball
     * @param ballDiameter  the diameter (in pixels) of the ball
     * @param ballColor  the color of the ball
     * @param bottomPos  the position of the bottom of the box (where the ball will bounce)
     * @param topPos    the position of the top of the box (where the ball will bounce)
     * @param leftPos   the position of the left wall (where the ball will bounce)
     * @param rightPos  the position of the right wall (where the ball will bounce)
     * @param drawingCanvas  the canvas to draw this ball on
     */
    public BoxBall(int xPos, int yPos, int ballDiameter, Color ballColor,
                        int bottomPos, int topPos, int leftPos, int rightPos, Canvas drawingCanvas)
    {
        xPosition = xPos;
        yPosition = yPos;
        color = ballColor;
        diameter = ballDiameter;
        bottomPosition = bottomPos;
        topPosition = topPos;
        leftPosition = leftPos;
        rightPosition = rightPos;
        canvas = drawingCanvas;
    }

    /**
     * Draw this ball at its current position onto the canvas.
     **/
    public void draw()
    {
        canvas.setForegroundColor(color);
        canvas.fillCircle(xPosition, yPosition, diameter);
    }

    /**
     * Erase this ball at its current position.
     **/
    public void erase()
    {
        canvas.eraseCircle(xPosition, yPosition, diameter);
    }    

    /**
     * Move this ball according to its position and speed and redraw.
     **/
    public void move()
    {
        // remove from canvas at the current position
        erase();
            
        // compute new position
        ySpeed += GRAVITY;
        yPosition += ySpeed;
        xPosition += xSpeed;

        // check if it has hit the bottom of the box
        if(yPosition >= (bottomPosition - diameter) && ySpeed > 0) {
           yPosition = (int)(bottomPosition - diameter);
           ySpeed = -ySpeed + ballDegradation;
           if(xSpeed < 0) {
               xSpeed += ballDegradation;
            } else if (xSpeed > 0) {
               xSpeed -= ballDegradation;
            }
        }
        // check if it has hit the top of the box
        if(yPosition <= (topPosition) && ySpeed < 0) {
            yPosition = (int)(topPosition);
            ySpeed = -ySpeed - ballDegradation;
            if(xSpeed < 0) {
               xSpeed += ballDegradation;
            } else if (xSpeed > 0) {
               xSpeed -= ballDegradation;
            }
        }
        //check if it has hit left wall
        if (xPosition <= (leftPosition) && xSpeed < 0) {
            xPosition = (int)(leftPosition);
            xSpeed = -xSpeed + ballDegradation;
            if(ySpeed < 0) {
               ySpeed += ballDegradation;
            } else if (ySpeed > 0) {
               ySpeed -= ballDegradation;
            }
        }
        //check if it has hit right wall
        if (xPosition >= (rightPosition - diameter) && xSpeed > 0) {
            xPosition = (int)(rightPosition - diameter);
            xSpeed = -xSpeed + ballDegradation;
            if(ySpeed < 0) {
               ySpeed += ballDegradation;
            } else if (ySpeed > 0) {
               ySpeed -= ballDegradation;
            }
        }
        
        // draw again at new position
        draw();
    }    

    /**
     * return the horizontal position of this ball
     */
    public int getXPosition()
    {
        return xPosition;
    }

    /**
     * return the vertical position of this ball
     */
    public int getYPosition()
    {
        return yPosition;
    }
    
    /**
     * return the vertical speed of the ball
     */
    public int getYSpeed()
    {
        return ySpeed;
    }
    
    /**
     * return the lateral speed of the ball
     */
    public int getXSpeed()
    {
        return xSpeed;
    }
}