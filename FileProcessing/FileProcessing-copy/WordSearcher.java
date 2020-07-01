// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2020T1, Assignment 4
 * Name: Shaylen Mamidi
 * Username: mamidishay 
 * ID: 300535776
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
     //String fileName = UIFileChooser.open("Choose file to search");
     String pattern = UI.askString("Pattern to search for");
     try {
            /*# YOUR CODE HERE */
       int  total = 0;
       List<String> allLines = Files.readAllLines(Path.of("dictionary.txt"));
       int lineNumberNumber = 1;
       UI.printf("Dictionary words containing %s: \n", pattern);
       for (String line : allLines){
            if(line.contains(pattern)){
                    UI.printf(line + "\n");
                    if(total > 100){
                        //UI.printf(total + "");

                        break;
                    }
                    //else if(total > 100){
                      //  UI.printf("..............");
                        
                    //}
            }
            //UI.printf("Total matching words = \n", total);
            //word++;
       }
       UI.printf("Total matching words = \n", total);
     } catch(IOException e){UI.println("File reading failed" + e);} 
   } 
}