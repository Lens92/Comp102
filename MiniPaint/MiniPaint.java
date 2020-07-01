// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2020T1, Assignment 7
 * Name: Shaylen Mamidi
 * Username: mamidishay
 * ID: 300535776
 */



import ecs100.*;
import java.awt.Color;
import javax.swing.JColorChooser;
import javax.swing.JButton;
import java.util.*;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
/**
 * A simple drawing program.
 * The user can select from a variety of tools and options using the buttons and
 *   elements down the left side, and can use the mouse to add elements to the drawing
 *   according to the current tool and options
 * Note, most of the "action" in the program happens in response to mouse events;
 *   the buttons, textFields, and sliders mostly record information that is used
 *   later by the mouse responding.
 */

public class MiniPaint{

    // fields to remember:
    //  - the "tool" - what will be drawn when the mouse is next released.
    //                 may be a shape, or an image, or a caption,
    //    [Completion] or freehand, or eraser
    //  - whether the shape should be filled or not
    //  - the position the mouse was pressed,
    //  - the string for the text caption
    //  - the width of the lines and the font size of the text captions.
    //  - [Completion] the name of the image file
    //  - [Completion] the colors for the border and fill for shapes and captions

    private String tool = "Line";   // the current tool, governing what the mouse will do.
    // Initial value is "Line";  changed by the buttons.

    // More fields
    /*# YOUR CODE HERE */
    public static final double ERASER_SIZE = 20;

    private double lastX;  
    private double lastY; 
    private String imageFileName = "yellowrose2.jpg";
    private String caption = "";
    private boolean fill = false;
    private Color lineColor = Color.blue;
    private Color fillColor = Color.white;
    private double lineWidth = 2;
    private double textSize = 12;

    private JButton lineButton;
    private JButton fillButton;
    private JButton fillNoFillButton;
    /*# END YOUR CODE HERE */
    /**
     * Set up the interface: buttons, textfields, sliders,
     * listening to the mouse
     */
    public void setupGUI(){
        UI.addButton("Clear", UI::clearGraphics);
        UI.addButton("Line", this::doSetLine); 
        /*# YOUR CODE HERE */
        UI.addButton("Rect", this::doSetRect); //button to draw a rectangle
        UI.addButton("Oval", this::doSetOval); //button to draw an oval
        UI.addTextField("Caption", this::doSetCaption);
        UI.addButton("Image", this::doSetImage);
        UI.addButton("Freehand", this::doSetFreehand); //button to draw freehand
        UI.addButton("Eraser", this::doSetErase); ////button erase
        UI.addButton("Nofill", this::doToggleFill);
        UI.addButton("Line Colour", this::doSetLineColor);
        UI.addButton("Fill Colour", this::doSetFillColor);
        UI.addSlider( "Line Width", 1, 20, this.lineWidth, this::doSetLineWidth);
        UI.addSlider( "Line Width", 8, 40, this.textSize, this::doSetTextSize);

        UI.setMouseMotionListener (this::doMouse);
        /*# END YOUR CODE HERE */

        UI.addButton("Quit", UI::quit);
        UI.setDivider(0.0);

    }   

    // Methods to respond to the buttons, textfield, and sliders
    // Mostly, These methods just save information to the fields.
    // The method header for doSetLine was given as an example; you will need other methods
    // for each button/textfield/slider
    // See hints in the Assignment description.

    /** Respond to the Line button */
    public void doSetLine(){
        /*# YOUR CODE HERE */
        this.tool="Line";
    }

    /** Respond to the Rectangle button */
    public void doSetRect(){
        /*# YOUR CODE HERE */
        this.tool="Rect";
    }

    /** Respond to the Oval button */
    public void doSetOval(){
        /*# YOUR CODE HERE */
        this.tool="Oval";
    }

    /** Respond to the Image button */
    public void doSetImage(){
        /*# YOUR CODE HERE */
        this.imageFileName = UIFileChooser.open("Select image file");
        this.tool="Image";
    }

    /** Respond to the Caption button */
    public void doSetCaption(String capt) {
        /*# YOUR CODE HERE */
        this.caption = capt;
        this.tool = "Caption";
    }

    /** Respond to the Freehand button */
    public void doSetFreehand() {
        /*# YOUR CODE HERE */
        this.tool = "Freehand";

    }

    /** Respond to the Eraser button */
    public void doSetErase() {
        /*# YOUR CODE HERE */
        this.tool = "Eraser";
    }

    /** Respond to the Border Color button */
    public void doSetLineColor() {
        /*# YOUR CODE HERE */
        this.lineColor = JColorChooser.showDialog(null,"Choose Color for new tools", null);
    }

    /** Respond to the Fill Color button */
    public void doSetFillColor() {
        /*# YOUR CODE HERE */
        this.fillColor = JColorChooser.showDialog(null,"Choose Color for new tools", null);
    }

    /** Respond to the Fill/Nofill button */
    public void doToggleFill(){
        /*# YOUR CODE HERE */
        this.fill = !this.fill;
    }

