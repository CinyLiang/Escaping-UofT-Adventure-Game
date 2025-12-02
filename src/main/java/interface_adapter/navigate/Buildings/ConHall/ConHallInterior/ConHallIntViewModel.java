package interface_adapter.navigate.Buildings.ConHall.ConHallInterior;

import interface_adapter.ViewModel;

public class ConHallIntViewModel extends ViewModel<ConHallIntViewState> {
    public ConHallIntViewModel() {
        super("conHall_interior_view");
        setState(new ConHallIntViewState());
    }
}
