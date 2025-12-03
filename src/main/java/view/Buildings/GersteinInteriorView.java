package view.Buildings;

import javax.swing.*;

// Updated import to reflect the ViewModel for the Interior (if it exists)
import interface_adapter.navigate.Buildings.Gerstein.GersteinInterior.GersteinIntViewModel;
import interface_adapter.navigate.Buildings.Gerstein.GersteinInterior.GersteinIntViewState;
import interface_adapter.navigate.NavigateController;
import interface_adapter.play_card_game.CardGameState;
import interface_adapter.trivia_game.TriviaGameState;
import view.theme.UISettings;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

// Updated Class Name
public class GersteinInteriorView extends JPanel implements PropertyChangeListener {
    // Updated View Name Constant
    public static final String VIEW_NAME = "gerstein_interior_view";

    // IMAGES + FONT
    // Updated Label Name
    private final JLabel gersteinInteriorLabel;

    // CONTROLLERS
    private NavigateController navigateController;

    // VIEW MODEL
    // Updated ViewModel Reference
    private GersteinIntViewModel gersteinIntViewModel;

    // DIALOGS

    // NAV UI
    private JTextArea storyArea;
    private JButton continueButton;

    // Updated Constructor Name and ViewModel Parameter
    public GersteinInteriorView(GersteinIntViewModel gersteinIntViewModel) throws IOException, FontFormatException {
        this.gersteinIntViewModel = gersteinIntViewModel;
        this.gersteinIntViewModel.addPropertyChangeListener(this);
        this.setLayout(new BorderLayout());
        this.setBackground(UISettings.PARCHMENT_BACKGROUND);

        storyArea = new JTextArea("You step inside the library. The air is warm and still, " +
                "thick with the scent of aged paper and beeswax. The light here is low—a golden, " +
                "scholarly glow cast by dozens of desk lamps that line the long, ancient reading " +
                "tables. Towering vaulted ceilings stretch far above, lost in shadow, " +
                "while massive, two-story bookshelves line every wall.\n\n" +
                "You feel utterly insignificant, pressed between millennia of collected wisdom." +
                " The only sound is the subtle scratch of quill on vellum from unseen corners, " +
                "and the slow, heavy tick of a grandfather clock somewhere in the darkness.\n\n" +
                "A wide, empty table beckons you to sit. On the table's edge lies a single " +
                "tarnished brass gear next to an open, hand-written journal. " +
                "Its pages are covered in complex celestial diagrams.\n\n" +
                "Do you examine the journal?");

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

        // gerstein interior image
        ImageIcon gersteinInt = UISettings.gersteinIntImage;
        int newWidth = 500;
        int newHeight = (gersteinInt.getIconHeight() * newWidth) / gersteinInt.getIconWidth();
        Image scaledGersteinExt = gersteinInt.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledGersteinIntIcon = new ImageIcon(scaledGersteinExt);

        gersteinInteriorLabel = new JLabel(scaledGersteinIntIcon);
        gersteinInteriorLabel.setHorizontalAlignment(JLabel.CENTER);
        gersteinInteriorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollableContent.add(gersteinInteriorLabel);
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

        continueButton = makeButton("Play Trivia Game");
        bottomPanel.add(continueButton);

        this.add(bottomPanel, BorderLayout.SOUTH);

        // action listeners (button logic)
        continueButton.addActionListener(e -> {
            String requestedDirection = "trivia game"; // maps to "trivia game"
            System.out.println("[GersteinInteriorView] BUTTON CLICKED -> requestedDirection=\"" + requestedDirection + "\", navigateController=" + navigateController);
            if (navigateController == null) {
                System.out.println("[GersteinInteriorView] BUTTON: navigateController is null");
                return;
            }
            try {
                navigateController.execute(requestedDirection);
                System.out.println("[GersteinInteriorView] BUTTON: navigateController.execute returned.");
            } catch (Throwable t) {
                System.err.println("[GersteinInteriorView] BUTTON: Exception while calling navigateController.execute:");
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
        GersteinIntViewState state = gersteinIntViewModel.getState();
    }

}