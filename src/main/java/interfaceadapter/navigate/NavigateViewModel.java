package interfaceadapter.navigate;
import interfaceadapter.ViewModel;

public class NavigateViewModel extends ViewModel<NavigateState> {
    public NavigateViewModel() {
        super("navigate_view");
        setState(new NavigateState());
    }
}
