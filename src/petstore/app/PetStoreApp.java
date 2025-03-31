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

    private void deletePet() {

    }// end of deletePet method

    private Bird addBird(String name, String dateReceived, String description) throws Exception {
        Bird bird = new Bird();
        return bird;
    }// end of addBird method

    private Fish addFish(String name, String dateReceived, String description) throws Exception {
        Fish fish = new Fish();
        return fish;
    } // end of addFish method

    private void addPet() throws Exception {

    } // end of addPet method

    private void displayInventory(){

    } // end of displayInventory method

    public void saveInventory() {

    } // end of saveInventory method - why is there no comment in your code?

    public void loadInventory(){

    } // end of loadInventory method

    private void mainMenu() throws Exception {

        boolean keepRunning = true;

        while (keepRunning) {

            System.out.println(SINGLE_DASH_LINE);
            System.out.println("Main Menu");
            System.out.println(SINGLE_DASH_LINE);

            System.out.println("0 = End Program");
            System.out.println("1 = Add Pet");
            System.out.println("2 = Delete Pet");
            System.out.println("3 = Display Inventory");
            System.out.println("4 = Save Inventory");
            System.out.println("5 = Load Inventory");

            System.out.println(SINGLE_DASH_LINE);
            int userInput = Input.getIntRange("Menu Choice: ", 0, 5);
            System.out.println(SINGLE_DASH_LINE);
            switch (userInput) {
                case 0:
                    keepRunning = false;
                    break;
                default:
                    throw new Exception("Invalid menu choice: " + userInput);
            } // end of switch
        } // end of while loop
    } // end of mainMenu method


        public static void main(String[] args) {

        PetStoreApp app = new PetStoreApp();

        app.displayAppHeading();

        try {
            app.mainMenu();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Sorry but this program ended with an error. Please contact Isaiah!");
        }
        Input.sc.close();
    }// end of main method

} // end of PetStoreApp class
