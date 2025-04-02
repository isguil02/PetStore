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

    private static final String DOUBLE_DASH_LINE = String.format("%57s", "").replace(' ', '=');

    private static final String SINGLE_DASH_LINE = DOUBLE_DASH_LINE.replace('=', '-');

    private final List<Pet> inventory;

    public PetStoreApp() { this.inventory = new ArrayList<>();}

    private void displayAppHeading() {
        System.out.println(DOUBLE_DASH_LINE);
        System.out.println("Welcome to the Pet Store App");
        System.out.println(DOUBLE_DASH_LINE);
    }

    private void deletePet() {
        System.out.println("Delete Inventory");
        System.out.println(SINGLE_DASH_LINE);

        int id = Input.getInt("Please enter the inventory id: ");

        for (Pet pet : inventory){
            System.out.println(id);
            if (pet.getId() == id){
                inventory.remove(pet);
                System.out.println("Successful Delete: " + pet);
                Input.getLine("Press enter to continue...");
                return;
            }
        }

        System.out.println("ERROR: Inventory ID:" + id + " NOT found!");

    }// end of deletePet method

    private Bird addBird(String name, String dateReceived, String description) throws Exception {

        Bird bird;
        int userInput;
        String species;
        HabitatType habitatType = null;

        species = Input.getString("Species: ");
        try {
            userInput = Input.getIntRange("Habitat Type 1=Cage, 2=Aquarium, 3=Terrarium, 4=Open_Space: ", 1, 4);
            habitatType = HabitatType.values()[userInput - 1];
        } catch (Exception e){
            throw new Exception("Invalid data! habitat choice = " + habitatType);
        }

        bird = new Bird(name, dateReceived, species, habitatType);
        bird.setDescription(description);

        return bird;
    }// end of addBird method

    private Fish addFish(String name, String dateReceived, String description) throws Exception {
        Fish fish;
        String species;
        FeedingSchedule feedingSchedule = null;

        species = Input.getString("Species: ");

        try {
            int userInput = Input.getIntRange("Feeding Schedule 1=Once Daily, 2=Twice Daily, 3=Three times a day, 4=Weekly, 5=Biweekly: ", 1, 5);
            feedingSchedule = FeedingSchedule.values()[userInput - 1];
        } catch (Exception e){
            throw new Exception("Invalid data! Periodical Feeding Schedule = " + feedingSchedule);
        }

        fish = new Fish(name, dateReceived, species, feedingSchedule);
        fish.setDescription(description);

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
                Fish fish = addFish(name, dateReceived, description);
                inventory.add(fish);
                System.out.println("Successful Add: " + fish);
                Input.getLine("Press enter to continue...");
                break;
            case 3:
                break;
            default:
                throw new Exception("Invalid pet type: " + petType);
        } // end of switch

    } // end of addPet method

    private void displayInventory(){
        System.out.println("Bird Inventory");
        System.out.println(SINGLE_DASH_LINE);
        System.out.println("ID  Name            Date Rec'd Species         Habitat");
        System.out.println("--- --------------- ---------- --------------- ----------");
        for (Pet pet : inventory) {
            if (pet instanceof Bird){
                pet.displayPet();
            }
        }
        System.out.println();


        System.out.println("Fish Inventory");
        System.out.println(SINGLE_DASH_LINE);
        System.out.println("ID  Name            Date Rec'd Species         Schedule");
        System.out.println("--- --------------- ---------- --------------- ----------");
        for (Pet pet : inventory) {
            if (pet instanceof Fish){
                pet.displayPet();
            }
        }
        System.out.println();

        Input.getLine("Press enter to continue...");
    } // end of displayInventory

    public void saveInventory() {
        System.out.println("Saving data! Please wait...");
/*
Try-With-Resources: shortcut way to declare and initialize in one step
when you use this way of opening the file as part of the try statement
Java will automatically close the file so there is no need to write a close statement
NOTE: Java doesn't automatically close the file if the file is opened inside the block
*/
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PetStoreApp.INVENTORY_FILE))) {


            for(Pet pet : inventory){


                // The file will be piped delimited so each field is separated by a |
                if (pet instanceof Bird)
                    bw.write("BIRD|");
                else if (pet instanceof Fish)
                    bw.write("FISH|");


                bw.write(pet.getId() + "|" + pet.getName() + "|" + pet.getDateReceived() + "|" + pet.getDescription() + "|");


                if (pet instanceof Bird)
                    bw.write(((Bird) pet).getSpecies() + "|" + ((Bird) pet).getHabitatType() + "\n");
                else if (pet instanceof Fish)
                    bw.write(((Fish) pet).getSpecies() + "|" + ((Fish) pet).getFeedingSchedule() + "\n");
            }


            bw.flush();
            // No explicit close needed - automatically handled when using Try-With-Resources


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        System.out.println(inventory.size() + " Inventory records successfully written to " + PetStoreApp.INVENTORY_FILE);
        Input.getLine("Please any key to continue...");

    } // end of saveInventory method - why is there no comment in your code?

    public void loadInventory(){
        System.out.println("Loading data! Please wait...");


        inventory.clear(); //empty the ArrayList so we can load the data from the file


/*
Try-With-Resources: shortcut way to declare and initialize in one step
when you use this way of opening the file as part of the try statement
Java will automatically close the file so there is no need to write a close statement
NOTE: Java doesn't automatically close the file if the file is opened inside the block
*/
        try (BufferedReader br = new BufferedReader(new FileReader(PetStoreApp.INVENTORY_FILE))) {


            String inLine;


            while ((inLine = br.readLine()) != null) {  // exclude newline


                String[] data = inLine.split("[|]"); // [|] is a regex for splitting by the pipe character


                //0=pet 1=id, 2=title, 3=date, 4=description, 5=species, 6=habitat type/feeding schedule
                switch(data[0]){
                    case "BIRD":
                        Bird b = new Bird(Integer.parseInt(data[1]), data[2], data[3], data[5], HabitatType.valueOf(data[6]));
                        b.setDescription(data[4]);
                        inventory.add(b);
                        break;
                    case "FISH":
                        Fish fish = new Fish(Integer.parseInt(data[1]), data[2], data[3], data[5], FeedingSchedule.valueOf(data[6]));
                        fish.setDescription(data[4]);
                        inventory.add(fish);
                        break;
                    default:
                        throw new Exception("Invalid inventory type: " + data[0]);
                } // end of switch


            } // end of while loop


            // No explicit close needed - automatically handled when using Try-With-Resources


            Pet.setLastId(inventory.get(inventory.size() - 1).getId());


        } catch (Exception e) {
            e.getMessage();
        } // end of try-catch


        System.out.println(inventory.size() + " Inventory records successfully loaded from " + PetStoreApp.INVENTORY_FILE);
        Input.getLine("Please any key to continue...");



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
                case 2:
                    try {
                        this.deletePet();
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                        Input.getLine("Press enter to continue...");
                    }
                    break;
                case 3:
                    displayInventory();
                    break;
                case 4:
                    saveInventory();
                case 5:
                    loadInventory();
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
