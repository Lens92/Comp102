// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2020T1, Assignment 10
 * Name: Shaylen Mamidi
 * Username: mamidishay 
 * ID: 300535776
 */


import ecs100.*;
import java.util.*;
import java.io.*;
import java.nio.file.*; 

/** 
 * Question 5: A line-breaking algorithm
 */

public class Question5 {

    public static final int LIMIT = 10;   // maximum number of characters allowed on a line.

    /** 
     * printText should print the String in its parameter in a series of lines.
     * Each line of output should have at most 10 characters on it.
     * There should be as few lines as possible.
     * Where necessary, it should break words between lines with hyphens;
     *  the hyphen should always be the last character on a line.
     * No word should start or end with a hyphen
     * Do not put a space at the beginning of any line.
     * You may assume that the String contains only words, with
     *  no punctuation and exactly one space between words.
     * See the assignment description for an example.
     */
    public void printText(String text) {
        /*# YOUR CODE HERE */
        try {
            String s="";
            for(int i=0;i<text.length();i++) {
                s=s.concat(text.charAt(i)+"");
                if((s.length()==9) && s.endsWith(" ")) {
                    UI.println(s);
                    s="";
                }
                if(s.length()==10 && text.charAt(i+1)==' ') {
                    UI.println(s);
                    s="";
                    i++;
                }
                if(s.length()==10 && text.charAt(i+1)!=' ') {
                    UI.println(s.substring(0,9)+"-");
                    s=""+text.charAt(i);
                }

            }
            UI.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /******************************************************
     * YOU CAN USE THE METHODS BELOW THIS LINE            * 
     * TO TEST YOUR PROGRAM                               *
     ******************************************************/

    public void test(){      
        String text = UI.askString("Enter some words here: ");
        this.printText(text);
    }

    public void setupGUI() {
        UI.addButton("Run", this::test);
        UI.addButton("Quit", UI::quit);
        UI.setWindowSize(800,500);
        UI.setDivider(1.0);
    }

    public static void main(String[] args) {
        new Question5().setupGUI();
    }
}

