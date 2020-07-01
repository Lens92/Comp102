/* Code for COMP102 Assignment
 * Name:
 * Usercode:
 * ID:
 */

import ecs100.*;
import java.awt.Color;
import java.util.*;
import java.io.*;
import java.nio.file.*;

/** DroneWorld   */
public class DroneWorld{
    public static final double GROUND = 400;
    public static final int NUM_DRONES = 10;
    public static final double LEFT = 50;
    public static final double BOX_SIZE = 30;

    private Drone[] drones = new Drone[NUM_DRONES];
    private Drone selectedDrone; 
    private double pressedX, pressedY;

    /**
     * Initialise the interface
     */
    public void setupGUI(){
        UI.setMouseListener(this::doMouse);
        UI.addButton("draw", this::drawBox);
        UI.addButton("Reset", this::reset);
        UI.addButton("Quit", UI::quit);
        this.reset();
        this.run();
    }

    /**
     * Make the drones move at their current speed
     * this is an infinite simulation loop.
     */
    public void run(){
        while (true){
            UI.clearGraphics();
            for (int i=0; i<this.drones.length; i++){
                Drone drone = this.drones[i];
                if (drone!=null){
                    drone.move();
                    if (drone.crash()){
                        this.drones[i] = null;
                    }
                    else {
                        drone.draw();
                    }
                }
            }
            drawBox();
            UI.printMessage(""+System.currentTimeMillis());
            UI.sleep(50);
        }
    }

    /** resets the array of Drones to have a new collection of Drones */
    public void reset(){
        this.drones = new Drone[NUM_DRONES];
        for(int i = 0; i< this.drones.length; i++){
            this.drones[i] = new Drone(LEFT + i*BOX_SIZE + BOX_SIZE/2);
            UI.println("drone "+i);
        }
    }


    /** Respond to mouse events */
    public void doMouse(String action, double x, double y) {
        if (action.equals("pressed")){
            // find the drone we are on.
            this.selectedDrone = null;
            for(Drone drone : this.drones){
                if (drone!=null && drone.on(x, y)) {
                    UI.println("On drone");
                    this.selectedDrone = drone;
                    this.pressedX = x;
                    this.pressedY = y;
                }
            }
        }
        else if (action.equals("released")){
            if (this.selectedDrone != null){
                // change the direction of the drone.
                this.selectedDrone.setSpeed(x-this.pressedX, this.pressedY-y);
            }
        }
    }
    public void drawBox(){
        for (int i=0; i<this.drones.length; i++){
            UI.drawRect(LEFT+i*BOX_SIZE, GROUND-BOX_SIZE/2, BOX_SIZE, BOX_SIZE/2);
        }
    }

    public static void main(String[] arguments){
        DroneWorld obj = new DroneWorld();
        obj.setupGUI();
    }    

}