  // This program is copyright VUW.
    // You are granted permission to use it to construct your answer to a COMP102 assignment.
    // You may not distribute it in any other way without permission.
    
    /* Code for COMP102 - 2020T1, Assignment 2
     * Name: 
     * Username: 
     * ID: 
     */
    
    
    
    
    
import ecs100.*;
import java.awt.Color;
public class KeyValidator {
    
  public void doCore(){
    String key = UI.askString("Key:   ");
    this.validateKeyCore(key);    
  }
  public void validateKeyCore(String key){
      /*# YOUR CODE HERE */
      /**The key be at least 7 characters long, and at most 20 characters long.*/
     if (key.length()< 7){  
         UI.println("Invalid: key is too short");
     }
     else if (key.length() > 20){
          UI.println("Invalid: key is too long");
     }
     else if(key.startsWith("#") ||key.startsWith    ("-")) {
         UI.println("Invalid: key cannot start with # or -");
     }
     else if (key.contains(" ")) {
         UI.println("Invalid: key cannot contain space");
     }
     else {
         UI.println("Valid");
     }
     /**The key must not start with the special characters '#' or '_', and the key must not have a space character anywhere.*/
     
   }

   /**
   * Asks user for key word and the name and then checks if it is a valid key word.
   */
   public void doCompletion(){
    String key = UI.askString("Key:   ");
    String name = UI.askString("Your name:   ");
      this.validateKeyCompletion(key, name);
    }

   /** COMPLETION
   * Report that the key is valid or report ALL the rules that the key failed.
   */
   public void validateKeyCompletion(String key, String name){
     /*# YOUR CODE HERE */
     /**The key be at least 7 characters long, and at most 20 characters long.*/
     boolean valid = true;        
     if(key.length() < 7){
         UI.println(key  + "is too short");
         valid = false;
     }      
     
     if (key.length() > 20){
        UI.printf(key + "is too ;long");     
        valid = false;
     }
     
     /**The key must not start with the special characters '#' or '_', and the key must not have a space character anywhere.*/
     if (key.startsWith("#") || key.startsWith("_")){
       UI.printf(key + "cannot start with these characters");
       valid = false; 
     }
     
     if(key.contains(" ")){
         UI.println(key + "cannot contain space");
         valid = false;
     }
     /**The key must have at least one Upper case character and at least one Lower case character*/
     
     if (key.compareTo(key.toLowerCase())==0){
         UI.println(key +"must contain at lease one upper case character");
         valid = false;
     }
      //Must have at least one lower case character
     if (key.compareTo(key.toUpperCase())==0){
         UI.println(key +"must contain at lease one lower case character");
         valid = false;
     }
     
     //Must not contain the name - case insensitive
     if (key.toLowerCase().contains(name.toLowerCase())){
         UI.println(key +"cannot contain " + name);
         valid = false;
     }
     //Must contain one (but not both) of the special character
     //=> invalid if boht present or neither present
     if (key.contains("#") == key.contains("-")){
         UI.println(key +"must contain just one of the special character # -");
         valid = false;
     }
     
     if (valid){
         UI.printf("The key \"%s\" is valid %n", key);
         }
     else {
         UI.printf("The key \"%s\" is not valid %n", key);
     
     }
  }   

  public void setupGUI(){
     UI.initialise();
        UI.addButton("Clear", UI::clearText );
        UI.addButton("Validate Key Core", this::doCore );
        UI.addButton("Validate Key Completion", this::doCompletion );
        UI.addButton("Quit", UI::quit );
        UI.setDivider(1);       // Expand the text area
    }
    
    
}
