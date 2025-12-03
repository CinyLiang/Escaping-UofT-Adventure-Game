package interface_adapter.navigate.Buildings.UC.UCInterior;

import interface_adapter.ViewModel;

public class UCIntViewModel extends ViewModel<UCIntViewState> {
    public UCIntViewModel() {
        super("uc_interior_view");
        setState(new UCIntViewState());
    }
}
