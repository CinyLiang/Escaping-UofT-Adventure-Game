package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.clear_history.ClearHistoryController;
import interface_adapter.clear_history.ClearHistoryPresenter;
import interface_adapter.navigate.NavigateViewModel;
import use_case.clear_history.ClearHistoryInputBoundary;
import interface_adapter.clear_history.ClearHistoryViewModel;
import use_case.clear_history.ClearHistoryInteractor;
import use_case.clear_history.ClearHistoryOutputBoundary;
import view.HomeView;
import view.InstructionsView;
import view.NavigateView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

// Save Progress imports
import interface_adapter.save_progress.SaveProgressController;
import interface_adapter.save_progress.SaveProgressPresenter;
import use_case.save_progress.SaveProgressInputBoundary;
import use_case.save_progress.SaveProgressInteractor;
import use_case.save_progress.SaveProgressOutputBoundary;
import use_case.save_progress.SaveProgressDataAccessInterface;

// View Progress imports
import interface_adapter.view_progress.ViewProgressController;
import interface_adapter.view_progress.ViewProgressPresenter;
import interface_adapter.view_progress.ViewProgressViewModel;

import use_case.view_progress.ViewProgressInputBoundary;
import use_case.view_progress.ViewProgressInteractor;
import use_case.view_progress.ViewProgressOutputBoundary;
import use_case.view_progress.ViewProgressDataAccessInterface;

public class AppBuilder {

    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    public final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    // ViewModels
    private NavigateViewModel navigateViewModel;
    private ClearHistoryViewModel clearHistoryViewModel;
    private ViewProgressViewModel viewProgressViewModel;

    // Views
    private HomeView homeView;
    private NavigateView navigateView;
    private InstructionsView instructionsView;

    // Track screen
    private String initialViewName = null;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    public AppBuilder addView(JPanel view, String name) {
        cardPanel.add(view, name);

        // first view becomes initial view
        if (initialViewName == null) {
            initialViewName = name;
        }
        return this;
    }

    public AppBuilder addClearHistoryUseCase() {
        ClearHistoryOutputBoundary outputBoundary =
                new ClearHistoryPresenter(clearHistoryViewModel);
        ClearHistoryInputBoundary inputBoundary =
                new ClearHistoryInteractor(outputBoundary);
        ClearHistoryController controller =
                new ClearHistoryController(inputBoundary);
        navigateView.setClearHistoryController(controller);
        return this;
    }

    // Save Progress Use Case
    public AppBuilder addSaveProgressUseCase(SaveProgressDataAccessInterface saveGateway) {
        SaveProgressOutputBoundary presenter = new SaveProgressPresenter();
        SaveProgressInputBoundary interactor = new SaveProgressInteractor(saveGateway, presenter);
        SaveProgressController controller = new SaveProgressController(interactor);

        NavigateView.setSaveProgressController(controller);
        return this;
    }

    public AppBuilder addClearHistoryUseCase() {

        ClearHistoryPresenter presenter =
                new ClearHistoryPresenter(clearHistoryViewModel);
        ClearHistoryInteractor interactor =
                new ClearHistoryInteractor(presenter);
        ClearHistoryController controller =
                new ClearHistoryController(interactor);
        navigateView.setClearHistoryController(controller);
        navigateView.setClearHistoryViewModel(clearHistoryViewModel);

        return this;
    }

    // View Progress Use Case
    public AppBuilder addViewProgressUseCase(ViewProgressDataAccessInterface viewGateway) {
        viewProgressViewModel = new ViewProgressViewModel();
        ViewProgressOutputBoundary presenter = new ViewProgressPresenter(viewProgressViewModel);
        ViewProgressInputBoundary interactor = new ViewProgressInteractor(viewGateway, presenter);
        ViewProgressController controller = new ViewProgressController(interactor);

        NavigateView.setViewProgressController(controller);
        return this;
    }

    public JFrame build() {

        // ViewModels
        navigateViewModel = new NavigateViewModel();
        clearHistoryViewModel = new ClearHistoryViewModel();

        // Create Views
        homeView = new HomeView(viewManagerModel);
        navigateView = new NavigateView();
        instructionsView = new InstructionsView();

        // Register views
        addView(homeView, HomeView.VIEW_NAME);
        addView(navigateView, NavigateView.VIEW_NAME);
        addView(instructionsView, InstructionsView.VIEW_NAME);

        // Add use cases
        addClearHistoryUseCase();

        // Build window
        JFrame window = new JFrame("UofT Adventure Game");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(900, 650);
        window.setResizable(false);

        window.add(viewManager.getCardPanel());

        // Show initial view
        viewManagerModel.setState(initialViewName);
        viewManagerModel.firePropertyChange();

        window.setVisible(true);
        return window;
    }
}
