package interface_adapter.navigate;

import interface_adapter.ViewManagerModel;
import interface_adapter.navigate.Buildings.Gerstein.GersteinExterior.GersteinExtViewModel; // gerstein
import interface_adapter.navigate.Buildings.Gerstein.GersteinInterior.GersteinIntViewModel;
import interface_adapter.navigate.Buildings.Knox.KnoxExterior.KnoxExtViewModel; // knox
import interface_adapter.navigate.Buildings.Knox.KnoxInterior.KnoxIntViewModel;
import interface_adapter.navigate.Buildings.UC.UCExterior.UCExtViewModel; // uc
import interface_adapter.navigate.Buildings.UC.UCInterior.UCIntViewModel;
import interface_adapter.play_card_game.CardGameViewModel;
import interface_adapter.trivia_game.TriviaGameViewModel;
import interface_adapter.win_game.WinGameViewModel;
import use_case.navigate.NavigateOutputBoundary;
import use_case.navigate.NavigateOutputData2;

/**
 * Presenter for the Navigation use case.
 * Receives output from the Interactor and updates the ViewModel.
 */
public class NavigatePresenter implements NavigateOutputBoundary {
    private final NavigateViewModel navigateViewModel;
    private final ViewManagerModel viewManagerModel;

    private final WinGameViewModel winGameViewModel;
    private final CardGameViewModel cardGameViewModel;
    private final TriviaGameViewModel triviaGameViewModel;
    private final KnoxExtViewModel knoxExtViewModel; // knox
    private final KnoxIntViewModel knoxIntViewModel;
    private final GersteinExtViewModel gersteinExtViewModel; // gerstein
    private final GersteinIntViewModel gersteinIntViewModel;
    private final UCExtViewModel ucExtViewModel; // uc
    private final UCIntViewModel ucIntViewModel;

    public NavigatePresenter(NavigateViewModel navigateViewModel, ViewManagerModel viewManagerModel, WinGameViewModel winGameViewModel, CardGameViewModel cardGameViewModel, TriviaGameViewModel triviaGameViewModel, KnoxExtViewModel knoxExtViewModel, KnoxIntViewModel knoxIntViewModel, GersteinExtViewModel gersteinExtViewModel, GersteinIntViewModel gersteinIntViewModel, UCExtViewModel ucExtViewModel, UCIntViewModel ucIntViewModel) {
        this.navigateViewModel = navigateViewModel;
        this.viewManagerModel = viewManagerModel;
        this.winGameViewModel = winGameViewModel;
        this.cardGameViewModel = cardGameViewModel;
        this.triviaGameViewModel = triviaGameViewModel;
        this.knoxExtViewModel = knoxExtViewModel;
        this.knoxIntViewModel = knoxIntViewModel;
        this.gersteinExtViewModel = gersteinExtViewModel;
        this.gersteinIntViewModel = gersteinIntViewModel;
        this.ucExtViewModel = ucExtViewModel;
        this.ucIntViewModel = ucIntViewModel;
    }

    @Override
    public void prepareSuccessView(NavigateOutputData2 outputData) {
        String targetRaw = outputData.getTargetView();
        String target = targetRaw == null ? "" : targetRaw.toLowerCase();
        System.out.println("[NavigatePresenter] prepareSuccessView called. rawTarget=\"" + targetRaw + "\", normalized=\"" + target + "\"");

        switch (target) {
            case "win game" -> {
                NavigateState state = this.navigateViewModel.getState();
                if (state.getNumberOfKeys() == state.getTargetNumberOfKeys()) {
                    viewManagerModel.setState(winGameViewModel.getViewName());
                } else {
                    state.setStoryText("Oh no, you can't enter yet. You need to get " + (2 - state.getNumberOfKeys()) + " more keys");
                    this.navigateViewModel.firePropertyChange();

                    // ok so this makes the whole controller then interactor bit useless lmaoaoaoa
                }
            }
            case "card game" -> {
                updateNavigation(cardGameViewModel.getState().getLocationName());
                viewManagerModel.setState(cardGameViewModel.getViewName());
            }
            case "trivia game" -> {
                updateNavigation(triviaGameViewModel.getState().getLocationName());
                viewManagerModel.setState(triviaGameViewModel.getViewName());
            }
            case "knox exterior" -> {
                updateNavigation(knoxExtViewModel.getState().getLocation());
                viewManagerModel.setState(knoxExtViewModel.getViewName());
            }
            case "knox interior" -> {
                updateNavigation(knoxIntViewModel.getState().getLocation());
                viewManagerModel.setState(knoxIntViewModel.getViewName());
            }
            case "gerstein exterior" -> {
                updateNavigation(gersteinExtViewModel.getState().getLocation());
                viewManagerModel.setState(gersteinExtViewModel.getViewName());
            }
            case "gerstein interior" -> {
                updateNavigation(gersteinIntViewModel.getState().getLocation());
                viewManagerModel.setState(gersteinIntViewModel.getViewName());
            }
            case "uc exterior" -> {
                updateNavigation(ucExtViewModel.getState().getLocation());
                viewManagerModel.setState(ucExtViewModel.getViewName());
            }
            case "uc interior" -> {
                updateNavigation(ucIntViewModel.getState().getLocation());
                viewManagerModel.setState(ucIntViewModel.getViewName());
            }
            default -> {
                // optional: ignore or throw
            }
        }
        System.out.println("[NavigatePresenter] prepareSuccessView finished; viewManagerState=" + viewManagerModel.getState());
        viewManagerModel.firePropertyChange();
    }

    @Override
    public void prepareFailView(String error) { }

    @Override
    public void updateNavigation(String newLocation) {
        NavigateState state = navigateViewModel.getState();
        state.setLocation(newLocation);
        navigateViewModel.firePropertyChange();
    }

}
