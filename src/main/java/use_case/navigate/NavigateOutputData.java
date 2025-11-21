package use_case.navigate;

public class NavigateOutputData {

    private final String storyText;
    private final String direction;

    public NavigateOutputData(String storyText, String direction) {
        this.storyText = storyText;
        this.direction = direction;}

    public String getStoryText() {
        return storyText;}

    public String getDirection() {
        return direction;
    }
}
