package interface_adapter.navigate.Buildings.ConHall.ConHallExterior;

import interface_adapter.ViewModel;

public class ConHallExtViewModel extends ViewModel<ConHallExtViewState> {
    public ConHallExtViewModel() {
        super("conHall_exterior_view");
        setState(new ConHallExtViewState());
    }
}
