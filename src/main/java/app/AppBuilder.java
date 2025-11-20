package app;

import interface_adapter.ViewManagerModel;
import view.ViewManager;

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
