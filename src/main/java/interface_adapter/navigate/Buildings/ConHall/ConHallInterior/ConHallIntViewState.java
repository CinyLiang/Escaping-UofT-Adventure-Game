package interface_adapter.navigate.Buildings.ConHall.ConHallInterior;

public class ConHallIntViewState {
    // Location
    private String location = "ConHall Interior";

    private int numberOfKeys = 2; // test value

    public ConHallIntViewState() {
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public String getLocation() {
        return location;
    }

    public int getNumberOfKeys() {
        return numberOfKeys;
    }
}
