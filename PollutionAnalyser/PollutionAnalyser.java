// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2020T1, Assignment 3
 * Name: Shaylen Mamidi
 * Username: mamidishay
 * ID: 300535776             */

 
 
 
 
 
import ecs100.*;
import java.awt.Color;
import java.util.*;

/**
 * The program contains methods which analyse and plot hourly Nitrogen Dioxide
 * air pollution levels over the course of a day.
 * There are several things about the pollution level that
 * a user may be interested in:
 *    The average, maximum, and minimum pollution levels during the day.
 *    Any times when the pollution level went over the danger threshold.
 *    A plot of the values
 *    Whether the pollution level was over the danger threshold for more than 50% of the day.
 */
   public class PollutionAnalyser {

    //Constants
    public static final int NO2_DANGER_LEVEL = 120;

    public final int BASE_OF_PLOT = 450; // base of the bars on the plot
    public final int LEFT_OF_PLOT = 50;  // left side of the plot
    public final int STEP = 25;          // distance between plotted points
    public static int MAX_VALUE = 400;   // maximum height of a bar in the graph

      /**
      * analyse() method reads a sequence of levels from the user
      * and calls 6 methods which each do one part of the analysis:
      * See the assignment instructions
      */
      public void analyse() {
        UI.clearPanes();
        // Reads a sequence of levels from the user,
        ArrayList<Double> levels = UI.askNumbers("Enter levels, end with 'done': ");
        if (levels.size() == 0) {
            UI.println("No readings");
        }
        else {
            UI.printf("-------Analysis-------\n");
            this.printAverageLevel(levels);
            this.printDangerousLevels(levels);
            this.plotLevels(levels);
            UI.printf("Maximum level was:  %.0f ppb\n", this.maximumLevel(levels));
            UI.printf("Minimum level was:  %.0f ppb\n", this.minimumLevel(levels));
            UI.printf("Finished Plotting\n");
            this.reportDangerPeriod(levels);
        }
      }

        /**
      * CORE
      * Calculate and print the average of the pollution levels
      *  There is guaranteed to be at least one level,
      */
       public void printAverageLevel(ArrayList<Double> levels) {
        /*# YOUR CODE HERE */
       double sum = 0   ;
        for(Double level :  levels){
            sum = sum + level;
        }
        double mean = sum / levels.size();
        UI.printf("Average leavelwas: %.1f\n", mean);
        /*#END OF YOUR CODE*/
        }
       
    
       /**
         * CORE
         * Find and print out all the readings where the pollution level is above the danger threshold (120 ppb).
         * then print out the percentage of readings that were above the danger threshold.
         * - may assume there is at least one level in the list.
         * - The method will need a variable to keep track of the counts of danger levels, which
         *   needs to be initialised to an appropriate value.
         */
         public void printDangerousLevels(ArrayList<Double> levels) {
            /*# YOUR CODE HERE */
           int countDanger = 0;
           
           for (double level:levels) {
             if(level > NO2_DANGER_LEVEL){
                UI.printf("%.0f is dangerouse!\n", level);
                countDanger = countDanger + 1;
               }
           }
           
           UI.printf("%.0f%% of the readings were dangerouse times\n",
                    100.0*countDanger/levels.size() );
           /*#END OF YOUR CODE*/  
        }
                  //starts a counter that counts how many time it went through the loop
                  //and also another one for that added one to a variable, ererytime the level was over thershold,then I did those as a fraction and times by 100
         /**
         * CORE
         *   Plots a bar graph of the sequence of levels,
         *     using narrow rectangles whose heights are equal to the level.
         *   Draws a horizontal line for the x-axis (or baseline) without any labels.
         * COMPLETION
         *   Any level greater than NO2_DANGER_LEVEL should be plotted in red rather than blue
         *   Any level greater than MAX_VALUE (400) should be plotted as if it were just 400,
         *     putting an asterisk ("*") above it to show that it has been cut off.
         */
          public void plotLevels(ArrayList<Double> levels) {
            // Initialise variables 
            /*# YOUR CODE HERE */
            UI.drawLine(LEFT_OF_PLOT, BASE_OF_PLOT, (LEFT_OF_PLOT + (24 * STEP) + 20), BASE_OF_PLOT);
           int x = LEFT_OF_PLOT;
              for (double level : levels) {
                  UI.fillRect(x, BASE_OF_PLOT - level, STEP - 2, level);
                  x = x + STEP;
              }
              UI.println("Finished plotting");
            /*#END OF YOUR CODE */
        }
        
           
    
        // ---------COMPLETION --------------------------
          /**
          * COMPLETION
         * Find and return the maximum level in the list
         *  - There is guaranteed to be at least one level,
         *  - The method will need a variable to keep track of the maximum, which
         *    needs to be initialised to an appropriate value.
         */
         public double maximumLevel(ArrayList<Double> levels) {
            /*# YOUR CODE HERE */
            double max = Integer.MIN_VALUE;
            
            for (Double level : levels) {
            if(level < max){
                max = level;
            }
           }
            return max;  
            /*#END OF YOUR CODE*///remove when method is implemented
        }
    
            /**
               * COMPLETION
         * Find and return the minimum level in the list
         *  - There is guaranteed to be at least one level,
         *  - The method will need a variable to keep track of the minimum, which
         *    needs to be initialised to an appropriate value.
         */
           public double minimumLevel(ArrayList<Double> levels) {
            /*# YOUR CODE HERE */
            double min = Integer.MAX_VALUE;
          for (Double level : levels) {
            if(level < min){
                min = level;
            }
          }
            return min;  //remove when method is implemented
            }
    
            /**
               * COMPLETION
                 * Find and calculates if the pollution level was above the danger threshold
                   * continuously for more than half (50%) of the readings, and what percentage of
                     * the readings were in the above that level.
                       */
            public void reportDangerPeriod(ArrayList<Double> levels) {
            /*# YOUR CODE HERE */
            int currentPeriod = 0;
            int maxPeriod = 0;
            
                for (double level : levels){
                if (level > NO2_DANGER_LEVEL){
                    currentPeriod = currentPeriod + 1;
                    if(currentPeriod > maxPeriod) {
                        maxPeriod = currentPeriod;
                }
               }
                else{
                    currentPeriod = 0;
                }
            
               }
            /*#END OF YOUR CODE*/
          }
          /**
          * COMPLETION
                * Find and calculates if the pollution level was above the danger threshold
                  * continuously for more than half (50%) of the readings, and what percentage of
                    * the readings were in the above that level.
                      */
           public void PlotLevelsCompletion(ArrayList<Double> levels) {
              /*# YOUR CODE HERE */
              int right = (LEFT_OF_PLOT + (24 * STEP) + 20);
              
              UI.drawLine(LEFT_OF_PLOT, BASE_OF_PLOT, right, BASE_OF_PLOT);   // x axis
            
              int x = LEFT_OF_PLOT;
            
              for (double level : levels){
                if (level > NO2_DANGER_LEVEL){
                    UI.setColor(Color.red);
                } else {
                    UI.setColor(Color.blue);
                }
                if (level > MAX_VALUE){
                    UI.fillRect(x, BASE_OF_PLOT - MAX_VALUE, STEP - 2, MAX_VALUE);
                    UI.drawString("*", x + STEP / 3, BASE_OF_PLOT - MAX_VALUE - 5);
                } else {
                    UI.fillRect(x, BASE_OF_PLOT - level, STEP - 2, level);
                }
                x = x + STEP;
               }
               //draw danger level
               UI.setColor(Color.red);
               UI.drawLine(LEFT_OF_PLOT, BASE_OF_PLOT - NO2_DANGER_LEVEL, right, BASE_OF_PLOT - NO2_DANGER_LEVEL );  
              
             }
            /*#END OF YOUR CODE*/
    
    
       
    
    
    
          // Helper methods - setupGUI and simpleTest
    
         // ------------------ Set up the GUI (buttons) ------------------------
         /** Make buttons to let the user run the methods */
         public void setupGUI() {
            UI.initialise();
            UI.addButton("Clear", UI::clearPanes);
            UI.addButton("Analyse Levels", this::analyse);
            //UI.addButton("Analyse Completion", this::analyseCompletion);
            UI.addButton("simple test", this::simpleTest);
            UI.addButton("Quit", UI::quit);
         }
    
         /**
         * Test method with some "magic" to test your methods
         * on the example data from the assignment handout.
         * THIS IS NOT A COMPLETE TEST FOR YOUR PROGRAM!!     
         */
         public void simpleTest() {
            UI.clearPanes();
            ArrayList<Double> levels = new ArrayList<Double>(Arrays.asList(new Double[]{
                        91.0,149.0,167.0,84.0,158.0,185.0,196.0,182.0,125.0,90.0}));
            this.printAverageLevel(levels);
            this.printDangerousLevels(levels);
            this.plotLevels(levels);
            UI.printf("Maximum level was:  %.0f ppb\n", this.maximumLevel(levels));
            UI.printf("Minimum level was:  %.0f ppb\n", this.minimumLevel(levels));
            this.reportDangerPeriod(levels);
        }
    

      }
