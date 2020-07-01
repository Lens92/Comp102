//// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2020T1, Assignment 4
 * Name: 
 * Username:  
 * ID: 
 */





import ecs100.*;
import java.util.*;
import java.io.*;
import java.nio.file.*;

public class WordSearcher {

    /** set up the buttons */
  public void setupGUI(){
        UI.addButton("Clear", UI::clearPanes);
        UI.addButton("Search", this::searchPattern);
        UI.addButton("quit", UI::quit);
        UI.setDivider(1.0);
  }

    /**
     * Asks the user for a pattern and then finds and prints out (one per line)
     *     all the words in a dictionary that contain that pattern.
     * At the end, it prints out how many words in the dictionary contained
     *    the pattern.
     * It should print the words as it finds them, but should stop printing
     *    after it has found 100 of them
     * The dictionary is in the file dictionary.txt, and has one word per line.
     */
   public void searchPattern() {
        /*# YOUR CODE HERE */
     
     try {
            /*# YOUR CODE HERE */
            List<String> dictionary = Files.readAllLines(Path.of("dictionary.txt"));
            final String pattern = UI.askToken("Pattern to serach for: ").toLowerCase();
            
       int matches = 0;
       
       UI.printf("Dictionary words containing "+pattern+":");

        for (String word : dictionary){
            if(word.contains(pattern)){
                    matches++;
                    
                    if (matches <= 100){
                        UI.println(word);
                    }
                    if (matches == 100){
                        UI.println(".....");
                    }
            }
        }
        
      UI.printf("\nTotal matching words = %d\n",matches);
     } catch(IOException e){UI.println("File reading failed");} 
   } 
}
