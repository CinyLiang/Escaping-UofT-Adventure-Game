package interface_adapter.navigate;

import interface_adapter.ViewModel;
import interface_adapter.navigate.NavigateState;

public class KnoxExtViewModel extends ViewModel<KnoxExtViewState> {
    public KnoxExtViewModel() {
        super("knox_exterior_view");
        setState(new KnoxExtViewState());
    }
}
