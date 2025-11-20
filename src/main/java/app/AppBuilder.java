package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.clear_history.ClearHistoryController;
import interface_adapter.clear_history.ClearHistoryPresenter;
import interface_adapter.navigate.NavigateViewModel;
import use_case.clear_history.ClearHistoryInputBoundary;
import use_case.clear_history.ClearHistoryInteractor;
import use_case.clear_history.ClearHistoryOutputBoundary;
import view.NavigateView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

// TODO: Add imports
import interface_adapter.clear_history.ClearHistoryController;
import interface_adapter.clear_history.ClearHistoryPresenter;

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    // TODO: Add Factories
    final ViewManagerModel viewManagerModel = new ViewManagerModel();
    ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    // TODO: Implement DAO

    // TODO: Implement Views and View Models
    private NavigateViewModel NavigateViewModel;
    private NavigateView NavigateView;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    public AppBuilder addView(JPanel view, String viewName) {
        cardPanel.add(view, viewName);
        if (initialViewName == null) {
            initialViewName = viewName;
        }
        return this;
    }

    // TODO: Implement add<item>UseCase()
    public AppBuilder add___UseCase() {
        /* Structure:
        final <item>OutputBoundary i = new <item>Presenter(...);
        final <item>InputBoundary j = new <item>Interactor>(...);
        <item>Controller k = new <item>Controller(...);
        <item>View.setController(k) // (private variable in AppBuilder)
        return this;
         */
    }

    public AppBuilder addClearHistoryUseCase() {
        final ClearHistoryOutputBoundary outputBoundary = new ClearHistoryPresenter(NavigateViewModel);
        final ClearHistoryInputBoundary inputBoundary = new ClearHistoryInteractor(outputBoundary);
        ClearHistoryController controller = new ClearHistoryController(inputBoundary);
        NavigateView.setClearHistoryController(controller);
        return this;
    }

    public JFrame build() {
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
