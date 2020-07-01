// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2020T1, Assignment 6
 * Name: Shaylen Mamidi
 * Username: mamidishay
 * ID: 300535776
 */


import ecs100.*;
import java.awt.Color;

/** Ball represents a ball that is launched by the mouse towards a direction.
 *    Each time the step() method is called, it will take one step.  
 * For the Completion part, gravity should act on the ball by reducing its vertical speed.
 */

public class Ball{

    // Constants for all balls: size, position of the ground
    public static final double RADIUS = 8;  // radius of the balls.
    public static final double GROUND = BallGame.GROUND;
    public static final double RIGHT_END = BallGame.RIGHT_END;

    // Fields to store state of the ball:
    // x position, height above ground, stepX, stepY, colour
    //   The ball should initially be not moving at all. (step should be 0)
    /*# YOUR CODE HERE */
    private double x;                   //x positin of ball
    private double height;               // height of the bottom of the ball
    private double stepX = 0;          //size of the horizontal step (positive to the right)
    private double stepY = 0;          // size of vertical step (positive up)
    private Color col;                 // colour        
    // Constructor
    /** Construct a new Ball object.
     *  Parameters are the initial position (x and the height above the ground),
     *  Stores the parameters into fields 
     *  and initialises the colour to a random colour
     *  SHOULD NOT DRAW THE BALL!
     */
    public Ball(double x, double h){
        /*# YOUR CODE HERE */
        this.x = x;
        this.height = h;
        this.col = Color.getHSBColor((float) Math.random(), 1.0f, 1.0f);

    }

    // Methods
    /**
     * Draw the ball on the Graphics Pane in its current position
     * (unless it is past the RIGHT_END )
     */
    public void draw(){
        /*# YOUR CODE HERE */
        if (this.x < RIGHT_END) {
            UI.setColor(this.col);    
            UI.fillOval(this.x-RADIUS, GROUND - this.height-RADIUS*2, RADIUS*2, RADIUS*2);
            UI.setColor(Color.black);    
            UI.setLineWidth(1);
            UI.drawOval(this.x-RADIUS, GROUND - this.height-RADIUS*2, RADIUS*2, RADIUS*2);
        }
    }

    /**
     * Move the ball one step (DO NOT REDRAW IT)
     * Core:
     *    Change its height and x position using the vertical and horizonal steps
     * Completion:
     *    Reduce its vertical speed each step (due to gravity), 
     *    If it would go below ground, then change its y position to ground level and
     *      set the  vertical speed back to 0.
     */
    public void step(){
        /*# YOUR CODE HERE */
        this.height = this.height + this.stepY;
        this.x = this.x + this.stepX;

        //completion:
        this.stepY = this.stepY - 0.2;
        if (this.height < 0){
            this.height = 0;
            this.stepY = 0;
        }

    }

    /**
     * Set the speed of the ball.
     * The horizontal speed is how much it moves to the right in each step.
     * The vertical speed is how much it moves up in each step (negative if ball going down).
     */
    public void setSpeed(double xSpeed, double ySpeed){
        /*# YOUR CODE HERE */
        this.stepX = xSpeed;
        this.stepY = ySpeed;
    }

    /**
     * Return the height of the ball above the ground
     */
    public double getHeight(){
        /*# YOUR CODE HERE */
        return this.height;
    }

    /**
     * Return the horizontal position of the ball
     */
    public double getX(){
        /*# YOUR CODE HERE */
        return this.x;
    }
}