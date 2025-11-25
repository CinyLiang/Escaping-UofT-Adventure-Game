package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.navigate.NavigateViewModel;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class HomeView extends JPanel {

    public static final String VIEW_NAME = "home_view";
    private static final String FONT = "Arial";

    private Image backgroundImage;
    private final String IMAGE_PATH = "/src/main/java/recourses/uoft_bg.png";
    // Menu Buttons
    private final JButton startButton;
    private final JButton continueButton;
    private final JButton settingsButton;
    private final JButton rulesButton;
    private final JButton quitButton;

    public HomeView(ViewManagerModel viewManagerModel) {
        Color uoftBlue = new Color(0, 33, 71); // Navy
        Color highlightGold = new Color(255, 203, 5); // Gold

        loadBackgroundImage();

        this.setOpaque(false);
        this.setLayout(new BorderLayout());

        // TITLE
        JLabel title = new JLabel("ESCAPE UofT");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 52));
        title.setForeground(highlightGold);
        title.setBorder(BorderFactory.createEmptyBorder(60, 0, 40, 0));
        title.setOpaque(false);
        this.add(title, BorderLayout.NORTH);

        // CENTER SECTION
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        // Buttons
        startButton = createMenuButton("START NEW ADVENTURE");
        continueButton = createMenuButton("CONTINUE EXPLORATION");
        settingsButton = createMenuButton("GAME SETTINGS");
        rulesButton = createMenuButton("ABOUT THE GAME");
        quitButton = createMenuButton("EXIT TO DESKTOP");

        centerPanel.add(Box.createVerticalGlue());

        centerPanel.add(startButton);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        centerPanel.add(continueButton);
        continueButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        centerPanel.add(settingsButton);
        settingsButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        centerPanel.add(rulesButton);
        rulesButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        centerPanel.add(quitButton);
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(Box.createVerticalGlue());

        this.add(centerPanel, BorderLayout.CENTER);

        // FOOTER
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setOpaque(false);

        JLabel footer = new JLabel("CSC207 Final Project - TUT0501 Group10");
        footer.setForeground(highlightGold);
        footer.setFont(new Font(FONT, Font.PLAIN, 12));
        footer.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 0));
        footerPanel.add(footer, BorderLayout.WEST);

        this.add(footerPanel, BorderLayout.SOUTH);

        // 1. Start New Adventure (Navigates to the main game view)
        startButton.addActionListener(e -> {
            viewManagerModel.setActiveView(new NavigateViewModel().getViewName());
            viewManagerModel.firePropertyChange();
        });

        // 2. Continue Exploration
        continueButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Loading saved game...");
        });

        // 3. Game Setting
        settingsButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Settings screen feature pending...");
        });

        // 4. About the Game (InstructionsView)
        rulesButton.addActionListener(e -> {
            viewManagerModel.setActiveView(InstructionsView.VIEW_NAME);
            viewManagerModel.firePropertyChange();
        });

        // 5. Exit
        quitButton.addActionListener(e -> {
            System.exit(0);
        });
    }

    private void loadBackgroundImage() {
        try {
            URL imageUrl = getClass().getResource(IMAGE_PATH);
            if (imageUrl != null) {
                backgroundImage = new ImageIcon(imageUrl).getImage();
            } else {
                System.err.println("Background image not found at path: " + IMAGE_PATH);
                this.setBackground(new Color(0, 33, 71));
            }
        } catch (Exception e) {
            System.err.println("Error loading background image: " + e.getMessage());
            this.setBackground(new Color(0, 33, 71));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        } else {
            g.setColor(this.getBackground());
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
    }

    private JButton createMenuButton(String text) {
        JButton b = new JButton(text);

        Color highlightGold = new Color(255, 203, 5);
        Color uoftBlue = new Color(0, 33, 71);

        b.setPreferredSize(new Dimension(300, 50));
        b.setFont(new Font(FONT, Font.BOLD, 18));

        b.setBackground(uoftBlue);
        b.setForeground(highlightGold);
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(highlightGold, 2),
                BorderFactory.createEmptyBorder(10, 25, 10, 25)
        ));

        b.setOpaque(true);
        return b;
    }

    public JButton getStartButton() {
        return startButton;
    }
}