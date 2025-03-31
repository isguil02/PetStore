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

    private final List<Pet> inventory;

    public PetStoreApp() { this.inventory = new ArrayList<>();}

    private void displayAppHeading() {
        System.out.println(DOUBLE_DASH_LINE);
        System.out.println("Welcome to the Pet Store App");
        System.out.println(DOUBLE_DASH_LINE);
    }

    private void deletePet() {

    }// end of deletePet method

    private Bird addBird(String name, String dateReceived, String description) throws Exception {

        Bird bird;
        int userInput;
        String species;
        HabitatType habitatType = null;

        species = Input.getString("Species: ");
        try {
            userInput = Input.getIntRange("Habitat Type 1=Cage, 2=Aquarium, 3=Terrarium, 4=Open_Space: ", 1, 3);
            habitatType = HabitatType.values()[userInput - 1];
        } catch (Exception e){
            throw new Exception("Invalid data! habitat choice = " + habitatType);
        }

        bird = new Bird(name, dateReceived, species, habitatType);
        bird.setDescription(description);

        return bird;
    }// end of addBird method

    private Fish addFish(String name, String dateReceived, String description) throws Exception {
        Fish fish = new Fish();
        return fish;
    } // end of addFish method

    private void addPet() throws Exception {
        System.out.println("Add Pet");
        System.out.println(SINGLE_DASH_LINE);

        System.out.println("Please enter the following pet information:");
        String name = Input.getString("Name: ");
        String dateReceived = Input.getDate("Date Received (MM-DD-YYYY): ");
        String description = Input.getLine("Description or press enter to continue: ");

        int petType = Input.getIntRange("Type 1=Bird, 2=Fish: ", 1, 2);
        switch (petType) {
            case 1:
                Bird bird = addBird(name, dateReceived, description);
                inventory.add(bird);
                System.out.println("Successful Add: " + bird);
                Input.getLine("Press enter to continue...");
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                throw new Exception("Invalid pet type: " + petType);
        } // end of switch

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
                case 1:
                    try {
                        this.addPet();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        Input.getLine("Press enter to continue...");
                    }
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
