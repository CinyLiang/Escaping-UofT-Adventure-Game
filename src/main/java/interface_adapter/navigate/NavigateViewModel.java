package interface_adapter.navigate;
import interface_adapter.ViewModel;

public class NavigateViewModel extends ViewModel<NavigateState> {
    public NavigateViewModel() {
        super("navigate_view");
        setState(new NavigateState());
    }
}
