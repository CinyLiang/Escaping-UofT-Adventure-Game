package view.Buildings;

import javax.swing.*;

import interface_adapter.navigate.Buildings.UC.UCExterior.UCExtViewModel;
import interface_adapter.navigate.NavigateController;
import resources.UISettings;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class UCExteriorView extends JPanel {
    public static final String VIEW_NAME = "uc_exterior_view";

    // IMAGES + FONT
    private final JLabel ucExteriorLabel;

    // CONTROLLERS
    private NavigateController navigateController;

    // VIEW MODEL
    private UCExtViewModel ucExtViewModel;

    // DIALOGS

    // NAV UI
    private JTextArea storyArea;
    private JButton continueButton;

    public UCExteriorView(UCExtViewModel ucExtViewModel) throws IOException, FontFormatException {
        this.ucExtViewModel = ucExtViewModel;
        this.setLayout(new BorderLayout());
        this.setBackground(UISettings.PARCHMENT_BACKGROUND);

        // insert header image

        storyArea = new JTextArea("A chilling mist clings to the Front Campus lawn, rising to meet" +
                " the gray, imposing walls of University College. The building's architecture is a" +
                " tapestry of ancient stonework and twisting spires, seeming less like a " +
                "campus building and more like a fortress holding a secret.\n\n" +
                "You stand directly before the heavy, recessed main archway. Above you, " +
                "the stone carvings are eerily still, yet they seem to shift in your peripheral " +
                "vision. The tall, gothic windows glow with a deep, unsettling yellow light, " +
                "casting sharp, elongated shadows onto the damp grass.\n\n" +
                "The silence is broken only by the hush of wind through the heavy ivy that" +
                " clings to the walls, whispering secrets in a language you almost recognize." +
                " A sense of profound age and stubborn memory presses down on you here.\n\n" +
                "As you step onto the lowest stone stair, you notice a small, smooth piece" +
                " of obsidian tucked into the crevice of the stone. It is cold to the" +
                " touch and seems to pulse faintly with an inner heat.\n\n" +
                "Do you dare step across the threshold?");

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

        // uc exterior image
        ImageIcon ucExt = UISettings.ucExtImage;
        int newWidth = 500;
        int newHeight = (ucExt.getIconHeight() * newWidth) / ucExt.getIconWidth();
        Image scaledUcExt = ucExt.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledUcExtIcon = new ImageIcon(scaledUcExt);

        ucExteriorLabel = new JLabel(scaledUcExtIcon);
        ucExteriorLabel.setHorizontalAlignment(JLabel.CENTER);
        ucExteriorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollableContent.add(ucExteriorLabel);
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
        continueButton = makeButton("Continue inside University College");
        bottomPanel.add(continueButton);

        this.add(bottomPanel, BorderLayout.SOUTH);

        // action listeners (button + dropdown logic)
        continueButton.addActionListener(e -> {
            if (navigateController != null) {
                System.out.println("Move from uc exterior to uc interior");
                navigateController.execute("UC Interior");
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