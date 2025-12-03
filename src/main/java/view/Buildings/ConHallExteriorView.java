package view.Buildings;

import javax.swing.*;

import interface_adapter.navigate.Buildings.ConHall.ConHallExterior.ConHallExtViewModel;
import interface_adapter.navigate.NavigateController;
import view.theme.UISettings;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class ConHallExteriorView extends JPanel {
    public static final String VIEW_NAME = "conHall_exterior_view";

    // IMAGES + FONT
    private final JLabel conHallExteriorLabel;

    // CONTROLLERS
    private NavigateController navigateController;

    // VIEW MODEL
    private ConHallExtViewModel conHallExtViewModel;

    // DIALOGS

    // NAV UI
    private JTextArea storyArea;
    private JButton continueButton;

    public ConHallExteriorView(ConHallExtViewModel conHallExtViewModel) throws IOException, FontFormatException {
        this.conHallExtViewModel = conHallExtViewModel;
//        setNavigateController();
        this.setLayout(new BorderLayout());
        this.setBackground(UISettings.PARCHMENT_BACKGROUND);

        // insert header image

        storyArea = new JTextArea("A cool, damp wind swirls around you as you reach the wide, grand stairs of Convocation Hall. The building is imposing, capped by a magnificent dome that mirrors the starry, empty sky above. Unlike the other colleges, this structure feels more like a civic monument—a vault for solemn agreements.\n" +
                "\n" +
                "You notice immediately that the massive, central bronze doors are not just closed, they are sealed. Heavy, corroded iron chains are crisscrossed over the entrance, fastened by an enormous, intricately carved padlock. A brass plaque gleams dully beneath the lock.\n" +
                "\n" +
                "It reads: \"SEALED UNTIL FINAL VERDICT.\"\n" +
                "\n" +
                "The hall is not merely locked; it is under judgment. You realize the bright yellow light spilling from the arched windows isn't welcoming, it's illuminating a continuous, centuries-old trial.\n" +
                "\n" +
                "You run your hand along the granite balustrade, and a subtle energy prickles your skin. You realize that the lock requires three distinct Keys—one for each of the major faculties—before the final judgment can be passed and the Hall opened.");

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

        // conHall exterior image
        ImageIcon conHallExt = UISettings.conHallExtImage;
        int newWidth = 500;
        int newHeight = (conHallExt.getIconHeight() * newWidth) / conHallExt.getIconWidth();
        Image scaledConHallExt = conHallExt.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledConHallExtIcon = new ImageIcon(scaledConHallExt);

        conHallExteriorLabel = new JLabel(scaledConHallExtIcon);
        conHallExteriorLabel.setHorizontalAlignment(JLabel.CENTER);
        conHallExteriorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollableContent.add(conHallExteriorLabel);
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

        // Bottom panel
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomPanel.setBackground(UISettings.ACCENT_COLOR);

        // Continue button
        continueButton = makeButton("Continue inside Convocation Hall");
        bottomPanel.add(continueButton);

        this.add(bottomPanel, BorderLayout.SOUTH);

        // action listeners (button + dropdown logic)
        continueButton.addActionListener(e -> {
            if (navigateController != null) {
                System.out.println("Move from conHall exterior to conHall interior");
                navigateController.execute("Con hall Interior");
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