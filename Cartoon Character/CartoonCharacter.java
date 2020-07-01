import ecs100.*;

/**
 * A CartoonCharacter object is a cartoon character, displayed on the screen
 * that can 
 *   move around,
 *   change the direction the character is facing,
 *   change its emotion (smiling or frowning)
 *   speak or "think" a phrase
 */

public class CartoonCharacter {

    /* Fields representing the state of a CartoonCharacter */
    private String imagePrefix;         // the prefix for the set of image files
    private double figX = -100;         // top left corner of character
    private double figY= -100;
    private String direction = "right"; // which direction the character is currently facing
    private String emotion = "smile";   // whether the character is smiling or frowning.

    /* Fields containing dimensions of CartoonCharacters */
    /* Could have different values for different objects, but no methods yet to change them */

    private int ht= 100;   // (this name is too short: should be characterHeight)
    private int wd= 70;    // (this name is too short: should be characterWidth)

    public static final int WORDS_WIDTH = 150;
    public static final int WORDS_HEIGHT= 35;

    /** Constructor requires the coordinates (left, top) of where it should be placed,
     *    and the prefix of the image file names
     *  For example
     *    new CartoonCharacter(100, 50, "casey");
     */
    public CartoonCharacter(double x, double y, String name ){
        this.figX = x;
        this.figY = y;
        this.imagePrefix=name;
        this.draw();
    }

    
    /** makes the CartoonCharacter turn to the left */
    public void lookLeft() {
        this.erase();
        // change direction 
        this.direction = "left";
        this.draw();
    }

    /** makes the CartoonCharacter turn to the right */
    public void lookRight() {
        this.erase();
        // change direction
        this.direction = "right";
        this.draw();
    }

    /** makes the CartoonCharacter smile */
    public void smile() {
        this.erase();
        //change emotion
        this.emotion = "smile";
        this.draw();
    }

    /** makes the CartoonCharacter frown */
    public void frown() {
        this.erase();
        //change emotion
        this.emotion = "frown";
        this.draw();
    }

    /** makes the CartoonCharacter walk in the direction it is facing */
    public void walk(double dist) {
        this.erase();
        //change position
        this.draw();
    }
    
   
    /** Helper method that draws the CartoonCharacter
     * All the public methods that change the character call draw.
     */

    private void draw(){
        // work out which image to use  (eg, "alice-right-smile.png")
        String imageFileName = this.imagePrefix+"-"+this.direction+"-"+this.emotion+".png";
        // draw the image on the graphics pane
        UI.drawImage(imageFileName, this.figX, this.figY, this.wd, this.ht);
        // wait a bit
        UI.sleep(500);
    }


    
    

    /** Helper method that erases the CartoonCharacter 
     * All the public methods that change the character call erase first 
     */
    private void erase() {
        UI.eraseRect(this.figX, this.figY, this.wd+1, this.ht+1);
    }

    
    
    /** makes the CartoonCharacter say something in a speech box */
    public void speak(String words) {
        double boxX = this.figX;
        double boxY = this.figY - this.WORDS_HEIGHT - 20;

        if (this.direction.equals("right"))
            boxX += 15 ;
        else
            boxX +=  this.wd  - 15 - this.WORDS_WIDTH;

        UI.eraseRect(boxX, boxY, this.WORDS_WIDTH, this.WORDS_HEIGHT);
        UI.drawRect(boxX, boxY, this.WORDS_WIDTH, this.WORDS_HEIGHT);
        UI.drawString(words, boxX + 5, boxY + this.WORDS_HEIGHT/2 + 3);

        UI.sleep(1000);

        UI.eraseRect(boxX, boxY, this.WORDS_WIDTH+1, this.WORDS_HEIGHT+1);
    }


}