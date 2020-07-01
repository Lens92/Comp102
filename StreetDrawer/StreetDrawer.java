        // This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2020T1, Assignment 3
 * Name: Shaylen Mamidi
 * Username: mamidishay 
 * ID: 300525776
 */





import ecs100.*;
import java.awt.Color;

/** Draws a street of houses
 */
public class StreetDrawer{
    /*# YOUR CODE HERE */
    public static final double HOUSE_WD = 50;
    public static final double HOUSE_HT = 200;
    /** This draws the Windows for the houses, using small rectangles*/
    public void drawWindow(double midX, double midY, double sz) {
        double rad = sz/2;
        UI.eraseRect(midX-rad, midY-rad, sz,sz);
        UI.drawRect(midX-rad, midY-rad, sz,sz);
        UI.drawLine(midY-rad, midY, midX+rad, midY);
        UI.drawLine(midY-rad, midY, midX+rad, midY);
       
    }
    
     /**This draws a house calling the above window method using for loops to create an array of windows*/
        public void drawHouse(double mid, double bot) {
        //draw the rectangle of the house and the roof
        double left = mid - HOUSE_WD/2.0;
        double right = mid + HOUSE_WD/2.0;
        double top = bot - HOUSE_HT;
        double tip = top - HOUSE_WD*0.5;
        double slope = HOUSE_WD*0.707;
        //to fill the house in red:
        //UI.setColor(Color.red);
        //UI.fillRect(left, top, HOUSE_WD, HOUSE_HT);
        //UI.fillARC(mid-slope, tip-slope,slope*2, slope*2, 225.0, 90.0);
        UI.setColor(Color.black);
        UI.drawRect( left, top, HOUSE_WD, HOUSE_HT);
        UI.drawLine( left, top, mid, tip);
        UI.drawLine( left, top, mid, tip);
        
        double winWd = HOUSE_HT*2.0/13.0;
        double lev1 = bot - winWd;
        double lev2 = bot - winWd*2.5;
        double lev3 = bot - winWd*4.0;
        double lev4 = bot - winWd*5.5;
        this.drawWindow(mid, lev1,winWd);
        this.drawWindow(mid, lev2,winWd);
        this.drawWindow(mid, lev3,winWd);
        this.drawWindow(mid, lev4,winWd);
        
        
      }
    
      /**This calls the above method to create 6 houses*/
      public void drawStreet() {
            UI.clearGraphics();
            double road = 400;    //Vertical position of bottom of the houses
            double left = 50;
            double step = HOUSE_WD + 6;
            //this code can also be used
            //for (int = 0; i<8; i++){
              //  this.drawHouse(left+i*step, road);
            //}
            this.drawHouse(left+0*step, road);
            this.drawHouse(left+1*step, road);
            this.drawHouse(left+2*step, road);
            this.drawHouse(left+3*step, road);
            this.drawHouse(left+4*step, road);
            this.drawHouse(left+5*step, road);
            this.drawHouse(left+6*step, road);
            this.drawHouse(left+7*step, road);
      }
    
     //COMPLETIUON
     public static final double LEVEL_HT = 50;     /**
      * Draw a street of varying houses 
      */
      public void drawStreetCompl() {
            UI.clearGraphics();
            double road = 500;    //Vertical position of bottom of the houses
            double left = 50;
            double step = HOUSE_WD + 6;
            while (left<1000){
            double ht = (1+Math.random()*5)*LEVEL_HT;
            int cols = (int)(1 + Math.random()*3);
            this.drawHouseCompl(left, road, ht, cols);
            left = left + cols*HOUSE_WD+10;
            road = road - cols*5;
        }
     }
    
     public void drawHouseCompl(double left, double bot, double ht, int cols) {
        //draw the rectangle of the house and the roof
        double height = Math.max(ht, HOUSE_HT/2);  //at least half the defualt height
        double width = HOUSE_WD*Math.max(cols,1);  //at least one column windows height        //to fill the house in red:
        double mid = left + width*0.5;
        double right = left + width;
        double top = bot - height;
        double tip = top - width*0.5;
        double slope = width*0.707;  //distance from tip of top right corner
        
        //fill
        UI.setColor(Color.getHSBColor((float)Math.random(),0.6f, 0.4f));
        UI.fillRect(left, top, width, height);
        UI.fillArc(mid-slope, tip-slope, slope*2, slope*2, 225.0, 90.0);
        //outline
        UI.setColor(Color.black);
        UI.drawRect(left, top, width, height);
        UI.drawLine(left, top, mid, tip);
        UI.drawLine(left, top, mid, tip);
         
        double winWd = HOUSE_WD*(0.4+Math.random()*.02);
                for (double winY = bot-winWd; winY > top+winWd*0.6; winY = winY-winWd*1.5){
                    Color curtainCol = Color.getHSBColor((float)Math.random(),0.7f, 1.0f);
                    for (int col=0; col<cols; col++){
                        double winX = left+ width/cols*(col+.05);
                        this.drawFancyWindow(winX, winY, winWd, curtainCol);
                    }
                }
      }
    
      public void drawFancyWindow(double midX, double midY, double sz, Color col) {
        double rad = sz/2;
        
        UI.eraseRect(midX-rad, midY-rad, sz, sz);
        UI.setColor(col);
        UI.fillArc(midX-1.5*rad, midY-2*rad, rad, 2*rad, 270, 90);
        UI.fillArc(midX+0.5*rad, midY-2*rad, rad, 2*rad, 180, 90);
        UI.fillArc(midX+1.3*rad, midY, rad*0.6, 2*rad, 0, 90);
        UI.fillArc(midX+0.7*rad, midY, rad*0.6, 2*rad, 90, 90);
        UI.setColor(Color.black);
        UI.drawRect(midX-rad, midY-rad, sz, sz);
        UI.drawLine(midX-rad, midY, midX+rad, midY);
        UI.drawLine(midX-rad, midY-rad, midX, midY+rad);
         
        
      }
     
     /**
         * Set up the user interface, with a button to draw the street and a quit
           */
      public void setupGUI(){
        UI.addButton("Clear", UI::clearGraphics);
        UI.addButton("Street", this::drawStreet);
        UI.addButton("Quit", UI::quit);
        UI.setDivider(0);
       }
   }