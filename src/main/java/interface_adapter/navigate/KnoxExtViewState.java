package interface_adapter.navigate;

import java.util.HashSet;
import java.util.Set;

public class KnoxExtViewState{
    private int numberOfKeys;
    private String storyText;
    private String direction;

    // Location
    private String location = "Knox Exterior";

    public KnoxExtViewState() {
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
