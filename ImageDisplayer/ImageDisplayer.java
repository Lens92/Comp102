// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2020T1, Assignment 5
 * Name: Shaylen Mamidi
 * Username: mamidishay
 * ID: 300535776
 */



import ecs100.*;
import java.awt.Color;
import java.awt.image.*;
import java.util.*;
import java.io.*;
import java.nio.file.*;

/**
 * Displays several kinds of images on the graphics panel:
 *  - colour gradients (vertical gradients and 2D gradients)
 *  - ppm images, the simplest possible colour image format.
 *  - any pnm (ppm, pgm, pbm) images, including animated images.
 */

public class ImageDisplayer{
    public static final double LEFT = 20;  // left edge of the image
    public static final double TOP = 20;   // top edge of the image
    public static final double GRAD_ROWS = 400;   // number of rows in the gradient
    public static final double GRAD_COLS = 400;   // number of columns in the gradient

    public void setupGUI(){
        UI.addButton("Clear", UI::clearPanes);
        UI.addButton("Core: gradient", this::doCoreGradient);
        UI.addButton("Core: render", this::doCoreRender);
        UI.addButton("Compl: 2DGradient", this::doComplGradient);
        UI.addButton("Compl: PPM", this::doComplRender);
        UI.addButton("Chall: fullPPM", this::doChallRender);
        UI.addButton("Quit", UI::quit );
    }

    // CORE
    /**
     * drawVertGradient is passed the component values (red, green, blue)
     * for two colours.
     * It then draw a square image made of coloured pixels that is a
     * smooth gradient from the first colour along the top row to the second
     * colour along the bottom row.
     * On each row inbetween, the colour will need to make a small step
     * towards the target colour.
     * Note: The steps in the red, green, and blue components will be different,
     *       and depend on the number of row and the difference between the 
     *       top colour and the botton colour.
     * The image should be GRAD_ROWS x GRAD_COLS in size,
     *     and each pixel should be 1x1.
     * Hint: be careful with your division - use doubles!
     */
    public void drawVertGradient(double red1, double green1, double blue1, double red2, double green2, double blue2){
        /*# YOUR CODE HERE */
        double redStep = (red2-red1)/GRAD_ROWS;
        double greenStep = (green2-green1)/GRAD_ROWS;
        double blueStep = (blue2-blue1)/GRAD_ROWS;

        double y = TOP;
        double red = red1;
        double green = green1;
        double blue = blue1;

        for (int row = 0; row < GRAD_ROWS; row++){
            Color color = new Color((int)red, (int)green, (int)blue);
            UI.setColor(color);
            double x = LEFT;
            for (int col = 0; col < GRAD_COLS; col++){
                UI.fillRect(x, y, 1, 1);
                x = x + 1;
            }
            red = red +redStep;
            green = green+greenStep;
            blue =blue+ blueStep;
            y = y + 1;
        }
    }

    /** 
     * Renders a 200x200 image from a file.
     * The file must contain exactly 200x200x3 integers:
     * three numbers (red, green, blue) for each pixel of the image.
     * the pixels are in row order - all the pixels for the first row,
     * from left to right, followed by all the pixels for the second row,
     * etc.
     * Each pixel should be drawn as a 1x1 square.
     * Asks for the name of the file and opens a Scanner on the file,
     * then reads three numbers at a time from the scanner, and draws the pixel.
     */    

    public void render200x200Image(String fname){
        /*# YOUR CODE HERE */

        try {
            Scanner sc = new Scanner(Path.of(fname));
            double y = TOP;
            for (int row = 0; row < 200; row++) {
                double x = LEFT;

                for (int col = 0; col < 200; col++) {
                    Color color = new Color(sc.nextInt(), sc.nextInt(), sc.nextInt());
                    UI.setColor(color);
                    UI.fillRect(x, y, 1, 1);
                    x = x + 1;
                }
                y = y + 1;
            }
            sc.close();

        } catch (IOException e) {UI.println("Fail: " + e);}

    }

    // COMPLETION
    /**
     * draw2DGradient is passed the component values (red, green, blue)
     * for two colours.
     * It then draw an image made of coloured squares that is a
     * smooth 2D gradient from the first colour at the top left corner
     * to the second colour at the bottom right.
     * The red component of the colour should move smoothly from the first red value
     *  on the top row to the second red value on the last row(vertical)
     * The blue component of the colour should move smoothly from the first blue value
     *  on the left column to the second blue value on the rightmost column(horizontal)
     * The green component of the colour should move smoothly from the first green value
     *  in the top-left corner to the second green value in the bottom-right corner.(diagonal)
     * The image should be GRAD_ROWS x GRAD_COLS in size, and each square should be 1x1.
     */

    public void draw2DGradient(double red1, double green1, double blue1, double red2, double green2, double blue2){
        /*# YOUR CODE HERE */
        double redStep = (red2-red1)/GRAD_ROWS;
        double greenStep = (green2-green1)/(GRAD_ROWS + GRAD_COLS);
        double blueStep = (blue2-blue1)/GRAD_COLS;

        double y = TOP;
        
        for (int row = 0; row < GRAD_ROWS; row++){
            double x = LEFT;

            for (int col = 0; col < GRAD_COLS; col++){
                double red = red1 + row*redStep;
                double green = green1 + (row+col)*greenStep;
                double blue = blue1 + col*blueStep;
                Color color = new Color((int)red, (int)green, (int)blue);
                UI.setColor(color);
                UI.fillRect(x, y, 1, 1);
                x = x + 1;
            }

            y = y + 1;
        }
    }

