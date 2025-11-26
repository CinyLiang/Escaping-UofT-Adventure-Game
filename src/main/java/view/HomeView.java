package view;

import interface_adapter.ViewManagerModel;
import view.ThemeManager;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class HomeView extends JPanel {

    public static final String VIEW_NAME = "home_view";
    private Image backgroundImage;

    private final JButton startButton;
    private final JButton continueButton;
    private final JButton settingsButton;
    private final JButton rulesButton;
    private final JButton quitButton;
    private final JLabel title;
    private final JLabel footer;

    public HomeView(ViewManagerModel viewManagerModel) {

        loadBackgroundImage();
        setLayout(new BorderLayout());
        setOpaque(true);

        // Title
        title = new JLabel("ESCAPE UofT", SwingConstants.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(60, 0, 40, 0));
        add(title, BorderLayout.NORTH);

        // Center
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        startButton = createButton("START NEW ADVENTURE");
        continueButton = createButton("CONTINUE EXPLORATION");
        settingsButton = createButton("GAME SETTINGS");
        rulesButton = createButton("ABOUT THE GAME");
        quitButton = createButton("EXIT TO DESKTOP");

        addButton(centerPanel, startButton);
        addButton(centerPanel, continueButton);
        addButton(centerPanel, settingsButton);
        addButton(centerPanel, rulesButton);
        addButton(centerPanel, quitButton);

        add(centerPanel, BorderLayout.CENTER);

        // Footer
        footer = new JLabel("CSC207 Final Project - TUT0501 Group10");
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setOpaque(false);
        footerPanel.add(footer, BorderLayout.WEST);
        add(footerPanel, BorderLayout.SOUTH);

        // Theme Colour
        applyTheme();

        viewManagerModel.addPropertyChangeListener(evt -> applyTheme());

        // Buttons actions
        startButton.addActionListener(e -> {
            viewManagerModel.setState(NavigateView.VIEW_NAME);
            viewManagerModel.firePropertyChange();
        });

        continueButton.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Loading saved game...")
        );

        settingsButton.addActionListener(e -> {
            viewManagerModel.setState(SettingsView.VIEW_NAME);
            viewManagerModel.firePropertyChange();
        });

        rulesButton.addActionListener(e -> {
            viewManagerModel.setState(InstructionsView.VIEW_NAME);
            viewManagerModel.firePropertyChange();
        });

        quitButton.addActionListener(e -> System.exit(0));
    }

    // Apply Theme Colors to All UI

    private void applyTheme() {
        setBackground(ThemeManager.getBackground());

        title.setForeground(ThemeManager.getTextPrimary());
        title.setFont(new Font("Serif", Font.BOLD, ThemeManager.getFontSize(52)));

        footer.setForeground(ThemeManager.getTextPrimary());
        footer.setFont(new Font("Arial", Font.PLAIN, ThemeManager.getFontSize(12)));

        styleButton(startButton);
        styleButton(continueButton);
        styleButton(settingsButton);
        styleButton(rulesButton);
        styleButton(quitButton);

        repaint();
    }

    private JButton createButton(String text) {
        JButton b = new JButton(text);
        b.setPreferredSize(new Dimension(320, 55));
        b.setFocusPainted(false);
        b.setOpaque(true);
        return b;
    }

    private void styleButton(JButton b) {
        b.setBackground(ThemeManager.getButtonBackground());
        b.setForeground(ThemeManager.getButtonForeground());
        b.setFont(new Font("Arial", Font.BOLD, ThemeManager.getFontSize(18)));
        b.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ThemeManager.getButtonForeground(), 2),
                BorderFactory.createEmptyBorder(10, 25, 10, 25)
        ));
    }

    private void addButton(JPanel p, JButton b) {
        p.add(Box.createVerticalGlue());
        p.add(b);
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(Box.createRigidArea(new Dimension(0, 15)));
    }

    private void loadBackgroundImage() {
        try {
            URL url = getClass().getResource("/uoft_bg.png");
            if (url != null) {
                backgroundImage = new ImageIcon(url).getImage();
            }
        } catch (Exception ignored) {}
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null && !ThemeManager.isHighContrast()) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        } else {
            g.setColor(ThemeManager.getBackground());
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}