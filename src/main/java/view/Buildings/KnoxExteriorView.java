package view.Buildings;

import javax.swing.*;

import interface_adapter.navigate.Buildings.Knox.KnoxExterior.KnoxExtViewModel;
import interface_adapter.navigate.NavigateController;
import resources.UISettings;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class KnoxExteriorView extends JPanel {
    public static final String VIEW_NAME = "knox_exterior_view";

    // IMAGES + FONT
    private final JLabel knoxExteriorLabel;

    // CONTROLLERS
    private NavigateController navigateController;

    // VIEW MODEL
    private KnoxExtViewModel knoxExtViewModel;

    // DIALOGS

    // NAV UI
    private JTextArea storyArea;
    private JButton continueButton;

    public KnoxExteriorView(KnoxExtViewModel knoxExtViewModel) throws IOException, FontFormatException {
        this.knoxExtViewModel = knoxExtViewModel;
//        setNavigateController();
        this.setLayout(new BorderLayout());
        this.setBackground(UISettings.PARCHMENT_BACKGROUND);

        // insert header image

        storyArea = new JTextArea("A cold wind blows across the open grass of Front Campus, pushing " +
                "you toward the entrance of Knox College. You get the sudden feeling that you aren't walking to the " +
                "building—the building is pulling you in. \n \n You stand before the main archway. Above you, " +
                "the stone is carved into complex, twisting knots. The shadows here are deep, hiding" +
                " the corners of the architecture. \n \n You place your hand on the wood. It " +
                "vibrates slightly, like the purr of a giant, sleeping cat. " +
                "Somewhere deep inside, a pipe organ plays a low, dissonant chord." +
                "\n \n Do you dare enter?");

        // status bar
        JPanel statusBar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        statusBar.setBackground(UISettings.PARCHMENT_BACKGROUND);
        statusBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, UISettings.ACCENT_COLOR));

        this.add(statusBar, BorderLayout.NORTH);

        // scroll pane containing center content
        // map, story, and selector all in here
        JPanel scrollableContent = new JPanel();
        scrollableContent.setLayout(new BoxLayout(scrollableContent, BoxLayout.Y_AXIS));
        scrollableContent.setBackground(UISettings.PARCHMENT_BACKGROUND);
        scrollableContent.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Overall padding

        // knox exterior image
        ImageIcon knoxExt = UISettings.knoxExtImage;
        int newWidth = 500;
        int newHeight = (knoxExt.getIconHeight() * newWidth) / knoxExt.getIconWidth();
        Image scaledKnoxExt = knoxExt.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledKnoxExtIcon = new ImageIcon(scaledKnoxExt);

        knoxExteriorLabel = new JLabel(scaledKnoxExtIcon);
        knoxExteriorLabel.setHorizontalAlignment(JLabel.CENTER);
        knoxExteriorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollableContent.add(knoxExteriorLabel);
        scrollableContent.add(Box.createVerticalStrut(25));

        // storyArea
        // note: moved story text out of its own scroll pane to the main scroll pane
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
        JPanel bottomPanel = new JPanel(new GridLayout(1, 4, 20, 0));
        bottomPanel.setBackground(UISettings.ACCENT_COLOR);


        // Continue button
        continueButton = makeButton("Continue inside Knox College");
        bottomPanel.add(continueButton);

        this.add(bottomPanel, BorderLayout.SOUTH);

        // action listeners (button + dropdown logic)
        continueButton.addActionListener(e -> {
            if (navigateController != null) {
                System.out.println("Move from knox exterior to knox interior");
                navigateController.execute("Knox Interior");
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

}