    /**
     * Renders a ppm image file, given an open Scanner 
     * Renders the image at position (LEFT, TOP).
     * Each pixel of the image is rendered by a square of size PIXEL_SIZE
     * Assumes that
     * - the colour depth is 255,
     * - there is just one image in the file (not "animated"), and
     * - there are no comments in the file.
     * The first four tokens are "P3", number of columns, number of rows, 255
     * The remaining tokens are the pixel values (red, green, blue for each pixel)
     * Hint: to draw the pixels, it is probably easiest to have a nested for loop
     *  that will repeatedly
     *  - read the next three numbers from the scanner
     *  - construct the Color and set UI's color
     *  - draw the pixel.
     *  (ie, don't construct an ArrayList of numbers).
     */
    public void renderPPMImage(String fname){
        /*# YOUR CODE HERE */

        try {
            Scanner sc = new Scanner(Path.of(fname));

            String magicNumber = sc.next(); //should be P3

            if (!magicNumber.equals("P3")) {
                UI.println("Invalid file format");
                return;
            }
            int cols = sc.nextInt();
            int rows = sc.nextInt();
            int max = sc.nextInt(); // needs to be 255
            double y = TOP;

            //Process the pixels: read them and draw them on the Graphics Pane
            for (int row =0; row < rows; row++){
                double x =- LEFT;
                for (int col = 0; col < cols; col++) {
                    UI.setColor(new Color(sc.nextInt(), sc.nextInt() ,sc.nextInt()));
                    UI.fillRect(x, y, 1, 1);
                    x = x + 1;
                }
                y = y + 1;
            }
        } catch (IOException e) {UI.println("Fail: " + e);

        }
    }


    /** Challenge
     * Renders a pnm image file which
     *  may be a ppm, pgm, or pbm file
     *  may have comments in header (which it should ignore)
     *  may have a colour depth other than 255,
     *   (in which case, it scales the colour values appropriately
     *  may be animated (multiple images in the file)
     *   (in which case, it renders each image in the file in turn with
     *    200 mSec delay between, and repeats the sequence 3 times.
     */
    public void renderPNM(String fname){
        /*# YOUR CODE HERE */
        File file = new File(fname);
        int count = 0;
        while (count < 3){
            try{
                Scanner sc = new Scanner(file);
                while (sc.hasNext()){
                    this.renderOnePNM(sc);
                    UI.sleep(200);
                }
                sc.close();
            }
            catch (IOException e) {UI.println("Fail: " + e);}
            count++;

        }
    }
    //**helper method that reads and renders one image of a multi-image*/
    public void renderOnePNM(Scanner input){
        String magicNumber = input.next(); //shoudl be P1 or P2 or P3
        while(!input.hasNextInt()){input.nextLine();}//gobble any comments
        int cols = input.nextInt();
        while(!input.hasNextInt()){input.nextLine();}//gobble any comments
        int rows = input.nextInt();
        while(!input.hasNextInt()){input.nextLine();}//gobble any comments
        int max = input.nextInt();// max colour value

        boolean p3 = magicNumber.equals("P3"); // colour
        boolean p2 = magicNumber.equals("P2"); // grey
        boolean p1 = magicNumber.equals("P1"); // black and white

        if (!p3&&!p2&&!p1){
            UI.println("Invalid file ppm type");
            return;
        }
        int row = 0;
        double y = TOP;
        while (row < rows){
            int col = 0;
            double x = LEFT;
            while (col < cols){
                if (p3){
                    int red = input.nextInt()*255/max;
                    int green = input.nextInt()*255/max;
                    int blue = input.nextInt()*255/max;
                    UI.setColor(new Color(red,green,blue));
                }
                else if (p2){
                    int grey = input.nextInt()*255/max;
                    UI.setColor(new Color(grey,grey,grey));
                }
                else if (p1){
                    if (input.nextInt()==1){
                        UI.setColor(new Color(255,255,255));
                    }
                }
                UI.fillRect(x, y, 1, 1);
                col++;
                x = x + 1;
            }
            y =y + 1;
        }
    }

        /** method for button, calling drawVertGradient. */
         public void doCoreGradient(){
            UI.println("Top color:");
            double red1 = this.askComponent("red");
            double grn1 = this.askComponent("green:");
            double blu1 = this.askComponent("blue:");
            UI.println("Bottom color");
            double red2 = this.askComponent("red:");
            double grn2 = this.askComponent("green:");
            double blu2 = this.askComponent("blue:");
            this.drawVertGradient(red1, grn1, blu1, red2, grn2, blu2);
        }

        /** method for button, calling renderImage. */
        public void doCoreRender(){
            this.render200x200Image(UIFileChooser.open("Name of 200x200 file to render"));
        }

        /** method for button, calling draw2DGradient. */
        public void doComplGradient(){
            UI.println("Top Left color:");
            double red1 = this.askComponent("red:");
            double grn1 = this.askComponent("green:");
            double blu1 = this.askComponent("blue:");
            UI.println("Bottom Right color:");
            double red2 = this.askComponent("red:");
            double grn2 = this.askComponent("green:");
            double blu2 = this.askComponent("blue:");
            this.draw2DGradient(red1, grn1, blu1, red2, grn2, blu2);
        }

        /** method for button, calling renderImage. */
        public void doComplRender(){
            this.renderPPMImage(UIFileChooser.open("Name of ppm file to render"));
        }

        /** method for button, calling renderImage. */
        public void doChallRender(){
            this.renderPNM(UIFileChooser.open("Name of ppm/pgm/pbm file to render"));
        }

        /** ask for a colour component, ensuring it is between 0 and 255, (inclusive) */
        public double askComponent(String prompt){
        int ans = UI.askInt(prompt);
        return (double)Math.max(0, Math.min(255, ans));
    }

    public static void main(String[] args){
        ImageDisplayer ir = new ImageDisplayer();
        ir.setupGUI();
    }

}
