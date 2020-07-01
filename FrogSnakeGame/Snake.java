// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2020T1, Assignment 6
 * Name:
 * Username:
 * ID:
 */

import ecs100.*;
import java.awt.Color;

/** The snake is created at a random position with a random 360 degree direction.
 * The constructor does not have any  parameters.
 * It can move
 *  - makes it go forward one step in its current direction.
 *  - if outside arena boundaries, makes it go backward one step, and
 *         then turn to a new (random) direction.
 *  The walls of the arena are determined by the constants:
 *    ARENA_SIZE, TOP_WALL, BOT_WALL, LEFT_WALL and RIGHT_WALL
 * It can report its current position (x and y) with the
 *  getX() and getY() methods.
 *  move() will make it move in the direction it is going. 
 *  draw() will make it draw itself (image size should be 30).
 */

public class Snake{

    public static final int ARENA_SIZE = FrogSnakeGame.ARENA_SIZE;
    public static final int LEFT_WALL = FrogSnakeGame.LEFT_WALL;
    public static final int RIGHT_WALL = FrogSnakeGame.RIGHT_WALL;
    public static final int TOP_WALL = FrogSnakeGame.TOP_WALL;
    public static final int BOT_WALL = FrogSnakeGame.BOT_WALL;

    /*# YOUR CODE HERE */
    private double x;
    private double y;
    private double direction;
    private double size = 30;
    
    /** 
     * Make a new Snake object
     * set its direction to a random direction , 0...360
     */
    public Snake()  {
        
        this.x = LEFT_WALL + size/2 + Math.random() * (ARENA_SIZE-size/2);
        this.y = TOP_WALL + size/2 + Math.random() * (ARENA_SIZE-size/2);
        this.direction = Math.random() * 360;
        
    }
    
    /**Return the x coordinate of the current postition */
    public double getX(){
        return this.x;
    }
    /**Return the y coordinate of the current postition */
    public double getY(){
        return this.y;
    }
    /** Step one unit in the current direction (but don't redraw)
     * if the snake hits a wall it shoudl go backward two steps, and turn ot a new (random)
     * direction
     */
    public void move(){
        this.x = this.x + Math.cos(this.direction*Math.PI/180);
        this.y = this.y + Math.sin(this.direction*Math.PI/180);
        if (this.x<LEFT_WALL+this.size/2
        ||this.x > RIGHT_WALL-this.size/2
        ||this.y < TOP_WALL+this.size/2
        ||this.y > BOT_WALL-this.size/2){
            this.x = this.x -2 * Math.cos(this.direction*Math.PI/180);
            this.y = this.y -2 * Math.sin(this.direction*Math.PI/180);
            this.direction = Math.random() * 360;
        }
        
    }
     /**Draw the snake*/
     public void draw(){
         double left = this.x-this.size/2;
         double top = this.y-this.size/2;
         UI.drawImage("snake.jpg", left, top, this.size, this.size);
        }
}
