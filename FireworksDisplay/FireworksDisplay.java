// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2020T1, Assignment 8
 * Name: 
 * Username: 
 * ID: 
 */

import ecs100.*;
import java.awt.Color;
import java.util.*;

/** FireworksDisplay
 * Runs a fireworks display with waves of fireworks.
 * Each wave has lots of fireworks that start together
 * When they have all finished, it starts the next wave.
 * The "Size" slider controls the number of fireworks in the next wave.
 */

public class FireworksDisplay{

    private int numFireworks = 20;  // number of fireworks in the wave. (set by slider)
    private Color c;
    private Firework f = new Firework();
    /**
     * Set up the GUI with a button and a slider
     * and start the simulation
     */
    public void setupGUI(){
        UI.addSlider("Size", 2, 30, this.numFireworks, this::setWaveSize);
        UI.addButton("Quit", UI::quit);
        UI.setWindowSize(800, 600);
        UI.setDivider(0.0);     // hide the text pane
        this.runDisplay();
    }

    /**
     * Set the number of fireworks for the next wave
     */
    public void setWaveSize(double value){
        /*# YOUR CODE HERE */
        this.numFireworks = (int)value;
    }

    /**
     * Run the display
     */
    public void runDisplay(){
        
        // create a list of fireworks
        // (stored in local variable because only used in this method)
        // - declare the variable
        // - make an empty list in the variable
        // - put numFireworks into the list.
        /*# YOUR CODE HERE */

        ArrayList<Firework> fireworks = new ArrayList<>();
	for (int i = 0; i < this.numFireworks; i++) {
		fireworks.add(new Firework());
	}
           
        // main loop (goes forever)
        while (true){
            // redraw the background
            UI.clearGraphics();
            UI.setColor(Color.black);
            UI.fillRect(0, 0, 1000, 700);

            // draw all the fireworks and pause for 70 milliseconds
            /*# YOUR CODE HERE */
            for(Firework firework:fireworks) {
		firework.draw();
           }	
            UI.sleep(70); 

            // make all the fireworks take one step
            /*# YOUR CODE HERE */
            for(Firework firework:fireworks) {
		firework.draw();
		firework.step();
	   }	
            // COMPLETION
            // find out if ALL the fireworks have finished,
            // (if there is one firework that is not finished, then
            //  they are not all finished)
            boolean allFinished = true;
            /*# YOUR CODE HERE */
           for (Firework firework : fireworks) {
		if (!firework.isFinished()) {
			allFinished = false;
			break;
		}
           }
            // COMPLETION
            // if all the fireworks have finished, create a new wave of new fireworks
           if (allFinished){
                /*# YOUR CODE HERE */
                this.runDisplay();
           }
        }
    }


    public static void main(String[] args){
        new FireworksDisplay().setupGUI();
    }  
}

