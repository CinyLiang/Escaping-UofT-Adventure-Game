package interface_adapter.navigate.Buildings.Knox.KnoxExterior;

import interface_adapter.ViewModel;

public class KnoxExtViewModel extends ViewModel<KnoxExtViewState> {
    public KnoxExtViewModel() {
        super("knox_exterior_view");
        setState(new KnoxExtViewState());
    }
}
