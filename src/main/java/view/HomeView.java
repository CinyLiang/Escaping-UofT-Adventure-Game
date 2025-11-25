package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.navigate.NavigateViewModel;

import javax.swing.*;
import java.awt.*;

public class HomeView extends JPanel {

    public static final String VIEW_NAME = "home_view";

    private final JButton startButton;

    public HomeView(ViewManagerModel viewManagerModel) {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);

        // TITLE
        JLabel title = new JLabel("Escaping UofT Adventure Game");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 40));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(80, 0, 20, 0));
        this.add(title, BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setBackground(Color.BLACK);
        this.add(center, BorderLayout.CENTER);

        // START BUTTON
        JPanel bottom = new JPanel();
        bottom.setBackground(Color.BLACK);

        startButton = new JButton("Start Game");
        startButton.setPreferredSize(new Dimension(200, 55));
        startButton.setFont(new Font("Arial", Font.BOLD, 22));
        startButton.setBackground(new Color(70, 130, 180));
        startButton.setForeground(Color.WHITE);
        startButton.setFocusPainted(false);

        // Navigation
        startButton.addActionListener(e -> {
            viewManagerModel.setState(new NavigateViewModel().getViewName()); // ????
            viewManagerModel.firePropertyChange();
        });

        bottom.add(startButton);
        bottom.setBorder(BorderFactory.createEmptyBorder(40, 0, 60, 0));

        this.add(bottom, BorderLayout.SOUTH);
    }

    public JButton getStartButton() {
        return startButton;
    }
}
