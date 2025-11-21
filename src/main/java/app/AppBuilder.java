package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.clear_history.*;
import interface_adapter.navigate.NavigateViewModel;
import interface_adapter.quit_game.QuitGameController;

import interface_adapter.save_progress.*;
import interface_adapter.view_progress.*;

import view.*;

import use_case.clear_history.*;
import use_case.save_progress.*;
import use_case.view_progress.*;

import javax.swing.*;
import java.awt.*;

public class AppBuilder {

    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();

    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager =
            new ViewManager(cardPanel, cardLayout, viewManagerModel);

    // ViewModels
    private NavigateViewModel navigateViewModel;
    private ClearHistoryViewModel clearHistoryViewModel;
    private ViewProgressViewModel viewProgressViewModel;

    // Views
    private HomeView homeView;
    private NavigateView navigateView;

    private String initialViewName = null;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    public AppBuilder addView(JPanel view, String name) {
        cardPanel.add(view, name);
        if (initialViewName == null) {
            initialViewName = name;
        }
        return this;
    }

    public AppBuilder addClearHistoryUseCase() {
        ClearHistoryPresenter presenter =
                new ClearHistoryPresenter(clearHistoryViewModel);
        ClearHistoryInputBoundary interactor =
                new ClearHistoryInteractor(presenter);
        ClearHistoryController controller =
                new ClearHistoryController(interactor);
        navigateView.setClearHistoryController(controller);
        navigateView.setClearHistoryViewModel(clearHistoryViewModel);
        return this;
    }

    public AppBuilder addSaveProgressUseCase(SaveProgressDataAccessInterface gateway) {
        SaveProgressPresenter presenter = new SaveProgressPresenter();
        SaveProgressInputBoundary interactor = new SaveProgressInteractor(gateway, presenter);
        SaveProgressController controller = new SaveProgressController(interactor);
        navigateView.setSaveProgressController(controller);
        return this;
    }
    public AppBuilder addViewProgressUseCase(ViewProgressDataAccessInterface gateway) {
        viewProgressViewModel = new ViewProgressViewModel();
        ViewProgressPresenter presenter = new ViewProgressPresenter(viewProgressViewModel);
        ViewProgressInputBoundary interactor = new ViewProgressInteractor(gateway, presenter);
        ViewProgressController controller = new ViewProgressController(interactor);
        navigateView.setViewProgressController(controller);
        return this;
    }

    public JFrame build() {
        navigateViewModel = new NavigateViewModel();
        clearHistoryViewModel = new ClearHistoryViewModel();
        homeView = new HomeView(viewManagerModel);
        navigateView = new NavigateView();
        addView(homeView, HomeView.VIEW_NAME);
        addView(navigateView, NavigateView.VIEW_NAME);
        QuitGameController quitController = new QuitGameController();
        SaveProgressController saveController = new SaveProgressController(null);
        QuitGameDialog quitDialog = new QuitGameDialog(quitController, saveController);
        SaveGameDialog saveDialog = new SaveGameDialog(saveController);
        quitController.setShowQuitDialog(quitDialog::show);
        quitController.setShowSaveDialog(saveDialog::show);
        navigateView.setQuitGameController(quitController, saveController);
        addClearHistoryUseCase();
        JFrame window = new JFrame("UofT Adventure Game");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(900, 650);
        window.setResizable(false);

        window.add(cardPanel);

        viewManagerModel.setState(initialViewName);
        viewManagerModel.firePropertyChange();

        window.setVisible(true);
        return window;
    }
}
