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
/** Program to create simple animated animal character using the
*  Animal class.  
*/
public class PetShow{
   public void Turtle(){
    Animal Gerald = new Animal("turtle", "Gerald", 0, 150);
    this.animate(Gerald);
   }
   /** animate creates two or several animals on the window.
   *  Then animates them according to a fixed script by calling a series
   *  of methods on the animals.
   *  
   *  CORE
   */
    public void animate(Animal Gerald){
    /*# YOUR CODE HERE */
    
    Animal turtle = new Animal ("turtle", "Berthie", 520,80);
    turtle.introduce("Hello");
    turtle.speak("In this demo, I will");
    turtle.speak("show you what we can do");
    
    Animal dog = new Animal ("dog", "Charlie", 200,200);
    dog.goRight(0);
    dog.jump(40);
    dog.jump(40);
    turtle.think("These dogs like jumping.");
    dog.jump(40);
    dog.jump(40);
    turtle.speak("Please dog.");
    turtle.speak("introduce yourself.");
    dog.introduce("Rrowff! ");
    dog.goLeft(800);
    
    Animal tiger = new Animal ("tiger", "Shere Khan", 100,300);
    tiger.shout("rrrrr");
    tiger.goRight(200);
    tiger.goLeft(200);
    tiger.goRight(200);
    tiger.goLeft(200);
    turtle.speak("Bye tiger");
    tiger.goLeft(800);
    
    Animal grasshopper = new Animal("grasshopper", "no_name",200,300);
    grasshopper.goRight(0);
    grasshopper.jump(100);
    grasshopper.speak("Good Day");
    grasshopper.goRight(100);
    grasshopper.jump(100);
    grasshopper.speak("Bye");
    grasshopper.jump(100);
    grasshopper.goRight(850);
    
    turtle.speak("that's all!");
    turtle.goRight(500);
    
    /*#END OF YOUR CODE*/
     }
  /** threeAnimalsRoutine creates three animals on the window.
  *  Then makes each animal do the same routine in turn.
  *  You should define a routine method, and threeAnimalsRoutine
  *   should call the routine method three times, to make
  *   each of the three animals perform the routine in turn.
  *   
  *   COMPLETION
  */
 public void threeAnimalsRoutine(){
    /*# YOUR CODE HERE */
    Animal bird = new Animal("bird", "Fred", 260, 300);
    Animal dinosaur = new Animal("dinosaur", "Nessie", 460, 300);
    Animal snake = new Animal("snake", "Sneaky", 660, 300);
    this.routine(bird);
    this.routine(dinosaur);
    this.routine(snake);
 }
 
 /** makes the animal character do a little routine */
 public void routine(Animal character){
    /*# YOUR CODE HERE */
    character.goLeft(100);
    character.introduce("Hey");
    character.jump(25);
    character.goRight(100);
    character.jump(50);
    character.goLeft(100);
    character.jump(25);
    character.speak("I must go now");
    character.goRight(10);
    character.goLeft(10);
    character.goRight(10);
    character.shout("bye");
    character.goLeft(750);

     } 
 
 
 /** Make buttons to let the user run the methods */
 public void setupGUI(){
        UI.initialise();
        UI.addButton("Clear", UI::clearGraphics );
        UI.addButton("Animate", this::Turtle);
        UI.addButton("Three", this::threeAnimalsRoutine );
        UI.addButton("Quit", UI::quit );
        UI.setDivider(0);       // Expand the graphics area
 }
}
    



