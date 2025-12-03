package interface_adapter.navigate.Buildings.ConHall.ConHallInterior;

public class ConHallIntViewState {
    // Location
    private String location = "ConHall Interior";

    private String storyText = "";
    public ConHallIntViewState() {
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public String getLocation() {
        return location;
    }

    private void setStoryText(String storyText) { this.storyText = storyText; }
}
