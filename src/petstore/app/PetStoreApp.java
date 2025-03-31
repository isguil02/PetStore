package petstore.app;

import petstore.inventory.Bird;
import petstore.inventory.Pet;
import petstore.inventory.Fish;
import petstore.inventory.HabitatType;
import petstore.inventory.FeedingSchedule;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import java.util.ArrayList;
import java.util.List;

/**
 *  @author Isaiah Guilliatt
 *  @since 3/30/2025
 *  @version 1.0 beta
 *  @see <a href="{https://github.com/isguil02/PetStore}">GitHub Repository</a>
 *
 */

public class PetStoreApp {

    private static final String INVENTORY_FILE = "inventory.txt";

    private static final String DOUBLE_DASH_LINE = String.format("%50s", "").replace(' ', '=');

    private static final String SINGLE_DASH_LINE = DOUBLE_DASH_LINE.replace('=', '-');

    //private final List<Pet> inventory;

    private void displayAppHeading() {
        System.out.println(DOUBLE_DASH_LINE);
        System.out.println("Welcome to the Pet Store App");
        System.out.println(DOUBLE_DASH_LINE);
    }

    public static void main(String[] args) {

        PetStoreApp app = new PetStoreApp();

        app.displayAppHeading();

        try {
            //app.mainMenu();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Sorry but this program ended with an error. Please contact Isaiah!");
        }
        Input.sc.close();
    }// end of main method

} // end of PetStoreApp class
