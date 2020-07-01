// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2020T1, Assignment 10
 * Name: Shaylen Mamidi
 * Username: mamidishay 
 * ID: 300535776
 */


import ecs100.*;
import java.util.ArrayList;
import java.util.*;
import java.io.*;
import java.nio.file.*;

/** 
 * Question 4: Write programs with Array and ArrayList
 * Write methods for a program that calculates ticket fares for a Wellington train line.
 */
public class Question4{    

    // DO NOT CHANGE THE NAMES OR TYPES OF THESE FIELDS

    // Cash fares for Wellington trains based on the number of zones in the trip:
    private double[] fares = new double[]{2.5,4,5,5.5,6.5,8.5,9.5,10.5,12,13,15,16,17.5,19};

    // ArrayList of Stations on the train line.
    private ArrayList<Station> stations = new ArrayList<Station>();

    //----------------------------------------------------------------------
    /*
     * Return the right fare for a trip from zone1 to zone2 
     *  (which might be the same zone).
     * For example:
     *   findFare(10,10) should return 2.5 and
     *   findFare(5, 8) should return 5.5.
     * Note that the trip can go from a larger zone number to a smaller zone number,
     *   e.g. from zone 8 to zone 3, and
     * Note the fares cannot be negative.
     */
    public double findFare(int zone1, int zone2){
        /*# YOUR CODE HERE */
        int fareIndex = Math.abs(zone1 - zone2);
        return fares[fareIndex];
    }

    //----------------------------------------------------------------------
    /*
     * Read data from a data file of stations (such as "kpl-stations.txt")
     *  into the ArrayList in the stations field.
     * The stations field contains an ArrayList of Station objects.
     * The Station.java file specifies Station objects which store information
     *  about a station: its name and zone number.
     */
    public void loadStations(String filename) {
        /*# YOUR CODE HERE */
        try {
            List<String> Lines = Files.readAllLines(Path.of(filename));
            for (String line : Lines) {
                Scanner sc = new Scanner(line);
                String name = sc.next();
                int zone = sc.nextInt();
                stations.add(new Station(name, zone));
            }
        } catch (IOException e) {
            UI.println("File reading failed");
            e.printStackTrace();
        }

    }

    //----------------------------------------------------------------------
    /*
     * Looks up the name of a station in the list of stations, 
     * and returns the zone number of the station.
     * If there is no station of that name in the list, findZone should return -1.
     */
    public int findZone(String stationName){
        /*# YOUR CODE HERE */
        for (Station s : stations) {
            if (s.getName().equalsIgnoreCase(stationName)) {
                return s.getZone();
            }
        }
        return -1;
    }

    //----------------------------------------------------------------------
    /*
     * Calculates and returns the fare to travel between the starting station
     *  and destination station, specified in the two parameters.
     * For example,
     *  ticketFare("Porirua", "Waikanae") should return 8.5.
     * If either of the station names is not in the list of stations,
     *  the method should return 0.0.
     */
    public double ticketFare(String start, String dest){
        /*# YOUR CODE HERE */
        int startZone = -1;
        int endZone = -1;
        for (Station s : stations) {
            if (s.getName().equalsIgnoreCase(start)) {
                startZone = s.getZone();
            }
            if (s.getName().equalsIgnoreCase(dest)) {
                endZone = s.getZone();
            }
        }
        if (startZone > 0 && endZone > 0) {
            return findFare(startZone, endZone);
        }
        return 0.0d;

    }    

    //----------------------------------------------------------------------

    /******************************************************
     * YOU CAN USE THE METHODS BELOW THIS LINE            * 
     * TO TEST YOUR PROGRAM                               *
     ******************************************************/
    public void testFindFare(){
        int startZone = UI.askInt("Start zone: ");
        int destZone = UI.askInt("Destination zone: ");
        double fare = this.findFare(startZone, destZone);
        UI.printf("Fare from zone %d to zone %d is: $%.2f\n", startZone, destZone, fare);
    }

    public void testLoadStations(){
        this.loadStations("kpl-stations.txt");
        UI.printf("stations field now contains %d stations\n", this.stations.size());
    }

    public void testFindZone(){
        String stName = UI.askString("Station name:");
        UI.printf("Station %s is in zone %d\n", stName, findZone(stName));
    }

    public void testTicketFare(){
        String startName = UI.askString("Start station name:");
        String destName = UI.askString("Dest station name:");
        UI.printf("Fare from %s to %s is $%.2f\n", startName, destName, ticketFare(startName, destName));
    }

    public void setupGUI() {
        UI.initialise();
        UI.addButton("test findFare", this::testFindFare);
        UI.addButton("test loadStations", this::testLoadStations);
        UI.addButton("test find Zone", this::testFindZone);
        UI.addButton("test ticketFare", this::testTicketFare);
        UI.addButton("Quit", UI::quit);
        UI.setWindowSize(800,500);
        UI.setDivider(1.0);
    }

    public static void main(String[] args) {
        new Question4().setupGUI();
    }

    //------------------------------------------------------------------
    // THESE METHODS ARE REQUIRED FOR MARKING
    // DO NOT USE THESE METHODS
    // DO NOT REMOVE OR MODIFY THESE METHODS!!!

    public double[] getFares(){return this.fares;}

    public ArrayList<Station> getStations(){return this.stations;}

    public void setStations(ArrayList<Station> s){this.stations = s;}

}
