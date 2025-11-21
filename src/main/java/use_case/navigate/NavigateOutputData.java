package use_case.navigate;

import java.util.Map;

public class NavigateOutputData {
    private final String locationName;
    private final String locationDesc;
    private final Map<String, String> locationMap;

    public NavigateOutputData(String locationName, String locationDesc, Map<String, String> locationMap) {
        this.locationName = locationName;
        this.locationDesc = locationDesc;
        this.locationMap = locationMap;
    }

    public String getLocationName() { return locationName; }

    public String getLocationDesc() { return locationDesc; }

    public Map<String, String> getLocationMap() { return locationMap; }
}