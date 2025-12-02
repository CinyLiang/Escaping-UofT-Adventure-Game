package interface_adapter.navigate.Buildings.Gerstein.GersteinInterior;

import interface_adapter.ViewModel;

public class GersteinIntViewModel extends ViewModel<GersteinIntViewState> {
    public GersteinIntViewModel() {
        super("gerstein_interior_view");
        setState(new GersteinIntViewState());
    }
}