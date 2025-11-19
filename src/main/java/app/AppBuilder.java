package main.java.app;

import main.java.interface_adapter.ViewManagerModel;
import main.java.view.ViewManager;

import javax.swing.*;
import java.awt.*;

// TODO: Add imports

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    // TODO: Add Factories
    final ViewManagerModel viewManagerModel = new ViewManagerModel();
    ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    // TODO: Implement DAO

    // TODO: Implement Views and View Models

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    // TODO: Implement add<item>View()
    public AppBuilder add___View() {
        /* Structure:
        <item>ViewModel i = new <item>ViewModel(...);
        <item>View j = new <item>View(...);
        cardPanel.add(j, j.getViewName());
        return this;
         */
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

    public JFrame build() {
        final JFrame application = new JFrame("User Login Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(signupView.getViewName());
        viewManagerModel.firePropertyChange();

        return application;
    }


}