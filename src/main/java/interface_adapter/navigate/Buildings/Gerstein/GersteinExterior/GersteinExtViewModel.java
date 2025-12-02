package interface_adapter.navigate.Buildings.Gerstein.GersteinExterior;

import interface_adapter.ViewModel;
import interface_adapter.navigate.Buildings.Knox.KnoxExterior.KnoxExtViewState;

public class GersteinExtViewModel extends ViewModel<GersteinExtViewState> {
    public GersteinExtViewModel() {
        super("gerstein_exterior_view");
        setState(new GersteinExtViewState());
    }
}
