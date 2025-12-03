package interface_adapter.navigate.Buildings.Knox.KnoxInterior;

import interface_adapter.ViewModel;

public class KnoxIntViewModel extends ViewModel<KnoxIntViewState> {
    public KnoxIntViewModel() {
        super("knox_interior_view");
        setState(new KnoxIntViewState());
    }
}
