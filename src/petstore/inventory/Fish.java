package petstore.inventory;

public class Fish extends Pet {
    private String species;
    private FeedingSchedule feedingschedule;

    public Fish(String name, String dateReceived, String species, FeedingSchedule feedingschedule) throws Exception {
        super(name, dateReceived);
        setSpecies(species);
        setFeedingSchedule(feedingschedule);
    }

    public Fish(int id, String name, String dateReceived, String species, FeedingSchedule feedingschedule) throws Exception {
        super(id, name, dateReceived);
        setSpecies(species);
        setFeedingSchedule(feedingschedule);
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) throws Exception {
        species = species.trim();

        if (species.isBlank())
            throw new Exception("Invalid data! Species can not be empty.");

        this.species = species;
    }

    public FeedingSchedule getFeedingSchedule() { return feedingschedule; }

    public void setFeedingSchedule(FeedingSchedule feedingschedule) {
        this.feedingschedule = feedingschedule;
    }

    @Override
    public void displayPet(){
        super.displayPet();
        System.out.printf(" %-15s %-10s\n", species, feedingschedule);
    }
}


