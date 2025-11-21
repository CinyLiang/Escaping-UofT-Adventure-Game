package interface_adapter.navigate;

import entity.Location;
import java.util.Map;

public class NavigatePresenter {
    private Map<String, Location> locationMap;

    public NavigatePresenter() {}

    public void updateLocationMap(Map<String, Location> locationMap) {
        this.locationMap = locationMap;
    }
}