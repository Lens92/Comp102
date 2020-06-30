// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2020T1, Assignment 1
 * Name:
 * Username:
 * ID:
 */

import ecs100.*;
import java.awt.Color;

/**
 * Draws various flags
 *
 * You can find lots of flag details (including the correct dimensions and colours)
 * from  http://www.crwflags.com/fotw/flags/    
 */

public class FlagDrawer{

    public static final double LEFT = 100;  // the left side of the flags
    public static final double TOP = 50;    // the top of the flags

    /**   CORE
     * Draw the flag of Germany.
     * The flag has three horizontal stripes;
     * The top is black, the middle is red, and the bottom is yellow.
     * The flag is 3/5 as high as it is wide (ratio 3:5).
     */
    public void drawGermanFlag(){
        UI.clearGraphics();
        UI.println("German Flag");
        double width = UI.askDouble("How wide: ");
        /*# YOUR CODE HERE */
        double height = width*3.0/5.0;
        UI.setColor(Color.black);
        UI.fillRect(LEFT, TOP, width, height/3.0);
        UI.setColor(Color.red);
        UI.fillRect(LEFT, TOP+height/3.0, width, height/3.0);
        UI.setColor(Color.yellow);
        UI.fillRect(LEFT, TOP+height*2.0/3.0, width, height/3.0);
        UI.setColor(Color.black);
        UI.fillRect(LEFT, TOP, width, height);
    }

    /** CORE
     *  The flag for Norway is a red rectangle with
     *  a white cross containing a thinner blue cross
     *  slightly off-set to the left-hand side;
     */
    public void drawNorwayFlag() {
        UI.clearGraphics();
        UI.println("Norway Flag");
        double width = UI.askDouble("How wide: ");
        /*# YOUR CODE HERE */
        double height = width*8.0/111.0;
        
        //red background
        UI.setColor(Color.red);
        UI.fillRect(LEFT, TOP, width, height);
        
        //white border
        double whiteStripeWidth = height/4.0;
        UI.setColor(Color.white);
        UI.fillRect(LEFT, TOP+height/2.0 - whiteStripeWidth/2.0, width, whiteStripeWidth);
        UI.fillRect(LEFT+height/2.0 - whiteStripeWidth/2.0, TOP, whiteStripeWidth, height);
        
        //blue cross
        double blueStripeWidth = height/8.0;
        UI.setColor(Color.blue);
        UI.fillRect(LEFT, TOP+height/2.0 - blueStripeWidth/2.0, width, blueStripeWidth);
        UI.fillRect(LEFT+height/2.0 - blueStripeWidth/2.0, TOP, blueStripeWidth, height);
        
        //outline
        UI.setColor(Color.black);
        UI.drawRect(LEFT, TOP, width, height);
    }

    /** COMPLETION
     *  Pacman
     *  A red pacman looking up on a black background chasing yellow, green, and blue dots.
     */
    public  void drawPacman() {
        UI.clearGraphics();        
        UI.println("Pacman Flag");
        double width = UI.askDouble("How wide: ");
        /*# YOUR CODE HERE */
        double height = width*3.0/2.0;
        
        //background
        UI.setColor(Color.black); //We want the background to be black
        UI.fillRect(LEFT, TOP, width, height); // Draw the background of the flags
        
        //The Pacman
        UI.setColor(Color.red);
        double mouthAngle = 50; //How far open the first pacman mouth is
        double pacmanDiameter = width/2.0;
        double pacmanLeft = LEFT + width/2.0 - pacmanDiameter/2.0;
        double pacmanTop = TOP + height/2.0;
        UI.fillArc(pacmanLeft, pacmanTop, pacmanDiameter, pacmanDiameter, mouthAngle/2.0+90, 360-mouthAngle);
        
        //The dots
        double foodDiameter = height/12.0;
        double foodLeft = LEFT + width/2.0-foodDiameter/2.0;
        double foodTop1 = TOP + height/8.0 - foodDiameter/2.0;
        double foodTop2 = foodTop1 + foodDiameter*2.0;
        double foodTop3 = foodTop2 + foodDiameter*2.0;
        UI.setColor(Color.blue);
        UI.fillOval(foodLeft, foodTop1, foodDiameter, foodDiameter);
        UI.setColor(Color.green);
        UI.fillOval(foodLeft, foodTop2, foodDiameter, foodDiameter);
        UI.setColor(Color.yellow);
        UI.fillOval(foodLeft, foodTop3, foodDiameter, foodDiameter);
    }

    /** COMPLETION
     *  The Czech flag
     *  The flag is 2/3 as high as it is wide (ratio 2:3).
     *  Two horizontal stripes (white and red) with a blue isoceles triangle on the left side.
     *  Note: there is no fillTriangle method in the UI class! Maybe you could use arcs?
     */
    public  void drawCzechFlag(){
        UI.clearGraphics();        
        UI.println("Czech Flag");
        double width = UI.askDouble("How wide: ");
        /*# YOUR CODE HERE */
        double height = width*2.0/3.0;
        
        //Blue Square
        UI.setColor(Color.blue);
        UI.fillRect(LEFT, TOP, width/2.0, height);
        
        //2 arcs to make the blue square beome a blue triangle
        double radius = width;
        UI.setColor(Color.white);
        UI.fillArc(LEFT-radius, TOP-radius, radius*2.0, radius*2.0, 0, -33.69);
        UI.setColor(Color.red);
        UI.fillArc(LEFT-radius, TOP+height-radius, radius*2.0, radius*2.0, 0, 33.69);
        
        //white top
        UI.setColor(Color.white);
        UI.fillRect(LEFT+width/2.0, TOP+height/2.0, width/2.0, height/2.0);
    }

    /**  CHALLENGE
     *  The Jamaican flag has a yellow diagonal cross with 
     *  green triangles top and bottom, and black triangles left and right.
     */
    public void drawJamaicaFlag(){
        UI.clearGraphics();
        UI.println("Flag of Jamaica");
        double width = UI.askDouble("How wide: ");
        /*# YOUR CODE HERE */

    }

    /**   CHALLENGE
     * The 3 stars flag has a blue vertical stripe on the left and black
     * vertical stripe on the right and 3 red 5 pointed stars in the middle stripe
     * The  height is 2/3 of the width,
     * A full marks solution will have a method for drawing a 5 pointed star,
     * and call that method for each of the stars
     */
    public void drawThreeStarsFlag() {
        UI.clearGraphics();        
        /*# YOUR CODE HERE */

    }


    public void setupGUI(){
        UI.initialise();
        UI.addButton("Clear", UI::clearPanes);
        UI.addButton("Core: Flag of Germany", this::drawGermanFlag);
        UI.addButton("Core: Flag of Norway",  this::drawNorwayFlag);
        // COMPLETION
        UI.addButton("Completion: Pacman Flag", this::drawPacman);
        UI.addButton("Completion: Czech Flag", this::drawCzechFlag);
        // CHALLENGE
        UI.addButton("Challenge: Flag of Jamaica", this::drawJamaicaFlag);
        UI.addButton("Challenge: Three stars flag", this::drawThreeStarsFlag);
        UI.addButton("Quit", UI::quit);

        UI.setDivider(0.3);
    }


}
