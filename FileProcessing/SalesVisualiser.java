// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2020T1, Assignment 4
 * Name: 
 * Username: 
 * ID: 
 */





import ecs100.*;
import java.awt.Color;
import java.util.*;
import java.io.*;
import java.nio.file.*;

/**
 * SalesVisualiser
 * Reads details of sales from a file and produces a bar graph of the data
 */

public class SalesVisualiser{

    // Constants for plotting the graph
    public static final double GRAPH_LEFT = 50;
    public static final double GRAPH_RIGHT = 650;
    public static final double GRAPH_BASE = 400;
    public static final double MONTH_WIDTH = (GRAPH_RIGHT-GRAPH_LEFT)/12;  // the width for each set of three bars
    public static final double BAR_WIDTH = 12;    // the width of each bar

    /** set up the buttons */
    public void setupGUI(){
        UI.addButton("Clear", UI::clearPanes);
        UI.addButton("Sales", this::graphSales); 
        UI.addButton("quit", UI::quit);
        UI.setDivider(0.0);
    }
    
    /**
     * Asks the user for the name of a file containing the details of sales
     *  reported by dealers over the last three years, and then produces a
     *  bar graph of the data, showing the sales for each month, with different
     *  color bars for each year.
     * Each line of a sales data file contains
     *    a year, a month, and a series of sales from the dealers
     *    There may be a different number of sales on each line.
     * For example:
     *   2017 01 21 15 32 12 2 7
     *   2017 02 5 18 12
     *   :
     *   2018 01 16 3 2 4 1 8 4 13
     *   2018 02 41 3
     *   :
     * There is no guarantee that the lines are in order of date
     * The total sales in any month will never be over 200.
     * 
     * The method should draw a bar graph with 12 sets of bars, one set for each month
     * Each set should have
     *  a red bar for the 2017 data,
     *  a green bar for the 2018 data, and
     *  a blue bar for the 2019 data
     * The height of the bar should be the total number of sales in that month
     * Hints:
     *   Use a Scanner for each line
     *   After getting the year and month from the Scanner, you will need a loop to add
     *       up all the sales on each line.
     *   Look carefully at the example file and the example output.
     */
    public void graphSales() {
        /*# YOUR CODE HERE */
       String fName = UIFileChooser.open("Sales file:");
       
       try {
          List<String> allSales = Files.readAllLines(Path.of("fname"));
          UI.setColor(Color.black);
          UI.drawLine(GRAPH_LEFT, GRAPH_BASE, GRAPH_RIGHT, GRAPH_BASE);
          UI.drawString("Month:", GRAPH_LEFT-MONTH_WIDTH, GRAPH_BASE+15);
         
         for(int i = 0; i<12; i++){
            double x = GRAPH_LEFT + i*MONTH_WIDTH;
            UI.drawLine(x,GRAPH_BASE+5,x,GRAPH_BASE);
             UI.drawString(""+(i+1), x+MONTH_WIDTH/2, GRAPH_BASE+15);
            }   
            
         for(String line : allSales){
             Scanner sc = new Scanner(line);
             int year = sc.nextInt();
             int month = sc.nextInt();
             int total = 0;
             while (sc.hasNext()){
                total = total + sc.nextInt();   
                }
               
       } 
    }catch(IOException e){UI.println("File reading failed" + e);}
  }
}

