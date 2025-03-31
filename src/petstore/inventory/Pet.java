package petstore.inventory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pet {

    private static int lastId = 0;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");

    protected final int id;
    protected String name;
    protected LocalDate dateReceived;
    protected String description;

    public Pet(String name, String dateReceived) throws Exception {
        this.id = ++Pet.lastId;
        setName(name);
        setDateReceived(dateReceived);
    }

    public Pet(int id, String name, String dateReceived) throws Exception {
        this.id = id;
        setName(name);
        setDateReceived(dateReceived);
    }

    public static void setLastId(int lastId){ Item.lastId = lastId;  }

    public int getId() { return id; }

    public String getTitle() {
        return name;
    }

    public void setName(String name) throws Exception {
        name = name.trim();

        if (name.isBlank()) {
            throw new Exception("Invalid! Name cannot be empty.");
        }

        this.name = name;
    }

    public String getDateReceived() {
        return dateReceived.format(Pet.formatter);
    }

    public void setDateReceived(String dateReceived) throws Exception {
        try {
            this.dateReceived = LocalDate.parse(dateReceived, Pet.formatter);
        } catch (Exception e) {
            throw new Exception("Invalid date! Must be MM-DD-YYYY");
        }
    }

    public String getDescription() { return description; }

    public void setDescription(String description) {
        this.description = description.trim();
    }

    public void displayPet() {
        System.out.printf("%3d %-15s %10s", id, name, getDateReceived());
    }

    @Override
    public String toString(){
        return id + " " + name + " " + getDateReceived();
    }

}