    /** Respond to the linewidth slider */
    public void doSetLineWidth(double v){
        /*# YOUR CODE HERE */
        this.lineWidth = v;
    }

    /** Respond to text size slider */
    public void doSetTextSize(double v){
        /*# YOUR CODE HERE */
        this.textSize = v;
    }

    
    /**
     * Respond to mouse events
     * When pressed, remember the position.
     * When released, draw what is specified by current tool
     * Uses the value stored in the field to determine which kind of tool to draw.
     *  It should call the drawALine, drawARectangle, drawAnOval, etc, methods
     *  passing the pressed and released positions
     * [Completion] should respond to "dragged" events also to do erasing and freehand drawing
     */
    public void doMouse(String action, double x, double y) {
        /*# YOUR CODE HERE */

        if (action.equals("pressed")) {
            this.lastX = x;
            this.lastY = y;
            if (this.tool.equals("Eraser")) {
                UI.eraseOval(x-ERASER_SIZE/2,y-ERASER_SIZE/2, ERASER_SIZE, ERASER_SIZE);
            }
        } 

        else if (action.equals("dragged")){
            if (tool.equals("ERASER")){
                UI.eraseOval(x-ERASER_SIZE/2,y-ERASER_SIZE/2, ERASER_SIZE, ERASER_SIZE);
            }
            else if (tool.equals("Freehand")){
                this.drawALine(this.lastX, this.lastY, x, y);
                this.lastX = x;
                this.lastY =y;
            }
        }
        else if (action.equals("release")){
            if (this.tool.equals("Line")){
                this.drawALine(this.lastX, this.lastY, x, y);
            }
        }
        else if (action.equals("Rect")){
            this.drawARectangle(this.lastX, this.lastY, x, y);   
        }
        else if (action.equals("Oval")){
            this.drawAnOval(this.lastX, this.lastY, x, y);   
        }
        else if (action.equals("Image")){
            this.drawAnImage(this.lastX, this.lastY, x, y);   
        }
        else if (action.equals("Caption")){
            this.drawCaption(x, y);   
        }

    }     

    /** 
     * Draw a line between the two positions (x1, y1) and (x2, y2)
     */
    public void drawALine(double x1, double y1, double x2, double y2){
        /*# YOUR CODE HERE */
        UI.setColor(this.lineColor);
        UI.setLineWidth(this.lineWidth);
        UI.drawLine(x1 ,y1 ,x2 ,y2);
    }

    /**
     * Draw a rectangle between the two diagonal corners
     * [Completion] Works out the left, top, width, and height 
     * Then draws the rectangle, based on the options
     */
    public void drawARectangle(double x1, double y1, double x2, double y2){
        /*# YOUR CODE HERE */
        double left = Math.min(x1, x2);
        double top = Math.min(y1, y2);
        double width = Math.abs(x1 - x2);
        double height = Math.abs(y1 - y2);
        if(this.fill) {
            UI.setColor(this.fillColor);
            UI.fillRect(left, top, width, height);
        }
        UI.setColor(this.lineColor);
        UI.setLineWidth(this.lineWidth);
        UI.drawRect(left, top, width, height);
    }

    /**
     * Draw an oval to fit the rectangle between the the two diagonal corners
     * [Completion] Works out the left, top, width, and height 
     * Then draws the oval, based on the options
     */
    public void drawAnOval(double x1, double y1, double x2, double y2){
        /*# YOUR CODE HERE */
        double left = Math.min(x1, x2);
        double top = Math.min(y1, y2);
        double width = Math.abs(x1 - x2);
        double height = Math.abs(y1 - y2);
        if(this.fill) {
            UI.setColor(this.fillColor);
            UI.fillOval(left, top, width, height);
        }
        UI.setColor(this.lineColor);
        UI.setLineWidth(this.lineWidth);
        UI.drawOval(left, top, width, height);
    }

    /** 
     * Draws the current caption at the mouse released point.
     */
    public void drawCaption(double x, double y){
        /*# YOUR CODE HERE */
        UI.setFontSize(this.textSize);
        UI.setColor(this.lineColor);
        UI.drawString(this.caption, x,y);
    }

    /** [Completion]
     * Draws the current image between the two diagonal corners, unless
     *  they are very close, and then just draws the image at its natural size
     *  Works out the left, top, width, and height 
     * Then draws the image, if there is one.
     */
    public void drawAnImage(double x1, double y1, double x2, double y2){
        /*# YOUR CODE HERE */
        double left = Math.min(x1, x2);
        double top = Math.min(y1, y2);
        double width = Math.abs(x1 - x2);
        double height = Math.abs(y1 - y2);
        if(this.imageFileName==null){
            UI.printMessage("No image file to draw");   
        }
        else if (width<5 && height <5){
            UI.drawImage(this.imageFileName, x2, y2);   
        }
        else {
            UI.drawImage(this.imageFileName, left, top, width, height);   
        }
    }

    // Main:  constructs a new MiniPaint object and set up GUI
    public static void main(String[] arguments){
        MiniPaint mp = new MiniPaint();
        mp.setupGUI();
    }
}
