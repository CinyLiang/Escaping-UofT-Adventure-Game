package view.Buildings;

import javax.swing.*;

// Updated import to reflect the ViewModel for the Interior (if it exists)
import interface_adapter.navigate.Buildings.UC.UCInterior.UCIntViewModel;
import interface_adapter.navigate.Buildings.UC.UCInterior.UCIntViewState;
import interface_adapter.navigate.NavigateController;
import interface_adapter.play_card_game.CardGameState;
import interface_adapter.trivia_game.TriviaGameState;
import resources.UISettings;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class UCInteriorView extends JPanel implements PropertyChangeListener {
    public static final String VIEW_NAME = "uc_interior_view";

    // IMAGES + FONT
    // Updated Label Name
    private final JLabel ucInteriorLabel;

    // CONTROLLERS
    private NavigateController navigateController;

    // VIEW MODEL
    // Updated ViewModel Reference
    private UCIntViewModel ucIntViewModel;

    // DIALOGS

    // NAV UI
    private JTextArea storyArea;
    private JButton continueButton;

    // Updated Constructor Name and ViewModel Parameter
    public UCInteriorView(UCIntViewModel ucIntViewModel) throws IOException, FontFormatException {
        this.ucIntViewModel = ucIntViewModel;
        this.ucIntViewModel.addPropertyChangeListener(this);
        this.setLayout(new BorderLayout());
        this.setBackground(UISettings.PARCHMENT_BACKGROUND);

        storyArea = new JTextArea("uc interior story text");

        // status bar
        JPanel statusBar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        statusBar.setBackground(UISettings.PARCHMENT_BACKGROUND);
        statusBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, UISettings.ACCENT_COLOR));

        this.add(statusBar, BorderLayout.NORTH);

        // scroll pane containing center content
        JPanel scrollableContent = new JPanel();
        scrollableContent.setLayout(new BoxLayout(scrollableContent, BoxLayout.Y_AXIS));
        scrollableContent.setBackground(UISettings.PARCHMENT_BACKGROUND);
        scrollableContent.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Overall padding

        // uc interior image
        ImageIcon ucInt = UISettings.ucIntImage;
        int newWidth = 500;
        int newHeight = (ucInt.getIconHeight() * newWidth) / ucInt.getIconWidth();
        Image scaledUcExt = ucInt.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledUcIntIcon = new ImageIcon(scaledUcExt);

        ucInteriorLabel = new JLabel(scaledUcIntIcon);
        ucInteriorLabel.setHorizontalAlignment(JLabel.CENTER);
        ucInteriorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollableContent.add(ucInteriorLabel);
        scrollableContent.add(Box.createVerticalStrut(25));

        // storyArea
        storyArea.setEditable(false);
        storyArea.setLineWrap(true);
        storyArea.setWrapStyleWord(true);
        storyArea.setBackground(UISettings.PARCHMENT_BACKGROUND);
        storyArea.setForeground(UISettings.DARK_MAP_TEXT);
        storyArea.setFont(UISettings.texturina);

        scrollableContent.add(storyArea);

        // Wrap the scrollableContent panel in the main JScrollPane
        JScrollPane mainScrollPane = new JScrollPane(scrollableContent);
        mainScrollPane.setBorder(BorderFactory.createEmptyBorder());
        mainScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        mainScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        this.add(mainScrollPane, BorderLayout.CENTER);

        // BOTTOM PANE: game control buttons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomPanel.setBackground(UISettings.ACCENT_COLOR);

        continueButton = makeButton("Play Card Game");
        bottomPanel.add(continueButton);

        this.add(bottomPanel, BorderLayout.SOUTH);

        // action listeners (button logic)
        continueButton.addActionListener(e -> {
            String requestedDirection = "card game"; // maps to "Card game"
            System.out.println("[UcInteriorView] BUTTON CLICKED -> requestedDirection=\"" + requestedDirection + "\", navigateController=" + navigateController);
            if (navigateController == null) {
                System.out.println("[UcInteriorView] BUTTON: navigateController is null");
                return;
            }
            try {
                navigateController.execute(requestedDirection);
                System.out.println("[UcInteriorView] BUTTON: navigateController.execute returned.");
            } catch (Throwable t) {
                System.err.println("[UcInteriorView] BUTTON: Exception while calling navigateController.execute:");
                t.printStackTrace();
            }
        });
    }

    private JButton makeButton(String text) {
        JButton b = new JButton(text);
        b.setBackground(UISettings.PARCHMENT_BACKGROUND);
        b.setForeground(UISettings.ACCENT_COLOR);
        b.setFont(UISettings.quintessential.deriveFont(Font.PLAIN, 18));
        b.setFocusPainted(false);
        b.setOpaque(true);

        //border
        b.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UISettings.ACCENT_COLOR, 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        // hover effect
        b.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                b.setBackground(UISettings.HOVER_COLOR);
                b.setForeground(UISettings.DARK_MAP_TEXT);
                b.repaint();
            }

            public void mouseExited(MouseEvent evt) {
                b.setBackground(UISettings.PARCHMENT_BACKGROUND);
                b.setForeground(UISettings.ACCENT_COLOR);
                b.repaint();
            }
        });
        return b;
    }

    // ACTION LISTENERS
    public void setNavigateController(NavigateController navigateController) {
        this.navigateController = navigateController;
    }

    public void propertyChange(PropertyChangeEvent evt) {
        UCIntViewState state = ucIntViewModel.getState();
    }

}