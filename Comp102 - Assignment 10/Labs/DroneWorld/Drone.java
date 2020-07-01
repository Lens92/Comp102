import ecs100.*;

/**
 * Write a description of class Drone here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Drone{
    public static final double WIDTH = 20;
    public static final double HEIGHT = 8;
    public static final double SPEED= 1;
    public static final double GROUND = DroneWorld.GROUND;

    // instance variables - replace the example below with your own
    private double x, ht;   // Position of the bottom of the Drone above the ground 
    private double dx, dy;

    /**
     * Constructor for objects of class Drone
     */
    public Drone(double xPos)    {
        // initialise instance variables
        this.x = xPos;
    }

    /** is the point x,y on the drone */
    public boolean on(double mouseX, double mouseY){
        double mouseHeight = DroneWorld.GROUND - mouseY;
        return (Math.abs(this.x - mouseX) < WIDTH/2  &&
                mouseHeight>=this.ht && mouseHeight<=this.ht+8);
    }

    /** change the speed */
    public void setSpeed(double changeX, double changeHt){
        this.dx = SPEED*Math.cos(Math.atan2(changeHt, changeX));
        this.dy = SPEED*Math.sin(Math.atan2(changeHt, changeX));
    }

    /** move by the current speed */
    public void move(){
        this.x = this.x +this.dx;
        this.ht = this.ht + this.dy;
    }

    /** Hit the ground, or off the edge */
    public boolean crash(){
        return (this.ht<0 || this.ht> GROUND || this.x<0 || this.x >1000);
    }
    
    /**
     * Draw the drone
     */
    public void draw(){
        double midY = DroneWorld.GROUND - this.ht - HEIGHT/2;  // mid point of Drone
        UI.fillRect(x-WIDTH/2, midY-HEIGHT/4, WIDTH, HEIGHT/2);
        UI.fillRect(x-WIDTH/4, midY-HEIGHT/2, WIDTH/2, HEIGHT);
    }

}