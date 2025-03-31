package petstore.inventory;

public class Bird extends Pet {
    private String species;
    private HabitatType habitatType;

    public Bird(String name, String dateReceived, String species, HabitatType habitatType) throws Exception {
        super(name, dateReceived);
        setSpecies(species);
        setHabitatType(habitatType);
    }

    public Bird(int id, String name, String dateReceived, String species, HabitatType habitatType) throws Exception {
        super(id, name, dateReceived);
        setSpecies(species);
        setHabitatType(habitatType);
    }

    public String getSpecies() { return species; }

    public void setSpecies(String species) throws Exception {
        species = species.trim();

        if (species.isBlank())
            throw new Exception("Invalid data! species can not be empty.");

        this.species = species;
    }

    public HabitatType getHabitatType() { return habitatType; }

    public void setHabitatType(HabitatType habitatType) { this.habitatType = habitatType; }

    @Override
    public void displayPet() {
        super.displayPet();
        System.out.printf(" %-15s %-10s", species, habitatType);
    }
}
