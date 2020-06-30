// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2020T1, Assignment 1
 * Name:
 * Username:
 * ID:
 */

import ecs100.*;

/** Program for calculating cost of shipping a package */

public class ShippingCalculator{

    public static final double HANDLING = 1.60;    // cost of handling a shipment
    public static final double SIZE_RATE = 300;           // cost per cubic meter
    public static final double WEIGHT_RATE = 1.50;        // cost per kg

    /**
     * Calculates and prints cost of shipping a package.
     */
    public void calculateShippingCore(){
        /*# YOUR CODE HERE */
        UI.printf("Shipping Calculator Core\n");
        double length = UI.askDouble("Length of package (cm) : ");
        double width = UI.askDouble("Width of package (cm) : ");
        double height = UI.askDouble("Height of package (cm) : ");
        double weight = UI.askDouble("Weight of package (kg) : ");
        double zones = UI.askDouble("Number of Zones (minimum 1): ");
        
        double sizeCharge = length/100 * height*100 * width/100 *SIZE_RATE;
        double weightCharge = weight * WEIGHT_RATE;
        double totalCost = (sizeCharge + weightCharge) * zones + HANDLING;
        
        UI.printf("size charge per zone: $%.2f%n", sizeCharge);
        UI.printf("weight charge per zone: $%.2f%n", weightCharge);
        UI.printf("zones: $%.2f%n", zones);
        UI.printf("Total Charge: $%.2f%n", totalCost);
    }

    /**
     * Calculates and prints cost of shipping a collection of packages.
     */
    public void calculateShippingCompletion(){
        /*# YOUR CODE HERE */
        UI.printf("------------------------------------------------\n");
        UI.printf("Shipping Calculator Completion\n");
        double zones = UI.askDouble("Number of Zones (minimum 1): ");
        
        int numGroup1 = UI.askInt("Number of Package of first size: ");
        double length1 = UI.askDouble("Length of package (cm) : ")/100;
        double width1 = UI.askDouble("Width of package (cm) : ")/100;
        double height1 = UI.askDouble("Height of package (cm) : ")/100;
        double weight1 = UI.askDouble("Weight of package (kg) : "); 
        double sizeCharge1 = (length1 * height1 * width1) * numGroup1 * SIZE_RATE;
        double weightCharge1 = weight1 * numGroup1 * WEIGHT_RATE;
        
        int numGroup2 = UI.askInt("Number of Package of second size: ");
        double length2 = UI.askDouble("Length of package (cm) : ")/100;
        double width2 = UI.askDouble("Width of package (cm) : ")/100;
        double height2 = UI.askDouble("Height of package (cm) : ")/100;
        double weight2 = UI.askDouble("Weight of package (kg) : "); 
        double sizeCharge2 = (length2 * height2 * width2) * numGroup2 * SIZE_RATE;
        double weightCharge2 = weight2 * numGroup2 * WEIGHT_RATE;
        
        double sizeCharge = sizeCharge1 + sizeCharge2;
        double weightCharge = weightCharge1 + weightCharge2;
        double totalCost =(sizeCharge + weightCharge) * zones + HANDLING;
        int num = numGroup1 + numGroup2;
        double discount = totalCost * (1 - 1.0/num) /3.0;
        
        UI.println("---------------------------------");
        UI.printf("zones: $%.2f%n", zones);
        UI.printf("Group 1 size charge per zone: $%.2f%n", sizeCharge1);
        UI.printf("Group 1 weight charge per zone: $%.2f%n", weightCharge1);
        UI.printf("Group 2 size charge per zone: $%.2f%n", sizeCharge2);
        UI.printf("Group 2 weight charge per zone: $%.2f%n", weightCharge2);
        UI.printf("Total before discount: $%.2f%n", totalCost);
        UI.printf("Discount for %d items: $%.2f%n", num, discount);
        UI.printf("Total after discount: $%.2f%n", (totalCost - discount));
    }

    public void setupGUI(){
        UI.initialise();
        UI.addButton("Core", this::calculateShippingCore ); 
        UI.addButton("Completion", this::calculateShippingCompletion );
        UI.addButton("Quit", UI::quit);
        UI.setDivider(1);    // Expand the Text pane
    }   


}
