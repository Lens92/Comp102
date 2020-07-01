import ecs100.*;
import java.awt.Color;
import java.util.*;
import java.io.*;

/** Firework
 *   A firework is a small coloured circle that starts at the ground
 *   then moves up the window each step
 *   until it has got to its target height,
 *   then it "explodes" - getting larger at each step (but not moving)
 *   until it is finished (its radius is half-way to the ground), and then
 *   it disappears.
 *   Note: step() should not redraw the firework
 */

public class Firework{

    public static final double GROUND = 400;
    public static final double STEP_SIZE = 5;
    public static final double GROW_SIZE = 2;

    //fields
    private double x;
    private double height = 0; // height of the firework above the ground
    private double maxHeight;
    private Color col;
    private double radius = 3;

    //constructor
    public Firework(double x, Color c, double max){
        this.x = x;
        this.col = c;
        this.maxHeight = max;
    }

    //methods: step,  isFinished, draw
    /**
     * Make the firework move one step:
     *  If below maxHeight, move it up by STEP_SIZE
     *  If at maxHeight and not finished, make the radius bigger by GROW_SIZE, 
     */
    public void step(){
        if (this.height < this.maxHeight){
            this.height = this.height + STEP_SIZE;
        }
        else if (this.radius < this.maxHeight/2){
            this.radius = this.radius + GROW_SIZE;
        }
    }

    /**
     * Finished if the radius is at least half of maxHeight
     */
    public boolean isFinished(){
        return (this.radius >= this.maxHeight/2);
    }
    /**
     * Draw the firework as a circle of the current size, unless it is finished
     */
    public void draw(){
        UI.setColor(this.col);
        double top = GROUND - this.height - this.radius;
        UI.fillOval(this.x-this.radius, top, radius*2, radius*2);
    }
}