package interface_adapter.navigate.Buildings.UC.UCExterior;

import interface_adapter.ViewModel;

public class UCExtViewModel extends ViewModel<UCExtViewState> {
    public UCExtViewModel() {
        super("uc_exterior_view");
        setState(new UCExtViewState());
    }
}
