package interface_adapter.navigate.Buildings.Knox.KnoxInterior;

public class KnoxIntViewState {
    private String storyText;
    private String direction;

    // Location
    private String location = "Knox Interior";

    public KnoxIntViewState() {
        this.storyText = "abcdefghijklmnopqrstuvwxyz";
        this.direction = "";
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public String getLocation() {
        return location;
    }
}
